package net.plotmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GUIPlotBase extends Screen {

    public GUIPlotBase(Component component) {
        super(component);

        Minecraft.getInstance().options.hideGui = true;

    }


}
