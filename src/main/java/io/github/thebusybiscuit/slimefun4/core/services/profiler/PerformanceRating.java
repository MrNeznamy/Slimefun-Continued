package io.github.thebusybiscuit.slimefun4.core.services.profiler;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang.Validate;
import eu.mrneznamy.utils.ColorSystem;

/**
 * This enum is used to quantify Slimefun's performance impact. This way we can assign a
 * "grade" to each timings report and also use this for metrics collection.
 * 
 * @author TheBusyBiscuit
 * 
 * @see SlimefunProfiler
 *
 */
public enum PerformanceRating implements Predicate<Float> {

    // Thresholds might change in the future!

    UNKNOWN(ColorSystem.colorize("&f"), -1),

    GOOD(ColorSystem.colorize("&2"), 10),
    FINE(ColorSystem.colorize("&2"), 20),
    OKAY(ColorSystem.colorize("&a"), 30),
    MODERATE(ColorSystem.colorize("&e"), 55),
    SEVERE(ColorSystem.colorize("&c"), 85),
    HURTFUL(ColorSystem.colorize("&4"), 500),
    BAD(ColorSystem.colorize("&4"), Float.MAX_VALUE);

    private final String color;
    private final float threshold;

    PerformanceRating(@Nonnull String color, float threshold) {
        Validate.notNull(color, "Color cannot be null");
        this.color = color;
        this.threshold = threshold;
    }

    @Override
    public boolean test(@Nullable Float value) {
        if (value == null) {
            // This way null will only test true for UNKNOWN
            return threshold < 0;
        }

        return value <= threshold;
    }

    @Nonnull
    public String getColor() {
        return color;
    }

}
