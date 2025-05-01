/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.timefall.mcdw.effects.NewEnchantmentEffects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @ModifyReturnValue(method = "getDamage", at = @At("RETURN"))
    private static float mcdw$hookStatusDamageEffects(float original, ServerWorld world, ItemStack stack, Entity target, DamageSource damageSource, float baseDamage){
        return original + NewEnchantmentEffects.mcdw$entityAwareDamageHook(world, stack, target, damageSource, original);
    }

}