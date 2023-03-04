package dev.r0bert.reagent.core.messages.regularevent;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.properties.UUIDd;
import dev.r0bert.reagent.core.simulationmanager.SimulationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * A specification for a {@link RegularEventMessage}.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class RegularEventMessageSpecification implements UUIDd {
    /**
     * The {@link Agent} that this {@link RegularEventMessage} is sent to and from.
     */
    private final @NotNull Agent agent;

    /**
     * The {@link UUID} of this {@link RegularEventMessageSpecification}.
     */
    private final @NotNull UUID uuid;

    /**
     * The {@link Logger} for this class.
     */
    private final static @NotNull Logger logger = LogManager.getLogger(RegularEventMessageSpecification.class);

    /**
     * Create a new {@link RegularEventMessageSpecification}.
     * If the {@link UUID} is null, a new {@link UUID} will be generated.
     *
     * @param agent The {@link Agent} that this {@link RegularEventMessageSpecification} is sent to and from.
     * @param uuid  The {@link UUID} of this {@link RegularEventMessageSpecification}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public RegularEventMessageSpecification(final @NotNull Agent agent, final @Nullable UUID uuid) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.agent = agent;
    }

    /**
     * Create and schedule a series of {@link RegularEventMessage}s.
     *
     * @param simulationManager The {@link SimulationManager} to schedule the {@link RegularEventMessage}s with.
     * @param interval          The {@link Duration} between each {@link RegularEventMessage}.
     * @param startTime         The {@link ZonedDateTime} to start sending {@link RegularEventMessage}s.
     * @param endTime           The {@link ZonedDateTime} to stop sending {@link RegularEventMessage}s.
     * @throws IllegalArgumentException If the start time is not before the end time, or if the interval is zero or
     *                                  negative.
     * @author Robert Greener
     * @since v0.0.1
     */
    public void createAndScheduleRegularEventMessages(final @NotNull SimulationManager simulationManager,
                                                      final @NotNull Duration interval,
                                                      final @NotNull ZonedDateTime startTime,
                                                      final @NotNull ZonedDateTime endTime) {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("The start time is not before the end time");
        }
        if (interval.isZero()) {
            throw new IllegalArgumentException("The time interval is zero.");
        }
        if (interval.isNegative()) {
            throw new IllegalArgumentException("The time interval is negative");
        }
        logger.atDebug().log("Creating and scheduling regular event messages for agent {} with interval {} from {} to {}",
                agent, interval, startTime, endTime);
        for (var currentTime = startTime; currentTime.isBefore(endTime); currentTime = currentTime.plus(interval)) {
            var message = this.createMessage();
            logger.atDebug().log("Scheduling message {} at time {}", message, currentTime);
            simulationManager.scheduleMessage(message, currentTime);
        }
    }

    /**
     * Create a new {@link RegularEventMessage}.
     *
     * @return The new {@link RegularEventMessage} that was created.
     * @author Robert Greener
     * @since v0.0.1
     */
    protected @NotNull RegularEventMessage createMessage() {
        logger.atDebug().log("Creating regular event message for agent {}", agent);
        return new RegularEventMessage(agent, null);
    }

    /**
     * Convert this {@link RegularEventMessageSpecification} to a {@link String} representation.
     *
     * @return The {@link String} representation of this {@link RegularEventMessageSpecification}.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public String toString() {
        return "RegularEventMessageSpecification{" +
                "agent=" + agent +
                ", uuid=" + this.getUUID() +
                '}';
    }

    /**
     * Get the {@link UUID} of this {@link RegularEventMessageSpecification}.
     *
     * @return The {@link UUID} of this {@link RegularEventMessageSpecification}.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public @NotNull UUID getUUID() {
        return uuid;
    }
}
