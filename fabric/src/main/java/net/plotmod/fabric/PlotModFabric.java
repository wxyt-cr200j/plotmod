package net.plotmod.fabric;

import net.plotmod.PlotMod;
import net.fabricmc.api.ModInitializer;

public class PlotModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PlotMod.init();
    }
}
