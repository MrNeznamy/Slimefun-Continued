package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import eu.mrneznamy.slimefun5.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;

public class HeatedPressureChamber extends AContainer {

    @ParametersAreNonnullByDefault
    public HeatedPressureChamber(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // BlockMenuPreset is already created by AContainer parent class
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(45, new ItemStack[] { SlimefunItems.OIL_BUCKET.item() }, new ItemStack[] { CustomItemStack.create(SlimefunItems.PLASTIC_SHEET.item(), 8) });
        registerRecipe(30, new ItemStack[] { SlimefunItems.GOLD_24K.item(), SlimefunItems.URANIUM.item() }, new ItemStack[] { SlimefunItems.BLISTERING_INGOT.item() });
        registerRecipe(60, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_2.item(), new ItemStack(Material.NETHER_STAR) }, new ItemStack[] { SlimefunItems.BLISTERING_INGOT_3.item() });
        registerRecipe(90, new ItemStack[] { SlimefunItems.PLUTONIUM.item(), SlimefunItems.URANIUM.item() }, new ItemStack[] { SlimefunItems.BOOSTED_URANIUM.item() });
        registerRecipe(60, new ItemStack[] { SlimefunItems.NETHER_ICE.item(), SlimefunItems.PLUTONIUM.item() }, new ItemStack[] { CustomItemStack.create(SlimefunItems.ENRICHED_NETHER_ICE.item(), 4) });
        registerRecipe(45, new ItemStack[] { SlimefunItems.ENRICHED_NETHER_ICE.item() }, new ItemStack[] { CustomItemStack.create(SlimefunItems.NETHER_ICE_COOLANT_CELL.item(), 8) });
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public String getMachineIdentifier() {
        return "HEATED_PRESSURE_CHAMBER";
    }
}

