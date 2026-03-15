package net.plotmod.data;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.plotmod.lib.JsonReader;

import static net.plotmod.PlotMod.plots;

public class PlotBase {
    public String id;
    public JsonObject jsonObject;
    public Plots plots1;
    public Component text;
    public String next;
    public PlotBase(String id,Plots plots1)
    {
        this.id = id;
        this.plots1=plots1;
    }
    public void createPlot(){
        jsonObject=JsonReader.readJson(new ResourceLocation(plots1.plots.get(id)!=null?plots.plots.get(id).getAsJsonObject().get("location").getAsString():"plotmod:plot/part1/test1.json"));
        text=Component.translatable(jsonObject.get("text")!=null?jsonObject.get("text").getAsString():"plot.plotmod.error.no_plot");
        next=jsonObject.get("next")!=null?jsonObject.get("next").getAsString():"";
    }
}
