package dev.r0bert.reagent.social.socialnetwork.directedweighted;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.simulationmanager.SimulationManager;
import dev.r0bert.reagent.social.messages.socialmessage.SocialMessageSpecification;
import dev.r0bert.reagent.social.socialnetwork.SocialNetwork;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.UUID;

/**
 * A {@link SocialNetwork} that is directed and weighted.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class DirectedWeightedSocialNetwork implements SocialNetwork {
    /**
     * The {@link DefaultDirectedWeightedGraph} that is used to store the {@link Agent}s and their connections.
     *
     * @since v0.0.1
     */
    private final DefaultDirectedWeightedGraph<UUID, DefaultWeightedEdge> network =
            new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    @Override
    public void addAgent(final @NotNull Agent agent) {
        network.addVertex(agent.getUUID());
    }

    @Override
    public void addEdge(final @NotNull Agent source, final @NotNull Agent target, final double weight) {
        var edge = network.addEdge(source.getUUID(), target.getUUID());
        network.setEdgeWeight(edge, weight);
    }

    @Override
    public void removeAgent(final @NotNull Agent agent) {
        network.removeVertex(agent.getUUID());
    }

    @Override
    public @Nullable Double getWeight(final @NotNull Agent source, final @NotNull Agent target) {
        var edge = network.getEdge(source.getUUID(), target.getUUID());
        return edge == null ? null : network.getEdgeWeight(edge);
    }

    @Override
    public void setWeight(final @NotNull Agent source, final @NotNull Agent target, final double weight) {
        var edge = network.getEdge(source.getUUID(), target.getUUID());
        network.setEdgeWeight(edge, weight);
    }

    @Override
    public void sendSocialMessages(final @NotNull SimulationManager simulationManager,
                                   final @NotNull SocialMessageSpecification specification) {
        specification
                .toSocialMessagesFromUUIDsAndWeights(
                        network
                                .outgoingEdgesOf(specification.getSender())
                                .stream()
                                .map(edge -> new SocialMessageSpecification.UUIDWeightPair(network.getEdgeTarget(edge), network.getEdgeWeight(edge)))
                                .toList()
                )
                .forEach(simulationManager::sendMessageNow);
    }

    @Override
    public String toString() {
        return "DirectedWeightedSocialNetwork{" +
                "network=" + network +
                '}';
    }
}
