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
import dev.timefall.mcdw.bases.McdwSickleItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwSickleItemRegistry {

    public static final Identifier SICKLE_LAST_LAUGH_GOLD_ID    = Mcdw.ID("sickle_last_laugh_gold");
    public static final Identifier SICKLE_LAST_LAUGH_SILVER_ID  = Mcdw.ID("sickle_last_laugh_silver");
    public static final Identifier SICKLE_NIGHTMARES_BITE_ID    = Mcdw.ID("sickle_nightmares_bite");
    public static final Identifier SICKLE_SICKLE_ID             = Mcdw.ID("sickle_sickle");

    public static final McdwSickleItem SICKLE_LAST_LAUGH_GOLD   = register(SICKLE_LAST_LAUGH_GOLD_ID, McdwWeaponStatsConfig.CONFIG.getSickleItemStats().getSickleLastLaughGold());
    public static final McdwSickleItem SICKLE_LAST_LAUGH_SILVER = register(SICKLE_LAST_LAUGH_SILVER_ID, McdwWeaponStatsConfig.CONFIG.getSickleItemStats().getSickleLastLaughSilver());
    public static final McdwSickleItem SICKLE_NIGHTMARES_BITE   = register(SICKLE_NIGHTMARES_BITE_ID, McdwWeaponStatsConfig.CONFIG.getSickleItemStats().getSickleNightmaresBite());
    public static final McdwSickleItem SICKLE_SICKLE            = register(SICKLE_SICKLE_ID, McdwWeaponStatsConfig.CONFIG.getSickleItemStats().getSickleSickle());

    public static void register() {
    }

    private static McdwSickleItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwSickleItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwSickleItem(
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
        return new McdwSickleItem(
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