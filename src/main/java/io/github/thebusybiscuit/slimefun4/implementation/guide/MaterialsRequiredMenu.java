package io.github.thebusybiscuit.slimefun4.implementation.guide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.mrneznamy.utils.ColorSystem;
import eu.mrneznamy.slimefuncontinued.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideImplementation;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

/**
 * This class creates and manages the Materials Required menu
 * that displays all materials needed for a Slimefun item recipe
 * with color-coded availability status and quantity calculator.
 * 
 * @author TheBusyBiscuit
 * @since 4.0
 */
public class MaterialsRequiredMenu {

    private static final int ITEMS_PER_PAGE = 28;
    private static final int[] ITEM_SLOTS = {
        9, 10, 11, 12, 13, 14, 15, 16, 17,
        18, 19, 20, 21, 22, 23, 24, 25, 26,
        27, 28, 29, 30, 31, 32, 33, 34, 35,
        36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    
    // Quantity calculator slots
    private static final int SLOT_DECREASE_10 = 46;
    private static final int SLOT_DECREASE_1 = 47;
    private static final int SLOT_QUANTITY_DISPLAY = 49;
    private static final int SLOT_INCREASE_1 = 51;
    private static final int SLOT_INCREASE_10 = 52;

    private MaterialsRequiredMenu() {}

    /**
     * Opens the Materials Required menu for the specified player and item.
     * 
     * @param player The player to show the menu to
     * @param item The Slimefun item to calculate materials for
     * @param page The page number to display (0-based)
     */
    @ParametersAreNonnullByDefault
    public static void open(Player player, SlimefunItem item, int page) {
        open(player, item, page, 1);
    }
    
    /**
     * Opens the Materials Required menu for the specified player and item with quantity.
     * 
     * @param player The player to show the menu to
     * @param item The Slimefun item to calculate materials for
     * @param page The page number to display (0-based)
     * @param quantity The quantity of items to calculate materials for
     */
    @ParametersAreNonnullByDefault
    public static void open(Player player, SlimefunItem item, int page, int quantity) {
        // Ensure quantity is at least 1
        int finalQuantity = Math.max(1, quantity);
        
        // Calculate required materials
        MaterialRequirementCalculator calculator = new MaterialRequirementCalculator();
        Map<ItemStack, Integer> baseMaterials = calculator.calculateRequiredMaterials(item);
        
        // Multiply by quantity
        Map<ItemStack, Integer> requiredMaterials = new java.util.HashMap<>();
        for (Map.Entry<ItemStack, Integer> entry : baseMaterials.entrySet()) {
            requiredMaterials.put(entry.getKey(), entry.getValue() * finalQuantity);
        }
        
        // Check availability in player's inventory
        InventoryChecker checker = new InventoryChecker();
        Map<ItemStack, MaterialRequirement> materialRequirements = checker.checkAllMaterials(player, requiredMaterials);
        
        // Create the menu
        String title = Slimefun.getLocalization().getMessage(player, "guide.title.materials-required");
        if (title.equals("guide.title.materials-required")) {
            title = "Materials Required (" + finalQuantity + "x): " + item.getItemName();
        } else {
            title = title.replace("%item%", item.getItemName()).replace("%quantity%", String.valueOf(finalQuantity));
        }
        
        ChestMenu menu = new ChestMenu(title);
        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(SoundEffect.GUIDE_BUTTON_CLICK_SOUND::playFor);
        
        // Draw background (excluding quantity calculator slots)
        ChestMenuUtils.drawBackground(menu, 0, 1, 2, 3, 4, 5, 6, 7, 8, 48, 50);
        
        // Add back button
        menu.addItem(1, ChestMenuUtils.getBackButton(player, "", "&7Back to recipe"));
        menu.addMenuClickHandler(1, (pl, slot, itemStack, action) -> {
            // Return to the item's recipe display
            SlimefunGuideImplementation guide = Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE);
            PlayerProfile.find(pl).ifPresent(profile -> {
                guide.displayItem(profile, item, false);
            });
            return false;
        });
        
        // Add quantity calculator
        addQuantityCalculator(menu, player, item, page, finalQuantity);
        
        // Convert map to list for pagination
        List<Map.Entry<ItemStack, MaterialRequirement>> materialList = new ArrayList<>(materialRequirements.entrySet());
        
        // Calculate pagination
        int totalPages = (materialList.size() - 1) / ITEMS_PER_PAGE + 1;
        int startIndex = page * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, materialList.size());
        
