package eu.mrneznamy.slimefun5.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

/**
 * Utility class for finding connected blocks (veins)
 * Replaces io.github.bakedlibs.dough.blocks.Vein
 */
public final class Vein {

    private static final BlockFace[] FACES = {
        BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST,
        BlockFace.UP, BlockFace.DOWN
    };

    private Vein() {}

    /**
     * Finds all connected blocks of the same type
     * @param block The starting block
     * @param maxSize The maximum number of blocks to find
     * @return Set of connected blocks
     */
    @Nonnull
    public static Set<Block> find(@Nonnull Block block, int maxSize) {
        return find(block, maxSize, b -> b.getType() == block.getType());
    }

    /**
     * Finds all connected blocks matching the predicate
     * @param block The starting block
     * @param maxSize The maximum number of blocks to find
     * @param predicate The predicate to match blocks
     * @return Set of connected blocks
     */
    @Nonnull
    public static Set<Block> find(@Nonnull Block block, int maxSize, @Nonnull Predicate<Block> predicate) {
        Set<Block> vein = new HashSet<>();
        Queue<Block> queue = new LinkedList<>();
        
        if (predicate.test(block)) {
            queue.add(block);
            vein.add(block);
        }
        
        while (!queue.isEmpty() && vein.size() < maxSize) {
            Block current = queue.poll();
            
            for (BlockFace face : FACES) {
                Block adjacent = current.getRelative(face);
                
                if (!vein.contains(adjacent) && predicate.test(adjacent)) {
                    vein.add(adjacent);
                    queue.add(adjacent);
                    
                    if (vein.size() >= maxSize) {
                        break;
                    }
                }
            }
        }
        
        return vein;
    }

    /**
     * Finds all connected blocks of the same material
     * @param block The starting block
     * @param material The material to match
     * @param maxSize The maximum number of blocks to find
     * @return Set of connected blocks
     */
    @Nonnull
    public static Set<Block> find(@Nonnull Block block, @Nonnull Material material, int maxSize) {
        return find(block, maxSize, b -> b.getType() == material);
    }

    /**
     * Finds all connected blocks of the same type (unlimited size)
     * @param block The starting block
     * @return Set of connected blocks
     */
    @Nonnull
    public static Set<Block> find(@Nonnull Block block) {
        return find(block, Integer.MAX_VALUE);
    }

    /**
     * Finds all connected blocks matching the predicate (unlimited size)
     * @param block The starting block
     * @param predicate The predicate to match blocks
     * @return Set of connected blocks
     */
    @Nonnull
    public static Set<Block> find(@Nonnull Block block, @Nonnull Predicate<Block> predicate) {
        return find(block, Integer.MAX_VALUE, predicate);
    }

    /**
     * Counts connected blocks of the same type
     * @param block The starting block
     * @param maxSize The maximum number of blocks to count
     * @return The number of connected blocks
     */
    public static int count(@Nonnull Block block, int maxSize) {
        return find(block, maxSize).size();
    }

    /**
     * Counts connected blocks matching the predicate
     * @param block The starting block
     * @param maxSize The maximum number of blocks to count
     * @param predicate The predicate to match blocks
     * @return The number of connected blocks
     */
    public static int count(@Nonnull Block block, int maxSize, @Nonnull Predicate<Block> predicate) {
        return find(block, maxSize, predicate).size();
    }
}
