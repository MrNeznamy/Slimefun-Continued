package eu.mrneznamy.slimefuncontinued.common;

import java.util.regex.Pattern;

/**
 * CommonPatterns implementation for slimefun5 - replaces Dough CommonPatterns
 */
public class CommonPatterns {
    
    public static final Pattern NUMERIC = Pattern.compile("\\d+");
    public static final Pattern ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9]+");
    public static final Pattern ALPHABETIC = Pattern.compile("[a-zA-Z]+");
    public static final Pattern LOWERCASE = Pattern.compile("[a-z]+");
    public static final Pattern UPPERCASE = Pattern.compile("[A-Z]+");
    public static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
    public static final Pattern MINECRAFT_USERNAME = Pattern.compile("^[a-zA-Z0-9_]{3,16}$");
    public static final Pattern HEX_COLOR = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    public static final Pattern DECIMAL = Pattern.compile("\\d+\\.\\d+");
    public static final Pattern INTEGER = Pattern.compile("-?\\d+");
    public static final Pattern POSITIVE_INTEGER = Pattern.compile("\\d+");
    public static final Pattern COLON = Pattern.compile(":");
    public static final Pattern SEMICOLON = Pattern.compile(";");
    public static final Pattern COMMA = Pattern.compile(",");
    public static final Pattern HASH = Pattern.compile("#");
    public static final Pattern NUMBER_SEPARATOR = Pattern.compile("[,.]");
    public static final Pattern HEXADECIMAL = Pattern.compile("[0-9a-fA-F]+");
    public static final Pattern ASCII = Pattern.compile("\\p{ASCII}*");
    public static final Pattern UNDERSCORE = Pattern.compile("_");
    public static final Pattern DASH = Pattern.compile("-");
    
    private CommonPatterns() {
        // Utility class
    }
    
    /**
     * Checks if string matches numeric pattern
     */
    public static boolean isNumeric(String input) {
        return input != null && NUMERIC.matcher(input).matches();
    }
    
    /**
     * Checks if string matches alphanumeric pattern
     */
    public static boolean isAlphanumeric(String input) {
        return input != null && ALPHANUMERIC.matcher(input).matches();
    }
    
    /**
     * Checks if string is a valid Minecraft username
     */
    public static boolean isValidMinecraftUsername(String input) {
        return input != null && MINECRAFT_USERNAME.matcher(input).matches();
    }
    
    /**
     * Checks if string is a valid UUID
     */
    public static boolean isValidUUID(String input) {
        return input != null && UUID.matcher(input).matches();
    }
    
    /**
     * Checks if string is a valid hex color
     */
    public static boolean isValidHexColor(String input) {
        return input != null && HEX_COLOR.matcher(input).matches();
    }
}
