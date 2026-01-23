package eu.mrneznamy.slimefuncontinued.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * A snapshot of ItemMeta for comparison purposes
 * Replaces io.github.bakedlibs.dough.items.ItemMetaSnapshot
 */
public class ItemMetaSnapshot {

    private final String displayName;
    private final List<String> lore;
    private final Map<Enchantment, Integer> enchantments;
    private final Set<ItemFlag> itemFlags;
    private final boolean hasCustomModelData;
    private final int customModelData;
    private final boolean unbreakable;

    /**
     * Creates an ItemMetaSnapshot from ItemMeta
     * @param meta The ItemMeta to snapshot
     */
    public ItemMetaSnapshot(@Nullable ItemMeta meta) {
        if (meta == null) {
            this.displayName = null;
            this.lore = new ArrayList<>();
            this.enchantments = new HashMap<>();
            this.itemFlags = new HashSet<>();
            this.hasCustomModelData = false;
            this.customModelData = 0;
            this.unbreakable = false;
        } else {
            this.displayName = meta.hasDisplayName() ? meta.getDisplayName() : null;
            this.lore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
            this.enchantments = new HashMap<>(meta.getEnchants());
            this.itemFlags = new HashSet<>(meta.getItemFlags());
            this.hasCustomModelData = meta.hasCustomModelData();
            this.customModelData = hasCustomModelData ? meta.getCustomModelData() : 0;
            this.unbreakable = meta.isUnbreakable();
        }
    }

    /**
     * Creates an ItemMetaSnapshot from an ItemStack
     * @param item The ItemStack to snapshot
     */
    public ItemMetaSnapshot(@Nullable ItemStack item) {
        this(item != null ? item.getItemMeta() : null);
    }

    /**
     * Gets the display name
     * @return The display name, or null if none
     */
    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Checks if the item has a display name
     * @return True if has display name
     */
    public boolean hasDisplayName() {
        return displayName != null;
    }

    /**
     * Gets the lore
     * @return The lore list (copy)
     */
    @Nonnull
    public List<String> getLore() {
        return new ArrayList<>(lore);
    }

    /**
     * Checks if the item has lore
     * @return True if has lore
     */
    public boolean hasLore() {
        return !lore.isEmpty();
    }

    /**
     * Gets the enchantments
     * @return The enchantments map (copy)
     */
    @Nonnull
    public Map<Enchantment, Integer> getEnchantments() {
        return new HashMap<>(enchantments);
    }

    /**
     * Checks if the item has enchantments
     * @return True if has enchantments
     */
    public boolean hasEnchantments() {
        return !enchantments.isEmpty();
    }

    /**
     * Gets the item flags
     * @return The item flags set (copy)
     */
    @Nonnull
    public Set<ItemFlag> getItemFlags() {
        return new HashSet<>(itemFlags);
    }

    /**
     * Checks if the item has item flags
     * @return True if has item flags
     */
    public boolean hasItemFlags() {
        return !itemFlags.isEmpty();
    }

    /**
     * Checks if the item has custom model data
     * @return True if has custom model data
     */
    public boolean hasCustomModelData() {
        return hasCustomModelData;
    }

    /**
     * Gets the custom model data
     * @return The custom model data
     */
    public int getCustomModelData() {
        return customModelData;
    }

    /**
     * Checks if the item is unbreakable
     * @return True if unbreakable
     */
    public boolean isUnbreakable() {
        return unbreakable;
    }

    /**
     * Compares this snapshot with another ItemMeta
     * @param meta The ItemMeta to compare with
     * @return True if they match
     */
    public boolean matches(@Nullable ItemMeta meta) {
        return equals(new ItemMetaSnapshot(meta));
    }

    /**
     * Compares this snapshot with an ItemStack's meta
     * @param item The ItemStack to compare with
     * @return True if they match
     */
    public boolean matches(@Nullable ItemStack item) {
        return equals(new ItemMetaSnapshot(item));
    }

    /**
     * Creates a snapshot from an ItemStack
     * @param item The ItemStack
     * @return The snapshot
     */
    @Nonnull
    public static ItemMetaSnapshot of(@Nullable ItemStack item) {
        return new ItemMetaSnapshot(item);
    }

    /**
     * Creates a snapshot from ItemMeta
     * @param meta The ItemMeta
     * @return The snapshot
     */
    @Nonnull
    public static ItemMetaSnapshot of(@Nullable ItemMeta meta) {
        return new ItemMetaSnapshot(meta);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ItemMetaSnapshot that = (ItemMetaSnapshot) obj;
        return hasCustomModelData == that.hasCustomModelData &&
               customModelData == that.customModelData &&
               unbreakable == that.unbreakable &&
               Objects.equals(displayName, that.displayName) &&
               lore.equals(that.lore) &&
               enchantments.equals(that.enchantments) &&
               itemFlags.equals(that.itemFlags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, lore, enchantments, itemFlags, 
                          hasCustomModelData, customModelData, unbreakable);
    }

    @Override
    public String toString() {
        return "ItemMetaSnapshot{" +
               "displayName='" + displayName + '\'' +
               ", lore=" + lore.size() + " lines" +
               ", enchantments=" + enchantments.size() +
               ", itemFlags=" + itemFlags.size() +
               ", hasCustomModelData=" + hasCustomModelData +
               ", customModelData=" + customModelData +
               ", unbreakable=" + unbreakable +
               '}';
    }
}
