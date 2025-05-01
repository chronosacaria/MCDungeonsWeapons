/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwSoulDaggerItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwSoulDaggerItemRegistry {

    public static final Identifier SOUL_DAGGER_ETERNAL_KNIFE_ID        = Mcdw.ID("soul_dagger_eternal_knife");
    public static final Identifier SOUL_DAGGER_SOUL_KNIFE_ID           = Mcdw.ID("soul_dagger_soul_knife");
    public static final Identifier SOUL_DAGGER_TRUTHSEEKER_ID          = Mcdw.ID("soul_dagger_truthseeker");

    public static final McdwSoulDaggerItem SOUL_DAGGER_ETERNAL_KNIFE   = register(SOUL_DAGGER_ETERNAL_KNIFE_ID, McdwWeaponStatsConfig.CONFIG.getSoulDaggerItemStats().getSoul_dagger_eternal_knife());
    public static final McdwSoulDaggerItem SOUL_DAGGER_SOUL_KNIFE      = register(SOUL_DAGGER_SOUL_KNIFE_ID, McdwWeaponStatsConfig.CONFIG.getSoulDaggerItemStats().getSoul_dagger_soul_knife());
    public static final McdwSoulDaggerItem SOUL_DAGGER_TRUTHSEEKER     = register(SOUL_DAGGER_TRUTHSEEKER_ID, McdwWeaponStatsConfig.CONFIG.getSoulDaggerItemStats().getSoul_dagger_truthseeker());

    public static void register() {

    }

    private static McdwSoulDaggerItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwSoulDaggerItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwSoulDaggerItem(
                    stats,
                    stats.material,
                    new Item.Settings()
                            .maxDamage(stats.material.getDurability())
                            .rarity(RarityHelper.fromToolMaterial(stats.material))
                            .maxCount(1)
                            .attributeModifiers(
                                    CleanlinessHelper.createBaseWithRangeAttributeModifiers(
                                            stats.material,
                                            stats.damage,
                                            stats.attackSpeed,
                                            stats.additionalAttackRange
                                    )
                            )
            );
        }
        return new McdwSoulDaggerItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                SwordItem.createAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}