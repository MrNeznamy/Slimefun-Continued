package eu.mrneznamy.utils;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ColorSystem {
    
    private static final List<String> legacyColors = Arrays.asList("&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f", "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f");
    private static final List<String> specialChars = Arrays.asList("&l", "&n", "&o", "&k", "&m", "&r", "§l", "§n", "§o", "§k", "§m", "§r");
    
    private static final Pattern patternNormal = Pattern.compile("\\{#([0-9A-Fa-f]{6})\\}");
    private static final Pattern patternGrad = Pattern.compile("\\{#([0-9A-Fa-f]{6})>\\}(.*?)\\{#([0-9A-Fa-f]{6})<\\}");
    private static final Pattern patternGradReverse = Pattern.compile("\\{#([0-9A-Fa-f]{6})<\\}(.*?)\\{#([0-9A-Fa-f]{6})>\\}");
    private static final Pattern patternOneFromTwo = Pattern.compile("\\{#([0-9A-Fa-f]{6})<>\\}");
    
    // New patterns
    private static final Pattern patternAngleBrackets = Pattern.compile("<#([0-9A-Fa-f]{6})>");
    private static final Pattern patternHashOnly = Pattern.compile("(?<!\\{)(?<!<)#([0-9A-Fa-f]{6})(?!\\})(?!>)");
    private static final Pattern patternHtmlStyle = Pattern.compile("<#([0-9A-Fa-f]{6})>(.*?)</#([0-9A-Fa-f]{6})>");
    
    // ChatColors compatibility patterns
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private static final LegacyComponentSerializer SERIALIZER = 
        LegacyComponentSerializer.legacyAmpersand();
    
    private static boolean placeholderAPIAvailable = false;
    
    static {
        try {
            Class.forName("me.clip.placeholderapi.PlaceholderAPI");
            placeholderAPIAvailable = true;
        } catch (ClassNotFoundException e) {
            placeholderAPIAvailable = false;
        }
    }

    public static String colorize(String input, Player player) {
        if (input == null) return null;
        
        if (placeholderAPIAvailable && player != null) {
            input = applyPlaceholders(input, player);
        }
        
        input = colorizeGradient(input);
        input = colorizeRGB(input);
        input = colorizeNewFormats(input);
        input = ChatColor.translateAlternateColorCodes('&', input);
        
        return input;
    }
    
    public static String colorize(String input) {
        return colorize(input, null);
    }

    /**
     * ChatColors compatibility method - applies color codes to text
     */
    public static String color(String message) {
        if (message == null) return null;
        
        // Support for hex colors &#RRGGBB
        message = HEX_PATTERN.matcher(message)
            .replaceAll(match -> "§x§" + 
                String.join("§", match.group(1).split("")));
        
        // Standard color codes
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Converts text to Adventure Component
     */
    public static Component toComponent(String message) {
        return SERIALIZER.deserialize(color(message));
    }

    /**
     * Creates alternating colored text
     * @param text The text to color
     * @param color1 First color
     * @param color2 Second color
     * @return Colored text
     */
    public static String alternating(String text, org.bukkit.ChatColor color1, org.bukkit.ChatColor color2) {
        if (text == null) return null;
        StringBuilder result = new StringBuilder();
        boolean useFirst = true;
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                result.append(useFirst ? color1 : color2);
                useFirst = !useFirst;
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * Converts Adventure Component to text
     */
    public static String fromComponent(Component component) {
        return SERIALIZER.serialize(component);
    }
    
    /**
     * Checks if text contains color codes
     */
    public static boolean hasColors(String message) {
        if (message == null) return false;
        return message.contains("&") || message.contains("§");
    }

    private static String applyPlaceholders(String input, Player player) {
        try {
            return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, input);
        } catch (Exception e) {
            return input;
        }
    }

    private static String colorizeNewFormats(String input) {
        Matcher htmlMatcher = patternHtmlStyle.matcher(input);
        while (htmlMatcher.find()) {
            String startColor = htmlMatcher.group(1);
            String text = htmlMatcher.group(2);
            String endColor = htmlMatcher.group(3);
            
            if (startColor.equals(endColor)) {
                String colored = ChatColor.of("#" + startColor) + text;
                input = input.replace(htmlMatcher.group(), colored);
            } else {
                String gradient = color(text, new Color(Integer.parseInt(startColor, 16)), new Color(Integer.parseInt(endColor, 16)));
                input = input.replace(htmlMatcher.group(), gradient);
            }
        }
        
        Matcher angleMatcher = patternAngleBrackets.matcher(input);
        while (angleMatcher.find()) {
            String color = angleMatcher.group(1);
            input = input.replace(angleMatcher.group(), String.valueOf(getColor(color)));
        }
        
        Matcher hashMatcher = patternHashOnly.matcher(input);
        while (hashMatcher.find()) {
            String color = hashMatcher.group(1);
            input = input.replace("#" + color, String.valueOf(getColor(color)));
        }
        
        return input;
    }

    public static String sbcolorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String clear(String input) {
        input = removePatterns(input);
        input = removeLegacyColors(input);
        input = removeSpecialChars(input);
        return input;
    }
    
    /**
     * Alias for clear() - strips all color codes
     */
    public static String stripColor(String input) {
        return clear(input);
    }

    public static String removeSpecialChars(String input) {
        for (String chars : specialChars) {
            if (input.contains(chars)) {
                input = input.replaceAll(Pattern.quote(chars), "");
            }
        }
        return input;
    }

    public static String removeLegacyColors(String input) {
        for (String color : legacyColors) {
            if (input.contains(color)) {
                input = input.replaceAll(Pattern.quote(color), "");
            }
        }
        return input;
    }

    public static String removePatterns(String input) {
        input = input.replaceAll("\\{#([0-9A-Fa-f]{6})>\\}", "");
        input = input.replaceAll("\\{#([0-9A-Fa-f]{6})<\\}", "");
        input = input.replaceAll("\\{#([0-9A-Fa-f]{6})\\}", "");
        input = input.replaceAll("<#([0-9A-Fa-f]{6})>", "");
        input = input.replaceAll("</#([0-9A-Fa-f]{6})>", "");
        input = input.replaceAll("#([0-9A-Fa-f]{6})", "");
        return input;
    }

    public static String colorizeClassic(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String colorizeGradient(String input) {
        Matcher oneFromTwoMatcher = patternOneFromTwo.matcher(input);
        StringBuffer output = new StringBuffer();
        while (oneFromTwoMatcher.find()) {
            String text = oneFromTwoMatcher.group(1);
            oneFromTwoMatcher.appendReplacement(output, "{#" + text + "<}{#" + text + ">}");
        }
        oneFromTwoMatcher.appendTail(output);
        input = output.toString();
        
        Matcher gradMatcher = patternGrad.matcher(input);
        while (gradMatcher.find()) {
            String text = gradMatcher.group(2);
            Color startColor = new Color(Integer.parseInt(gradMatcher.group(1), 16));
            Color endColor = new Color(Integer.parseInt(gradMatcher.group(3), 16));
            input = input.replace(gradMatcher.group(), color(text, startColor, endColor));
        }
        
        Matcher gradReverseMatcher = patternGradReverse.matcher(input);
        while (gradReverseMatcher.find()) {
            String text = gradReverseMatcher.group(2);
            Color startColor = new Color(Integer.parseInt(gradReverseMatcher.group(1), 16));
            Color endColor = new Color(Integer.parseInt(gradReverseMatcher.group(3), 16));
            input = input.replace(gradReverseMatcher.group(), color(text, startColor, endColor));
        }
        
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String colorizeRGB(String input) {
        Matcher matcher = patternNormal.matcher(input);
        while (matcher.find()) {
            String color = matcher.group(1);
            input = input.replace(matcher.group(), String.valueOf(getColor(color)));
        }
        return input;
    }

    public static String color(String input, Color first, Color second) {
        ChatColor[] colors = createGradient(first, second, removeSpecialChars(input).length());
        return apply(input, colors);
    }

    private static String apply(String input, ChatColor[] colors) {
        StringBuilder specialColors = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        String[] characters = input.split("");
        int outIndex = 0;
        
        for (int i = 0; i < characters.length; i++) {
            if (!characters[i].equals("&") && !characters[i].equals("§")) {
                if (outIndex < colors.length) {
                    stringBuilder.append(colors[outIndex++]).append(specialColors).append(characters[i]);
                } else {
                    stringBuilder.append(characters[i]);
                }
                continue;
            }
            
            if (i + 1 >= characters.length) {
                if (outIndex < colors.length) {
                    stringBuilder.append(colors[outIndex++]).append(specialColors).append(characters[i]);
                } else {
                    stringBuilder.append(characters[i]);
                }
                continue;
            }
            
            if (characters[i + 1].equals("r")) {
                specialColors.setLength(0);
            } else {
                specialColors.append(characters[i]);
                specialColors.append(characters[i + 1]);
            }
            i++;
        }
        
        return stringBuilder.toString();
    }

    private static ChatColor[] createGradient(Color first, Color second, int amount) {
        if (amount <= 0) amount = 1;
        
        ChatColor[] colors = new ChatColor[amount];
        
        if (amount == 1) {
            colors[0] = ChatColor.of(first);
            return colors;
        }
        
        int amountR = Math.abs(first.getRed() - second.getRed()) / (amount - 1);
        int amountG = Math.abs(first.getGreen() - second.getGreen()) / (amount - 1);
        int amountB = Math.abs(first.getBlue() - second.getBlue()) / (amount - 1);
        
        int[] colorDir = new int[]{
            first.getRed() < second.getRed() ? 1 : -1, 
            first.getGreen() < second.getGreen() ? 1 : -1, 
            first.getBlue() < second.getBlue() ? 1 : -1
        };
        
        for (int i = 0; i < amount; i++) {
            int r = Math.max(0, Math.min(255, first.getRed() + amountR * i * colorDir[0]));
            int g = Math.max(0, Math.min(255, first.getGreen() + amountG * i * colorDir[1]));
            int b = Math.max(0, Math.min(255, first.getBlue() + amountB * i * colorDir[2]));
            
            colors[i] = ChatColor.of(new Color(r, g, b));
        }
        
        return colors;
    }

    public static ChatColor getColor(String hex) {
        return ChatColor.of(new Color(Integer.parseInt(hex, 16)));
    }
    
    public static boolean isPlaceholderAPIAvailable() {
        return placeholderAPIAvailable;
    }
}
