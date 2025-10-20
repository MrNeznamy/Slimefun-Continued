package eu.mrneznamy.slimefun5.skins;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Utility for looking up UUIDs from player names
 * Replaces io.github.bakedlibs.dough.skins.UUIDLookup
 */
public final class UUIDLookup {

    private static final String MOJANG_API_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final ConcurrentMap<String, UUID> cache = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Long> cacheTimestamps = new ConcurrentHashMap<>();
    private static final long CACHE_DURATION = 300000; // 5 minutes

    private UUIDLookup() {}

    /**
     * Looks up a UUID for a player name synchronously
     * @param playerName The player name
     * @return The UUID, or null if not found
     */
    @Nullable
    public static UUID getUUID(@Nonnull String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return null;
        }

        String normalizedName = playerName.toLowerCase();

        // Check cache first
        UUID cached = getCachedUUID(normalizedName);
        if (cached != null) {
            return cached;
        }

        // Check if player is online
        try {
            var onlinePlayer = Bukkit.getPlayer(playerName);
            if (onlinePlayer != null) {
                UUID uuid = onlinePlayer.getUniqueId();
                cache.put(normalizedName, uuid);
                cacheTimestamps.put(normalizedName, System.currentTimeMillis());
                return uuid;
            }
        } catch (Exception e) {
            // Ignore and continue with API lookup
        }

        // Lookup via Mojang API
        try {
            return lookupFromMojangAPI(playerName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Looks up a UUID for a player name asynchronously
     * @param playerName The player name
     * @return CompletableFuture with the UUID
     */
    @Nonnull
    public static CompletableFuture<UUID> getUUIDAsync(@Nonnull String playerName) {
        return CompletableFuture.supplyAsync(() -> getUUID(playerName));
    }

    /**
     * Gets a cached UUID if available and not expired
     * @param normalizedName The normalized player name
     * @return The cached UUID, or null if not cached or expired
     */
    @Nullable
    private static UUID getCachedUUID(@Nonnull String normalizedName) {
        Long timestamp = cacheTimestamps.get(normalizedName);
        if (timestamp != null && (System.currentTimeMillis() - timestamp) < CACHE_DURATION) {
            return cache.get(normalizedName);
        } else if (timestamp != null) {
            // Remove expired entry
            cache.remove(normalizedName);
            cacheTimestamps.remove(normalizedName);
        }
        return null;
    }

    /**
     * Looks up UUID from Mojang API
     * @param playerName The player name
     * @return The UUID, or null if not found
     * @throws IOException If API request fails
     */
    @Nullable
    private static UUID lookupFromMojangAPI(@Nonnull String playerName) throws IOException {
        URL url = new URL(MOJANG_API_URL + playerName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
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
                    String uuidString = json.get("id").getAsString();
                    
                    // Convert UUID string to proper UUID format
                    String formattedUuid = uuidString.replaceFirst(
                        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                        "$1-$2-$3-$4-$5"
                    );
                    
                    UUID uuid = UUID.fromString(formattedUuid);
                    
                    // Cache the result
                    String normalizedName = playerName.toLowerCase();
                    cache.put(normalizedName, uuid);
                    cacheTimestamps.put(normalizedName, System.currentTimeMillis());
                    
                    return uuid;
                }
            } else if (responseCode == 204) {
                // Player not found
                return null;
            } else {
                throw new IOException("HTTP " + responseCode + " from Mojang API");
            }
        } finally {
            connection.disconnect();
        }
    }

    /**
     * Clears the UUID cache
     */
    public static void clearCache() {
        cache.clear();
        cacheTimestamps.clear();
    }

    /**
     * Gets the current cache size
     * @return The number of cached entries
     */
    public static int getCacheSize() {
        return cache.size();
    }

    /**
     * Removes expired entries from the cache
     */
    public static void cleanupCache() {
        long currentTime = System.currentTimeMillis();
        cacheTimestamps.entrySet().removeIf(entry -> {
            if ((currentTime - entry.getValue()) >= CACHE_DURATION) {
                cache.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }

    /**
     * Gets a UUID from a username with plugin context (for compatibility)
     * @param plugin The plugin instance (not used but kept for API compatibility)
     * @param username The username to lookup
     * @return The UUID, or null if not found
     */
    @Nullable
    public static UUID getUuidFromUsername(@Nonnull Plugin plugin, @Nonnull String username) {
        return getUUID(username);
    }

    /**
     * Gets a UUID from a username asynchronously with plugin context
     * @param plugin The plugin instance (not used but kept for API compatibility)
     * @param username The username to lookup
     * @return CompletableFuture with the UUID
     */
    @Nonnull
    public static CompletableFuture<UUID> getUuidFromUsernameAsync(@Nonnull Plugin plugin, @Nonnull String username) {
        return getUUIDAsync(username);
    }
}
