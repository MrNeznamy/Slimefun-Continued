package eu.mrneznamy.slimefun5.skins;

import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;

/**
 * PlayerHead utility class for slimefun5 - replaces Dough PlayerHead
 */
public class PlayerHead {
    
    /**
     * Creates player head from PlayerSkin
     */
    public static ItemStack getItemStack(PlayerSkin skin) {
        return skin.getAsItemStack();
    }
    
    /**
     * Creates player head from Base64 texture
     */
    public static ItemStack getItemStack(String texture) {
        return PlayerSkin.fromBase64(texture).getAsItemStack();
    }
    
    /**
     * Creates player head from URL
     */
    public static ItemStack getItemStackFromURL(String url) {
        return PlayerSkin.fromURL(url).getAsItemStack();
    }
    
    /**
     * Sets the skin of a player head block
     * @param block The block to set the skin on
     * @param skin The PlayerSkin to apply
     * @param update Whether to update the block state
     */
    public static void setSkin(Block block, PlayerSkin skin, boolean update) {
        if (block.getState() instanceof Skull skull) {
            skull.setPlayerProfile(skin.getProfile());
            if (update) {
                skull.update();
            }
        }
    }
}
