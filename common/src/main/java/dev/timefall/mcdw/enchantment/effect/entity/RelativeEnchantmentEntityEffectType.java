/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.effect.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.timefall.mcdw.McdwCommon;
import dev.timefall.mcdw.enchantment.effect.entity.relative.DamageTakenRelativeEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.relative.MaxHealthRelativeEnchantmentEntityEffectType;
import dev.timefall.mcdw.registries.McdwRegistries;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.function.Function;

public interface RelativeEnchantmentEntityEffectType extends EnchantmentEntityEffect {

    Codec<RelativeEnchantmentEntityEffectType> CODEC = McdwRegistries.RELATIVE_ENTITY_EFFECT_TYPES.getCodec().dispatch(RelativeEnchantmentEntityEffectType::getCodec, Function.identity());

    @Override
    default void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
    }

    void applyRelative(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos, Entity relativeTo);

    @Override
    MapCodec<? extends RelativeEnchantmentEntityEffectType> getCodec();

    static void register(Registry<MapCodec<? extends RelativeEnchantmentEntityEffectType>> registry){
        Registry.register(registry, McdwCommon.ID("unit"), Unit.CODEC);
        Registry.register(registry, McdwCommon.ID("damage_taken_relative"), DamageTakenRelativeEnchantmentEntityEffect.CODEC);
        Registry.register(registry, McdwCommon.ID("max_health_relative"), MaxHealthRelativeEnchantmentEntityEffectType.CODEC);
    }

    //////////////

    class Unit implements RelativeEnchantmentEntityEffectType{

        @SuppressWarnings("CanBeFinal")
        public static MapCodec<Unit> CODEC = MapCodec.unit(new Unit());

        @Override
        public void applyRelative(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos, Entity relativeTo) {
        }

        @Override
        public MapCodec<? extends RelativeEnchantmentEntityEffectType> getCodec() {
            return CODEC;
        }
    }
}