package eu.mrneznamy.slimefuncontinued.common;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for working with player lists
 * Replaces io.github.bakedlibs.dough.common.PlayerList
 */
public final class PlayerList {

    private PlayerList() {}

    /**
     * Gets all online players
     * @return Collection of all online players
     */
    @Nonnull
    public static Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    /**
     * Gets all online player names
     * @return List of all online player names
     */
    @Nonnull
    public static List<String> getOnlinePlayerNames() {
        return getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    /**
     * Finds players whose names start with the given prefix
     * @param prefix The prefix to search for
     * @return List of matching player names
     */
    @Nonnull
    public static List<String> findPlayerNames(@Nonnull String prefix) {
        String lowerPrefix = prefix.toLowerCase();
        return getOnlinePlayerNames().stream()
                .filter(name -> name.toLowerCase().startsWith(lowerPrefix))
                .collect(Collectors.toList());
    }

    /**
     * Finds a player by name (case-insensitive)
     * @param name The player name
     * @return The player or null if not found
     */
    public static Player findPlayer(@Nonnull String name) {
        return Bukkit.getPlayerExact(name);
    }

    /**
     * Finds a player by name
     * @param name The player name
     * @return Optional containing the player if found, empty otherwise
     */
    public static Optional<Player> findByName(String name) {
        return Optional.ofNullable(findPlayer(name));
    }

    /**
     * Gets the number of online players
     * @return The number of online players
     */
    public static int getOnlinePlayerCount() {
        return getOnlinePlayers().size();
    }

    /**
     * Checks if a player is online
     * @param name The player name
     * @return True if the player is online
     */
    public static boolean isPlayerOnline(@Nonnull String name) {
        return findPlayer(name) != null;
    }

    /**
     * Gets a list of all online players as a list
     * @return List of all online players
     */
    @Nonnull
    public static List<Player> getOnlinePlayersAsList() {
        return new ArrayList<>(getOnlinePlayers());
    }
}
