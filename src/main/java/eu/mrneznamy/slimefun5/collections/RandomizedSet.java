package eu.mrneznamy.slimefun5.collections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A Set implementation that allows random element selection
 * Replaces io.github.bakedlibs.dough.collections.RandomizedSet
 */
public class RandomizedSet<T> implements Set<T> {

    private final List<T> elements;
    private final Set<T> set;
    private final Random random;

    /**
     * Creates a new RandomizedSet
     */
    public RandomizedSet() {
        this(ThreadLocalRandom.current());
    }

    /**
     * Creates a new RandomizedSet with a specific Random instance
     * @param random The Random instance to use
     */
    public RandomizedSet(@Nonnull Random random) {
        this.elements = new ArrayList<>();
        this.set = new HashSet<>();
        this.random = Objects.requireNonNull(random, "Random cannot be null");
    }

    /**
     * Gets a random element from the set
     * @return A random element, or null if the set is empty
     */
    @Nullable
    public T getRandom() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(random.nextInt(elements.size()));
    }

    /**
     * Gets a random element from the set
     * @param defaultValue The default value to return if the set is empty
     * @return A random element, or the default value if the set is empty
     */
    @Nullable
    public T getRandom(@Nullable T defaultValue) {
        if (elements.isEmpty()) {
            return defaultValue;
        }
        return elements.get(random.nextInt(elements.size()));
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return new ArrayList<>(elements).iterator();
    }

    @Override
    @Nonnull
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    @Nonnull
    public <U> U[] toArray(@Nonnull U[] a) {
        return elements.toArray(a);
    }

    @Override
    public boolean add(T t) {
        if (set.add(t)) {
            elements.add(t);
            return true;
        }
        return false;
    }

    /**
     * Adds an element with a specific weight (frequency)
     * Higher weight means the element is more likely to be selected
     * @param element The element to add
     * @param weight The weight/frequency of the element
     * @return True if the element was added
     */
    public boolean add(T element, int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        
        if (set.add(element)) {
            // Add the element multiple times based on weight
            for (int i = 0; i < weight; i++) {
                elements.add(element);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (set.remove(o)) {
            elements.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(@Nonnull Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(@Nonnull Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(@Nonnull Collection<?> c) {
        boolean modified = false;
        Iterator<T> iterator = elements.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (!c.contains(element)) {
                iterator.remove();
                set.remove(element);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(@Nonnull Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        elements.clear();
        set.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        RandomizedSet<?> that = (RandomizedSet<?>) obj;
        return set.equals(that.set);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        return set.toString();
    }
}
