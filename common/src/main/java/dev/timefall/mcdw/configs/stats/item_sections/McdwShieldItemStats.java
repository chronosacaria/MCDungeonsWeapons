/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.configs.stats.item_sections;

import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;

import java.util.Set;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwShieldItemStats extends ConfigSection {
    private IMcdwWeaponStats.ShieldStats shieldRoyalGuardShield = IMcdwWeaponStats.shieldStats(true,    true,   ToolMaterials.DIAMOND,  5,  Set.of(Identifier.of("minecraft:iron_ingot"), Identifier.of("minecraft:gold_ingot")));
    private IMcdwWeaponStats.ShieldStats shieldTowerGuardShield = IMcdwWeaponStats.shieldStats(true,    true,   ToolMaterials.DIAMOND,  5,  Set.of(Identifier.of("minecraft:iron_ingot"), Identifier.of("minecraft:gold_ingot"), Identifier.of("minecraft:copper_ingot")));
    private IMcdwWeaponStats.ShieldStats shieldVanguardShield   = IMcdwWeaponStats.shieldStats(true,    true,   ToolMaterials.DIAMOND,  5,  Set.of(CleanlinessHelper.mcdw$getItemTagKey("minecraft:planks"), Identifier.of("minecraft:iron_ingot")));

    public IMcdwWeaponStats.ShieldStats getShieldRoyalGuardShield() {
        return shieldRoyalGuardShield;
    }

    public IMcdwWeaponStats.ShieldStats getShieldTowerGuardShield() {
        return shieldTowerGuardShield;
    }

    public IMcdwWeaponStats.ShieldStats getShieldVanguardShield() {
        return shieldVanguardShield;
    }
}
