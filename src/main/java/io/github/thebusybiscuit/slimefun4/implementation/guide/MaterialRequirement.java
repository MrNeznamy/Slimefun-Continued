package io.github.thebusybiscuit.slimefun4.implementation.guide;

import javax.annotation.Nonnull;

/**
 * This class holds information about a material requirement,
 * including the required amount, available amount, and availability status.
 * 
 * @author TheBusyBiscuit
 * @since 4.0
 */
public class MaterialRequirement {

    private final int required;
    private final int available;
    private final MaterialAvailability availability;

    /**
     * Creates a new MaterialRequirement.
     * 
     * @param required The required amount of the material
     * @param available The available amount in the player's inventory
     * @param availability The availability status
     */
    public MaterialRequirement(int required, int available, @Nonnull MaterialAvailability availability) {
        this.required = required;
        this.available = available;
        this.availability = availability;
    }

    /**
     * Gets the required amount of the material.
     * 
     * @return The required amount
     */
    public int getRequired() {
        return required;
    }

    /**
     * Gets the available amount in the player's inventory.
     * 
     * @return The available amount
     */
    public int getAvailable() {
        return available;
    }

    /**
     * Gets the availability status.
     * 
     * @return The availability status
     */
    @Nonnull
    public MaterialAvailability getAvailability() {
        return availability;
    }

    /**
     * Gets the missing amount (required - available).
     * 
     * @return The missing amount, or 0 if sufficient
     */
    public int getMissing() {
        return Math.max(0, required - available);
    }

    /**
     * Checks if the player has sufficient amount of this material.
     * 
     * @return true if sufficient, false otherwise
     */
    public boolean isSufficient() {
        return availability == MaterialAvailability.SUFFICIENT;
    }

    /**
     * Checks if the player has partial amount of this material.
     * 
     * @return true if partial, false otherwise
     */
    public boolean isPartial() {
        return availability == MaterialAvailability.PARTIAL;
    }

    /**
     * Checks if the player has none of this material.
     * 
     * @return true if none, false otherwise
     */
    public boolean isNone() {
        return availability == MaterialAvailability.NONE;
    }

    @Override
    public String toString() {
        return String.format("MaterialRequirement{required=%d, available=%d, availability=%s}", 
                           required, available, availability);
    }
}