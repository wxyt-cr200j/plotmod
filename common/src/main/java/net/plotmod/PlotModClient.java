package net.plotmod;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;

import static net.plotmod.PlotMod.*;

public class PlotModClient {
    public static void initClient(){
        RenderTypeRegistry.register(RenderType.translucent(),PLOT_TRIGGER.get());
        System.out.println("hello world");
    }
}
