package dev.r0bert.reagent.core.messages.regularevent;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.simulationmanager.SimulationManager;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegularEventMessageSpecificationTest {
    @Test
    public void constructor_WhenUUIDNull_AssignsRandomUUID() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var spec1 = new RegularEventMessageSpecification(agent, null);
        var spec2 = new RegularEventMessageSpecification(agent, null);

        assertNotEquals(spec1.getUUID(), spec2.getUUID());
    }

    @Test
    public void constructor_WhenUUIDNotNull_AssignsUUID() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var uuid = UUID.randomUUID();
        var spec = new RegularEventMessageSpecification(agent, uuid);

        assertEquals(uuid, spec.getUUID());
    }

    @Test
    public void toString_Always_ReturnsCorrectRepresentation() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        when(agent.toString()).thenReturn("Agent1");
        var uuid = UUID.randomUUID();
        var expected = "RegularEventMessageSpecification{agent=Agent1, uuid=" + uuid + "}";
        var spec = new RegularEventMessageSpecification(agent, uuid);
        assertEquals(expected, spec.toString());
    }

    @Test
    public void createAndScheduleRegularEventMessages_WhenStartTimeIsAfterEndTime_ThrowsException() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var simulationManager = mock(SimulationManager.class);
        var startTime = ZonedDateTime.now();
        var endTime = startTime.minusSeconds(1);
        var interval = Duration.ofSeconds(1);
        var spec = new RegularEventMessageSpecification(agent, null);
        assertThrows(IllegalArgumentException.class, () -> spec.createAndScheduleRegularEventMessages(simulationManager, interval, startTime, endTime));
    }

    @Test
    public void createAndScheduleRegularEventMessages_WhenStartTimeEqualsEndTime_ThrowsException() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var simulationManager = mock(SimulationManager.class);
        var startTime = ZonedDateTime.now();
        var interval = Duration.ofSeconds(1);
        var spec = new RegularEventMessageSpecification(agent, null);
        assertThrows(IllegalArgumentException.class, () -> spec.createAndScheduleRegularEventMessages(simulationManager, interval, startTime, startTime));
    }

    @Test
    public void createAndScheduleRegularEventMessages_WhenIntervalIsZero_ThrowsException() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var simulationManager = mock(SimulationManager.class);
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusSeconds(1);
        var interval = Duration.ZERO;
        var spec = new RegularEventMessageSpecification(agent, null);
        assertThrows(IllegalArgumentException.class, () -> spec.createAndScheduleRegularEventMessages(simulationManager, interval, startTime, endTime));
    }

    @Test
    public void createAndScheduleRegularEventMessages_WhenIntervalIsNegative_ThrowsException() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var simulationManager = mock(SimulationManager.class);
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusSeconds(1);
        var interval = Duration.ofSeconds(-1);
        var spec = new RegularEventMessageSpecification(agent, null);
        assertThrows(IllegalArgumentException.class, () -> spec.createAndScheduleRegularEventMessages(simulationManager, interval, startTime, endTime));
    }

    @Test
    public void createAndScheduleRegularEventMessages_WhenValid_CreatesAndSchedules() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var simulationManager = mock(SimulationManager.class);
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusSeconds(2);
        var interval = Duration.ofSeconds(1);
        var spec = new RegularEventMessageSpecification(agent, null);
        spec.createAndScheduleRegularEventMessages(simulationManager, interval, startTime, endTime);
        verify(simulationManager, times(1)).scheduleMessage(any(RegularEventMessage.class), eq(startTime));
        verify(simulationManager, times(1)).scheduleMessage(any(RegularEventMessage.class), eq(startTime.plusSeconds(1)));
    }
}