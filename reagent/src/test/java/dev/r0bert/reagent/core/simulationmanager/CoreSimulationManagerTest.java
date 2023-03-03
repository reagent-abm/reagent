package dev.r0bert.reagent.core.simulationmanager;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoreSimulationManagerTest {
    @Test
    public void getStartTime_Always_ReturnsStartTime() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertEquals(startTime, simulationManager.getStartTime());
    }

    @Test
    public void getEndTime_Always_ReturnsEndTime() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertEquals(endTime, simulationManager.getEndTime());
    }

    @Test
    public void getCurrentTime_AtStart_ReturnsCurrentTime() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertEquals(startTime, simulationManager.getCurrentTime());
    }

    @Test
    public void getCurrentTime_AfterStart_ReturnsCurrentTime() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(1), true);
        assertEquals(startTime.plusHours(1), simulationManager.getCurrentTime());
    }

    @Test
    public void scheduleMessage_WhenTimeBeforeStartTime_ThrowsException() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        var message = mock(Message.class);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.scheduleMessage(message,
                startTime.minusHours(1)));
    }

    @Test
    public void scheduleMessage_WhenTimeAfterEndTime_ThrowsException() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        var message = mock(Message.class);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.scheduleMessage(message,
                endTime.plusHours(1)));
    }

    @Test
    public void scheduleMessage_WhenTimeBeforeCurrentTime_ThrowsException() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(2), true);
        var message = mock(Message.class);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.scheduleMessage(message,
                startTime.plusHours(1)));
    }

    @Test
    public void scheduleMessageAndRun_WhenValid_SchedulesAndSendsMessage() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        var agent = mock(Agent.class);
        var agentUuid = UUID.randomUUID();
        var message = mock(Message.class);
        when(message.getDestination()).thenReturn(agentUuid);
        when(agent.getUUID()).thenReturn(agentUuid);
        simulationManager.addAgent(agent);
        simulationManager.scheduleMessage(message, startTime.plusHours(1));
        simulationManager.run();
        verify(agent, times(1)).handleMessage(message);
        assertEquals(startTime.plusHours(1), simulationManager.getCurrentTime());
    }

    @Test
    public void sendMessageNow_WhenValid_SendsMessage() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        var agent = mock(Agent.class);
        var agentUuid = UUID.randomUUID();
        var message = mock(Message.class);
        var currentTime = startTime.plusHours(1);
        FieldUtils.writeField(simulationManager, "currentTime", currentTime, true);
        when(message.getDestination()).thenReturn(agentUuid);
        when(agent.getUUID()).thenReturn(agentUuid);
        simulationManager.addAgent(agent);
        simulationManager.sendMessageNow(message);
        simulationManager.run();
        verify(agent, times(1)).handleMessage(message);
        assertEquals(currentTime, simulationManager.getCurrentTime());
    }

    @Test
    public void checkTimeIsValid_WhenTimeBeforeStartTime_ThrowsException() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.checkTimeIsValid(startTime.minusHours(1)));
    }

    @Test
    public void checkTimeIsValid_WhenTimeAfterEndTime_ThrowsException() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.checkTimeIsValid(endTime.plusHours(1)));
    }

    @Test
    public void checkTimeIsValid_WhenTimeBeforeCurrentTime_ThrowsException() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(2), true);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.checkTimeIsValid(startTime.plusHours(1)));
    }

    @Test
    public void checkTimeIsValid_WhenTimeAfterCurrentTime_DoesNotThrowException() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(2), true);
        assertDoesNotThrow(() -> simulationManager.checkTimeIsValid(startTime.plusHours(3)));
    }

    @Test
    public void runForTime_WhenTimeBeforeStartTime_ThrowsException() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.runForTime(startTime.minusHours(1)));
    }

    @Test
    public void runForTime_WhenTimeAfterEndTime_ThrowsException() {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.runForTime(endTime.plusHours(1)));
    }

    @Test
    public void runForTime_WhenTimeBeforeCurrentTime_ThrowsException() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(2), true);
        assertThrows(IllegalArgumentException.class, () -> simulationManager.runForTime(startTime.plusHours(1)));
    }

    @Test
    public void runForTime_WhenTimeAfterCurrentTime_DoesNotThrowException() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(2), true);
        assertDoesNotThrow(() -> simulationManager.runForTime(startTime.plusHours(3)));
    }

    @Test
    public void runForTime_WhenTimeAfterCurrentTime_RunsForTimeAndSendsMessages() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusDays(1);
        var simulationManager = new CoreSimulationManager(startTime, endTime);
        FieldUtils.writeField(simulationManager, "currentTime", startTime.plusHours(2), true);
        var agent = mock(Agent.class);
        var agentUuid = UUID.randomUUID();
        var message = mock(Message.class);
        when(message.getDestination()).thenReturn(agentUuid);
        when(agent.getUUID()).thenReturn(agentUuid);
        simulationManager.addAgent(agent);
        simulationManager.scheduleMessage(message, startTime.plusHours(3));
        simulationManager.runForTime(startTime.plusHours(3));
        verify(agent, times(1)).handleMessage(message);
    }
}