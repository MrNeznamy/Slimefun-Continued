package io.github.thebusybiscuit.slimefun4.implementation.guide;

/**
 * This enum represents the availability status of a material in a player's inventory
 * for the Materials Required feature in the Slimefun Guide.
 * 
 * @author TheBusyBiscuit
 * @since 4.0
 */
public enum MaterialAvailability {
    
    /**
     * The player has sufficient amount of this material
     * (displayed in green color)
     */
    SUFFICIENT,
    
    /**
     * The player has some of this material but not enough
     * (displayed in orange color with "have/need" format)
     */
    PARTIAL,
    
    /**
     * The player has none of this material
     * (displayed in red color)
     */
    NONE
}