package io.github.thebusybiscuit.slimefun4.core.services.holograms;

import java.util.function.Consumer;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import eu.mrneznamy.slimefuncontinued.blocks.BlockPosition;
import eu.mrneznamy.slimefuncontinued.entities.holograms.HologramManager;
import eu.mrneznamy.slimefuncontinued.entities.holograms.EntityHologram;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.HologramProjector;

/**
 * This service is responsible for handling holograms using the new entity-based system.
 * This is a wrapper around {@link HologramManager} to maintain compatibility with existing Slimefun code.
 * 
 * @author TheBusyBiscuit
 * @author NEZNAMY (Entity-based implementation)
 *
 * @see HologramOwner
 * @see HologramManager
 */
public class HologramsService {

    /**
     * Our {@link Plugin} instance
     */
    private final org.bukkit.plugin.Plugin plugin;

    /**
     * This creates a new {@link HologramsService} for the given {@link Plugin}.
     * 
     * @param plugin
     *            Our {@link Plugin} instance
     */
    public HologramsService(@Nonnull org.bukkit.plugin.Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * This will start the {@link HologramsService} and schedule the purge task.
     */
    public void start() {
        // The new HologramManager is initialized statically, no need to start anything
    }

    /**
     * This will get a {@link Hologram} for the given {@link Location}.
     * If no {@link Hologram} exists, a new one will be created.
     * 
     * @param loc
     *            The {@link Location}
     * 
     * @return The {@link Hologram} (legacy wrapper)
     * 
     * @deprecated Use {@link HologramManager} directly for better performance
     */
    @Nullable
    @Deprecated
    public Hologram getHologram(@Nonnull Location loc) {
        Validate.notNull(loc, "Location cannot be null");

        // Create or get hologram using new system
        EntityHologram entityHologram = HologramManager.getHologram(plugin, loc);
        if (entityHologram == null) {
            entityHologram = HologramManager.createHologram(plugin, loc, "");
        }
        
        // Return legacy wrapper for compatibility
        return entityHologram != null ? new LegacyHologramWrapper(entityHologram) : null;
    }

    /**
     * This will update the {@link Hologram}.
     * You can use it to set the nametag or other properties.
     * <p>
     * <strong>This method must be executed on the main {@link org.bukkit.Server} {@link Thread}.</strong>
     * 
     * @param loc
     *            The {@link Location}
     * @param consumer
     *            The callback to run
     */
    private void updateHologram(@Nonnull Location loc, @Nonnull Consumer<Hologram> consumer) {
        Validate.notNull(loc, "Location must not be null");
        Validate.notNull(consumer, "Callbacks must not be null");

        Runnable runnable = () -> {
            try {
                Hologram hologram = getHologram(loc);

                if (hologram != null) {
                    consumer.accept(hologram);
                }
            } catch (Exception | LinkageError x) {
                Slimefun.logger().log(Level.SEVERE, "Hologram located at {0}", new BlockPosition(loc));
                Slimefun.logger().log(Level.SEVERE, "Something went wrong while trying to update this hologram", x);
            }
        };

        if (Bukkit.isPrimaryThread()) {
            runnable.run();
        } else {
            Slimefun.runSync(runnable);
        }
    }

    /**
     * This removes the {@link Hologram} at that given {@link Location}.
     * <p>
     * <strong>This method must be executed on the main {@link org.bukkit.Server} {@link Thread}.</strong>
     * 
     * @param loc
     *            The {@link Location}
     * 
     * @return Whether the {@link Hologram} could be removed, false if the {@link Hologram} does not
     *         exist or was already removed
     */
    public boolean removeHologram(@Nonnull Location loc) {
        Validate.notNull(loc, "Location cannot be null");

        if (Bukkit.isPrimaryThread()) {
            try {
                return HologramManager.removeHologram(plugin, loc);
            } catch (Exception | LinkageError x) {
                Slimefun.logger().log(Level.SEVERE, "Hologram located at {0}", new BlockPosition(loc));
                Slimefun.logger().log(Level.SEVERE, "Something went wrong while trying to remove this hologram", x);
                return false;
            }
        } else {
            throw new UnsupportedOperationException("You cannot remove a hologram asynchronously.");
        }
    }

    /**
     * This will update the label of the {@link Hologram}.
     * 
     * @param loc
     *            The {@link Location} of this {@link Hologram}
     * @param label
     *            The label to set, can be null
     */
    public void setHologramLabel(@Nonnull Location loc, @Nullable String label) {
        Validate.notNull(loc, "Location must not be null");

        updateHologram(loc, hologram -> hologram.setLabel(label));
    }

    /**
     * Gets the underlying {@link HologramManager} class for direct access to the new entity-based system.
     * 
     * @return The {@link HologramManager} class
     * @deprecated Use static methods in {@link HologramManager} directly
     */
    @Nonnull
    @Deprecated
    public Class<HologramManager> getHologramManager() {
        return HologramManager.class;
    }

    /**
     * Legacy wrapper class to maintain compatibility with existing Slimefun code.
     * This wraps the new {@link EntityHologram} to provide the old {@link Hologram} interface.
     */
    private static class LegacyHologramWrapper extends Hologram {
        private final EntityHologram entityHologram;

        public LegacyHologramWrapper(@Nonnull EntityHologram entityHologram) {
            super(entityHologram.getUniqueId());
            this.entityHologram = entityHologram;
        }

        @Override
        void setLabel(@Nullable String label) {
            entityHologram.setLabel(label);
        }

        @Override
        void remove() {
            entityHologram.remove();
        }

        @Override
        boolean hasDespawned() {
            return entityHologram.hasDespawned();
        }

        @Override
        boolean hasExpired() {
            return entityHologram.hasExpired();
        }
    }

}
