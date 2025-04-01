package net.araknid42.mcxdnd.item.custom;

import net.araknid42.mcxdnd.sound.ModSounds;
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
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class AdvantageDieItem extends DieItem {

    // Constructor
    public AdvantageDieItem(Properties pProperties) {
        super(pProperties);
    }

    /* Custom variables */
    public static int NUM_SIDES = 20;
    public static int MODIFIER = 0;
    public static SoundEvent END_SOUND = SoundEvents.DRAGON_FIREBALL_EXPLODE;
    public static SoundEvent ROLL_SOUND = ModSounds.DICE_ROLL.get();

    /* Custom Functions */
    // Roll Functions
    @Override
    public String rollDieString() {
        int roll1 = (int)((Math.random()* AdvantageDieItem.NUM_SIDES + 1) + AdvantageDieItem.MODIFIER);
        int roll2 = (int)((Math.random()* AdvantageDieItem.NUM_SIDES + 1) + AdvantageDieItem.MODIFIER);
        String roll1String = String.valueOf(roll1);
        String roll2String = String.valueOf(roll2);

        return roll1String + " " + roll2String;
    }

    @Override
    public String determineCriticalString(String rollString, ItemStack pStack) {
        String criticalString = "";
        String failureString = "CRITICAL FAILURE";
        String successString = "CRITICAL SUCCESS";
        String blankString = "";

        // Subtitle
        if (rollString.equals("1 1")) {
            criticalString = failureString;
        }
        else if (rollString.contains("20")) {
            criticalString = successString;
        }
        else {
            criticalString = blankString;
        }

        return criticalString;
    }
}
