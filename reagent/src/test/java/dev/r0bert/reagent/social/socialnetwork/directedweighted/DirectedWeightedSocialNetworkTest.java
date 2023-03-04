package dev.r0bert.reagent.social.socialnetwork.directedweighted;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import dev.r0bert.reagent.core.simulationmanager.SimulationManager;
import dev.r0bert.reagent.social.messages.socialmessage.SocialMessage;
import dev.r0bert.reagent.social.messages.socialmessage.SocialMessageSpecification;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectedWeightedSocialNetworkTest {
    @Test
    public void addAgent_Always_AddsAgentToSocialNetwork() throws IllegalAccessException {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var agent = mock(Agent.class);
        var agentUuid = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUuid);

        socialNetwork.addAgent(agent);

        @SuppressWarnings("unchecked") var network =
                (DefaultDirectedWeightedGraph<UUID, DefaultWeightedEdge>) FieldUtils.readField(socialNetwork,
                        "network", true);
        assertTrue(network.containsVertex(agentUuid));
    }

    @Test
    public void addEdge_Always_AddsEdgeToSocialNetwork() throws IllegalAccessException {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var source = mock(Agent.class);
        var sourceUuid = UUID.randomUUID();
        when(source.getUUID()).thenReturn(sourceUuid);
        var target = mock(Agent.class);
        var targetUuid = UUID.randomUUID();
        when(target.getUUID()).thenReturn(targetUuid);

        socialNetwork.addAgent(source);
        socialNetwork.addAgent(target);
        socialNetwork.addEdge(source, target, 0.3);

        @SuppressWarnings("unchecked") var network =
                (DefaultDirectedWeightedGraph<UUID, DefaultWeightedEdge>) FieldUtils.readField(socialNetwork,
                        "network", true);
        assertTrue(network.containsEdge(sourceUuid, targetUuid));
        var edge = network.getEdge(sourceUuid, targetUuid);
        assertEquals(0.3, network.getEdgeWeight(edge));
    }

    @Test
    public void removeAgent_Always_RemovesAgentFromSocialNetwork() throws IllegalAccessException {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var agent = mock(Agent.class);
        var agentUuid = UUID.randomUUID();
        when(agent.getUUID()).thenReturn(agentUuid);
        socialNetwork.addAgent(agent);

        @SuppressWarnings("unchecked") var network =
                (DefaultDirectedWeightedGraph<UUID, DefaultWeightedEdge>) FieldUtils.readField(socialNetwork,
                        "network", true);
        assertTrue(network.containsVertex(agentUuid));

        socialNetwork.removeAgent(agent);

        assertFalse(network.containsVertex(agentUuid));
    }

    @Test
    public void getWeight_WhenExists_ReturnsWeight() {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var source = mock(Agent.class);
        var sourceUuid = UUID.randomUUID();
        when(source.getUUID()).thenReturn(sourceUuid);
        var target = mock(Agent.class);
        var targetUuid = UUID.randomUUID();
        when(target.getUUID()).thenReturn(targetUuid);
        socialNetwork.addAgent(source);
        socialNetwork.addAgent(target);
        socialNetwork.addEdge(source, target, 0.3);

        assertEquals(0.3, socialNetwork.getWeight(source, target));
    }

    @Test
    public void getWeight_WhenDoesNotExist_ReturnsNull() {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var source = mock(Agent.class);
        var sourceUuid = UUID.randomUUID();
        when(source.getUUID()).thenReturn(sourceUuid);
        var target = mock(Agent.class);
        var targetUuid = UUID.randomUUID();
        when(target.getUUID()).thenReturn(targetUuid);

        assertNull(socialNetwork.getWeight(source, target));
    }

    @Test
    public void setWeight_Always_SetsWeight() {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var source = mock(Agent.class);
        var sourceUuid = UUID.randomUUID();
        when(source.getUUID()).thenReturn(sourceUuid);
        var target = mock(Agent.class);
        var targetUuid = UUID.randomUUID();
        when(target.getUUID()).thenReturn(targetUuid);
        socialNetwork.addAgent(source);
        socialNetwork.addAgent(target);
        socialNetwork.addEdge(source, target, 0.3);

        socialNetwork.setWeight(source, target, 0.5);

        assertEquals(0.5, socialNetwork.getWeight(source, target));
    }

    @Test
    public void sendSocialMessages_Always_SendsMessages() {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var source = mock(Agent.class);
        var sourceUuid = UUID.randomUUID();
        when(source.getUUID()).thenReturn(sourceUuid);
        var target1 = mock(Agent.class);
        var targetUuid = UUID.randomUUID();
        when(target1.getUUID()).thenReturn(targetUuid);
        socialNetwork.addAgent(source);
        socialNetwork.addAgent(target1);
        socialNetwork.addEdge(source, target1, 0.3);
        var target2 = mock(Agent.class);
        var target2Uuid = UUID.randomUUID();
        when(target2.getUUID()).thenReturn(target2Uuid);
        socialNetwork.addAgent(target2);
        socialNetwork.addEdge(source, target2, 0.7);

        var specification = new SocialMessageSpecification(null, sourceUuid, mock(Message.class));

        var simulationManager = mock(SimulationManager.class);

        socialNetwork.sendSocialMessages(simulationManager, specification);
        verify(simulationManager, times(2)).sendMessageNow(any(SocialMessage.class));
    }

    @Test
    public void toString_Always_ReturnsCorrectRepresentation() throws IllegalAccessException {
        var socialNetwork = new DirectedWeightedSocialNetwork();
        var source = mock(Agent.class);
        var sourceUuid = UUID.randomUUID();
        when(source.getUUID()).thenReturn(sourceUuid);
        var target1 = mock(Agent.class);
        var targetUuid = UUID.randomUUID();
        when(target1.getUUID()).thenReturn(targetUuid);
        socialNetwork.addAgent(source);
        socialNetwork.addAgent(target1);
        socialNetwork.addEdge(source, target1, 0.3);
        var target2 = mock(Agent.class);
        var target2Uuid = UUID.randomUUID();
        when(target2.getUUID()).thenReturn(target2Uuid);
        socialNetwork.addAgent(target2);
        socialNetwork.addEdge(source, target2, 0.7);

        @SuppressWarnings("unchecked") var network =
                (DefaultDirectedWeightedGraph<UUID, DefaultWeightedEdge>) FieldUtils.readField(socialNetwork,
                        "network", true);

        var expected = "DirectedWeightedSocialNetwork{network=" + network + "}";
        assertEquals(expected, socialNetwork.toString());
    }
}