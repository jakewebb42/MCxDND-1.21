package net.araknid42.mcxdnd.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class D8Item extends Item {

    public D8Item(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        // Init
        Minecraft minecraft = Minecraft.getInstance();
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockPos playerPos = new BlockPos((int)pPlayer.getX(), (int)pPlayer.getY(), (int)pPlayer.getZ());

        // Roll
        int roll = (int)(Math.random()*8 + 1);
        String rollString = String.valueOf(roll);

        // Output Roll
        if (!pLevel.isClientSide) {
            minecraft.gui.setTitle(Component.literal(rollString));
            pLevel.playSound(null, playerPos, SoundEvents.FIREWORK_ROCKET_SHOOT, SoundSource.BLOCKS);
            //pPlayer.displayClientMessage(Component.literal(rollString), true);
        }

        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }
}
