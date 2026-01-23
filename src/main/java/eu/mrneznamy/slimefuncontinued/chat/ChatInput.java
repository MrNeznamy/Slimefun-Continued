package eu.mrneznamy.slimefuncontinued.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Utility for handling chat input from players
 * Replaces io.github.bakedlibs.dough.chat.ChatInput
 */
public class ChatInput implements Listener {

    private static final Map<UUID, ChatInputHandler> handlers = new ConcurrentHashMap<>();
    private static ChatInput instance;
    private static Plugin plugin;

    private ChatInput() {}

    /**
     * Initializes the ChatInput system
     * @param plugin The plugin instance
     */
    public static void initialize(@Nonnull Plugin plugin) {
        if (instance == null) {
            ChatInput.plugin = plugin;
            instance = new ChatInput();
            plugin.getServer().getPluginManager().registerEvents(instance, plugin);
        }
    }

    /**
     * Waits for the next chat message from a player
     * @param player The player to wait for
     * @param onInput Callback to handle the input
     */
    public static void waitForPlayer(@Nonnull Player player, @Nonnull Consumer<String> onInput) {
        waitForPlayer(player, onInput, null);
    }

    /**
     * Waits for the next chat message from a player
     * @param player The player to wait for
     * @param onInput Callback to handle the input
     * @param onCancel Callback to handle cancellation (player quit, etc.)
     */
    public static void waitForPlayer(@Nonnull Player player, @Nonnull Consumer<String> onInput, @Nonnull Consumer<Player> onCancel) {
        if (instance == null) {
            throw new IllegalStateException("ChatInput not initialized! Call ChatInput.initialize(plugin) first.");
        }

        handlers.put(player.getUniqueId(), new ChatInputHandler(onInput, onCancel));
    }

    /**
     * Cancels waiting for input from a player
     * @param player The player to cancel for
     */
    public static void cancel(@Nonnull Player player) {
        ChatInputHandler handler = handlers.remove(player.getUniqueId());
        if (handler != null && handler.onCancel != null) {
            handler.onCancel.accept(player);
        }
    }

    /**
     * Checks if we're waiting for input from a player
     * @param player The player to check
     * @return True if waiting for input
     */
    public static boolean isWaitingForInput(@Nonnull Player player) {
        return handlers.containsKey(player.getUniqueId());
    }

    /**
     * Shuts down the ChatInput system
     */
    public static void shutdown() {
        if (instance != null) {
            HandlerList.unregisterAll(instance);
            handlers.clear();
            instance = null;
            plugin = null;
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ChatInputHandler handler = handlers.remove(player.getUniqueId());
        
        if (handler != null) {
            event.setCancelled(true);
            
            // Run on main thread
            plugin.getServer().getScheduler().runTask(plugin, () -> {
                try {
                    handler.onInput.accept(event.getMessage());
                } catch (Exception e) {
                    plugin.getLogger().warning("Error handling chat input: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ChatInputHandler handler = handlers.remove(player.getUniqueId());
        
        if (handler != null && handler.onCancel != null) {
            try {
                handler.onCancel.accept(player);
            } catch (Exception e) {
                plugin.getLogger().warning("Error handling chat input cancellation: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Internal class to hold chat input handlers
     */
    private static class ChatInputHandler {
        final Consumer<String> onInput;
        final Consumer<Player> onCancel;

        ChatInputHandler(Consumer<String> onInput, Consumer<Player> onCancel) {
            this.onInput = onInput;
            this.onCancel = onCancel;
        }
    }
}
