package io.github.thebusybiscuit.slimefun4.implementation.items.cargo;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.cargo.CargoNet;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class CargoManager extends SlimefunItem implements HologramOwner {

    @ParametersAreNonnullByDefault
    public CargoManager(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    private BlockBreakHandler onBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(@Nonnull Block b) {
                removeHologram(b);
            }
        };
    }

    @Override
    public void preRegister() {
        addItemHandler(onBreak(), new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                CargoNet.getNetworkFromLocationOrCreate(b.getLocation()).tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }

        }, new BlockUseHandler() {

            @Override
            public void onRightClick(PlayerRightClickEvent e) {
                Optional<Block> block = e.getClickedBlock();

                if (block.isPresent()) {
                    Player p = e.getPlayer();
                    Block b = block.get();

                    if (BlockStorage.getLocationInfo(b.getLocation(), "visualizer") == null) {
                        // Visualizer is currently enabled (null = enabled), so disable it
                        BlockStorage.addBlockInfo(b, "visualizer", "disabled");
                        p.sendMessage(ColorSystem.colorize("&cCargo Net Visualizer: " + "&4\u2718 &7(Disabled)"));
                    } else {
                        // Visualizer is currently disabled, so enable it
                        BlockStorage.addBlockInfo(b, "visualizer", null);
                        p.sendMessage(ColorSystem.colorize("&aCargo Net Visualizer: " + "&2\u2714 &7(Enabled)"));
                    }
                }
            }
        });
    }

}
