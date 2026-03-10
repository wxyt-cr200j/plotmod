package net.plotmod.forge;

import net.plotmod.PlotExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PlotExpectPlatformImpl {
    /**
     * This is our actual method to {@link PlotExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
