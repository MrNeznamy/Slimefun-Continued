package io.github.thebusybiscuit.slimefun4.api.network;

import javax.annotation.Nonnull;

import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;

import io.github.thebusybiscuit.slimefun4.utils.compatibility.VersionedParticle;

/**
 * This class represents the visualizer task of a given {@link Network}.
 * 
 * @author TheBusyBiscuit
 *
 */
class NetworkVisualizer implements Runnable {

    /**
     * This is our {@link Network} instance.
     */
    private final Network network;

    /**
     * This creates a new {@link NetworkVisualizer} for the given {@link Network}.
     * 
     * @param network
     *            The {@link Network} to visualize
     * @param color
     *            The color parameter (kept for compatibility but not used)
     */
    NetworkVisualizer(@Nonnull Network network, @Nonnull org.bukkit.Color color) {
        Validate.notNull(network, "The network should not be null.");
        Validate.notNull(color, "The color cannot be null.");

        this.network = network;
    }

    @Override
    public void run() {
        for (Location l : network.connectorNodes) {
            spawnParticles(l);
        }

        for (Location l : network.terminusNodes) {
            spawnParticles(l);
        }
    }

    /**
     * This method will spawn the actual particles centered on the cargo block.
     * 
     * @param l
     *            The {@link Location} of our node
     */
    private void spawnParticles(@Nonnull Location l) {
        // Spawn particles for all players in the world within reasonable distance (64 blocks)
        for (org.bukkit.entity.Player player : l.getWorld().getPlayers()) {
            double distanceSquared = player.getLocation().distanceSquared(l);
            if (distanceSquared <= 4096) { // 64^2 = 4096
                
                // Centered particles at block center (Y + 0.5 for true center)
                if (VersionedParticle.DUST != null) {
                    // Create blue dust options
                    DustOptions blueOptions = new DustOptions(Color.fromRGB(0, 100, 255), 1.0f);
                    
                    // Single centered particle effect
                    player.spawnParticle(VersionedParticle.DUST, 
                        l.getX() + 0.5, l.getY() + 0.5, l.getZ() + 0.5, 
                        8, 0.1, 0.1, 0.1, 0, blueOptions);
                } else {
                    // Fallback for older versions - use basic blue particles
                    try {
                        DustOptions blueOptions = new DustOptions(Color.fromRGB(0, 100, 255), 1.0f);
                        player.spawnParticle(Particle.DUST, 
                            l.getX() + 0.5, l.getY() + 0.5, l.getZ() + 0.5, 
                            6, 0.1, 0.1, 0.1, 0, blueOptions);
                    } catch (Exception e) {
                        // Final fallback - use ENCHANTED_HIT for blue effect
                        player.spawnParticle(Particle.ENCHANTED_HIT, 
                            l.getX() + 0.5, l.getY() + 0.5, l.getZ() + 0.5, 
                            4, 0.1, 0.1, 0.1, 0);
                    }
                }
            }
        }
    }
}
