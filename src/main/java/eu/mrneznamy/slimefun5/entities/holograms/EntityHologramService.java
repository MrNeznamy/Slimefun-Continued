package eu.mrneznamy.slimefun5.entities.holograms;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import eu.mrneznamy.slimefun5.blocks.BlockPosition;

/**
 * This service is responsible for handling entity-based holograms using TextDisplay entities.
 * This is a more stable and resource-efficient alternative to ArmorStand-based holograms.
 * 
 * @author TheBusyBiscuit
 * @author NEZNAMY
 */
public class EntityHologramService {

    /**
     * The radius in which we scan for holograms
     */
    private static final double RADIUS = 0.45;

    /**
     * The {@link Plugin} instance
     */
    private final Plugin plugin;

    /**
     * The {@link Logger} for this service
     */
    private final Logger logger;

    /**
     * Our {@link NamespacedKey} for persistent data
     */
    private final NamespacedKey persistentDataKey;

    /**
     * Our cache for holograms
     */
    private final ConcurrentMap<BlockPosition, EntityHologram> cache = new ConcurrentHashMap<>();

    /**
     * Whether the service is enabled
     */
    private boolean enabled = true;

    /**
     * This creates a new {@link EntityHologramService}.
     * 
     * @param plugin
     *            The {@link Plugin} instance
     */
    public EntityHologramService(@Nonnull Plugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.persistentDataKey = new NamespacedKey(plugin, "hologram_position");

        // Start the cleanup task
        startCleanupTask();
    }

