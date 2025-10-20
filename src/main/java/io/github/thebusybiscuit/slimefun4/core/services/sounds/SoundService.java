package io.github.thebusybiscuit.slimefun4.core.services.sounds;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import eu.mrneznamy.slimefun5.config.Config;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

import com.google.common.base.Preconditions;

/**
 * The {@link SoundService} is responsible for our sound management.
 * It allows server owners to fully customize their users' sound experience.
 *
 * @author TheBusyBiscuit
 */
public class SoundService {

    /**
     * Our {@link Config} instance.
     */
    private final Config config;
    
    /**
     * The plugin instance.
     */
    private final Slimefun plugin;

    /**
     * In this map we cache the corresponding {@link SoundConfiguration} to each {@link SoundEffect}.
     */
    private final Map<SoundEffect, SoundConfiguration> soundMap = new EnumMap<>(SoundEffect.class);

    public SoundService(@Nonnull Slimefun plugin) {
        this.plugin = plugin;
        
        // Ensure the sounds.yml file exists by copying from resources if needed
        copyDefaultSoundsFile(plugin);
        
        try {
            config = new Config(plugin, "sounds.yml");

            // @formatter:off
            config.getConfiguration().options().header(
                "This file is used to assign the sounds which Slimefun will play.\n" +
                "You can fully customize any sound you want and even change their pitch\n" +
                "and volume. To disable a sound, simply set the volume to zero.\n"
            );
            // @formatter:on

            config.getConfiguration().options().copyHeader();
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to initialize SoundService config", e);
            throw new RuntimeException("Could not initialize SoundService", e);
        }
    }

