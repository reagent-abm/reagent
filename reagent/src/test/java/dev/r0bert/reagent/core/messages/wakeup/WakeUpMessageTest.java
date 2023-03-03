package dev.r0bert.reagent.core.messages.wakeup;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.simulationmanager.SimulationManager;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WakeUpMessageTest {
    @Test
    public void constructor_WhenUUIDNull_AssignsRandomUUID() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var wakeUpTime = ZonedDateTime.now();
        var message1 = new WakeUpMessage(simulationManager, null, agent, wakeUpTime);
        var message2 = new WakeUpMessage(simulationManager, null, agent, wakeUpTime);
        assertNotEquals(message1.getUUID(), message2.getUUID());
    }

    @Test
    public void constructor_WhenUUIDNotNull_AssignsUUID() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var wakeUpTime = ZonedDateTime.now();
        var uuid = UUID.randomUUID();
        var message = new WakeUpMessage(simulationManager, uuid, agent, wakeUpTime);
        assertEquals(uuid, message.getUUID());
    }

    @Test
    public void getDestination_Always_ReturnsAgentUUID() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        var agentUUID = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUUID);
        var wakeUpTime = ZonedDateTime.now();
        var message = new WakeUpMessage(simulationManager, null, agent, wakeUpTime);
        assertEquals(agentUUID, message.getDestination());
    }

    @Test
    public void getSender_Always_ReturnsAgentUUID() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        var agentUUID = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUUID);
        var wakeUpTime = ZonedDateTime.now();
        var message = new WakeUpMessage(simulationManager, null, agent, wakeUpTime);
        assertEquals(agentUUID, message.getSender());
    }

    @Test
    public void getUUID_Always_ReturnsUUID() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var wakeUpTime = ZonedDateTime.now();
        var uuid = UUID.randomUUID();
        var message = new WakeUpMessage(simulationManager, uuid, agent, wakeUpTime);
        assertEquals(uuid, message.getUUID());
    }

    @Test
    public void getWakeTime_Always_ReturnsWakeUpTime() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var wakeUpTime = ZonedDateTime.now();
        var message = new WakeUpMessage(simulationManager, null, agent, wakeUpTime);
        assertEquals(wakeUpTime, message.getWakeTime());
    }

    @Test
    public void toString_Always_ReturnsCorrectRepresentation() {
        var simulationManager = mock(SimulationManager.class);
        var agent = mock(Agent.class);
        var agentUUID = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUUID);
        var wakeUpTime = ZonedDateTime.now();
        var uuid = UUID.randomUUID();
        var expected = "WakeUpMessage{sender=" + agentUUID + ", destination=" + agentUUID + ", uuid=" + uuid + ", wakeTime=" + wakeUpTime + "}";
        var message = new WakeUpMessage(simulationManager, uuid, agent, wakeUpTime);
        assertEquals(expected, message.toString());
    }
}