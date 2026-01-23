package io.github.thebusybiscuit.slimefun4.api.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.mrneznamy.slimefuncontinued.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.groups.LockedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SeasonalItemGroup;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.compatibility.VersionedItemFlag;

/**
 * Represents an item group, which structure
 * multiple {@link SlimefunItem} in the {@link SlimefunGuide}.
 *
 * @author TheBusyBiscuit
 *
 * @see LockedItemGroup
 * @see SeasonalItemGroup
 *
 */
public class ItemGroup implements Keyed {

    private SlimefunAddon addon;

    protected final List<SlimefunItem> items = new ArrayList<>();
    protected final NamespacedKey key;
    protected final ItemStack item;
    protected int tier;
    protected boolean crossAddonItemGroup = false;
    protected String customDescription = null;
    protected boolean rawMode = false;

    /**
     * Constructs a new {@link ItemGroup} with the given {@link NamespacedKey} as an identifier
     * and the given {@link ItemStack} as its display item.
     * The tier is set to a default value of {@code 3}.
     *
     * @param key
     *            The {@link NamespacedKey} that is used to identify this {@link ItemGroup}
     * @param item
     *            The {@link ItemStack} that is used to display this {@link ItemGroup}
     */
    @ParametersAreNonnullByDefault
    public ItemGroup(NamespacedKey key, ItemStack item) {
        this(key, item, 3);
    }

