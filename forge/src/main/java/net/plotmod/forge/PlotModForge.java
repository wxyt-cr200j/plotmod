package net.plotmod.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.plotmod.PlotMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.plotmod.PlotModClient;

import static net.plotmod.PlotModClient.initClient;

@Mod(PlotMod.MOD_ID)
public class PlotModForge {
    public PlotModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(PlotMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        PlotMod.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(PlotModForgeClient::initialize);
       // DistExecutor.safeRunWhenOn(Dist.CLIENT, ()->PlotModForgeClient::initialize);

    }

}
