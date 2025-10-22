package io.github.thebusybiscuit.slimefun4.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.mrneznamy.utils.ColorSystem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.items.magical.staves.StormStaff;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.compatibility.VersionedEnchantment;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;

/**
 * This class holds a static references to every {@link SlimefunItemStack}
 * found in Slimefun.
 */
@SuppressWarnings("java:S1192") // Suppress "duplicate string literal" warnings
public final class SlimefunItems {

    private SlimefunItems() {}

    /* Items */
    public static final SlimefunItemStack PORTABLE_CRAFTER = new SlimefunItemStack("PORTABLE_CRAFTER", HeadTexture.PORTABLE_CRAFTER, ColorSystem.colorize("&6Portable Crafter"), ColorSystem.colorize("&a&oA portable Crafting Table"), "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack PORTABLE_DUSTBIN = new SlimefunItemStack("PORTABLE_DUSTBIN", HeadTexture.TRASH_CAN, ColorSystem.colorize("&6Portable Dustbin"), ColorSystem.colorize("&fYour portable Item-Destroyer"), "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack ENDER_BACKPACK = new SlimefunItemStack("ENDER_BACKPACK", HeadTexture.ENDER_BACKPACK, ColorSystem.colorize("&6Ender Backpack"), ColorSystem.colorize("&a&oA portable Ender Chest"), "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack MAGIC_EYE_OF_ENDER = new SlimefunItemStack("MAGIC_EYE_OF_ENDER", Material.ENDER_EYE, ColorSystem.colorize("&6&lMagic Eye of Ender"), ColorSystem.colorize("&4&lRequires full Ender Armor"), "", ColorSystem.colorize("&7&eRight Click&7 to shoot an Ender Pearl"));
    public static final SlimefunItemStack BROKEN_SPAWNER = new SlimefunItemStack("BROKEN_SPAWNER", Material.SPAWNER, ColorSystem.colorize("&cBroken Spawner"), ColorSystem.colorize("&7Type: &b<Type>"), "", ColorSystem.colorize("&cFractured, must be repaired in an Ancient Altar"));
    public static final SlimefunItemStack REPAIRED_SPAWNER = new SlimefunItemStack("REINFORCED_SPAWNER", Material.SPAWNER, ColorSystem.colorize("&bReinforced Spawner"), ColorSystem.colorize("&7Type: &b<Type>"));
    public static final SlimefunItemStack INFERNAL_BONEMEAL = new SlimefunItemStack("INFERNAL_BONEMEAL", Material.BONE_MEAL, ColorSystem.colorize("&4Infernal Bonemeal"), "", ColorSystem.colorize("&cSpeeds up the Growth of"), ColorSystem.colorize("&cNether Warts as well"));
    public static final SlimefunItemStack TAPE_MEASURE = new SlimefunItemStack("TAPE_MEASURE", "180d5c43a6cf5bb7769fd0c8240e1e70d2ae38ef9d78a1db401aca6a2cb36f65", ColorSystem.colorize("&6Tape Measure"), "", ColorSystem.colorize("&eCrouch & Right Click &7to set an anchor"), ColorSystem.colorize("&eRight Click &7to measure"));

    /* Gadgets */
    public static final SlimefunItemStack GOLD_PAN = new SlimefunItemStack("GOLD_PAN", Material.BOWL, ColorSystem.colorize("&6Gold Pan"), "", ColorSystem.colorize("&eRight Click&7 to collect resources"), ColorSystem.colorize("&7from Gravel"));
    public static final SlimefunItemStack NETHER_GOLD_PAN = new SlimefunItemStack("NETHER_GOLD_PAN", Material.BOWL, ColorSystem.colorize("&4Nether Gold Pan"), "", ColorSystem.colorize("&eRight Click&7 to collect resources"), ColorSystem.colorize("&7from Soul Sand"));
    public static final SlimefunItemStack PARACHUTE = new SlimefunItemStack("PARACHUTE", Material.LEATHER_CHESTPLATE, Color.WHITE, ColorSystem.colorize("&f&lParachute"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack GRAPPLING_HOOK = new SlimefunItemStack("GRAPPLING_HOOK", Material.LEAD, ColorSystem.colorize("&6Grappling Hook"), "", LoreBuilder.RIGHT_CLICK_TO_USE);
    public static final SlimefunItemStack SOLAR_HELMET = new SlimefunItemStack("SOLAR_HELMET", Material.IRON_HELMET, ColorSystem.colorize("&bSolar Helmet"), "", ColorSystem.colorize("&a&oCharges held Items and Armor"));
    public static final SlimefunItemStack CLOTH = new SlimefunItemStack("CLOTH", Material.PAPER, ColorSystem.colorize("&bCloth"));
    public static final SlimefunItemStack REINFORCED_CLOTH = new SlimefunItemStack("REINFORCED_CLOTH", Material.PAPER, ColorSystem.colorize("&bReinforced Cloth"), "", ColorSystem.colorize("&fThis cloth has been reinforced"), ColorSystem.colorize("&fwith &bLead &fto protect against"), ColorSystem.colorize("&fradioactive substances"));
    public static final SlimefunItemStack TIN_CAN = new SlimefunItemStack("CAN", HeadTexture.TIN_CAN, ColorSystem.colorize("&fTin Can"));
    public static final SlimefunItemStack NIGHT_VISION_GOGGLES = new SlimefunItemStack("NIGHT_VISION_GOGGLES", Material.LEATHER_HELMET, Color.BLACK, ColorSystem.colorize("&aNight Vision Goggles"), "", ColorSystem.colorize("&9+ Night Vision"));
    public static final SlimefunItemStack ELYTRA_CAP = new SlimefunItemStack("ELYTRA_CAP", Material.LEATHER_HELMET, Color.PURPLE, ColorSystem.colorize("&5Elytra Cap"), "", ColorSystem.colorize("&7This helmet will protect you from"), ColorSystem.colorize("&7crashing while flying with an elytra."));
    public static final SlimefunItemStack FARMER_SHOES = new SlimefunItemStack("FARMER_SHOES", Material.LEATHER_BOOTS, Color.YELLOW, ColorSystem.colorize("&eFarmer Shoes"), "", ColorSystem.colorize("&6&oPrevents you from trampling your Crops"));
    public static final SlimefunItemStack INFUSED_MAGNET = new SlimefunItemStack("INFUSED_MAGNET", HeadTexture.MAGNET, ColorSystem.colorize("&aInfused Magnet"), "", ColorSystem.colorize("&fMagical infused Magnets"), ColorSystem.colorize("&fattract nearby Items"), ColorSystem.colorize("&fas long as it is somewhere in"), ColorSystem.colorize("&fyour Inventory"), "", ColorSystem.colorize("&7Hold &eShift&7 to pick up nearby Items"));
    public static final SlimefunItemStack RAG = new SlimefunItemStack("RAG", Material.PAPER, ColorSystem.colorize("&cRag"), "", ColorSystem.colorize("&aLevel I - Medical Supply"), "", ColorSystem.colorize("&fRestores 2 Hearts"), ColorSystem.colorize("&fExtinguishes Fire"), "", LoreBuilder.RIGHT_CLICK_TO_USE);
    public static final SlimefunItemStack BANDAGE = new SlimefunItemStack("BANDAGE", Material.PAPER, ColorSystem.colorize("&cBandage"), "", ColorSystem.colorize("&aLevel II - Medical Supply"), "", ColorSystem.colorize("&fRestores 4 Hearts"), ColorSystem.colorize("&fExtinguishes Fire"), "", LoreBuilder.RIGHT_CLICK_TO_USE);
    public static final SlimefunItemStack SPLINT = new SlimefunItemStack("SPLINT", Material.STICK, ColorSystem.colorize("&cSplint"), "", ColorSystem.colorize("&aLevel I - Medical Supply"), "", ColorSystem.colorize("&fRestores 2 Hearts"), "", LoreBuilder.RIGHT_CLICK_TO_USE);
    public static final SlimefunItemStack VITAMINS = new SlimefunItemStack("VITAMINS", Material.NETHER_WART, ColorSystem.colorize("&cVitamins"), "", ColorSystem.colorize("&aLevel III - Medical Supply"), "", ColorSystem.colorize("&fRestores 4 Hearts"), ColorSystem.colorize("&fExtinguishes Fire"), ColorSystem.colorize("&fCures Poison/Wither/Radiation"), "", LoreBuilder.RIGHT_CLICK_TO_USE);
    public static final SlimefunItemStack MEDICINE = new SlimefunItemStack("MEDICINE", Material.POTION, Color.RED, ColorSystem.colorize("&cMedicine"), "", ColorSystem.colorize("&aLevel III - Medical Supply"), "", ColorSystem.colorize("&fRestores 4 Hearts"), ColorSystem.colorize("&fExtinguishes Fire"), ColorSystem.colorize("&fCures Poison/Wither/Radiation"));
    public static final SlimefunItemStack MAGICAL_ZOMBIE_PILLS = new SlimefunItemStack("MAGICAL_ZOMBIE_PILLS", Material.NETHER_WART, ColorSystem.colorize("&6Magical Zombie Pills"), "", ColorSystem.colorize("&eRight Click &7a Zombified Villager"), ColorSystem.colorize("&eor &7a Zombified Piglin to"), ColorSystem.colorize("&7instantly cure it from its curse"));

    public static final SlimefunItemStack FLASK_OF_KNOWLEDGE = new SlimefunItemStack("FLASK_OF_KNOWLEDGE", Material.GLASS_BOTTLE, ColorSystem.colorize("&cFlask of Knowledge"), "", ColorSystem.colorize("&fAllows you to store some of"), ColorSystem.colorize("&fyour Experience in a Bottle"), ColorSystem.colorize("&7Cost: &a1 Level"));
    public static final SlimefunItemStack FILLED_FLASK_OF_KNOWLEDGE = new SlimefunItemStack("FILLED_FLASK_OF_KNOWLEDGE", Material.EXPERIENCE_BOTTLE, ColorSystem.colorize("&aFlask of Knowledge"));

    /* Backpacks */
    private static final String BACKPACK_ID = ColorSystem.colorize("&7ID: <ID>");
    public static final SlimefunItemStack BACKPACK_SMALL = new SlimefunItemStack("SMALL_BACKPACK", HeadTexture.BACKPACK, ColorSystem.colorize("&eSmall Backpack"), "", ColorSystem.colorize("&7Size: &e9"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack BACKPACK_MEDIUM = new SlimefunItemStack("MEDIUM_BACKPACK", HeadTexture.BACKPACK, ColorSystem.colorize("&eBackpack"), "", ColorSystem.colorize("&7Size: &e18"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack BACKPACK_LARGE = new SlimefunItemStack("LARGE_BACKPACK", HeadTexture.BACKPACK, ColorSystem.colorize("&eLarge Backpack"), "", ColorSystem.colorize("&7Size: &e27"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack WOVEN_BACKPACK = new SlimefunItemStack("WOVEN_BACKPACK", HeadTexture.BACKPACK, ColorSystem.colorize("&eWoven Backpack"), "", ColorSystem.colorize("&7Size: &e36"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack GILDED_BACKPACK = new SlimefunItemStack("GILDED_BACKPACK", HeadTexture.BACKPACK, ColorSystem.colorize("&eGilded Backpack"), "", ColorSystem.colorize("&7Size: &e45"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack RADIANT_BACKPACK = new SlimefunItemStack("RADIANT_BACKPACK", HeadTexture.BACKPACK, ColorSystem.colorize("&eRadiant Backpack"), "", ColorSystem.colorize("&7Size: &e54 (Double chest)"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack BOUND_BACKPACK = new SlimefunItemStack("BOUND_BACKPACK", HeadTexture.ENDER_BACKPACK, ColorSystem.colorize("&cSoulbound Backpack"), "", ColorSystem.colorize("&7Size: &e36"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack COOLER = new SlimefunItemStack("COOLER", HeadTexture.COOLER, ColorSystem.colorize("&bCooler"), ColorSystem.colorize("&fAllows you to store Juices/Smoothies"), ColorSystem.colorize("&fand automatically consumes them when you are hungry"), ColorSystem.colorize("&fand you have this in your Inventory"), "", ColorSystem.colorize("&7Size: &e27"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack RESTORED_BACKPACK = new SlimefunItemStack("RESTORED_BACKPACK", HeadTexture.RESTORED_BACKPACK, ColorSystem.colorize("&eRestored Backpack"), "", ColorSystem.colorize("&7Retrieve your lost items"), BACKPACK_ID, "", LoreBuilder.RIGHT_CLICK_TO_OPEN);

    /* Jetpacks */
    public static final SlimefunItemStack DURALUMIN_JETPACK = new SlimefunItemStack("DURALUMIN_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, ColorSystem.colorize("&9Electric Jetpack &7- &eI"), "", LoreBuilder.material("Duralumin"), LoreBuilder.powerCharged(0, 20), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.35"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack SOLDER_JETPACK = new SlimefunItemStack("SOLDER_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, ColorSystem.colorize("&9Electric Jetpack &7- &eII"), "", LoreBuilder.material("Solder"), LoreBuilder.powerCharged(0, 30), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.4"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack BILLON_JETPACK = new SlimefunItemStack("BILLON_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, ColorSystem.colorize("&9Electric Jetpack &7- &eIII"), "", LoreBuilder.material("Billon"), LoreBuilder.powerCharged(0, 45), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.45"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack STEEL_JETPACK = new SlimefunItemStack("STEEL_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, ColorSystem.colorize("&9Electric Jetpack &7- &eIV"), "", LoreBuilder.material("Steel"), LoreBuilder.powerCharged(0, 60), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.5"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack DAMASCUS_STEEL_JETPACK = new SlimefunItemStack("DAMASCUS_STEEL_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, ColorSystem.colorize("&9Electric Jetpack &7- &eV"), "", LoreBuilder.material("Damascus Steel"), LoreBuilder.powerCharged(0, 75), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.55"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack REINFORCED_ALLOY_JETPACK = new SlimefunItemStack("REINFORCED_ALLOY_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, ColorSystem.colorize("&9Electric Jetpack &7- &eVI"), "", LoreBuilder.material("Reinforced Alloy"), LoreBuilder.powerCharged(0, 100), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.6"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack CARBONADO_JETPACK = new SlimefunItemStack("CARBONADO_JETPACK", Material.LEATHER_CHESTPLATE, Color.BLACK, ColorSystem.colorize("&9Electric Jetpack &7- &eVII"), "", LoreBuilder.material("Carbonado"), LoreBuilder.powerCharged(0, 150), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.7"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack ARMORED_JETPACK = new SlimefunItemStack("ARMORED_JETPACK", Material.IRON_CHESTPLATE, ColorSystem.colorize("&9Armored Jetpack"), LoreBuilder.material("Steel"), "", LoreBuilder.powerCharged(0, 50), ColorSystem.colorize("&8\u21E8 &7Thrust: &c0.5"), "", LoreBuilder.CROUCH_TO_USE);

    /* Jetboots */
    public static final SlimefunItemStack DURALUMIN_JETBOOTS = new SlimefunItemStack("DURALUMIN_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, ColorSystem.colorize("&9Jet Boots &7- &eI"), "", LoreBuilder.material("Duralumin"), LoreBuilder.powerCharged(0, 20), LoreBuilder.speed(0.35F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &c50%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack SOLDER_JETBOOTS = new SlimefunItemStack("SOLDER_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, ColorSystem.colorize("&9Jet Boots &7- &eII"), "", LoreBuilder.material("Solder"), LoreBuilder.powerCharged(0, 30), LoreBuilder.speed(0.4F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &660%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack BILLON_JETBOOTS = new SlimefunItemStack("BILLON_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, ColorSystem.colorize("&9Jet Boots &7- &eIII"), "", LoreBuilder.material("Billon"), LoreBuilder.powerCharged(0, 40), LoreBuilder.speed(0.45F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &665%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack STEEL_JETBOOTS = new SlimefunItemStack("STEEL_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, ColorSystem.colorize("&9Jet Boots &7- &eIV"), "", LoreBuilder.material("Steel"), LoreBuilder.powerCharged(0, 50), LoreBuilder.speed(0.5F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &e70%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack DAMASCUS_STEEL_JETBOOTS = new SlimefunItemStack("DAMASCUS_STEEL_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, ColorSystem.colorize("&9Jet Boots &7- &eV"), "", LoreBuilder.material("Damascus Steel"), LoreBuilder.powerCharged(0, 75), LoreBuilder.speed(0.55F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &a75%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack REINFORCED_ALLOY_JETBOOTS = new SlimefunItemStack("REINFORCED_ALLOY_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, ColorSystem.colorize("&9Jet Boots &7- &eVI"), "", LoreBuilder.material("Reinforced Alloy"), LoreBuilder.powerCharged(0, 100), LoreBuilder.speed(0.6F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &c80%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack CARBONADO_JETBOOTS = new SlimefunItemStack("CARBONADO_JETBOOTS", Material.LEATHER_BOOTS, Color.BLACK, ColorSystem.colorize("&9Jet Boots &7- &eVII"), "", LoreBuilder.material("Carbonado"), LoreBuilder.powerCharged(0, 125), LoreBuilder.speed(0.7F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &c99.9%"), "", LoreBuilder.CROUCH_TO_USE);
    public static final SlimefunItemStack ARMORED_JETBOOTS = new SlimefunItemStack("ARMORED_JETBOOTS", Material.IRON_BOOTS, ColorSystem.colorize("&9Armored Jet Boots"), "", LoreBuilder.material("Steel"), LoreBuilder.powerCharged(0, 50), LoreBuilder.speed(0.45F), ColorSystem.colorize("&8\u21E8 &7Accuracy: &e70%"), "", LoreBuilder.CROUCH_TO_USE);

    /* Multi Tools */
    private static final String MULTI_TOOL_LORE = ColorSystem.colorize("&eCrouch & Right Click&7 to switch modes");
    public static final SlimefunItemStack DURALUMIN_MULTI_TOOL = new SlimefunItemStack("DURALUMIN_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eI"), "", LoreBuilder.material("Duralumin"), LoreBuilder.powerCharged(0, 20), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);
    public static final SlimefunItemStack SOLDER_MULTI_TOOL = new SlimefunItemStack("SOLDER_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eII"), "", LoreBuilder.material("Solder"), LoreBuilder.powerCharged(0, 30), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);
    public static final SlimefunItemStack BILLON_MULTI_TOOL = new SlimefunItemStack("BILLON_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eIII"), "", LoreBuilder.material("Billon"), LoreBuilder.powerCharged(0, 40), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);
    public static final SlimefunItemStack STEEL_MULTI_TOOL = new SlimefunItemStack("STEEL_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eIV"), "", LoreBuilder.material("Steel"), LoreBuilder.powerCharged(0, 50), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);
    public static final SlimefunItemStack DAMASCUS_STEEL_MULTI_TOOL = new SlimefunItemStack("DAMASCUS_STEEL_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eV"), "", LoreBuilder.material("Damascus Steel"), LoreBuilder.powerCharged(0, 60), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);
    public static final SlimefunItemStack REINFORCED_ALLOY_MULTI_TOOL = new SlimefunItemStack("REINFORCED_ALLOY_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eVI"), "", LoreBuilder.material("Reinforced Alloy"), LoreBuilder.powerCharged(0, 75), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);
    public static final SlimefunItemStack CARBONADO_MULTI_TOOL = new SlimefunItemStack("CARBONADO_MULTI_TOOL", Material.SHEARS, ColorSystem.colorize("&9Multi Tool &7- &eVII"), "", LoreBuilder.material("Carbonado"), LoreBuilder.powerCharged(0, 100), "", LoreBuilder.RIGHT_CLICK_TO_USE, MULTI_TOOL_LORE);

    static {
        ItemMeta duralumin = DURALUMIN_MULTI_TOOL.getItemMeta();
        duralumin.setUnbreakable(true);
        DURALUMIN_MULTI_TOOL.setItemMeta(duralumin);

        ItemMeta solder = SOLDER_MULTI_TOOL.getItemMeta();
        solder.setUnbreakable(true);
        SOLDER_MULTI_TOOL.setItemMeta(solder);

        ItemMeta billon = BILLON_MULTI_TOOL.getItemMeta();
        billon.setUnbreakable(true);
        BILLON_MULTI_TOOL.setItemMeta(billon);

        ItemMeta steel = STEEL_MULTI_TOOL.getItemMeta();
        steel.setUnbreakable(true);
        STEEL_MULTI_TOOL.setItemMeta(steel);

        ItemMeta damascus = DAMASCUS_STEEL_MULTI_TOOL.getItemMeta();
        damascus.setUnbreakable(true);
        DAMASCUS_STEEL_MULTI_TOOL.setItemMeta(damascus);

        ItemMeta reinforced = REINFORCED_ALLOY_MULTI_TOOL.getItemMeta();
        reinforced.setUnbreakable(true);
        REINFORCED_ALLOY_MULTI_TOOL.setItemMeta(reinforced);

        ItemMeta carbonado = CARBONADO_MULTI_TOOL.getItemMeta();
        carbonado.setUnbreakable(true);
        CARBONADO_MULTI_TOOL.setItemMeta(carbonado);
    }

    /* Food */
    public static final SlimefunItemStack FORTUNE_COOKIE = new SlimefunItemStack("FORTUNE_COOKIE", Material.COOKIE, ColorSystem.colorize("&6Fortune Cookie"), "", ColorSystem.colorize("&a&oTells you stuff about your Future :o"));
    public static final SlimefunItemStack DIET_COOKIE = new SlimefunItemStack("DIET_COOKIE", Material.COOKIE, ColorSystem.colorize("&6Diet Cookie"), "", ColorSystem.colorize("&aA very &olightweight &f&acookie."));
    public static final SlimefunItemStack MAGIC_SUGAR = new SlimefunItemStack("MAGIC_SUGAR", Material.SUGAR, ColorSystem.colorize("&6Magic Sugar"), "", ColorSystem.colorize("&a&oFeel the Power of Hermes!"));
    public static final SlimefunItemStack MONSTER_JERKY = new SlimefunItemStack("MONSTER_JERKY", Material.ROTTEN_FLESH, ColorSystem.colorize("&6Monster Jerky"), "", ColorSystem.colorize("&a&oNo longer hungry"));
    public static final SlimefunItemStack APPLE_JUICE = new SlimefunItemStack("APPLE_JUICE", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 5, 0), ColorSystem.colorize("&cApple Juice"), "", LoreBuilder.hunger(3));
    public static final SlimefunItemStack MELON_JUICE = new SlimefunItemStack("MELON_JUICE", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 5, 0), ColorSystem.colorize("&cMelon Juice"), "", LoreBuilder.hunger(3));
    public static final SlimefunItemStack CARROT_JUICE = new SlimefunItemStack("CARROT_JUICE", Color.ORANGE, new PotionEffect(PotionEffectType.SATURATION, 5, 0), ColorSystem.colorize("&6Carrot Juice"), "", LoreBuilder.hunger(3));
    public static final SlimefunItemStack PUMPKIN_JUICE = new SlimefunItemStack("PUMPKIN_JUICE", Color.ORANGE, new PotionEffect(PotionEffectType.SATURATION, 5, 0), ColorSystem.colorize("&6Pumpkin Juice"), "", LoreBuilder.hunger(3));
    public static final SlimefunItemStack SWEET_BERRY_JUICE = new SlimefunItemStack("SWEET_BERRY_JUICE", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 5, 0), ColorSystem.colorize("&cSweet Berry Juice"), "", LoreBuilder.hunger(3));
    public static final SlimefunItemStack GLOW_BERRY_JUICE = new SlimefunItemStack("GLOW_BERRY_JUICE", Color.ORANGE, new PotionEffect(PotionEffectType.SATURATION, 5, 0), ColorSystem.colorize("&6Glow Berry Juice"), "", LoreBuilder.hunger(3));
    public static final SlimefunItemStack GOLDEN_APPLE_JUICE = new SlimefunItemStack("GOLDEN_APPLE_JUICE", Color.YELLOW, new PotionEffect(PotionEffectType.ABSORPTION, 20 * 20, 0), ColorSystem.colorize("&bGolden Apple Juice"));

    public static final SlimefunItemStack BEEF_JERKY = new SlimefunItemStack("BEEF_JERKY", Material.COOKED_BEEF, ColorSystem.colorize("&6Beef Jerky"), "", ColorSystem.colorize("&fExtra saturating!"));
    public static final SlimefunItemStack PORK_JERKY = new SlimefunItemStack("PORK_JERKY", Material.COOKED_PORKCHOP, ColorSystem.colorize("&6Pork Jerky"), "", ColorSystem.colorize("&fExtra saturating!"));
    public static final SlimefunItemStack CHICKEN_JERKY = new SlimefunItemStack("CHICKEN_JERKY", Material.COOKED_CHICKEN, ColorSystem.colorize("&6Chicken Jerky"), "", ColorSystem.colorize("&fExtra saturating!"));
    public static final SlimefunItemStack MUTTON_JERKY = new SlimefunItemStack("MUTTON_JERKY", Material.COOKED_MUTTON, ColorSystem.colorize("&6Mutton Jerky"), "", ColorSystem.colorize("&fExtra saturating!"));
    public static final SlimefunItemStack RABBIT_JERKY = new SlimefunItemStack("RABBIT_JERKY", Material.COOKED_RABBIT, ColorSystem.colorize("&6Rabbit Jerky"), "", ColorSystem.colorize("&fExtra saturating!"));
    public static final SlimefunItemStack FISH_JERKY = new SlimefunItemStack("FISH_JERKY", Material.COOKED_COD, ColorSystem.colorize("&6Fish Jerky"), "", ColorSystem.colorize("&fExtra saturating!"));

    public static final SlimefunItemStack KELP_COOKIE = new SlimefunItemStack("KELP_COOKIE", Material.COOKIE, ColorSystem.colorize("&2Kelp Cookie"));

    /* Christmas */
    public static final SlimefunItemStack CHRISTMAS_MILK = new SlimefunItemStack("CHRISTMAS_MILK", Color.WHITE, new PotionEffect(PotionEffectType.SATURATION, 4, 0), ColorSystem.colorize("&6Glass of Milk"), "", LoreBuilder.hunger(2.5));
    public static final SlimefunItemStack CHRISTMAS_CHOCOLATE_MILK = new SlimefunItemStack("CHRISTMAS_CHOCOLATE_MILK", Color.MAROON, new PotionEffect(PotionEffectType.SATURATION, 11, 0), ColorSystem.colorize("&6Chocolate Milk"), "", LoreBuilder.hunger(6));
    public static final SlimefunItemStack CHRISTMAS_EGG_NOG = new SlimefunItemStack("CHRISTMAS_EGG_NOG", Color.GRAY, new PotionEffect(PotionEffectType.SATURATION, 6, 0), ColorSystem.colorize("&aEgg Nog"), "", LoreBuilder.hunger(3.5));
    public static final SlimefunItemStack CHRISTMAS_APPLE_CIDER = new SlimefunItemStack("CHRISTMAS_APPLE_CIDER", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 13, 0), ColorSystem.colorize("&cApple Cider"), "", LoreBuilder.hunger(7));
    public static final SlimefunItemStack CHRISTMAS_COOKIE = new SlimefunItemStack("CHRISTMAS_COOKIE", Material.COOKIE, ChatUtils.christmas("Christmas Cookie"));
    public static final SlimefunItemStack CHRISTMAS_FRUIT_CAKE = new SlimefunItemStack("CHRISTMAS_FRUIT_CAKE", Material.PUMPKIN_PIE, ChatUtils.christmas("Fruit Cake"));
    public static final SlimefunItemStack CHRISTMAS_APPLE_PIE = new SlimefunItemStack("CHRISTMAS_APPLE_PIE", Material.PUMPKIN_PIE, ColorSystem.colorize("&fApple Pie"));
    public static final SlimefunItemStack CHRISTMAS_HOT_CHOCOLATE = new SlimefunItemStack("CHRISTMAS_HOT_CHOCOLATE", Color.MAROON, new PotionEffect(PotionEffectType.SATURATION, 13, 0), ColorSystem.colorize("&6Hot Chocolate"), "", LoreBuilder.hunger(7));
    public static final SlimefunItemStack CHRISTMAS_CAKE = new SlimefunItemStack("CHRISTMAS_CAKE", Material.PUMPKIN_PIE, ChatUtils.christmas("Christmas Cake"));
    public static final SlimefunItemStack CHRISTMAS_CARAMEL = new SlimefunItemStack("CHRISTMAS_CARAMEL", Material.BRICK, ColorSystem.colorize("&6Caramel"));
    public static final SlimefunItemStack CHRISTMAS_CARAMEL_APPLE = new SlimefunItemStack("CHRISTMAS_CARAMEL_APPLE", Material.APPLE, ColorSystem.colorize("&6Caramel Apple"));
    public static final SlimefunItemStack CHRISTMAS_CHOCOLATE_APPLE = new SlimefunItemStack("CHRISTMAS_CHOCOLATE_APPLE", Material.APPLE, ColorSystem.colorize("&6Chocolate Apple"));
    public static final SlimefunItemStack CHRISTMAS_PRESENT = new SlimefunItemStack("CHRISTMAS_PRESENT", HeadTexture.CHRISTMAS_PRESENT, ChatUtils.christmas("Christmas Present"), ColorSystem.colorize("&7From: &cTheBusyBiscuit"), ColorSystem.colorize("&7To: &eYou"), "", LoreBuilder.RIGHT_CLICK_TO_OPEN);

    /* Easter */
    public static final SlimefunItemStack EASTER_EGG = new SlimefunItemStack("EASTER_EGG", HeadTexture.EASTER_EGG, ColorSystem.colorize("&fEaster Egg"), ColorSystem.colorize("&dHappy Easter! Have a surprise."), "", LoreBuilder.RIGHT_CLICK_TO_OPEN);
    public static final SlimefunItemStack EASTER_CARROT_PIE = new SlimefunItemStack("CARROT_PIE", Material.PUMPKIN_PIE, ColorSystem.colorize("&6Carrot Pie"));
    public static final SlimefunItemStack EASTER_APPLE_PIE = new SlimefunItemStack("EASTER_APPLE_PIE", Material.PUMPKIN_PIE, ColorSystem.colorize("&fApple Pie"));

    /* Weapons */
    public static final SlimefunItemStack GRANDMAS_WALKING_STICK = new SlimefunItemStack("GRANDMAS_WALKING_STICK", Material.STICK, ColorSystem.colorize("&7Grandmas Walking Stick"));
    public static final SlimefunItemStack GRANDPAS_WALKING_STICK = new SlimefunItemStack("GRANDPAS_WALKING_STICK", Material.STICK, ColorSystem.colorize("&7Grandpas Walking Stick"));
    public static final SlimefunItemStack SWORD_OF_BEHEADING = new SlimefunItemStack("SWORD_OF_BEHEADING", Material.IRON_SWORD, ColorSystem.colorize("&6Sword of Beheading"), ColorSystem.colorize("&7Beheading II"), "", ColorSystem.colorize("&fHas a chance to behead Mobs"), ColorSystem.colorize("&f(even a higher chance for Wither Skeletons)"));
    public static final SlimefunItemStack BLADE_OF_VAMPIRES = new SlimefunItemStack("BLADE_OF_VAMPIRES", Material.GOLDEN_SWORD, ColorSystem.colorize("&cBlade of Vampires"), ColorSystem.colorize("&7Life Steal I"), "", ColorSystem.colorize("&fEverytime you attack something"), ColorSystem.colorize("&fyou have a 45% chance to"), ColorSystem.colorize("&frecover 2 Hearts of your Health"));
    public static final SlimefunItemStack SEISMIC_AXE = new SlimefunItemStack("SEISMIC_AXE", Material.IRON_AXE, ColorSystem.colorize("&aSeismic Axe"), "", ColorSystem.colorize("&7&oA portable Earthquake..."), "", LoreBuilder.RIGHT_CLICK_TO_USE);

    static {
        GRANDMAS_WALKING_STICK.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        GRANDPAS_WALKING_STICK.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);

        BLADE_OF_VAMPIRES.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
        BLADE_OF_VAMPIRES.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 4);
        BLADE_OF_VAMPIRES.addUnsafeEnchantment(VersionedEnchantment.SHARPNESS, 2);
    }

    /* Bows */
    public static final SlimefunItemStack EXPLOSIVE_BOW = new SlimefunItemStack("EXPLOSIVE_BOW", Material.BOW, ColorSystem.colorize("&cExplosive Bow"), ColorSystem.colorize("&fAny Arrows fired using this Bow"), ColorSystem.colorize("&fwill launch hit enemys into the air"));
    public static final SlimefunItemStack ICY_BOW = new SlimefunItemStack("ICY_BOW", Material.BOW, ColorSystem.colorize("&bIcy Bow"), ColorSystem.colorize("&fAny Arrows fired using this Bow"), ColorSystem.colorize("&fwill prevent hit enemys from moving"), ColorSystem.colorize("&ffor 2 seconds"));

    /* Tools */
    public static final SlimefunItemStack SMELTERS_PICKAXE = new SlimefunItemStack("SMELTERS_PICKAXE", Material.DIAMOND_PICKAXE, ColorSystem.colorize("&6Smelter's Pickaxe"), ColorSystem.colorize("&c&lAuto-Smelting"), "", ColorSystem.colorize("&9Works with Fortune"));
    public static final SlimefunItemStack LUMBER_AXE = new SlimefunItemStack("LUMBER_AXE", Material.DIAMOND_AXE, ColorSystem.colorize("&6Lumber Axe"), ColorSystem.colorize("&a&oCuts down the whole Tree..."));
    public static final SlimefunItemStack PICKAXE_OF_CONTAINMENT = new SlimefunItemStack("PICKAXE_OF_CONTAINMENT", Material.IRON_PICKAXE, ColorSystem.colorize("&cPickaxe of Containment"), "", ColorSystem.colorize("&9Can pickup Spawners"));
    public static final SlimefunItemStack EXPLOSIVE_PICKAXE = new SlimefunItemStack("EXPLOSIVE_PICKAXE", Material.DIAMOND_PICKAXE, ColorSystem.colorize("&eExplosive Pickaxe"), "", ColorSystem.colorize("&fAllows you to mine a good bit"), ColorSystem.colorize("&fof Blocks at once..."), "", ColorSystem.colorize("&9Works with Fortune"));
    public static final SlimefunItemStack EXPLOSIVE_SHOVEL = new SlimefunItemStack("EXPLOSIVE_SHOVEL", Material.DIAMOND_SHOVEL, ColorSystem.colorize("&eExplosive Shovel"), "", ColorSystem.colorize("&fAllows you to mine a good bit"), ColorSystem.colorize("&fof diggable Blocks at once..."));
    public static final SlimefunItemStack PICKAXE_OF_THE_SEEKER = new SlimefunItemStack("PICKAXE_OF_THE_SEEKER", Material.DIAMOND_PICKAXE, ColorSystem.colorize("&aPickaxe of the Seeker"), ColorSystem.colorize("&fWill always point you to the nearest Ore"), ColorSystem.colorize("&fbut might get damaged when doing it"), "", ColorSystem.colorize("&7&eRight Click&7 to be pointed to the nearest Ore"));
    public static final SlimefunItemStack COBALT_PICKAXE = new SlimefunItemStack("COBALT_PICKAXE", Material.IRON_PICKAXE, ColorSystem.colorize("&9Cobalt Pickaxe"));
    public static final SlimefunItemStack PICKAXE_OF_VEIN_MINING = new SlimefunItemStack("PICKAXE_OF_VEIN_MINING", Material.DIAMOND_PICKAXE, ColorSystem.colorize("&ePickaxe of Vein Mining"), "", ColorSystem.colorize("&fThis Pickaxe will dig out"), ColorSystem.colorize("&fwhole Veins of Ores..."));
    public static final SlimefunItemStack CLIMBING_PICK = new SlimefunItemStack("CLIMBING_PICK", Material.IRON_PICKAXE, ColorSystem.colorize("&bClimbing Pick"), "", ColorSystem.colorize("&fAllows you to climb certain surfaces"), ColorSystem.colorize("&fby right-clicking."), ColorSystem.colorize("&fEnchant this pick with Efficiency to"), ColorSystem.colorize("&fclimb even faster!"));

    static {
        COBALT_PICKAXE.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 10);
        COBALT_PICKAXE.addUnsafeEnchantment(VersionedEnchantment.EFFICIENCY, 6);
    }

    /* Armor */
    public static final SlimefunItemStack GLOWSTONE_HELMET = new SlimefunItemStack("GLOWSTONE_HELMET", Material.LEATHER_HELMET, Color.YELLOW, ColorSystem.colorize("&e&lGlowstone Helmet"), "", ColorSystem.colorize("&a&oShining like the sun!"), "", ColorSystem.colorize("&9+ Night Vision"));
    public static final SlimefunItemStack GLOWSTONE_CHESTPLATE = new SlimefunItemStack("GLOWSTONE_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.YELLOW, ColorSystem.colorize("&e&lGlowstone Chestplate"), "", ColorSystem.colorize("&a&oShining like the sun!"), "", ColorSystem.colorize("&9+ Night Vision"));
    public static final SlimefunItemStack GLOWSTONE_LEGGINGS = new SlimefunItemStack("GLOWSTONE_LEGGINGS", Material.LEATHER_LEGGINGS, Color.YELLOW, ColorSystem.colorize("&e&lGlowstone Leggings"), "", ColorSystem.colorize("&a&oShining like the sun!"), "", ColorSystem.colorize("&9+ Night Vision"));
    public static final SlimefunItemStack GLOWSTONE_BOOTS = new SlimefunItemStack("GLOWSTONE_BOOTS", Material.LEATHER_BOOTS, Color.YELLOW, ColorSystem.colorize("&e&lGlowstone Boots"), "", ColorSystem.colorize("&a&oShining like the sun!"), "", ColorSystem.colorize("&9+ Night Vision"));
    public static final SlimefunItemStack RAINBOW_LEATHER = new SlimefunItemStack("RAINBOW_LEATHER", Material.RABBIT_HIDE, Color.FUCHSIA, ColorSystem.colorize("&dRainbow Leather"), "", ColorSystem.colorize("&fCan be used to craft rainbow armor"));
    public static final SlimefunItemStack RAINBOW_HELMET = new SlimefunItemStack("RAINBOW_HELMET", Material.LEATHER_HELMET, Color.FUCHSIA, ColorSystem.colorize("&d&lRainbow Helmet"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_CHESTPLATE = new SlimefunItemStack("RAINBOW_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.FUCHSIA, ColorSystem.colorize("&d&lRainbow Chestplate"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_LEGGINGS = new SlimefunItemStack("RAINBOW_LEGGINGS", Material.LEATHER_LEGGINGS, Color.FUCHSIA, ColorSystem.colorize("&d&lRainbow Leggings"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_BOOTS = new SlimefunItemStack("RAINBOW_BOOTS", Material.LEATHER_BOOTS, Color.FUCHSIA, ColorSystem.colorize("&d&lRainbow Boots"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack ENDER_HELMET = new SlimefunItemStack("ENDER_HELMET", Material.LEATHER_HELMET, Color.fromRGB(28, 25, 112), ColorSystem.colorize("&5&lEnder Helmet"), "", ColorSystem.colorize("&a&oSometimes its here, sometimes there!"));
    public static final SlimefunItemStack ENDER_CHESTPLATE = new SlimefunItemStack("ENDER_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.fromRGB(28, 25, 112), ColorSystem.colorize("&5&lEnder Chestplate"), "", ColorSystem.colorize("&a&oSometimes its here, sometimes there!"));
    public static final SlimefunItemStack ENDER_LEGGINGS = new SlimefunItemStack("ENDER_LEGGINGS", Material.LEATHER_LEGGINGS, Color.fromRGB(28, 25, 112), ColorSystem.colorize("&5&lEnder Leggings"), "", ColorSystem.colorize("&a&oSometimes its here, sometimes there!"));
    public static final SlimefunItemStack ENDER_BOOTS = new SlimefunItemStack("ENDER_BOOTS", Material.LEATHER_BOOTS, Color.fromRGB(28, 25, 112), ColorSystem.colorize("&5&lEnder Boots"), "", ColorSystem.colorize("&a&oSometimes its here, sometimes there!"), "", ColorSystem.colorize("&9+ No Enderpearl Damage"));

    public static final SlimefunItemStack SLIME_HELMET = new SlimefunItemStack("SLIME_HELMET", Material.LEATHER_HELMET, Color.LIME, ColorSystem.colorize("&a&lSlime Helmet"), "", ColorSystem.colorize("&a&oBouncy Feeling"));
    public static final SlimefunItemStack SLIME_CHESTPLATE = new SlimefunItemStack("SLIME_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.LIME, ColorSystem.colorize("&a&lSlime Chestplate"), "", ColorSystem.colorize("&a&oBouncy Feeling"));
    public static final SlimefunItemStack SLIME_LEGGINGS = new SlimefunItemStack("SLIME_LEGGINGS", Material.LEATHER_LEGGINGS, Color.LIME, ColorSystem.colorize("&a&lSlime Leggings"), "", ColorSystem.colorize("&a&oBouncy Feeling"), "", ColorSystem.colorize("&9+ Speed"));
    public static final SlimefunItemStack SLIME_BOOTS = new SlimefunItemStack("SLIME_BOOTS", Material.LEATHER_BOOTS, Color.LIME, ColorSystem.colorize("&a&lSlime Boots"), "", ColorSystem.colorize("&a&oBouncy Feeling"), "", ColorSystem.colorize("&9+ Jump Boost"), ColorSystem.colorize("&9+ No Fall Damage"));

    public static final SlimefunItemStack CACTUS_HELMET = new SlimefunItemStack("CACTUS_HELMET", Material.LEATHER_HELMET, Color.GREEN, ColorSystem.colorize("&2Cactus Helmet"));
    public static final SlimefunItemStack CACTUS_CHESTPLATE = new SlimefunItemStack("CACTUS_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.GREEN, ColorSystem.colorize("&2Cactus Chestplate"));
    public static final SlimefunItemStack CACTUS_LEGGINGS = new SlimefunItemStack("CACTUS_LEGGINGS", Material.LEATHER_LEGGINGS, Color.GREEN, ColorSystem.colorize("&2Cactus Leggings"));
    public static final SlimefunItemStack CACTUS_BOOTS = new SlimefunItemStack("CACTUS_BOOTS", Material.LEATHER_BOOTS, Color.GREEN, ColorSystem.colorize("&2Cactus Boots"));

    public static final SlimefunItemStack DAMASCUS_STEEL_HELMET = new SlimefunItemStack("DAMASCUS_STEEL_HELMET", Material.IRON_HELMET, ColorSystem.colorize("&7Damascus Steel Helmet"));
    public static final SlimefunItemStack DAMASCUS_STEEL_CHESTPLATE = new SlimefunItemStack("DAMASCUS_STEEL_CHESTPLATE", Material.IRON_CHESTPLATE, ColorSystem.colorize("&7Damascus Steel Chestplate"));
    public static final SlimefunItemStack DAMASCUS_STEEL_LEGGINGS = new SlimefunItemStack("DAMASCUS_STEEL_LEGGINGS", Material.IRON_LEGGINGS, ColorSystem.colorize("&7Damascus Steel Leggings"));
    public static final SlimefunItemStack DAMASCUS_STEEL_BOOTS = new SlimefunItemStack("DAMASCUS_STEEL_BOOTS", Material.IRON_BOOTS, ColorSystem.colorize("&7Damascus Steel Boots"));

    public static final SlimefunItemStack REINFORCED_ALLOY_HELMET = new SlimefunItemStack("REINFORCED_ALLOY_HELMET", Material.IRON_HELMET, ColorSystem.colorize("&bReinforced Helmet"));
    public static final SlimefunItemStack REINFORCED_ALLOY_CHESTPLATE = new SlimefunItemStack("REINFORCED_ALLOY_CHESTPLATE", Material.IRON_CHESTPLATE, ColorSystem.colorize("&bReinforced Chestplate"));
    public static final SlimefunItemStack REINFORCED_ALLOY_LEGGINGS = new SlimefunItemStack("REINFORCED_ALLOY_LEGGINGS", Material.IRON_LEGGINGS, ColorSystem.colorize("&bReinforced Leggings"));
    public static final SlimefunItemStack REINFORCED_ALLOY_BOOTS = new SlimefunItemStack("REINFORCED_ALLOY_BOOTS", Material.IRON_BOOTS, ColorSystem.colorize("&bReinforced Boots"));

    private static final List<String> hazmatLore = new ArrayList<>();

    static {
        hazmatLore.add("");
        hazmatLore.add(ColorSystem.colorize("&6Full set effects:"));
        hazmatLore.add(ColorSystem.colorize("&e- Radiation immunity"));
        hazmatLore.add(ColorSystem.colorize("&e- Bee Sting protection"));
    }

    public static final SlimefunItemStack SCUBA_HELMET = new SlimefunItemStack("SCUBA_HELMET", Material.LEATHER_HELMET, Color.ORANGE, ColorSystem.colorize("&cScuba Helmet"), "", ColorSystem.colorize("&7Allows you to breathe underwater"));
    public static final SlimefunItemStack HAZMAT_CHESTPLATE = new SlimefunItemStack("HAZMAT_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.ORANGE, ColorSystem.colorize("&cHazmat Suit"), "", ColorSystem.colorize("&7Allows you to walk through fire and lava"));
    public static final SlimefunItemStack HAZMAT_LEGGINGS = new SlimefunItemStack("HAZMAT_LEGGINGS", Material.LEATHER_LEGGINGS, Color.ORANGE, ColorSystem.colorize("&cHazmat Suit Leggings"), hazmatLore.toArray(new String[0]));
    public static final SlimefunItemStack HAZMAT_BOOTS = new SlimefunItemStack("RUBBER_BOOTS", Material.LEATHER_BOOTS, Color.BLACK, ColorSystem.colorize("&cHazmat Boots"), hazmatLore.toArray(new String[0]));

    static {
        ItemMeta helmetMeta = SCUBA_HELMET.getItemMeta();
        List<String> helmetLore = helmetMeta.getLore();
        helmetLore.addAll(hazmatLore);
        helmetMeta.setLore(helmetLore);
        SCUBA_HELMET.setItemMeta(helmetMeta);

        ItemMeta chestplateMeta = HAZMAT_CHESTPLATE.getItemMeta();
        List<String> chestplateLore = chestplateMeta.getLore();
        chestplateLore.addAll(hazmatLore);
        chestplateMeta.setLore(chestplateLore);
        HAZMAT_CHESTPLATE.setItemMeta(chestplateMeta);
    }

    public static final SlimefunItemStack GILDED_IRON_HELMET = new SlimefunItemStack("GILDED_IRON_HELMET", Material.GOLDEN_HELMET, ColorSystem.colorize("&6Gilded Iron Helmet"));
    public static final SlimefunItemStack GILDED_IRON_CHESTPLATE = new SlimefunItemStack("GILDED_IRON_CHESTPLATE", Material.GOLDEN_CHESTPLATE, ColorSystem.colorize("&6Gilded Iron Chestplate"));
    public static final SlimefunItemStack GILDED_IRON_LEGGINGS = new SlimefunItemStack("GILDED_IRON_LEGGINGS", Material.GOLDEN_LEGGINGS, ColorSystem.colorize("&6Gilded Iron Leggings"));
    public static final SlimefunItemStack GILDED_IRON_BOOTS = new SlimefunItemStack("GILDED_IRON_BOOTS", Material.GOLDEN_BOOTS, ColorSystem.colorize("&6Gilded Iron Boots"));

    public static final SlimefunItemStack GOLDEN_HELMET_12K = new SlimefunItemStack("GOLD_12K_HELMET", Material.GOLDEN_HELMET, ColorSystem.colorize("&6Golden Helmet &7(12-Carat)"));
    public static final SlimefunItemStack GOLDEN_CHESTPLATE_12K = new SlimefunItemStack("GOLD_12K_CHESTPLATE", Material.GOLDEN_CHESTPLATE, ColorSystem.colorize("&6Golden Chestplate &7(12-Carat)"));
    public static final SlimefunItemStack GOLDEN_LEGGINGS_12K = new SlimefunItemStack("GOLD_12K_LEGGINGS", Material.GOLDEN_LEGGINGS, ColorSystem.colorize("&6Golden Leggings &7(12-Carat)"));
    public static final SlimefunItemStack GOLDEN_BOOTS_12K = new SlimefunItemStack("GOLD_12K_BOOTS", Material.GOLDEN_BOOTS, ColorSystem.colorize("&6Golden Boots &7(12-Carat)"));

    public static final SlimefunItemStack SLIME_HELMET_STEEL = new SlimefunItemStack("SLIME_STEEL_HELMET", Material.IRON_HELMET, ColorSystem.colorize("&a&lSlime Helmet"), ColorSystem.colorize("&7&oReinforced"), "", ColorSystem.colorize("&a&oBouncy Feeling"));
    public static final SlimefunItemStack SLIME_CHESTPLATE_STEEL = new SlimefunItemStack("SLIME_STEEL_CHESTPLATE", Material.IRON_CHESTPLATE, ColorSystem.colorize("&a&lSlime Chestplate"), ColorSystem.colorize("&7&oReinforced"), "", ColorSystem.colorize("&a&oBouncy Feeling"));
    public static final SlimefunItemStack SLIME_LEGGINGS_STEEL = new SlimefunItemStack("SLIME_STEEL_LEGGINGS", Material.IRON_LEGGINGS, ColorSystem.colorize("&a&lSlime Leggings"), ColorSystem.colorize("&7&oReinforced"), "", ColorSystem.colorize("&a&oBouncy Feeling"), "", ColorSystem.colorize("&9+ Speed"));
    public static final SlimefunItemStack SLIME_BOOTS_STEEL = new SlimefunItemStack("SLIME_STEEL_BOOTS", Material.IRON_BOOTS, ColorSystem.colorize("&a&lSlime Boots"), ColorSystem.colorize("&7&oReinforced"), "", ColorSystem.colorize("&a&oBouncy Feeling"), "", ColorSystem.colorize("&9+ Jump Boost"), ColorSystem.colorize("&9+ No Fall Damage"));

    public static final SlimefunItemStack BOOTS_OF_THE_STOMPER = new SlimefunItemStack("BOOTS_OF_THE_STOMPER", Material.LEATHER_BOOTS, Color.AQUA, ColorSystem.colorize("&bBoots of the Stomper"), "", ColorSystem.colorize("&9All Fall Damage you receive"), ColorSystem.colorize("&9will be applied to nearby Mobs/Players"), "", ColorSystem.colorize("&9+ No Fall Damage"));

    public static final SlimefunItemStack BEE_HELMET = new SlimefunItemStack("BEE_HELMET", Material.GOLDEN_HELMET, ColorSystem.colorize("&e&lBee Helmet"), " ", ColorSystem.colorize("&fBzzzzzzz"));
    public static final SlimefunItemStack BEE_WINGS = new SlimefunItemStack("BEE_WINGS", Material.ELYTRA, ColorSystem.colorize("&e&lBee Wings"), " ", ColorSystem.colorize("&fBzzzzzzz"), " ", ColorSystem.colorize("&9Activates Slow falling"), ColorSystem.colorize("&9when approaching the ground"));
    public static final SlimefunItemStack BEE_LEGGINGS = new SlimefunItemStack("BEE_LEGGINGS", Material.GOLDEN_LEGGINGS, ColorSystem.colorize("&e&lBee Leggings"), " ", ColorSystem.colorize("&fBzzzzzzz"));
    public static final SlimefunItemStack BEE_BOOTS = new SlimefunItemStack("BEE_BOOTS", Material.GOLDEN_BOOTS, ColorSystem.colorize("&e&lBee Boots"), "", ColorSystem.colorize("&fBzzzzzzz"), "", ColorSystem.colorize("&9+ Jump Boost"), ColorSystem.colorize("&9+ No Fall Damage"));

    static {
        Map<Enchantment, Integer> cactusEnchs = new HashMap<>();
        cactusEnchs.put(Enchantment.THORNS, 3);
        cactusEnchs.put(VersionedEnchantment.UNBREAKING, 6);

        CACTUS_HELMET.addUnsafeEnchantments(cactusEnchs);
        CACTUS_CHESTPLATE.addUnsafeEnchantments(cactusEnchs);
        CACTUS_LEGGINGS.addUnsafeEnchantments(cactusEnchs);
        CACTUS_BOOTS.addUnsafeEnchantments(cactusEnchs);

        Map<Enchantment, Integer> damascusEnchs = new HashMap<>();
        damascusEnchs.put(VersionedEnchantment.UNBREAKING, 5);
        damascusEnchs.put(VersionedEnchantment.PROTECTION, 5);

        DAMASCUS_STEEL_HELMET.addUnsafeEnchantments(damascusEnchs);
        DAMASCUS_STEEL_CHESTPLATE.addUnsafeEnchantments(damascusEnchs);
        DAMASCUS_STEEL_LEGGINGS.addUnsafeEnchantments(damascusEnchs);
        DAMASCUS_STEEL_BOOTS.addUnsafeEnchantments(damascusEnchs);

        Map<Enchantment, Integer> reinforcedEnchs = new HashMap<>();
        reinforcedEnchs.put(VersionedEnchantment.UNBREAKING, 9);
        reinforcedEnchs.put(VersionedEnchantment.PROTECTION, 9);

        REINFORCED_ALLOY_HELMET.addUnsafeEnchantments(reinforcedEnchs);
        REINFORCED_ALLOY_CHESTPLATE.addUnsafeEnchantments(reinforcedEnchs);
        REINFORCED_ALLOY_LEGGINGS.addUnsafeEnchantments(reinforcedEnchs);
        REINFORCED_ALLOY_BOOTS.addUnsafeEnchantments(reinforcedEnchs);

        Map<Enchantment, Integer> gildedEnchs = new HashMap<>();
        gildedEnchs.put(VersionedEnchantment.UNBREAKING, 6);
        gildedEnchs.put(VersionedEnchantment.PROTECTION, 8);

        GILDED_IRON_HELMET.addUnsafeEnchantments(gildedEnchs);
        GILDED_IRON_CHESTPLATE.addUnsafeEnchantments(gildedEnchs);
        GILDED_IRON_LEGGINGS.addUnsafeEnchantments(gildedEnchs);
        GILDED_IRON_BOOTS.addUnsafeEnchantments(gildedEnchs);

        GOLDEN_HELMET_12K.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 10);
        GOLDEN_CHESTPLATE_12K.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 10);
        GOLDEN_LEGGINGS_12K.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 10);
        GOLDEN_BOOTS_12K.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 10);

        Map<Enchantment, Integer> slimeEnchs = new HashMap<>();
        slimeEnchs.put(VersionedEnchantment.UNBREAKING, 4);
        slimeEnchs.put(VersionedEnchantment.PROTECTION, 2);

        SLIME_HELMET_STEEL.addUnsafeEnchantments(slimeEnchs);
        SLIME_CHESTPLATE_STEEL.addUnsafeEnchantments(slimeEnchs);
        SLIME_LEGGINGS_STEEL.addUnsafeEnchantments(slimeEnchs);
        SLIME_BOOTS_STEEL.addUnsafeEnchantments(slimeEnchs);

        Map<Enchantment, Integer> beeEnchs = new HashMap<>();
        beeEnchs.put(VersionedEnchantment.UNBREAKING, 4);
        beeEnchs.put(VersionedEnchantment.PROTECTION, 2);

        BEE_HELMET.addUnsafeEnchantments(beeEnchs);
        BEE_WINGS.addUnsafeEnchantments(beeEnchs);
        BEE_LEGGINGS.addUnsafeEnchantments(beeEnchs);
        BEE_BOOTS.addUnsafeEnchantments(beeEnchs);
    }

    /* Magical components */
    public static final SlimefunItemStack MAGIC_LUMP_1 = new SlimefunItemStack("MAGIC_LUMP_1", Material.GOLD_NUGGET, ColorSystem.colorize("&6Magical Lump &7- &eI"), "", ColorSystem.colorize("&c&oTier: I"));
    public static final SlimefunItemStack MAGIC_LUMP_2 = new SlimefunItemStack("MAGIC_LUMP_2", Material.GOLD_NUGGET, ColorSystem.colorize("&6Magical Lump &7- &eII"), "", ColorSystem.colorize("&c&oTier: II"));
    public static final SlimefunItemStack MAGIC_LUMP_3 = new SlimefunItemStack("MAGIC_LUMP_3", Material.GOLD_NUGGET, ColorSystem.colorize("&6Magical Lump &7- &eIII"), "", ColorSystem.colorize("&c&oTier: III"));
    public static final SlimefunItemStack ENDER_LUMP_1 = new SlimefunItemStack("ENDER_LUMP_1", Material.GOLD_NUGGET, ColorSystem.colorize("&5Ender Lump &7- &eI"), "", ColorSystem.colorize("&c&oTier: I"));
    public static final SlimefunItemStack ENDER_LUMP_2 = new SlimefunItemStack("ENDER_LUMP_2", Material.GOLD_NUGGET, ColorSystem.colorize("&5Ender Lump &7- &eII"), "", ColorSystem.colorize("&c&oTier: II"));
    public static final SlimefunItemStack ENDER_LUMP_3 = new SlimefunItemStack("ENDER_LUMP_3", Material.GOLD_NUGGET, ColorSystem.colorize("&5Ender Lump &7- &eIII"), "", ColorSystem.colorize("&c&oTier: III"));
    public static final SlimefunItemStack MAGICAL_BOOK_COVER = new SlimefunItemStack("MAGICAL_BOOK_COVER", Material.PAPER, ColorSystem.colorize("&6Magical Book Cover"), "", ColorSystem.colorize("&a&oUsed for various Magic Books"));
    public static final SlimefunItemStack MAGICAL_GLASS = new SlimefunItemStack("MAGICAL_GLASS", Material.GLASS_PANE, ColorSystem.colorize("&6Magical Glass"), "", ColorSystem.colorize("&a&oUsed for various Magical Gadgets"));
    public static final SlimefunItemStack SYNTHETIC_SHULKER_SHELL = new SlimefunItemStack("SYNTHETIC_SHULKER_SHELL", Material.SHULKER_SHELL, ColorSystem.colorize("&dSynthetic Shulker Shell"), "", ColorSystem.colorize("&fThis item can be used in a"), ColorSystem.colorize("&fworkbench like a normal Shulker Shell"));

    /* Technical components */
    public static final SlimefunItemStack BASIC_CIRCUIT_BOARD = new SlimefunItemStack("BASIC_CIRCUIT_BOARD", Material.ACTIVATOR_RAIL, ColorSystem.colorize("&bBasic Circuit Board"));
    public static final SlimefunItemStack ADVANCED_CIRCUIT_BOARD = new SlimefunItemStack("ADVANCED_CIRCUIT_BOARD", Material.POWERED_RAIL, ColorSystem.colorize("&bAdvanced Circuit Board"));
    public static final SlimefunItemStack WHEAT_FLOUR = new SlimefunItemStack("WHEAT_FLOUR", Material.SUGAR, ColorSystem.colorize("&fWheat Flour"));
    public static final SlimefunItemStack STEEL_PLATE = new SlimefunItemStack("STEEL_PLATE", Material.PAPER, ColorSystem.colorize("&7&lSteel Plate"));
    public static final SlimefunItemStack BATTERY = new SlimefunItemStack("BATTERY", HeadTexture.BATTERY, ColorSystem.colorize("&6Battery"));
    public static final SlimefunItemStack CARBON = new SlimefunItemStack("CARBON", HeadTexture.CARBON, ColorSystem.colorize("&eCarbon"));
    public static final SlimefunItemStack COMPRESSED_CARBON = new SlimefunItemStack("COMPRESSED_CARBON", HeadTexture.COMPRESSED_CARBON, ColorSystem.colorize("&cCompressed Carbon"));
    public static final SlimefunItemStack CARBON_CHUNK = new SlimefunItemStack("CARBON_CHUNK", HeadTexture.COMPRESSED_CARBON, ColorSystem.colorize("&4Carbon Chunk"));
    public static final SlimefunItemStack STEEL_THRUSTER = new SlimefunItemStack("STEEL_THRUSTER", Material.BUCKET, ColorSystem.colorize("&7&lSteel Thruster"));
    public static final SlimefunItemStack POWER_CRYSTAL = new SlimefunItemStack("POWER_CRYSTAL", HeadTexture.POWER_CRYSTAL, ColorSystem.colorize("&c&lPower Crystal"));
    public static final SlimefunItemStack CHAIN = new SlimefunItemStack("CHAIN", Material.STRING, ColorSystem.colorize("&bChain"));
    public static final SlimefunItemStack HOOK = new SlimefunItemStack("HOOK", Material.FLINT, ColorSystem.colorize("&bHook"));
    public static final SlimefunItemStack SIFTED_ORE = new SlimefunItemStack("SIFTED_ORE", Material.GUNPOWDER, ColorSystem.colorize("&6Sifted Ore"));
    public static final SlimefunItemStack STONE_CHUNK = new SlimefunItemStack("STONE_CHUNK", HeadTexture.STONE_CHUNK, ColorSystem.colorize("&6Stone Chunk"));
    public static final SlimefunItemStack LAVA_CRYSTAL = new SlimefunItemStack("LAVA_CRYSTAL", HeadTexture.LAVA_CRYSTAL, ColorSystem.colorize("&4Lava Crystal"));
    public static final SlimefunItemStack SALT = new SlimefunItemStack("SALT", Material.SUGAR, ColorSystem.colorize("&fSalt"));
    public static final SlimefunItemStack CHEESE = new SlimefunItemStack("CHEESE", HeadTexture.CHEESE, ColorSystem.colorize("&fCheese"));
    public static final SlimefunItemStack BUTTER = new SlimefunItemStack("BUTTER", HeadTexture.BUTTER, ColorSystem.colorize("&fButter"));
    public static final SlimefunItemStack DUCT_TAPE = new SlimefunItemStack("DUCT_TAPE", HeadTexture.DUCT_TAPE, ColorSystem.colorize("&8Duct Tape"), "", ColorSystem.colorize("&fYou can repair Items using this"), ColorSystem.colorize("&fin an Auto-Anvil"));
    public static final SlimefunItemStack HEAVY_CREAM = new SlimefunItemStack("HEAVY_CREAM", Material.SNOWBALL, ColorSystem.colorize("&fHeavy Cream"));
    public static final SlimefunItemStack CRUSHED_ORE = new SlimefunItemStack("CRUSHED_ORE", Material.GUNPOWDER, ColorSystem.colorize("&6Crushed Ore"));
    public static final SlimefunItemStack PULVERIZED_ORE = new SlimefunItemStack("PULVERIZED_ORE", Material.GUNPOWDER, ColorSystem.colorize("&6Pulverized Ore"));
    public static final SlimefunItemStack PURE_ORE_CLUSTER = new SlimefunItemStack("PURE_ORE_CLUSTER", Material.GUNPOWDER, ColorSystem.colorize("&6Pure Ore Cluster"));
    public static final SlimefunItemStack TINY_URANIUM = new SlimefunItemStack("TINY_URANIUM", HeadTexture.URANIUM, ColorSystem.colorize("&cTiny Pile of Uranium"), "", LoreBuilder.radioactive(Radioactivity.LOW));
    public static final SlimefunItemStack SMALL_URANIUM = new SlimefunItemStack("SMALL_URANIUM", HeadTexture.URANIUM, ColorSystem.colorize("&cSmall Chunk of Uranium"), "", LoreBuilder.radioactive(Radioactivity.MODERATE));
    public static final SlimefunItemStack SOLAR_PANEL = new SlimefunItemStack("SOLAR_PANEL", Material.DAYLIGHT_DETECTOR, ColorSystem.colorize("&9Photovoltaic Cell"), "", ColorSystem.colorize("&7Important component for"), ColorSystem.colorize("&7crafting a &bSolar Generator"));
    public static final SlimefunItemStack PLASTIC_SHEET = new SlimefunItemStack("PLASTIC_SHEET", Material.PAPER, ColorSystem.colorize("&fPlastic Sheet"));

    public static final SlimefunItemStack MAGNET = new SlimefunItemStack("MAGNET", HeadTexture.MAGNET, ColorSystem.colorize("&cMagnet"));
    public static final SlimefunItemStack NECROTIC_SKULL = new SlimefunItemStack("NECROTIC_SKULL", HeadTexture.NECROTIC_SKULL, ColorSystem.colorize("&cNecrotic Skull"));
    public static final SlimefunItemStack ESSENCE_OF_AFTERLIFE = new SlimefunItemStack("ESSENCE_OF_AFTERLIFE", Material.GUNPOWDER, ColorSystem.colorize("&4Essence of Afterlife"));
    public static final SlimefunItemStack STRANGE_NETHER_GOO = new SlimefunItemStack("STRANGE_NETHER_GOO", Material.PURPLE_DYE, ColorSystem.colorize("&5Strange Nether Goo"), "", ColorSystem.colorize("&fA strange bio matter that"), ColorSystem.colorize("&fcan be acquired from"), ColorSystem.colorize("&fbartering with Piglins"));
    public static final SlimefunItemStack ELECTRO_MAGNET = new SlimefunItemStack("ELECTRO_MAGNET", HeadTexture.MAGNET, ColorSystem.colorize("&cElectromagnet"));
    public static final SlimefunItemStack HEATING_COIL = new SlimefunItemStack("HEATING_COIL", HeadTexture.HEATING_COIL, ColorSystem.colorize("&cHeating Coil"));
    public static final SlimefunItemStack COOLING_UNIT = new SlimefunItemStack("COOLING_UNIT", HeadTexture.COOLING_UNIT, ColorSystem.colorize("&bCooling Unit"));
    public static final SlimefunItemStack ELECTRIC_MOTOR = new SlimefunItemStack("ELECTRIC_MOTOR", HeadTexture.MOTOR, ColorSystem.colorize("&cElectric Motor"));
    public static final SlimefunItemStack CARGO_MOTOR = new SlimefunItemStack("CARGO_MOTOR", HeadTexture.CARGO_MOTOR, ColorSystem.colorize("&3Cargo Motor"), "", ColorSystem.colorize("&7Important ingredient for items"), ColorSystem.colorize("&7related to Cargo Management"));
    public static final SlimefunItemStack SCROLL_OF_DIMENSIONAL_TELEPOSITION = new SlimefunItemStack("SCROLL_OF_DIMENSIONAL_TELEPOSITION", Material.PAPER, ColorSystem.colorize("&6Scroll of Dimensional Teleposition"), "", ColorSystem.colorize("&cThis Scroll is capable of creating"), ColorSystem.colorize("&ca temporary black Hole which pulls"), ColorSystem.colorize("&cnearby Entities into itself and sends"), ColorSystem.colorize("&cthem into another Dimension where"), ColorSystem.colorize("&ceverything is turned around"), "", ColorSystem.colorize("&fIn other words: Makes Entities turn by 180 Degrees"));
    public static final SlimefunItemStack TOME_OF_KNOWLEDGE_SHARING = new SlimefunItemStack("TOME_OF_KNOWLEDGE_SHARING", Material.ENCHANTED_BOOK, ColorSystem.colorize("&6Tome of Knowledge Sharing"), ColorSystem.colorize("&7Owner: &bNone"), "", ColorSystem.colorize("&eRight Click&7 to bind this Tome to yourself"), "", "", ColorSystem.colorize("&eRight Click&7 to obtain all Researches by"), ColorSystem.colorize("&7the previously assigned Owner"));
    public static final SlimefunItemStack HARDENED_GLASS = new SlimefunItemStack("HARDENED_GLASS", Material.LIGHT_GRAY_STAINED_GLASS, ColorSystem.colorize("&7Hardened Glass"), "", ColorSystem.colorize("&fWithstands Explosions"));
    public static final SlimefunItemStack WITHER_PROOF_OBSIDIAN = new SlimefunItemStack("WITHER_PROOF_OBSIDIAN", Material.OBSIDIAN, ColorSystem.colorize("&5Wither-Proof Obsidian"), "", ColorSystem.colorize("&fWithstands Explosions"), ColorSystem.colorize("&fWithstands Wither Bosses"));
    public static final SlimefunItemStack WITHER_PROOF_GLASS = new SlimefunItemStack("WITHER_PROOF_GLASS", Material.PURPLE_STAINED_GLASS, ColorSystem.colorize("&5Wither-Proof Glass"), "", ColorSystem.colorize("&fWithstands Explosions"), ColorSystem.colorize("&fWithstands Wither Bosses"));
    public static final SlimefunItemStack REINFORCED_PLATE = new SlimefunItemStack("REINFORCED_PLATE", Material.PAPER, ColorSystem.colorize("&7Reinforced Plate"));
    public static final SlimefunItemStack ANCIENT_PEDESTAL = new SlimefunItemStack("ANCIENT_PEDESTAL", Material.DISPENSER, ColorSystem.colorize("&dAncient Pedestal"), "", ColorSystem.colorize("&5Part of the Ancient Altar"));
    public static final SlimefunItemStack ANCIENT_ALTAR = new SlimefunItemStack("ANCIENT_ALTAR", Material.ENCHANTING_TABLE, ColorSystem.colorize("&dAncient Altar"), "", ColorSystem.colorize("&5Multi-Block Altar for"), ColorSystem.colorize("&5magical Crafting Processes"));
    public static final SlimefunItemStack COPPER_WIRE = new SlimefunItemStack("COPPER_WIRE", Material.STRING, ColorSystem.colorize("&6Copper Wire"), "", ColorSystem.colorize("&6Crucial component in electric modules"));
    public static final SlimefunItemStack CRAFTING_MOTOR = new SlimefunItemStack("CRAFTING_MOTOR", HeadTexture.CRAFTING_MOTOR, ColorSystem.colorize("&6Crafting Motor"), "", ColorSystem.colorize("&7Important component of Auto-Crafters"));

    /* Rainbow blocks */
    public static final SlimefunItemStack RAINBOW_WOOL = new SlimefunItemStack("RAINBOW_WOOL", Material.WHITE_WOOL, ColorSystem.colorize("&5Rainbow Wool"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_GLASS = new SlimefunItemStack("RAINBOW_GLASS", Material.WHITE_STAINED_GLASS, ColorSystem.colorize("&5Rainbow Glass"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_CLAY = new SlimefunItemStack("RAINBOW_CLAY", Material.WHITE_TERRACOTTA, ColorSystem.colorize("&5Rainbow Clay"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_GLASS_PANE = new SlimefunItemStack("RAINBOW_GLASS_PANE", Material.WHITE_STAINED_GLASS_PANE, ColorSystem.colorize("&5Rainbow Glass Pane"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_CONCRETE = new SlimefunItemStack("RAINBOW_CONCRETE", Material.WHITE_CONCRETE, ColorSystem.colorize("&5Rainbow Concrete"), "", LoreBuilder.RAINBOW);
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA", Material.WHITE_GLAZED_TERRACOTTA, ColorSystem.colorize("&5Rainbow Glazed Terracotta"), "", LoreBuilder.RAINBOW);
    /* Seasonal */
    private static final String CHRISTMAS = ChatUtils.christmas("[Christmas Edition]");
    public static final SlimefunItemStack RAINBOW_WOOL_XMAS = new SlimefunItemStack("RAINBOW_WOOL_XMAS", Material.WHITE_WOOL, ColorSystem.colorize("&5Rainbow Wool &7(Christmas)"), "", CHRISTMAS);
    public static final SlimefunItemStack RAINBOW_GLASS_XMAS = new SlimefunItemStack("RAINBOW_GLASS_XMAS", Material.WHITE_STAINED_GLASS, ColorSystem.colorize("&5Rainbow Glass &7(Christmas)"), "", CHRISTMAS);
    public static final SlimefunItemStack RAINBOW_CLAY_XMAS = new SlimefunItemStack("RAINBOW_CLAY_XMAS", Material.WHITE_TERRACOTTA, ColorSystem.colorize("&5Rainbow Clay &7(Christmas)"), "", CHRISTMAS);
    public static final SlimefunItemStack RAINBOW_GLASS_PANE_XMAS = new SlimefunItemStack("RAINBOW_GLASS_PANE_XMAS", Material.WHITE_STAINED_GLASS_PANE, ColorSystem.colorize("&5Rainbow Glass Pane &7(Christmas)"), "", CHRISTMAS);
    public static final SlimefunItemStack RAINBOW_CONCRETE_XMAS = new SlimefunItemStack("RAINBOW_CONCRETE_XMAS", Material.WHITE_CONCRETE, ColorSystem.colorize("&5Rainbow Concrete &7(Christmas)"), "", CHRISTMAS);
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA_XMAS = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA_XMAS", Material.WHITE_GLAZED_TERRACOTTA, ColorSystem.colorize("&5Rainbow Glazed Terracotta &7(Christmas)"), "", CHRISTMAS);

    private static final String VALENTINES_DAY = ColorSystem.colorize("&5[&dValentine's Day Edition&5]");
    public static final SlimefunItemStack RAINBOW_WOOL_VALENTINE = new SlimefunItemStack("RAINBOW_WOOL_VALENTINE", Material.PINK_WOOL, ColorSystem.colorize("&5Rainbow Wool &7(Valentine's Day)"), "", VALENTINES_DAY);
    public static final SlimefunItemStack RAINBOW_GLASS_VALENTINE = new SlimefunItemStack("RAINBOW_GLASS_VALENTINE", Material.PINK_STAINED_GLASS, ColorSystem.colorize("&5Rainbow Glass &7(Valentine's Day)"), "", VALENTINES_DAY);
    public static final SlimefunItemStack RAINBOW_CLAY_VALENTINE = new SlimefunItemStack("RAINBOW_CLAY_VALENTINE", Material.PINK_TERRACOTTA, ColorSystem.colorize("&5Rainbow Clay &7(Valentine's Day)"), "", VALENTINES_DAY);
    public static final SlimefunItemStack RAINBOW_GLASS_PANE_VALENTINE = new SlimefunItemStack("RAINBOW_GLASS_PANE_VALENTINE", Material.PINK_STAINED_GLASS_PANE, ColorSystem.colorize("&5Rainbow Glass Pane &7(Valentine's Day)"), "", VALENTINES_DAY);
    public static final SlimefunItemStack RAINBOW_CONCRETE_VALENTINE = new SlimefunItemStack("RAINBOW_CONCRETE_VALENTINE", Material.PINK_CONCRETE, ColorSystem.colorize("&5Rainbow Concrete &7(Valentine's Day)"), "", VALENTINES_DAY);
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA_VALENTINE = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA_VALENTINE", Material.PINK_GLAZED_TERRACOTTA, ColorSystem.colorize("&5Rainbow Glazed Terracotta &7(Valentine's Day)"), "", VALENTINES_DAY);

    private static final String HALLOWEEN = ColorSystem.colorize("&c[&6Halloween Edition&c]");
    public static final SlimefunItemStack RAINBOW_WOOL_HALLOWEEN = new SlimefunItemStack("RAINBOW_WOOL_HALLOWEEN", Material.ORANGE_WOOL, ColorSystem.colorize("&5Rainbow Wool &7(Halloween)"), "", HALLOWEEN);
    public static final SlimefunItemStack RAINBOW_GLASS_HALLOWEEN = new SlimefunItemStack("RAINBOW_GLASS_HALLOWEEN", Material.ORANGE_STAINED_GLASS, ColorSystem.colorize("&5Rainbow Glass &7(Halloween)"), "", HALLOWEEN);
    public static final SlimefunItemStack RAINBOW_CLAY_HALLOWEEN = new SlimefunItemStack("RAINBOW_CLAY_HALLOWEEN", Material.ORANGE_TERRACOTTA, ColorSystem.colorize("&5Rainbow Clay &7(Halloween)"), "", HALLOWEEN);
    public static final SlimefunItemStack RAINBOW_GLASS_PANE_HALLOWEEN = new SlimefunItemStack("RAINBOW_GLASS_PANE_HALLOWEEN", Material.ORANGE_STAINED_GLASS_PANE, ColorSystem.colorize("&5Rainbow Glass Pane &7(Halloween)"), "", HALLOWEEN);
    public static final SlimefunItemStack RAINBOW_CONCRETE_HALLOWEEN = new SlimefunItemStack("RAINBOW_CONCRETE_HALLOWEEN", Material.ORANGE_CONCRETE, ColorSystem.colorize("&5Rainbow Concrete &7(Halloween)"), "", HALLOWEEN);
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA_HALLOWEEN = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA_HALLOWEEN", Material.ORANGE_GLAZED_TERRACOTTA, ColorSystem.colorize("&5Rainbow Glazed Terracotta &7(Halloween)"), "", HALLOWEEN);

    /* Ingots */
    public static final SlimefunItemStack COPPER_INGOT = new SlimefunItemStack("COPPER_INGOT", Material.BRICK, ColorSystem.colorize("&bCopper Ingot"));
    public static final SlimefunItemStack TIN_INGOT = new SlimefunItemStack("TIN_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bTin Ingot"));
    public static final SlimefunItemStack SILVER_INGOT = new SlimefunItemStack("SILVER_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bSilver Ingot"));
    public static final SlimefunItemStack ALUMINUM_INGOT = new SlimefunItemStack("ALUMINUM_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bAluminum Ingot"));
    public static final SlimefunItemStack LEAD_INGOT = new SlimefunItemStack("LEAD_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bLead Ingot"));
    public static final SlimefunItemStack ZINC_INGOT = new SlimefunItemStack("ZINC_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bZinc Ingot"));
    public static final SlimefunItemStack MAGNESIUM_INGOT = new SlimefunItemStack("MAGNESIUM_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bMagnesium Ingot"));

    /* Alloy (Carbon + Iron) */
    public static final SlimefunItemStack STEEL_INGOT = new SlimefunItemStack("STEEL_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bSteel Ingot"));
    /* Alloy (Copper + Tin) */
    public static final SlimefunItemStack BRONZE_INGOT = new SlimefunItemStack("BRONZE_INGOT", Material.BRICK, ColorSystem.colorize("&bBronze Ingot"));
    /* Alloy (Copper + Aluminum) */
    public static final SlimefunItemStack DURALUMIN_INGOT = new SlimefunItemStack("DURALUMIN_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bDuralumin Ingot"));
    /* Alloy (Copper + Silver) */
    public static final SlimefunItemStack BILLON_INGOT = new SlimefunItemStack("BILLON_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bBillon Ingot"));
    /* Alloy (Copper + Zinc) */
    public static final SlimefunItemStack BRASS_INGOT = new SlimefunItemStack("BRASS_INGOT", Material.GOLD_INGOT, ColorSystem.colorize("&bBrass Ingot"));
    /* Alloy (Aluminum + Brass) */
    public static final SlimefunItemStack ALUMINUM_BRASS_INGOT = new SlimefunItemStack("ALUMINUM_BRASS_INGOT", Material.GOLD_INGOT, ColorSystem.colorize("&bAluminum Brass Ingot"));
    /* Alloy (Aluminum + Bronze) */
    public static final SlimefunItemStack ALUMINUM_BRONZE_INGOT = new SlimefunItemStack("ALUMINUM_BRONZE_INGOT", Material.GOLD_INGOT, ColorSystem.colorize("&bAluminum Bronze Ingot"));
    /* Alloy (Gold + Silver + Copper) */
    public static final SlimefunItemStack CORINTHIAN_BRONZE_INGOT = new SlimefunItemStack("CORINTHIAN_BRONZE_INGOT", Material.GOLD_INGOT, ColorSystem.colorize("&bCorinthian Bronze Ingot"));
    /* Alloy (Lead + Tin) */
    public static final SlimefunItemStack SOLDER_INGOT = new SlimefunItemStack("SOLDER_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bSolder Ingot"));
    /* Alloy (Steel + Iron + Carbon) */
    public static final SlimefunItemStack DAMASCUS_STEEL_INGOT = new SlimefunItemStack("DAMASCUS_STEEL_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bDamascus Steel Ingot"));
    /* Alloy (Damascus Steel + Duralumin + Compressed Carbon + Aluminium Bronze) */
    public static final SlimefunItemStack HARDENED_METAL_INGOT = new SlimefunItemStack("HARDENED_METAL_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&b&lHardened Metal"));
    /* Alloy (Hardened Metal + Corinthian Bronze + Solder + Billon + Damascus Steel) */
    public static final SlimefunItemStack REINFORCED_ALLOY_INGOT = new SlimefunItemStack("REINFORCED_ALLOY_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&b&lReinforced Alloy Ingot"));
    /* Alloy (Iron + Silicon) */
    public static final SlimefunItemStack FERROSILICON = new SlimefunItemStack("FERROSILICON", Material.IRON_INGOT, ColorSystem.colorize("&bFerrosilicon"));
    /* Alloy (Iron + Gold) */
    public static final SlimefunItemStack GILDED_IRON = new SlimefunItemStack("GILDED_IRON", Material.GOLD_INGOT, ColorSystem.colorize("&6&lGilded Iron"));
    /* Alloy (Redstone + Ferrosilicon) */
    public static final SlimefunItemStack REDSTONE_ALLOY = new SlimefunItemStack("REDSTONE_ALLOY", Material.BRICK, ColorSystem.colorize("&cRedstone Alloy Ingot"));
    /* Alloy (Iron + Copper) */
    public static final SlimefunItemStack NICKEL_INGOT = new SlimefunItemStack("NICKEL_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&bNickel Ingot"));
    /* Alloy (Nickel + Iron + Copper) */
    public static final SlimefunItemStack COBALT_INGOT = new SlimefunItemStack("COBALT_INGOT", Material.IRON_INGOT, ColorSystem.colorize("&9Cobalt Ingot"));

    /* Gold */
    public static final SlimefunItemStack GOLD_4K = new SlimefunItemStack("GOLD_4K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(4-Carat)"));
    public static final SlimefunItemStack GOLD_6K = new SlimefunItemStack("GOLD_6K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(6-Carat)"));
    public static final SlimefunItemStack GOLD_8K = new SlimefunItemStack("GOLD_8K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(8-Carat)"));
    public static final SlimefunItemStack GOLD_10K = new SlimefunItemStack("GOLD_10K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(10-Carat)"));
    public static final SlimefunItemStack GOLD_12K = new SlimefunItemStack("GOLD_12K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(12-Carat)"));
    public static final SlimefunItemStack GOLD_14K = new SlimefunItemStack("GOLD_14K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(14-Carat)"));
    public static final SlimefunItemStack GOLD_16K = new SlimefunItemStack("GOLD_16K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(16-Carat)"));
    public static final SlimefunItemStack GOLD_18K = new SlimefunItemStack("GOLD_18K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(18-Carat)"));
    public static final SlimefunItemStack GOLD_20K = new SlimefunItemStack("GOLD_20K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(20-Carat)"));
    public static final SlimefunItemStack GOLD_22K = new SlimefunItemStack("GOLD_22K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(22-Carat)"));
    public static final SlimefunItemStack GOLD_24K = new SlimefunItemStack("GOLD_24K", Material.GOLD_INGOT, ColorSystem.colorize("&fGold Ingot &7(24-Carat)"));

    /* Dusts */
    public static final SlimefunItemStack IRON_DUST = new SlimefunItemStack("IRON_DUST", Material.GUNPOWDER, ColorSystem.colorize("&6Iron Dust"));
    public static final SlimefunItemStack GOLD_DUST = new SlimefunItemStack("GOLD_DUST", Material.GLOWSTONE_DUST, ColorSystem.colorize("&6Gold Dust"));
    public static final SlimefunItemStack TIN_DUST = new SlimefunItemStack("TIN_DUST", Material.SUGAR, ColorSystem.colorize("&6Tin Dust"));
    public static final SlimefunItemStack COPPER_DUST = new SlimefunItemStack("COPPER_DUST", Material.GLOWSTONE_DUST, ColorSystem.colorize("&6Copper Dust"));
    public static final SlimefunItemStack SILVER_DUST = new SlimefunItemStack("SILVER_DUST", Material.SUGAR, ColorSystem.colorize("&6Silver Dust"));
    public static final SlimefunItemStack ALUMINUM_DUST = new SlimefunItemStack("ALUMINUM_DUST", Material.SUGAR, ColorSystem.colorize("&6Aluminum Dust"));
    public static final SlimefunItemStack LEAD_DUST = new SlimefunItemStack("LEAD_DUST", Material.GUNPOWDER, ColorSystem.colorize("&6Lead Dust"));
    public static final SlimefunItemStack ZINC_DUST = new SlimefunItemStack("ZINC_DUST", Material.SUGAR, ColorSystem.colorize("&6Zinc Dust"));
    public static final SlimefunItemStack MAGNESIUM_DUST = new SlimefunItemStack("MAGNESIUM_DUST", Material.SUGAR, ColorSystem.colorize("&6Magnesium"));

    public static final SlimefunItemStack SULFATE = new SlimefunItemStack("SULFATE", Material.GLOWSTONE_DUST, ColorSystem.colorize("&6Sulfate"));
    public static final SlimefunItemStack SILICON = new SlimefunItemStack("SILICON", Material.FIREWORK_STAR, ColorSystem.colorize("&6Silicon"));
    public static final SlimefunItemStack GOLD_24K_BLOCK = new SlimefunItemStack("GOLD_24K_BLOCK", Material.GOLD_BLOCK, ColorSystem.colorize("&fGold Block &7(24-Carat)"));

    /* Gems */
    public static final SlimefunItemStack SYNTHETIC_DIAMOND = new SlimefunItemStack("SYNTHETIC_DIAMOND", Material.DIAMOND, ColorSystem.colorize("&bSynthetic Diamond"), "", ColorSystem.colorize("&fThis item can be used in a"), ColorSystem.colorize("&fworkbench and acts like a normal Diamond"));
    public static final SlimefunItemStack SYNTHETIC_EMERALD = new SlimefunItemStack("SYNTHETIC_EMERALD", Material.EMERALD, ColorSystem.colorize("&bSynthetic Emerald"), "", ColorSystem.colorize("&fThis item can be used to"), ColorSystem.colorize("&ftrade with Villagers"));
    public static final SlimefunItemStack SYNTHETIC_SAPPHIRE = new SlimefunItemStack("SYNTHETIC_SAPPHIRE", HeadTexture.SAPPHIRE, ColorSystem.colorize("&bSynthetic Sapphire"), "", ColorSystem.colorize("&fThis item can be used in a"), ColorSystem.colorize("&fworkbench and acts like Lapis Lazuli"));
    public static final SlimefunItemStack CARBONADO = new SlimefunItemStack("CARBONADO", HeadTexture.CARBONADO, ColorSystem.colorize("&b&lCarbonado"), "", ColorSystem.colorize("&7&o\"Black Diamond\""));
    public static final SlimefunItemStack RAW_CARBONADO = new SlimefunItemStack("RAW_CARBONADO", HeadTexture.RAW_CARBONADO, ColorSystem.colorize("&bRaw Carbonado"));

    public static final SlimefunItemStack URANIUM = new SlimefunItemStack("URANIUM", HeadTexture.URANIUM, ColorSystem.colorize("&4Uranium"), "", LoreBuilder.radioactive(Radioactivity.HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack NEPTUNIUM = new SlimefunItemStack("NEPTUNIUM", HeadTexture.NEPTUNIUM, ColorSystem.colorize("&aNeptunium"), "", LoreBuilder.radioactive(Radioactivity.HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack PLUTONIUM = new SlimefunItemStack("PLUTONIUM", HeadTexture.PLUTONIUM, ColorSystem.colorize("&7Plutonium"), "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack BOOSTED_URANIUM = new SlimefunItemStack("BOOSTED_URANIUM", HeadTexture.BOOSTED_URANIUM, ColorSystem.colorize("&2Boosted Uranium"), "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);

    /* Talisman */
    public static final SlimefunItemStack COMMON_TALISMAN = new SlimefunItemStack("COMMON_TALISMAN", Material.EMERALD, ColorSystem.colorize("&6Common Talisman"));
    public static final SlimefunItemStack ENDER_TALISMAN = new SlimefunItemStack("ENDER_TALISMAN", Material.EMERALD, ColorSystem.colorize("&5Ender Talisman"));

    public static final SlimefunItemStack TALISMAN_ANVIL = new SlimefunItemStack("ANVIL_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Anvil"), "", ColorSystem.colorize("&fEach Talisman can prevent"), ColorSystem.colorize("&f1 Tool from breaking, but will then"), ColorSystem.colorize("&fbe consumed"), "", ColorSystem.colorize("&4&lWARNING:"), ColorSystem.colorize("&4This Talisman does not work on"), ColorSystem.colorize("&4Tools which are too powerful"), ColorSystem.colorize("&4due to their complexity"));
    public static final SlimefunItemStack TALISMAN_MINER = new SlimefunItemStack("MINER_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Miner"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it has"), ColorSystem.colorize("&fa 20% chance of doubling"), ColorSystem.colorize("&fall Ores you mine"));
    public static final SlimefunItemStack TALISMAN_FARMER = new SlimefunItemStack("FARMER_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Farmer"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it has"), ColorSystem.colorize("&fa 20% chance of doubling"), ColorSystem.colorize("&fall crops you harvest"));
    public static final SlimefunItemStack TALISMAN_HUNTER = new SlimefunItemStack("HUNTER_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Hunter"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it has"), ColorSystem.colorize("&fa 20% chance of doubling"), ColorSystem.colorize("&fall Drops from Mobs you kill"));
    public static final SlimefunItemStack TALISMAN_LAVA = new SlimefunItemStack("LAVA_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Lava Walker"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it will"), ColorSystem.colorize("&fgive you Fire Resistance"), ColorSystem.colorize("&fas soon as you touch Lava"), ColorSystem.colorize("&fbut will then be consumed"));
    public static final SlimefunItemStack TALISMAN_WATER = new SlimefunItemStack("WATER_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Water Breather"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it will"), ColorSystem.colorize("&fgive you the ability"), ColorSystem.colorize("&fto breath underwater as"), ColorSystem.colorize("&fsoon as you start drowning"), ColorSystem.colorize("&fbut will then be consumed"));
    public static final SlimefunItemStack TALISMAN_ANGEL = new SlimefunItemStack("ANGEL_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Angel"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it has a"), ColorSystem.colorize("&f75% chance to prevent you"), ColorSystem.colorize("&ffrom taking Fall Damage"));
    public static final SlimefunItemStack TALISMAN_FIRE = new SlimefunItemStack("FIRE_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Firefighter"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it will"), ColorSystem.colorize("&fgive you Fire Resistance"), ColorSystem.colorize("&fas soon as you start burning"), ColorSystem.colorize("&fbut will then be consumed"));
    public static final SlimefunItemStack TALISMAN_MAGICIAN = new SlimefunItemStack("MAGICIAN_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Magician"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it gives"), ColorSystem.colorize("&fyou a 80% Luck Bonus on Enchanting"), ColorSystem.colorize("&fYou will sometimes get an Extra Enchantment"));
    public static final SlimefunItemStack TALISMAN_TRAVELLER = new SlimefunItemStack("TRAVELLER_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Traveller"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it gives"), ColorSystem.colorize("&fyou a 60% Chance for a decent"), ColorSystem.colorize("&fSpeed Buff when you start sprinting"));
    public static final SlimefunItemStack TALISMAN_WARRIOR = new SlimefunItemStack("WARRIOR_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Warrior"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it gives"), ColorSystem.colorize("&fyou Strength III whenever you get hit"), ColorSystem.colorize("&fbut will then be consumed"));
    public static final SlimefunItemStack TALISMAN_KNIGHT = new SlimefunItemStack("KNIGHT_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Knight"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it gives"), ColorSystem.colorize("&fyou a 30% Chance for 5 Seconds of Regeneration"), ColorSystem.colorize("&fwhenever You get hit"), ColorSystem.colorize("&fbut will then be consumed"));
    public static final SlimefunItemStack TALISMAN_WHIRLWIND = new SlimefunItemStack("WHIRLWIND_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Whirlwind"), "", ColorSystem.colorize("&fHaving this Talisman"), ColorSystem.colorize("&fin your Inventory will reflect"), ColorSystem.colorize("&f60% of any projectiles fired at you."), ColorSystem.colorize("&e&oOnly a thrown Trident can pierce"), ColorSystem.colorize("&e&othrough this layer of protection"));
    public static final SlimefunItemStack TALISMAN_WIZARD = new SlimefunItemStack("WIZARD_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Wizard"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your Inventory it allows you to"), ColorSystem.colorize("&fobtain Fortune Level 4/5 however"), ColorSystem.colorize("&fit also has a chance to lower the"), ColorSystem.colorize("&fLevel of some Enchantments on your Item"));
    public static final SlimefunItemStack TALISMAN_CAVEMAN = new SlimefunItemStack("CAVEMAN_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Caveman"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your inventory it gives"), ColorSystem.colorize("&fyou a 50% chance for a decent"), ColorSystem.colorize("&fHaste buff when you mine any ore"));
    public static final SlimefunItemStack TALISMAN_WISE = new SlimefunItemStack("WISE_TALISMAN", Material.EMERALD, ColorSystem.colorize("&aTalisman of the Wise"), "", ColorSystem.colorize("&fWhile you have this Talisman"), ColorSystem.colorize("&fin your inventory it gives"), ColorSystem.colorize("&fyou a 20% chance of doubling"), ColorSystem.colorize("&fany experience you obtain"));

    /* Staves */
    public static final SlimefunItemStack STAFF_ELEMENTAL = new SlimefunItemStack("STAFF_ELEMENTAL", Material.STICK, ColorSystem.colorize("&6Elemental Staff"));
    public static final SlimefunItemStack STAFF_WIND = new SlimefunItemStack("STAFF_ELEMENTAL_WIND", Material.STICK, ColorSystem.colorize("&6Elemental Staff &7- &b&oWind"), "", ColorSystem.colorize("&7Element: &b&oWind"), "", ColorSystem.colorize("&eRight Click&7 to launch yourself forward"));
    public static final SlimefunItemStack STAFF_FIRE = new SlimefunItemStack("STAFF_ELEMENTAL_FIRE", Material.STICK, ColorSystem.colorize("&6Elemental Staff &7- &c&oFire"), "", ColorSystem.colorize("&7Element: &c&oFire"));
    public static final SlimefunItemStack STAFF_WATER = new SlimefunItemStack("STAFF_ELEMENTAL_WATER", Material.STICK, ColorSystem.colorize("&6Elemental Staff &7- &1&oWater"), "", ColorSystem.colorize("&7Element: &1&oWater"), "", ColorSystem.colorize("&eRight Click&7 to extinguish yourself"));
    public static final SlimefunItemStack STAFF_STORM = new SlimefunItemStack("STAFF_ELEMENTAL_STORM", Material.STICK, ColorSystem.colorize("&6Elemental Staff &7- &8&oStorm"), "", ColorSystem.colorize("&7Element: &8&oStorm"), "", ColorSystem.colorize("&eRight Click&7 to summon a lightning"), LoreBuilder.usesLeft(StormStaff.MAX_USES));

    static {
        STAFF_WIND.addUnsafeEnchantment(VersionedEnchantment.LUCK_OF_THE_SEA, 1);
        STAFF_FIRE.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
        STAFF_WATER.addUnsafeEnchantment(VersionedEnchantment.AQUA_AFFINITY, 1);
        STAFF_STORM.addUnsafeEnchantment(VersionedEnchantment.UNBREAKING, 1);
    }

    /* Multiblocks */
    public static final SlimefunItemStack ENHANCED_CRAFTING_TABLE = new SlimefunItemStack("ENHANCED_CRAFTING_TABLE", Material.CRAFTING_TABLE, ColorSystem.colorize("&eEnhanced Crafting Table"), "", ColorSystem.colorize("&aA regular Crafting Table cannot"), ColorSystem.colorize("&ahold this massive Amount of Power..."));
    public static final SlimefunItemStack GRIND_STONE = new SlimefunItemStack("GRIND_STONE", Material.DISPENSER, ColorSystem.colorize("&bGrind Stone"), "", ColorSystem.colorize("&aGrinds items down into other items"));
    public static final SlimefunItemStack ARMOR_FORGE = new SlimefunItemStack("ARMOR_FORGE", Material.ANVIL, ColorSystem.colorize("&6Armor Forge"), "", ColorSystem.colorize("&aGives you the ability to create powerful armor"));
    public static final SlimefunItemStack MAKESHIFT_SMELTERY = new SlimefunItemStack("MAKESHIFT_SMELTERY", Material.BLAST_FURNACE, ColorSystem.colorize("&eMakeshift Smeltery"), "", ColorSystem.colorize("&fImprovised version of the Smeltery"), ColorSystem.colorize("&fthat only allows you to"), ColorSystem.colorize("&fsmelt dusts into ingots"));
    public static final SlimefunItemStack SMELTERY = new SlimefunItemStack("SMELTERY", Material.FURNACE, ColorSystem.colorize("&6Smeltery"), "", ColorSystem.colorize("&fA high-temperature furnace"), ColorSystem.colorize("&fthat allows you to smelt dusts"), ColorSystem.colorize("&finto ingots and create alloys."));
    public static final SlimefunItemStack ORE_CRUSHER = new SlimefunItemStack("ORE_CRUSHER", Material.DISPENSER, ColorSystem.colorize("&bOre Crusher"), "", ColorSystem.colorize("&aCrushes ores to double them"));
    public static final SlimefunItemStack COMPRESSOR = new SlimefunItemStack("COMPRESSOR", Material.PISTON, ColorSystem.colorize("&bCompressor"), "", ColorSystem.colorize("&aCompresses Items"));
    public static final SlimefunItemStack PRESSURE_CHAMBER = new SlimefunItemStack("PRESSURE_CHAMBER", Material.GLASS, ColorSystem.colorize("&bPressure Chamber"), "", ColorSystem.colorize("&aCompresses Items even further"));
    public static final SlimefunItemStack MAGIC_WORKBENCH = new SlimefunItemStack("MAGIC_WORKBENCH", Material.CRAFTING_TABLE, ColorSystem.colorize("&6Magic Workbench"), "", ColorSystem.colorize("&dInfuses Items with magical Energy"));
    public static final SlimefunItemStack ORE_WASHER = new SlimefunItemStack("ORE_WASHER", Material.CAULDRON, ColorSystem.colorize("&6Ore Washer"), "", ColorSystem.colorize("&aWashes Sifted Ore to filter Ores"), ColorSystem.colorize("&aand gives you small Stone Chunks"));
    public static final SlimefunItemStack TABLE_SAW = new SlimefunItemStack("TABLE_SAW", Material.STONECUTTER, ColorSystem.colorize("&6Table Saw"), "", ColorSystem.colorize("&aAllows you to get 8 planks from 1 Log"), ColorSystem.colorize("&a(Works with all log types)"));
    public static final SlimefunItemStack JUICER = new SlimefunItemStack("JUICER", Material.GLASS_BOTTLE, ColorSystem.colorize("&aJuicer"), "", ColorSystem.colorize("&aAllows you to create delicious Juice"));
    public static final SlimefunItemStack AUTOMATED_PANNING_MACHINE = new SlimefunItemStack("AUTOMATED_PANNING_MACHINE", Material.BOWL, ColorSystem.colorize("&eAutomated Panning Machine"), "", ColorSystem.colorize("&fA MultiBlock Version of the Gold Pan"), ColorSystem.colorize("&fand Nether Gold Pan combined in one machine."));

    public static final SlimefunItemStack INDUSTRIAL_MINER = new SlimefunItemStack("INDUSTRIAL_MINER", Material.GOLDEN_PICKAXE, ColorSystem.colorize("&bIndustrial Miner"), "", ColorSystem.colorize("&fThis Multiblock will mine any Ores"), ColorSystem.colorize("&fin a 7x7 area underneath it."), ColorSystem.colorize("&fPlace coal or similar in its chest"), ColorSystem.colorize("&fto fuel this machine."));
    public static final SlimefunItemStack ADVANCED_INDUSTRIAL_MINER = new SlimefunItemStack("ADVANCED_INDUSTRIAL_MINER", Material.DIAMOND_PICKAXE, ColorSystem.colorize("&cAdvanced Industrial Miner"), "", ColorSystem.colorize("&fThis Multiblock will mine any Ores"), ColorSystem.colorize("&fin a 11x11 area underneath it."), ColorSystem.colorize("&fPlace a bucket of fuel or lava in"), ColorSystem.colorize("&fits chest to fuel this machine."), "", ColorSystem.colorize("&a+ Silk Touch"));

    static {
        ItemMeta meta = INDUSTRIAL_MINER.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        INDUSTRIAL_MINER.setItemMeta(meta);

        ItemMeta meta2 = ADVANCED_INDUSTRIAL_MINER.getItemMeta();
        meta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ADVANCED_INDUSTRIAL_MINER.setItemMeta(meta2);
    }

    /* Machines */
    public static final SlimefunItemStack COMPOSTER = new SlimefunItemStack("COMPOSTER", Material.CAULDRON, ColorSystem.colorize("&aComposter"), "", ColorSystem.colorize("&a&oCan convert various Materials over Time..."));
    public static final SlimefunItemStack CRUCIBLE = new SlimefunItemStack("CRUCIBLE", Material.CAULDRON, ColorSystem.colorize("&cCrucible"), "", ColorSystem.colorize("&a&oUsed to smelt Items into Liquids"));
    public static final SlimefunItemStack OUTPUT_CHEST = new SlimefunItemStack("OUTPUT_CHEST", Material.CHEST, ColorSystem.colorize("&4Output Chest"), "", ColorSystem.colorize("&c&oA basic machine will try to put"), ColorSystem.colorize("&c&oitems in this chest if it's placed"), ColorSystem.colorize("&c&oadjacent to the dispenser."));
    public static final SlimefunItemStack IGNITION_CHAMBER = new SlimefunItemStack("IGNITION_CHAMBER", Material.DROPPER, ColorSystem.colorize("&4Automatic Ignition Chamber"), "", ColorSystem.colorize("&fPrevents the Smeltery from using up fire."), ColorSystem.colorize("&fJust fill it up with \"Flint and Steel\""), ColorSystem.colorize("&fand place it adjacent to the Smeltery's dispenser"));
    public static final SlimefunItemStack HOLOGRAM_PROJECTOR = new SlimefunItemStack("HOLOGRAM_PROJECTOR", Material.QUARTZ_SLAB, ColorSystem.colorize("&bHologram Projector"), "", ColorSystem.colorize("&fProjects an Editable Hologram"));
    public static final SlimefunItemStack BLOCK_PLACER = new SlimefunItemStack("BLOCK_PLACER", Material.DISPENSER, ColorSystem.colorize("&aBlock Placer"), "", ColorSystem.colorize("&fAll Blocks in this Dispenser"), ColorSystem.colorize("&fwill automatically get placed"));

    /* Enhanced Furnaces */
    public static final SlimefunItemStack ENHANCED_FURNACE = new SlimefunItemStack("ENHANCED_FURNACE", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eI"), "", ColorSystem.colorize("&7Processing Speed: &e1x"), ColorSystem.colorize("&7Fuel Efficiency: &e1x"), ColorSystem.colorize("&7Luck Multiplier: &e1x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_2 = new SlimefunItemStack("ENHANCED_FURNACE_2", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eII"), "", ColorSystem.colorize("&7Processing Speed: &e2x"), ColorSystem.colorize("&7Fuel Efficiency: &e1x"), ColorSystem.colorize("&7Luck Multiplier: &e1x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_3 = new SlimefunItemStack("ENHANCED_FURNACE_3", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eIII"), "", ColorSystem.colorize("&7Processing Speed: &e2x"), ColorSystem.colorize("&7Fuel Efficiency: &e2x"), ColorSystem.colorize("&7Luck Multiplier: &e1x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_4 = new SlimefunItemStack("ENHANCED_FURNACE_4", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eIV"), "", ColorSystem.colorize("&7Processing Speed: &e3x"), ColorSystem.colorize("&7Fuel Efficiency: &e2x"), ColorSystem.colorize("&7Luck Multiplier: &e1x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_5 = new SlimefunItemStack("ENHANCED_FURNACE_5", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eV"), "", ColorSystem.colorize("&7Processing Speed: &e3x"), ColorSystem.colorize("&7Fuel Efficiency: &e2x"), ColorSystem.colorize("&7Luck Multiplier: &e2x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_6 = new SlimefunItemStack("ENHANCED_FURNACE_6", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eVI"), "", ColorSystem.colorize("&7Processing Speed: &e3x"), ColorSystem.colorize("&7Fuel Efficiency: &e3x"), ColorSystem.colorize("&7Luck Multiplier: &e2x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_7 = new SlimefunItemStack("ENHANCED_FURNACE_7", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eVII"), "", ColorSystem.colorize("&7Processing Speed: &e4x"), ColorSystem.colorize("&7Fuel Efficiency: &e3x"), ColorSystem.colorize("&7Luck Multiplier: &e2x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_8 = new SlimefunItemStack("ENHANCED_FURNACE_8", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eVIII"), "", ColorSystem.colorize("&7Processing Speed: &e4x"), ColorSystem.colorize("&7Fuel Efficiency: &e4x"), ColorSystem.colorize("&7Luck Multiplier: &e2x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_9 = new SlimefunItemStack("ENHANCED_FURNACE_9", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eIX"), "", ColorSystem.colorize("&7Processing Speed: &e5x"), ColorSystem.colorize("&7Fuel Efficiency: &e4x"), ColorSystem.colorize("&7Luck Multiplier: &e2x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_10 = new SlimefunItemStack("ENHANCED_FURNACE_10", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eX"), "", ColorSystem.colorize("&7Processing Speed: &e5x"), ColorSystem.colorize("&7Fuel Efficiency: &e5x"), ColorSystem.colorize("&7Luck Multiplier: &e2x"));
    public static final SlimefunItemStack ENHANCED_FURNACE_11 = new SlimefunItemStack("ENHANCED_FURNACE_11", Material.FURNACE, ColorSystem.colorize("&7Enhanced Furnace - &eXI"), "", ColorSystem.colorize("&7Processing Speed: &e5x"), ColorSystem.colorize("&7Fuel Efficiency: &e5x"), ColorSystem.colorize("&7Luck Multiplier: &e3x"));
    public static final SlimefunItemStack REINFORCED_FURNACE = new SlimefunItemStack("REINFORCED_FURNACE", Material.FURNACE, ColorSystem.colorize("&7Reinforced Furnace"), "", ColorSystem.colorize("&7Processing Speed: &e10x"), ColorSystem.colorize("&7Fuel Efficiency: &e10x"), ColorSystem.colorize("&7Luck Multiplier: &e3x"));
    public static final SlimefunItemStack CARBONADO_EDGED_FURNACE = new SlimefunItemStack("CARBONADO_EDGED_FURNACE", Material.FURNACE, ColorSystem.colorize("&7Carbonado Edged Furnace"), "", ColorSystem.colorize("&7Processing Speed: &e20x"), ColorSystem.colorize("&7Fuel Efficiency: &e10x"), ColorSystem.colorize("&7Luck Multiplier: &e3x"));

    /* Soulbound Items */
    public static final SlimefunItemStack SOULBOUND_SWORD = new SlimefunItemStack("SOULBOUND_SWORD", Material.DIAMOND_SWORD, ColorSystem.colorize("&cSoulbound Sword"));
    public static final SlimefunItemStack SOULBOUND_BOW = new SlimefunItemStack("SOULBOUND_BOW", Material.BOW, ColorSystem.colorize("&cSoulbound Bow"));
    public static final SlimefunItemStack SOULBOUND_PICKAXE = new SlimefunItemStack("SOULBOUND_PICKAXE", Material.DIAMOND_PICKAXE, ColorSystem.colorize("&cSoulbound Pickaxe"));
    public static final SlimefunItemStack SOULBOUND_AXE = new SlimefunItemStack("SOULBOUND_AXE", Material.DIAMOND_AXE, ColorSystem.colorize("&cSoulbound Axe"));
    public static final SlimefunItemStack SOULBOUND_SHOVEL = new SlimefunItemStack("SOULBOUND_SHOVEL", Material.DIAMOND_SHOVEL, ColorSystem.colorize("&cSoulbound Shovel"));
    public static final SlimefunItemStack SOULBOUND_HOE = new SlimefunItemStack("SOULBOUND_HOE", Material.DIAMOND_HOE, ColorSystem.colorize("&cSoulbound Hoe"));
    public static final SlimefunItemStack SOULBOUND_TRIDENT = new SlimefunItemStack("SOULBOUND_TRIDENT", Material.TRIDENT, ColorSystem.colorize("&cSoulbound Trident"));

    public static final SlimefunItemStack SOULBOUND_HELMET = new SlimefunItemStack("SOULBOUND_HELMET", Material.DIAMOND_HELMET, ColorSystem.colorize("&cSoulbound Helmet"));
    public static final SlimefunItemStack SOULBOUND_CHESTPLATE = new SlimefunItemStack("SOULBOUND_CHESTPLATE", Material.DIAMOND_CHESTPLATE, ColorSystem.colorize("&cSoulbound Chestplate"));
    public static final SlimefunItemStack SOULBOUND_LEGGINGS = new SlimefunItemStack("SOULBOUND_LEGGINGS", Material.DIAMOND_LEGGINGS, ColorSystem.colorize("&cSoulbound Leggings"));
    public static final SlimefunItemStack SOULBOUND_BOOTS = new SlimefunItemStack("SOULBOUND_BOOTS", Material.DIAMOND_BOOTS, ColorSystem.colorize("&cSoulbound Boots"));

    /* Runes */
    public static final SlimefunItemStack BLANK_RUNE = new SlimefunItemStack("BLANK_RUNE", ColoredFireworkStar.create(Color.BLACK, ColorSystem.colorize("&8Blank Rune")));

    public static final SlimefunItemStack AIR_RUNE = new SlimefunItemStack("ANCIENT_RUNE_AIR", ColoredFireworkStar.create(Color.AQUA, ColorSystem.colorize("&7Ancient Rune &8&l[&b&lAir&8&l]")));
    public static final SlimefunItemStack WATER_RUNE = new SlimefunItemStack("ANCIENT_RUNE_WATER", ColoredFireworkStar.create(Color.BLUE, ColorSystem.colorize("&7Ancient Rune &8&l[&1&lWater&8&l]")));
    public static final SlimefunItemStack FIRE_RUNE = new SlimefunItemStack("ANCIENT_RUNE_FIRE", ColoredFireworkStar.create(Color.RED, ColorSystem.colorize("&7Ancient Rune &8&l[&4&lFire&8&l]")));
    public static final SlimefunItemStack EARTH_RUNE = new SlimefunItemStack("ANCIENT_RUNE_EARTH", ColoredFireworkStar.create(Color.fromRGB(112, 47, 7), ColorSystem.colorize("&7Ancient Rune &8&l[&c&lEarth&8&l]")));
    public static final SlimefunItemStack ENDER_RUNE = new SlimefunItemStack("ANCIENT_RUNE_ENDER", ColoredFireworkStar.create(Color.PURPLE, ColorSystem.colorize("&7Ancient Rune &8&l[&5&lEnder&8&l]")));

    public static final SlimefunItemStack RAINBOW_RUNE = new SlimefunItemStack("ANCIENT_RUNE_RAINBOW", ColoredFireworkStar.create(Color.FUCHSIA, ColorSystem.colorize("&7Ancient Rune &8&l[&d&lRainbow&8&l]")));
    public static final SlimefunItemStack LIGHTNING_RUNE = new SlimefunItemStack("ANCIENT_RUNE_LIGHTNING", ColoredFireworkStar.create(Color.fromRGB(255, 255, 95), ColorSystem.colorize("&7Ancient Rune &8&l[&e&lLightning&8&l]")));
    public static final SlimefunItemStack SOULBOUND_RUNE = new SlimefunItemStack("ANCIENT_RUNE_SOULBOUND", ColoredFireworkStar.create(Color.fromRGB(47, 0, 117), ColorSystem.colorize("&7Ancient Rune &8&l[&5&lSoulbound&8&l]"), ColorSystem.colorize("&eDrop this rune onto a dropped item to"), ColorSystem.colorize("&5bind &ethat item to your soul."), " ", ColorSystem.colorize("&eIt is advised that you only use this rune"), ColorSystem.colorize("&eon &6important &eitems."), " ", ColorSystem.colorize("&eItems bound to your soul won't drop on death.")));
    public static final SlimefunItemStack ENCHANTMENT_RUNE = new SlimefunItemStack("ANCIENT_RUNE_ENCHANTMENT", ColoredFireworkStar.create(Color.fromRGB(255, 217, 25), ColorSystem.colorize("&7Ancient Rune &8&l[&6&lEnchantment&8&l]"), ColorSystem.colorize("&eDrop this rune onto a dropped item to"), ColorSystem.colorize("&6enchant &ethat item with a random enchantment.")));
    public static final SlimefunItemStack VILLAGER_RUNE = new SlimefunItemStack("ANCIENT_RUNE_VILLAGERS", ColoredFireworkStar.create(Color.fromRGB(160, 20, 5), ColorSystem.colorize("&7Ancient Rune &8&l[&4&lVillagers&8&l]"), ColorSystem.colorize("&eRight click a villager to clear"), ColorSystem.colorize("&etheir current job and trades."), ColorSystem.colorize("&eThe villager will start looking"), ColorSystem.colorize("&efor a job again after some"), ColorSystem.colorize("&etime has passed.")));

    /* Electricity */
    public static final SlimefunItemStack SOLAR_GENERATOR = new SlimefunItemStack("SOLAR_GENERATOR", Material.DAYLIGHT_DETECTOR, "&bSolar Generator", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(4));
    public static final SlimefunItemStack SOLAR_GENERATOR_2 = new SlimefunItemStack("SOLAR_GENERATOR_2", Material.DAYLIGHT_DETECTOR, "&cAdvanced Solar Generator", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(16));
    public static final SlimefunItemStack SOLAR_GENERATOR_3 = new SlimefunItemStack("SOLAR_GENERATOR_3", Material.DAYLIGHT_DETECTOR, "&4Carbonado Solar Generator", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(64));
    public static final SlimefunItemStack SOLAR_GENERATOR_4 = new SlimefunItemStack("SOLAR_GENERATOR_4", Material.DAYLIGHT_DETECTOR, "&eEnergized Solar Generator", "", "&9Works at Night", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(256) + " (Day)", LoreBuilder.powerPerSecond(128) + " (Night)");

    public static final SlimefunItemStack COAL_GENERATOR = new SlimefunItemStack("COAL_GENERATOR", HeadTexture.GENERATOR, "&cCoal Generator", "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.GENERATOR), LoreBuilder.powerBuffer(64), LoreBuilder.powerPerSecond(16));
    public static final SlimefunItemStack COAL_GENERATOR_2 = new SlimefunItemStack("COAL_GENERATOR_2", HeadTexture.GENERATOR, "&cCoal Generator &7(&eII&7)", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(30));

    public static final SlimefunItemStack LAVA_GENERATOR = new SlimefunItemStack("LAVA_GENERATOR", HeadTexture.GENERATOR, "&4Lava Generator", "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.GENERATOR), LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(20));
    public static final SlimefunItemStack LAVA_GENERATOR_2 = new SlimefunItemStack("LAVA_GENERATOR_2", HeadTexture.GENERATOR, "&4Lava Generator &7(&eII&7)", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(40));

    public static final SlimefunItemStack ELECTRIC_FURNACE = new SlimefunItemStack("ELECTRIC_FURNACE", Material.FURNACE, ColorSystem.colorize("&cElectric Furnace"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(4));
    public static final SlimefunItemStack ELECTRIC_FURNACE_2 = new SlimefunItemStack("ELECTRIC_FURNACE_2", Material.FURNACE, ColorSystem.colorize("&cElectric Furnace &7- &eII"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(2), LoreBuilder.powerPerSecond(6));
    public static final SlimefunItemStack ELECTRIC_FURNACE_3 = new SlimefunItemStack("ELECTRIC_FURNACE_3", Material.FURNACE, ColorSystem.colorize("&cElectric Furnace &7- &eIII"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(4), LoreBuilder.powerPerSecond(10));

    public static final SlimefunItemStack ELECTRIC_ORE_GRINDER = new SlimefunItemStack("ELECTRIC_ORE_GRINDER", Material.FURNACE, ColorSystem.colorize("&cElectric Ore Grinder"), "", ColorSystem.colorize("&fWorks as an Ore Crusher and Grind Stone"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(12));
    public static final SlimefunItemStack ELECTRIC_ORE_GRINDER_2 = new SlimefunItemStack("ELECTRIC_ORE_GRINDER_2", Material.FURNACE, ColorSystem.colorize("&cElectric Ore Grinder &7(&eII&7)"), "", ColorSystem.colorize("&fWorks as an Ore Crusher and Grind Stone"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(4), LoreBuilder.powerPerSecond(30));
    public static final SlimefunItemStack ELECTRIC_ORE_GRINDER_3 = new SlimefunItemStack("ELECTRIC_ORE_GRINDER_3", Material.FURNACE, ColorSystem.colorize("&cElectric Ore Grinder &7(&eIII&7)"), "", ColorSystem.colorize("&fWorks as an Ore Crusher and Grind Stone"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(10), LoreBuilder.powerPerSecond(90));
    public static final SlimefunItemStack ELECTRIC_INGOT_PULVERIZER = new SlimefunItemStack("ELECTRIC_INGOT_PULVERIZER", Material.FURNACE, ColorSystem.colorize("&cElectric Ingot Pulverizer"), "", ColorSystem.colorize("&fPulverizes Ingots into Dust"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(14));
    public static final SlimefunItemStack AUTO_DRIER = new SlimefunItemStack("AUTO_DRIER", Material.SMOKER, ColorSystem.colorize("&6Auto Drier"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(10));
    public static final SlimefunItemStack AUTO_ENCHANTER = new SlimefunItemStack("AUTO_ENCHANTER", Material.ENCHANTING_TABLE, ColorSystem.colorize("&5Auto Enchanter"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(18));
    public static final SlimefunItemStack AUTO_ENCHANTER_2 = new SlimefunItemStack("AUTO_ENCHANTER_2", Material.ENCHANTING_TABLE, ColorSystem.colorize("&5Auto Enchanter &7- &eII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerPerSecond(48));
    public static final SlimefunItemStack AUTO_DISENCHANTER = new SlimefunItemStack("AUTO_DISENCHANTER", Material.ENCHANTING_TABLE, ColorSystem.colorize("&5Auto Disenchanter"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(18));
    public static final SlimefunItemStack AUTO_DISENCHANTER_2 = new SlimefunItemStack("AUTO_DISENCHANTER_2", Material.ENCHANTING_TABLE, ColorSystem.colorize("&5Auto Disenchanter &7- &eII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerPerSecond(48));
    public static final SlimefunItemStack AUTO_ANVIL = new SlimefunItemStack("AUTO_ANVIL", Material.IRON_BLOCK, ColorSystem.colorize("&7Auto Anvil"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Repair Factor: 10%"), LoreBuilder.powerPerSecond(24));
    public static final SlimefunItemStack AUTO_ANVIL_2 = new SlimefunItemStack("AUTO_ANVIL_2", Material.IRON_BLOCK, ColorSystem.colorize("&7Auto Anvil Mk.II"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Repair Factor: 25%"), LoreBuilder.powerPerSecond(32));
    public static final SlimefunItemStack AUTO_BREWER = new SlimefunItemStack("AUTO_BREWER", Material.SMOKER, ColorSystem.colorize("&6Auto Brewer"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(12));

    public static final SlimefunItemStack BOOK_BINDER = new SlimefunItemStack("BOOK_BINDER", Material.BOOKSHELF, ColorSystem.colorize("&6Book Binder"), "", ColorSystem.colorize("&fBinds multiple enchanted books into one."), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.powerPerSecond(16));

    public static final SlimefunItemStack BIO_REACTOR = new SlimefunItemStack("BIO_REACTOR", Material.LIME_TERRACOTTA, ColorSystem.colorize("&2Bio Reactor"), "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.GENERATOR), LoreBuilder.powerBuffer(128), LoreBuilder.powerPerSecond(8));
    public static final SlimefunItemStack MULTIMETER = new SlimefunItemStack("MULTIMETER", Material.CLOCK, ColorSystem.colorize("&eMultimeter"), "", ColorSystem.colorize("&fMeasures the Amount of stored"), ColorSystem.colorize("&fEnergy in a Block"));

    public static final SlimefunItemStack SMALL_CAPACITOR = new SlimefunItemStack("SMALL_CAPACITOR", HeadTexture.CAPACITOR_25, ColorSystem.colorize("&aSmall Energy Capacitor"), LoreBuilder.range(6), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.CAPACITOR), ColorSystem.colorize("&8\u21E8 &e\u26A1 &7128 J Capacity"));
    public static final SlimefunItemStack MEDIUM_CAPACITOR = new SlimefunItemStack("MEDIUM_CAPACITOR", HeadTexture.CAPACITOR_25, ColorSystem.colorize("&aMedium Energy Capacitor"), LoreBuilder.range(6), "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.CAPACITOR), ColorSystem.colorize("&8\u21E8 &e\u26A1 &7512 J Capacity"));
    public static final SlimefunItemStack BIG_CAPACITOR = new SlimefunItemStack("BIG_CAPACITOR", HeadTexture.CAPACITOR_25, ColorSystem.colorize("&aBig Energy Capacitor"), LoreBuilder.range(6), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.CAPACITOR), ColorSystem.colorize("&8\u21E8 &e\u26A1 &71024 J Capacity"));
    public static final SlimefunItemStack LARGE_CAPACITOR = new SlimefunItemStack("LARGE_CAPACITOR", HeadTexture.CAPACITOR_25, ColorSystem.colorize("&aLarge Energy Capacitor"), LoreBuilder.range(6), "", LoreBuilder.machine(MachineTier.GOOD, MachineType.CAPACITOR), ColorSystem.colorize("&8\u21E8 &e\u26A1 &78192 J Capacity"));
    public static final SlimefunItemStack CARBONADO_EDGED_CAPACITOR = new SlimefunItemStack("CARBONADO_EDGED_CAPACITOR", HeadTexture.CAPACITOR_25, ColorSystem.colorize("&aCarbonado Edged Energy Capacitor"), LoreBuilder.range(6), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.CAPACITOR), ColorSystem.colorize("&8\u21E8 &e\u26A1 &765536 J Capacity"));
    public static final SlimefunItemStack ENERGIZED_CAPACITOR = new SlimefunItemStack("ENERGIZED_CAPACITOR", HeadTexture.CAPACITOR_25, ColorSystem.colorize("&aEnergized Energy Capacitor"), LoreBuilder.range(6), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.CAPACITOR), ColorSystem.colorize("&8\u21E8 &e\u26A1 &7524288 J Capacity"));

    /* Robots */
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID = new SlimefunItemStack("PROGRAMMABLE_ANDROID", HeadTexture.PROGRAMMABLE_ANDROID, ColorSystem.colorize("&cProgrammable Android &7(Normal)"), "", ColorSystem.colorize("&8\u21E8 &7Function: None"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_FARMER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_FARMER", HeadTexture.PROGRAMMABLE_ANDROID_FARMER, ColorSystem.colorize("&cProgrammable Android &7(Farmer)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Farming"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_MINER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_MINER", HeadTexture.PROGRAMMABLE_ANDROID_MINER, ColorSystem.colorize("&cProgrammable Android &7(Miner)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Mining"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_WOODCUTTER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_WOODCUTTER", HeadTexture.PROGRAMMABLE_ANDROID_WOODCUTTER, ColorSystem.colorize("&cProgrammable Android &7(Woodcutter)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Woodcutting"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_BUTCHER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_BUTCHER", HeadTexture.PROGRAMMABLE_ANDROID_BUTCHER, ColorSystem.colorize("&cProgrammable Android &7(Butcher)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Slaughtering"), ColorSystem.colorize("&8\u21E8 &7Damage: 4"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_FISHERMAN = new SlimefunItemStack("PROGRAMMABLE_ANDROID_FISHERMAN", HeadTexture.PROGRAMMABLE_ANDROID_FISHERMAN, ColorSystem.colorize("&cProgrammable Android &7(Fisherman)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Fishing"), ColorSystem.colorize("&8\u21E8 &7Success Rate: 10%"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.0x"));

    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_2 = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2", HeadTexture.PROGRAMMABLE_ANDROID, ColorSystem.colorize("&cAdvanced Programmable Android &7(Normal)"), "", ColorSystem.colorize("&8\u21E8 &7Function: None"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.5x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_2_FISHERMAN = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2_FISHERMAN", HeadTexture.PROGRAMMABLE_ANDROID_FISHERMAN, ColorSystem.colorize("&cAdvanced Programmable Android &7(Fisherman)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Fishing"), ColorSystem.colorize("&8\u21E8 &7Success Rate: 20%"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.5x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_2_FARMER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2_FARMER", HeadTexture.PROGRAMMABLE_ANDROID_FARMER, ColorSystem.colorize("&cAdvanced Programmable Android &7(Farmer)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Farming"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.5x"), ColorSystem.colorize("&8\u21E8 &7Can also harvest Plants from ExoticGarden"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_2_BUTCHER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2_BUTCHER", HeadTexture.PROGRAMMABLE_ANDROID_BUTCHER, ColorSystem.colorize("&cAdvanced Programmable Android &7(Butcher)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Slaughtering"), ColorSystem.colorize("&8\u21E8 &7Damage: 8"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 1.5x"));

    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_3 = new SlimefunItemStack("PROGRAMMABLE_ANDROID_3", HeadTexture.PROGRAMMABLE_ANDROID, ColorSystem.colorize("&eEmpowered Programmable Android &7(Normal)"), "", ColorSystem.colorize("&8\u21E8 &7Function: None"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 3.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_3_FISHERMAN = new SlimefunItemStack("PROGRAMMABLE_ANDROID_3_FISHERMAN", HeadTexture.PROGRAMMABLE_ANDROID_FISHERMAN, ColorSystem.colorize("&eEmpowered Programmable Android &7(Fisherman)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Fishing"), ColorSystem.colorize("&8\u21E8 &7Success Rate: 30%"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 8.0x"));
    public static final SlimefunItemStack PROGRAMMABLE_ANDROID_3_BUTCHER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_3_BUTCHER", HeadTexture.PROGRAMMABLE_ANDROID_BUTCHER, ColorSystem.colorize("&eEmpowered Programmable Android &7(Butcher)"), "", ColorSystem.colorize("&8\u21E8 &7Function: Slaughtering"), ColorSystem.colorize("&8\u21E8 &7Damage: 20"), ColorSystem.colorize("&8\u21E8 &7Fuel Efficiency: 8.0x"));

    /* GPS */
    public static final SlimefunItemStack GPS_TRANSMITTER = new SlimefunItemStack("GPS_TRANSMITTER", HeadTexture.GPS_TRANSMITTER, ColorSystem.colorize("&bGPS Transmitter"), "", LoreBuilder.powerBuffer(16), LoreBuilder.powerPerSecond(2));
    public static final SlimefunItemStack GPS_TRANSMITTER_2 = new SlimefunItemStack("GPS_TRANSMITTER_2", HeadTexture.GPS_TRANSMITTER, ColorSystem.colorize("&cAdvanced GPS Transmitter"), "", LoreBuilder.powerBuffer(64), LoreBuilder.powerPerSecond(6));
    public static final SlimefunItemStack GPS_TRANSMITTER_3 = new SlimefunItemStack("GPS_TRANSMITTER_3", HeadTexture.GPS_TRANSMITTER, ColorSystem.colorize("&4Carbonado GPS Transmitter"), "", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(22));
    public static final SlimefunItemStack GPS_TRANSMITTER_4 = new SlimefunItemStack("GPS_TRANSMITTER_4", HeadTexture.GPS_TRANSMITTER, ColorSystem.colorize("&eEnergized GPS Transmitter"), "", LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(92));

    public static final SlimefunItemStack GPS_MARKER_TOOL = new SlimefunItemStack("GPS_MARKER_TOOL", Material.REDSTONE_TORCH, ColorSystem.colorize("&bGPS Marker Tool"), "", ColorSystem.colorize("&fAllows you to set a Waypoint at"), ColorSystem.colorize("&fthe Location you place this"));
    public static final SlimefunItemStack GPS_CONTROL_PANEL = new SlimefunItemStack("GPS_CONTROL_PANEL", HeadTexture.GPS_CONTROL_PANEL, ColorSystem.colorize("&bGPS Control Panel"), "", ColorSystem.colorize("&fAllows you to track your Satellites"), ColorSystem.colorize("&fand manage your Waypoints"));
    public static final SlimefunItemStack GPS_EMERGENCY_TRANSMITTER = new SlimefunItemStack("GPS_EMERGENCY_TRANSMITTER", HeadTexture.GPS_TRANSMITTER, ColorSystem.colorize("&cGPS Emergency Transmitter"), "", ColorSystem.colorize("&fCarrying this in your Inventory"), ColorSystem.colorize("&fautomatically sets a Waypoint"), ColorSystem.colorize("&fat your Location when you die."));

    public static final SlimefunItemStack ANDROID_INTERFACE_FUEL = new SlimefunItemStack("ANDROID_INTERFACE_FUEL", Material.DISPENSER, ColorSystem.colorize("&7Android Interface &c(Fuel)"), "", ColorSystem.colorize("&fItems stored in this Interface"), ColorSystem.colorize("&fwill be inserted into an Android's Fuel Slot"), ColorSystem.colorize("&fwhen its Script tells them to do so"));
    public static final SlimefunItemStack ANDROID_INTERFACE_ITEMS = new SlimefunItemStack("ANDROID_INTERFACE_ITEMS", Material.DISPENSER, ColorSystem.colorize("&7Android Interface &9(Items)"), "", ColorSystem.colorize("&fItems stored in an Android's Inventory"), ColorSystem.colorize("&fwill be inserted into this Interface"), ColorSystem.colorize("&fwhen its Script tells them to do so"));

    public static final SlimefunItemStack GPS_GEO_SCANNER = new SlimefunItemStack("GPS_GEO_SCANNER", HeadTexture.GEO_SCANNER, ColorSystem.colorize("&bGPS Geo-Scanner"), "", ColorSystem.colorize("&fScans a Chunk for natural Resources"), ColorSystem.colorize("&fsuch as &8Oil"));
    public static final SlimefunItemStack PORTABLE_GEO_SCANNER = new SlimefunItemStack("PORTABLE_GEO_SCANNER", Material.CLOCK, ColorSystem.colorize("&bPortable Geo-Scanner"), "", ColorSystem.colorize("&fScans a Chunk for natural Resources"), "", ColorSystem.colorize("&eRight Click&7 to scan"));
    public static final SlimefunItemStack GEO_MINER = new SlimefunItemStack("GEO_MINER", HeadTexture.GEO_MINER, ColorSystem.colorize("&6GEO Miner"), "", ColorSystem.colorize("&eMines up resources from the chunk"), ColorSystem.colorize("&eThese Resources cannot be mined with a pickaxe"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(48), "", ColorSystem.colorize("&c&l! &cMake sure to Geo-Scan the Chunk first"));
    public static final SlimefunItemStack OIL_PUMP = new SlimefunItemStack("OIL_PUMP", HeadTexture.OIL_PUMP, ColorSystem.colorize("&4Oil Pump"), "", ColorSystem.colorize("&7Pumps up Oil and fills it into Buckets"), "", ColorSystem.colorize("&c&l! &cMake sure to Geo-Scan the Chunk first"));
    public static final SlimefunItemStack OIL_BUCKET = new SlimefunItemStack("BUCKET_OF_OIL", HeadTexture.OIL_BUCKET, ColorSystem.colorize("&fBucket of Oil"));
    public static final SlimefunItemStack FUEL_BUCKET = new SlimefunItemStack("BUCKET_OF_FUEL", HeadTexture.FUEL_BUCKET, ColorSystem.colorize("&fBucket of Fuel"));

    public static final SlimefunItemStack REFINERY = new SlimefunItemStack("REFINERY", Material.PISTON, ColorSystem.colorize("&cRefinery"), "", ColorSystem.colorize("&fRefines Oil to create Fuel"));
    public static final SlimefunItemStack COMBUSTION_REACTOR = new SlimefunItemStack("COMBUSTION_REACTOR", HeadTexture.GENERATOR, ColorSystem.colorize("&cCombustion Reactor"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(24));
    public static final SlimefunItemStack ANDROID_MEMORY_CORE = new SlimefunItemStack("ANDROID_MEMORY_CORE", HeadTexture.ENERGY_REGULATOR, ColorSystem.colorize("&bAndroid Memory Core"));

    public static final SlimefunItemStack GPS_TELEPORTER_PYLON = new SlimefunItemStack("GPS_TELEPORTER_PYLON", Material.PURPLE_STAINED_GLASS, ColorSystem.colorize("&5GPS Teleporter Pylon"), "", ColorSystem.colorize("&7Teleporter Component"));
    public static final SlimefunItemStack GPS_TELEPORTATION_MATRIX = new SlimefunItemStack("GPS_TELEPORTATION_MATRIX", Material.IRON_BLOCK, ColorSystem.colorize("&bGPS Teleporter Matrix"), "", ColorSystem.colorize("&fThis is your Teleporter's Main Component"), ColorSystem.colorize("&fThis Matrix allows Players to choose from all"), ColorSystem.colorize("&fWaypoints made by the Player who has placed"), ColorSystem.colorize("&fthis Device."));
    public static final SlimefunItemStack GPS_ACTIVATION_DEVICE_SHARED = new SlimefunItemStack("GPS_ACTIVATION_DEVICE_SHARED", Material.STONE_PRESSURE_PLATE, ColorSystem.colorize("&fGPS Activation Device &3(Shared)"), "", ColorSystem.colorize("&fPlace this onto a Teleportation Matrix"), ColorSystem.colorize("&fand step onto this Plate to activate"), ColorSystem.colorize("&fthe Teleportation Process"));
    public static final SlimefunItemStack GPS_ACTIVATION_DEVICE_PERSONAL = new SlimefunItemStack("GPS_ACTIVATION_DEVICE_PERSONAL", Material.STONE_PRESSURE_PLATE, ColorSystem.colorize("&fGPS Activation Device &a(Personal)"), "", ColorSystem.colorize("&fPlace this onto a Teleportation Matrix"), ColorSystem.colorize("&fand step onto this Plate to activate"), ColorSystem.colorize("&fthe Teleportation Process"), "", ColorSystem.colorize("&fThis Version only allows the Person who"), ColorSystem.colorize("&fplaced this Device to use it"));
    public static final SlimefunItemStack PORTABLE_TELEPORTER = new SlimefunItemStack("PORTABLE_TELEPORTER", Material.COMPASS, ColorSystem.colorize("&bPortable Teleporter"), "", ColorSystem.colorize("&fThis device allows you to teleport"), ColorSystem.colorize("&fto your waypoints from anywhere"), "", LoreBuilder.powerCharged(0, 50), "", ColorSystem.colorize("&eRight Click&7 to use"));

    public static final SlimefunItemStack ELEVATOR_PLATE = new SlimefunItemStack("ELEVATOR_PLATE", Material.STONE_PRESSURE_PLATE, ColorSystem.colorize("&bElevator Plate"), "", ColorSystem.colorize("&fPlace an Elevator Plate on every floor"), ColorSystem.colorize("&fand you will be able to teleport between them."), "", ColorSystem.colorize("&eRight Click this Block &7to name it"));
    public static final SlimefunItemStack INFUSED_HOPPER = new SlimefunItemStack("INFUSED_HOPPER", Material.HOPPER, ColorSystem.colorize("&5Infused Hopper"), "", ColorSystem.colorize("&fAutomatically picks up nearby Items in a 7x7x7"), ColorSystem.colorize("&fRadius when placed."));

    public static final SlimefunItemStack HEATED_PRESSURE_CHAMBER = new SlimefunItemStack("HEATED_PRESSURE_CHAMBER", Material.LIGHT_GRAY_STAINED_GLASS, ColorSystem.colorize("&cHeated Pressure Chamber"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(10));
    public static final SlimefunItemStack HEATED_PRESSURE_CHAMBER_2 = new SlimefunItemStack("HEATED_PRESSURE_CHAMBER_2", Material.LIGHT_GRAY_STAINED_GLASS, ColorSystem.colorize("&cHeated Pressure Chamber &7- &eII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(5), LoreBuilder.powerPerSecond(44));

    public static final SlimefunItemStack ELECTRIC_SMELTERY = new SlimefunItemStack("ELECTRIC_SMELTERY", Material.FURNACE, ColorSystem.colorize("&cElectric Smeltery"), "", ColorSystem.colorize("&4Alloys-Only, doesn't smelt Dust into Ingots"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(20));
    public static final SlimefunItemStack ELECTRIC_SMELTERY_2 = new SlimefunItemStack("ELECTRIC_SMELTERY_2", Material.FURNACE, ColorSystem.colorize("&cElectric Smeltery &7- &eII"), "", ColorSystem.colorize("&4Alloys-Only, doesn't smelt Dust into Ingots"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerPerSecond(40));

    public static final SlimefunItemStack ELECTRIC_PRESS = new SlimefunItemStack("ELECTRIC_PRESS", HeadTexture.ELECTRIC_PRESS, ColorSystem.colorize("&eElectric Press"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(16));
    public static final SlimefunItemStack ELECTRIC_PRESS_2 = new SlimefunItemStack("ELECTRIC_PRESS_2", HeadTexture.ELECTRIC_PRESS, ColorSystem.colorize("&eElectric Press &7- &eII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerPerSecond(40));

    public static final SlimefunItemStack ELECTRIFIED_CRUCIBLE = new SlimefunItemStack("ELECTRIFIED_CRUCIBLE", Material.RED_TERRACOTTA, ColorSystem.colorize("&cElectrified Crucible"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(48));
    public static final SlimefunItemStack ELECTRIFIED_CRUCIBLE_2 = new SlimefunItemStack("ELECTRIFIED_CRUCIBLE_2", Material.RED_TERRACOTTA, ColorSystem.colorize("&cElectrified Crucible &7- &eII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(2), LoreBuilder.powerPerSecond(80));
    public static final SlimefunItemStack ELECTRIFIED_CRUCIBLE_3 = new SlimefunItemStack("ELECTRIFIED_CRUCIBLE_3", Material.RED_TERRACOTTA, ColorSystem.colorize("&cElectrified Crucible &7- &eIII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(4), LoreBuilder.powerPerSecond(120));

    public static final SlimefunItemStack CARBON_PRESS = new SlimefunItemStack("CARBON_PRESS", Material.BLACK_STAINED_GLASS, ColorSystem.colorize("&cCarbon Press"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(20));
    public static final SlimefunItemStack CARBON_PRESS_2 = new SlimefunItemStack("CARBON_PRESS_2", Material.BLACK_STAINED_GLASS, ColorSystem.colorize("&cCarbon Press &7- &eII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerPerSecond(50));
    public static final SlimefunItemStack CARBON_PRESS_3 = new SlimefunItemStack("CARBON_PRESS_3", Material.BLACK_STAINED_GLASS, ColorSystem.colorize("&cCarbon Press &7- &eIII"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(15), LoreBuilder.powerPerSecond(180));

    public static final SlimefunItemStack BLISTERING_INGOT = new SlimefunItemStack("BLISTERING_INGOT", Material.GOLD_INGOT, ColorSystem.colorize("&6Blistering Ingot &7(33%)"), "", LoreBuilder.radioactive(Radioactivity.HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack BLISTERING_INGOT_2 = new SlimefunItemStack("BLISTERING_INGOT_2", Material.GOLD_INGOT, ColorSystem.colorize("&6Blistering Ingot &7(66%)"), "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack BLISTERING_INGOT_3 = new SlimefunItemStack("BLISTERING_INGOT_3", Material.GOLD_INGOT, ColorSystem.colorize("&6Blistering Ingot"), "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);

    public static final SlimefunItemStack ENERGY_REGULATOR = new SlimefunItemStack("ENERGY_REGULATOR", HeadTexture.ENERGY_REGULATOR, ColorSystem.colorize("&6Energy Regulator"), "", ColorSystem.colorize("&fCore Component of an Energy Network"));
    public static final SlimefunItemStack ENERGY_CONNECTOR = new SlimefunItemStack("ENERGY_CONNECTOR", HeadTexture.ENERGY_CONNECTOR, ColorSystem.colorize("&eEnergy Connector"), LoreBuilder.range(6), "", ColorSystem.colorize("&fPlace this between machines"), ColorSystem.colorize("&fand generators to connect them"), ColorSystem.colorize("&fto your regulator."));
    public static final SlimefunItemStack DEBUG_FISH = new SlimefunItemStack("DEBUG_FISH", Material.SALMON, ColorSystem.colorize("&3How much is the Fish?"), "", ColorSystem.colorize("&eRight Click &fany Block to view it's BlockData"), ColorSystem.colorize("&eLeft Click &fto break a Block"), ColorSystem.colorize("&eShift + Left Click &fany Block to erase it's BlockData"), ColorSystem.colorize("&eShift + Right Click &fto place a Placeholder Block"));

    public static final SlimefunItemStack NETHER_ICE = new SlimefunItemStack("NETHER_ICE", HeadTexture.NETHER_ICE, ColorSystem.colorize("&eNether Ice"), "", LoreBuilder.radioactive(Radioactivity.MODERATE), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack ENRICHED_NETHER_ICE = new SlimefunItemStack("ENRICHED_NETHER_ICE", HeadTexture.ENRICHED_NETHER_ICE, ColorSystem.colorize("&eEnriched Nether Ice"), "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final SlimefunItemStack NETHER_ICE_COOLANT_CELL = new SlimefunItemStack("NETHER_ICE_COOLANT_CELL", HeadTexture.NETHER_ICE_COOLANT_CELL, ColorSystem.colorize("&6Nether Ice Coolant Cell"));

    // Cargo
    public static final SlimefunItemStack CARGO_MANAGER = new SlimefunItemStack("CARGO_MANAGER", HeadTexture.CARGO_MANAGER, ColorSystem.colorize("&6Cargo Manager"), "", ColorSystem.colorize("&fCore Component of an Item Transport Network"));
    public static final SlimefunItemStack CARGO_CONNECTOR_NODE = new SlimefunItemStack("CARGO_NODE", HeadTexture.CARGO_CONNECTOR_NODE, ColorSystem.colorize("&7Cargo Node &c(Connector)"), "", ColorSystem.colorize("&fCargo Connector Pipe"));
    public static final SlimefunItemStack CARGO_INPUT_NODE = new SlimefunItemStack("CARGO_NODE_INPUT", HeadTexture.CARGO_INPUT_NODE, ColorSystem.colorize("&7Cargo Node &c(Input)"), "", ColorSystem.colorize("&fCargo Input Pipe"));
    public static final SlimefunItemStack CARGO_OUTPUT_NODE = new SlimefunItemStack("CARGO_NODE_OUTPUT", HeadTexture.CARGO_OUTPUT_NODE, ColorSystem.colorize("&7Cargo Node &c(Output)"), "", ColorSystem.colorize("&fCargo Output Pipe"));
    public static final SlimefunItemStack CARGO_OUTPUT_NODE_2 = new SlimefunItemStack("CARGO_NODE_OUTPUT_ADVANCED", HeadTexture.CARGO_OUTPUT_NODE, ColorSystem.colorize("&6Advanced Cargo Node &c(Output)"), "", ColorSystem.colorize("&fCargo Output Pipe"));

    // Animal farm
    public static final SlimefunItemStack AUTO_BREEDER = new SlimefunItemStack("AUTO_BREEDER", Material.HAY_BLOCK, ColorSystem.colorize("&eAuto-Breeder"), "", ColorSystem.colorize("&fRuns on &aOrganic Food"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.powerBuffer(1024), ColorSystem.colorize("&8\u21E8 &e\u26A1 &760 J/Animal"));
    public static final SlimefunItemStack PRODUCE_COLLECTOR = new SlimefunItemStack("PRODUCE_COLLECTOR", Material.HAY_BLOCK, ColorSystem.colorize("&bProduce Collector"), "", ColorSystem.colorize("&fThis machine allows you to"), ColorSystem.colorize("&fcollect produce from nearby animals."), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(32));

    public static final SlimefunItemStack ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD", HeadTexture.FILLED_CAN, ColorSystem.colorize("&aOrganic Food"), ColorSystem.colorize("&7Content: &9???"));
    public static final SlimefunItemStack WHEAT_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_WHEAT", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Wheat"));
    public static final SlimefunItemStack CARROT_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_CARROT", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Carrots"));
    public static final SlimefunItemStack POTATO_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_POTATO", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Potatoes"));
    public static final SlimefunItemStack SEEDS_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_SEEDS", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Seeds"));
    public static final SlimefunItemStack BEETROOT_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_BEETROOT", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Beetroot"));
    public static final SlimefunItemStack MELON_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_MELON", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Melon"));
    public static final SlimefunItemStack APPLE_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_APPLE", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Apple"));
    public static final SlimefunItemStack SWEET_BERRIES_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_SWEET_BERRIES", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Sweet Berries"));
    public static final SlimefunItemStack KELP_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_KELP", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Dried Kelp"));
    public static final SlimefunItemStack COCOA_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_COCOA", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Cocoa Beans"));
    public static final SlimefunItemStack SEAGRASS_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_SEAGRASS", HeadTexture.FILLED_CAN, ORGANIC_FOOD.getDisplayName(), ColorSystem.colorize("&7Content: &9Seagrass"));

    public static final SlimefunItemStack FERTILIZER = new SlimefunItemStack("FERTILIZER", HeadTexture.FILLED_CAN, ColorSystem.colorize("&aOrganic Fertilizer"), ColorSystem.colorize("&7Content: &9???"));
    public static final SlimefunItemStack WHEAT_FERTILIZER = new SlimefunItemStack("FERTILIZER_WHEAT", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Wheat"));
    public static final SlimefunItemStack CARROT_FERTILIZER = new SlimefunItemStack("FERTILIZER_CARROT", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Carrots"));
    public static final SlimefunItemStack POTATO_FERTILIZER = new SlimefunItemStack("FERTILIZER_POTATO", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Potatoes"));
    public static final SlimefunItemStack SEEDS_FERTILIZER = new SlimefunItemStack("FERTILIZER_SEEDS", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Seeds"));
    public static final SlimefunItemStack BEETROOT_FERTILIZER = new SlimefunItemStack("FERTILIZER_BEETROOT", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Beetroot"));
    public static final SlimefunItemStack MELON_FERTILIZER = new SlimefunItemStack("FERTILIZER_MELON", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Melon"));
    public static final SlimefunItemStack APPLE_FERTILIZER = new SlimefunItemStack("FERTILIZER_APPLE", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Apple"));
    public static final SlimefunItemStack SWEET_BERRIES_FERTILIZER = new SlimefunItemStack("FERTILIZER_SWEET_BERRIES", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Sweet Berries"));
    public static final SlimefunItemStack KELP_FERTILIZER = new SlimefunItemStack("FERTILIZER_KELP", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Dried Kelp"));
    public static final SlimefunItemStack COCOA_FERTILIZER = new SlimefunItemStack("FERTILIZER_COCOA", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Cocoa beans"));
    public static final SlimefunItemStack SEAGRASS_FERTILIZER = new SlimefunItemStack("FERTILIZER_SEAGRASS", HeadTexture.FILLED_CAN, FERTILIZER.getDisplayName(), ColorSystem.colorize("&7Content: &9Seagrass"));

    public static final SlimefunItemStack ANIMAL_GROWTH_ACCELERATOR = new SlimefunItemStack("ANIMAL_GROWTH_ACCELERATOR", Material.HAY_BLOCK, ColorSystem.colorize("&bAnimal Growth Accelerator"), "", ColorSystem.colorize("&fRuns on &aOrganic Food"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(28));
    public static final SlimefunItemStack CROP_GROWTH_ACCELERATOR = new SlimefunItemStack("CROP_GROWTH_ACCELERATOR", Material.LIME_TERRACOTTA, ColorSystem.colorize("&aCrop Growth Accelerator"), "", ColorSystem.colorize("&fRuns on &aOrganic Fertilizer"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Radius: 7x7"), ColorSystem.colorize("&8\u21E8 &7Speed: &a3/time"), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(50));
    public static final SlimefunItemStack CROP_GROWTH_ACCELERATOR_2 = new SlimefunItemStack("CROP_GROWTH_ACCELERATOR_2", Material.LIME_TERRACOTTA, ColorSystem.colorize("&aCrop Growth Accelerator &7(&eII&7)"), "", ColorSystem.colorize("&fRuns on &aOrganic Fertilizer"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Radius: 9x9"), ColorSystem.colorize("&8\u21E8 &7Speed: &a4/time"), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(60));
    public static final SlimefunItemStack TREE_GROWTH_ACCELERATOR = new SlimefunItemStack("TREE_GROWTH_ACCELERATOR", Material.BROWN_TERRACOTTA, ColorSystem.colorize("&aTree Growth Accelerator"), "", ColorSystem.colorize("&fRuns on &aOrganic Fertilizer"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Radius: 9x9"), ColorSystem.colorize("&8\u21E8 &7Speed: &a4/time"), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(48));

    public static final SlimefunItemStack FOOD_FABRICATOR = new SlimefunItemStack("FOOD_FABRICATOR", Material.GREEN_STAINED_GLASS, ColorSystem.colorize("&cFood Fabricator"), "", ColorSystem.colorize("&fProduces &aOrganic Food"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(14));
    public static final SlimefunItemStack FOOD_FABRICATOR_2 = new SlimefunItemStack("FOOD_FABRICATOR_2", Material.GREEN_STAINED_GLASS, ColorSystem.colorize("&cFood Fabricator &7(&eII&7)"), "", ColorSystem.colorize("&fProduces &aOrganic Food"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(6), LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(48));

    public static final SlimefunItemStack FOOD_COMPOSTER = new SlimefunItemStack("FOOD_COMPOSTER", Material.GREEN_TERRACOTTA, ColorSystem.colorize("&cFood Composter"), "", ColorSystem.colorize("&fProduces &aOrganic Fertilizer"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(16));
    public static final SlimefunItemStack FOOD_COMPOSTER_2 = new SlimefunItemStack("FOOD_COMPOSTER_2", Material.GREEN_TERRACOTTA, ColorSystem.colorize("&cFood Composter &7(&eII&7)"), "", ColorSystem.colorize("&fProduces &aOrganic Fertilizer"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(10), LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(52));

    public static final SlimefunItemStack EXP_COLLECTOR = new SlimefunItemStack("XP_COLLECTOR", HeadTexture.EXP_COLLECTOR, ColorSystem.colorize("&aEXP Collector"), "", ColorSystem.colorize("&fCollects nearby Exp and stores it"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(20));
    public static final SlimefunItemStack REACTOR_COOLANT_CELL = new SlimefunItemStack("REACTOR_COLLANT_CELL", HeadTexture.COOLANT_CELL, ColorSystem.colorize("&bReactor Coolant Cell"));

    public static final SlimefunItemStack NUCLEAR_REACTOR = new SlimefunItemStack("NUCLEAR_REACTOR", HeadTexture.NUCLEAR_REACTOR, ColorSystem.colorize("&2Nuclear Reactor"), "", ColorSystem.colorize("&fRequires Cooling!"), ColorSystem.colorize("&8\u21E8 &bMust be surrounded by Water"), ColorSystem.colorize("&8\u21E8 &bMust be supplied with Reactor Coolant Cells"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(16384), LoreBuilder.powerPerSecond(500));
    public static final SlimefunItemStack NETHER_STAR_REACTOR = new SlimefunItemStack("NETHERSTAR_REACTOR", HeadTexture.NETHER_STAR_REACTOR, ColorSystem.colorize("&fNether Star Reactor"), "", ColorSystem.colorize("&fRuns on Nether Stars"), ColorSystem.colorize("&8\u21E8 &bMust be surrounded by Water"), ColorSystem.colorize("&8\u21E8 &bMust be supplied with Nether Ice Coolant Cells"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(32768), LoreBuilder.powerPerSecond(1024), ColorSystem.colorize("&8\u21E8 &4Causes nearby Entities to get Withered"));
    public static final SlimefunItemStack REACTOR_ACCESS_PORT = new SlimefunItemStack("REACTOR_ACCESS_PORT", Material.CYAN_TERRACOTTA, ColorSystem.colorize("&2Reactor Access Port"), "", ColorSystem.colorize("&fAllows you to interact with a Reactor"), ColorSystem.colorize("&fvia Cargo Nodes, can also be used"), ColorSystem.colorize("&fas a Buffer"), "", ColorSystem.colorize("&8\u21E8 &eMust be placed &a3 Blocks &eabove the Reactor"));

    public static final SlimefunItemStack FREEZER = new SlimefunItemStack("FREEZER", Material.LIGHT_BLUE_STAINED_GLASS, ColorSystem.colorize("&bFreezer"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(18));
    public static final SlimefunItemStack FREEZER_2 = new SlimefunItemStack("FREEZER_2", Material.LIGHT_BLUE_STAINED_GLASS, ColorSystem.colorize("&bFreezer &7(&eII&7)"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(2), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(30));
    public static final SlimefunItemStack FREEZER_3 = new SlimefunItemStack("FREEZER_3", Material.LIGHT_BLUE_STAINED_GLASS, ColorSystem.colorize("&bFreezer &7(&eIII&7)"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(42));

    public static final SlimefunItemStack ELECTRIC_GOLD_PAN = new SlimefunItemStack("ELECTRIC_GOLD_PAN", Material.BROWN_TERRACOTTA, ColorSystem.colorize("&6Electric Gold Pan"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(2));
    public static final SlimefunItemStack ELECTRIC_GOLD_PAN_2 = new SlimefunItemStack("ELECTRIC_GOLD_PAN_2", Material.BROWN_TERRACOTTA, ColorSystem.colorize("&6Electric Gold Pan &7(&eII&7)"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(3), LoreBuilder.powerPerSecond(4));
    public static final SlimefunItemStack ELECTRIC_GOLD_PAN_3 = new SlimefunItemStack("ELECTRIC_GOLD_PAN_3", Material.BROWN_TERRACOTTA, ColorSystem.colorize("&6Electric Gold Pan &7(&eIII&7)"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(10), LoreBuilder.powerPerSecond(14));

    public static final SlimefunItemStack ELECTRIC_DUST_WASHER = new SlimefunItemStack("ELECTRIC_DUST_WASHER", Material.BLUE_STAINED_GLASS, ColorSystem.colorize("&3Electric Dust Washer"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(6));
    public static final SlimefunItemStack ELECTRIC_DUST_WASHER_2 = new SlimefunItemStack("ELECTRIC_DUST_WASHER_2", Material.BLUE_STAINED_GLASS, ColorSystem.colorize("&3Electric Dust Washer &7(&eII&7)"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(2), LoreBuilder.powerPerSecond(10));
    public static final SlimefunItemStack ELECTRIC_DUST_WASHER_3 = new SlimefunItemStack("ELECTRIC_DUST_WASHER_3", Material.BLUE_STAINED_GLASS, ColorSystem.colorize("&3Electric Dust Washer &7(&eIII&7)"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(10), LoreBuilder.powerPerSecond(30));

    public static final SlimefunItemStack ELECTRIC_INGOT_FACTORY = new SlimefunItemStack("ELECTRIC_INGOT_FACTORY", Material.RED_TERRACOTTA, ColorSystem.colorize("&cElectric Ingot Factory"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilder.powerPerSecond(8));
    public static final SlimefunItemStack ELECTRIC_INGOT_FACTORY_2 = new SlimefunItemStack("ELECTRIC_INGOT_FACTORY_2", Material.RED_TERRACOTTA, ColorSystem.colorize("&cElectric Ingot Factory &7(&eII&7)"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.speed(2), LoreBuilder.powerPerSecond(14));
    public static final SlimefunItemStack ELECTRIC_INGOT_FACTORY_3 = new SlimefunItemStack("ELECTRIC_INGOT_FACTORY_3", Material.RED_TERRACOTTA, ColorSystem.colorize("&cElectric Ingot Factory &7(&eIII&7)"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(8), LoreBuilder.powerPerSecond(40));

    public static final SlimefunItemStack FLUID_PUMP = new SlimefunItemStack("FLUID_PUMP", Material.BLUE_TERRACOTTA, ColorSystem.colorize("&9Fluid Pump"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &e\u26A1 &732 J/Block"));
    public static final SlimefunItemStack CHARGING_BENCH = new SlimefunItemStack("CHARGING_BENCH", Material.CRAFTING_TABLE, ColorSystem.colorize("&6Charging Bench"), "", ColorSystem.colorize("&fCharges Items such as Jetpacks"), "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.powerBuffer(128), ColorSystem.colorize("&8\u21E8 &e\u26A1 &7Energy Loss: &c50%"));

    public static final SlimefunItemStack VANILLA_AUTO_CRAFTER = new SlimefunItemStack("VANILLA_AUTO_CRAFTER", HeadTexture.VANILLA_AUTO_CRAFTER, ColorSystem.colorize("&2Auto-Crafter &8(Vanilla)"), "", ColorSystem.colorize("&fPlace this machine on top of a"), ColorSystem.colorize("&fchest or similar and make it craft"), ColorSystem.colorize("&fanything that can be crafted using a"), ColorSystem.colorize("&fnormal &eCrafting Table"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &e\u26A1 &716 J/Item"));
    public static final SlimefunItemStack ENHANCED_AUTO_CRAFTER = new SlimefunItemStack("ENHANCED_AUTO_CRAFTER", HeadTexture.ENHANCED_AUTO_CRAFTER, ColorSystem.colorize("&2Auto-Crafter &8(Enhanced)"), "", ColorSystem.colorize("&fPlace this machine on top of a"), ColorSystem.colorize("&fchest or similar and make it craft"), ColorSystem.colorize("&fanything that can be crafted using an"), ColorSystem.colorize("&eEnhanced Crafting Table"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &e\u26A1 &716 J/Item"));
    public static final SlimefunItemStack ARMOR_AUTO_CRAFTER = new SlimefunItemStack("ARMOR_AUTO_CRAFTER", HeadTexture.ARMOR_AUTO_CRAFTER, ColorSystem.colorize("&2Auto-Crafter &8(Armor Forge)"), "", ColorSystem.colorize("&fPlace this machine on top of a"), ColorSystem.colorize("&fchest or similar and make it craft"), ColorSystem.colorize("&fanything that can be crafted using an"), ColorSystem.colorize("&eArmor Forge"), "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &e\u26A1 &732 J/Item"));

    public static final SlimefunItemStack IRON_GOLEM_ASSEMBLER = new SlimefunItemStack("IRON_GOLEM_ASSEMBLER", Material.IRON_BLOCK, ColorSystem.colorize("&6Iron Golem Assembler"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Cooldown: &b30 Seconds"), LoreBuilder.powerBuffer(4096), ColorSystem.colorize("&8\u21E8 &e\u26A1 &72048 J/Golem"));
    public static final SlimefunItemStack WITHER_ASSEMBLER = new SlimefunItemStack("WITHER_ASSEMBLER", Material.OBSIDIAN, ColorSystem.colorize("&5Wither Assembler"), "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), ColorSystem.colorize("&8\u21E8 &7Cooldown: &b30 Seconds"), LoreBuilder.powerBuffer(4096), ColorSystem.colorize("&8\u21E8 &e\u26A1 &74096 J/Wither"));

    public static final SlimefunItemStack TRASH_CAN = new SlimefunItemStack("TRASH_CAN_BLOCK", HeadTexture.TRASH_CAN, ColorSystem.colorize("&3Trash Can"), "", ColorSystem.colorize("&fWill destroy all Items put into it"));

    public static final SlimefunItemStack ELYTRA_SCALE = new SlimefunItemStack("ELYTRA_SCALE", Material.FEATHER, ColorSystem.colorize("&bElytra Scale"));
    public static final SlimefunItemStack INFUSED_ELYTRA = new SlimefunItemStack("INFUSED_ELYTRA", Material.ELYTRA, ColorSystem.colorize("&5Infused Elytra"));
    public static final SlimefunItemStack SOULBOUND_ELYTRA = new SlimefunItemStack("SOULBOUND_ELYTRA", Material.ELYTRA, ColorSystem.colorize("&cSoulbound Elytra"));

    public static final SlimefunItemStack MAGNESIUM_SALT = new SlimefunItemStack("MAGNESIUM_SALT", Material.SUGAR, ColorSystem.colorize("&cMagnesium Salt"), "", ColorSystem.colorize("&7A special type of fuel that can be"), ColorSystem.colorize("&7used in a Magnesium-powered Generator"));
    public static final SlimefunItemStack MAGNESIUM_GENERATOR = new SlimefunItemStack("MAGNESIUM_GENERATOR", HeadTexture.GENERATOR, ColorSystem.colorize("&cMagnesium-powered Generator"), "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR), LoreBuilder.powerBuffer(128), LoreBuilder.powerPerSecond(36));

    static {
        INFUSED_ELYTRA.addUnsafeEnchantment(Enchantment.MENDING, 1);
    }
}
