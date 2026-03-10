package net.plotmod.forge;

import dev.architectury.platform.forge.EventBuses;
import net.plotmod.PlotMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PlotMod.MOD_ID)
public class PlotModForge {
    public PlotModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(PlotMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        PlotMod.init();
    }
}
