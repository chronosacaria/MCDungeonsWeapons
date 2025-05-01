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
import dev.timefall.mcdw.bases.McdwHammerItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwHammerItemRegistry {

    public static final Identifier HAMMER_BONECLUB_ID       = Mcdw.ID("hammer_boneclub");
    public static final Identifier HAMMER_BONE_CUDGEL_ID    = Mcdw.ID("hammer_bone_cudgel");
    public static final Identifier HAMMER_FLAIL_ID          = Mcdw.ID("hammer_flail");
    public static final Identifier HAMMER_GRAVITY_ID        = Mcdw.ID("hammer_gravity");
    public static final Identifier HAMMER_GREAT_HAMMER_ID   = Mcdw.ID("hammer_great_hammer");
    public static final Identifier HAMMER_MACE_ID           = Mcdw.ID("hammer_mace");
    public static final Identifier HAMMER_STORMLANDER_ID    = Mcdw.ID("hammer_stormlander");
    public static final Identifier HAMMER_SUNS_GRACE_ID     = Mcdw.ID("hammer_suns_grace");

    public static final McdwHammerItem HAMMER_BONECLUB      = register(HAMMER_BONECLUB_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerBoneclub());
    public static final McdwHammerItem HAMMER_BONE_CUDGEL   = register(HAMMER_BONE_CUDGEL_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerBoneCudgel());
    public static final McdwHammerItem HAMMER_FLAIL         = register(HAMMER_FLAIL_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerFlail());
    public static final McdwHammerItem HAMMER_GRAVITY       = register(HAMMER_GRAVITY_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerGravity()) ;
    public static final McdwHammerItem HAMMER_GREAT_HAMMER  = register(HAMMER_GREAT_HAMMER_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerGreatHammer());
    public static final McdwHammerItem HAMMER_MACE          = register(HAMMER_MACE_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerMace());
    public static final McdwHammerItem HAMMER_STORMLANDER   = register(HAMMER_STORMLANDER_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerStormlander());
    public static final McdwHammerItem HAMMER_SUNS_GRACE    = register(HAMMER_SUNS_GRACE_ID, McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerSunsGrace());

    public static void register() {

    }

    private static McdwHammerItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }


    private static McdwHammerItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwHammerItem(
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
        return new McdwHammerItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                CleanlinessHelper.createBaseAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}