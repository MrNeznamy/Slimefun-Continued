package io.github.thebusybiscuit.slimefun4.core.attributes;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import eu.mrneznamy.utils.ColorSystem;
import eu.mrneznamy.slimefun5.entities.holograms.HologramManager;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.HologramProjector;

/**
 * This {@link ItemAttribute} manages holograms using the new entity-based system.
 * 
 * @author TheBusyBiscuit
 * @author NEZNAMY (Entity-based implementation)
 * 
 * @see HologramProjector
 * @see HologramManager
 *
 */
public interface HologramOwner extends ItemAttribute {

    /**
     * This will update the hologram text for the given {@link Block}.
     * 
     * @param b
     *            The {@link Block} to which the hologram belongs
     * 
     * @param text
     *            The nametag for the hologram
     */
    default void updateHologram(@Nonnull Block b, @Nonnull String text) {
        Location loc = b.getLocation().add(getHologramOffset(b));
        
        // Use new entity-based system
        if (!HologramManager.updateHologramText(Slimefun.instance(), loc, ColorSystem.colorize(text))) {
            // Hologram doesn't exist, create a new one
            HologramManager.createHologram(Slimefun.instance(), loc, ColorSystem.colorize(text));
        }
    }

    /**
     * This will remove the hologram for the given {@link Block}.
     * 
     * @param b
     *            The {@link Block} to which the hologram blocks
     */
    default void removeHologram(@Nonnull Block b) {
        Location loc = b.getLocation().add(getHologramOffset(b));
        HologramManager.removeHologram(Slimefun.instance(), loc);
    }

    /**
     * This returns the offset of the hologram as a {@link Vector}.
     * This offset is applied to {@link Block#getLocation()} when spawning
     * the hologram.
     * 
     * @param block
     *            The {@link Block} which serves as the origin point
     * 
     * @return The hologram offset
     */
    @Nonnull
    default Vector getHologramOffset(@Nonnull Block block) {
        // Default offset for entity-based holograms (slightly higher than armor stands)
        return new Vector(0.5, 0.8, 0.5);
    }

    /**
     * Gets the {@link HologramManager} for direct access to advanced hologram features.
     * This method is deprecated as the new system uses static methods.
     * 
     * @return The {@link HologramManager} class for static access
     * @deprecated Use static methods in {@link HologramManager} directly
     */
    @Nonnull
    @Deprecated
    default Class<HologramManager> getHologramManager() {
        return HologramManager.class;
    }

}
