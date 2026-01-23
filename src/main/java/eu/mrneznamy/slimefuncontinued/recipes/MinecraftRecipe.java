package eu.mrneznamy.slimefuncontinued.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Utility class for working with Minecraft recipes
 * Replaces io.github.bakedlibs.dough.recipes.MinecraftRecipe
 */
public class MinecraftRecipe {

    public static final NamespacedKey FURNACE = NamespacedKey.minecraft("furnace");
    public static final NamespacedKey BLASTING = NamespacedKey.minecraft("blasting");
    public static final NamespacedKey SMOKING = NamespacedKey.minecraft("smoking");
    public static final NamespacedKey CAMPFIRE = NamespacedKey.minecraft("campfire_cooking");
    public static final NamespacedKey STONECUTTING = NamespacedKey.minecraft("stonecutting");

    private final Recipe recipe;
    private final NamespacedKey key;

    /**
     * Creates a MinecraftRecipe wrapper
     * @param recipe The Bukkit recipe
     */
    public MinecraftRecipe(@Nonnull Recipe recipe) {
        this.recipe = Objects.requireNonNull(recipe, "Recipe cannot be null");
        this.key = getRecipeKey(recipe);
    }

    /**
     * Gets the wrapped recipe
     * @return The Bukkit recipe
     */
    @Nonnull
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Gets the recipe key
     * @return The recipe key
     */
    @Nonnull
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * Gets the result of the recipe
     * @return The result ItemStack
     */
    @Nonnull
    public ItemStack getResult() {
        return recipe.getResult();
    }

    /**
     * Gets the ingredients of the recipe
     * @return List of ingredients
     */
    @Nonnull
    public List<RecipeChoice> getIngredients() {
        List<RecipeChoice> ingredients = new ArrayList<>();

        if (recipe instanceof ShapedRecipe) {
            ShapedRecipe shaped = (ShapedRecipe) recipe;
            Map<Character, RecipeChoice> choiceMap = shaped.getChoiceMap();
            String[] shape = shaped.getShape();

            for (String row : shape) {
                for (char c : row.toCharArray()) {
                    RecipeChoice choice = choiceMap.get(c);
                    if (choice != null) {
                        ingredients.add(choice);
                    } else if (c != ' ') {
                        // Handle legacy recipes that might use ItemStack instead of RecipeChoice
                        ItemStack item = shaped.getIngredientMap().get(c);
                        if (item != null && item.getType() != Material.AIR) {
                            ingredients.add(new RecipeChoice.ExactChoice(item));
                        }
                    }
                }
            }
        } else if (recipe instanceof ShapelessRecipe) {
            ShapelessRecipe shapeless = (ShapelessRecipe) recipe;
            ingredients.addAll(shapeless.getChoiceList());
        }

        return ingredients;
    }

    /**
     * Gets the shape of a shaped recipe
     * @return The shape array, or empty array if not a shaped recipe
     */
    @Nonnull
    public String[] getShape() {
        if (recipe instanceof ShapedRecipe) {
            return ((ShapedRecipe) recipe).getShape();
        }
        return new String[0];
    }

    /**
     * Checks if this is a shaped recipe
     * @return True if shaped recipe
     */
    public boolean isShaped() {
        return recipe instanceof ShapedRecipe;
    }

    /**
     * Checks if this is a shapeless recipe
     * @return True if shapeless recipe
     */
    public boolean isShapeless() {
        return recipe instanceof ShapelessRecipe;
    }

    /**
     * Creates a MinecraftRecipe from a Bukkit Recipe
     * @param recipe The Bukkit recipe
     * @return A MinecraftRecipe wrapper
     */
    @Nonnull
    public static MinecraftRecipe of(@Nonnull Recipe recipe) {
        return new MinecraftRecipe(recipe);
    }

    /**
     * Gets all recipes for a specific result
     * @param result The result to search for
     * @return List of recipes that produce this result
     */
    @Nonnull
    public static List<MinecraftRecipe> getRecipesFor(@Nonnull ItemStack result) {
        List<MinecraftRecipe> recipes = new ArrayList<>();
        
        for (Recipe recipe : Bukkit.getRecipesFor(result)) {
            recipes.add(new MinecraftRecipe(recipe));
        }
        
        return recipes;
    }

    /**
     * Gets all recipes for a specific material
     * @param material The material to search for
     * @return List of recipes that produce this material
     */
    @Nonnull
    public static List<MinecraftRecipe> getRecipesFor(@Nonnull Material material) {
        return getRecipesFor(new ItemStack(material));
    }

    /**
     * Gets a recipe by its key
     * @param key The recipe key
     * @return The recipe, or null if not found
     */
    @Nullable
    public static MinecraftRecipe getRecipe(@Nonnull NamespacedKey key) {
        Recipe recipe = Bukkit.getRecipe(key);
        return recipe != null ? new MinecraftRecipe(recipe) : null;
    }

    /**
     * Gets all registered recipes
     * @return Iterator of all recipes
     */
    @Nonnull
    public static Iterator<MinecraftRecipe> getAllRecipes() {
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        return new Iterator<MinecraftRecipe>() {
            @Override
            public boolean hasNext() {
                return recipeIterator.hasNext();
            }

            @Override
            public MinecraftRecipe next() {
                return new MinecraftRecipe(recipeIterator.next());
            }
        };
    }

    /**
     * Extracts the NamespacedKey from a recipe
     * @param recipe The recipe
     * @return The NamespacedKey
     */
    @Nonnull
    private static NamespacedKey getRecipeKey(@Nonnull Recipe recipe) {
        if (recipe instanceof ShapedRecipe) {
            return ((ShapedRecipe) recipe).getKey();
        } else if (recipe instanceof ShapelessRecipe) {
            return ((ShapelessRecipe) recipe).getKey();
        }
        
        // Fallback for other recipe types
        try {
            return (NamespacedKey) recipe.getClass().getMethod("getKey").invoke(recipe);
        } catch (Exception e) {
            // Create a fallback key
            return new NamespacedKey("minecraft", "unknown_" + recipe.hashCode());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        MinecraftRecipe that = (MinecraftRecipe) obj;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "MinecraftRecipe{key=" + key + ", result=" + recipe.getResult() + "}";
    }
}
