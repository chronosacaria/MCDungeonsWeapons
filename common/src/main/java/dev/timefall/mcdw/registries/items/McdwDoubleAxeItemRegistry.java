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
import dev.timefall.mcdw.bases.McdwDoubleAxeItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwDoubleAxeItemRegistry {

    public static final Identifier DOUBLE_AXE_CURSED_ID         = Mcdw.ID("double_axe_cursed");
    public static final Identifier DOUBLE_AXE_DOUBLE_ID         = Mcdw.ID("double_axe_double");
    public static final Identifier DOUBLE_AXE_WHIRLWIND_ID      = Mcdw.ID("double_axe_whirlwind");

    public static final McdwDoubleAxeItem DOUBLE_AXE_CURSED     = register(DOUBLE_AXE_CURSED_ID, McdwWeaponStatsConfig.CONFIG.getDoubleAxeItemStats().getDoubleAxeCursed());
    public static final McdwDoubleAxeItem DOUBLE_AXE_DOUBLE     = register(DOUBLE_AXE_DOUBLE_ID, McdwWeaponStatsConfig.CONFIG.getDoubleAxeItemStats().getDoubleAxeDouble());
    public static final McdwDoubleAxeItem DOUBLE_AXE_WHIRLWIND  = register(DOUBLE_AXE_WHIRLWIND_ID, McdwWeaponStatsConfig.CONFIG.getDoubleAxeItemStats().getDoubleAxeWhirlwind());

    public static void register() {

    }

    private static McdwDoubleAxeItem register(Identifier id, IMcdwWeaponStats.MeleeStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }


    private static McdwDoubleAxeItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwDoubleAxeItem(
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
        return new McdwDoubleAxeItem(
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