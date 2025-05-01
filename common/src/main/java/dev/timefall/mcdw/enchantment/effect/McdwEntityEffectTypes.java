/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.enchantment.effect;

import com.mojang.serialization.MapCodec;
import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchantment.effect.entity.AOEEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.ApplyStackingMobEffectEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.LeechMobEnchantmentEntityEffect;
import dev.timefall.mcdw.enchantment.effect.entity.RemoveMobEffectEnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwEntityEffectTypes {

    public static MapCodec<AOEEnchantmentEntityEffect> AOE_EFFECT = register("aoe_effect", AOEEnchantmentEntityEffect.CODEC);
    public static MapCodec<ApplyStackingMobEffectEnchantmentEntityEffect> APPLY_STACKING_MOB_EFFECT = register("apply_stacking_mob_effect", ApplyStackingMobEffectEnchantmentEntityEffect.CODEC);
    public static MapCodec<LeechMobEnchantmentEntityEffect> LEECH_MOB_EFFECT = register("leech_mob_effect", LeechMobEnchantmentEntityEffect.CODEC);
    public static MapCodec<RemoveMobEffectEnchantmentEntityEffect> REMOVE_MOB_EFFECT = register("remove_mob_effect", RemoveMobEffectEnchantmentEntityEffect.CODEC);


    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Mcdw.ID(id), codec);
    }

}