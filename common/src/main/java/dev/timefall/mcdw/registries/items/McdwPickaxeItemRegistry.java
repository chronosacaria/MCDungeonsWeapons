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
import dev.timefall.mcdw.bases.McdwPickaxeItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwPickaxeItemRegistry {

    public static final Identifier PICK_DIAMOND_PICKAXE_VAR_ID      = Mcdw.ID("pickaxe_diamond_pickaxe_var");
    public static final Identifier PICK_HAILING_PINNACLE_ID         = Mcdw.ID("pickaxe_hailing_pinnacle");
    public static final Identifier PICK_HOWLING_PICK_ID             = Mcdw.ID("pickaxe_howling_pick");
    public static final Identifier PICK_MOUNTAINEER_PICK_ID         = Mcdw.ID("pickaxe_mountineer_pick");

    public static final McdwPickaxeItem PICK_DIAMOND_PICKAXE_VAR    = register(PICK_DIAMOND_PICKAXE_VAR_ID, McdwWeaponStatsConfig.CONFIG.getPickaxeItemStats().getPickaxeDiamondPickaxeVar());
    public static final McdwPickaxeItem PICK_HAILING_PINNACLE       = register(PICK_HAILING_PINNACLE_ID, McdwWeaponStatsConfig.CONFIG.getPickaxeItemStats().getPickaxeHailingPinnacle());
    public static final McdwPickaxeItem PICK_HOWLING_PICK           = register(PICK_HOWLING_PICK_ID, McdwWeaponStatsConfig.CONFIG.getPickaxeItemStats().getPickaxeHowlingPick());
    public static final McdwPickaxeItem PICK_MOUNTAINEER_PICK       = register(PICK_MOUNTAINEER_PICK_ID, McdwWeaponStatsConfig.CONFIG.getPickaxeItemStats().getPickaxeMountineerPick());

    public static void register() {

    }

    private static McdwPickaxeItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwPickaxeItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwPickaxeItem(
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
        return new McdwPickaxeItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                PickaxeItem.createAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}