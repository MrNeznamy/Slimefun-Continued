package eu.mrneznamy.slimefuncontinued.skins;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

/**
 * PlayerSkin system for slimefun5 - replaces Dough PlayerSkin
 * Fixes IncompatibleClassChangeError with GameProfile in MC 1.21.10+
 */
public class PlayerSkin {
    private final String texture;
    private final String signature;
    private final String url;
    
    private PlayerSkin(String texture, String signature, String url) {
        this.texture = texture;
        this.signature = signature;
        this.url = url;
    }

    /**
     * Creates PlayerSkin from Base64 texture
     */
    public static PlayerSkin fromBase64(String texture) {
        return new PlayerSkin(texture, null, null);
    }
    
    /**
     * Creates PlayerSkin from URL
     */
    public static PlayerSkin fromURL(String url) {
        try {
            // Encode URL to Base64 format for Minecraft
            String json = "{\"textures\":{\"SKIN\":{\"url\":\"" + url + "\"}}}";
            String encoded = Base64.getEncoder().encodeToString(json.getBytes());
            return new PlayerSkin(encoded, null, url);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URL: " + url, e);
        }
    }

    /**
     * Creates PlayerSkin from hash code (texture hash)
     */
    public static PlayerSkin fromHashCode(String hash) {
        String url = "http://textures.minecraft.net/texture/" + hash;
        return fromURL(url);
    }
    
    /**
     * Creates PlayerSkin from hash code with UUID (for compatibility)
     */
    public static PlayerSkin fromHashCode(String uuid, String hash) {
        return fromHashCode(hash);
    }

    /**
     * Creates PlayerSkin from player UUID by fetching from Mojang API
     */
    public static PlayerSkin fromPlayerUUID(UUID uuid) {
        try {
            // First get the player profile from Mojang API
            String uuidString = uuid.toString().replace("-", "");
            URL profileUrl = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuidString);
            HttpURLConnection connection = (HttpURLConnection) profileUrl.openConnection();
            
            try {
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("User-Agent", "slimefun5/1.0");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {
                        
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
                        if (json.has("properties")) {
                            var properties = json.getAsJsonArray("properties");
                            for (var property : properties) {
                                var propertyObj = property.getAsJsonObject();
                                if ("textures".equals(propertyObj.get("name").getAsString())) {
                                    String texture = propertyObj.get("value").getAsString();
                                    String signature = propertyObj.has("signature") ? 
                                        propertyObj.get("signature").getAsString() : null;
                                    return new PlayerSkin(texture, signature, null);
                                }
                            }
                        }
                    }
                }
                
                // If we can't get the texture, return a default skin
                return fromBase64("");
            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            // Return empty skin on error
            return fromBase64("");
        }
    }

    /**
     * Converts PlayerSkin to ItemStack (player head)
     */
    public ItemStack getAsItemStack() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        
        if (meta != null) {
            // Use Paper API for texture setting
            PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
            setTextureViaProperty(profile);
            meta.setPlayerProfile(profile);
            skull.setItemMeta(meta);
        }
        
        return skull;
    }

    private void setTextureViaProperty(PlayerProfile profile) {
        if (texture != null) {
            ProfileProperty property = new ProfileProperty("textures", texture, signature);
            profile.getProperties().add(property);
        }
    }
    
    public String getTexture() {
        return texture;
    }

    public String getSignature() {
        return signature;
    }
    
    public String getUrl() {
        return url;
    }
    
    /**
     * Gets the PlayerProfile for this skin
     * @return The PlayerProfile with texture applied
     */
    public PlayerProfile getProfile() {
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
        setTextureViaProperty(profile);
        return profile;
    }
}
