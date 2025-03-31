package net.araknid42.mcxdnd.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DisadvantageDieItem extends DieItem {

    // Constructor
    public DisadvantageDieItem(Properties pProperties) {
        super(pProperties);
    }

    /* Custom variables */
    public static int NUM_SIDES = 20;
    public static int MODIFIER = 0;
    public static SoundEvent END_SOUND = SoundEvents.DRAGON_FIREBALL_EXPLODE;
    public static SoundEvent ROLL_SOUND = SoundEvents.EXPERIENCE_ORB_PICKUP;

    /* Custom Functions */
    // Roll Functions
    private String rollDieString() {
        int roll1 = (int)((Math.random()* DisadvantageDieItem.NUM_SIDES + 1) + DisadvantageDieItem.MODIFIER);
        int roll2 = (int)((Math.random()* DisadvantageDieItem.NUM_SIDES + 1) + DisadvantageDieItem.MODIFIER);
        String roll1String = String.valueOf(roll1);
        String roll2String = String.valueOf(roll2);

        return " " + roll1String + " " + roll2String + " ";
    }
    private String determineCriticalString(String rollString, ItemStack pStack) {
        String criticalString = "";
        String failureString = "CRITICAL FAILURE";
        String successString = "CRITICAL SUCCESS";
        String blankString = "";

        // Subtitle
        if (rollString.contains(" 1 ")) {
            criticalString = failureString;
        }
        else if (rollString.equals("20 20")) {
            criticalString = successString;
        }
        else {
            criticalString = blankString;
        }

        return criticalString;
    }

    /* Use Functions */
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        // Start using item
        pPlayer.startUsingItem(pHand);

        // Return Success
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        if (!pLevel.isClientSide) {
            // Init
            BlockPos playerPos = new BlockPos((int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());
            Minecraft minecraft = Minecraft.getInstance();
            String blankString = ""; // show blank subtitle no matter what

            // Roll
            String rollString = rollDieString();

            // Output Roll
            minecraft.gui.setTimes(0, 50, 50);
            minecraft.gui.setTitle(Component.literal(rollString));
            minecraft.gui.setSubtitle(Component.literal(blankString));

            // Output Sound
            pLevel.playSound(null, playerPos, DieItem.ROLL_SOUND, SoundSource.BLOCKS);
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if ((pEntityLiving != null) && (!pLevel.isClientSide)) {

            // Init Die
            Minecraft minecraft = Minecraft.getInstance();
            BlockPos playerPos = new BlockPos((int)pEntityLiving.getX(), (int)pEntityLiving.getY(), (int)pEntityLiving.getZ());

            // Init charge
            int useTime = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
            float pullProgress = getPowerForTime(useTime);

            // Charge
            if (pullProgress > 0.45F) {
                // Generate roll and subtitle
                String rollString = rollDieString();
                String subtitleString = determineCriticalString(rollString, pStack);

                // Output Roll
                minecraft.gui.setTitle(Component.literal(rollString));
                minecraft.gui.setSubtitle(Component.literal(subtitleString));

                // Output Sound
                pLevel.playSound(null, playerPos, DieItem.END_SOUND, SoundSource.BLOCKS);
            }
        }
    }
}
