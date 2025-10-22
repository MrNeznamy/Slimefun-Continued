package io.github.thebusybiscuit.slimefun4.utils.messages;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.mrneznamy.utils.ColorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advanced message system with multiple display types (without placeholders)
 *
 * Supports:
 * (!message!) - Chat message
 * (!title!) - Title
 * (!subtitle!) - Subtitle
 * (!actionbar!) - ActionBar
 * (!hover:TEXT!) - Hover message
 * (!click:COMMAND!) - Click command
 * (!sound:SOUND!) - Play sound to player
 * (!close!) - Close player's inventory
 */
public class SlimefunMessage {
    
    private final JavaPlugin plugin;
    
    private static final Pattern TAG_PATTERN = Pattern.compile("\\((!?)([^!)]+)(!?)\\)");
    
    public SlimefunMessage(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Send message with tag parsing
     * 
     * @param player Player
     * @param message Message with tags
     * 
     * Example:
     * "(!message!)Hello (!hover:Click me!)world (!actionbar!)ActionBar text (!title!)Title text"
     */
    public void send(Player player, String message) {
        if (message == null || message.isEmpty()) {
            return;
        }
        
        MessageParser parser = new MessageParser(message, player);
        parser.parse();
    }
    
    /**
     * Send to all players
     */
    public void sendToAll(String message) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            send(player, message);
        }
    }
    
    /**
     * Send to players with permission
     */
    public void sendWithPermission(String message, String permission) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                send(player, message);
            }
        }
    }
    
    /**
     * Colorize text with basic color codes
     */
    private String colorize(String text) {
        if (text == null) return null;
        return ColorSystem.colorize(text);
    }
    
    /**
     * Message parser
     */
    private class MessageParser {
        private final String input;
        private final Player player;
        private String currentMode = "message";
        private List<MessagePart> parts = new ArrayList<>();
        private String pendingHover = null;
        private String pendingClick = null;
        private String pendingSound = null;
        private boolean shouldCloseInventory = false;
        
        public MessageParser(String input, Player player) {
            this.input = input;
            this.player = player;
        }
        
        public void parse() {
            Matcher matcher = TAG_PATTERN.matcher(input);
            int lastEnd = 0;
            
            while (matcher.find()) {
                // Text before tag
                if (matcher.start() > lastEnd) {
                    String text = input.substring(lastEnd, matcher.start());
                    if (!text.isEmpty()) {
                        MessagePart part = new MessagePart(currentMode, text, null);
                        applyPendingEffects(part);
                        parts.add(part);
                    }
                }
                
                // Parse tag
                String tag = matcher.group(2);
                parseTag(tag);
                
                lastEnd = matcher.end();
            }
            
            // Remaining text
            if (lastEnd < input.length()) {
                String text = input.substring(lastEnd);
                if (!text.isEmpty()) {
                    MessagePart part = new MessagePart(currentMode, text, null);
                    applyPendingEffects(part);
                    parts.add(part);
                }
            }
            
            // Execute parsed parts
            execute();
        }
        
        private void parseTag(String tag) {
            if (tag.startsWith("hover:")) {
                pendingHover = tag.substring(6);
            } else if (tag.startsWith("click:")) {
                pendingClick = tag.substring(6);
            } else if (tag.startsWith("sound:")) {
                pendingSound = tag.substring(6);
            } else if (tag.equalsIgnoreCase("close")) {
                shouldCloseInventory = true;
            } else {
                currentMode = tag.toLowerCase();
            }
        }
        
        private void applyPendingEffects(MessagePart part) {
            if (pendingHover != null) {
                part.hoverText = pendingHover;
                pendingHover = null;
            }
            if (pendingClick != null) {
                part.clickCommand = pendingClick;
                pendingClick = null;
            }
            if (pendingSound != null) {
                part.soundName = pendingSound;
                pendingSound = null;
            }
        }
        
        private void execute() {
            List<TextComponent> chatComponents = new ArrayList<>();
            String titleText = null;
            String subtitleText = null;
            String actionBarText = null;

            for (MessagePart part : parts) {
                String colored = colorize(part.text);

                switch (part.mode) {
                    case "message":
                        // Create separate TextComponent for each message part
                        TextComponent component = new TextComponent(colored);

                        // Add hover if present for this part
                        if (part.hoverText != null) {
                            String coloredHover = colorize(part.hoverText);
                            BaseComponent[] hoverComponents = TextComponent.fromLegacyText(coloredHover);
                            component.setHoverEvent(new HoverEvent(
                                HoverEvent.Action.SHOW_TEXT,
                                hoverComponents
                            ));
                        }

                        // Add click if present for this part
                        if (part.clickCommand != null) {
                            component.setClickEvent(new ClickEvent(
                                ClickEvent.Action.SUGGEST_COMMAND,
                                part.clickCommand
                            ));
                        }

                        chatComponents.add(component);
                        break;

                    case "title":
                        titleText = colored;
                        break;

                    case "subtitle":
                        subtitleText = colored;
                        break;

                    case "actionbar":
                        actionBarText = colored;
                        break;
                }
            }

            // Send chat message components
            if (!chatComponents.isEmpty()) {
                // Combine all components into one message
                BaseComponent[] messageComponents = new BaseComponent[chatComponents.size()];
                for (int i = 0; i < chatComponents.size(); i++) {
                    messageComponents[i] = chatComponents.get(i);
                }
                player.spigot().sendMessage(messageComponents);
            }
            
            // Send title
            if (titleText != null || subtitleText != null) {
                player.sendTitle(
                    titleText != null ? titleText : "",
                    subtitleText != null ? subtitleText : "",
                    10, 70, 20
                );
            }
            
            // Send actionbar
            if (actionBarText != null) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, 
                    TextComponent.fromLegacyText(actionBarText));
            }

            // Play sounds
            for (MessagePart part : parts) {
                if (part.soundName != null && !part.soundName.isEmpty()) {
                    try {
                        org.bukkit.Sound sound = org.bukkit.Sound.valueOf(part.soundName.toUpperCase());
                        player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
                    } catch (IllegalArgumentException e) {
                        // Invalid sound name, ignore
                    }
                }
            }
            
            // Close inventory if requested
            if (shouldCloseInventory) {
                player.closeInventory();
            }
        }
    }
    
    /**
     * Message part
     */
    private static class MessagePart {
        String mode;
        String text;
        String hoverText;
        String clickCommand;
        String soundName;

        MessagePart(String mode, String text, String hover) {
            this.mode = mode;
            this.text = text;
            this.hoverText = hover;
        }
    }
}
