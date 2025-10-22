package io.github.thebusybiscuit.slimefun4.core.attributes;

import eu.mrneznamy.utils.ColorSystem;

import javax.annotation.Nonnull;

public enum MachineTier {

    BASIC(ColorSystem.colorize("&eBasic")),
    AVERAGE(ColorSystem.colorize("&6Average")),
    MEDIUM(ColorSystem.colorize("&aMedium")),
    GOOD(ColorSystem.colorize("&2Good")),
    ADVANCED(ColorSystem.colorize("&6Advanced")),
    END_GAME(ColorSystem.colorize("&4End-Game"));

    private final String prefix;

    MachineTier(@Nonnull String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }

}
