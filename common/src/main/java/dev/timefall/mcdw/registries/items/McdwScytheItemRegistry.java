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
import dev.timefall.mcdw.bases.McdwScytheItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwScytheItemRegistry {

    public static final Identifier SCYTHE_FROST_SCYTHE_ID      = Mcdw.ID("scythe_frost_scythe");
    public static final Identifier SCYTHE_JAILORS_SCYTHE_ID    = Mcdw.ID("scythe_jailors_scythe");
    public static final Identifier SCYTHE_SKULL_SCYTHE_ID      = Mcdw.ID("scythe_skull_scythe");
    public static final Identifier SCYTHE_SOUL_SCYTHE_ID       = Mcdw.ID("scythe_soul_scythe");

    public static final McdwScytheItem SCYTHE_FROST_SCYTHE     = register(SCYTHE_FROST_SCYTHE_ID, McdwWeaponStatsConfig.CONFIG.getScytheItemStats().getScytheFrostScythe());
    public static final McdwScytheItem SCYTHE_JAILORS_SCYTHE   = register(SCYTHE_JAILORS_SCYTHE_ID, McdwWeaponStatsConfig.CONFIG.getScytheItemStats().getScytheJailorsScythe());
    public static final McdwScytheItem SCYTHE_SKULL_SCYTHE     = register(SCYTHE_SKULL_SCYTHE_ID, McdwWeaponStatsConfig.CONFIG.getScytheItemStats().getScytheSkullScythe());
    public static final McdwScytheItem SCYTHE_SOUL_SCYTHE      = register(SCYTHE_SOUL_SCYTHE_ID, McdwWeaponStatsConfig.CONFIG.getScytheItemStats().getScytheSoulScythe());

    public static void register() {

    }

    private static McdwScytheItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwScytheItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwScytheItem(
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
        return new McdwScytheItem(
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