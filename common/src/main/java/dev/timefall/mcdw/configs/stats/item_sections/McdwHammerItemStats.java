/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.configs.stats.item_sections;

import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwHammerItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats hammerBoneclub      = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON,         7,  -3.2f,  0.0d, 10,   Identifier.of("minecraft:bone_block"));
    private IMcdwWeaponStats.MeleeStats hammerBoneCudgel    = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.NETHERITE,    7,  -3.2f,  0.0d, 5,    Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats hammerFlail         = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON,         5,  -2.8f,  0.0d, 5,    Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats hammerGravity       = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.DIAMOND,      6,  -3.2f,  0.0d, 5,    Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats hammerGreatHammer   = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON,         6,  -3.2f,  0.0d, 10,   Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats hammerMace          = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON,         5,  -2.8f,  0.0d, 10,   Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats hammerStormlander   = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.DIAMOND,      7,  -3.2f,  0.0d, 5,    Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats hammerSunsGrace     = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.DIAMOND,      4,  -2.8f,  0.0d, 5,    Identifier.of("minecraft:diamond"));

    public IMcdwWeaponStats.MeleeStats getHammerBoneclub() {
        return hammerBoneclub;
    }

    public IMcdwWeaponStats.MeleeStats getHammerBoneCudgel() {
        return hammerBoneCudgel;
    }

    public IMcdwWeaponStats.MeleeStats getHammerFlail() {
        return hammerFlail;
    }

    public IMcdwWeaponStats.MeleeStats getHammerGravity() {
        return hammerGravity;
    }

    public IMcdwWeaponStats.MeleeStats getHammerGreatHammer() {
        return hammerGreatHammer;
    }

    public IMcdwWeaponStats.MeleeStats getHammerMace() {
        return hammerMace;
    }

    public IMcdwWeaponStats.MeleeStats getHammerStormlander() {
        return hammerStormlander;
    }

    public IMcdwWeaponStats.MeleeStats getHammerSunsGrace() {
        return hammerSunsGrace;
    }
}
