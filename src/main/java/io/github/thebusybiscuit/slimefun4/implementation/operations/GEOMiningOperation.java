package io.github.thebusybiscuit.slimefun4.implementation.operations;

import java.util.OptionalInt;

import javax.annotation.Nonnull;

import eu.mrneznamy.slimefun5.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.geo.ResourceManager;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineOperation;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.geo.GEOMiner;

/**
 * This {@link MachineOperation} represents a {@link GEOMiner}
 * mining a {@link GEOResource}.
 *
 * @author iTwins
 *
 * @see GEOMiner
 */
public class GEOMiningOperation extends MiningOperation {

    private final GEOResource resource;

    public GEOMiningOperation(@Nonnull GEOResource resource, int totalTicks) {
        super(resource.getItem().clone(), totalTicks);
        this.resource = resource;
    }

    /**
     * This returns the {@link GEOResource} back to the chunk
     * when the {@link GEOMiningOperation} gets cancelled
     */
    @Override
    public void onCancel(@Nonnull BlockPosition position) {
        ResourceManager resourceManager = Slimefun.getGPSNetwork().getResourceManager();
        int chunkX = position.getX() >> 4;
        int chunkZ = position.getZ() >> 4;
        OptionalInt supplies = resourceManager.getSupplies(resource, position.getWorld(), chunkX, chunkZ);
        supplies.ifPresent(s -> resourceManager.setSupplies(resource, position.getWorld(), chunkX, chunkZ, s + 1));
    }

}
