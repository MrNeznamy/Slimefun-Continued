package eu.mrneznamy.slimefuncontinued.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import eu.mrneznamy.utils.ColorSystem;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * CustomItemStack implementation for slimefun5 - replaces Dough CustomItemStack
 */
public class CustomItemStack extends ItemStack {
    
    public CustomItemStack(Material material) {
        super(material);
    }
    
    public CustomItemStack(Material material, String name) {
        super(material);
        setDisplayName(name);
    }
    
    public CustomItemStack(Material material, String name, String... lore) {
        super(material);
        setDisplayName(name);
        setLore(Arrays.asList(lore));
    }
    
    public CustomItemStack(Material material, String name, List<String> lore) {
        super(material);
        setDisplayName(name);
        setLore(lore);
    }
    
    // Static factory methods to match Dough's API
    public static ItemStack create(Material material) {
        return new CustomItemStack(material);
    }
    
    public static ItemStack create(Material material, String name) {
        return new CustomItemStack(material, name);
    }
    
    public static ItemStack create(Material material, String name, String... lore) {
        return new CustomItemStack(material, name, lore);
    }
    
    public static ItemStack create(Material material, String name, List<String> lore) {
        return new CustomItemStack(material, name, lore);
    }
    
    // Create method with ItemStack and amount
    public static ItemStack create(ItemStack item, int amount) {
        ItemStack result = item.clone();
        result.setAmount(amount);
        return result;
    }
    
    // Create method with Consumer for ItemMeta modification
    public static ItemStack create(Material material, Consumer<ItemMeta> metaConsumer) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            metaConsumer.accept(meta);
            item.setItemMeta(meta);
        }
        return item;
    }
    
    // Create method with ItemStack base and Consumer for ItemMeta modification
    public static ItemStack create(ItemStack base, Consumer<ItemMeta> metaConsumer) {
        ItemStack item = base.clone();
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            metaConsumer.accept(meta);
            item.setItemMeta(meta);
        }
        return item;
    }
    
    // Create method with ItemStack and display name
    public static ItemStack create(ItemStack base, String name) {
        ItemStack item = base.clone();
        ItemMeta meta = item.getItemMeta();
        if (meta != null && name != null) {
            meta.setDisplayName(ColorSystem.colorize(name));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    // Create method with ItemStack, display name, and lore
    public static ItemStack create(ItemStack base, String name, String... lore) {
        ItemStack item = base.clone();
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            if (name != null) {
                meta.setDisplayName(ColorSystem.colorize(name));
            }
            if (lore != null && lore.length > 0) {
                List<String> coloredLore = Arrays.stream(lore)
                    .map(ColorSystem::colorize)
                    .toList();
                meta.setLore(coloredLore);
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    // Create method with ItemStack and lore list
    public static ItemStack create(ItemStack base, List<String> lore) {
        ItemStack item = base.clone();
        ItemMeta meta = item.getItemMeta();
        if (meta != null && lore != null && !lore.isEmpty()) {
            List<String> coloredLore = lore.stream()
                .map(ColorSystem::colorize)
                .toList();
            meta.setLore(coloredLore);
            item.setItemMeta(meta);
        }
        return item;
    }



    // Create method with Material and lore list
    public static ItemStack create(Material material, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null && lore != null && !lore.isEmpty()) {
            List<String> coloredLore = lore.stream()
                .map(ColorSystem::colorize)
                .toList();
            meta.setLore(coloredLore);
            item.setItemMeta(meta);
        }
        return item;
    }
    
    private void setDisplayName(String name) {
        if (name != null) {
            ItemMeta meta = getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ColorSystem.colorize(name));
                setItemMeta(meta);
            }
        }
    }
    
    public void setLore(List<String> lore) {
        if (lore != null && !lore.isEmpty()) {
            ItemMeta meta = getItemMeta();
            if (meta != null) {
                List<String> coloredLore = lore.stream()
                    .map(ColorSystem::colorize)
                    .toList();
                meta.setLore(coloredLore);
                setItemMeta(meta);
            }
        }
    }
}
