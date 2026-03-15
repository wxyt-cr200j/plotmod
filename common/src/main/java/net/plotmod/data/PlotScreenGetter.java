package net.plotmod.data;

import net.minecraft.client.gui.screens.Screen;
import net.plotmod.gui.GUIPlotBase;

public abstract class PlotScreenGetter {
    public static Screen getScreenById(String id){
        return new GUIPlotBase(id);
    }
}
