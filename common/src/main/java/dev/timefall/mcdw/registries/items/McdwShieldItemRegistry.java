/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwShieldItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class McdwShieldItemRegistry {

    public static final Identifier SHIELD_ROYAL_GUARD_SHIELD_ID   = Mcdw.ID("shield_royal_guard_shield");
    public static final Identifier SHIELD_TOWER_GUARD_SHIELD_ID   = Mcdw.ID("shield_tower_guard_shield");
    public static final Identifier SHIELD_VANGUARD_SHIELD_ID      = Mcdw.ID("shield_vanguard_shield");

    public static final McdwShieldItem SHIELD_ROYAL_GUARD_SHIELD  = register(SHIELD_ROYAL_GUARD_SHIELD_ID, McdwWeaponStatsConfig.CONFIG.getShieldItemStats().getShieldRoyalGuardShield());
    public static final McdwShieldItem SHIELD_TOWER_GUARD_SHIELD  = register(SHIELD_TOWER_GUARD_SHIELD_ID, McdwWeaponStatsConfig.CONFIG.getShieldItemStats().getShieldTowerGuardShield());
    public static final McdwShieldItem SHIELD_VANGUARD_SHIELD     = register(SHIELD_VANGUARD_SHIELD_ID, McdwWeaponStatsConfig.CONFIG.getShieldItemStats().getShieldVanguardShield());

    public static void register() {

    }

    private static McdwShieldItem register(Identifier id, IMcdwWeaponStats.ShieldStats stats) {
        McdwWeaponStatsConfig.CONFIG.registerItemEnableCheck(id,() -> stats.isEnabled);
        return Registry.register(Registries.ITEM, id, makeWeapon(stats));
    }

    private static McdwShieldItem makeWeapon(IMcdwWeaponStats.ShieldStats stats) {
        return new McdwShieldItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(250 + stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
        );
    }
}