    /**
     * This starts the cleanup task that removes expired holograms.
     */
    private void startCleanupTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (enabled) {
                    purge();
                }
            }
        }.runTaskTimer(plugin, 20L * 60L, 20L * 60L); // Run every minute
    }

    /**
     * This purges any expired {@link EntityHologram}.
     */
    private void purge() {
        Iterator<EntityHologram> iterator = cache.values().iterator();

        while (iterator.hasNext()) {
            EntityHologram hologram = iterator.next();

            if (hologram.hasExpired() || hologram.hasDespawned()) {
                iterator.remove();

                if (!hologram.hasDespawned()) {
                    hologram.remove();
                }
            }
        }
    }

    /**
     * This method disables the service and cleans up all holograms.
     */
    public void disable() {
        enabled = false;
        
        // Remove all holograms
        for (EntityHologram hologram : cache.values()) {
            hologram.remove();
        }
        
        cache.clear();
    }

    /**
     * This returns whether the service is enabled.
     * 
     * @return Whether the service is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This returns the number of cached holograms.
     * 
     * @return The number of cached holograms
     */
    public int getCachedHolograms() {
        return cache.size();
    }

    /**
     * This method returns an existing (or creates a new) {@link EntityHologram} at the given {@link Location}.
     * 
     * @param loc
     *            The {@link Location}
     * @param createIfNoneExists
     *            Whether to create a new {@link TextDisplay} if none was found
     * 
     * @return The existing (or newly created) hologram
     */
    @Nullable
    private EntityHologram getHologram(@Nonnull Location loc, boolean createIfNoneExists) {
        Validate.notNull(loc, "Location cannot be null");

        BlockPosition position = new BlockPosition(loc);
        EntityHologram hologram = cache.get(position);

        // Check if the TextDisplay was cached and still exists
        if (hologram != null && !hologram.hasDespawned()) {
            return hologram;
        }

        // Scan all nearby entities which could be possible holograms
        Collection<Entity> holograms = loc.getWorld().getNearbyEntities(loc, RADIUS, RADIUS, RADIUS, this::isHologram);

        for (Entity entity : holograms) {
            if (entity instanceof TextDisplay textDisplay) {
                PersistentDataContainer container = textDisplay.getPersistentDataContainer();

                /*
                 * Any hologram we created will have a persistent data key for identification.
                 * Make sure that the value matches our BlockPosition.
                 */
                if (EntityHologram.hasHologramData(container, position, persistentDataKey)) {
                    if (hologram != null) {
                        // Remove any duplicates we find
                        entity.remove();
                    } else {
                        hologram = getAsHologram(position, textDisplay, container);
                    }
                }
            }
        }

        if (hologram == null && createIfNoneExists) {
            // Spawn a new TextDisplay
            TextDisplay textDisplay = (TextDisplay) loc.getWorld().spawnEntity(loc, EntityType.TEXT_DISPLAY);
            PersistentDataContainer container = textDisplay.getPersistentDataContainer();

            return getAsHologram(position, textDisplay, container);
        } else {
            return hologram;
        }
    }

    /**
     * This checks if a given {@link Entity} is a {@link TextDisplay}
     * and whether it has the correct attributes to be considered a hologram.
     * 
     * @param entity
     *            The {@link Entity} to check
     * 
     * @return Whether this could be a hologram
     */
    private boolean isHologram(@Nonnull Entity entity) {
        if (entity instanceof TextDisplay textDisplay) {
            // Check if it has our persistent data key
            return textDisplay.getPersistentDataContainer().has(persistentDataKey, PersistentDataType.LONG);
        }
        return false;
    }

    /**
     * This will cast the {@link Entity} to a {@link TextDisplay} and it will apply
     * all necessary attributes to the {@link TextDisplay}, then return an {@link EntityHologram}.
     * 
     * @param position
     *            The {@link BlockPosition} of this hologram
     * @param textDisplay
     *            The {@link TextDisplay} entity
     * @param container
     *            The {@link PersistentDataContainer} of the given {@link TextDisplay}
     * 
     * @return The {@link EntityHologram}
     */
    @Nullable
    private EntityHologram getAsHologram(@Nonnull BlockPosition position, @Nonnull TextDisplay textDisplay, @Nonnull PersistentDataContainer container) {
        // Configure the TextDisplay for hologram use
        textDisplay.setInvulnerable(true);
        textDisplay.setSilent(true);
        textDisplay.setGravity(false);
        textDisplay.setPersistent(true);
        textDisplay.setBillboard(org.bukkit.entity.Display.Billboard.CENTER);
        textDisplay.setSeeThrough(false);

        // Set a persistent tag to re-identify the correct hologram later
        container.set(persistentDataKey, PersistentDataType.LONG, position.getAsLong());

        // Store in cache for faster access
        EntityHologram hologram = new EntityHologram(textDisplay.getUniqueId(), position);
        cache.put(position, hologram);

        return hologram;
    }

    /**
     * This updates the {@link EntityHologram}.
     * You can use it to set the text or other properties.
     * <p>
     * <strong>This method must be executed on the main {@link org.bukkit.Server} thread.</strong>
     * 
     * @param loc
     *            The {@link Location}
     * @param consumer
     *            The callback to run
     */
    private void updateHologram(@Nonnull Location loc, @Nonnull Consumer<EntityHologram> consumer) {
        Validate.notNull(loc, "Location must not be null");
        Validate.notNull(consumer, "Callbacks must not be null");

        Runnable runnable = () -> {
            try {
                EntityHologram hologram = getHologram(loc, true);

                if (hologram != null) {
                    consumer.accept(hologram);
                }
            } catch (Exception | LinkageError x) {
                logger.log(Level.SEVERE, "Hologram located at " + new BlockPosition(loc), x);
                logger.log(Level.SEVERE, "Something went wrong while trying to update this hologram", x);
            }
        };

        if (Bukkit.isPrimaryThread()) {
            runnable.run();
        } else {
            Bukkit.getScheduler().runTask(plugin, runnable);
        }
    }

    /**
     * This removes the {@link EntityHologram} at that given {@link Location}.
     * <p>
     * <strong>This method must be executed on the main {@link org.bukkit.Server} thread.</strong>
     * 
     * @param loc
     *            The {@link Location}
     * 
     * @return Whether the {@link EntityHologram} could be removed, false if the {@link EntityHologram} does not
     *         exist or was already removed
     */
    public boolean removeHologram(@Nonnull Location loc) {
        Validate.notNull(loc, "Location cannot be null");

        if (Bukkit.isPrimaryThread()) {
            try {
                EntityHologram hologram = getHologram(loc, false);

                if (hologram != null) {
                    cache.remove(new BlockPosition(loc));
                    hologram.remove();
                    return true;
                } else {
                    return false;
                }
            } catch (Exception | LinkageError x) {
                logger.log(Level.SEVERE, "Hologram located at " + new BlockPosition(loc), x);
                logger.log(Level.SEVERE, "Something went wrong while trying to remove this hologram", x);
                return false;
            }
        } else {
            throw new UnsupportedOperationException("You cannot remove a hologram asynchronously.");
        }
    }

    /**
     * This will update the label of the {@link EntityHologram}.
     * 
     * @param loc
     *            The {@link Location} of this {@link EntityHologram}
     * @param label
     *            The label to set, can be null
     */
    public void setHologramLabel(@Nonnull Location loc, @Nullable String label) {
        Validate.notNull(loc, "Location must not be null");

        updateHologram(loc, hologram -> hologram.setLabel(label));
    }

    /**
     * This will update the scale of the {@link EntityHologram}.
     * 
     * @param loc
     *            The {@link Location} of this {@link EntityHologram}
     * @param scale
     *            The scale to set (1.0 = normal size)
     */
    public void setHologramScale(@Nonnull Location loc, float scale) {
        Validate.notNull(loc, "Location must not be null");

        updateHologram(loc, hologram -> hologram.setScale(scale));
    }

    /**
     * This will update the background color of the {@link EntityHologram}.
     * 
     * @param loc
     *            The {@link Location} of this {@link EntityHologram}
     * @param color
     *            The background color in ARGB format (0 = transparent)
     */
    public void setHologramBackgroundColor(@Nonnull Location loc, int color) {
        Validate.notNull(loc, "Location must not be null");

        updateHologram(loc, hologram -> hologram.setBackgroundColor(color));
    }

    /**
     * This will update the text opacity of the {@link EntityHologram}.
     * 
     * @param loc
     *            The {@link Location} of this {@link EntityHologram}
     * @param opacity
     *            The text opacity (0-255, where 255 is fully opaque)
     */
    public void setHologramTextOpacity(@Nonnull Location loc, byte opacity) {
        Validate.notNull(loc, "Location must not be null");

        updateHologram(loc, hologram -> hologram.setTextOpacity(opacity));
    }

    /**
     * This will get the {@link EntityHologram} at the given {@link Location}.
     * 
     * @param loc
     *            The {@link Location}
     * 
     * @return The {@link EntityHologram} or null if none exists
     */
    @Nullable
    public EntityHologram getHologramAt(@Nonnull Location loc) {
        Validate.notNull(loc, "Location cannot be null");
        return getHologram(loc, false);
    }

    /**
     * This will create a new {@link EntityHologram} at the given {@link Location}.
     * 
     * @param loc
     *            The {@link Location}
     * @param label
     *            The initial label for the hologram
     * 
     * @return The created {@link EntityHologram} or null if creation failed
     */
    @Nullable
    public EntityHologram createHologram(@Nonnull Location loc, @Nullable String label) {
        Validate.notNull(loc, "Location cannot be null");

        if (!Bukkit.isPrimaryThread()) {
            throw new UnsupportedOperationException("You cannot create a hologram asynchronously.");
        }

        EntityHologram hologram = getHologram(loc, true);
        if (hologram != null && label != null) {
            hologram.setLabel(label);
        }
        return hologram;
    }

    /**
     * This returns the {@link NamespacedKey} used for persistent data.
     * 
     * @return The {@link NamespacedKey}
     */
    @Nonnull
    public NamespacedKey getPersistentDataKey() {
        return persistentDataKey;
    }

    /**
     * This returns the {@link Plugin} instance.
     * 
     * @return The {@link Plugin}
     */
    @Nonnull
    public Plugin getPlugin() {
        return plugin;
    }
}
