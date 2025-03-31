package net.araknid42.mcxdnd.item.custom;

import net.araknid42.mcxdnd.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class DieItem extends Item {

    public DieItem(Properties pProperties) {
        super(pProperties);
    }

    public static int NUM_SIDES;

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        // Init
        Minecraft minecraft = Minecraft.getInstance();
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockPos playerPos = new BlockPos((int)pPlayer.getX(), (int)pPlayer.getY(), (int)pPlayer.getZ());
        String subtitleString;

        // Set NUM_SIDES
        if (itemstack.is(ModItems.D20.get())){
            DieItem.NUM_SIDES = 20;
        }
        else if (itemstack.is(ModItems.D12.get())) {
            DieItem.NUM_SIDES = 12;
        }
        else if (itemstack.is(ModItems.D10.get())) {
            DieItem.NUM_SIDES = 10;
        }
        else if (itemstack.is(ModItems.D8.get())) {
            DieItem.NUM_SIDES = 8;
        }
        else if (itemstack.is(ModItems.D6.get())) {
            DieItem.NUM_SIDES = 6;
        }
        else if (itemstack.is(ModItems.D4.get()) ||
                itemstack.is(ModItems.DAGGER.get())) {
            DieItem.NUM_SIDES = 4;
        }

        // Roll
        int roll = (int)(Math.random()* DieItem.NUM_SIDES + 1);
        String rollString = String.valueOf(roll);

        // Subtitle
        if (roll == 1 && (itemstack.is(ModItems.D20.get()))) {
            subtitleString = "CRITICAL FAILURE";
        }
        else if (roll == 20 && (itemstack.is(ModItems.D20.get()))) {
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

            //pPlayer.getItemInHand(pHand).set(ModDataComponentTypes.COORDINATES.get(), playerPos);
        }

        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }
}