        // Add material items to the menu
        for (int i = startIndex; i < endIndex; i++) {
            Map.Entry<ItemStack, MaterialRequirement> entry = materialList.get(i);
            ItemStack material = entry.getKey();
            MaterialRequirement requirement = entry.getValue();
            
            ItemStack displayItem = createMaterialDisplayItem(material, requirement);
            int slotIndex = i - startIndex;
            
            if (slotIndex < ITEM_SLOTS.length) {
                menu.addItem(ITEM_SLOTS[slotIndex], displayItem);
                menu.addMenuClickHandler(ITEM_SLOTS[slotIndex], (pl, slot, itemStack, action) -> {
                    // Check if this is a Slimefun item and show its recipe
                    SlimefunItem sfItem = SlimefunItem.getByItem(material);
                    if (sfItem != null) {
                        SlimefunGuideImplementation guide = Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE);
                        PlayerProfile.find(pl).ifPresent(profile -> {
                            guide.displayItem(profile, sfItem, false);
                        });
                    }
                    return false;
                });
            }
        }
        
        // Add pagination buttons
        if (totalPages > 1) {
            menu.addItem(45, ChestMenuUtils.getPreviousButton(player, page + 1, totalPages));
            menu.addMenuClickHandler(45, (pl, slot, itemStack, action) -> {
                if (page > 0) {
                    open(pl, item, page - 1, finalQuantity);
                }
                return false;
            });
            
            menu.addItem(53, ChestMenuUtils.getNextButton(player, page + 1, totalPages));
            menu.addMenuClickHandler(53, (pl, slot, itemStack, action) -> {
                if (page + 1 < totalPages) {
                    open(pl, item, page + 1, finalQuantity);
                }
                return false;
            });
        }
        
        // Add summary information
        addSummaryItem(menu, player, materialRequirements);
        
        menu.open(player);
    }
    
    /**
     * Creates a display item for a material with color-coded availability.
     * 
     * @param material The material item
     * @param requirement The material requirement information
     * @return The display item with appropriate coloring and lore
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    private static ItemStack createMaterialDisplayItem(ItemStack material, MaterialRequirement requirement) {
        ItemStack displayItem = material.clone();
        displayItem.setAmount(requirement.getRequired());
        
        ItemMeta meta = displayItem.getItemMeta();
        if (meta != null) {
            // Set display name with color coding
            String displayName = getColoredDisplayName(material, requirement);
            meta.setDisplayName(displayName);
            
            // Create lore with availability information
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ColorSystem.colorize("&7Required: &f" + requirement.getRequired()));
            lore.add(ColorSystem.colorize("&7Available: &f" + requirement.getAvailable()));
            
            if (requirement.isSufficient()) {
                lore.add(ColorSystem.colorize("&aâœ“ You have enough!"));
            } else if (requirement.isPartial()) {
                lore.add(ColorSystem.colorize("&6âš  Missing: &c" + requirement.getMissing()));
            } else {
                lore.add(ColorSystem.colorize("&câœ— You don't have any!"));
            }
            
            // Add click hint for Slimefun items
            SlimefunItem sfItem = SlimefunItem.getByItem(material);
            if (sfItem != null) {
                lore.add("");
                lore.add(ColorSystem.colorize("&eâ–¶ Click to view recipe"));
            }
            
            meta.setLore(lore);
            displayItem.setItemMeta(meta);
        }
        
        return displayItem;
    }
    
    /**
     * Gets the color-coded display name for a material based on availability.
     * 
     * @param material The material item
     * @param requirement The material requirement information
     * @return The colored display name
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    private static String getColoredDisplayName(ItemStack material, MaterialRequirement requirement) {
        String baseName;
        
        // Check if this is a Slimefun item first
        SlimefunItem sfItem = SlimefunItem.getByItem(material);
        if (sfItem != null) {
            baseName = sfItem.getItemName();
        } else if (material.getItemMeta() != null && material.getItemMeta().hasDisplayName()) {
            baseName = material.getItemMeta().getDisplayName();
        } else {
            baseName = material.getType().name().replace("_", " ").toLowerCase();
        }
        
        switch (requirement.getAvailability()) {
            case SUFFICIENT:
                return ColorSystem.colorize("&a" + baseName);
            case PARTIAL:
                return ColorSystem.colorize("&6" + baseName + " &7(" + requirement.getAvailable() + "/" + requirement.getRequired() + ")");
            case NONE:
                return ColorSystem.colorize("&c" + baseName);
            default:
                return baseName;
        }
    }
    
    /**
     * Adds a summary item showing overall material availability.
     * 
     * @param menu The menu to add the summary to
     * @param player The player viewing the menu
     * @param materialRequirements The map of material requirements
     */
    @ParametersAreNonnullByDefault
    private static void addSummaryItem(ChestMenu menu, Player player, Map<ItemStack, MaterialRequirement> materialRequirements) {
        int sufficient = 0;
        int partial = 0;
        int missing = 0;
        
        for (MaterialRequirement requirement : materialRequirements.values()) {
            switch (requirement.getAvailability()) {
                case SUFFICIENT:
                    sufficient++;
                    break;
                case PARTIAL:
                    partial++;
                    break;
                case NONE:
                    missing++;
                    break;
            }
        }
        
        Material summaryMaterial = sufficient == materialRequirements.size() ? Material.EMERALD : 
                                  missing == materialRequirements.size() ? Material.REDSTONE : Material.GOLD_INGOT;
        
        ItemStack summaryItem = CustomItemStack.create(summaryMaterial, "&eMaterial Summary", 
            "",
            "&7Total materials: &f" + materialRequirements.size(),
            "&aâœ“ Sufficient: &f" + sufficient,
            "&6âš  Partial: &f" + partial,
            "&câœ— Missing: &f" + missing,
            "",
            sufficient == materialRequirements.size() ? "&a&lYou can craft this item!" : "&c&lYou need more materials!"
        );
        
        menu.addItem(4, summaryItem);
        menu.addMenuClickHandler(4, ChestMenuUtils.getEmptyClickHandler());
    }
    
    /**
     * Adds quantity calculator controls to the menu.
     * 
     * @param menu The menu to add controls to
     * @param player The player viewing the menu
     * @param item The Slimefun item
     * @param page Current page
     * @param currentQuantity Current quantity
     */
    @ParametersAreNonnullByDefault
    private static void addQuantityCalculator(ChestMenu menu, Player player, SlimefunItem item, int page, int currentQuantity) {
        // -10 button
        menu.addItem(SLOT_DECREASE_10, CustomItemStack.create(Material.RED_CONCRETE, "&c-10", "", "&7Click to decrease by 10", "&7Current: &f" + currentQuantity));
        menu.addMenuClickHandler(SLOT_DECREASE_10, (pl, slot, itemStack, action) -> {
            int newQuantity = Math.max(1, currentQuantity - 10);
            open(pl, item, page, newQuantity);
            return false;
        });
        
        // -1 button
        menu.addItem(SLOT_DECREASE_1, CustomItemStack.create(Material.ORANGE_CONCRETE, "&6-1", "", "&7Click to decrease by 1", "&7Current: &f" + currentQuantity));
        menu.addMenuClickHandler(SLOT_DECREASE_1, (pl, slot, itemStack, action) -> {
            int newQuantity = Math.max(1, currentQuantity - 1);
            open(pl, item, page, newQuantity);
            return false;
        });
        
        // Quantity display
        ItemStack quantityItem = item.getItem().clone();
        quantityItem.setAmount(Math.min(64, currentQuantity));
        ItemMeta meta = quantityItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ColorSystem.colorize("&eQuantity: &f" + currentQuantity));
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ColorSystem.colorize("&7Calculating materials for"));
            lore.add(ColorSystem.colorize("&f" + currentQuantity + "x &7" + item.getItemName()));
            lore.add("");
            lore.add(ColorSystem.colorize("&7Use buttons to adjust quantity"));
            meta.setLore(lore);
            quantityItem.setItemMeta(meta);
        }
        menu.addItem(SLOT_QUANTITY_DISPLAY, quantityItem);
        menu.addMenuClickHandler(SLOT_QUANTITY_DISPLAY, ChestMenuUtils.getEmptyClickHandler());
        
        // +1 button
        menu.addItem(SLOT_INCREASE_1, CustomItemStack.create(Material.LIME_CONCRETE, "&a+1", "", "&7Click to increase by 1", "&7Current: &f" + currentQuantity));
        menu.addMenuClickHandler(SLOT_INCREASE_1, (pl, slot, itemStack, action) -> {
            int newQuantity = currentQuantity + 1;
            open(pl, item, page, newQuantity);
            return false;
        });
        
        // +10 button
        menu.addItem(SLOT_INCREASE_10, CustomItemStack.create(Material.GREEN_CONCRETE, "&a+10", "", "&7Click to increase by 10", "&7Current: &f" + currentQuantity));
        menu.addMenuClickHandler(SLOT_INCREASE_10, (pl, slot, itemStack, action) -> {
            int newQuantity = currentQuantity + 10;
            open(pl, item, page, newQuantity);
            return false;
        });
    }
}