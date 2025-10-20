package eu.mrneznamy.slimefun5.entities.holograms;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

/**
 * This class provides examples of how to use the new entity-based hologram system.
 * 
 * @author NEZNAMY
 */
public class HologramExample {

    /**
     * Example of how to initialize the hologram system in your plugin's onEnable() method.
     * 
     * @param plugin Your plugin instance
     */
    public static void initializeHologramSystem(Plugin plugin) {
        // Initialize the hologram system for your plugin
        EntityHologramService service = HologramManager.initialize(plugin);
        
        plugin.getLogger().info("Hologram system initialized with " + service.getCachedHolograms() + " cached holograms");
    }

    /**
     * Example of how to create a simple hologram.
     * 
     * @param plugin Your plugin instance
     * @param location The location where to create the hologram
     */
    public static void createSimpleHologram(Plugin plugin, Location location) {
        // Create a simple hologram with text
        EntityHologram hologram = HologramManager.createHologram(plugin, location, "§6Welcome to Slimefun!");
        
        if (hologram != null) {
            plugin.getLogger().info("Created hologram at " + location);
        } else {
            plugin.getLogger().warning("Failed to create hologram at " + location);
        }
    }

    /**
     * Example of how to create a scaled hologram.
     * 
     * @param plugin Your plugin instance
     * @param location The location where to create the hologram
     */
    public static void createScaledHologram(Plugin plugin, Location location) {
        // Create a hologram with custom scale (2x larger)
        EntityHologram hologram = HologramManager.createSimpleHologram(plugin, location, "§aBig Text!", 2.0f);
        
        if (hologram != null) {
            plugin.getLogger().info("Created scaled hologram at " + location);
        }
    }

    /**
     * Example of how to create a colored hologram with background.
     * 
     * @param plugin Your plugin instance
     * @param location The location where to create the hologram
     */
    public static void createColoredHologram(Plugin plugin, Location location) {
        // Create a hologram with colored background
        // Background color: semi-transparent black (0x80000000)
        // Text opacity: fully opaque (255)
        EntityHologram hologram = HologramManager.createColoredHologram(
            plugin, 
            location, 
            "§eColored Hologram!", 
            0x80000000, // Semi-transparent black background
            (byte) 255  // Fully opaque text
        );
        
        if (hologram != null) {
            plugin.getLogger().info("Created colored hologram at " + location);
        }
    }

    /**
     * Example of how to update an existing hologram.
     * 
     * @param plugin Your plugin instance
     * @param location The location of the hologram to update
     */
    public static void updateHologram(Plugin plugin, Location location) {
        // Check if hologram exists
        if (HologramManager.hasHologram(plugin, location)) {
            // Update the text
            HologramManager.updateHologramText(plugin, location, "§cUpdated Text!");
            
            // Update the scale
            HologramManager.updateHologramScale(plugin, location, 1.5f);
            
            plugin.getLogger().info("Updated hologram at " + location);
        } else {
            plugin.getLogger().warning("No hologram found at " + location);
        }
    }

    /**
     * Example of how to remove a hologram.
     * 
     * @param plugin Your plugin instance
     * @param location The location of the hologram to remove
     */
    public static void removeHologram(Plugin plugin, Location location) {
        boolean removed = HologramManager.removeHologram(plugin, location);
        
        if (removed) {
            plugin.getLogger().info("Removed hologram at " + location);
        } else {
            plugin.getLogger().warning("No hologram found to remove at " + location);
        }
    }

    /**
     * Example of how to get information about a hologram.
     * 
     * @param plugin Your plugin instance
     * @param location The location to check
     */
    public static void getHologramInfo(Plugin plugin, Location location) {
        EntityHologram hologram = HologramManager.getHologram(plugin, location);
        
        if (hologram != null) {
            plugin.getLogger().info("Hologram found:");
            plugin.getLogger().info("  UUID: " + hologram.getUniqueId());
            plugin.getLogger().info("  Label: " + hologram.getLabel());
            plugin.getLogger().info("  Position: " + hologram.getPosition());
            plugin.getLogger().info("  Has despawned: " + hologram.hasDespawned());
            plugin.getLogger().info("  Has expired: " + hologram.hasExpired());
        } else {
            plugin.getLogger().info("No hologram found at " + location);
        }
    }

    /**
     * Example of how to get service statistics.
     * 
     * @param plugin Your plugin instance
     */
    public static void getServiceStats(Plugin plugin) {
        if (HologramManager.isServiceEnabled(plugin)) {
            int count = HologramManager.getCachedHologramCount(plugin);
            plugin.getLogger().info("Hologram service is enabled with " + count + " cached holograms");
        } else {
            plugin.getLogger().warning("Hologram service is not enabled for this plugin");
        }
    }

    /**
     * Example of how to shutdown the hologram system in your plugin's onDisable() method.
     * 
     * @param plugin Your plugin instance
     */
    public static void shutdownHologramSystem(Plugin plugin) {
        // Shutdown the hologram system (removes all holograms)
        HologramManager.shutdown(plugin);
        
        plugin.getLogger().info("Hologram system shut down");
    }

    /**
     * Complete example showing the lifecycle of hologram management.
     * 
     * @param plugin Your plugin instance
     * @param location A test location
     */
    public static void completeExample(Plugin plugin, Location location) {
        // 1. Initialize the system
        initializeHologramSystem(plugin);
        
        // 2. Create a hologram
        createSimpleHologram(plugin, location);
        
        // 3. Update the hologram
        updateHologram(plugin, location);
        
        // 4. Get information about the hologram
        getHologramInfo(plugin, location);
        
        // 5. Get service statistics
        getServiceStats(plugin);
        
        // 6. Remove the hologram
        removeHologram(plugin, location);
        
        // 7. Shutdown (typically called in onDisable())
        // shutdownHologramSystem(plugin);
    }
}
