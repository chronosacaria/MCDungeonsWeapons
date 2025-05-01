/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.component;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchantment.effect.EntityAwareValueEffect;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.enchantment.effect.TargetedEnchantmentEffect;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;
import java.util.function.UnaryOperator;

public class McdwEffectComponentTypes {

    public static final ComponentType<EntityAwareValueEffect> ACCELERATE_CHARGE_TIME = register("accelerate_charge_time", builder -> builder.codec(EntityAwareValueEffect.CODEC));
    public static final ComponentType<EnchantmentEntityEffect> ACCELERATE_BOW_CHARGED = register("accelerate_bow_charged", builder -> builder.codec(EnchantmentEntityEffect.CODEC));
    public static final ComponentType<List<EnchantmentEffectEntry<EntityAwareValueEffect>>> ENTITY_AWARE_DAMAGE = register("entity_aware_damage", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EntityAwareValueEffect.CODEC, LootContextTypes.ENCHANTED_DAMAGE).listOf()));
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentEntityEffect>>> ON_JUMP = register("on_jump", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentEntityEffect.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()));
    public static final ComponentType<List<TargetedEnchantmentEffect<EnchantmentEntityEffect>>> POST_DEATH = register("post_death", builder -> builder.codec(TargetedEnchantmentEffect.createPostAttackCodec(EnchantmentEntityEffect.CODEC, LootContextTypes.ENCHANTED_DAMAGE).listOf()));
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffect>>> XP_REPAIR_PLAYER = register("xp_repair_player", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentValueEffect.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Mcdw.ID(id), (builderOperator.apply(ComponentType.builder())).build());
    }
}