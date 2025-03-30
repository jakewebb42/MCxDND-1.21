package net.araknid42.mcxdnd.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class D20Item extends Item {

    public D20Item(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        // Init
        Minecraft minecraft = Minecraft.getInstance();
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockPos playerPos = new BlockPos((int)pPlayer.getX(), (int)pPlayer.getY(), (int)pPlayer.getZ());
        String subtitleString;

        // Roll
        int roll = (int)(Math.random()*20 + 1);
        String rollString = String.valueOf(roll);

        // Subtitle
        if (roll == 1) {
            subtitleString = "CRITICAL FAILURE";
        }
        else if (roll == 20) {
            subtitleString = "CRITICAL SUCCESS";
        }
        else {
            subtitleString = "";
        }

        // Output Roll
        if (!pLevel.isClientSide) {
            minecraft.gui.setTitle(Component.literal(rollString));
            minecraft.gui.setSubtitle(Component.literal(subtitleString));
            pLevel.playSound(null, playerPos, SoundEvents.FIREWORK_ROCKET_SHOOT, SoundSource.BLOCKS);
        }

        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }
}
