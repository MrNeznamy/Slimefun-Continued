package eu.mrneznamy.slimefuncontinued.collections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A Map that wraps values in Optional
 * Replaces io.github.bakedlibs.dough.collections.OptionalMap
 */
public class OptionalMap<K, V> {

    private final Map<K, V> map;

    public OptionalMap() {
        this.map = new HashMap<>();
    }

    public OptionalMap(@Nonnull Map<K, V> map) {
        this.map = new HashMap<>(map);
    }

    /**
     * Gets a value wrapped in Optional
     * @param key The key
     * @return Optional containing the value or empty
     */
    @Nonnull
    public Optional<V> get(@Nonnull K key) {
        return Optional.ofNullable(map.get(key));
    }

    /**
     * Puts a value in the map
     * @param key The key
     * @param value The value
     * @return Optional containing the previous value or empty
     */
    @Nonnull
    public Optional<V> put(@Nonnull K key, @Nullable V value) {
        return Optional.ofNullable(map.put(key, value));
    }

    /**
     * Removes a value from the map
     * @param key The key
     * @return Optional containing the removed value or empty
     */
    @Nonnull
    public Optional<V> remove(@Nonnull K key) {
        return Optional.ofNullable(map.remove(key));
    }

    /**
     * Checks if the map contains a key
     * @param key The key
     * @return True if the key exists
     */
    public boolean containsKey(@Nonnull K key) {
        return map.containsKey(key);
    }

    /**
     * Checks if the map contains a value
     * @param value The value
     * @return True if the value exists
     */
    public boolean containsValue(@Nullable V value) {
        return map.containsValue(value);
    }

    /**
     * Gets the size of the map
     * @return The size
     */
    public int size() {
        return map.size();
    }

    /**
     * Checks if the map is empty
     * @return True if empty
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Clears the map
     */
    public void clear() {
        map.clear();
    }

    /**
     * Gets the key set
     * @return The key set
     */
    @Nonnull
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Gets the values
     * @return The values
     */
    @Nonnull
    public Collection<V> values() {
        return map.values();
    }

    /**
     * Gets the entry set
     * @return The entry set
     */
    @Nonnull
    public Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * Puts a value if the key is absent
     * @param key The key
     * @param value The value
     * @return Optional containing the previous value or empty
     */
    @Nonnull
    public Optional<V> putIfAbsent(@Nonnull K key, @Nullable V value) {
        return Optional.ofNullable(map.putIfAbsent(key, value));
    }

    /**
     * Computes a value if absent
     * @param key The key
     * @param mappingFunction The mapping function
     * @return Optional containing the computed or existing value
     */
    @Nonnull
    public Optional<V> computeIfAbsent(@Nonnull K key, @Nonnull Function<? super K, ? extends V> mappingFunction) {
        return Optional.ofNullable(map.computeIfAbsent(key, mappingFunction));
    }

    /**
     * Computes a value if present
     * @param key The key
     * @param remappingFunction The remapping function
     * @return Optional containing the computed value or empty
     */
    @Nonnull
    public Optional<V> computeIfPresent(@Nonnull K key, @Nonnull java.util.function.BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return Optional.ofNullable(map.computeIfPresent(key, remappingFunction));
    }

    /**
     * Performs an action for each entry
     * @param action The action to perform
     */
    public void forEach(@Nonnull BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    /**
     * Gets the underlying map
     * @return The underlying map
     */
    @Nonnull
    public Map<K, V> getMap() {
        return new HashMap<>(map);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OptionalMap)) return false;
        OptionalMap<?, ?> other = (OptionalMap<?, ?>) obj;
        return map.equals(other.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return "OptionalMap" + map.toString();
    }

    @FunctionalInterface
    public interface BiFunction<T, U, R> {
        R apply(T t, U u);
    }
}
