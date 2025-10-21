package io.github.thebusybiscuit.slimefun4.implementation.guide;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This class checks the availability of materials in a player's inventory
 * for the Materials Required feature in the Slimefun Guide.
 * 
 * @author TheBusyBiscuit
 * @since 4.0
 */
public class InventoryChecker {

    /**
     * Checks the availability of a specific material in the player's inventory.
     * 
     * @param player The player to check
     * @param material The material to check for
     * @param required The required amount
     * @return The availability status
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public MaterialAvailability checkMaterialAvailability(Player player, ItemStack material, int required) {
        int available = countMaterialInInventory(player, material);
        
        if (available >= required) {
            return MaterialAvailability.SUFFICIENT;
        } else if (available > 0) {
            return MaterialAvailability.PARTIAL;
        } else {
            return MaterialAvailability.NONE;
        }
    }

    /**
     * Checks the availability of all materials in the given map.
     * 
     * @param player The player to check
     * @param materials Map of materials and their required amounts
     * @return Map of materials and their availability status
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public Map<ItemStack, MaterialRequirement> checkAllMaterials(Player player, Map<ItemStack, Integer> materials) {
        Map<ItemStack, MaterialRequirement> result = new HashMap<>();
        
        for (Map.Entry<ItemStack, Integer> entry : materials.entrySet()) {
            ItemStack material = entry.getKey();
            int required = entry.getValue();
            int available = countMaterialInInventory(player, material);
            
            MaterialAvailability availability = checkMaterialAvailability(player, material, required);
            result.put(material, new MaterialRequirement(required, available, availability));
        }
        
        return result;
    }

    /**
     * Counts how many of a specific material the player has in their inventory.
     * 
     * @param player The player to check
     * @param material The material to count
     * @return The total amount of the material in the inventory
     */
    @ParametersAreNonnullByDefault
    private int countMaterialInInventory(Player player, ItemStack material) {
        int count = 0;
        
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(material)) {
                count += item.getAmount();
            }
        }
        
        return count;
    }
}