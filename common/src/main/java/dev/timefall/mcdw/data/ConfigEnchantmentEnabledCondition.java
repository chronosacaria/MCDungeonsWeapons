/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.data;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.configs.McdwEnchantmentSettingsConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public record ConfigEnchantmentEnabledCondition(Identifier enchant) implements ResourceCondition {

    private static final MapCodec<ConfigEnchantmentEnabledCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Identifier.CODEC.fieldOf("enchant").forGetter(ConfigEnchantmentEnabledCondition::enchant)
        ).apply(instance, ConfigEnchantmentEnabledCondition::new)
    );

    private static final ResourceConditionType<ConfigEnchantmentEnabledCondition> TYPE = ResourceConditionType.create(Mcdw.ID("enchant_enabled"), CODEC);

    public static void register(){
        ResourceConditions.register(TYPE);
    }

    @Override
    public ResourceConditionType<?> getType() {
        return TYPE;
    }

    @Override
    public boolean test(@Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return McdwEnchantmentSettingsConfig.CONFIG.isEnchantmentEnabled(enchant);
    }

}