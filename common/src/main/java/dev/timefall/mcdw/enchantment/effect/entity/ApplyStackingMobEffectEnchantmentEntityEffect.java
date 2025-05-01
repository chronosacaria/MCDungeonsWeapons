/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.effect.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public record ApplyStackingMobEffectEnchantmentEntityEffect(
        RegistryEntryList<StatusEffect> toApply,
        EnchantmentLevelBasedValue duration,
        EnchantmentLevelBasedValue startingAmplifier,
        EnchantmentLevelBasedValue maxAmplifier,
        boolean popIfMaxed)implements EnchantmentEntityEffect
{

    public ApplyStackingMobEffectEnchantmentEntityEffect(
            RegistryEntry<StatusEffect> toApply,
            EnchantmentLevelBasedValue duration,
            EnchantmentLevelBasedValue maxAmplifier)
    {
        this(RegistryEntryList.of(toApply), duration, EnchantmentLevelBasedValue.constant(0f), maxAmplifier, false);
    }

    public static final MapCodec<ApplyStackingMobEffectEnchantmentEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        RegistryCodecs.entryList(RegistryKeys.STATUS_EFFECT).fieldOf("to_apply").forGetter(ApplyStackingMobEffectEnchantmentEntityEffect::toApply),
                        EnchantmentLevelBasedValue.CODEC.fieldOf("duration").forGetter(ApplyStackingMobEffectEnchantmentEntityEffect::duration),
                        EnchantmentLevelBasedValue.CODEC.optionalFieldOf("starting_amplifier", new EnchantmentLevelBasedValue.Constant(0f)).forGetter(ApplyStackingMobEffectEnchantmentEntityEffect::startingAmplifier),
                        EnchantmentLevelBasedValue.CODEC.fieldOf("max_amplifier").forGetter(ApplyStackingMobEffectEnchantmentEntityEffect::maxAmplifier),
                        Codec.BOOL.optionalFieldOf("pop_if_maxed",false).forGetter(ApplyStackingMobEffectEnchantmentEntityEffect::popIfMaxed)
                ).apply(instance, ApplyStackingMobEffectEnchantmentEntityEffect::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity livingEntity){
            Random random = user.getRandom();
            Optional<RegistryEntry<StatusEffect>> optional;
            if((optional = this.toApply.getRandom(random)).isPresent()){
                int durationValue = (int) this.duration.getValue(level);
                StatusEffectInstance instance = livingEntity.getStatusEffect(optional.get());
                int amplifierValue = instance != null
                        ? Math.max((int) this.maxAmplifier.getValue(level), instance.getAmplifier() + 1)
                        : (int) this.startingAmplifier.getValue(level);
                if (popIfMaxed && instance != null && instance.getAmplifier() == this.maxAmplifier.getValue(level))
                    livingEntity.removeStatusEffect(optional.get());
                else
                    livingEntity.addStatusEffect(new StatusEffectInstance(optional.get(), durationValue, amplifierValue));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}