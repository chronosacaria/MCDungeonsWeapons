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
import dev.timefall.mcdw.bases.McdwWhipItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwWhipItemRegistry {

    public static final Identifier WHIP_WHIP_ID      = Mcdw.ID("whip_whip");
    public static final Identifier WHIP_VINE_WHIP_ID = Mcdw.ID("whip_vine_whip");

    public static final McdwWhipItem WHIP_WHIP       = register(WHIP_WHIP_ID, McdwWeaponStatsConfig.CONFIG.getWhipItemStats().getWhipWhip());
    public static final McdwWhipItem WHIP_VINE_WHIP  = register(WHIP_VINE_WHIP_ID, McdwWeaponStatsConfig.CONFIG.getWhipItemStats().getWhipVineWhip());

    public static void register() {
    }

    private static McdwWhipItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwWhipItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwWhipItem(
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
        return new McdwWhipItem(
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