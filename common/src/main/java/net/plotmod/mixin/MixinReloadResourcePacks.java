package net.plotmod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.plotmod.data.Plots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

import static net.plotmod.PlotMod.plots;

@Mixin(Minecraft.class)
public class MixinReloadResourcePacks {
    @Inject(method = "reloadResourcePacks", at = @At("RETURN"))
    //@Inject(method = "load")
    private void onLoadingPlots(CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        plots.onLoad();
    }
}
