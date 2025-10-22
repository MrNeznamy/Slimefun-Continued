package io.github.thebusybiscuit.slimefun4.core.guide.options;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import eu.mrneznamy.slimefun5.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import eu.mrneznamy.utils.ColorSystem;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

/**
 * This menu allows players to choose between Slimefun 4 and Slimefun 5 contributors.
 *
 * @author TheBusyBiscuit
 *
 */
final class ContributorsSelectionMenu {

    private ContributorsSelectionMenu() {}

    public static void open(Player p, ItemStack guide) {
        ChestMenu menu = new ChestMenu(Slimefun.getLocalization().getMessage(p, "guide.title.credits"));

        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(SoundEffect.GUIDE_CONTRIBUTORS_OPEN_SOUND::playFor);

        ChestMenuUtils.drawBackground(menu, 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26);

        // Back button
        menu.addItem(4, ChestMenuUtils.getBackButton(p, "", "&7" + Slimefun.getLocalization().getMessage(p, "guide.back.settings")));
        menu.addMenuClickHandler(4, (pl, slot, item, action) -> {
            SlimefunGuideSettings.openSettings(pl, guide);
            return false;
        });

        // Slimefun 4 Contributors
        menu.addItem(13, CustomItemStack.create(SlimefunUtils.getCustomHead("e952d2b3f351a6b0487cc59db31bf5f2641133e5ba0006b18576e996a0293e52"),
            ColorSystem.colorize("&a" + Slimefun.getLocalization().getMessage(p, "guide.menu.contributors.slimefun4.title")),
            "",
            ColorSystem.colorize("&7" + Slimefun.getLocalization().getMessage(p, "guide.menu.contributors.slimefun4.description")),
            "",
            ColorSystem.colorize("&7\u21E8 &e" + Slimefun.getLocalization().getMessage(p, "guide.open-itemgroup"))));

        menu.addMenuClickHandler(13, (pl, slot, item, action) -> {
            ContributorsMenu.openSF4Contributors(pl, 0);
            return false;
        });

        // Slimefun 5 Contributors  
        menu.addItem(22, CustomItemStack.create(SlimefunUtils.getCustomHead("d78f2b7e5e75639ea7fb796c35d364c4df28b4243e66b76277aadcd6261337"),
            ColorSystem.colorize("{#926CDE}&l" + Slimefun.getLocalization().getMessage(p, "guide.menu.contributors.slimefun5.title")),
            "",
            ColorSystem.colorize("&8" + Slimefun.getLocalization().getMessage(p, "guide.menu.contributors.slimefun5.description")),
            "",
            ColorSystem.colorize("&7\u21E8 {#E982D9}" + Slimefun.getLocalization().getMessage(p, "guide.open-itemgroup"))));

        menu.addMenuClickHandler(22, (pl, slot, item, action) -> {
            ContributorsMenu.open(pl, 0);
            return false;
        });

        menu.open(p);
    }
}