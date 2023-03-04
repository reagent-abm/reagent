package dev.r0bert.reagent.social.socialnetwork;

import org.jetbrains.annotations.NotNull;

/**
 * An object that has a {@link SocialNetwork}.
 *
 * @author Robert Greener
 * @since v0.0.1
 */
public interface HasSocialNetwork {
    /**
     * Get the {@link SocialNetwork} that this object has.
     *
     * @return The {@link SocialNetwork} that this object has.
     * @author Robert Greener
     * @since v0.0.1
     */
    @NotNull SocialNetwork getSocialNetwork();
}
