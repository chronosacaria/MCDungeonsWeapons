/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.util;

import dev.timefall.mcdw.bases.McdwBowItem;
import dev.timefall.mcdw.bases.McdwCrossbowItem;
import dev.timefall.mcdw.bases.McdwShortbowItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class RangedAttackHelper {

    public static float getVanillaArrowVelocity(ItemStack stack, int charge){
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }
        float arrowVelocity = (float) charge / bowChargeTime;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 3.0F;
        return Math.min(arrowVelocity, 1.0F);
    }

    public static float getVanillaBowChargeTime(ItemStack stack){
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        //int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ACCELERATE, stack);

        float bowChargeTime = 30 * (Math.max(20.0F - 5 * quickChargeLevel, 0));
        long lastFiredtime = (long)(McdwBowItem.getPullProgress(22) * (Math.max(20.0F - 5 * quickChargeLevel, 0)));

        if (/*accelerateLevel > 0 &&*/ lastFiredtime > 0){
            return Math.max(bowChargeTime - 5 * quickChargeLevel, 0);
        } else {
            return Math.max(20.0F - 5 * quickChargeLevel, 0);
        }
    }

    public static float getShortBowChargeTime(ItemStack stack){
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        //int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ACCELERATE, stack);

        float bowChargeTime = 15 * (Math.max(10.0F - 5 * quickChargeLevel, 0));
        long lastFiredtime = (long)(McdwShortbowItem.getPullProgress(11) * (Math.max(10.0F - 5 * quickChargeLevel, 0)));

        if (/*accelerateLevel > 0 &&*/ lastFiredtime > 0){
            return Math.max(bowChargeTime - 5 * quickChargeLevel, 0);
        } else {
            return Math.max(10.0F - 5 * quickChargeLevel, 0);
        }
    }


    public static float getVanillaOrModdedCrossbowArrowVelocity(ItemStack stack){
        float arrowVelocity;
        ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null) {
            if (stack.getItem() instanceof McdwCrossbowItem) {
                arrowVelocity = McdwCrossbowItem.mcdw$getSpeed(chargedProjectilesComponent);
            } else {
                arrowVelocity = chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
            }
            return arrowVelocity;
        }
        return 3.15F;
    }

    public static float getVanillaOrModdedBowArrowVelocity(ItemStack stack, int charge){
        float arrowVelocity;
        if (stack.getItem() instanceof McdwBowItem){
            arrowVelocity = McdwBowItem.getBowArrowVelocity(stack, charge);
        } else {
            arrowVelocity = getVanillaArrowVelocity(stack, charge);
        }
        return arrowVelocity;
    }
}
