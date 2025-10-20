package eu.mrneznamy.slimefun5.entities.holograms;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.TextDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import eu.mrneznamy.slimefun5.blocks.BlockPosition;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link EntityHologram}
 */
public class EntityHologramTest {

    @Mock
    private TextDisplay mockTextDisplay;
    
    @Mock
    private World mockWorld;
    
    @Mock
    private Location mockLocation;
    
    private EntityHologram hologram;
    private UUID testUuid;
    private BlockPosition testPosition;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUuid = UUID.randomUUID();
        testPosition = new BlockPosition(mockWorld, 100, 64, 200);
        
        when(mockTextDisplay.getUniqueId()).thenReturn(testUuid);
        when(mockTextDisplay.getLocation()).thenReturn(mockLocation);
        when(mockLocation.getWorld()).thenReturn(mockWorld);
        
        hologram = new EntityHologram(testUuid, testPosition);
    }

    @Test
    public void testConstructor() {
        assertNotNull(hologram);
        // Note: getUniqueId() method doesn't exist in current implementation
        // We can only test that the hologram was created successfully
        assertNull(hologram.getLabel()); // Label should be null initially
    }

    @Test
    public void testHasExpired() {
        // New hologram should not be expired
        assertFalse(hologram.hasExpired());
    }

    @Test
    public void testInitialState() {
        // Test initial state
        assertNull(hologram.getLabel());
        
        // Test that getUniqueId works
        assertEquals(testUuid, hologram.getUniqueId());
        
        // Test that getPosition works
        assertEquals(testPosition, hologram.getPosition());
    }

    @Test
    public void testExpiration() {
        // New hologram should not be expired
        assertFalse(hologram.hasExpired());
    }
}
