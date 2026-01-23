package io.github.thebusybiscuit.slimefun4.implementation.listeners;

import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import eu.mrneznamy.utils.ColorSystem;
import eu.mrneznamy.slimefuncontinued.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;

import me.mrCookieSlime.Slimefun.api.BlockStorage;

/**
 * This {@link Listener} is responsible for handling our debugging tool, the debug fish.
 * This is where the functionality of this item is implemented.
 * 
 * @author TheBusyBiscuit
 *
 */
public class DebugFishListener implements Listener {

    private final String greenCheckmark;
    private final String redCross;

    public DebugFishListener(@Nonnull Slimefun plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        greenCheckmark = ColorSystem.colorize("&2\u2714");
        redCross = ColorSystem.colorize("&4\u2718");
    }

    @EventHandler
    public void onDebug(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL || e.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Player p = e.getPlayer();

        if (SlimefunUtils.isItemSimilar(e.getItem(), SlimefunItems.DEBUG_FISH.item(), true, false)) {
            e.setCancelled(true);

            if (p.hasPermission("slimefun.debugging")) {
                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    onLeftClick(p, e.getClickedBlock(), e);
                } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    onRightClick(p, e.getClickedBlock(), e.getBlockFace());
                }
            } else {
                Slimefun.getLocalization().sendMessage(p, "messages.no-permission", true);
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void onLeftClick(Player p, Block b, PlayerInteractEvent e) {
        if (p.isSneaking()) {
            if (BlockStorage.hasBlockInfo(b)) {
                BlockStorage.clearBlockInfo(b);
            }
        } else {
            e.setCancelled(false);
        }
    }

    @ParametersAreNonnullByDefault
    private void onRightClick(Player p, Block b, BlockFace face) {
        if (p.isSneaking()) {
            // Fixes #2655 - Delaying the placement to prevent a new event from being fired
            Slimefun.runSync(() -> {
                Block block = b.getRelative(face);
                block.setType(Material.PLAYER_HEAD);

                PlayerHead.setSkin(block, HeadTexture.MISSING_TEXTURE.getAsSkin(), true);
                SoundEffect.DEBUG_FISH_CLICK_SOUND.playFor(p);
            }, 2L);
        } else if (BlockStorage.hasBlockInfo(b)) {
            try {
                sendInfo(p, b);
            } catch (Exception x) {
                Slimefun.logger().log(Level.SEVERE, "An Exception occurred while using a Debug-Fish", x);
            }
        } else {
            // Read applicable Slimefun tags
            Set<SlimefunTag> tags = EnumSet.noneOf(SlimefunTag.class);

            for (SlimefunTag tag : SlimefunTag.values()) {
                if (tag.isTagged(b.getType())) {
                    tags.add(tag);
                }
            }

            if (!tags.isEmpty()) {
                p.sendMessage(" ");
                p.sendMessage(ColorSystem.colorize("&dSlimefun tags for: &e") + b.getType().name());

                for (SlimefunTag tag : tags) {
                    p.sendMessage(ColorSystem.colorize("&d* &e") + tag.name());
                }

                p.sendMessage(" ");
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void sendInfo(Player p, Block b) {
        SlimefunItem item = BlockStorage.check(b);

        p.sendMessage(" ");
        p.sendMessage(ColorSystem.colorize("&d" + b.getType() + " &e@ X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ()));
        p.sendMessage(ColorSystem.colorize("&dId: " + "&e" + item.getId()));
        p.sendMessage(ColorSystem.colorize("&dPlugin: " + "&e" + item.getAddon().getName()));

        if (b.getState() instanceof Skull) {
            p.sendMessage(ColorSystem.colorize("&dSkull: " + greenCheckmark));

            // Check if the skull is a wall skull, and if so use Directional instead of Rotatable.
            if (b.getType() == Material.PLAYER_WALL_HEAD) {
                p.sendMessage(ColorSystem.colorize("  &dFacing: &e" + ((Directional) b.getBlockData()).getFacing().toString()));
            } else {
                p.sendMessage(ColorSystem.colorize("  &dRotation: &e" + ((Rotatable) b.getBlockData()).getRotation().toString()));
            }
        }

        if (BlockStorage.getStorage(b.getWorld()).hasInventory(b.getLocation())) {
            p.sendMessage(ColorSystem.colorize("&dInventory: " + greenCheckmark));
        } else {
            p.sendMessage(ColorSystem.colorize("&dInventory: " + redCross));
        }

        if (item.isTicking()) {
            p.sendMessage(ColorSystem.colorize("&dTicking: " + greenCheckmark));
            p.sendMessage(ColorSystem.colorize("  &dAsync: &e" + (item.getBlockTicker().isSynchronized() ? redCross : greenCheckmark)));
        } else if (item instanceof EnergyNetProvider) {
            p.sendMessage(ColorSystem.colorize("&dTicking: &3Indirect (Generator)"));
        } else {
            p.sendMessage(ColorSystem.colorize("&dTicking: " + redCross));
        }

        if (Slimefun.getProfiler().hasTimings(b)) {
            p.sendMessage(ColorSystem.colorize("  &dTimings: &e" + Slimefun.getProfiler().getTime(b)));
            p.sendMessage(ColorSystem.colorize("  &dTotal Timings: &e" + Slimefun.getProfiler().getTime(item)));
            p.sendMessage(ColorSystem.colorize("  &dChunk Timings: &e" + Slimefun.getProfiler().getTime(b.getChunk())));
        }

        if (item instanceof EnergyNetComponent component) {
            p.sendMessage(ColorSystem.colorize("&dEnergyNet Component"));
            p.sendMessage(ColorSystem.colorize("  &dType: &e" + component.getEnergyComponentType()));

            if (component.isChargeable()) {
                p.sendMessage(ColorSystem.colorize("  &dChargeable: " + greenCheckmark));
                p.sendMessage(ColorSystem.colorize("  &dEnergy: &e" + component.getCharge(b.getLocation()) + " / " + component.getCapacity()));
            } else {
                p.sendMessage(ColorSystem.colorize("&dChargeable: " + redCross));
            }
        }

        p.sendMessage(ColorSystem.colorize("&6" + BlockStorage.getBlockInfoAsJson(b)));
        p.sendMessage(" ");
    }
}
