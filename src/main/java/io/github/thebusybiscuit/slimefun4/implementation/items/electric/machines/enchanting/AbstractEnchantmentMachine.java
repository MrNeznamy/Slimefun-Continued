package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.enchanting;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.mrneznamy.utils.ColorSystem;
import eu.mrneznamy.slimefuncontinued.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

/**
 *
 * @author TheBusyBiscuit
 * @author Rothes
 * @author J3fftw1
 *
 * @see AutoEnchanter
 * @see AutoDisenchanter
 *
 */
abstract class AbstractEnchantmentMachine extends AContainer {

    private final ItemSetting<Boolean> useLevelLimit = new ItemSetting<>(this, "use-enchant-level-limit", false);
    private final IntRangeSetting levelLimit = new IntRangeSetting(this, "enchant-level-limit", 0, 10, Short.MAX_VALUE);
    private final ItemSetting<Integer> maxEnchants = new IntRangeSetting(this, "max-enchants", 0, 10, Short.MAX_VALUE);
    private final ItemSetting<Boolean> useMaxEnchants= new ItemSetting<>(this, "use-max-enchants", false);
    private final ItemSetting<Boolean> useIgnoredLores = new ItemSetting<>(this, "use-ignored-lores", false);
    private final ItemSetting<List<String>> ignoredLores = new ItemSetting<>(this, "ignored-lores", Collections.singletonList("&7- &cCan't be used in " + this.getItemName()));

    @ParametersAreNonnullByDefault
    protected AbstractEnchantmentMachine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(useLevelLimit);
        addItemSetting(levelLimit);
        addItemSetting(useIgnoredLores);
        addItemSetting(ignoredLores);
        addItemSetting(maxEnchants);
        addItemSetting(useMaxEnchants);
    }

    protected boolean isEnchantmentLevelAllowed(int enchantmentLevel) {
        return !useLevelLimit.getValue() || levelLimit.getValue() >= enchantmentLevel;
    }

    protected void showEnchantmentLevelWarning(@Nonnull BlockMenu menu) {
        if (!useLevelLimit.getValue()) {
            throw new IllegalStateException("Enchantment level limit not enabled, cannot display a warning.");
        }

        String notice = ColorSystem.colorize(Slimefun.getLocalization().getMessage("messages.above-limit-level"));
        notice = notice.replace("%level%", String.valueOf(levelLimit.getValue()));
        ItemStack progressBar = CustomItemStack.create(Material.BARRIER, " ", notice);
        menu.replaceExistingItem(22, progressBar);
    }

    protected boolean isIgnoredLore(@Nonnull Item item) {
        if (useIgnoredLores.getValue()) {
            ItemMeta meta = item.getItemStack().getItemMeta();

            if (meta != null && meta.hasLore()) {
                List<String> itemLore = meta.getLore();
                List<String> ignoredLore = ignoredLores.getValue();

                // Check if any of the lines are found on the item
                for (String lore : ignoredLore) {
                    if (itemLore.contains(ColorSystem.colorize(lore))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected boolean hasIgnoredLore(@Nonnull ItemStack item) {
        if (useIgnoredLores.getValue()) {
            ItemMeta meta = item.getItemMeta();

            if (meta != null && meta.hasLore()) {
                List<String> itemLore = meta.getLore();
                List<String> ignoredLore = ignoredLores.getValue();

                // Check if any of the lines are found on the item
                for (String lore : ignoredLore) {
                    if (itemLore.contains(ColorSystem.colorize(lore))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected boolean isEnchantmentAmountAllowed(int numberOfEnchants) {
        if (!useMaxEnchants.getValue()) {
            return true;
        }
        return numberOfEnchants <= maxEnchants.getValue();
    }
}
