package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.entities;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import eu.mrneznamy.slimefuncontinued.items.CustomItemStack;
import eu.mrneznamy.slimefuncontinued.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

/**
 * This is an abstract super class for Entity Assemblers.
 *
 * @author TheBusyBiscuit
 *
 * @see WitherAssembler
 * @see IronGolemAssembler
 *
 */
public abstract class AbstractEntityAssembler<T extends Entity> extends SimpleSlimefunItem<BlockTicker> implements EnergyNetComponent, InventoryBlock {

    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_OFFSET = "offset";

    private final int[] border = { 0, 2, 3, 4, 5, 6, 8, 12, 14, 21, 23, 30, 32, 39, 40, 41 };
    private final int[] inputSlots = { 19, 28, 25, 34 };

    private final int[] headSlots = { 19, 28 };
    private final int[] headBorder = { 9, 10, 11, 18, 20, 27, 29, 36, 37, 38 };

    private final int[] bodySlots = { 25, 34 };
    private final int[] bodyBorder = { 15, 16, 17, 24, 26, 33, 35, 42, 43, 44 };

    private int lifetime = 0;

    @ParametersAreNonnullByDefault
    protected AbstractEntityAssembler(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        createPreset(this, this::constructMenu);
        addItemHandler(onPlace(), onBreak());
    }

    @Nonnull
    private BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(true) {

            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                onPlace(e);
            }

            @Override
            public void onBlockPlacerPlace(BlockPlacerPlaceEvent e) {
                onPlace(e);
            }

            private void onPlace(BlockEvent e) {
                BlockStorage.addBlockInfo(e.getBlock(), KEY_OFFSET, "3.0");
                BlockStorage.addBlockInfo(e.getBlock(), KEY_ENABLED, String.valueOf(false));
            }
        };
    }

    @Nonnull
    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), headSlots);
                    inv.dropItems(b.getLocation(), bodySlots);
                }
            }
        };
    }

    private void updateBlockInventory(BlockMenu menu, Block b) {
        if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), KEY_ENABLED) == null || BlockStorage.getLocationInfo(b.getLocation(), KEY_ENABLED).equals(String.valueOf(false))) {
            menu.replaceExistingItem(22, CustomItemStack.create(Material.GUNPOWDER, "&7Enabled: &4\u2718", "", "&e> Click to enable this Machine"));
            menu.addMenuClickHandler(22, (p, slot, item, action) -> {
                BlockStorage.addBlockInfo(b, KEY_ENABLED, String.valueOf(true));
                updateBlockInventory(menu, b);
                return false;
            });
        } else {
            menu.replaceExistingItem(22, CustomItemStack.create(Material.REDSTONE, "&7Enabled: &2\u2714", "", "&e> Click to disable this Machine"));
            menu.addMenuClickHandler(22, (p, slot, item, action) -> {
                BlockStorage.addBlockInfo(b, KEY_ENABLED, String.valueOf(false));
                updateBlockInventory(menu, b);
                return false;
            });
        }

        double offset = (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), KEY_OFFSET) == null) ? 3.0F : Double.valueOf(BlockStorage.getLocationInfo(b.getLocation(), KEY_OFFSET));

        menu.replaceExistingItem(31, CustomItemStack.create(Material.PISTON, "&7Offset: &3" + offset + " Block(s)", "", "&fLeft Click: &7+0.1", "&fRight Click: &7-0.1"));
        menu.addMenuClickHandler(31, (p, slot, item, action) -> {
            double offsetv = NumberUtils.reparseDouble(Double.valueOf(BlockStorage.getLocationInfo(b.getLocation(), KEY_OFFSET)) + (action.isRightClicked() ? -0.1F : 0.1F));
            BlockStorage.addBlockInfo(b, KEY_OFFSET, String.valueOf(offsetv));
            updateBlockInventory(menu, b);
            return false;
        });
    }

    @Override
    public BlockTicker getItemHandler() {
        return new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                if ("false".equals(BlockStorage.getLocationInfo(b.getLocation(), KEY_ENABLED))) {
                    return;
                }

                if (lifetime % 60 == 0 && getCharge(b.getLocation(), data) >= getEnergyConsumption()) {
                    BlockMenu menu = BlockStorage.getInventory(b);

                    boolean hasBody = findResource(menu, getBody(), bodySlots);
                    boolean hasHead = findResource(menu, getHead(), headSlots);

                    if (hasBody && hasHead) {
                        consumeResources(menu);

                        removeCharge(b.getLocation(), getEnergyConsumption());
                        double offset = Double.parseDouble(BlockStorage.getLocationInfo(b.getLocation(), KEY_OFFSET));

                        Slimefun.runSync(() -> {
                            Location loc = new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + offset, b.getZ() + 0.5D);
                            spawnEntity(loc);

                            b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, getHead().getType());
                        });
                    }
                }
            }

            @Override
            public void uniqueTick() {
                lifetime++;
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        };
    }

    private boolean findResource(BlockMenu menu, ItemStack item, int[] slots) {
        int found = 0;

        for (int slot : slots) {
            if (SlimefunUtils.isItemSimilar(menu.getItemInSlot(slot), item, true, false)) {
                found += menu.getItemInSlot(slot).getAmount();

                if (found >= item.getAmount()) {
                    return true;
                }
            }
        }

        return false;
    }

    private void consumeResources(BlockMenu inv) {
        int bodyCount = getBody().getAmount();
        int headCount = getHead().getAmount();

        for (int slot : bodySlots) {
            if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(slot), getBody(), true, false)) {
                int amount = inv.getItemInSlot(slot).getAmount();

                if (amount >= bodyCount) {
                    inv.consumeItem(slot, bodyCount);
                    break;
                } else {
                    bodyCount -= amount;
                    inv.replaceExistingItem(slot, null);
                }
            }
        }

        for (int slot : headSlots) {
            if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(slot), getHead(), true, false)) {
                int amount = inv.getItemInSlot(slot).getAmount();

                if (amount >= headCount) {
                    inv.consumeItem(slot, headCount);
                    break;
                } else {
                    headCount -= amount;
                    inv.replaceExistingItem(slot, null);
                }
            }
        }
    }

    protected void constructMenu(BlockMenuPreset preset) {
        // Set inventory size to 45 slots to accommodate all border slots (up to slot 44)
        preset.setSize(45);
        
        for (int i : border) {
            preset.addItem(i, CustomItemStack.create(Material.GRAY_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : headBorder) {
            preset.addItem(i, CustomItemStack.create(getHeadBorder(), " "), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : bodyBorder) {
            preset.addItem(i, CustomItemStack.create(getBodyBorder(), " "), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(1, CustomItemStack.create(getHead(), "&7Head Slot", "", "&fThis Slot accepts the head type"), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(7, CustomItemStack.create(getBody(), "&7Body Slot", "", "&fThis Slot accepts the body type"), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(13, CustomItemStack.create(Material.CLOCK, "&7Cooldown: &b30 Seconds", "", "&fThis Machine takes up to half a Minute to operate", "&fso give it some Time!"), ChestMenuUtils.getEmptyClickHandler());

        preset.addMenuOpeningHandler(p -> {
            // Menu opening handler only receives Player parameter
        });
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public abstract int getEnergyConsumption();

    public abstract ItemStack getHead();

    public abstract ItemStack getBody();

    public abstract Material getHeadBorder();

    public abstract Material getBodyBorder();

    public abstract T spawnEntity(Location l);

    @Override
    public int[] getInputSlots() {
        return new int[] { 4, 5, 6, 7, 8, 13, 14, 15, 16, 17 };
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] { 22 };
    }

}
