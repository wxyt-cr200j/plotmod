package net.plotmod.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.plotmod.lib.JsonReader;

import java.io.FileNotFoundException;

import static net.plotmod.PlotMod.MOD_ID;

public class Plots {
    public JsonObject plots = new JsonObject();

    public void onLoad() {

        try {
            ResourceLocation CUSTOM_RES = new ResourceLocation("plotmod:plot_custom_resources.json");
            JsonObject temp = JsonReader.readJson(CUSTOM_RES);
            System.out.println(temp);
            JsonObject temp1 = new JsonObject();
            temp.entrySet().forEach(stringJsonElementEntry -> {
                JsonObject temp2 = stringJsonElementEntry.getValue().getAsJsonObject().get("plots").getAsJsonObject();
                temp2.entrySet().forEach(act ->
                {
                    temp1.add(act.getKey(), act.getValue());
                });
            });
            System.out.println(temp1);
            temp1.entrySet().forEach(element -> {
                ResourceLocation part = new ResourceLocation(element.getValue().getAsString());
                JsonObject temp3 = JsonReader.readJson(part).getAsJsonObject("context");
                temp3.entrySet().forEach(jsonElement -> {
                    plots.add(jsonElement.getKey(), jsonElement.getValue());
                });
            });
            System.out.println(plots);
        } catch (Exception ignored) {

        }

    }

}
