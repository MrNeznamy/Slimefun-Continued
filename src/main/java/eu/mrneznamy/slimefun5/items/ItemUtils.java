package eu.mrneznamy.slimefun5.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

/**
 * ItemUtils implementation for slimefun5 - replaces Dough ItemUtils
 */
public class ItemUtils {
    
    /**
     * Compares two ItemStacks regardless of amount
     */
    public static boolean isItemSimilar(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }
        
        if (item1.getType() != item2.getType()) {
            return false;
        }
        
        ItemMeta meta1 = item1.getItemMeta();
        ItemMeta meta2 = item2.getItemMeta();
        
        return Objects.equals(meta1, meta2);
    }
    
    /**
     * Damages an item by the specified amount
     * @param item The item to damage
     * @param damage The amount of damage to apply
     * @param unbreaking Whether to consider unbreaking enchantment
     */
    public static void damageItem(ItemStack item, int damage, boolean unbreaking) {
        if (item == null || item.getType().isAir() || item.getAmount() <= 0) {
            return;
        }
        
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable damageable && !meta.isUnbreakable()) {
            int currentDamage = damageable.getDamage();
            int newDamage = currentDamage + damage;
            
            if (newDamage >= item.getType().getMaxDurability()) {
                // Item breaks
                item.setAmount(0);
            } else {
                damageable.setDamage(newDamage);
                item.setItemMeta(meta);
            }
        }
    }
    
    /**
     * Consumes specified amount from ItemStack
     */
    public static void consumeItem(ItemStack item, int amount) {
        if (item != null && item.getAmount() >= amount) {
            item.setAmount(item.getAmount() - amount);
        }
    }
    
    /**
     * Gets the display name of an ItemStack
     * @param item The ItemStack to get the name from
     * @return The display name or the material name if no display name is set
     */
    public static String getItemName(ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return "";
        }
        
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            return meta.getDisplayName();
        }
        
        // Return the material name as fallback
        return item.getType().name();
    }
    
    /**
     * Checks if two ItemStacks can be stacked together
     * @param item1 The first ItemStack
     * @param item2 The second ItemStack
     * @return True if the items can be stacked together
     */
    public static boolean canStack(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return false;
        }
        
        if (item1.getType().isAir() || item2.getType().isAir()) {
            return false;
        }
        
        return item1.isSimilar(item2);
    }
}
