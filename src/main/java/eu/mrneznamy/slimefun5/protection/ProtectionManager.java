package eu.mrneznamy.slimefun5.protection;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Manager for protection system integrations
 * Replaces io.github.bakedlibs.dough.protection.ProtectionManager
 */
public class ProtectionManager {

    /**
     * Checks if a player has permission to perform an interaction at a location
     * @param player The player
     * @param location The location
     * @param interaction The type of interaction
     * @return True if the player has permission
     */
    public boolean hasPermission(@Nullable OfflinePlayer player, @Nonnull Location location, @Nonnull Interaction interaction) {
        // Default implementation - always allow
        // This should be extended to integrate with protection plugins
        return true;
    }

    /**
     * Checks if a player has permission to perform an interaction on a block
     * @param player The player
     * @param block The block
     * @param interaction The type of interaction
     * @return True if the player has permission
     */
    public boolean hasPermission(@Nullable OfflinePlayer player, @Nonnull Block block, @Nonnull Interaction interaction) {
        return hasPermission(player, block.getLocation(), interaction);
    }

    /**
     * Checks if a player can build at a location
     * @param player The player
     * @param location The location
     * @return True if the player can build
     */
    public boolean canBuild(@Nullable OfflinePlayer player, @Nonnull Location location) {
        return hasPermission(player, location, Interaction.PLACE_BLOCK);
    }

    /**
     * Checks if a player can break a block
     * @param player The player
     * @param block The block
     * @return True if the player can break the block
     */
    public boolean canBreak(@Nullable OfflinePlayer player, @Nonnull Block block) {
        return hasPermission(player, block, Interaction.BREAK_BLOCK);
    }

    /**
     * Checks if a player can interact with a block
     * @param player The player
     * @param block The block
     * @return True if the player can interact with the block
     */
    public boolean canInteract(@Nullable OfflinePlayer player, @Nonnull Block block) {
        return hasPermission(player, block, Interaction.INTERACT_BLOCK);
    }

    /**
     * Checks if a player can access inventories at a location
     * @param player The player
     * @param location The location
     * @return True if the player can access inventories
     */
    public boolean canAccessInventories(@Nullable OfflinePlayer player, @Nonnull Location location) {
        return hasPermission(player, location, Interaction.ACCESS_INVENTORIES);
    }
}
