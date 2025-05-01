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
import dev.timefall.mcdw.bases.McdwSpearItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwSpearItemRegistry {

    public static final Identifier SPEAR_SPEAR_ID             = Mcdw.ID("spear_spear");
    public static final Identifier SPEAR_WHISPERING_SPEAR_ID  = Mcdw.ID("spear_whispering_spear");
    public static final Identifier SPEAR_FORTUNE_SPEAR_ID     = Mcdw.ID("spear_fortune_spear");

    public static final McdwSpearItem SPEAR_SPEAR             = register(SPEAR_SPEAR_ID, McdwWeaponStatsConfig.CONFIG.getSpearItemStats().getSpearSpear());
    public static final McdwSpearItem SPEAR_WHISPERING_SPEAR  = register(SPEAR_WHISPERING_SPEAR_ID, McdwWeaponStatsConfig.CONFIG.getSpearItemStats().getSpearWhisperingSpear());
    public static final McdwSpearItem SPEAR_FORTUNE_SPEAR     = register(SPEAR_FORTUNE_SPEAR_ID, McdwWeaponStatsConfig.CONFIG.getSpearItemStats().getSpearFortuneSpear());

    public static void register() {

    }

    private static McdwSpearItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwSpearItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwSpearItem(
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
        return new McdwSpearItem(
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