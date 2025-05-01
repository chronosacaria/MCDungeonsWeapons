/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwGauntletItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwGauntletItemRegistry {

    public static final Identifier GAUNTLET_GAUNTLET_ID     = Mcdw.ID("gauntlet_gauntlet");
    public static final Identifier GAUNTLET_MAULER_ID       = Mcdw.ID("gauntlet_mauler");
    public static final Identifier GAUNTLET_SOUL_FIST_ID    = Mcdw.ID("gauntlet_soul_fist");

    public static final McdwGauntletItem GAUNTLET_GAUNTLET  = register(GAUNTLET_GAUNTLET_ID, McdwWeaponStatsConfig.CONFIG.getGauntletItemStats().getGauntletGauntlet());
    public static final McdwGauntletItem GAUNTLET_MAULER    = register(GAUNTLET_MAULER_ID, McdwWeaponStatsConfig.CONFIG.getGauntletItemStats().getGauntletMauler());
    public static final McdwGauntletItem GAUNTLET_SOUL_FIST = register(GAUNTLET_SOUL_FIST_ID, McdwWeaponStatsConfig.CONFIG.getGauntletItemStats().getGauntletSoulFist());

    public static void register() {

    }

    private static McdwGauntletItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }


    private static McdwGauntletItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwGauntletItem(
                    stats,
                    stats.material,
                    new Item.Settings()
                            .maxDamage(stats.material.getDurability())
                            .rarity(RarityHelper.fromToolMaterial(stats.material))
                            .maxCount(1)
                            .attributeModifiers(
                                    CleanlinessHelper.createDualWieldWithRangeAttributeModifiers(
                                            stats.material,
                                            stats.damage,
                                            stats.attackSpeed,
                                            stats.additionalAttackRange
                                    )
                            )
            );
        }
        return new McdwGauntletItem(
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