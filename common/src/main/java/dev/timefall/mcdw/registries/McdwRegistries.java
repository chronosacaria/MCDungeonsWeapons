/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.registries;

import com.mojang.serialization.MapCodec;
import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchantment.effect.EntityAwareValueEffect;
import dev.timefall.mcdw.enchantment.effect.entity.RelativeEnchantmentEntityEffectType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.RegistryKey;

public class McdwRegistries {

    public static final DefaultedRegistry<MapCodec<? extends EntityAwareValueEffect>> ENTITY_AWARE_VALUE_EFFECT_TYPES = FabricRegistryBuilder.<MapCodec<? extends EntityAwareValueEffect>>createDefaulted(RegistryKey.ofRegistry(Mcdw.ID("entity_aware_value_effect_types")),Mcdw.ID("unit")).buildAndRegister();

    public static final DefaultedRegistry<MapCodec<? extends RelativeEnchantmentEntityEffectType>> RELATIVE_ENTITY_EFFECT_TYPES = FabricRegistryBuilder.<MapCodec<? extends RelativeEnchantmentEntityEffectType>>createDefaulted(RegistryKey.ofRegistry(Mcdw.ID("relative_entity_effect_types")),Mcdw.ID("unit")).buildAndRegister();


    public static void register(){
        EntityAwareValueEffect.register(ENTITY_AWARE_VALUE_EFFECT_TYPES);
    }

}