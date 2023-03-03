package dev.r0bert.reagent.core.messages.regularevent;

import dev.r0bert.reagent.core.agents.Agent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegularEventMessageTest {

    @Test
    public void constructor_WhenUUIDNull_AssignsRandomUUID() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var message1 = new RegularEventMessage(agent, null);
        var message2 = new RegularEventMessage(agent, null);
        assertNotEquals(message1.getUUID(), message2.getUUID());
    }

    @Test
    public void constructor_WhenUUIDNotNull_AssignsUUID() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var uuid = UUID.randomUUID();
        var message = new RegularEventMessage(agent, uuid);
        assertEquals(uuid, message.getUUID());
    }

    @Test
    public void getDestination_Always_ReturnsAgentUUID() {
        var agent = mock(Agent.class);
        var agentUUID = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUUID);
        var message = new RegularEventMessage(agent, null);
        assertEquals(agentUUID, message.getDestination());
    }

    @Test
    public void getSender_Always_ReturnsAgentUUID() {
        var agent = mock(Agent.class);
        var agentUUID = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUUID);
        var message = new RegularEventMessage(agent, null);
        assertEquals(agentUUID, message.getSender());
    }

    @Test
    public void getUUID_Always_ReturnsUUID() {
        var agent = mock(Agent.class);
        when(agent.getUUID()).thenReturn(UUID.randomUUID());
        var uuid = UUID.randomUUID();
        var message = new RegularEventMessage(agent, uuid);
        assertEquals(uuid, message.getUUID());
    }

    @Test
    public void toString_Always_ReturnsCorrectRepresentation() {
        var agent = mock(Agent.class);
        var agentUUID = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUUID);
        var uuid = UUID.randomUUID();
        var message = new RegularEventMessage(agent, uuid);
        assertEquals("RegularEventMessage{" +
                "destination=" + agentUUID +
                ", sender=" + agentUUID +
                ", uuid=" + uuid +
                '}', message.toString());
    }
}