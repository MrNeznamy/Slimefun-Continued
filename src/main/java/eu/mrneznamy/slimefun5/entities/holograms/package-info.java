/**
 * This package contains the entity-based hologram system for slimefun5.
 * 
 * <h2>Overview</h2>
 * This system provides a more stable and resource-efficient alternative to ArmorStand-based holograms
 * by using TextDisplay entities introduced in Minecraft 1.19.4+.
 * 
 * <h2>Key Features</h2>
 * <ul>
 *   <li><strong>Better Performance:</strong> TextDisplay entities are more efficient than ArmorStands</li>
 *   <li><strong>Enhanced Stability:</strong> Less prone to glitches and visual artifacts</li>
 *   <li><strong>Advanced Features:</strong> Support for scaling, background colors, and text opacity</li>
 *   <li><strong>Automatic Cleanup:</strong> Built-in expiration system to prevent memory leaks</li>
 *   <li><strong>Thread Safety:</strong> Safe to use from multiple threads</li>
 *   <li><strong>Persistent Data:</strong> Holograms survive server restarts</li>
 * </ul>
 * 
 * <h2>Main Classes</h2>
 * <ul>
 *   <li>{@link eu.mrneznamy.slimefun5.entities.holograms.HologramManager} - Main API for creating and managing holograms</li>
 *   <li>{@link eu.mrneznamy.slimefun5.entities.holograms.EntityHologram} - Represents a single hologram entity</li>
 *   <li>{@link eu.mrneznamy.slimefun5.entities.holograms.EntityHologramService} - Internal service for hologram management</li>
 *   <li>{@link eu.mrneznamy.slimefun5.entities.holograms.HologramExample} - Usage examples and best practices</li>
 * </ul>
 * 
 * <h2>Quick Start</h2>
 * <pre>{@code
 * // In your plugin's onEnable() method
 * HologramManager.initialize(this);
 * 
 * // Create a simple hologram
 * Location loc = player.getLocation().add(0, 2, 0);
 * EntityHologram hologram = HologramManager.createHologram(this, loc, "§6Hello World!");
 * 
 * // Update the hologram
 * HologramManager.updateHologramText(this, loc, "§aUpdated Text!");
 * 
 * // Remove the hologram
 * HologramManager.removeHologram(this, loc);
 * 
 * // In your plugin's onDisable() method
 * HologramManager.shutdown(this);
 * }</pre>
 * 
 * <h2>Advanced Usage</h2>
 * <pre>{@code
 * // Create a scaled hologram with background
 * EntityHologram hologram = HologramManager.createColoredHologram(
 *     plugin, 
 *     location, 
 *     "§eImportant Message!", 
 *     0x80FF0000, // Semi-transparent red background
 *     (byte) 255  // Fully opaque text
 * );
 * 
 * // Set custom scale
 * HologramManager.updateHologramScale(plugin, location, 2.0f);
 * }</pre>
 * 
 * <h2>Migration from ArmorStand Holograms</h2>
 * <p>
 * This system is designed to be a drop-in replacement for ArmorStand-based hologram systems.
 * The main differences are:
 * </p>
 * <ul>
 *   <li>Uses TextDisplay entities instead of ArmorStands</li>
 *   <li>Requires Minecraft 1.19.4+ (TextDisplay entities were introduced in this version)</li>
 *   <li>Provides additional features like scaling and background colors</li>
 *   <li>Better performance and stability</li>
 * </ul>
 * 
 * <h2>Requirements</h2>
 * <ul>
 *   <li>Minecraft 1.19.4 or higher</li>
 *   <li>Bukkit/Spigot/Paper API</li>
 *   <li>slimefun5 library</li>
 * </ul>
 * 
 * <h2>Thread Safety</h2>
 * <p>
 * All public methods in this package are thread-safe. However, hologram creation and removal
 * must be performed on the main server thread. The system will automatically schedule
 * operations on the main thread when necessary.
 * </p>
 * 
 * <h2>Memory Management</h2>
 * <p>
 * The system includes automatic cleanup mechanisms:
 * </p>
 * <ul>
 *   <li>Holograms expire after 10 minutes of inactivity</li>
 *   <li>Cleanup task runs every minute to remove expired holograms</li>
 *   <li>All holograms are removed when the plugin is disabled</li>
 * </ul>
 * 
 * @author TheBusyBiscuit
 * @author NEZNAMY
 * @since 1.0.0
 * @version 1.0.0
 */
package eu.mrneznamy.slimefun5.entities.holograms;
