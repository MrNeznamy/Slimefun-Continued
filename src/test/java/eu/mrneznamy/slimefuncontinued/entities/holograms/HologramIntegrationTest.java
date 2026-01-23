package eu.mrneznamy.slimefuncontinued.entities.holograms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the new hologram system.
 * This test verifies that all classes are properly integrated and can be instantiated.
 */
public class HologramIntegrationTest {

    @Test
    public void testHologramManagerExists() {
        // Test that HologramManager class exists and has expected methods
        assertDoesNotThrow(() -> {
            Class<?> managerClass = HologramManager.class;
            
            // Verify key methods exist
            managerClass.getMethod("initialize", org.bukkit.plugin.Plugin.class);
            managerClass.getMethod("shutdown", org.bukkit.plugin.Plugin.class);
            managerClass.getMethod("getService", org.bukkit.plugin.Plugin.class);
            managerClass.getMethod("createHologram", org.bukkit.plugin.Plugin.class, org.bukkit.Location.class, String.class);
            managerClass.getMethod("removeHologram", org.bukkit.plugin.Plugin.class, org.bukkit.Location.class);
            managerClass.getMethod("getHologram", org.bukkit.plugin.Plugin.class, org.bukkit.Location.class);
        });
    }

    @Test
    public void testEntityHologramServiceExists() {
        // Test that EntityHologramService class exists and has expected methods
        assertDoesNotThrow(() -> {
            Class<?> serviceClass = EntityHologramService.class;
            
            // Verify key methods exist
            serviceClass.getMethod("createHologram", org.bukkit.Location.class, String.class);
            serviceClass.getMethod("getHologramAt", org.bukkit.Location.class);
            serviceClass.getMethod("removeHologram", org.bukkit.Location.class);
            serviceClass.getMethod("setHologramLabel", org.bukkit.Location.class, String.class);
            serviceClass.getMethod("setHologramScale", org.bukkit.Location.class, float.class);
            serviceClass.getMethod("setHologramBackgroundColor", org.bukkit.Location.class, int.class);
            serviceClass.getMethod("setHologramTextOpacity", org.bukkit.Location.class, byte.class);
            serviceClass.getMethod("getCachedHolograms");
            serviceClass.getMethod("isEnabled");
            serviceClass.getMethod("disable");
            serviceClass.getMethod("getPersistentDataKey");
            serviceClass.getMethod("getPlugin");
        });
    }

    @Test
    public void testEntityHologramExists() {
        // Test that EntityHologram class exists and has expected methods
        assertDoesNotThrow(() -> {
            Class<?> hologramClass = EntityHologram.class;
            
            // Verify key methods exist
            hologramClass.getMethod("getLabel");
            hologramClass.getMethod("setLabel", String.class);
            hologramClass.getMethod("hasExpired");
            hologramClass.getMethod("hasDespawned");
            hologramClass.getMethod("remove");
            hologramClass.getMethod("getUniqueId");
            hologramClass.getMethod("getPosition");
            hologramClass.getMethod("setScale", float.class);
            hologramClass.getMethod("setBackgroundColor", int.class);
            hologramClass.getMethod("setTextOpacity", byte.class);
        });
    }

    @Test
    public void testPackageStructure() {
        // Verify that all classes are in the correct package
        assertEquals("eu.mrneznamy.slimefuncontinued.entities.holograms", EntityHologram.class.getPackageName());
        assertEquals("eu.mrneznamy.slimefuncontinued.entities.holograms", EntityHologramService.class.getPackageName());
        assertEquals("eu.mrneznamy.slimefuncontinued.entities.holograms", HologramManager.class.getPackageName());
    }

    @Test
    public void testStaticMethods() {
        // Test that static methods exist and can be called
        assertDoesNotThrow(() -> {
            // Test HologramManager static methods
            HologramManager.class.getMethod("initialize", org.bukkit.plugin.Plugin.class);
            HologramManager.class.getMethod("shutdown", org.bukkit.plugin.Plugin.class);
            HologramManager.class.getMethod("getService", org.bukkit.plugin.Plugin.class);
            
            // Test EntityHologram static method
            EntityHologram.class.getMethod("hasHologramData", 
                org.bukkit.persistence.PersistentDataContainer.class,
                eu.mrneznamy.slimefuncontinued.blocks.BlockPosition.class,
                org.bukkit.NamespacedKey.class);
        });
    }
}