    /**
     * Copies the default sounds.yml file from resources to the plugin's data folder if it doesn't exist.
     *
     * @param plugin The Slimefun plugin instance
     */
    private void copyDefaultSoundsFile(@Nonnull Slimefun plugin) {
        File soundsFile = new File(plugin.getDataFolder(), "sounds.yml");
        
        plugin.getLogger().log(Level.INFO, "Checking sounds.yml file at: " + soundsFile.getAbsolutePath());
        
        // If the file already exists, don't overwrite it
        if (soundsFile.exists()) {
            plugin.getLogger().log(Level.INFO, "sounds.yml already exists, skipping copy");
            return;
        }
        
        // Ensure the plugin data folder exists
        if (!plugin.getDataFolder().exists()) {
            boolean created = plugin.getDataFolder().mkdirs();
            plugin.getLogger().log(Level.INFO, "Created plugin data folder: " + created + " at " + plugin.getDataFolder().getAbsolutePath());
        }
        
        // Copy the default sounds.yml from resources
        try (InputStream inputStream = plugin.getClass().getResourceAsStream("/sounds.yml")) {
            if (inputStream != null) {
                Files.copy(inputStream, soundsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                plugin.getLogger().log(Level.INFO, "Successfully created default sounds.yml configuration file at: " + soundsFile.getAbsolutePath());
                
                // Verify the file was created and has content
                if (soundsFile.exists() && soundsFile.length() > 0) {
                    plugin.getLogger().log(Level.INFO, "Verified sounds.yml file exists and has content (" + soundsFile.length() + " bytes)");
                } else {
                    plugin.getLogger().log(Level.WARNING, "sounds.yml file was not created properly or is empty");
                }
            } else {
                plugin.getLogger().log(Level.WARNING, "Could not find default sounds.yml in resources");
                // Create a minimal sounds.yml file as fallback
                createMinimalSoundsFile(plugin, soundsFile);
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to copy default sounds.yml file", e);
            // Create a minimal sounds.yml file as fallback
            createMinimalSoundsFile(plugin, soundsFile);
        }
    }
    
    /**
     * Creates a minimal sounds.yml file as a fallback when the default cannot be copied from resources.
     *
     * @param plugin The Slimefun plugin instance
     * @param soundsFile The target sounds.yml file
     */
    private void createMinimalSoundsFile(@Nonnull Slimefun plugin, @Nonnull File soundsFile) {
        try {
            String minimalContent = "# Slimefun Sounds Configuration\n" +
                                  "# This file was created as a fallback when the default sounds.yml could not be loaded\n" +
                                  "\n" +
                                  "# Example sound configuration:\n" +
                                  "# SOUND_NAME:\n" +
                                  "#   sound: minecraft:sound_id\n" +
                                  "#   volume: 1.0\n" +
                                  "#   pitch: 1.0\n";
            
            Files.write(soundsFile.toPath(), minimalContent.getBytes());
            plugin.getLogger().log(Level.INFO, "Created minimal sounds.yml fallback file");
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to create minimal sounds.yml fallback file", e);
        }
    }

    /**
     * This method reloads every {@link SoundConfiguration}.
     *
     * @param save
     *            Whether to save the defaults to disk
     */
    public void reload(boolean save) {
        try {
            config.reload();
        } catch (Exception e) {
            Slimefun.logger().log(Level.SEVERE, "Failed to reload sounds.yml config file", e);
            
            // Try to recreate the sounds.yml file
            File soundsFile = new File(plugin.getDataFolder(), "sounds.yml");
            if (!soundsFile.exists()) {
                Slimefun.logger().log(Level.INFO, "sounds.yml file does not exist, attempting to recreate it");
                copyDefaultSoundsFile(plugin);
                
                // Try to reload again after recreating the file
                try {
                    config.reload();
                    Slimefun.logger().log(Level.INFO, "Successfully reloaded sounds.yml after recreation");
                } catch (Exception e2) {
                    Slimefun.logger().log(Level.SEVERE, "Failed to reload sounds.yml even after recreation", e2);
                    return; // Don't continue if we can't load the config
                }
            } else {
                Slimefun.logger().log(Level.SEVERE, "sounds.yml file exists but cannot be loaded, skipping sound configuration");
                return; // Don't continue if we can't load the config
            }
        }

        for (SoundEffect sound : SoundEffect.values()) {
            try {
                reloadSound(sound);
            } catch (Exception | LinkageError x) {
                Slimefun.logger().log(Level.SEVERE, x, () -> "An exception was thrown while trying to load the configuration data for the following sound:" + sound.name());
            }
        }

        if (save) {
            try {
                config.save();
            } catch (IOException e) {
                Slimefun.logger().log(Level.WARNING, "Failed to save sound config: " + e.getMessage());
            }
        }
    }

    private void reloadSound(@Nonnull SoundEffect sound) {
        // Set up default values
        config.setDefaultValue(sound.name() + ".sound", sound.getDefaultSoundId());
        config.setDefaultValue(sound.name() + ".volume", sound.getDefaultVolume());
        config.setDefaultValue(sound.name() + ".pitch", sound.getDefaultPitch());

        // Read the values
        String soundId = config.getString(sound.name() + ".sound");
        float volume = config.getFloat(sound.name() + ".volume");
        float pitch = config.getFloat(sound.name() + ".pitch");

        // Check whether the volume is at least 0.0
        if (volume < 0) {
            Slimefun.logger().log(Level.WARNING, "Invalid value in sounds.yml! Volume for Sound \"{0}\" was {1} (must be at least 0.0)", new Object[] { sound.name(), volume });
            volume = 0;
        }

        // Check if the pitch is at least 0.5
        if (pitch < 0.5F) {
            Slimefun.logger().log(Level.WARNING, "Invalid value in sounds.yml! Pitch for Sound \"{0}\" was {1} (must be at least 0.5)", new Object[] { sound.name(), pitch });
            pitch = 0.5F;
        }

        // Cache this configuration
        SoundConfiguration configuration = new SoundConfiguration(soundId, volume, pitch);
        soundMap.put(sound, configuration);
    }

    /**
     * This returns the currently used (immutable) {@link SoundConfiguration} for the given {@link SoundEffect}.
     *
     * @param sound
     *            The {@link SoundEffect}
     *
     * @return The corresponding {@link SoundConfiguration}. This may be null if something went wrong
     */
    public @Nullable SoundConfiguration getConfiguration(@Nonnull SoundEffect sound) {
        Preconditions.checkNotNull(sound, "The sound must not be null!");
        return soundMap.get(sound);
    }
}
