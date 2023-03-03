package dev.r0bert.reagent.core.simulationmanager;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

/**
 * A {@link SimulationManager} is responsible for managing the simulation.
 * It is responsible for scheduling {@link Message}s to be sent at a specific time.
 * It is also responsible for sending {@link Message}s to {@link Agent}s.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public interface SimulationManager {
    /**
     * Get the start time of the simulation.
     *
     * @return The start time of the simulation.
     * @author Robert Greener
     * @since v0.0.1
     */
    ZonedDateTime getStartTime();

    /**
     * Get the end time of the simulation.
     *
     * @return The end time of the simulation.
     * @author Robert Greener
     * @since v0.0.1
     */
    ZonedDateTime getEndTime();

    /**
     * Get the current time of the simulation.
     *
     * @return The current time of the simulation.
     * @author Robert Greener
     * @since v0.0.1
     */
    ZonedDateTime getCurrentTime();

    /**
     * Schedule a {@link Message} to be sent at a specific time.
     *
     * @param message The {@link Message} to be sent.
     * @param time    The time that the {@link Message} is to be sent.
     * @author Robert Greener
     * @since v0.0.1
     */
    void scheduleMessage(final @NotNull Message message, final @NotNull ZonedDateTime time);

    /**
     * Send a {@link Message} to an {@link Agent}.
     *
     * @param message The {@link Message} to be sent.
     * @author Robert Greener
     * @since v0.0.1
     */
    void sendMessageNow(final @NotNull Message message);

    /**
     * Run the simulation.
     *
     * @author Robert Greener
     * @since v0.0.1
     */
    void run();

    /**
     * Add an {@link Agent} to the simulation.
     *
     * @param agent The {@link Agent} to add.
     * @author Robert Greener
     * @since v0.0.1
     */
    void addAgent(final @NotNull Agent agent);
}
