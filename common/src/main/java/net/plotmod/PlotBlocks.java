package net.plotmod;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.plotmod.block.BlockPlotTriggerBase;

import static net.plotmod.PlotMod.BLOCKS;

public class PlotBlocks {
    public static RegistrySupplier<Block> BASE_TEST = BLOCKS.register("base_test",()->new BlockPlotTriggerBase(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));
}
