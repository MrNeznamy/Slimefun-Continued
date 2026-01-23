package eu.mrneznamy.slimefuncontinued.config;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Config implementation for slimefun5 - replaces Dough Config
 */
public class Config {
    
    private final YamlConfiguration config;
    private final File file;
    
    /**
     * Creates a Config from a File object
     */
    public Config(File file) {
        this.file = file;
        // Ensure parent directories exist
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }
    
    /**
     * Creates a Config from a file path string
     */
    public Config(String filePath) {
        this(new File(filePath));
    }
    
    /**
     * Creates a Config from a plugin and filename
     */
    public Config(Plugin plugin, String fileName) {
        this(new File(plugin.getDataFolder(), fileName));
    }
    
    /**
     * Creates a Config from a plugin (uses config.yml)
     */
    public Config(Plugin plugin) {
        this(plugin, "config.yml");
    }
    
    public String getString(String path) {
        return config.getString(path);
    }
    
    public String getString(String path, String defaultValue) {
        return config.getString(path, defaultValue);
    }
    
    public int getInt(String path) {
        return config.getInt(path);
    }
    
    public int getInt(String path, int defaultValue) {
        return config.getInt(path, defaultValue);
    }
    
    public int getOrSetDefault(String path, int defaultValue) {
        if (!config.contains(path)) {
            config.set(path, defaultValue);
            try {
                save();
            } catch (IOException e) {
                // Log error but continue execution
                System.err.println("Failed to save config: " + e.getMessage());
            }
        }
        return config.getInt(path, defaultValue);
    }
    
    public boolean getOrSetDefault(String path, boolean defaultValue) {
        if (!config.contains(path)) {
            config.set(path, defaultValue);
            try {
                save();
            } catch (IOException e) {
                // Log error but continue execution
                System.err.println("Failed to save config: " + e.getMessage());
            }
        }
        return config.getBoolean(path, defaultValue);
    }
    
    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }
    
    public boolean getBoolean(String path, boolean defaultValue) {
        return config.getBoolean(path, defaultValue);
    }
    
    public double getDouble(String path) {
        return config.getDouble(path);
    }
    
    public double getDouble(String path, double defaultValue) {
        return config.getDouble(path, defaultValue);
    }
    
    public float getFloat(String path) {
        return (float) config.getDouble(path);
    }
    
    public float getFloat(String path, float defaultValue) {
        return (float) config.getDouble(path, defaultValue);
    }
    
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }
    
    public UUID getUUID(String path) {
        String uuidString = config.getString(path);
        if (uuidString == null) {
            return null;
        }
        try {
            return UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public UUID getUUID(String path, UUID defaultValue) {
        UUID uuid = getUUID(path);
        return uuid != null ? uuid : defaultValue;
    }
    
    public Set<String> getKeys() {
        return config.getKeys(false);
    }
    
    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }
    
    public Set<String> getKeys(String path) {
        if (config.getConfigurationSection(path) != null) {
            return config.getConfigurationSection(path).getKeys(false);
        }
        return new HashSet<>();
    }
    
    public void setValue(String path, Object value) {
        config.set(path, value);
    }
    
    public void setString(String path, String value) {
        config.set(path, value);
    }
    
    public void setInt(String path, int value) {
        config.set(path, value);
    }
    
    public void setBoolean(String path, boolean value) {
        config.set(path, value);
    }
    
    public void setDouble(String path, double value) {
        config.set(path, value);
    }
    
    public void setUUID(String path, UUID value) {
        config.set(path, value != null ? value.toString() : null);
    }
    
    public void setDefaultValue(String path, Object value) {
        if (!config.contains(path)) {
            config.set(path, value);
        }
    }
    
    public void setDefaultValue(String path, String value) {
        setDefaultValue(path, (Object) value);
    }
    
    public void setDefaultValue(String path, int value) {
        setDefaultValue(path, (Object) value);
    }
    
    public void setDefaultValue(String path, boolean value) {
        setDefaultValue(path, (Object) value);
    }
    
    public void setDefaultValue(String path, double value) {
        setDefaultValue(path, (Object) value);
    }
    
    public boolean contains(String path) {
        return config.contains(path);
    }
    
    public void save() throws IOException {
        config.save(file);
    }
    
    public void reload() {
        try {
            config.load(file);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reload config: " + file.getName(), e);
        }
    }
    
    public File getFile() {
        return file;
    }
    
    public YamlConfiguration getConfiguration() {
        return config;
    }

    /**
     * Gets an ItemStack from the config
     * @param path The path
     * @return The ItemStack or null
     */
    public ItemStack getItem(String path) {
        return config.getItemStack(path);
    }

    /**
     * Gets a Location from the config
     * @param path The path
     * @return The Location or null
     */
    public Location getLocation(String path) {
        return config.getLocation(path);
    }

    /**
     * Gets a value from the config
     * @param path The path
     * @return The value or null
     */
    public Object getValue(String path) {
        return config.get(path);
    }

    /**
     * Clears all values from the config
     */
    public void clear() {
        for (String key : config.getKeys(false)) {
            config.set(key, null);
        }
    }
}
