package io.github.thebusybiscuit.slimefun4.core.attributes;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun4.implementation.tasks.armor.RadiationTask;

import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.entity.Player;

/**
 * This enum holds all available levels of {@link Radioactivity}.
 * The higher the level the more severe the effect of radiation will be.
 * 
 * @author TheBusyBiscuit
 * 
 * @see Radioactive
 *
 */
public enum Radioactivity {

    /**
     * This represents a low level of radiation.
     * It will still cause damage but will take a while before it becomes deadly.
     */
    LOW("&e", 1),

    /**
     * This represents a medium level of radiation.
     * This can be considered the default.
     */
    MODERATE("&e", 2),

    /**
     * This is a high level of radiation.
     * It will cause death if the {@link Player} does not act quickly.
     */
    HIGH("&6", 3),

    /**
     * A very high level of radiation will be deadly.
     * The {@link Player} should not take this too lightly...
     */
    VERY_HIGH("&c", 5),

    /**
     * This is the deadliest level of radiation.
     * The {@link Player} has basically no chance to protect themselves in time.
     * It will cause certain death.
     */
    VERY_DEADLY("&4", 10);

    private final String color;
    private final int exposureModifier;

    Radioactivity(@Nonnull String color, int exposureModifier) {
        this.color = color;
        this.exposureModifier = exposureModifier;
    }

    /**
     * This method returns the amount of exposure applied
     * to a player every run of the {@link RadiationTask}
     * for this radiation level.
     *
     * @return The exposure amount applied per run.
     */
    public int getExposureModifier() {
        return exposureModifier;
    }

    public @Nonnull String getLore() {
        return ColorSystem.colorize("&a\u2622&7 Radiation level: " + color + toString().replace('_', ' '));
    }

    /**
     * This method returns the level for the radiation effect to use in conjunction
     * with this level of {@link Radioactive}.
     * 
     * It is basically the index of this enum constant.
     * 
     * @return The level of radiation associated with this constant.
     */
    public int getRadiationLevel() {
        return ordinal() + 1;
    }

}
