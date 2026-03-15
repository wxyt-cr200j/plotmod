package net.plotmod.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TSWButton extends PictureButton{
    public Component coma,comb;
    public TSWButton(int x, int y, int w, int h, Component com,Component com1, OnPress func, ResourceLocation rl)
    {
        super(x,y,w,h,com,func,rl);
        coma=com;comb=com1;
    }

    public void render(PoseStack ps, Font font,int mx,int my)
    {
        super.render(ps,mx,my);
        ps.pushPose();
        ps.scale(2,2,2);//width/20,height/5,width/20*6,height/5*2
        drawString(ps,font,coma,super.x1/2+super.w1/12/2,super.y1/2+super.h1/2-super.h1/3/2-super.h1/12/2,0xFFFFFF);
        ps.popPose();
        drawString(ps,font,comb,super.x1+super.w1/12,super.y1+super.h1-super.h1/9-super.h1/12,0xFFFFFF);

    }
}
