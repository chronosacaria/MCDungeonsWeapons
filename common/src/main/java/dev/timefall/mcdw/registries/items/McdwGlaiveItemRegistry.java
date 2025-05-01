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
import dev.timefall.mcdw.bases.McdwGlaiveItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwGlaiveItemRegistry {

    public static final Identifier GLAIVE_CACKLING_BROOM_ID     = Mcdw.ID("glaive_cackling_broom");
    public static final Identifier GLAIVE_GLAIVE_ID             = Mcdw.ID("glaive_glaive");
    public static final Identifier GLAIVE_GRAVE_BANE_ID         = Mcdw.ID("glaive_grave_bane");
    public static final Identifier GLAIVE_VENOM_GLAIVE_ID       = Mcdw.ID("glaive_venom_glaive");

    public static final McdwGlaiveItem GLAIVE_CACKLING_BROOM    = register(GLAIVE_CACKLING_BROOM_ID, McdwWeaponStatsConfig.CONFIG.getGlaiveItemStats().getGlaiveCacklingBroom());
    public static final McdwGlaiveItem GLAIVE_GLAIVE            = register(GLAIVE_GLAIVE_ID, McdwWeaponStatsConfig.CONFIG.getGlaiveItemStats().getGlaiveGlaive());
    public static final McdwGlaiveItem GLAIVE_GRAVE_BANE        = register(GLAIVE_GRAVE_BANE_ID, McdwWeaponStatsConfig.CONFIG.getGlaiveItemStats().getGlaiveGraveBane());
    public static final McdwGlaiveItem GLAIVE_VENOM_GLAIVE      = register(GLAIVE_VENOM_GLAIVE_ID, McdwWeaponStatsConfig.CONFIG.getGlaiveItemStats().getGlaiveVenomGlaive());

    public static void register() {

    }

    private static McdwGlaiveItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }


    private static McdwGlaiveItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwGlaiveItem(
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
        return new McdwGlaiveItem(
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