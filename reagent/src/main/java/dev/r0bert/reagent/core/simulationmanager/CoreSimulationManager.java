package dev.r0bert.reagent.core.simulationmanager;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * The {@link CoreSimulationManager} is the default implementation of the {@link SimulationManager}.
 * <p>
 * {@inheritDoc}
 * </p>
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class CoreSimulationManager implements SimulationManager {
    /**
     * The start time of the simulation.
     *
     * @since v0.0.1
     */
    private final ZonedDateTime startTime;

    /**
     * The end time of the simulation.
     *
     * @since v0.0.1
     */
    private final ZonedDateTime endTime;

    /**
     * The current time of the simulation.
     *
     * @since v0.0.1
     */
    private ZonedDateTime currentTime;

    /**
     * The queue of messages to be sent.
     *
     * @since v0.0.1
     */
    private final SortedMap<ZonedDateTime, Queue<Message>> messageQueue = new TreeMap<>();

    /**
     * The agents in the simulation.
     *
     * @since v0.0.1
     */
    private final Map<UUID, Agent> agents = new LinkedHashMap<>();

    /**
     * The {@link Logger} for this class.
     *
     * @since v0.0.1
     */
    private final static Logger logger = LogManager.getLogger(CoreSimulationManager.class);

    /**
     * Create a new {@link CoreSimulationManager}.
     *
     * @param startTime The start time of the simulation.
     * @param endTime   The end time of the simulation.
     * @author Robert Greener
     * @since v0.0.1
     */
    public CoreSimulationManager(final @NotNull ZonedDateTime startTime, final @NotNull ZonedDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentTime = startTime;
    }

    /**
     * Check that the time is valid.
     *
     * @param time The time to check.
     * @throws IllegalArgumentException If the time is before the start time, after the end time, or before the
     *                                  current time.
     * @author Robert Greener
     * @since v0.0.1
     */
    protected void checkTimeIsValid(final @NotNull ZonedDateTime time) {
        if (time.isBefore(this.getStartTime())) {
            throw new IllegalArgumentException(String.format("The time is before the start time %s",
                    this.getStartTime()));
        } else if (time.isAfter(this.getEndTime())) {
            throw new IllegalArgumentException(String.format("The time is after the end time %s", this.getEndTime()));
        } else if (time.isBefore(this.getCurrentTime())) {
            throw new IllegalArgumentException(String.format("The time is before the current time %s",
                    this.getCurrentTime()));
        }
    }

    @Override
    public ZonedDateTime getStartTime() {
        return startTime;
    }

    @Override
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    @Override
    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    @Override
    public void scheduleMessage(final @NotNull Message message, final @NotNull ZonedDateTime time) {
        checkTimeIsValid(time);

        logger.atDebug().log("Scheduling message {} for time {}", message, time);

        messageQueue.putIfAbsent(time, new LinkedList<>());

        var queue = messageQueue.get(time);
        queue.add(message);
    }

    @Override
    public void sendMessageNow(final @NotNull Message message) {
        scheduleMessage(message, this.getCurrentTime());
    }

    @Override
    public void run() {
        logger.atInfo().log("Running simulation from {} to {}", this.getStartTime(), this.getEndTime());

        while (this.getCurrentTime().isBefore(this.getEndTime()) && !messageQueue.isEmpty()) {
            currentTime = messageQueue.firstKey();
            runForTime(currentTime);
        }

        logger.atInfo().log("Simulation finished");
    }

    /**
     * Run the simulation for a specific time.
     *
     * @param time The time to run the simulation for.
     * @author Robert Greener
     * @see #run()
     * @since v0.0.1
     */
    protected void runForTime(final @NotNull ZonedDateTime time) {
        checkTimeIsValid(time);

        logger.atInfo().log("Running simulation for time {}", time);

        if (!messageQueue.containsKey(time)) {
            return;
        }

        while (!messageQueue.get(time).isEmpty()) {
            var message = messageQueue.get(time).remove();
            var agent = agents.get(message.getDestination());
            agent.handleMessage(message);
        }

        messageQueue.remove(time);
    }

    @Override
    public void addAgent(final @NotNull Agent agent) {
        logger.atDebug().log("Adding agent {}", agent);
        this.agents.put(agent.getUUID(), agent);
    }
}
