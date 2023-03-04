package dev.r0bert.reagent.core.agents;

import dev.r0bert.reagent.core.messages.Message;
import dev.r0bert.reagent.core.properties.UUIDd;
import org.jetbrains.annotations.NotNull;

/**
 * An {@link Agent} is an agent in the simulation.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public interface Agent extends UUIDd {
    /**
     * Handle a message.
     *
     * @param message The message to handle.
     * @author Robert Greener
     * @since v0.0.1
     */
    void handleMessage(final @NotNull Message message);
}
