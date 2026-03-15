package net.plotmod;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.types.Type;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.plotmod.block.BlockPlotTriggerBase;
import net.plotmod.data.Plots;

import java.util.function.Supplier;
import java.util.logging.Logger;

import static net.plotmod.PlotBlocks.BASE_TEST;
import static net.plotmod.PlotItems.*;
import static net.plotmod.PlotBlocks.*;

public class PlotMod {
    public static final String MOD_ID = "plotmod";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID,Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(MOD_ID,Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final Logger LOGGER = Logger.getLogger(MOD_ID);
    @FunctionalInterface
    interface TileEntitySupplier<T extends BlockEntity> {
        T supplier(BlockPos pos, BlockState state);
    }

    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));
    // Registering a new creative tab

    //register blocks
    public static RegistrySupplier<Block> PLOT_TRIGGER = BLOCKS.register("plot_trigger",()->new BlockPlotTriggerBase(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));

    public static CreativeModeTab PLOT_BLOCKS_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "plot_blocks_tab"), () ->
            new ItemStack(Items.GRASS_BLOCK));


    //register items
    public static RegistrySupplier<Item> PLOT_TRIGGER_ITEM = ITEMS.register("plot_trigger",()->new BlockItem(PLOT_TRIGGER.get(),new Item.Properties().tab(PLOT_BLOCKS_TAB)));


    //register entity types
    public static RegistrySupplier<BlockEntityType<BlockPlotTriggerBase.PlotTriggerEntityBase>> PLOT_TRIGGER_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("plot_trigger", ()->BlockEntityType.Builder.of(BlockPlotTriggerBase.PlotTriggerEntityBase::new,PLOT_TRIGGER.get()).build(null) );



    public static Plots plots = new Plots();
    public static void init() {
        BLOCKS.register();
        ITEMS.register();

        BLOCK_ENTITY_TYPES.register();
        //Minecraft.getInstance().
        System.out.println(PlotExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
