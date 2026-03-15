package net.plotmod.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.plotmod.PlotMod;
import net.plotmod.PlotModClient;

public class PlotModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlotModClient.initClient();
    }
}