    /**
     * Constructs a new {@link ItemGroup} with the given {@link NamespacedKey} as an identifier
     * and the given {@link ItemStack} as its display item.
     *
     * @param key
     *            The {@link NamespacedKey} that is used to identify this {@link ItemGroup}
     * @param item
     *            The {@link ItemStack} that is used to display this {@link ItemGroup}
     * @param tier
     *            The tier of this {@link ItemGroup}, higher tiers will make this {@link ItemGroup} appear further down
     *            in
     *            the {@link SlimefunGuide}
     */
    @ParametersAreNonnullByDefault
    public ItemGroup(NamespacedKey key, ItemStack item, int tier) {
        Validate.notNull(key, "An item group's NamespacedKey must not be null!");
        Validate.notNull(item, "An item group's ItemStack must not be null!");

        this.item = item;
        this.key = key;

        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        if (VersionedItemFlag.HIDE_ADDITIONAL_TOOLTIP != null) {
            meta.addItemFlags(VersionedItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        }
        this.item.setItemMeta(meta);
        this.tier = tier;
    }

    /**
     * Constructs a new {@link ItemGroup} with the given {@link NamespacedKey} as an identifier,
     * the given {@link ItemStack} as its display item, and a custom description.
     *
     * @param key
     *            The {@link NamespacedKey} that is used to identify this {@link ItemGroup}
     * @param item
     *            The {@link ItemStack} that is used to display this {@link ItemGroup}
     * @param tier
     *            The tier of this {@link ItemGroup}, higher tiers will make this {@link ItemGroup} appear further down
     *            in the {@link SlimefunGuide}
     * @param customDescription
     *            A custom description for this {@link ItemGroup} that overrides localization
     */
    @ParametersAreNonnullByDefault
    public ItemGroup(NamespacedKey key, ItemStack item, int tier, String customDescription) {
        this(key, item, tier);
        this.customDescription = customDescription;
    }

    /**
     * Constructs a new "raw" {@link ItemGroup} with the given {@link NamespacedKey} as an identifier
     * and the given {@link ItemStack} as its display item. In raw mode, no automatic color schemes,
     * lore formatting, or localization is applied - the developer has full control over the appearance.
     *
     * @param key
     *            The {@link NamespacedKey} that is used to identify this {@link ItemGroup}
     * @param item
     *            The {@link ItemStack} that is used to display this {@link ItemGroup} (used as-is without modifications)
     * @param tier
     *            The tier of this {@link ItemGroup}, higher tiers will make this {@link ItemGroup} appear further down
     *            in the {@link SlimefunGuide}
     * @param rawMode
     *            Set to true to enable raw mode (no automatic formatting), false for standard behavior
     */
    @ParametersAreNonnullByDefault
    public ItemGroup(NamespacedKey key, ItemStack item, int tier, boolean rawMode) {
        Validate.notNull(key, "An item group's NamespacedKey must not be null!");
        Validate.notNull(item, "An item group's ItemStack must not be null!");

        this.item = item.clone(); // Clone to avoid modifying the original
        this.key = key;
        this.tier = tier;
        this.rawMode = rawMode;

        if (!rawMode) {
            // Apply standard formatting only if not in raw mode
            ItemMeta meta = this.item.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            if (VersionedItemFlag.HIDE_ADDITIONAL_TOOLTIP != null) {
                meta.addItemFlags(VersionedItemFlag.HIDE_ADDITIONAL_TOOLTIP);
            }
            this.item.setItemMeta(meta);
        }
    }

    /**
     * Constructs a new "raw" {@link ItemGroup} with the given {@link NamespacedKey} as an identifier,
     * the given {@link ItemStack} as its display item, and a custom description. In raw mode, no automatic 
     * color schemes, lore formatting, or localization is applied - the developer has full control over the appearance.
     *
     * @param key
     *            The {@link NamespacedKey} that is used to identify this {@link ItemGroup}
     * @param item
     *            The {@link ItemStack} that is used to display this {@link ItemGroup} (used as-is without modifications)
     * @param tier
     *            The tier of this {@link ItemGroup}, higher tiers will make this {@link ItemGroup} appear further down
     *            in the {@link SlimefunGuide}
     * @param customDescription
     *            A custom description for this {@link ItemGroup} that overrides localization
     * @param rawMode
     *            Set to true to enable raw mode (no automatic formatting), false for standard behavior
     */
    @ParametersAreNonnullByDefault
    public ItemGroup(NamespacedKey key, ItemStack item, int tier, String customDescription, boolean rawMode) {
        this(key, item, tier, rawMode);
        this.customDescription = customDescription;
    }

    @Override
    public final @Nonnull NamespacedKey getKey() {
        return key;
    }

    /**
     * Registers this {@link ItemGroup}.
     * <p>
     * By default, an {@link ItemGroup} is automatically registered when
     * a {@link SlimefunItem} was added to it.
     *
     * @param addon
     *            The {@link SlimefunAddon} that wants to register this {@link ItemGroup}
     */
    public void register(@Nonnull SlimefunAddon addon) {
        Validate.notNull(addon, "The Addon cannot be null");

        if (isRegistered()) {
            throw new UnsupportedOperationException("This ItemGroup has already been registered!");
        }

        this.addon = addon;

        Slimefun.getRegistry().getAllItemGroups().add(this);
        sortCategoriesByTier();
    }

    /**
     * This method returns whether this {@link ItemGroup} has been registered yet.
     * More specifically: Whether {@link #register(SlimefunAddon)} was called or not.
     *
     * @return Whether this {@link ItemGroup} has been registered
     */
    public boolean isRegistered() {
        return this.addon != null && Slimefun.getRegistry().getAllItemGroups().contains(this);
    }

    /**
     * Returns the tier of this {@link ItemGroup}.
     * The tier determines the position of this {@link ItemGroup} in the {@link SlimefunGuide}.
     *
     * @return the tier of this {@link ItemGroup}
     */
    public int getTier() {
        return tier;
    }

    /**
     * This sets the tier of this {@link ItemGroup}.
     * The tier determines the position of this {@link ItemGroup} in the {@link SlimefunGuide}.
     *
     * @param tier
     *            The tier for this {@link ItemGroup}
     */
    public void setTier(int tier) {
        this.tier = tier;

        // Refresh ItemGroup order if already registered.
        if (isRegistered()) {
            sortCategoriesByTier();
        }
    }

    /**
     * Sets a custom description for this {@link ItemGroup}.
     * This description will override the localized description from language files.
     *
     * @param description
     *            The custom description for this {@link ItemGroup}
     */
    public void setDescription(@Nullable String description) {
        this.customDescription = description;
    }

    /**
     * Gets the description for this {@link ItemGroup}.
     * Returns the custom description if set, otherwise falls back to localization.
     *
     * @param player
     *            The {@link Player} for localization context
     * @return The description for this {@link ItemGroup}
     */
    public @Nullable String getDescription(@Nonnull Player player) {
        if (customDescription != null) {
            return customDescription;
        }
        
        // Fallback to localization
        String categoryKey = getKey().getKey();
        String descriptionKey = "guide.categories.descriptions." + categoryKey;
        return Slimefun.getLocalization().getMessage(player, descriptionKey);
    }

    /**
     * This refreshes the {@link ItemGroup} order.
     */
    private void sortCategoriesByTier() {
        List<ItemGroup> categories = Slimefun.getRegistry().getAllItemGroups();
        Collections.sort(categories, Comparator.comparingInt(ItemGroup::getTier));
    }

    /**
     * This returns the {@link SlimefunAddon} which has registered this {@link ItemGroup}.
     * Or null if it has not been registered yet.
     *
     * @return The {@link SlimefunAddon} or null if unregistered
     */
    public final @Nullable SlimefunAddon getAddon() {
        return addon;
    }

    /**
     * Adds the given {@link SlimefunItem} to this {@link ItemGroup}.
     *
     * @param item
     *            the {@link SlimefunItem} that should be added to this {@link ItemGroup}
     */
    public void add(@Nonnull SlimefunItem item) {
        Validate.notNull(item, "Cannot add null Items to an ItemGroup!");

        if (items.contains(item)) {
            // Ignore duplicate entries
            return;
        }

        if (isRegistered() && !isCrossAddonItemGroup() && !item.getAddon().getName().equals(this.addon.getName())) {
            item.warn("This item does not belong into ItemGroup " + this + " as that group belongs to " + this.addon.getName());
        }

        items.add(item);
    }

    /**
     * Removes the given {@link SlimefunItem} from this {@link ItemGroup}.
     *
     * @param item
     *            the {@link SlimefunItem} that should be removed from this {@link ItemGroup}
     */
    public void remove(@Nonnull SlimefunItem item) {
        Validate.notNull(item, "Cannot remove null from an ItemGroup!");
        items.remove(item);
    }

    /**
     * This method returns a localized display item of this {@link ItemGroup}
     * for the specified {@link Player}.
     *
     * @param p
     *            The Player to create this {@link ItemStack} for
     *
     * @return A localized display item for this {@link ItemGroup}
     */
    public @Nonnull ItemStack getItem(@Nonnull Player p) {
        // In raw mode, return the item as-is without any automatic formatting
        if (rawMode) {
            return item.clone();
        }

        return CustomItemStack.create(item, meta -> {
            String name = Slimefun.getLocalization().getItemGroupName(p, getKey());

            if (name == null) {
                name = item.getItemMeta().getDisplayName();
            }

            // Determine color scheme based on category type
            String[] colors = getColorScheme();
            String primaryColor = colors[0];
            String secondaryColor = colors[1];

            // Set category name with appropriate color scheme
            meta.setDisplayName(ColorSystem.colorize(primaryColor) + name);

            // Create enhanced lore with INFORMATIONS and DESCRIPTION sections
            List<String> lore = new ArrayList<>();
            
            // Add empty line after category name
            lore.add(ColorSystem.colorize("&f"));
            
            // INFORMATIONS section header
            String informationsHeader = Slimefun.getLocalization().getMessage(p, "guide.categories.informations");
            if (informationsHeader != null) {
                String coloredHeader = informationsHeader.replace("{PRIMARY_COLOR}", primaryColor)
                                                        .replace("{SECONDARY_COLOR}", secondaryColor);
                lore.add(ColorSystem.colorize(coloredHeader));
            }
            
            // Items count
            int itemCount = items.size();
            String itemCountText = Slimefun.getLocalization().getMessage(p, "guide.categories.items-count");
            if (itemCountText != null) {
                String coloredItemCount = itemCountText.replace("{PRIMARY_COLOR}", primaryColor)
                                                      .replace("{SECONDARY_COLOR}", secondaryColor)
                                                      .replace("{0}", String.valueOf(itemCount));
                lore.add(ColorSystem.colorize(coloredItemCount));
            }
            
            lore.add(ColorSystem.colorize("&f")); // Empty white line
            
            // DESCRIPTION section header
            String descriptionHeader = Slimefun.getLocalization().getMessage(p, "guide.categories.description-header");
            if (descriptionHeader != null) {
                String coloredDescHeader = descriptionHeader.replace("{PRIMARY_COLOR}", primaryColor)
                                                           .replace("{SECONDARY_COLOR}", secondaryColor);
                lore.add(ColorSystem.colorize(coloredDescHeader));
            }
            
            // Category description - use custom description if available, otherwise fallback to localization
            String description = getDescription(p);
            if (description != null) {
                String descriptionText = Slimefun.getLocalization().getMessage(p, "guide.categories.description-text");
                if (descriptionText != null) {
                    String coloredDescText = descriptionText.replace("{PRIMARY_COLOR}", primaryColor)
                                                           .replace("{SECONDARY_COLOR}", secondaryColor)
                                                           .replace("{0}", description);
                    lore.add(ColorSystem.colorize(coloredDescText));
                }
            }
            
            lore.add(ColorSystem.colorize("&f")); // Empty white line
            
            // Click instruction
            String clickInstruction = Slimefun.getLocalization().getMessage(p, "guide.categories.click-instruction");
            if (clickInstruction != null) {
                String coloredClickInstruction = clickInstruction.replace("{PRIMARY_COLOR}", primaryColor)
                                                                .replace("{SECONDARY_COLOR}", secondaryColor);
                lore.add(ColorSystem.colorize(coloredClickInstruction));
            }

            meta.setLore(lore);
        });
    }

    /**
     * Gets the color scheme for this ItemGroup based on its type.
     * Returns an array with [primaryColor, secondaryColor].
     * Colors are loaded from localization files with fallback to defaults.
     *
     * @return Color scheme array
     */
    private @Nonnull String[] getColorScheme() {
        String categoryKey = getKey().getKey();
        
        // Try to load colors from localization
        String primaryColorKey = "guide.categories.colors." + categoryKey + ".primary";
        String secondaryColorKey = "guide.categories.colors." + categoryKey + ".secondary";
        
        String primaryColor = Slimefun.getLocalization().getMessage(primaryColorKey);
        String secondaryColor = Slimefun.getLocalization().getMessage(secondaryColorKey);
        
        // If specific category colors not found, try default colors
        if (primaryColor == null || secondaryColor == null) {
            primaryColor = Slimefun.getLocalization().getMessage("guide.categories.colors.default.primary");
            secondaryColor = Slimefun.getLocalization().getMessage("guide.categories.colors.default.secondary");
        }
        
        // Final fallback to hardcoded defaults if localization fails
        if (primaryColor == null) {
            primaryColor = "#926CDE";
        }
        if (secondaryColor == null) {
            secondaryColor = "#E982D9";
        }
        
        return new String[]{primaryColor, secondaryColor};
    }

    /**
     * This method makes Walshy happy.
     * It adds a way to get the name of a {@link ItemGroup} without localization nor coloring.
     *
     * @return The unlocalized name of this {@link ItemGroup}
     */
    public @Nonnull String getUnlocalizedName() {
        return ColorSystem.stripColor(item.getItemMeta().getDisplayName());
    }

    /**
     * This returns the localized display name of this {@link ItemGroup} for the given {@link Player}.
     * The method will fall back to {@link #getUnlocalizedName()} if no translation was found.
     *
     * @param p
     *            The {@link Player} who to translate the name for
     *
     * @return The localized name of this {@link ItemGroup}
     */
    public @Nonnull String getDisplayName(@Nonnull Player p) {
        String localized = Slimefun.getLocalization().getItemGroupName(p, getKey());

        if (localized != null) {
            return localized;
        } else {
            return getUnlocalizedName();
        }
    }

    /**
     * Returns all instances of {@link SlimefunItem} bound to this {@link ItemGroup}.
     *
     * @return the list of SlimefunItems bound to this {@link ItemGroup}
     */
    public @Nonnull List<SlimefunItem> getItems() {
        return items;
    }

    /**
     * This method returns whether a given {@link SlimefunItem} exists in this {@link ItemGroup}.
     *
     * @param item
     *            The {@link SlimefunItem} to find
     *
     * @return Whether the given {@link SlimefunItem} was found in this {@link ItemGroup}
     */
    public boolean contains(@Nullable SlimefunItem item) {
        return item != null && items.contains(item);
    }

    /**
     * This method returns whether this {@link ItemGroup} can be accessed
     * by the given {@link Player}. If an {@link ItemGroup} is not accessible,
     * it will not show up in the {@link SlimefunGuide} nor will items from this
     * {@link ItemGroup} show up in the guide search.
     *
     * @param p
     *            The {@link Player} to check for
     *
     * @return Whether this {@link ItemGroup} is accessible by the given {@link Player}
     */
    public boolean isAccessible(@Nonnull Player p) {
        return true;
    }

    /**
     * This method returns whether this {@link ItemGroup} can be viewed
     * by the given {@link Player}. Empty {@link ItemGroup ItemGroups} will not
     * be visible. This includes {@link ItemGroup ItemGroups} where every {@link SlimefunItem}
     * is disabled. If an {@link ItemGroup} is not accessible by the {@link Player},
     * see {@link #isAccessible(Player)}, this method will also return false.
     *
     * @param p
     *            The {@link Player} to check for
     *
     * @return Whether this {@link ItemGroup} is visible to the given {@link Player}
     */
    public boolean isVisible(@Nonnull Player p) {
        if (items.isEmpty() || !isAccessible(p)) {
            return false;
        }

        for (SlimefunItem slimefunItem : getItems()) {
            /*
             * If any item for this item group is visible,
             * the item group itself is also visible.
             * Empty item groups are not displayed.
             */
            if (!slimefunItem.isHidden() && !slimefunItem.isDisabledIn(p.getWorld())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns if items from other addons are allowed to be
     * added to this {@link ItemGroup}.
     *
     * @return true if items from other addons are allowed to be added to this {@link ItemGroup}.
     */
    public boolean isCrossAddonItemGroup() {
        return crossAddonItemGroup;
    }

    /**
     * This method will set if this {@link ItemGroup} will
     * allow {@link SlimefunItem}s from other addons to
     * be added, without a warning, into the group. False by default.
     * If set to true, Slimefun will not warn about items being added.
     *
     * @param crossAddonItemGroup
     *                          Whether items from another addon are allowable
     */
    public void setCrossAddonItemGroup(boolean crossAddonItemGroup) {
        this.crossAddonItemGroup = crossAddonItemGroup;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof ItemGroup group) {
            return group.getKey().equals(this.getKey());
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + key + ",tier=" + tier + "}";
    }

    /**
     * This method checks whether this {@link ItemGroup} will be hidden for the specified
     * {@link Player}.
     *
     * Categories are hidden if all of their items have been disabled.
     *
     * @param p
     *            The {@link Player} to check for
     *
     * @return Whether this {@link ItemGroup} will be hidden to the given {@link Player}
     */
    @Deprecated
    public boolean isHidden(@Nonnull Player p) {
        return !isVisible(p);
    }

    /**
     * Returns whether this {@link ItemGroup} is in raw mode.
     * Raw mode categories don't have automatic color schemes, lore formatting, 
     * or localization applied - the developer has full control over the appearance.
     *
     * @return true if this category is in raw mode, false otherwise
     */
    public boolean isRawMode() {
        return rawMode;
    }

}
