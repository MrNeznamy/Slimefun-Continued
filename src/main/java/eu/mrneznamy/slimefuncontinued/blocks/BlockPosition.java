package eu.mrneznamy.slimefuncontinued.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Objects;

/**
 * BlockPosition implementation for slimefun5 - replaces Dough BlockPosition
 */
public class BlockPosition {
    
    private final String worldName;
    private final int x;
    private final int y;
    private final int z;
    
    public BlockPosition(World world, int x, int y, int z) {
        this.worldName = world.getName();
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public BlockPosition(Location location) {
        this(location.getWorld(), 
             location.getBlockX(), 
             location.getBlockY(), 
             location.getBlockZ());
    }
    
    public BlockPosition(Block block) {
        this(block.getLocation());
    }
    
    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }
    
    public String getWorldName() {
        return worldName;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getZ() {
        return z;
    }
    
    public Block getBlock() {
        World world = getWorld();
        return world != null ? world.getBlockAt(x, y, z) : null;
    }
    
    public Location toLocation() {
        World world = getWorld();
        return world != null ? new Location(world, x, y, z) : null;
    }
    
    public BlockPosition getPosition() {
        return this;
    }
    
    public BlockPosition add(int x, int y, int z) {
        return new BlockPosition(getWorld(), this.x + x, this.y + y, this.z + z);
    }
    
    public BlockPosition subtract(int x, int y, int z) {
        return new BlockPosition(getWorld(), this.x - x, this.y - y, this.z - z);
    }
    
    public double distance(BlockPosition other) {
        if (!Objects.equals(this.worldName, other.worldName)) {
            return Double.MAX_VALUE;
        }
        
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BlockPosition other)) return false;
        return x == other.x && y == other.y && z == other.z && 
               Objects.equals(worldName, other.worldName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(worldName, x, y, z);
    }
    
    @Override
    public String toString() {
        return String.format("BlockPosition{world=%s, x=%d, y=%d, z=%d}", 
                           worldName, x, y, z);
    }
    
    /**
     * Converts this BlockPosition to a long value for efficient storage and comparison.
     * This packs the x, y, z coordinates into a single long value.
     * Note: This method assumes coordinates are within reasonable bounds.
     */
    public long getAsLong() {
        // Pack coordinates into a long: x (21 bits) | y (12 bits) | z (21 bits)
        // This supports coordinates from -1048576 to 1048575 for x/z and 0-4095 for y
        long packed = 0L;
        packed |= (long)(x & 0x1FFFFF) << 42;  // x in bits 42-62
        packed |= (long)(y & 0xFFF) << 21;     // y in bits 21-32
        packed |= (long)(z & 0x1FFFFF);        // z in bits 0-20
        
        return packed;
    }

    /**
     * Converts a Location to a long value for efficient storage and comparison.
     * This packs the x, y, z coordinates into a single long value.
     * Note: This method assumes coordinates are within reasonable bounds.
     */
    public static long getAsLong(Location location) {
        if (location == null) {
            return 0L;
        }
        
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        
        // Pack coordinates into a long: x (21 bits) | y (12 bits) | z (21 bits)
        // This supports coordinates from -1048576 to 1048575 for x/z and 0-4095 for y
        long packed = 0L;
        packed |= (long)(x & 0x1FFFFF) << 42;  // x in bits 42-62
        packed |= (long)(y & 0xFFF) << 21;     // y in bits 21-32
        packed |= (long)(z & 0x1FFFFF);        // z in bits 0-20
        
        return packed;
    }
    
    /**
     * Converts a packed long back to coordinates.
     * Returns an array with [x, y, z] coordinates.
     */
    public static int[] fromLong(long packed) {
        int x = (int)((packed >> 42) & 0x1FFFFF);
        int y = (int)((packed >> 21) & 0xFFF);
        int z = (int)(packed & 0x1FFFFF);
        
        // Handle negative coordinates (sign extension)
        if (x >= 0x100000) x -= 0x200000;  // Convert from 21-bit to 32-bit signed
        if (z >= 0x100000) z -= 0x200000;  // Convert from 21-bit to 32-bit signed
        
        return new int[]{x, y, z};
    }
}
