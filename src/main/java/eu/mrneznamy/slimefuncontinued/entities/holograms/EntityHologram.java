package eu.mrneznamy.slimefuncontinued.entities.holograms;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import eu.mrneznamy.slimefuncontinued.blocks.BlockPosition;

/**
 * This represents a {@link TextDisplay} entity that can expire and be renamed.
 * This is a more stable and resource-efficient alternative to ArmorStand-based holograms.
 * 
 * @author TheBusyBiscuit
 * @author NEZNAMY
 */
public class EntityHologram {

    /**
     * This is the minimum duration after which the {@link EntityHologram} will expire.
     */
    private static final long EXPIRES_AFTER = TimeUnit.MINUTES.toMillis(10);

    /**
     * The {@link UUID} of the {@link TextDisplay} entity.
     */
    private final UUID uniqueId;

    /**
     * The {@link BlockPosition} where this hologram is located.
     */
    private final BlockPosition position;

    /**
     * The timestamp of when the {@link TextDisplay} was last accessed.
     */
    private long lastAccess;

    /**
     * The label of this {@link EntityHologram}.
     */
    private String label;

    /**
     * This creates a new {@link EntityHologram} for the given {@link UUID}.
     * 
     * @param uniqueId
     *            The {@link UUID} of the corresponding {@link TextDisplay}
     * @param position
     *            The {@link BlockPosition} where this hologram is located
     */
    public EntityHologram(@Nonnull UUID uniqueId, @Nonnull BlockPosition position) {
        this.uniqueId = uniqueId;
        this.position = position;
        this.lastAccess = System.currentTimeMillis();
    }

    /**
     * This returns the corresponding {@link TextDisplay}
     * and also updates the "lastAccess" timestamp.
     * <p>
     * If the {@link TextDisplay} was removed, it will return null.
     * 
     * @return The {@link TextDisplay} or null.
     */
    @Nullable
    public TextDisplay getTextDisplay() {
        Entity entity = Bukkit.getEntity(uniqueId);

        if (entity instanceof TextDisplay textDisplay && entity.isValid()) {
            this.lastAccess = System.currentTimeMillis();
            return textDisplay;
        } else {
            this.lastAccess = 0;
            return null;
        }
    }

    /**
     * This checks if the associated {@link TextDisplay} has despawned.
     * 
     * @return Whether the {@link TextDisplay} despawned
     */
    public boolean hasDespawned() {
        return getTextDisplay() == null;
    }

    /**
     * This returns whether this {@link EntityHologram} has expired.
     * The entity will expire if the last access has been more than 10
     * minutes ago.
     * 
     * @return Whether this {@link EntityHologram} has expired
     */
    public boolean hasExpired() {
        return System.currentTimeMillis() - lastAccess > EXPIRES_AFTER;
    }

    /**
     * This method sets the label of this {@link EntityHologram}.
     * 
     * @param label
     *            The label to set
     */
    public void setLabel(@Nullable String label) {
        if (Objects.equals(this.label, label)) {
            /*
             * Label is already set, no need to cause an entity
             * update. But we can update the lastAccess flag.
             */
            this.lastAccess = System.currentTimeMillis();
        } else {
            this.label = label;
            TextDisplay entity = getTextDisplay();

            if (entity != null) {
                if (label != null && !label.isEmpty()) {
                    entity.text(net.kyori.adventure.text.Component.text(label));
                    entity.setBillboard(Display.Billboard.CENTER);
                    entity.setSeeThrough(false);
                } else {
                    entity.text(net.kyori.adventure.text.Component.empty());
                }
            }
        }
    }

    /**
     * This method gets the current label of this {@link EntityHologram}.
     * 
     * @return The current label or null if not set
     */
    @Nullable
    public String getLabel() {
        return this.label;
    }

    /**
     * This method sets the scale of this {@link EntityHologram}.
     * 
     * @param scale
     *            The scale to set (1.0 = normal size)
     */
    public void setScale(float scale) {
        TextDisplay entity = getTextDisplay();
        if (entity != null) {
            // Create a new transformation with the specified scale
            org.bukkit.util.Transformation transformation = entity.getTransformation();
            org.joml.Vector3f scaleVector = new org.joml.Vector3f(scale, scale, scale);
            org.bukkit.util.Transformation newTransformation = new org.bukkit.util.Transformation(
                transformation.getTranslation(),
                transformation.getLeftRotation(),
                scaleVector,
                transformation.getRightRotation()
            );
            entity.setTransformation(newTransformation);
            this.lastAccess = System.currentTimeMillis();
        }
    }

    /**
     * This method sets the background color of this {@link EntityHologram}.
     * 
     * @param color
     *            The background color in ARGB format (0 = transparent)
     */
    public void setBackgroundColor(int color) {
        TextDisplay entity = getTextDisplay();
        if (entity != null) {
            entity.setBackgroundColor(org.bukkit.Color.fromARGB(color));
            this.lastAccess = System.currentTimeMillis();
        }
    }

    /**
     * This method sets the text opacity of this {@link EntityHologram}.
     * 
     * @param opacity
     *            The text opacity (0-255, where 255 is fully opaque)
     */
    public void setTextOpacity(byte opacity) {
        TextDisplay entity = getTextDisplay();
        if (entity != null) {
            entity.setTextOpacity(opacity);
            this.lastAccess = System.currentTimeMillis();
        }
    }

    /**
     * This will remove the {@link TextDisplay} and expire this {@link EntityHologram}.
     */
    public void remove() {
        TextDisplay textDisplay = getTextDisplay();

        if (textDisplay != null) {
            lastAccess = 0;
            textDisplay.remove();
        }
    }

    /**
     * This returns the {@link BlockPosition} where this hologram is located.
     * 
     * @return The {@link BlockPosition}
     */
    @Nonnull
    public BlockPosition getPosition() {
        return position;
    }

    /**
     * This returns the {@link UUID} of the {@link TextDisplay} entity.
     * 
     * @return The {@link UUID}
     */
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    /**
     * This returns the {@link Location} of the {@link TextDisplay} entity.
     * 
     * @return The {@link Location} or null if the entity has despawned
     */
    @Nullable
    public Location getLocation() {
        TextDisplay entity = getTextDisplay();
        return entity != null ? entity.getLocation() : null;
    }

    /**
     * This method checks if this hologram has persistent data with the given key.
     * 
     * @param container
     *            The {@link PersistentDataContainer} to check
     * @param position
     *            The {@link BlockPosition} to verify
     * @param key
     *            The persistent data key
     * 
     * @return Whether the hologram has the correct persistent data
     */
    public static boolean hasHologramData(@Nonnull PersistentDataContainer container, 
                                         @Nonnull BlockPosition position, 
                                         @Nonnull org.bukkit.NamespacedKey key) {
        if (container.has(key, PersistentDataType.LONG)) {
            long value = container.get(key, PersistentDataType.LONG);
            return value == position.getAsLong();
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EntityHologram that = (EntityHologram) obj;
        return Objects.equals(uniqueId, that.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }

    @Override
    public String toString() {
        return "EntityHologram{" +
                "uniqueId=" + uniqueId +
                ", position=" + position +
                ", label='" + label + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }
}
