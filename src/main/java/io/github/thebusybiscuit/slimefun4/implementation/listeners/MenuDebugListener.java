package io.github.thebusybiscuit.slimefun4.implementation.listeners;

import javax.annotation.Nonnull;

import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

/**
 * Debug listener for testing menu opening issues
 */
public class MenuDebugListener implements Listener {

    public MenuDebugListener(@Nonnull Slimefun plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDebugClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (e.getItem() == null || e.getItem().getType() != Material.STICK) {
            return;
        }

        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        
        if (block == null) {
            return;
        }

        // Check if it's a Slimefun block
        SlimefunItem sfItem = BlockStorage.check(block);
        if (sfItem == null) {
            player.sendMessage(ColorSystem.colorize("&cNot a Slimefun block"));
        return;
    }

    player.sendMessage(ColorSystem.colorize("&e=== Menu Debug Info ==="));
    player.sendMessage(ColorSystem.colorize("&aBlock: " + sfItem.getId()));
    
    // Check if preset exists
    boolean hasPreset = BlockMenuPreset.isInventory(sfItem.getId());
    player.sendMessage(ColorSystem.colorize("&aHas Preset: " + hasPreset));
    
    if (hasPreset) {
        BlockMenuPreset preset = BlockMenuPreset.getPreset(sfItem.getId());
        player.sendMessage(ColorSystem.colorize("&aPreset ID: " + (preset != null ? preset.getID() : "null")));
    }
    
    // Check if inventory exists
    boolean hasInventory = BlockStorage.hasInventory(block);
    player.sendMessage(ColorSystem.colorize("&aHas Inventory: " + hasInventory));
    
    if (hasInventory) {
        BlockMenu menu = BlockStorage.getInventory(block);
        player.sendMessage(ColorSystem.colorize("&aMenu exists: " + (menu != null)));
        
        if (menu != null) {
            boolean canOpen = menu.canOpen(block, player);
            player.sendMessage(ColorSystem.colorize("&aCan Open: " + canOpen));
            
            if (canOpen) {
                player.sendMessage(ColorSystem.colorize("&aAttempting to open menu..."));
                menu.open(player);
            } else {
                player.sendMessage(ColorSystem.colorize("&cCannot open menu - permission denied"));
            }
        }
    } else {
        player.sendMessage(ColorSystem.colorize("&cNo inventory found for this block"));
    }
        
        e.setCancelled(true);
    }
}