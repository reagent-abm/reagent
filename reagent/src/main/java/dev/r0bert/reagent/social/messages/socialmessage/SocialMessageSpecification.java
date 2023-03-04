package dev.r0bert.reagent.social.messages.socialmessage;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.core.messages.Message;
import dev.r0bert.reagent.core.properties.UUIDd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/**
 * A {@link SocialMessageSpecification} is the specification for a {@link SocialMessage} that is sent between
 * {@link Agent}s in a {@link dev.r0bert.reagent.social.socialnetwork.SocialNetwork}.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public class SocialMessageSpecification implements UUIDd {
    /**
     * The {@link UUID} of this {@link SocialMessageSpecification}.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID uuid;

    /**
     * The {@link UUID} of the {@link Agent} that this {@link SocialMessageSpecification} is sent from.
     *
     * @since v0.0.1
     */
    private final @NotNull UUID sender;

    /**
     * The {@link Message} that is sent in this {@link SocialMessageSpecification}.
     *
     * @since v0.0.1
     */
    private final @NotNull Message payload;

    /**
     * The {@link Logger} for this class.
     *
     * @since v0.0.1
     */
    private final static @NotNull Logger logger = LogManager.getLogger(SocialMessageSpecification.class);

    /**
     * Create a new {@link SocialMessageSpecification}.
     * If the {@link UUID} is null, a new {@link UUID} will be generated.
     *
     * @param uuid    The {@link UUID} of this {@link SocialMessageSpecification}.
     * @param sender  The {@link UUID} of the {@link Agent} that this {@link SocialMessageSpecification} is sent from.
     * @param payload The {@link Message} that is sent in this {@link SocialMessageSpecification}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public SocialMessageSpecification(final UUID uuid, final @NotNull UUID sender, final @NotNull Message payload) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.sender = sender;
        this.payload = payload;
    }

    /**
     * Convert this {@link SocialMessageSpecification} to a {@link Collection} of {@link SocialMessage}s.
     *
     * @param destinations The {@link Collection} of {@link UUID}s that this {@link SocialMessageSpecification} is
     *                     sent to.
     * @return The {@link Collection} of {@link SocialMessage}s.
     * @author Robert Greener
     * @since v0.0.1
     */
    public @NotNull Collection<SocialMessage> toSocialMessagesFromUUIDs(final @NotNull Collection<UUID> destinations) {
        logger.atTrace().log("Converting SocialMessageSpecification {} from Sender {} to Destinations {}", uuid,
                sender, destinations.size());
        return destinations.stream().map(destination -> new SocialMessage(destination, sender, null, payload, 1.0)).toList();
    }

    /**
     * Convert this {@link SocialMessageSpecification} to a {@link Collection} of {@link SocialMessage}s.
     *
     * @param destinations The {@link Collection} of {@link Agent}s that this {@link SocialMessageSpecification} is
     *                     sent to.
     * @return The {@link Collection} of {@link SocialMessage}s.
     * @author Robert Greener
     * @since v0.0.1
     */
    public @NotNull Collection<SocialMessage> toSocialMessagesFromAgents(final @NotNull Collection<Agent> destinations) {
        return toSocialMessagesFromUUIDs(destinations.stream().map(Agent::getUUID).toList());
    }

    /**
     * A {@link UUIDWeightPair} is a record that contains a {@link UUID} and a weight.
     *
     * @param uuid   The {@link UUID}.
     * @param weight The weight.
     * @author Robert Greener
     * @since v0.0.1
     */
    public record UUIDWeightPair(@NotNull UUID uuid, double weight) {
    }

    /**
     * Convert this {@link SocialMessageSpecification} to a {@link Collection} of {@link SocialMessage}s.
     *
     * @param destinations The {@link Collection} of {@link UUIDWeightPair}s that this
     *                     {@link SocialMessageSpecification} is sent to.
     * @return The {@link Collection} of {@link SocialMessage}s.
     * @author Robert Greener
     * @since v0.0.1
     */
    public @NotNull Collection<SocialMessage> toSocialMessagesFromUUIDsAndWeights(final @NotNull Collection<UUIDWeightPair> destinations) {
        logger.atTrace().log("Converting SocialMessageSpecification {} from Sender {} to Destinations {}", uuid,
                sender, destinations.size());
        return destinations.stream().map(destination -> new SocialMessage(destination.uuid, sender, null, payload,
                destination.weight)).toList();
    }

    /**
     * A {@link AgentWeightPair} is a record that contains an {@link Agent} and a weight.
     *
     * @param agent  The {@link Agent}.
     * @param weight The weight.
     * @since v0.0.1
     */
    public record AgentWeightPair(@NotNull Agent agent, double weight) {
        private @NotNull UUIDWeightPair toUUIDWeightPair() {
            return new UUIDWeightPair(agent.getUUID(), weight);
        }
    }

    /**
     * Convert this {@link SocialMessageSpecification} to a {@link Collection} of {@link SocialMessage}s.
     *
     * @param destinations The {@link Collection} of {@link AgentWeightPair}s that this
     *                     {@link SocialMessageSpecification} is sent to.
     * @return The {@link Collection} of {@link SocialMessage}s.
     * @since v0.0.1
     */
    public @NotNull Collection<SocialMessage> toSocialMessagesFromAgentsAndWeights(final @NotNull Collection<AgentWeightPair> destinations) {
        return toSocialMessagesFromUUIDsAndWeights(destinations.stream().map(AgentWeightPair::toUUIDWeightPair).toList());
    }

    @Override
    public @NotNull UUID getUUID() {
        return uuid;
    }

    /**
     * Get the {@link UUID} of the {@link Agent} that this {@link SocialMessageSpecification} is sent from.
     *
     * @return The {@link UUID} of the {@link Agent} that this {@link SocialMessageSpecification} is sent from.
     * @author Robert Greener
     * @since v0.0.1
     */
    public @NotNull UUID getSender() {
        return sender;
    }

    /**
     * Get the {@link Message} that is sent in this {@link SocialMessageSpecification}.
     *
     * @return The {@link Message} that is sent in this {@link SocialMessageSpecification}.
     * @author Robert Greener
     * @since v0.0.1
     */
    public @NotNull Message getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "SocialMessageSpecification{" +
                "uuid=" + uuid +
                ", sender=" + sender +
                ", payload=" + payload +
                '}';
    }
}
