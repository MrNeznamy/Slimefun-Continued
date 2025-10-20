package eu.mrneznamy.utils.compatibility;

import org.bukkit.attribute.Attribute;

/**
 * Provides version-safe access to attribute constants across Minecraft versions.
 *
 * In 1.21+, attribute constants like MAX_HEALTH were renamed (the GENERIC_ prefix was removed).
 * To support both 1.20.x and 1.21.x at runtime, use this helper to resolve the appropriate
 * attribute name without creating a hard compile-time link to a constant that may not exist
 * on older servers.
 */
public final class VersionedAttribute {

    private VersionedAttribute() {}

    /**
     * Returns the MAX_HEALTH attribute constant in a version-safe way.
     * On 1.21+, this returns Attribute.MAX_HEALTH; on 1.20.x, it falls back to
     * Attribute.GENERIC_MAX_HEALTH.
     */
    public static Attribute MAX_HEALTH() {
        try {
            // 1.21+ (no GENERIC_ prefix)
            return Attribute.valueOf("MAX_HEALTH");
        } catch (IllegalArgumentException ex) {
            // 1.20.x and below (legacy name with GENERIC_ prefix)
            return Attribute.valueOf("GENERIC_MAX_HEALTH");
        }
    }
}
