package dev.r0bert.reagent.behaviour;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CoreBehaviour implements Behaviour {
    private final @NotNull UUID uuid;

    private final @NotNull String name;

    public CoreBehaviour(final @Nullable UUID uuid, final @NotNull String name) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.name = name;
    }

    @Override
    public @NotNull UUID getUUID() {
        return uuid;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }
}
