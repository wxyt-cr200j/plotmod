package net.plotmod.lib;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Optional;

public class ResourceGetting {
    public Optional<Resource> getResources(ResourceManager manager, ResourceLocation rl){
        return manager.getResource(rl);
    }
}
