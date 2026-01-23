package eu.mrneznamy.slimefuncontinued.blocks;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Represents a chunk position in a world
 * Replaces io.github.bakedlibs.dough.blocks.ChunkPosition
 */
public final class ChunkPosition {

    private final World world;
    private final int x;
    private final int z;

    /**
     * Creates a new ChunkPosition
     * @param world The world
     * @param x The chunk X coordinate
     * @param z The chunk Z coordinate
     */
    public ChunkPosition(@Nonnull World world, int x, int z) {
        this.world = Objects.requireNonNull(world, "World cannot be null");
        this.x = x;
        this.z = z;
    }

    /**
     * Creates a ChunkPosition from a Chunk
     * @param chunk The chunk
     */
    public ChunkPosition(@Nonnull Chunk chunk) {
        this(chunk.getWorld(), chunk.getX(), chunk.getZ());
    }

    /**
     * Creates a ChunkPosition from a Location
     * @param location The location
     */
    public ChunkPosition(@Nonnull Location location) {
        this(Objects.requireNonNull(location.getWorld(), "Location world cannot be null"),
             location.getBlockX() >> 4,
             location.getBlockZ() >> 4);
    }

    /**
     * Creates a ChunkPosition from a Block
     * @param block The block
     */
    public ChunkPosition(@Nonnull Block block) {
        this(block.getLocation());
    }

    /**
     * Gets the world
     * @return The world
     */
    @Nonnull
    public World getWorld() {
        return world;
    }

    /**
     * Gets the chunk X coordinate
     * @return The X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the chunk Z coordinate
     * @return The Z coordinate
     */
    public int getZ() {
        return z;
    }

    /**
     * Gets the chunk at this position
     * @return The chunk, or null if not loaded
     */
    @Nullable
    public Chunk getChunk() {
        if (world.isChunkLoaded(x, z)) {
            return world.getChunkAt(x, z);
        }
        return null;
    }

    /**
     * Loads and gets the chunk at this position
     * @return The chunk
     */
    @Nonnull
    public Chunk loadChunk() {
        return world.getChunkAt(x, z);
    }

    /**
     * Checks if the chunk is loaded
     * @return True if the chunk is loaded
     */
    public boolean isLoaded() {
        return world.isChunkLoaded(x, z);
    }

    /**
     * Loads the chunk if not already loaded
     */
    public void load() {
        if (!isLoaded()) {
            world.loadChunk(x, z);
        }
    }

    /**
     * Unloads the chunk
     */
    public void unload() {
        world.unloadChunk(x, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ChunkPosition that = (ChunkPosition) obj;
        return x == that.x && z == that.z && world.equals(that.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, z);
    }

    @Override
    public String toString() {
        return "ChunkPosition{world=" + world.getName() + ", x=" + x + ", z=" + z + "}";
    }
}
