package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import eu.mrneznamy.slimefun5.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.Smeltery;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

/**
 * @author TheBusyBiscuit
 *
 */
public class ElectricSmeltery extends AContainer implements NotHopperable {

    private static final int[] border = { 4, 5, 6, 7, 8, 13, 31, 40, 41, 42, 43, 44 };
    private static final int[] inputBorder = { 0, 1, 2, 3, 9, 12, 18, 21, 27, 30, 36, 37, 38, 39 };
    private static final int[] outputBorder = { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };

    @ParametersAreNonnullByDefault
    public ElectricSmeltery(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // BlockMenuPreset is already created by AContainer parent class
    }

    @Override
    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : inputBorder) {
            preset.addItem(i, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : outputBorder) {
            preset.addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
            preset.addMenuClickHandler(i, ChestMenuUtils.getDefaultOutputHandler());
        }

        preset.addItem(22, CustomItemStack.create(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public int[] getInputSlots() {
        return new int[] { 10, 11, 19, 20, 28, 29 };
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] { 24, 25 };
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_SMELTERY";
    }
}

