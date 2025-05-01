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
import dev.timefall.mcdw.bases.McdwStaffItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwStaffItemRegistry {

    public static final Identifier STAFF_BATTLESTAFF_ID           = Mcdw.ID("staff_battlestaff");
    public static final Identifier STAFF_BATTLESTAFF_OF_TERROR_ID = Mcdw.ID("staff_battlestaff_of_terror");
    public static final Identifier STAFF_GROWING_STAFF_ID         = Mcdw.ID("staff_battlestaff_growing_staff");

    public static final McdwStaffItem STAFF_BATTLESTAFF           = register(STAFF_BATTLESTAFF_ID, McdwWeaponStatsConfig.CONFIG.getStaffItemStats().getStaffBattlestaff());
    public static final McdwStaffItem STAFF_BATTLESTAFF_OF_TERROR = register(STAFF_BATTLESTAFF_OF_TERROR_ID, McdwWeaponStatsConfig.CONFIG.getStaffItemStats().getStaffBattlestaffOfTerror());
    public static final McdwStaffItem STAFF_GROWING_STAFF         = register(STAFF_GROWING_STAFF_ID, McdwWeaponStatsConfig.CONFIG.getStaffItemStats().getStaffGrowingStaff());

    public static void register() {

    }

    private static McdwStaffItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwStaffItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwStaffItem(
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

        return new McdwStaffItem(
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