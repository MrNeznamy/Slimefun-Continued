package eu.mrneznamy.slimefuncontinued.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A three-state optional that can be TRUE, FALSE, or NOT_SET
 * Replaces io.github.bakedlibs.dough.data.TriStateOptional
 */
public final class TriStateOptional<T> {

    private final State state;
    private final T value;

    private TriStateOptional(@Nonnull State state, @Nullable T value) {
        this.state = state;
        this.value = value;
    }

    /**
     * Creates a TriStateOptional with a value (TRUE state)
     * @param value The value
     * @param <T> The type
     * @return A TriStateOptional with TRUE state
     */
    @Nonnull
    public static <T> TriStateOptional<T> of(@Nonnull T value) {
        return new TriStateOptional<>(State.TRUE, value);
    }

    /**
     * Creates a TriStateOptional with no value (FALSE state)
     * @param <T> The type
     * @return A TriStateOptional with FALSE state
     */
    @Nonnull
    public static <T> TriStateOptional<T> empty() {
        return new TriStateOptional<>(State.FALSE, null);
    }

    /**
     * Creates a TriStateOptional that is not set (NOT_SET state)
     * @param <T> The type
     * @return A TriStateOptional with NOT_SET state
     */
    @Nonnull
    public static <T> TriStateOptional<T> notSet() {
        return new TriStateOptional<>(State.NOT_SET, null);
    }

    /**
     * Creates a TriStateOptional from a nullable value
     * @param value The value (can be null)
     * @param <T> The type
     * @return A TriStateOptional with TRUE state if value is not null, FALSE otherwise
     */
    @Nonnull
    public static <T> TriStateOptional<T> ofNullable(@Nullable T value) {
        return value != null ? of(value) : empty();
    }

    /**
     * Creates a new empty TriStateOptional (alias for empty())
     * @param <T> The type
     * @return A TriStateOptional with FALSE state
     */
    @Nonnull
    public static <T> TriStateOptional<T> createNew() {
        return empty();
    }

    /**
     * Gets the current state
     * @return The state
     */
    @Nonnull
    public State getState() {
        return state;
    }

    /**
     * Checks if this has a value (TRUE state)
     * @return True if state is TRUE
     */
    public boolean isPresent() {
        return state == State.TRUE;
    }

    /**
     * Checks if this is empty (FALSE state)
     * @return True if state is FALSE
     */
    public boolean isEmpty() {
        return state == State.FALSE;
    }

    /**
     * Checks if this is not set (NOT_SET state)
     * @return True if state is NOT_SET
     */
    public boolean isNotSet() {
        return state == State.NOT_SET;
    }
    
    /**
     * Checks if this has been computed (not NOT_SET state)
     * @return True if state is not NOT_SET
     */
    public boolean isComputed() {
        return state != State.NOT_SET;
    }

    /**
     * Gets the value if present
     * @return The value or null
     */
    @Nullable
    public T get() {
        return value;
    }

    /**
     * Gets the value or a default if not present
     * @param defaultValue The default value
     * @return The value or default
     */
    @Nullable
    public T orElse(@Nullable T defaultValue) {
        return isPresent() ? value : defaultValue;
    }

    /**
     * Gets the value or gets it from a supplier if not present
     * @param supplier The supplier
     * @return The value or supplied value
     */
    @Nullable
    public T orElseGet(@Nonnull Supplier<? extends T> supplier) {
        return isPresent() ? value : supplier.get();
    }

    /**
     * Executes a consumer if present
     * @param consumer The consumer
     */
    public void ifPresent(@Nonnull Consumer<? super T> consumer) {
        if (isPresent()) {
            consumer.accept(value);
        }
    }

    /**
     * Executes a runnable if empty
     * @param runnable The runnable
     */
    public void ifEmpty(@Nonnull Runnable runnable) {
        if (isEmpty()) {
            runnable.run();
        }
    }

    /**
     * Executes a runnable if not set
     * @param runnable The runnable
     */
    public void ifNotSet(@Nonnull Runnable runnable) {
        if (isNotSet()) {
            runnable.run();
        }
    }

    /**
     * Maps the value if present
     * @param mapper The mapper function
     * @param <U> The new type
     * @return A new TriStateOptional with the mapped value
     */
    @Nonnull
    public <U> TriStateOptional<U> map(@Nonnull Function<? super T, ? extends U> mapper) {
        if (isPresent()) {
            return TriStateOptional.of(mapper.apply(value));
        } else if (isEmpty()) {
            return TriStateOptional.empty();
        } else {
            return TriStateOptional.notSet();
        }
    }

    /**
     * Converts to a regular Optional
     * @return An Optional containing the value if present, empty otherwise
     */
    @Nonnull
    public Optional<T> toOptional() {
        return isPresent() ? Optional.of(value) : Optional.empty();
    }
    
    /**
     * Computes and sets a value
     * @param value The value to set
     * @return A new TriStateOptional with the computed value
     */
    @Nonnull
    public TriStateOptional<T> compute(@Nullable T value) {
        return value != null ? TriStateOptional.of(value) : TriStateOptional.empty();
    }
    
    /**
     * Gets as Optional (alias for toOptional)
     * @return An Optional containing the value if present, empty otherwise
     */
    @Nonnull
    public Optional<T> getAsOptional() {
        return toOptional();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TriStateOptional)) return false;
        
        TriStateOptional<?> other = (TriStateOptional<?>) obj;
        return state == other.state && 
               (value == null ? other.value == null : value.equals(other.value));
    }

    @Override
    public int hashCode() {
        return state.hashCode() * 31 + (value != null ? value.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "TriStateOptional[" + state + (isPresent() ? ", " + value : "") + "]";
    }

    /**
     * The three possible states
     */
    public enum State {
        TRUE,
        FALSE,
        NOT_SET
    }
}
