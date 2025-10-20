package eu.mrneznamy.slimefun5.data.persistent;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Utility class for working with PersistentData
 * Replaces io.github.bakedlibs.dough.data.persistent.PersistentDataAPI
 */
public final class PersistentDataAPI {

    private PersistentDataAPI() {}

    /**
     * Sets a value in the persistent data container
     * @param holder The data holder
     * @param key The namespaced key
     * @param type The data type
     * @param value The value to set
     * @param <T> The primitive type
     * @param <Z> The complex type
     */
    public static <T, Z> void set(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, 
                                  @Nonnull PersistentDataType<T, Z> type, @Nonnull Z value) {
        holder.getPersistentDataContainer().set(key, type, value);
    }

    /**
     * Gets a value from the persistent data container
     * @param holder The data holder
     * @param key The namespaced key
     * @param type The data type
     * @param <T> The primitive type
     * @param <Z> The complex type
     * @return The value or null if not found
     */
    @Nullable
    public static <T, Z> Z get(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, 
                               @Nonnull PersistentDataType<T, Z> type) {
        return holder.getPersistentDataContainer().get(key, type);
    }

    /**
     * Gets a value from the persistent data container with a default
     * @param holder The data holder
     * @param key The namespaced key
     * @param type The data type
     * @param defaultValue The default value
     * @param <T> The primitive type
     * @param <Z> The complex type
     * @return The value or default if not found
     */
    @Nonnull
    public static <T, Z> Z getOrDefault(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, 
                                        @Nonnull PersistentDataType<T, Z> type, @Nonnull Z defaultValue) {
        Z value = get(holder, key, type);
        return value != null ? value : defaultValue;
    }

    /**
     * Checks if the persistent data container has a key
     * @param holder The data holder
     * @param key The namespaced key
     * @return True if the key exists
     */
    public static boolean has(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return holder.getPersistentDataContainer().has(key);
    }

    /**
     * Checks if the persistent data container has a key with a specific type
     * @param holder The data holder
     * @param key The namespaced key
     * @param type The data type
     * @param <T> The primitive type
     * @param <Z> The complex type
     * @return True if the key exists with the specified type
     */
    public static <T, Z> boolean has(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, 
                                     @Nonnull PersistentDataType<T, Z> type) {
        return holder.getPersistentDataContainer().has(key, type);
    }

    /**
     * Removes a key from the persistent data container
     * @param holder The data holder
     * @param key The namespaced key
     */
    public static void remove(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        holder.getPersistentDataContainer().remove(key);
    }

    /**
     * Gets the persistent data container from a holder
     * @param holder The data holder
     * @return The persistent data container
     */
    @Nonnull
    public static PersistentDataContainer getContainer(@Nonnull PersistentDataHolder holder) {
        return holder.getPersistentDataContainer();
    }

    /**
     * Sets a string value
     * @param holder The data holder
     * @param key The namespaced key
     * @param value The string value
     */
    public static void setString(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, @Nonnull String value) {
        set(holder, key, PersistentDataType.STRING, value);
    }

    /**
     * Gets a string value
     * @param holder The data holder
     * @param key The namespaced key
     * @return The string value or null if not found
     */
    @Nullable
    public static String getString(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return get(holder, key, PersistentDataType.STRING);
    }
    
    /**
     * Gets an optional string value
     * @param holder The data holder
     * @param key The namespaced key
     * @return Optional containing the string value if found, empty otherwise
     */
    @Nonnull
    public static Optional<String> getOptionalString(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return Optional.ofNullable(getString(holder, key));
    }

    /**
     * Sets an integer value
     * @param holder The data holder
     * @param key The namespaced key
     * @param value The integer value
     */
    public static void setInt(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, int value) {
        set(holder, key, PersistentDataType.INTEGER, value);
    }

    /**
     * Gets an integer value
     * @param holder The data holder
     * @param key The namespaced key
     * @return The integer value or 0 if not found
     */
    public static int getInt(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return getOrDefault(holder, key, PersistentDataType.INTEGER, 0);
    }

    /**
     * Sets a boolean value
     * @param holder The data holder
     * @param key The namespaced key
     * @param value The boolean value
     */
    public static void setBoolean(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, boolean value) {
        set(holder, key, PersistentDataType.BYTE, (byte) (value ? 1 : 0));
    }

    /**
     * Gets a boolean value
     * @param holder The data holder
     * @param key The namespaced key
     * @return The boolean value or false if not found
     */
    public static boolean getBoolean(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return getOrDefault(holder, key, PersistentDataType.BYTE, (byte) 0) == 1;
    }

    /**
     * Sets a byte value
     * @param holder The data holder
     * @param key The namespaced key
     * @param value The byte value
     */
    public static void setByte(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key, byte value) {
        set(holder, key, PersistentDataType.BYTE, value);
    }

    /**
     * Gets a byte value
     * @param holder The data holder
     * @param key The namespaced key
     * @return The byte value or 0 if not found
     */
    public static byte getByte(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return getOrDefault(holder, key, PersistentDataType.BYTE, (byte) 0);
    }

    /**
     * Checks if the persistent data container has a byte value
     * @param holder The data holder
     * @param key The namespaced key
     * @return True if the key exists with a byte value
     */
    public static boolean hasByte(@Nonnull PersistentDataHolder holder, @Nonnull NamespacedKey key) {
        return has(holder, key, PersistentDataType.BYTE);
    }
}
