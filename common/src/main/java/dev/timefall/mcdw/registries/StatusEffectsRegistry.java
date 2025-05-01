/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.statuseffects.AccelerateStatusEffect;
import dev.timefall.mcdw.statuseffects.DynamoStatusEffect;
import dev.timefall.mcdw.statuseffects.PainCycleStatusEffect;
import dev.timefall.mcdw.statuseffects.ShadowFormStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class StatusEffectsRegistry {
    public static RegistryEntry<StatusEffect> ACCELERATE;
    public static RegistryEntry<StatusEffect> DYNAMO;
    public static RegistryEntry<StatusEffect> PAIN_CYCLE;
    public static RegistryEntry<StatusEffect> SHADOW_FORM;

    public static void register() {
        ACCELERATE  = register("accelerate", new AccelerateStatusEffect(StatusEffectCategory.BENEFICIAL, 0x036edc));
        DYNAMO      = register("dynamo", new DynamoStatusEffect(StatusEffectCategory.BENEFICIAL, 0xffbf00));
        PAIN_CYCLE  = register("pain_cycle", new PainCycleStatusEffect(StatusEffectCategory.NEUTRAL, 0x640004));
        SHADOW_FORM = register("shadow_form", new ShadowFormStatusEffect(StatusEffectCategory.BENEFICIAL, 0x40023e));
    }

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(id), statusEffect);
    }
}
