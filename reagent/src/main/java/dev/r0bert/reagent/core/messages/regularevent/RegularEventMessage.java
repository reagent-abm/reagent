package dev.r0bert.reagent.core.messages.regularevent;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A {@link RegularEventMessage} is a {@link Message} that is sent to an {@link Agent} at a regular interval.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class RegularEventMessage implements Message {
    /**
     * The {@link UUID} of the {@link Agent} that this {@link RegularEventMessage} is sent to and from.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID agentUuid;

    /**
     * The {@link UUID} of this {@link RegularEventMessage}.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID uuid;

    /**
     * Create a new {@link RegularEventMessage}.
     * If the {@link UUID} is null, a new {@link UUID} will be generated.
     *
     * @param agent The {@link Agent} that this {@link RegularEventMessage} is sent to and from.
     * @param uuid  The {@link UUID} of this {@link RegularEventMessage}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public RegularEventMessage(final @NotNull Agent agent, final @Nullable UUID uuid) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.agentUuid = agent.getUUID();
    }

    /**
     * Get the {@link UUID} of the {@link Agent} that this {@link RegularEventMessage} is sent to and from.
     *
     * @return The {@link UUID}.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public @NotNull UUID getDestination() {
        return agentUuid;
    }

    /**
     * Get the {@link UUID} of the {@link Agent} that this {@link RegularEventMessage} is sent to and from.
     *
     * @return The {@link UUID}.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public @NotNull UUID getSender() {
        return agentUuid;
    }

    /**
     * Get the {@link UUID} of this {@link RegularEventMessage}.
     *
     * @return The {@link UUID}.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public @NotNull UUID getUUID() {
        return uuid;
    }

    /**
     * Get a {@link String} representation of this {@link RegularEventMessage}.
     *
     * @return The {@link String} representation of this {@link RegularEventMessage}.
     * @author Robert Greener
     * @since v0.0.1
     */
    @Override
    public String toString() {
        return "RegularEventMessage{" +
                "destination=" + agentUuid +
                ", sender=" + agentUuid +
                ", uuid=" + uuid +
                '}';
    }
}
