package eu.mrneznamy.slimefun5.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.Optional;

/**
 * A snapshot of a recipe's ingredients and result
 * Replaces io.github.bakedlibs.dough.recipes.RecipeSnapshot
 */
public class RecipeSnapshot {

    private final ItemStack result;
    private final List<RecipeChoice> ingredients;
    private final String[] shape;
    private final boolean shaped;
    
    // For server-wide recipe snapshot
    private final Map<NamespacedKey, Recipe> recipeMap;
    private final Map<ItemStack, List<Recipe>> recipesByResult;

    /**
     * Creates a RecipeSnapshot from all server recipes (for Plugin)
     * @param plugin The plugin requesting the snapshot
     */
    public RecipeSnapshot(@Nonnull Plugin plugin) {
        this.result = null;
        this.ingredients = new ArrayList<>();
        this.shape = new String[0];
        this.shaped = false;
        
        // Initialize server-wide recipe maps
        this.recipeMap = new HashMap<>();
        this.recipesByResult = new HashMap<>();
        
        // Collect all recipes from the server
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            
            // Add to recipe map if it's a keyed recipe
            if (recipe instanceof Keyed) {
                recipeMap.put(((Keyed) recipe).getKey(), recipe);
            }
            
            // Add to recipes by result map
            ItemStack result = recipe.getResult();
            recipesByResult.computeIfAbsent(result, k -> new ArrayList<>()).add(recipe);
        }
    }

    /**
     * Creates a RecipeSnapshot from a MinecraftRecipe
     * @param recipe The MinecraftRecipe
     */
    public RecipeSnapshot(@Nonnull MinecraftRecipe recipe) {
        this.result = recipe.getResult().clone();
        this.ingredients = new ArrayList<>(recipe.getIngredients());
        this.shape = recipe.getShape().clone();
        this.shaped = recipe.isShaped();
        this.recipeMap = null;
        this.recipesByResult = null;
    }

    /**
     * Creates a RecipeSnapshot from a Bukkit Recipe
     * @param recipe The Bukkit Recipe
     */
    public RecipeSnapshot(@Nonnull Recipe recipe) {
        this(new MinecraftRecipe(recipe));
    }

    /**
     * Creates a custom RecipeSnapshot
     * @param result The result ItemStack
     * @param ingredients The ingredients
     * @param shaped Whether this is a shaped recipe
     */
    public RecipeSnapshot(@Nonnull ItemStack result, @Nonnull List<RecipeChoice> ingredients, boolean shaped) {
        this(result, ingredients, shaped, new String[0]);
    }

    /**
     * Creates a custom RecipeSnapshot with shape
     * @param result The result ItemStack
     * @param ingredients The ingredients
     * @param shaped Whether this is a shaped recipe
     * @param shape The recipe shape
     */
    public RecipeSnapshot(@Nonnull ItemStack result, @Nonnull List<RecipeChoice> ingredients, boolean shaped, @Nonnull String[] shape) {
        this.result = Objects.requireNonNull(result, "Result cannot be null").clone();
        this.ingredients = new ArrayList<>(Objects.requireNonNull(ingredients, "Ingredients cannot be null"));
        this.shaped = shaped;
        this.shape = Objects.requireNonNull(shape, "Shape cannot be null").clone();
        this.recipeMap = null;
        this.recipesByResult = null;
    }

    /**
     * Gets the result of the recipe
     * @return The result ItemStack
     */
    @Nonnull
    public ItemStack getResult() {
        return result.clone();
    }

    /**
     * Gets the ingredients of the recipe
     * @return List of ingredients
     */
    @Nonnull
    public List<RecipeChoice> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    /**
     * Gets the shape of the recipe
     * @return The shape array
     */
    @Nonnull
    public String[] getShape() {
        return shape.clone();
    }

    /**
     * Checks if this is a shaped recipe
     * @return True if shaped recipe
     */
    public boolean isShaped() {
        return shaped;
    }

    /**
     * Checks if this is a shapeless recipe
     * @return True if shapeless recipe
     */
    public boolean isShapeless() {
        return !shaped;
    }

    /**
     * Gets the number of ingredients
     * @return The number of ingredients
     */
    public int getIngredientCount() {
        return ingredients.size();
    }

    /**
     * Gets an ingredient at a specific index
     * @param index The index
     * @return The ingredient, or null if index is out of bounds
     */
    @Nullable
    public RecipeChoice getIngredient(int index) {
        if (index >= 0 && index < ingredients.size()) {
            return ingredients.get(index);
        }
        return null;
    }

    /**
     * Checks if the recipe contains a specific ingredient
     * @param ingredient The ingredient to check
     * @return True if the recipe contains this ingredient
     */
    public boolean containsIngredient(@Nonnull ItemStack ingredient) {
        for (RecipeChoice choice : ingredients) {
            if (choice.test(ingredient)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all possible ItemStacks for the ingredients
     * @return List of all possible ingredient combinations
     */
    @Nonnull
    public List<List<ItemStack>> getAllIngredientCombinations() {
        List<List<ItemStack>> combinations = new ArrayList<>();
        
        if (ingredients.isEmpty()) {
            combinations.add(new ArrayList<>());
            return combinations;
        }

        // Get all choices for each ingredient
        List<List<ItemStack>> choicesPerIngredient = new ArrayList<>();
        for (RecipeChoice choice : ingredients) {
            List<ItemStack> choices = new ArrayList<>();
            if (choice instanceof RecipeChoice.MaterialChoice) {
                RecipeChoice.MaterialChoice materialChoice = (RecipeChoice.MaterialChoice) choice;
                for (org.bukkit.Material material : materialChoice.getChoices()) {
                    choices.add(new ItemStack(material));
                }
            } else if (choice instanceof RecipeChoice.ExactChoice) {
                RecipeChoice.ExactChoice exactChoice = (RecipeChoice.ExactChoice) choice;
                choices.addAll(exactChoice.getChoices());
            }
            choicesPerIngredient.add(choices);
        }

        // Generate all combinations
        generateCombinations(choicesPerIngredient, 0, new ArrayList<>(), combinations);
        
        return combinations;
    }

    /**
     * Helper method to generate all combinations recursively
     */
    private void generateCombinations(List<List<ItemStack>> choicesPerIngredient, int index, 
                                    List<ItemStack> current, List<List<ItemStack>> result) {
        if (index == choicesPerIngredient.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (ItemStack choice : choicesPerIngredient.get(index)) {
            current.add(choice);
            generateCombinations(choicesPerIngredient, index + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // Server-wide recipe methods (only available when created with Plugin constructor)

    /**
     * Gets a recipe by its NamespacedKey
     * @param key The NamespacedKey
     * @return The recipe, or null if not found
     */
    @Nullable
    public Recipe getRecipe(@Nonnull NamespacedKey key) {
        if (recipeMap == null) {
            return null;
        }
        return recipeMap.get(key);
    }

    /**
     * Gets all recipes that produce the given ItemStack
     * @param result The result ItemStack
     * @return List of recipes that produce this result
     */
    @Nonnull
    public List<Recipe> getRecipesFor(@Nonnull ItemStack result) {
        if (recipesByResult == null) {
            return new ArrayList<>();
        }
        return recipesByResult.getOrDefault(result, new ArrayList<>());
    }

    /**
     * Gets all recipes of a specific type
     * @param recipeClass The recipe class to filter by
     * @param <T> The recipe type
     * @return List of recipes of the specified type
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T extends Recipe> List<T> getRecipes(@Nonnull Class<T> recipeClass) {
        if (recipeMap == null) {
            return new ArrayList<>();
        }
        
        List<T> recipes = new ArrayList<>();
        for (Recipe recipe : recipeMap.values()) {
            if (recipeClass.isInstance(recipe)) {
                recipes.add((T) recipe);
            }
        }
        return recipes;
    }

    /**
     * Gets the recipe output for a specific recipe type and input
     * @param recipeType The recipe type (e.g., MinecraftRecipe.FURNACE)
     * @param input The input ItemStack
     * @return Optional containing the output, or empty if not found
     */
    @Nonnull
    public Optional<ItemStack> getRecipeOutput(@Nonnull NamespacedKey recipeType, @Nonnull ItemStack input) {
        if (recipeMap == null) {
            return Optional.empty();
        }

        for (Recipe recipe : recipeMap.values()) {
            if (recipe instanceof org.bukkit.inventory.FurnaceRecipe && recipeType.equals(MinecraftRecipe.FURNACE)) {
                org.bukkit.inventory.FurnaceRecipe furnaceRecipe = (org.bukkit.inventory.FurnaceRecipe) recipe;
                RecipeChoice choice = furnaceRecipe.getInputChoice();
                if (choice != null && choice.test(input)) {
                    return Optional.of(furnaceRecipe.getResult());
                }
            }
            // Add other recipe types as needed
        }

        return Optional.empty();
    }

    /**
     * Gets the recipe input for a given recipe
     * @param recipe The recipe
     * @return Array of RecipeChoice representing the input
     */
    @Nonnull
    public RecipeChoice[] getRecipeInput(@Nonnull Recipe recipe) {
        MinecraftRecipe minecraftRecipe = new MinecraftRecipe(recipe);
        List<RecipeChoice> ingredients = minecraftRecipe.getIngredients();
        return ingredients.toArray(new RecipeChoice[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        RecipeSnapshot that = (RecipeSnapshot) obj;
        return shaped == that.shaped &&
               result.equals(that.result) &&
               ingredients.equals(that.ingredients) &&
               Arrays.equals(shape, that.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, ingredients, Arrays.hashCode(shape), shaped);
    }

    @Override
    public String toString() {
        return "RecipeSnapshot{" +
               "result=" + result +
               ", ingredients=" + ingredients.size() +
               ", shaped=" + shaped +
               "}";
    }
}
