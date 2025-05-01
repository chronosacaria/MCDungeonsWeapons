/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.effect.entity_aware;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.timefall.mcdw.enchantment.effect.EntityAwareValueEffect;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Optional;

public record MultiplyStatusValueEffect(RegistryEntry<StatusEffect> statusEffect, EnchantmentLevelBasedValue amount, boolean provideFlatBonus, Optional<EntityAwareValueEffect.BoundedFloatUnaryOperator> bounds) implements EntityAwareValueEffect {

    public static final MapCodec<MultiplyStatusValueEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Registries.STATUS_EFFECT.getEntryCodec().fieldOf("status_effect").forGetter(MultiplyStatusValueEffect::statusEffect),
            EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(MultiplyStatusValueEffect::amount),
            Codec.BOOL.optionalFieldOf("provide_flat_bonus", false).forGetter(MultiplyStatusValueEffect::provideFlatBonus),
            BoundedFloatUnaryOperator.CODEC.optionalFieldOf("bounds").forGetter(MultiplyStatusValueEffect::bounds)
        ).apply(instance, MultiplyStatusValueEffect::new)
    );

    @Override
    public float apply(int level, float input, LivingEntity livingEntity) {
        //get status
        StatusEffectInstance instance = livingEntity.getStatusEffect(statusEffect);

        //if no status and we don't provide a flat bonus, short circuit back the input
        if (instance == null && !provideFlatBonus) return input;

        //get the amplifier, 0 if the instance is null (this would be a flat bonus)
        int amplifier = instance == null ? 0 : instance.getAmplifier();

        //calculate the multiplier, optionally bounding it
        float multiplier = (bounds.isPresent()
            ? this.bounds.get().apply(this.amount.getValue(level) * (amplifier + 1f))
            : this.amount.getValue(level) * (amplifier + 1f));

        //return the multiplied input
        return input * (1f + multiplier);
    }

    @Override
    public MapCodec<? extends EntityAwareValueEffect> getCodec() {
        return CODEC;
    }
}