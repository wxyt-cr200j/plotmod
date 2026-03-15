package net.plotmod.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.plotmod.PlotMod;
import net.plotmod.data.PlotScreenGetter;
import net.plotmod.gui.GUIPlotBase;
import net.plotmod.gui.GUIPlotEditorBase;
import org.jetbrains.annotations.NotNull;

public class BlockPlotTriggerBase extends Block implements EntityBlock {
    public BlockPlotTriggerBase(Properties properties) {
        super(properties);
    }



    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {


        if (!level.isClientSide)
        {
            BlockEntity ent = level.getBlockEntity(pos);
            if(ent instanceof PlotTriggerEntityBase base) {

                if(base.getId()!=null)base.refresh();
                //player.sendSystemMessage(Component.literal(String.valueOf(base.getNum())));
                //base.setChanged();
            }
            return InteractionResult.SUCCESS;
        }
        else if(level.isClientSide){
            BlockEntity ent = level.getBlockEntity(pos);
            if(ent instanceof PlotTriggerEntityBase base) {

                if(base.getId()!=null)Minecraft.getInstance().setScreen(Minecraft.getInstance().gameMode.getPlayerMode()!= GameType.CREATIVE? PlotScreenGetter.getScreenById(base.id) :new GUIPlotEditorBase(base));
                //player.sendSystemMessage(Component.literal(String.valueOf(base.getNum())));
                //base.setChanged();
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }


    @Override
    public @NotNull BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {


        return new PlotTriggerEntityBase(PlotMod.PLOT_TRIGGER_ENTITY_TYPE.get(), blockPos, blockState);
    }


    public static class PlotTriggerEntityBase extends BlockEntity  {
       // public int num = 0;
        public String id ="";
        public PlotTriggerEntityBase(BlockEntityType type,BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        public PlotTriggerEntityBase(BlockPos pos, BlockState state) {
            super(PlotMod.PLOT_TRIGGER_ENTITY_TYPE.get(), pos, state);
        }

        // 每 tick 更新

        public void refresh(){
            setChanged();



            if (level != null) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // 触发更新包发送
            }
        }

        public String getId() {
            refresh();
            return id;
        }

        @Override
        protected void saveAdditional(CompoundTag nbt) {
            super.saveAdditional(nbt);
            //nbt.putInt("number", num);

            nbt.putString("id",id);

        }
        /*@Override
        public @NotNull CompoundTag getUpdateTag() {

            CompoundTag tag = super.getUpdateTag();
            tag.putInt("num", num);
            return tag;
            return saveWithoutMetadata();
        }*/
        @Override
        public void load(CompoundTag nbt) {
            super.load(nbt);
           // num = nbt.getInt("number");

            id=nbt.getString("id");


        }
        public CompoundTag getTag() {
            CompoundTag tag = null;
            if (level != null) {
                tag = saveWithoutMetadata();
            }
            return tag;
        }

        @Override
        public @NotNull CompoundTag getUpdateTag() {
            return getTag();
        }


        @Override
        public ClientboundBlockEntityDataPacket getUpdatePacket() {
            return ClientboundBlockEntityDataPacket.create(this);
        }



        // 客户端同步



    }

}
