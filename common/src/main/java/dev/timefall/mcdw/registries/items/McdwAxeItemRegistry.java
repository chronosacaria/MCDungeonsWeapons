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
import dev.timefall.mcdw.bases.McdwAxeItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwAxeItemRegistry {

    public static final Identifier AXE_ANCHOR_ID            = Mcdw.ID("axe_anchor");
    public static final Identifier AXE_HIGHLAND_ID          = Mcdw.ID("axe_highland");
    public static final Identifier AXE_AXE_ID               = Mcdw.ID("axe_axe");
    public static final Identifier AXE_ENCRUSTED_ANCHOR_ID  = Mcdw.ID("axe_encrusted_anchor");
    public static final Identifier AXE_FIREBRAND_ID         = Mcdw.ID("axe_firebrand");

    public static final McdwAxeItem AXE_ANCHOR              = register(AXE_ANCHOR_ID, McdwWeaponStatsConfig.CONFIG.getAxeItemStats().getAxeAnchor());
    public static final McdwAxeItem AXE_HIGHLAND            = register(AXE_HIGHLAND_ID, McdwWeaponStatsConfig.CONFIG.getAxeItemStats().getAxeHighland());
    public static final McdwAxeItem AXE_AXE                 = register(AXE_AXE_ID, McdwWeaponStatsConfig.CONFIG.getAxeItemStats().getAxeAxe());
    public static final McdwAxeItem AXE_ENCRUSTED_ANCHOR    = register(AXE_ENCRUSTED_ANCHOR_ID, McdwWeaponStatsConfig.CONFIG.getAxeItemStats().getAxeEncrustedAnchor());
    public static final McdwAxeItem AXE_FIREBRAND           = register(AXE_FIREBRAND_ID, McdwWeaponStatsConfig.CONFIG.getAxeItemStats().getAxeFirebrand());

    public static void register() {
    }

    private static McdwAxeItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwAxeItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwAxeItem(
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
        return new McdwAxeItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                AxeItem.createAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}