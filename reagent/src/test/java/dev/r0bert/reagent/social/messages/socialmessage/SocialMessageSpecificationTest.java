package dev.r0bert.reagent.social.messages.socialmessage;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SocialMessageSpecificationTest {
    @Test
    public void constructor_NullUUID_AssignsRandomUUID() {
        var sender = UUID.randomUUID();
        var payload = mock(Message.class);

        var specification1 = new SocialMessageSpecification(null, sender, payload);
        var specification2 = new SocialMessageSpecification(null, sender, payload);

        assertNotEquals(specification1.getUUID(), specification2.getUUID());
        assertEquals(sender, specification1.getSender());
        assertEquals(payload, specification1.getPayload());
        assertEquals(sender, specification2.getSender());
        assertEquals(payload, specification2.getPayload());
    }

    @Test
    public void constructor_WithUUID_AssignsUUID() {
        var uuid = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var payload = mock(Message.class);

        var specification = new SocialMessageSpecification(uuid, sender, payload);

        assertEquals(uuid, specification.getUUID());
        assertEquals(sender, specification.getSender());
        assertEquals(payload, specification.getPayload());
    }

    @Test
    public void toSocialMessagesFromUUIDs_EmptyDestinations_ReturnsEmpty() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));

        var socialMessages = specification.toSocialMessagesFromUUIDs(new ArrayList<>());

        assertTrue(socialMessages.isEmpty());
    }

    @Test
    public void toSocialMessagesFromUUIDs_MultipleDestinations_ReturnsCorrectMessages() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));
        var destinations = new ArrayList<UUID>();
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());

        var socialMessages = specification.toSocialMessagesFromUUIDs(destinations);

        assertEquals(destinations.size(), socialMessages.size());
        for (final var socialMessage : socialMessages) {
            assertTrue(destinations.contains(socialMessage.getDestination()));
            assertEquals(specification.getSender(), socialMessage.getSender());
            assertEquals(specification.getPayload(), socialMessage.getPayload());
        }
    }

    @Test
    public void toSocialMessagesFromAgents_EmptyDestinations_ReturnsEmpty() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));

        var socialMessages = specification.toSocialMessagesFromAgents(new ArrayList<>());

        assertTrue(socialMessages.isEmpty());
    }

    @Test
    public void toSocialMessagesFromAgents_MultipleDestinations_ReturnsCorrectMessages() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));
        var destinations = new ArrayList<UUID>();
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());

        var destinationAgents = new ArrayList<Agent>();

        for (final var destination : destinations) {
            var agent = mock(Agent.class);
            when(agent.getUUID()).thenReturn(destination);
            destinationAgents.add(agent);
        }

        var socialMessages = specification.toSocialMessagesFromAgents(destinationAgents);

        assertEquals(destinations.size(), socialMessages.size());
        for (final var socialMessage : socialMessages) {
            assertTrue(destinations.contains(socialMessage.getDestination()));
            assertEquals(specification.getSender(), socialMessage.getSender());
            assertEquals(specification.getPayload(), socialMessage.getPayload());
        }
    }

    @Test
    public void UUIDWeightPairConstructor_Always_Works() {
        var uuid = UUID.randomUUID();
        var weight = 1.1;

        var pair = new SocialMessageSpecification.UUIDWeightPair(uuid, weight);

        assertEquals(uuid, pair.uuid());
        assertEquals(weight, pair.weight());
    }

    @Test
    public void toSocialMessagesFromUUIDsAndWeights_EmptyDestinations_ReturnsEmpty() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));

        var socialMessages = specification.toSocialMessagesFromUUIDsAndWeights(new ArrayList<>());

        assertTrue(socialMessages.isEmpty());
    }

    @Test
    public void toSocialMessagesFromUUIDsAndWeights_MultipleDestinations_ReturnsCorrectMessages() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));
        var destinations = new ArrayList<UUID>();
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());

        var weights = new ArrayList<Double>();
        weights.add(1.1);
        weights.add(2.2);
        weights.add(3.3);

        var destinationsAndWeights = new ArrayList<SocialMessageSpecification.UUIDWeightPair>(destinations.size());
        for (int i = 0; i < destinations.size(); i++) {
            destinationsAndWeights.add(new SocialMessageSpecification.UUIDWeightPair(destinations.get(i),
                    weights.get(i)));
        }

        var socialMessages = specification.toSocialMessagesFromUUIDsAndWeights(destinationsAndWeights);

        assertEquals(destinations.size(), socialMessages.size());
        for (final var socialMessage : socialMessages) {
            var pair = new SocialMessageSpecification.UUIDWeightPair(socialMessage.getDestination(),
                    socialMessage.getWeight());
            assertTrue(destinationsAndWeights.contains(pair));
            assertEquals(specification.getSender(), socialMessage.getSender());
            assertEquals(specification.getPayload(), socialMessage.getPayload());
        }
    }

    @Test
    public void AgentWeightPairConstructor_Always_Works() {
        var agent = mock(Agent.class);
        var weight = 1.1;

        var pair = new SocialMessageSpecification.AgentWeightPair(agent, weight);

        assertEquals(agent, pair.agent());
        assertEquals(weight, pair.weight());
    }

    @Test
    public void toSocialMessagesFromAgentsAndWeights_EmptyDestinations_ReturnsEmpty() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));

        var socialMessages = specification.toSocialMessagesFromAgentsAndWeights(new ArrayList<>());

        assertTrue(socialMessages.isEmpty());
    }

    @Test
    public void toSocialMessagesFromAgentsAndWeights_MultipleDestinations_ReturnsCorrectMessages() {
        var specification = new SocialMessageSpecification(UUID.randomUUID(), UUID.randomUUID(), mock(Message.class));
        var destinations = new ArrayList<UUID>();
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());
        destinations.add(UUID.randomUUID());

        var weights = new ArrayList<Double>();
        weights.add(1.1);
        weights.add(2.2);
        weights.add(3.3);

        var destinationAgents = new ArrayList<Agent>();

        for (final var destination : destinations) {
            var agent = mock(Agent.class);
            when(agent.getUUID()).thenReturn(destination);
            destinationAgents.add(agent);
        }

        var destinationsAndWeights =
                new ArrayList<SocialMessageSpecification.AgentWeightPair>(destinationAgents.size());
        var destinationUUIDsAndWeights =
                new ArrayList<SocialMessageSpecification.UUIDWeightPair>(destinationAgents.size());
        for (int i = 0; i < destinationAgents.size(); i++) {
            destinationsAndWeights.add(new SocialMessageSpecification.AgentWeightPair(destinationAgents.get(i),
                    weights.get(i)));
            destinationUUIDsAndWeights.add(new SocialMessageSpecification.UUIDWeightPair(destinations.get(i),
                    weights.get(i)));
        }

        var socialMessages = specification.toSocialMessagesFromAgentsAndWeights(destinationsAndWeights);

        assertEquals(destinations.size(), socialMessages.size());
        for (final var socialMessage : socialMessages) {
            var pair = new SocialMessageSpecification.UUIDWeightPair(socialMessage.getDestination(),
                    socialMessage.getWeight());
            assertTrue(destinationUUIDsAndWeights.contains(pair));
            assertEquals(specification.getSender(), socialMessage.getSender());
            assertEquals(specification.getPayload(), socialMessage.getPayload());
        }
    }

    @Test
    public void toString_Always_ReturnsCorrectString() {
        var uuid = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var payload = mock(Message.class);
        var specification = new SocialMessageSpecification(uuid, sender, payload);

        var expected = "SocialMessageSpecification{" +
                "uuid=" + uuid +
                ", sender=" + sender +
                ", payload=" + payload +
                '}';

        assertEquals(expected, specification.toString());
    }
}