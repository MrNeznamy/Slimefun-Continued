package io.github.thebusybiscuit.slimefun4.implementation.items.blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import eu.mrneznamy.utils.ColorSystem;
import eu.mrneznamy.slimefuncontinued.items.CustomItemStack;
import eu.mrneznamy.slimefuncontinued.entities.holograms.HologramManager;
import eu.mrneznamy.slimefuncontinued.entities.holograms.EntityHologram;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

/**
 * The {@link HologramProjector} is a very simple block which allows the {@link Player}
 * to create a floating text that is completely configurable.
 * 
 * This implementation uses the new entity-based hologram system from slimefun5
 * for better stability and performance compared to the old ArmorStand approach.
 *
 * @author TheBusyBiscuit
 * @author Kry-Vosa
 * @author SoSeDiK
 *
 * @see HologramOwner
 * @see HologramsService
 * @see HologramManager
 *
 */
public class HologramProjector extends SlimefunItem implements HologramOwner {

    private static final String OFFSET_PARAMETER = "offset";

    @ParametersAreNonnullByDefault
    public HologramProjector(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);

        addItemHandler(onPlace(), onBreak());
    }
    
    @Override
    public void preRegister() {
        // Register all handlers in preRegister for proper initialization
        addItemHandler(onPlace());
        addItemHandler(onRightClick());
        
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                // Check if hologram exists, if not recreate it
                String text = BlockStorage.getLocationInfo(b.getLocation(), "text");
                if (text != null && !text.isEmpty()) {
                    String offsetStr = BlockStorage.getLocationInfo(b.getLocation(), OFFSET_PARAMETER);
                    double offset = offsetStr != null ? Double.parseDouble(offsetStr) : 0.5;
                    Location hologramLocation = getHologramLocation(b, offset);
                    
                    // Check if hologram exists at this location
                    if (!HologramManager.hasHologram(Slimefun.instance(), hologramLocation)) {
                        createHologram(b);
                    }
                }
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    private @Nonnull BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(false) {

            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                Block b = e.getBlockPlaced();
                BlockStorage.addBlockInfo(b, "text", "Edit me via the Projector");
                BlockStorage.addBlockInfo(b, OFFSET_PARAMETER, "0.5");
                BlockStorage.addBlockInfo(b, "owner", e.getPlayer().getUniqueId().toString());

                createHologram(b);
            }

        };
    }

    private @Nonnull BlockBreakHandler onBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@Nonnull Block b) {
                removeHologram(b);
            }
        };
    }

    public @Nonnull BlockUseHandler onRightClick() {
        return e -> {
            e.cancel();

            Player p = e.getPlayer();
            Block b = e.getClickedBlock().get();

            String owner = BlockStorage.getLocationInfo(b.getLocation(), "owner");
            if (owner != null && owner.equals(p.getUniqueId().toString())) {
                openEditor(p, b);
            }
        };
    }

    private void openEditor(@Nonnull Player p, @Nonnull Block projector) {
        ChestMenu menu = new ChestMenu(Slimefun.getLocalization().getMessage(p, "machines.HOLOGRAM_PROJECTOR.inventory-title"));

        menu.addItem(0, CustomItemStack.create(Material.NAME_TAG, "&7Text &e(Click to edit)", "", "&f" + ColorSystem.colorize(BlockStorage.getLocationInfo(projector.getLocation(), "text"))));
        menu.addMenuClickHandler(0, (pl, slot, item, action) -> {
            pl.closeInventory();
            Slimefun.getLocalization().sendMessage(pl, "machines.HOLOGRAM_PROJECTOR.enter-text", true);

            ChatUtils.awaitInput(pl, message -> {
                // Fixes #3445 - Make sure the projector is not broken
                if (!BlockStorage.check(projector, getId())) {
                    // Hologram projector no longer exists.
                    // TODO: Add a chat message informing the player that their message was ignored.
                    return;
                }

                String coloredMessage = ColorSystem.colorize(message);
                updateHologramText(projector, coloredMessage);
                BlockStorage.addBlockInfo(projector, "text", coloredMessage);
                openEditor(pl, projector);
            });

            return false;
        });

        String offsetStr = BlockStorage.getLocationInfo(projector.getLocation(), OFFSET_PARAMETER);
        double currentOffset = offsetStr != null ? Double.parseDouble(offsetStr) : 0.5;
        menu.addItem(1, CustomItemStack.create(Material.CLOCK, "&7Offset: &e" + String.format("%.1f", currentOffset + 1.0D), "", "&fLeft Click: &7+0.1", "&fRight Click: &7-0.1"));
        menu.addMenuClickHandler(1, (pl, slot, item, action) -> {
            String currentOffsetStr = BlockStorage.getLocationInfo(projector.getLocation(), OFFSET_PARAMETER);
            double currentOffsetValue = currentOffsetStr != null ? Double.parseDouble(currentOffsetStr) : 0.5;
            double newOffset = Math.round((currentOffsetValue + (action.isRightClicked() ? -0.1F : 0.1F)) * 10.0) / 10.0;
            
            // First update the BlockStorage with new offset, then update hologram
            BlockStorage.addBlockInfo(projector, OFFSET_PARAMETER, String.valueOf(newOffset));
            updateHologramOffset(projector, currentOffsetValue, newOffset);
            openEditor(pl, projector);
            return false;
        });

        menu.open(p);
    }

    /**
     * Creates a new hologram for the projector block using the new entity-based system.
     * 
     * @param projector The projector block
     */
    private void createHologram(@Nonnull Block projector) {
        try {
            String text = BlockStorage.getLocationInfo(projector.getLocation(), "text");
            String offsetStr = BlockStorage.getLocationInfo(projector.getLocation(), OFFSET_PARAMETER);
            double offset = offsetStr != null ? Double.parseDouble(offsetStr) : 0.7;
            
            Location hologramLocation = getHologramLocation(projector, offset);
            
            EntityHologram hologram = HologramManager.createHologram(Slimefun.instance(), hologramLocation, text);
            if (hologram == null) {
                Slimefun.logger().warning("Failed to create hologram for projector at " + projector.getLocation());
            }
        } catch (Exception e) {
            Slimefun.logger().warning("Failed to create hologram for projector at " + projector.getLocation() + ": " + e.getMessage());
        }
    }
    
    /**
     * Updates the text of an existing hologram.
     * 
     * @param projector The projector block
     * @param text The new text to display
     */
    private void updateHologramText(@Nonnull Block projector, @Nonnull String text) {
        try {
            String offsetStr = BlockStorage.getLocationInfo(projector.getLocation(), OFFSET_PARAMETER);
            double offset = offsetStr != null ? Double.parseDouble(offsetStr) : 0.7;
            Location hologramLocation = getHologramLocation(projector, offset);
            
            if (!HologramManager.updateHologramText(Slimefun.instance(), hologramLocation, text)) {
                // Hologram doesn't exist, create a new one
                createHologram(projector);
            }
        } catch (Exception e) {
            Slimefun.logger().warning("Failed to update hologram text for projector at " + projector.getLocation() + ": " + e.getMessage());
        }
    }
    
    /**
     * Updates the offset (position) of an existing hologram.
     * 
     * @param projector The projector block
     * @param oldOffset The current Y offset from the block
     * @param newOffset The new Y offset from the block
     */
    private void updateHologramOffset(@Nonnull Block projector, double oldOffset, double newOffset) {
        try {
            // Calculate the old location using the old offset
            Location oldLocation = getHologramLocation(projector, oldOffset);
            
            // Remove the old hologram
            HologramManager.removeHologram(Slimefun.instance(), oldLocation);
            
            // Create a new hologram at the new location (using the new offset from BlockStorage)
            createHologram(projector);
        } catch (Exception e) {
            Slimefun.logger().warning("Failed to update hologram offset for projector at " + projector.getLocation() + ": " + e.getMessage());
        }
    }
    
    /**
     * Removes the hologram associated with the projector block.
     * 
     * @param projector The projector block
     */
    @Override
    public void removeHologram(@Nonnull Block projector) {
        try {
            String offsetStr = BlockStorage.getLocationInfo(projector.getLocation(), OFFSET_PARAMETER);
            double offset = offsetStr != null ? Double.parseDouble(offsetStr) : 0.7;
            Location hologramLocation = getHologramLocation(projector, offset);
            
            HologramManager.removeHologram(Slimefun.instance(), hologramLocation);
        } catch (Exception e) {
            Slimefun.logger().warning("Failed to remove hologram for projector at " + projector.getLocation() + ": " + e.getMessage());
        }
    }

    /**
     * Calculates the hologram location based on the projector block and offset.
     * 
     * @param projector The projector block
     * @param offset The Y offset from the block
     * @return The calculated hologram location
     */
    private Location getHologramLocation(@Nonnull Block projector, double offset) {
        return new Location(
            projector.getWorld(), 
            projector.getX() + 0.5, 
            projector.getY() + offset, 
            projector.getZ() + 0.5
        );
    }
}
