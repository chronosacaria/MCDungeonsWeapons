/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.interfaces;

import dev.timefall.mcdw.api.util.PlayerAttackHelper;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IOffhandAttack {
    default TypedActionResult<ItemStack> useOffhand(World world, PlayerEntity player, Hand hand) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (hand == Hand.OFF_HAND && world.isClient && (player.getOffHandStack().getItem() instanceof IOffhandAttack && PlayerAttackHelper.mixAndMatchWeapons(player))) {
                //OffhandAttackChecker.checkForOffhandAttack();
                ItemStack offhand = player.getStackInHand(hand);
                return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, player.getStackInHand(hand));
    }
}
