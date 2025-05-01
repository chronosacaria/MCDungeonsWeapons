/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.client;


import dev.timefall.mcdw.bases.*;
import dev.timefall.mcdw.enums.EnchantmentsID;
import dev.timefall.mcdw.registries.EnchantsRegistry;
import dev.timefall.mcdw.registries.StatusEffectsRegistry;
import dev.timefall.mcdw.registries.SummonedEntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

@Environment(EnvType.CLIENT)
public class McdwClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SummonedEntityRegistry.SUMMONED_BEE_ENTITY, SummonedBeeRenderer::new);
        ParticlesRegistry.registerOnClient();

        Arrays.stream(McdwBowItemRegistry.values()).forEach(bowsID -> registerRangedWeaponPredicates(bowsID.getItem()));
        Arrays.stream(McdwShortbowItemRegistry.values()).forEach(shortBowsID -> registerRangedWeaponPredicates(shortBowsID.getItem()));
        Arrays.stream(McdwLongbowItemRegistry.values()).forEach(longBowsID -> registerRangedWeaponPredicates(longBowsID.getItem()));
        Arrays.stream(McdwCrossbowItemRegistry.values()).forEach(crossbowsID -> registerRangedWeaponPredicates(crossbowsID.getItem()));
        Arrays.stream(McdwShieldItemRegistry.values()).forEach(shieldsID -> registerShieldPredicates(shieldsID.getItem()));
    }

    public static void registerRangedWeaponPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.of("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else if (item instanceof McdwBowItem bow) {
                return calculateDrawSpeed(itemStack, livingEntity, bow.getDrawSpeed());
            } else if (item instanceof McdwShortbowItem shortbow) {
                return calculateDrawSpeed(itemStack, livingEntity, shortbow.getDrawSpeed());
            } else if (item instanceof McdwLongbowItem longbow) {
                return calculateDrawSpeed(itemStack, livingEntity, longbow.getDrawSpeed());
            } else if (item instanceof McdwCrossbowItem crossbow) {
                return calculateDrawSpeed(itemStack, livingEntity, crossbow.getDrawSpeed());
            }
            return 0.0F;
        });

        if (item instanceof BowItem) {
            ModelPredicateProviderRegistry.register(item, Identifier.of("pulling"), (itemStack, clientWorld, livingEntity, seed) ->
                    livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
        }
        if (item instanceof CrossbowItem) {
            ModelPredicateProviderRegistry.register(item, Identifier.of("pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !McdwCrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
                }
            });

            ModelPredicateProviderRegistry.register(item, Identifier.of("charged"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return McdwCrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
                }
            });

            ModelPredicateProviderRegistry.register(item, Identifier.of("firework"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return McdwCrossbowItem.isCharged(itemStack) && McdwCrossbowItem.hasProjectile(itemStack,
                            Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                }
            });
        }
    }
    public static void registerShieldPredicates(McdwShieldItem shield) {
        ModelPredicateProviderRegistry.register(shield, Identifier.of("blocking"),
                (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() &&
                        livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F
        );
    }
    private static float calculateDrawSpeed(ItemStack itemStack, LivingEntity livingEntity, float drawSpeed) {
        int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ACCELERATE).mcdw$getIsEnabled()) {
            int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ACCELERATE), itemStack);
            if (accelerateLevel > 0) {
                StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
            }
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.OVERCHARGE).mcdw$getIsEnabled()) {
            int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.OVERCHARGE), itemStack);
            if (overchargeLevel > 0) {
                int overcharge = (int) Math.min((useTicks / drawSpeed) - 1, overchargeLevel);
                useTicks = overcharge == overchargeLevel ? useTicks : (int) (useTicks % drawSpeed);
            }
        }
        if (itemStack.getItem() instanceof BowItem)
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (float) useTicks / drawSpeed;
        if (itemStack.getItem() instanceof McdwCrossbowItem)
            return McdwCrossbowItem.isCharged(itemStack) ? 0.0F : (float) useTicks / (float) McdwCrossbowItem.getPullTime(itemStack);
        return drawSpeed;
    }
}
