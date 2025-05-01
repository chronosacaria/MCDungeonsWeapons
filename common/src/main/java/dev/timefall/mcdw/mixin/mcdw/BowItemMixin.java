/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.mixin.mcdw;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.timefall.mcdw.api.interfaces.IMcdwDrawSpeed;
import dev.timefall.mcdw.effects.NewEnchantmentEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public class BowItemMixin {

    @Inject(method = "onStoppedUsing", at = @At(("HEAD")))
    private void mcdw$onStoppedUsingShareVariable(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci,
                                                  @Share("mcdw$stack") LocalRef<ItemStack> sharedStack, @Share("mcdw$user") LocalRef<LivingEntity> sharedUser) {
        sharedStack.set(stack);
        sharedUser.set(user);
    }

    @ModifyArg(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"), index = 0)
    private int mcdw$acceleratedPullProgress(int value,
                                             @Share("mcdw$stack") LocalRef<ItemStack> sharedStack, @Share("mcdw$user") LocalRef<LivingEntity> sharedUser) {
        ItemStack bowItemStack = sharedStack.get();
        LivingEntity user = sharedUser.get();

        if (bowItemStack.getItem() instanceof IMcdwDrawSpeed mcdwDrawSpeed) {
            value /= (int) (mcdwDrawSpeed.getDrawSpeed() / 20);
        }

        float drawSpeedMultiplier = NewEnchantmentEffects.mcdw$accelerateChargeTimeHook(bowItemStack, user, 1f);

        value = (int) (((float) value) * drawSpeedMultiplier);

        // create stack component for passing modified draw progress to model predicate provider

        if (BowItem.getPullProgress(value) >= 1f) {
            NewEnchantmentEffects.mcdw$accelerateFullyChargedHook(bowItemStack, user);
        }

        //Overcharge??

        return value;
    }
}
