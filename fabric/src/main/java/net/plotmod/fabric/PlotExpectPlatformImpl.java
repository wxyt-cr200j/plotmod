package net.plotmod.fabric;

import net.plotmod.PlotExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class PlotExpectPlatformImpl {
    /**
     * This is our actual method to {@link PlotExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
