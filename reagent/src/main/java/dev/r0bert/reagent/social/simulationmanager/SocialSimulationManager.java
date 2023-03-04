package dev.r0bert.reagent.social.simulationmanager;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.simulationmanager.CoreSimulationManager;
import dev.r0bert.reagent.social.socialnetwork.HasSocialNetwork;
import dev.r0bert.reagent.social.socialnetwork.SocialNetwork;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

/**
 * A {@link SocialSimulationManager} is a {@link CoreSimulationManager} that also has a {@link SocialNetwork}.
 * {@inheritDoc}
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class SocialSimulationManager extends CoreSimulationManager implements HasSocialNetwork {
    /**
     * The {@link SocialNetwork} that this {@link SocialSimulationManager} uses.
     *
     * @since v0.0.1
     */
    private final @NotNull SocialNetwork socialNetwork;

    /**
     * Create a new {@link CoreSimulationManager}.
     *
     * @param startTime The start time of the simulation.
     * @param endTime   The end time of the simulation.
     * @author Robert Greener
     * @since v0.0.1
     */
    public SocialSimulationManager(final @NotNull ZonedDateTime startTime, final @NotNull ZonedDateTime endTime,
                                   final @NotNull SocialNetwork socialNetwork) {
        super(startTime, endTime);
        this.socialNetwork = socialNetwork;
    }

    /**
     * Add an {@link Agent} to this {@link SocialSimulationManager}.
     *
     * @param agent The {@link Agent} to add.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public void addAgent(final @NotNull Agent agent) {
        super.addAgent(agent);
        this.getSocialNetwork().addAgent(agent);
    }

    @Override
    public String toString() {
        return "SocialSimulationManager{" +
                "startTime=" + this.getStartTime() +
                ", endTime=" + this.getEndTime() +
                ", currentTime=" + this.getCurrentTime() +
                "}";
    }

    @Override
    public @NotNull SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }
}
