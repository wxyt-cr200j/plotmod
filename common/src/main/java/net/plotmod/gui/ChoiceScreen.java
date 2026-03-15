package net.plotmod.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ChoiceScreen extends Screen {
    private Screen lt;
    private Component coma,comb;
    public ChoiceScreen(Component com,Component com1,Screen last)
    {
        super(com);
        lt=last;
        coma=com;comb=com1;
    }
    public ColorButton cb1;
    public ColorButton cb2;

    @Override
    public void init(){
        cb1 = new ColorButton(width/2-100,height/3*2-30,100,20,Component.literal("是"),btn->{
            Minecraft.getInstance().close();
        },0xff57a6d0);
        addRenderableWidget(cb1);
        cb2 = new ColorButton(width/2,height/3*2-30,100,20,Component.literal("否"),btn->{
            Minecraft.getInstance().setScreen(lt);
        },0xffabc7cb);
        addRenderableWidget(cb2);
    }
    @Override
    public void render(PoseStack ps,int a,int b,float c){
        fill(ps,width/2-110,height/3,width/2+110,height/3*2,0xFFFFFFFF);
        fill(ps,width/2-108,height/3+height/15,width/2+108,height/3*2-2,0xff202829);
        drawCenteredString(ps,this.font,coma,width/2,height/3+height/45,0x202829);
        drawCenteredString(ps,this.font,comb,width/2,height/3+height/15+(height/3-30-height/15)/3,0xffffff);
        cb1.render(ps,a,b);
        cb2.render(ps,a,b);
    }
}
