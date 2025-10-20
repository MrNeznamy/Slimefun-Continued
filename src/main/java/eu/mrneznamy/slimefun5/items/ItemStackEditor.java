package eu.mrneznamy.slimefun5.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Utility for editing ItemStacks in a fluent manner
 * Replaces io.github.bakedlibs.dough.items.ItemStackEditor
 */
public class ItemStackEditor {

    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * Creates an ItemStackEditor for an existing ItemStack
     * @param item The ItemStack to edit
     */
    public ItemStackEditor(@Nonnull ItemStack item) {
        this.item = item.clone();
        this.meta = this.item.getItemMeta();
        if (this.meta == null) {
            throw new IllegalArgumentException("ItemStack must have ItemMeta");
        }
    }

    /**
     * Creates an ItemStackEditor for a new ItemStack
     * @param material The material for the new ItemStack
     */
    public ItemStackEditor(@Nonnull Material material) {
        this(new ItemStack(material));
    }

    /**
     * Creates an ItemStackEditor for a new ItemStack with amount
     * @param material The material for the new ItemStack
     * @param amount The amount
     */
    public ItemStackEditor(@Nonnull Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Sets the display name
     * @param name The display name
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor setDisplayName(@Nullable String name) {
        meta.setDisplayName(name);
        return this;
    }

    /**
     * Sets the lore
     * @param lore The lore lines
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor setLore(@Nullable List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    /**
     * Sets the lore from varargs
     * @param lore The lore lines
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor setLore(@Nullable String... lore) {
        return setLore(lore != null ? Arrays.asList(lore) : null);
    }

    /**
     * Adds lore lines
     * @param lines The lines to add
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor addLore(@Nonnull String... lines) {
        List<String> currentLore = meta.hasLore() ? meta.getLore() : new java.util.ArrayList<>();
        currentLore.addAll(Arrays.asList(lines));
        meta.setLore(currentLore);
        return this;
    }

    /**
     * Sets the amount
     * @param amount The amount
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Adds an enchantment
     * @param enchantment The enchantment
     * @param level The level
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor addEnchantment(@Nonnull Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Removes an enchantment
     * @param enchantment The enchantment to remove
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor removeEnchantment(@Nonnull Enchantment enchantment) {
        meta.removeEnchant(enchantment);
        return this;
    }

    /**
     * Adds item flags
     * @param flags The flags to add
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor addItemFlags(@Nonnull ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    /**
     * Removes item flags
     * @param flags The flags to remove
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor removeItemFlags(@Nonnull ItemFlag... flags) {
        meta.removeItemFlags(flags);
        return this;
    }

    /**
     * Sets the custom model data
     * @param data The custom model data
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor setCustomModelData(int data) {
        meta.setCustomModelData(data);
        return this;
    }

    /**
     * Removes the custom model data
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor removeCustomModelData() {
        meta.setCustomModelData(null);
        return this;
    }

    /**
     * Sets the unbreakable flag
     * @param unbreakable Whether the item should be unbreakable
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Applies a custom function to the ItemMeta
     * @param function The function to apply
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor apply(@Nonnull Consumer<ItemMeta> function) {
        function.accept(meta);
        return this;
    }

    /**
     * Applies a custom function to the ItemStack
     * @param function The function to apply
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor applyToItem(@Nonnull Consumer<ItemStack> function) {
        // Apply current meta first
        item.setItemMeta(meta);
        function.accept(item);
        // Update meta reference in case it changed
        ItemMeta newMeta = item.getItemMeta();
        if (newMeta != null) {
            // Copy the new meta back to our working meta
            meta.setDisplayName(newMeta.hasDisplayName() ? newMeta.getDisplayName() : null);
            meta.setLore(newMeta.hasLore() ? newMeta.getLore() : null);
            meta.setUnbreakable(newMeta.isUnbreakable());
            if (newMeta.hasCustomModelData()) {
                meta.setCustomModelData(newMeta.getCustomModelData());
            }
            // Copy enchantments
            meta.getEnchants().clear();
            meta.getEnchants().putAll(newMeta.getEnchants());
            // Copy item flags
            for (ItemFlag flag : ItemFlag.values()) {
                if (newMeta.hasItemFlag(flag)) {
                    meta.addItemFlags(flag);
                } else {
                    meta.removeItemFlags(flag);
                }
            }
        }
        return this;
    }

    /**
     * Builds the final ItemStack
     * @return The edited ItemStack
     */
    @Nonnull
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Gets the current ItemStack (applies meta first)
     * @return The current ItemStack
     */
    @Nonnull
    public ItemStack getItem() {
        return build();
    }

    /**
     * Gets the current ItemMeta
     * @return The current ItemMeta
     */
    @Nonnull
    public ItemMeta getMeta() {
        return meta;
    }

    /**
     * Alias for addItemFlags for compatibility
     * @param flags The flags to add
     * @return This editor for chaining
     */
    @Nonnull
    public ItemStackEditor addFlags(@Nonnull ItemFlag... flags) {
        return addItemFlags(flags);
    }

    /**
     * Applies a consumer to a specific meta type
     * @param metaClass The meta class type
     * @param consumer The consumer to apply
     * @param <T> The meta type
     * @return This editor for chaining
     */
    @Nonnull
    public <T extends ItemMeta> ItemStackEditor andMetaConsumer(@Nonnull Class<T> metaClass, @Nonnull Consumer<T> consumer) {
        if (metaClass.isInstance(meta)) {
            consumer.accept(metaClass.cast(meta));
        }
        return this;
    }

    /**
     * Alias for build() for compatibility
     * @return The edited ItemStack
     */
    @Nonnull
    public ItemStack create() {
        return build();
    }

    /**
     * Creates a new ItemStackEditor from an ItemStack
     * @param item The ItemStack
     * @return A new ItemStackEditor
     */
    @Nonnull
    public static ItemStackEditor of(@Nonnull ItemStack item) {
        return new ItemStackEditor(item);
    }

    /**
     * Creates a new ItemStackEditor from a Material
     * @param material The Material
     * @return A new ItemStackEditor
     */
    @Nonnull
    public static ItemStackEditor of(@Nonnull Material material) {
        return new ItemStackEditor(material);
    }

    /**
     * Creates a new ItemStackEditor from a Material with amount
     * @param material The Material
     * @param amount The amount
     * @return A new ItemStackEditor
     */
    @Nonnull
    public static ItemStackEditor of(@Nonnull Material material, int amount) {
        return new ItemStackEditor(material, amount);
    }
}
