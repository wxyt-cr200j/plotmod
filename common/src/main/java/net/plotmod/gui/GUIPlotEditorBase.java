package net.plotmod.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.plotmod.PlotMod;
import net.plotmod.block.BlockPlotTriggerBase;
import net.plotmod.data.PlotBase;

import static net.plotmod.PlotMod.plots;

public class GUIPlotEditorBase extends Screen {

    public BlockPlotTriggerBase.PlotTriggerEntityBase base;

    public GUIPlotEditorBase(BlockPlotTriggerBase.PlotTriggerEntityBase base) {
        super(Component.translatable("editor"));

        this.base=base;

    }
    private EditBox box;
    private Button btn;
    @Override
    public boolean isPauseScreen(){
        return false;
    }
    @Override

    public void init(){

        box=new EditBox(this.font,width/4,height/2,width/2,20,Component.translatable(base.id));

        btn=new Button(width/4,height/2+20,width/2,20,Component.translatable("gui.plotmod.button.confirm"),btn->{
            base.id=box.getValue();
            base.refresh();
        });
        addRenderableWidget(box);

        addRenderableWidget(btn);
    }

    @Override
    public void render(PoseStack ps,int a,int b,float c){

        drawCenteredString(ps,this.font,Component.translatable("gui.plotmod.text.enter_id"),width/2,height/2-20,0xffffff);
        box.render(ps,a,b,c);
        btn.render(ps, a, b, c);

    }
}
