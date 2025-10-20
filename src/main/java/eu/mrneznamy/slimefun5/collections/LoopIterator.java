package eu.mrneznamy.slimefun5.collections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * An iterator that loops infinitely through a collection
 * Replaces io.github.bakedlibs.dough.collections.LoopIterator
 */
public class LoopIterator<T> implements Iterator<T> {

    private final List<T> elements;
    private int currentIndex;

    /**
     * Creates a new LoopIterator from a collection
     * @param collection The collection to iterate over
     */
    public LoopIterator(@Nonnull Collection<T> collection) {
        this.elements = new ArrayList<>(Objects.requireNonNull(collection, "Collection cannot be null"));
        this.currentIndex = 0;
    }

    /**
     * Creates a new LoopIterator from an array
     * @param array The array to iterate over
     */
    @SafeVarargs
    public LoopIterator(@Nonnull T... array) {
        this.elements = Arrays.asList(Objects.requireNonNull(array, "Array cannot be null"));
        this.currentIndex = 0;
    }

    /**
     * Always returns true as this iterator loops infinitely
     * @return Always true
     */
    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    /**
     * Gets the next element, looping back to the beginning if necessary
     * @return The next element
     * @throws NoSuchElementException if the collection is empty
     */
    @Override
    @Nullable
    public T next() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Cannot iterate over empty collection");
        }

        T element = elements.get(currentIndex);
        currentIndex = (currentIndex + 1) % elements.size();
        return element;
    }

    /**
     * Gets the current element without advancing the iterator
     * @return The current element, or null if the collection is empty
     */
    @Nullable
    public T current() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(currentIndex);
    }

    /**
     * Gets the previous element without changing the iterator position
     * @return The previous element, or null if the collection is empty
     */
    @Nullable
    public T previous() {
        if (elements.isEmpty()) {
            return null;
        }
        int prevIndex = (currentIndex - 1 + elements.size()) % elements.size();
        return elements.get(prevIndex);
    }

    /**
     * Resets the iterator to the beginning
     */
    public void reset() {
        currentIndex = 0;
    }

    /**
     * Gets the current index
     * @return The current index
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Sets the current index
     * @param index The new index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void setCurrentIndex(int index) {
        if (elements.isEmpty()) {
            throw new IndexOutOfBoundsException("Cannot set index on empty collection");
        }
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + elements.size());
        }
        this.currentIndex = index;
    }

    /**
     * Gets the size of the collection
     * @return The size of the collection
     */
    public int size() {
        return elements.size();
    }

    /**
     * Checks if the collection is empty
     * @return True if the collection is empty
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Gets a copy of the elements list
     * @return A copy of the elements
     */
    @Nonnull
    public List<T> getElements() {
        return new ArrayList<>(elements);
    }

    @Override
    public String toString() {
        return "LoopIterator{elements=" + elements + ", currentIndex=" + currentIndex + "}";
    }
}
