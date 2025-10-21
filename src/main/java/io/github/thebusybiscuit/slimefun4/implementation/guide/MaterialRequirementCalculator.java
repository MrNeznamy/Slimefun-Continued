package io.github.thebusybiscuit.slimefun4.implementation.guide;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

/**
 * This class calculates all required materials for crafting a Slimefun item,
 * including recursive calculation of sub-recipes with cycle prevention.
 * 
 * @author TheBusyBiscuit
 * @since 4.0
 */
public class MaterialRequirementCalculator {

    /**
     * Maximum recursion depth to prevent infinite loops
     */
    private static final int MAX_RECURSION_DEPTH = 10;
    
    /**
     * Set of RecipeTypes that should be ignored when calculating material requirements.
     * These represent ways to obtain items that don't require crafting materials.
     */
    private static final Set<RecipeType> IGNORED_RECIPE_TYPES = Set.of(
        RecipeType.GOLD_PAN,        // Obtained by using Gold Pan on gravel
        RecipeType.MOB_DROP,        // Dropped by mobs
        RecipeType.BARTER_DROP,     // Obtained by bartering with piglins
        RecipeType.GEO_MINER,       // Mined from the ground
        RecipeType.NUCLEAR_REACTOR, // Byproduct of nuclear reactor
        RecipeType.INTERACT         // Obtained by interaction
    );

    /**
     * Calculates all required materials for crafting the given SlimefunItem.
     * This includes recursive calculation of sub-recipes.
     * 
     * @param item The SlimefunItem to calculate materials for
     * @return A map of ItemStack to required amount
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public Map<ItemStack, Integer> calculateRequiredMaterials(SlimefunItem item) {
        Map<ItemStack, Integer> materials = new HashMap<>();
        Set<String> processed = new HashSet<>();
        
        calculateRecursiveMaterials(item, materials, 1, processed, 0);
        return materials;
    }

    /**
     * Recursively calculates required materials with cycle prevention.
     * 
     * @param item The SlimefunItem to process
     * @param materials The map to store material requirements
     * @param multiplier The amount multiplier for this item
     * @param processed Set of already processed item IDs to prevent cycles
     * @param depth Current recursion depth
     */
    @ParametersAreNonnullByDefault
    private void calculateRecursiveMaterials(SlimefunItem item, Map<ItemStack, Integer> materials, 
                                           int multiplier, Set<String> processed, int depth) {
        // Prevent infinite recursion
        if (depth >= MAX_RECURSION_DEPTH) {
            return;
        }
        
        // Prevent cycles
        if (processed.contains(item.getId())) {
            return;
        }
        
        processed.add(item.getId());
        
        try {
            // Skip items with ignored recipe types (drops, mining, etc.)
            if (IGNORED_RECIPE_TYPES.contains(item.getRecipeType())) {
                return;
            }
            
            ItemStack[] recipe = item.getRecipe();
            if (recipe != null) {
                processRecipe(recipe, materials, multiplier, processed, depth);
            }
        } finally {
            // Remove from processed set to allow the same item in different branches
            processed.remove(item.getId());
        }
    }

    /**
     * Processes a recipe array and adds materials to the requirements map.
     * 
     * @param recipe The recipe array to process
     * @param materials The map to store material requirements
     * @param multiplier The amount multiplier
     * @param processed Set of already processed item IDs
     * @param depth Current recursion depth
     */
    @ParametersAreNonnullByDefault
    private void processRecipe(ItemStack[] recipe, Map<ItemStack, Integer> materials, 
                             int multiplier, Set<String> processed, int depth) {
        for (ItemStack ingredient : recipe) {
            if (ingredient != null && ingredient.getType() != Material.AIR) {
                SlimefunItem subItem = SlimefunItem.getByItem(ingredient);
                
                if (subItem != null) {
                    // Check if this Slimefun item should be broken down or treated as a material
                    if (shouldBreakDownItem(subItem)) {
                        // Recursive call for craftable Slimefun items
                        calculateRecursiveMaterials(subItem, materials, 
                            multiplier * ingredient.getAmount(), processed, depth + 1);
                    } else {
                        // Treat as material requirement (non-craftable Slimefun item)
                        addMaterial(materials, ingredient, multiplier * ingredient.getAmount());
                    }
                } else {
                    // Base material (vanilla Minecraft item)
                    addMaterial(materials, ingredient, multiplier * ingredient.getAmount());
                }
            }
        }
    }

    /**
     * Adds a material to the requirements map, merging amounts if already present.
     * 
     * @param materials The map to add the material to
     * @param material The material to add
     * @param amount The amount to add
     */
    @ParametersAreNonnullByDefault
    private void addMaterial(Map<ItemStack, Integer> materials, ItemStack material, int amount) {
        // Find existing similar material or create new entry
        ItemStack existingKey = null;
        for (ItemStack key : materials.keySet()) {
            if (key.isSimilar(material)) {
                existingKey = key;
                break;
            }
        }
        
        if (existingKey != null) {
            materials.put(existingKey, materials.get(existingKey) + amount);
        } else {
            // Create a clean copy for the key
            ItemStack cleanMaterial = material.clone();
            cleanMaterial.setAmount(1);
            materials.put(cleanMaterial, amount);
        }
    }
    
    /**
     * Determines whether a Slimefun item should be broken down into its components
     * or treated as a material requirement.
     * 
     * @param item The SlimefunItem to check
     * @return true if the item should be broken down, false if it should be treated as a material
     */
    @ParametersAreNonnullByDefault
    private boolean shouldBreakDownItem(SlimefunItem item) {
        // Don't break down items with ignored recipe types (drops, mining, etc.)
        if (IGNORED_RECIPE_TYPES.contains(item.getRecipeType())) {
            return false;
        }
        
        // Don't break down items without recipes
        ItemStack[] recipe = item.getRecipe();
        if (recipe == null) {
            return false;
        }
        
        // Check if the recipe contains only vanilla materials
        // If so, break it down. If it contains other Slimefun items, treat as material
        boolean hasSlimefunIngredients = false;
        for (ItemStack ingredient : recipe) {
            if (ingredient != null && ingredient.getType() != Material.AIR) {
                SlimefunItem subItem = SlimefunItem.getByItem(ingredient);
                if (subItem != null) {
                    hasSlimefunIngredients = true;
                    break;
                }
            }
        }
        
        // Break down items that only use vanilla materials
        // Keep as materials items that require other Slimefun items
        return !hasSlimefunIngredients;
    }
}