package dev.r0bert.reagent.core.messages;

import dev.r0bert.reagent.core.properties.UUIDd;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * A {@link Message} is a message that is sent between agents.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public interface Message extends UUIDd {
    /**
     * Get the destination of the message.
     *
     * @return The destination of the message.
     * @author Robert Greener
     * @since v0.0.1
     */
    @NotNull UUID getDestination();

    /**
     * Get the sender of the message.
     *
     * @return The sender of the message.
     * @author Robert Greener
     * @since v0.0.1
     */
    @NotNull UUID getSender();
}
