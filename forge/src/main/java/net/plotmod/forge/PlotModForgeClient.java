package net.plotmod.forge;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.plotmod.PlotModClient;

import static net.plotmod.PlotModClient.initClient;


public class PlotModForgeClient  {

    public static void initialize(FMLClientSetupEvent event) {
        event.enqueueWork(PlotModClient::initClient);
    }


}
