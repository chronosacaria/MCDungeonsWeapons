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
import dev.timefall.mcdw.mixin.LivingEntityAccessor;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public record AOEEnchantmentEntityEffect(EnchantmentLevelBasedValue radius, EnchantmentEntityEffect effect, Optional<EnchantmentLevelBasedValue> targetLimit, boolean ignoreTargetEntity)implements EnchantmentEntityEffect {

    public AOEEnchantmentEntityEffect(EnchantmentLevelBasedValue radius, EnchantmentEntityEffect effect){
        this(radius, effect, Optional.empty(), true);
    }

    public static final MapCodec<AOEEnchantmentEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("radius").forGetter(AOEEnchantmentEntityEffect::radius),
                    EnchantmentEntityEffect.CODEC.fieldOf("effect").forGetter(AOEEnchantmentEntityEffect::effect),
                    EnchantmentLevelBasedValue.CODEC.optionalFieldOf("target_limit").forGetter(AOEEnchantmentEntityEffect::targetLimit),
                    Codec.BOOL.optionalFieldOf("ignore_target_entity", true).forGetter(AOEEnchantmentEntityEffect::ignoreTargetEntity)
                ).apply(instance, AOEEnchantmentEntityEffect::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        double d = (double) this.radius.getValue(level) * 2.0;
        Predicate<Entity> aoePredicate = e -> true;
        Predicate<Entity> predicate = e -> (!this.ignoreTargetEntity || e != user) && aoePredicate.test(e);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, Box.of(pos, d, d, d), predicate);
        if (effect instanceof RelativeEnchantmentEntityEffectType relativeEffect) {
            float damage = user instanceof LivingEntity ? ((LivingEntityAccessor)user).getLastDamageTaken() : 0f;
            if (this.targetLimit.isPresent()) {
                Collections.shuffle(entities);
                int limit = (int) this.targetLimit.get().getValue(level);
                for (LivingEntity livingEntity : entities) {
                    relativeEffect.applyRelative(world, level, context, livingEntity, pos, user);
                    --limit;
                    if (limit <= 0) break;
                }
            } else {
                for (LivingEntity livingEntity : entities) {
                    relativeEffect.applyRelative(world, level, context, livingEntity, pos, user);
                }
            }
        } else {
            if (this.targetLimit.isPresent()) {
                Collections.shuffle(entities);
                int limit = (int) this.targetLimit.get().getValue(level);
                for (LivingEntity livingEntity : entities) {
                    effect.apply(world, level, context, livingEntity, pos);
                    --limit;
                    if (limit <= 0) break;
                }
            } else {
                for (LivingEntity livingEntity : entities) {
                    effect.apply(world, level, context, livingEntity, pos);
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}