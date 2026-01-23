package eu.mrneznamy.slimefuncontinued.entities.holograms;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

/**
 * This is the main API class for managing entity-based holograms.
 * It provides a simple interface for creating, updating, and removing holograms
 * using the more stable and resource-efficient TextDisplay entities.
 * 
 * @author TheBusyBiscuit
 * @author NEZNAMY
 */
public class HologramManager {

    /**
     * Map of plugin instances to their hologram services
     */
    private static final ConcurrentMap<Plugin, EntityHologramService> services = new ConcurrentHashMap<>();

    /**
     * Private constructor to prevent instantiation
     */
    private HologramManager() {
        // Utility class
    }

    /**
     * This initializes the hologram system for the given plugin.
     * This method should be called in the plugin's onEnable() method.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * 
     * @return The {@link EntityHologramService} for this plugin
     */
    @Nonnull
    public static EntityHologramService initialize(@Nonnull Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");

        EntityHologramService service = services.get(plugin);
        if (service == null) {
            service = new EntityHologramService(plugin);
            services.put(plugin, service);
            plugin.getLogger().info("Entity-based hologram system initialized for " + plugin.getName());
        }
        return service;
    }

    /**
     * This shuts down the hologram system for the given plugin.
     * This method should be called in the plugin's onDisable() method.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     */
    public static void shutdown(@Nonnull Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");

        EntityHologramService service = services.remove(plugin);
        if (service != null) {
            service.disable();
            plugin.getLogger().info("Entity-based hologram system shut down for " + plugin.getName());
        }
    }

    /**
     * This returns the {@link EntityHologramService} for the given plugin.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * 
     * @return The {@link EntityHologramService} or null if not initialized
     */
    @Nullable
    public static EntityHologramService getService(@Nonnull Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");
        return services.get(plugin);
    }

    /**
     * This creates a new hologram at the given location.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} where to create the hologram
     * @param text
     *            The text to display
     * 
     * @return The created {@link EntityHologram} or null if creation failed
     */
    @Nullable
    public static EntityHologram createHologram(@Nonnull Plugin plugin, @Nonnull Location location, @Nullable String text) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            return service.createHologram(location, text);
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return null;
        }
    }

    /**
     * This updates the text of an existing hologram.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} of the hologram
     * @param text
     *            The new text to display
     * 
     * @return Whether the hologram was successfully updated
     */
    public static boolean updateHologramText(@Nonnull Plugin plugin, @Nonnull Location location, @Nullable String text) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            service.setHologramLabel(location, text);
            return true;
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return false;
        }
    }

    /**
     * This updates the scale of an existing hologram.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} of the hologram
     * @param scale
     *            The scale to set (1.0 = normal size)
     * 
     * @return Whether the hologram was successfully updated
     */
    public static boolean updateHologramScale(@Nonnull Plugin plugin, @Nonnull Location location, float scale) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            service.setHologramScale(location, scale);
            return true;
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return false;
        }
    }

    /**
     * This updates the background color of an existing hologram.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} of the hologram
     * @param color
     *            The background color in ARGB format (0 = transparent)
     * 
     * @return Whether the hologram was successfully updated
     */
    public static boolean updateHologramBackgroundColor(@Nonnull Plugin plugin, @Nonnull Location location, int color) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            service.setHologramBackgroundColor(location, color);
            return true;
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return false;
        }
    }

    /**
     * This updates the text opacity of an existing hologram.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} of the hologram
     * @param opacity
     *            The text opacity (0-255, where 255 is fully opaque)
     * 
     * @return Whether the hologram was successfully updated
     */
    public static boolean updateHologramTextOpacity(@Nonnull Plugin plugin, @Nonnull Location location, byte opacity) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            service.setHologramTextOpacity(location, opacity);
            return true;
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return false;
        }
    }

    /**
     * This removes a hologram at the given location.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} of the hologram to remove
     * 
     * @return Whether the hologram was successfully removed
     */
    public static boolean removeHologram(@Nonnull Plugin plugin, @Nonnull Location location) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            return service.removeHologram(location);
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return false;
        }
    }

    /**
     * This gets an existing hologram at the given location.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} of the hologram
     * 
     * @return The {@link EntityHologram} or null if none exists
     */
    @Nullable
    public static EntityHologram getHologram(@Nonnull Plugin plugin, @Nonnull Location location) {
        Validate.notNull(plugin, "Plugin cannot be null");
        Validate.notNull(location, "Location cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null && service.isEnabled()) {
            return service.getHologramAt(location);
        } else {
            plugin.getLogger().log(Level.WARNING, "Hologram service not initialized for plugin: " + plugin.getName());
            return null;
        }
    }

    /**
     * This checks if a hologram exists at the given location.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} to check
     * 
     * @return Whether a hologram exists at the location
     */
    public static boolean hasHologram(@Nonnull Plugin plugin, @Nonnull Location location) {
        return getHologram(plugin, location) != null;
    }

    /**
     * This returns the number of cached holograms for the given plugin.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * 
     * @return The number of cached holograms
     */
    public static int getCachedHologramCount(@Nonnull Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");

        EntityHologramService service = getService(plugin);
        if (service != null) {
            return service.getCachedHolograms();
        }
        return 0;
    }

    /**
     * This checks if the hologram service is enabled for the given plugin.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * 
     * @return Whether the service is enabled
     */
    public static boolean isServiceEnabled(@Nonnull Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");

        EntityHologramService service = getService(plugin);
        return service != null && service.isEnabled();
    }

    /**
     * Convenience method to create a simple text hologram.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} where to create the hologram
     * @param text
     *            The text to display
     * @param scale
     *            The scale of the hologram (1.0 = normal size)
     * 
     * @return The created {@link EntityHologram} or null if creation failed
     */
    @Nullable
    @ParametersAreNonnullByDefault
    public static EntityHologram createSimpleHologram(Plugin plugin, Location location, String text, float scale) {
        EntityHologram hologram = createHologram(plugin, location, text);
        if (hologram != null && scale != 1.0f) {
            updateHologramScale(plugin, location, scale);
        }
        return hologram;
    }

    /**
     * Convenience method to create a colored hologram with background.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     * @param location
     *            The {@link Location} where to create the hologram
     * @param text
     *            The text to display
     * @param backgroundColor
     *            The background color in ARGB format
     * @param textOpacity
     *            The text opacity (0-255)
     * 
     * @return The created {@link EntityHologram} or null if creation failed
     */
    @Nullable
    @ParametersAreNonnullByDefault
    public static EntityHologram createColoredHologram(Plugin plugin, Location location, String text, 
                                                      int backgroundColor, byte textOpacity) {
        EntityHologram hologram = createHologram(plugin, location, text);
        if (hologram != null) {
            updateHologramBackgroundColor(plugin, location, backgroundColor);
            updateHologramTextOpacity(plugin, location, textOpacity);
        }
        return hologram;
    }
}
