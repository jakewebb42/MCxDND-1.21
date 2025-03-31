package net.araknid42.mcxdnd.item.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.araknid42.mcxdnd.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class DieItem extends BowItem {

    public DieItem(Properties pProperties) {
        super(pProperties);
    }
    public static int NUM_SIDES;

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        if (!pLevel.isClientSide) {
            // Init
            BlockPos playerPos = new BlockPos((int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());
            Minecraft minecraft = Minecraft.getInstance();
            ItemStack itemstack = pLivingEntity.getItemInHand(InteractionHand.MAIN_HAND);
            String subtitleString = "";

            // Set NUM_SIDES
            if (pStack.is(ModItems.D20.get())){
                DieItem.NUM_SIDES = 20;
            }
            else if (pStack.is(ModItems.D12.get())) {
                DieItem.NUM_SIDES = 12;
            }
            else if (pStack.is(ModItems.D10.get())) {
                DieItem.NUM_SIDES = 10;
            }
            else if (pStack.is(ModItems.D8.get())) {
                DieItem.NUM_SIDES = 8;
            }
            else if (pStack.is(ModItems.D6.get())) {
                DieItem.NUM_SIDES = 6;
            }
            else if (pStack.is(ModItems.D4.get()) ||
                    pStack.is(ModItems.DAGGER.get())) {
                DieItem.NUM_SIDES = 4;
            }

            // Roll
            int roll = (int)(Math.random()* DieItem.NUM_SIDES + 1);
            String rollString = String.valueOf(roll);

            // Output Roll
            minecraft.gui.setTimes(0, 50, 50);
            minecraft.gui.setTitle(Component.literal(rollString));
            minecraft.gui.setSubtitle(Component.literal(subtitleString));
            pLevel.playSound(null, playerPos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS);
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving != null) {
            Minecraft minecraft = Minecraft.getInstance();
            ItemStack itemstack = pEntityLiving.getItemInHand(InteractionHand.MAIN_HAND);
            BlockPos playerPos = new BlockPos((int)pEntityLiving.getX(), (int)pEntityLiving.getY(), (int)pEntityLiving.getZ());
            String subtitleString;

            int useTime = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
            float pullProgress = getPowerForTime(useTime);

            if (pullProgress > 0.45F) {
                if (!pLevel.isClientSide) {
                    // do what you want to do
                    // Set NUM_SIDES
                    if (pStack.is(ModItems.D20.get())){
                        DieItem.NUM_SIDES = 20;
                    }
                    else if (pStack.is(ModItems.D12.get())) {
                        DieItem.NUM_SIDES = 12;
                    }
                    else if (pStack.is(ModItems.D10.get())) {
                        DieItem.NUM_SIDES = 10;
                    }
                    else if (pStack.is(ModItems.D8.get())) {
                        DieItem.NUM_SIDES = 8;
                    }
                    else if (pStack.is(ModItems.D6.get())) {
                        DieItem.NUM_SIDES = 6;
                    }
                    else if (pStack.is(ModItems.D4.get()) ||
                            pStack.is(ModItems.DAGGER.get())) {
                        DieItem.NUM_SIDES = 4;
                    }

                    // Roll
                    int roll = (int)(Math.random()* DieItem.NUM_SIDES + 1);
                    String rollString = String.valueOf(roll);

                    // Subtitle
                    if (roll == 1 && (pStack.is(ModItems.D20.get()))) {
                        subtitleString = "CRITICAL FAILURE";
                    }
                    else if (roll == 20 && (pStack.is(ModItems.D20.get()))) {
                        subtitleString = "CRITICAL SUCCESS";
                    }
                    else {
                        subtitleString = "";
                    }

                    // Output Roll
                    if (!pLevel.isClientSide) {
                        minecraft.gui.setTitle(Component.literal(rollString));
                        minecraft.gui.setSubtitle(Component.literal(subtitleString));
                        pLevel.playSound(null, playerPos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS);
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        // Start using item
        pPlayer.startUsingItem(pHand);

        // Play sound at playerPos
        BlockPos playerPos = new BlockPos((int)pPlayer.getX(), (int)pPlayer.getY(), (int)pPlayer.getZ());
        pLevel.playSound(null, playerPos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS);

        // Return Success
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }
}
