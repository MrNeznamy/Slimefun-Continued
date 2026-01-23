package eu.mrneznamy.slimefuncontinued.collections;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

/**
 * A Map implementation that uses a key extractor function
 * Replaces io.github.bakedlibs.dough.collections.KeyMap
 */
public class KeyMap<V> implements Map<NamespacedKey, V> {

    private final Map<NamespacedKey, V> map;
    private final Function<V, NamespacedKey> keyExtractor;

    /**
     * Creates a new KeyMap for Keyed objects
     * This constructor is used when V extends Keyed
     */
    @SuppressWarnings("unchecked")
    public KeyMap() {
        this.map = new HashMap<>();
        // Default key extractor for Keyed objects
        this.keyExtractor = value -> {
            if (value instanceof Keyed) {
                return ((Keyed) value).getKey();
            }
            throw new IllegalArgumentException("Value must implement Keyed interface");
        };
    }

    /**
     * Creates a new KeyMap
     * @param keyExtractor Function to extract keys from values
     */
    public KeyMap(@Nonnull Function<V, NamespacedKey> keyExtractor) {
        this.map = new HashMap<>();
        this.keyExtractor = Objects.requireNonNull(keyExtractor, "Key extractor cannot be null");
    }

    /**
     * Creates a new KeyMap with a specific Map implementation
     * @param map The underlying map implementation
     * @param keyExtractor Function to extract keys from values
     */
    public KeyMap(@Nonnull Map<NamespacedKey, V> map, @Nonnull Function<V, NamespacedKey> keyExtractor) {
        this.map = Objects.requireNonNull(map, "Map cannot be null");
        this.keyExtractor = Objects.requireNonNull(keyExtractor, "Key extractor cannot be null");
    }

    /**
     * Puts a value using the key extracted from the value
     * @param value The value to put
     * @return The previous value associated with the extracted key
     */
    @Nullable
    public V put(@Nonnull V value) {
        NamespacedKey key = keyExtractor.apply(value);
        return map.put(key, value);
    }

    /**
     * Puts all values using keys extracted from the values
     * @param values The values to put
     */
    public void putAll(@Nonnull Collection<V> values) {
        for (V value : values) {
            put(value);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    @Nullable
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    @Nullable
    public V put(NamespacedKey key, V value) {
        return map.put(key, value);
    }

    @Override
    @Nullable
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@Nonnull Map<? extends NamespacedKey, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    @Nonnull
    public Set<NamespacedKey> keySet() {
        return map.keySet();
    }

    @Override
    @Nonnull
    public Collection<V> values() {
        return map.values();
    }

    @Override
    @Nonnull
    public Set<Entry<NamespacedKey, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        KeyMap<?> keyMap = (KeyMap<?>) obj;
        return map.equals(keyMap.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    /**
     * Gets the key extractor function
     * @return The key extractor function
     */
    @Nonnull
    public Function<V, NamespacedKey> getKeyExtractor() {
        return keyExtractor;
    }

    /**
     * Gets the underlying map
     * @return The underlying map
     */
    @Nonnull
    public Map<NamespacedKey, V> getMap() {
        return map;
    }
}
