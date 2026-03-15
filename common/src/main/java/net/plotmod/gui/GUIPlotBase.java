package net.plotmod.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.plotmod.PlotMod;
import net.plotmod.data.PlotBase;
import net.plotmod.lib.JsonReader;

import static net.plotmod.PlotMod.MOD_ID;
import static net.plotmod.PlotMod.plots;

public class GUIPlotBase extends Screen {

    public String id,next1;
    public PlotBase base;

    public GUIPlotBase(String plotId) {
        super(Component.translatable(plotId));

       // Minecraft.getInstance().options.hideGui = true;

        PlotMod.LOGGER.info(id);
        id=plotId;
        base=new PlotBase(id,plots);
        base.createPlot();
    }
    private ColorButton nextPlot;
    @Override
    public boolean isPauseScreen(){
        return false;
    }
    @Override

    public void init(){

        nextPlot=new ColorButton(0,height/3*2,width,height/3,Component.translatable(base.next),btn->{
            if(!base.next.isEmpty()){
               // JsonObject next = plots.plots.get(jsonObject.get("next").getAsString()).getAsJsonObject();
                Minecraft.getInstance().setScreen(new GUIPlotBase(base.next));
            }
            else {
               // Minecraft.getInstance().options.hideGui=false;
                Minecraft.getInstance().setScreen(null);
            }
        },0x80FFFFFF);
        addRenderableWidget(nextPlot);
    }

    @Override
    public void render(PoseStack ps,int a,int b,float c){

        nextPlot.render(ps, a, b);
        drawCenteredString(ps,this.font,base.text,width/2,height/5*4,0xFFFFFF);
    }
}
