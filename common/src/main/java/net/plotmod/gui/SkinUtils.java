package net.plotmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

import static net.minecraft.client.gui.GuiComponent.blit;

public class SkinUtils {
    // 缓存皮肤纹理的ResourceLocation（避免重复加载）
    private static ResourceLocation skinTextureLocation;

    // 获取当前玩家的UUID（用于加载皮肤）
    public static UUID getCurrentPlayerUUID() {
        Minecraft client = Minecraft.getInstance();
        if (client.getUser() != null) {
            return client.getUser().getProfileId();
        }
        return UUID.randomUUID(); // 离线模式返回随机UUID
    }

    // 加载并绘制玩家头像（绘制到界面指定位置）
    public static void returnPlayerHead(PoseStack ps, int x, int y, int size) {
        Minecraft client = Minecraft.getInstance();
        TextureManager textureManager = client.getTextureManager();
        UUID playerUUID = getCurrentPlayerUUID();

        // 1. 加载玩家皮肤纹理
        if (skinTextureLocation == null) {
            skinTextureLocation=client.getSkinManager().getInsecureSkinLocation(client.getUser().getGameProfile());
        }

        // 2. 绘制头像（截取皮肤的头部区域）
        if (skinTextureLocation != null) {
            // 绑定皮肤纹理
RenderSystem.setShaderTexture(0,skinTextureLocation);
RenderSystem.enableTexture();
            // 计算UV坐标（皮肤的头部区域：U=8-16, V=8-16；第二层帽子：U=40-48, V=8-16）
            int u1 = 8 ;    // 头部起始U
            int v1 = 8 ;    // 头部起始V
            int u2 = 16 ;   // 头部结束U
            int v2 = 16;   // 头部结束V

            // 绘制纹理（只显示头部）
            blit(
                    ps,
                    x, y,          // 界面坐标
                    size,   size,
                    u1,v1,u2-u1,v2-v1,64,64// Z层
                        // 显示尺寸
                               // 边框大小（无）
                   // u1*64, v1*64,        // 纹理起始UV
                   // (int)((u2 - u1)*64), (int)((v2 - v1)*64), // 纹理裁剪尺寸
                   // 64, 64       // 边框UV偏移（无）
            );
        }
    }
}