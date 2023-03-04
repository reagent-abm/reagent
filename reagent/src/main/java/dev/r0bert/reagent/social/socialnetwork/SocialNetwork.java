package dev.r0bert.reagent.social.socialnetwork;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.simulationmanager.SimulationManager;
import dev.r0bert.reagent.social.messages.socialmessage.SocialMessageSpecification;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link SocialNetwork} is a network of {@link Agent}s that can send
 * {@link dev.r0bert.reagent.social.messages.socialmessage.SocialMessage}s to each other.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public interface SocialNetwork {
    /**
     * Add an {@link Agent} to this {@link SocialNetwork}.
     *
     * @param agent The {@link Agent} to add to this {@link SocialNetwork}.
     * @author Robert Greener
     * @since v0.0.1
     */
    void addAgent(final @NotNull Agent agent);

    /**
     * Add an edge between two {@link Agent}s in this {@link SocialNetwork}.
     *
     * @param source The source {@link Agent} of the edge.
     * @param target The target {@link Agent} of the edge.
     * @param weight The weight of the edge.
     * @author Robert Greener
     * @since v0.0.1
     */
    void addEdge(final @NotNull Agent source, final @NotNull Agent target, final double weight);

    /**
     * Add an edge between two {@link Agent}s in this {@link SocialNetwork}.
     *
     * @param source The source {@link Agent} of the edge.
     * @param target The target {@link Agent} of the edge.
     * @author Robert Greener
     * @since v0.0.1
     */
    default void addEdge(final @NotNull Agent source, final @NotNull Agent target) {
        addEdge(source, target, 1.0);
    }

    /**
     * Remove an {@link Agent} from this {@link SocialNetwork}.
     *
     * @param agent The {@link Agent} to remove from this {@link SocialNetwork}.
     * @author Robert Greener
     * @since v0.0.1
     */
    void removeAgent(final @NotNull Agent agent);

    /**
     * Get the weight of an edge between two {@link Agent}s in this {@link SocialNetwork}.
     *
     * @param source The source {@link Agent} of the edge.
     * @param target The target {@link Agent} of the edge.
     * @return The weight of the edge between the two {@link Agent}s.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Nullable Double getWeight(final @NotNull Agent source, final @NotNull Agent target);

    /**
     * Set the weight of an edge between two {@link Agent}s in this {@link SocialNetwork}.
     *
     * @param source The source {@link Agent} of the edge.
     * @param target The target {@link Agent} of the edge.
     * @param weight The weight of the edge.
     * @author Robert Greener
     * @since v0.0.1
     */
    void setWeight(final @NotNull Agent source, final @NotNull Agent target, final double weight);

    /**
     * Set the weight of an edge between two {@link Agent}s in this {@link SocialNetwork} to 1.0.
     *
     * @param source The source {@link Agent} of the edge.
     * @param target The target {@link Agent} of the edge.
     * @author Robert Greener
     * @since v0.0.1
     */
    default void setWeight(final @NotNull Agent source, final @NotNull Agent target) {
        setWeight(source, target, 1.0);
    }

    /**
     * Send {@link dev.r0bert.reagent.social.messages.socialmessage.SocialMessage}s to {@link Agent}s in this
     * {@link SocialNetwork}.
     *
     * @param simulationManager The {@link SimulationManager} to use to send the
     * {@link dev.r0bert.reagent.social.messages.socialmessage.SocialMessage}s.
     * @param specification     The {@link SocialMessageSpecification} to use to send the
     *                          {@link dev.r0bert.reagent.social.messages.socialmessage.SocialMessage}s.
     * @author Robert Greener
     * @since v0.0.1
     */
    void sendSocialMessages(final @NotNull SimulationManager simulationManager,
                            final @NotNull SocialMessageSpecification specification);
}
