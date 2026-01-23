package eu.mrneznamy.slimefuncontinued.inventory;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Predicate;

/**
 * Utility class for inventory operations
 * Replaces io.github.bakedlibs.dough.inventory.InvUtils
 */
public final class InvUtils {

    private InvUtils() {}

    /**
     * Fits the given ItemStack into the inventory
     * @param inv The inventory
     * @param item The item to fit
     * @return The remaining ItemStack that couldn't fit
     */
    @Nullable
    public static ItemStack fit(@Nonnull Inventory inv, @Nonnull ItemStack item) {
        if (item.getAmount() <= 0) {
            return null;
        }

        ItemStack clone = item.clone();
        
        // First pass: try to add to existing stacks
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slot = inv.getItem(i);
            if (slot != null && slot.isSimilar(clone)) {
                int maxStack = Math.min(slot.getMaxStackSize(), inv.getMaxStackSize());
                int canAdd = maxStack - slot.getAmount();
                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, clone.getAmount());
                    slot.setAmount(slot.getAmount() + toAdd);
                    clone.setAmount(clone.getAmount() - toAdd);
                    if (clone.getAmount() <= 0) {
                        return null;
                    }
                }
            }
        }

        // Second pass: try to add to empty slots
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slot = inv.getItem(i);
            if (slot == null || slot.getType().isAir()) {
                int maxStack = Math.min(clone.getMaxStackSize(), inv.getMaxStackSize());
                int toAdd = Math.min(maxStack, clone.getAmount());
                ItemStack newStack = clone.clone();
                newStack.setAmount(toAdd);
                inv.setItem(i, newStack);
                clone.setAmount(clone.getAmount() - toAdd);
                if (clone.getAmount() <= 0) {
                    return null;
                }
            }
        }

        return clone.getAmount() > 0 ? clone : null;
    }

    /**
     * Pushes an ItemStack into the inventory
     * @param inv The inventory
     * @param item The item to push
     * @param replaceExisting Whether to replace existing items
     */
    public static void push(@Nonnull Inventory inv, @Nonnull ItemStack item, boolean replaceExisting) {
        if (replaceExisting) {
            for (int i = 0; i < inv.getSize(); i++) {
                if (inv.getItem(i) == null || inv.getItem(i).getType().isAir()) {
                    inv.setItem(i, item.clone());
                    return;
                }
            }
        } else {
            fit(inv, item);
        }
    }

    /**
     * Pushes an ItemStack into the inventory
     * @param inv The inventory
     * @param item The item to push
     */
    public static void push(@Nonnull Inventory inv, @Nonnull ItemStack item) {
        push(inv, item, false);
    }

    /**
     * Counts items in the inventory matching the predicate
     * @param inv The inventory
     * @param predicate The predicate to match items
     * @return The count of matching items
     */
    public static int count(@Nonnull Inventory inv, @Nonnull Predicate<ItemStack> predicate) {
        int count = 0;
        for (ItemStack item : inv.getContents()) {
            if (item != null && predicate.test(item)) {
                count += item.getAmount();
            }
        }
        return count;
    }

    /**
     * Counts items in the inventory similar to the given item
     * @param inv The inventory
     * @param item The item to count
     * @return The count of similar items
     */
    public static int count(@Nonnull Inventory inv, @Nonnull ItemStack item) {
        return count(inv, stack -> stack.isSimilar(item));
    }

    /**
     * Removes items from the inventory matching the predicate
     * @param inv The inventory
     * @param predicate The predicate to match items
     * @param amount The amount to remove
     * @return The amount actually removed
     */
    public static int remove(@Nonnull Inventory inv, @Nonnull Predicate<ItemStack> predicate, int amount) {
        int removed = 0;
        for (int i = 0; i < inv.getSize() && removed < amount; i++) {
            ItemStack item = inv.getItem(i);
            if (item != null && predicate.test(item)) {
                int toRemove = Math.min(item.getAmount(), amount - removed);
                item.setAmount(item.getAmount() - toRemove);
                if (item.getAmount() <= 0) {
                    inv.setItem(i, null);
                }
                removed += toRemove;
            }
        }
        return removed;
    }

    /**
     * Removes items from the inventory similar to the given item
     * @param inv The inventory
     * @param item The item to remove
     * @param amount The amount to remove
     * @return The amount actually removed
     */
    public static int remove(@Nonnull Inventory inv, @Nonnull ItemStack item, int amount) {
        return remove(inv, stack -> stack.isSimilar(item), amount);
    }

    /**
     * Checks if the inventory contains the specified amount of items matching the predicate
     * @param inv The inventory
     * @param predicate The predicate to match items
     * @param amount The amount to check for
     * @return True if the inventory contains at least the specified amount
     */
    public static boolean contains(@Nonnull Inventory inv, @Nonnull Predicate<ItemStack> predicate, int amount) {
        return count(inv, predicate) >= amount;
    }

    /**
     * Checks if the inventory contains the specified amount of items similar to the given item
     * @param inv The inventory
     * @param item The item to check for
     * @param amount The amount to check for
     * @return True if the inventory contains at least the specified amount
     */
    public static boolean contains(@Nonnull Inventory inv, @Nonnull ItemStack item, int amount) {
        return contains(inv, stack -> stack.isSimilar(item), amount);
    }

    /**
     * Checks if an item fits in the inventory (can be added without overflow)
     * @param inv The inventory
     * @param item The item to check
     * @return True if the item can fit in the inventory
     */
    public static boolean fits(@Nonnull Inventory inv, @Nonnull ItemStack item) {
        if (item == null || item.getType().isAir() || item.getAmount() <= 0) {
            return true;
        }
        
        ItemStack clone = item.clone();
        
        // First pass: try to add to existing stacks
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slot = inv.getItem(i);
            if (slot != null && !slot.getType().isAir() && slot.isSimilar(clone)) {
                int maxStack = Math.min(slot.getMaxStackSize(), inv.getMaxStackSize());
                int canAdd = maxStack - slot.getAmount();
                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, clone.getAmount());
                    clone.setAmount(clone.getAmount() - toAdd);
                    if (clone.getAmount() <= 0) {
                        return true;
                    }
                }
            }
        }

        // Second pass: try to add to empty slots
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slot = inv.getItem(i);
            if (slot == null || slot.getType().isAir()) {
                int maxStack = Math.min(clone.getMaxStackSize(), inv.getMaxStackSize());
                int toAdd = Math.min(maxStack, clone.getAmount());
                clone.setAmount(clone.getAmount() - toAdd);
                if (clone.getAmount() <= 0) {
                    return true;
                }
            }
        }

        return clone.getAmount() <= 0;
    }

    /**
     * Checks if an item fits in the specified slots of the inventory
     * @param inv The inventory
     * @param item The item to check
     * @param slots The slots to check
     * @return True if the item can fit in the specified slots
     */
    public static boolean fits(@Nonnull Inventory inv, @Nonnull ItemStack item, @Nonnull int[] slots) {
        if (item == null || item.getType().isAir() || item.getAmount() <= 0) {
            return true;
        }
        
        ItemStack clone = item.clone();
        
        // Try to fit in the specified slots
        for (int slot : slots) {
            if (slot >= 0 && slot < inv.getSize()) {
                ItemStack slotItem = inv.getItem(slot);
                
                if (slotItem == null || slotItem.getType().isAir()) {
                    // Empty slot - can place the item here
                    int maxStack = Math.min(clone.getMaxStackSize(), inv.getMaxStackSize());
                    int toPlace = Math.min(maxStack, clone.getAmount());
                    clone.setAmount(clone.getAmount() - toPlace);
                    
                    if (clone.getAmount() <= 0) {
                        return true;
                    }
                } else if (slotItem.isSimilar(clone)) {
                    // Similar item - can stack
                    int maxStack = Math.min(slotItem.getMaxStackSize(), inv.getMaxStackSize());
                    int canAdd = maxStack - slotItem.getAmount();
                    if (canAdd > 0) {
                        int toAdd = Math.min(canAdd, clone.getAmount());
                        clone.setAmount(clone.getAmount() - toAdd);
                        
                        if (clone.getAmount() <= 0) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return clone.getAmount() <= 0;
    }

    /**
     * Checks if an item is allowed in a specific inventory type
     * @param material The material to check
     * @param inventoryType The inventory type
     * @return True if the item is allowed in the inventory type
     */
    public static boolean isItemAllowed(@Nonnull Material material, @Nonnull InventoryType inventoryType) {
        // Handle special inventory types that have restrictions
        switch (inventoryType) {
            case FURNACE:
            case BLAST_FURNACE:
            case SMOKER:
                // Only allow items that can be smelted or fuel
                return material.isFuel() || material.isEdible() || 
                       material == Material.COAL || material == Material.CHARCOAL ||
                       material.name().contains("ORE") || material.name().contains("RAW_");
                       
            case BREWING:
                // Only allow brewing ingredients and bottles
                return material == Material.POTION || material == Material.SPLASH_POTION ||
                       material == Material.LINGERING_POTION || material == Material.GLASS_BOTTLE ||
                       material == Material.NETHER_WART || material == Material.BLAZE_POWDER ||
                       material == Material.SPIDER_EYE || material == Material.FERMENTED_SPIDER_EYE ||
                       material == Material.MAGMA_CREAM || material == Material.SUGAR ||
                       material == Material.GLISTERING_MELON_SLICE || material == Material.GOLDEN_CARROT ||
                       material == Material.RABBIT_FOOT || material == Material.TURTLE_HELMET ||
                       material == Material.PHANTOM_MEMBRANE || material == Material.DRAGON_BREATH;
                       
            case BEACON:
                // Only allow payment items (iron, gold, emerald, diamond, netherite)
                return material == Material.IRON_INGOT || material == Material.GOLD_INGOT ||
                       material == Material.EMERALD || material == Material.DIAMOND ||
                       material == Material.NETHERITE_INGOT;
                       
            case ANVIL:
                // Allow most items except air and some special blocks
                return !material.isAir() && material != Material.BEDROCK && 
                       material != Material.BARRIER && material != Material.STRUCTURE_VOID;
                       
            case ENCHANTING:
                // Allow enchantable items and lapis lazuli
                return material == Material.LAPIS_LAZULI || material.toString().contains("BOOK") ||
                       material.toString().contains("SWORD") || material.toString().contains("AXE") ||
                       material.toString().contains("PICKAXE") || material.toString().contains("SHOVEL") ||
                       material.toString().contains("HOE") || material.toString().contains("HELMET") ||
                       material.toString().contains("CHESTPLATE") || material.toString().contains("LEGGINGS") ||
                       material.toString().contains("BOOTS") || material.toString().contains("BOW") ||
                       material == Material.CROSSBOW || material == Material.TRIDENT ||
                       material == Material.FISHING_ROD || material == Material.SHEARS ||
                       material == Material.FLINT_AND_STEEL || material == Material.CARROT_ON_A_STICK ||
                       material == Material.WARPED_FUNGUS_ON_A_STICK || material == Material.ELYTRA;
                       
            default:
                // For most inventory types, allow all items except air
                return !material.isAir();
        }
    }

    /**
     * Checks if all items can fit in the specified slots of the inventory
     * @param inv The inventory
     * @param items The items to check
     * @param slots The slots to check
     * @return True if all items can fit in the specified slots
     */
    public static boolean fitAll(@Nonnull Inventory inv, @Nonnull ItemStack[] items, @Nonnull int[] slots) {
        if (items == null || items.length == 0) {
            return true;
        }

        // Create a copy of the inventory to simulate the fitting
        Inventory tempInv = org.bukkit.Bukkit.createInventory(null, inv.getSize());
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null) {
                tempInv.setItem(i, item.clone());
            }
        }

        // Try to fit each item in the specified slots
        for (ItemStack item : items) {
            if (item == null || item.getType().isAir()) {
                continue;
            }

            ItemStack remaining = item.clone();
            boolean fitted = false;

            // Try to fit in the specified slots
            for (int slot : slots) {
                if (slot >= 0 && slot < tempInv.getSize()) {
                    ItemStack slotItem = tempInv.getItem(slot);
                    
                    if (slotItem == null || slotItem.getType().isAir()) {
                        // Empty slot - can place the item here
                        int maxStack = Math.min(remaining.getMaxStackSize(), tempInv.getMaxStackSize());
                        int toPlace = Math.min(maxStack, remaining.getAmount());
                        ItemStack newStack = remaining.clone();
                        newStack.setAmount(toPlace);
                        tempInv.setItem(slot, newStack);
                        remaining.setAmount(remaining.getAmount() - toPlace);
                        
                        if (remaining.getAmount() <= 0) {
                            fitted = true;
                            break;
                        }
                    } else if (slotItem.isSimilar(remaining)) {
                        // Similar item - can stack
                        int maxStack = Math.min(slotItem.getMaxStackSize(), tempInv.getMaxStackSize());
                        int canAdd = maxStack - slotItem.getAmount();
                        if (canAdd > 0) {
                            int toAdd = Math.min(canAdd, remaining.getAmount());
                            slotItem.setAmount(slotItem.getAmount() + toAdd);
                            remaining.setAmount(remaining.getAmount() - toAdd);
                            
                            if (remaining.getAmount() <= 0) {
                                fitted = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (!fitted) {
                return false;
            }
        }

        return true;
    }
}
