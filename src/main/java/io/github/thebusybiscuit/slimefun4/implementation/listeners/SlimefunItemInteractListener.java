package io.github.thebusybiscuit.slimefun4.implementation.listeners;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;

/**
 * This {@link Listener} listens to the {@link PlayerInteractEvent}.
 * It is also responsible for calling our {@link PlayerRightClickEvent} and triggering any
 * {@link ItemUseHandler} or {@link BlockUseHandler} for the clicked {@link ItemStack} or {@link Block}.
 * 
 * @author TheBusyBiscuit
 * @author Liruxo
 * 
 * @see PlayerRightClickEvent
 * @see ItemUseHandler
 * @see BlockUseHandler
 *
 */
public class SlimefunItemInteractListener implements Listener {

    public SlimefunItemInteractListener(@Nonnull Slimefun plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // Only process main hand events - ignore off-hand completely
            if (e.getHand() != EquipmentSlot.HAND) {
                return;
            }
            

            
            // Exclude the Debug Fish here because it is handled in a seperate Listener
            if (SlimefunUtils.isItemSimilar(e.getItem(), SlimefunItems.DEBUG_FISH.item(), true)) {
                return;
            }

            // Fixes #4087 - Prevents players from interacting with a block that is about to be deleted
            // We especially don't want to open inventories as that can cause duplication
            if (e.getClickedBlock() != null && Slimefun.getTickerTask().isDeletedSoon(e.getClickedBlock().getLocation())) {
                e.setCancelled(true);
                return;
            }

            // Fire our custom Event
            PlayerRightClickEvent event = new PlayerRightClickEvent(e);
            Bukkit.getPluginManager().callEvent(event);

            boolean itemUsed = (e.getHand() == EquipmentSlot.HAND);

            // Only handle the Item if it hasn't been denied
            if (event.useItem() != Result.DENY) {
                rightClickItem(e, event, itemUsed);
            }

            // Check if the clicked block is a Slimefun block first
            Block clickedBlock = event.getInteractEvent().getClickedBlock();
            SlimefunItem conditionCheckItem = clickedBlock != null ? BlockStorage.check(clickedBlock) : null;
            boolean isSlimefunBlock = conditionCheckItem != null;
            
            // Allow rightClickBlock if: 1) no item used, OR 2) clicking on Slimefun block (takes priority)
            if ((!itemUsed || isSlimefunBlock) && event.useBlock() != Result.DENY) {
                boolean result = rightClickBlock(event);
                if (!result) {
                    return;
                }
            }

            /**
             * If the original Event was not denied but the custom one was,
             * we also want to deny the original one.
             * This only applies for non-denied events because we do not want to
             * override any protective checks.
             */

            if (e.useInteractedBlock() != Result.DENY) {
                e.setUseInteractedBlock(event.useBlock());
            }

            if (e.useItemInHand() != Result.DENY) {
                e.setUseItemInHand(event.useItem());
            }
        }
    }

    @ParametersAreNonnullByDefault
    private boolean rightClickItem(PlayerInteractEvent e, PlayerRightClickEvent event, boolean defaultValue) {
        Optional<SlimefunItem> optional = event.getSlimefunItem();

        if (optional.isPresent()) {
            SlimefunItem sfItem = optional.get();

            if (sfItem.canUse(e.getPlayer(), true)) {
                return sfItem.callItemHandler(ItemUseHandler.class, handler -> {
                    handler.onRightClick(event);
                });
            } else {
                event.setUseItem(Result.DENY);
            }
        }

        return defaultValue;
    }

    @ParametersAreNonnullByDefault
    private boolean rightClickBlock(PlayerRightClickEvent event) {
        Optional<SlimefunItem> optional = event.getSlimefunBlock();

        if (optional.isPresent()) {
            SlimefunItem sfItem = optional.get();
            Player p = event.getPlayer();

            if (!sfItem.canUse(event.getPlayer(), true)) {
                event.getInteractEvent().setCancelled(true);
                return false;
            }

            sfItem.callItemHandler(BlockUseHandler.class, handler -> handler.onRightClick(event));

            // Check if the event was cancelled by the BlockUseHandler
            if (event.useBlock() != Result.DENY) {
                boolean hasPreset = BlockMenuPreset.isInventory(sfItem.getId());
                if (hasPreset) {
                    openInventory(p, sfItem, event.getInteractEvent().getClickedBlock(), event);
                    return false;
                }
            }
        }

        return true;
    }

    @ParametersAreNonnullByDefault
    private void openInventory(Player p, SlimefunItem item, Block clickedBlock, PlayerRightClickEvent event) {
        try {
            if (!p.isSneaking() || event.getItem().getType() == Material.AIR) {
                event.getInteractEvent().setCancelled(true);
                
                if (BlockStorage.hasUniversalInventory(item.getId())) {
                    UniversalBlockMenu menu = BlockStorage.getUniversalInventory(item.getId());

                    if (menu.canOpen(clickedBlock, p)) {
                        menu.open(p);
                    } else {
                        Slimefun.getLocalization().sendMessage(p, "inventory.no-access", true);
                    }
                } else {
                    BlockStorage storage = BlockStorage.getStorage(clickedBlock.getWorld());
                    if (storage == null) {
                        return;
                    }
                    
                    boolean hasInventory = storage.hasInventory(clickedBlock.getLocation());
                    
                    if (hasInventory) {
                        BlockMenu menu = BlockStorage.getInventory(clickedBlock.getLocation());

                        if (menu != null && menu.canOpen(clickedBlock, p)) {
                            menu.open(p);
                        } else {
                            Slimefun.getLocalization().sendMessage(p, "inventory.no-access", true);
                        }
                    }
                }
            }
        } catch (Exception | LinkageError x) {
            item.error("An Exception was caught while trying to open the Inventory", x);
        }
    }

}
