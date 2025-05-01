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
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public record RemoveMobEffectEnchantmentEntityEffect(RegistryEntryList<StatusEffect> toRemove, boolean removeAll) implements EnchantmentEntityEffect {

    public RemoveMobEffectEnchantmentEntityEffect(RegistryEntry<StatusEffect> effect){
        this(RegistryEntryList.of(effect), true);
    }

    public static final MapCodec<RemoveMobEffectEnchantmentEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                    RegistryCodecs.entryList(RegistryKeys.STATUS_EFFECT).fieldOf("to_remove").forGetter(RemoveMobEffectEnchantmentEntityEffect::toRemove),
                        Codec.BOOL.optionalFieldOf("remove_all", true).forGetter(RemoveMobEffectEnchantmentEntityEffect::removeAll)
                ).apply(instance, RemoveMobEffectEnchantmentEntityEffect::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity livingEntity){
            if (!removeAll) {
                Random random = user.getRandom();
                Optional<RegistryEntry<StatusEffect>> optional;
                if ((optional = this.toRemove.getRandom(random)).isPresent()) {
                    livingEntity.removeStatusEffect(optional.get());
                }
            } else {
                for (var effect : this.toRemove){
                    livingEntity.removeStatusEffect(effect);
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}