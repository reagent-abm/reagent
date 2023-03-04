package dev.r0bert.reagent.core.properties;

import org.jetbrains.annotations.NotNull;

/**
 * This interface is used to mark classes that have a name.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public interface Named {
    /**
     * Get the name of the object.
     *
     * @return The name of the object.
     * @author Robert Greener
     * @since v0.0.1
     */
    @NotNull String getName();
}
