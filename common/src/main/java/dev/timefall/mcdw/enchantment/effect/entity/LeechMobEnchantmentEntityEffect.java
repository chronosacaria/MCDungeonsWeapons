/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.effect.entity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record LeechMobEnchantmentEntityEffect(EnchantmentLevelBasedValue amount)implements EnchantmentEntityEffect
{

    public static final MapCodec<LeechMobEnchantmentEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(LeechMobEnchantmentEntityEffect::amount)
                ).apply(instance, LeechMobEnchantmentEntityEffect::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity victim){
            if (context.owner() != null){
                float healthRegained = this.amount.getValue(level) * victim.getMaxHealth();
                context.owner().heal(healthRegained);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}