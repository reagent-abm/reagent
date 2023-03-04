package dev.r0bert.reagent.social.messages.socialmessage;

import dev.r0bert.reagent.core.messages.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A {@link SocialMessage} is a {@link Message} that is sent between {@link dev.r0bert.reagent.core.agents.Agent}s in a
 * {@link dev.r0bert.reagent.social.socialnetwork.SocialNetwork}.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class SocialMessage implements Message {
    /**
     * The {@link UUID} of the {@link dev.r0bert.reagent.core.agents.Agent} that this {@link SocialMessage} is sent to.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID destination;

    /**
     * The {@link UUID} of the {@link dev.r0bert.reagent.core.agents.Agent} that this {@link SocialMessage} is sent
     * from.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID sender;

    /**
     * The {@link UUID} of this {@link SocialMessage}.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID uuid;

    /**
     * The {@link Message} that is sent in this {@link SocialMessage}.
     *
     * @since v0.0.1
     */
    private final @NotNull Message payload;

    /**
     * The weight of this {@link SocialMessage}.
     *
     * @since v0.0.1
     */
    private final double weight;

    /**
     * Create a new {@link SocialMessage}.
     * If the {@link UUID} is null, a new {@link UUID} will be generated.
     *
     * @param destination The {@link UUID} of the {@link dev.r0bert.reagent.core.agents.Agent} that this
     *                    {@link SocialMessage} is sent to.
     * @param sender      The {@link UUID} of the {@link dev.r0bert.reagent.core.agents.Agent} that this
     *                    {@link SocialMessage} is sent from.
     * @param uuid        The {@link UUID} of this {@link SocialMessage}.
     * @param payload     The {@link Message} that is sent in this {@link SocialMessage}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public SocialMessage(final @NotNull UUID destination, final @NotNull UUID sender, final @Nullable UUID uuid,
                         final @NotNull Message payload) {
        this(destination, sender, uuid, payload, 1.0);
    }

    /**
     * Create a new {@link SocialMessage}.
     * If the {@link UUID} is null, a new {@link UUID} will be generated.
     *
     * @param destination The {@link UUID} of the {@link dev.r0bert.reagent.core.agents.Agent} that this
     *                    {@link SocialMessage} is sent to.
     * @param sender      The {@link UUID} of the {@link dev.r0bert.reagent.core.agents.Agent} that this
     *                    {@link SocialMessage} is sent from.
     * @param uuid        The {@link UUID} of this {@link SocialMessage}.
     * @param payload     The {@link Message} that is sent in this {@link SocialMessage}.
     * @param weight      The weight of this {@link SocialMessage}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public SocialMessage(final @NotNull UUID destination, final @NotNull UUID sender, final UUID uuid,
                         final @NotNull Message payload, final double weight) {
        this.destination = destination;
        this.sender = sender;
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.payload = payload;
        this.weight = weight;
    }

    @Override
    public @NotNull UUID getDestination() {
        return destination;
    }

    @Override
    public @NotNull UUID getSender() {
        return sender;
    }

    @Override
    public @NotNull UUID getUUID() {
        return uuid;
    }

    /**
     * Get the {@link Message} that is sent in this {@link SocialMessage}.
     *
     * @return The {@link Message} that is sent in this {@link SocialMessage}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public @NotNull Message getPayload() {
        return payload;
    }

    /**
     * Get the weight of this {@link SocialMessage}.
     *
     * @return The weight of this {@link SocialMessage}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "SocialMessage{" + "destination=" + destination + ", sender=" + sender + ", uuid=" + uuid + ", payload" +
                "=" + payload + ", weight=" + weight + '}';
    }
}
