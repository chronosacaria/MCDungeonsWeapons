/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.timefall.mcdw.effects.NewEnchantmentEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @WrapOperation(method = "onDeath", at = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.onKilledOther (Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;)Z"))
    private boolean mcdw$hookOnDeathEnchantmentEffects(Entity attacker, ServerWorld world, LivingEntity victim, Operation<Boolean> original, DamageSource damageSource){
        // always run the original call to chain any mixins and get the original method to run
        boolean result = original.call(attacker, world, victim);
        // if the thing didn't actually die, don't run the hook
        if (!result) return false;
        //run the on Death Hook from EnchantmentEffects
        NewEnchantmentEffects.mcdw$onDeathHook(attacker, world, victim, damageSource);
        //return death success
        return true;
    }

    @SuppressWarnings("ConstantValue")
    @Inject(method = "jump", at = @At("HEAD"))
    public void mcdw$onJumpEffects(CallbackInfo ci){
        if (((Object) this instanceof ServerPlayerEntity playerEntity)) {
            NewEnchantmentEffects.mcdw$onJumpHook(playerEntity);
        }
    }

}