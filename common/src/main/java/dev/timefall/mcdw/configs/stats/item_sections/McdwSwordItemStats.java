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
public class McdwSwordItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats swordBeeStinger         = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     0,  -1.1f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordFreezingFoil       = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     1,  -1.1f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordRapier             = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     0,  -1.14f, 0.0d,   10, Identifier.of("minecraft:iron_ingot"));

    private IMcdwWeaponStats.MeleeStats swordBroadsword         = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     5,  -3.0f,  0.5d,   10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordClaymore           = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     7,  -3.2f,  0.5d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordFrostSlayer        = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.DIAMOND,  6,  -3.2f,  0.5d,   5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats swordGreatAxeblade      = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     7,  -3.2f,  0.5d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordHeartstealer       = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.DIAMOND,  6,  -3.2f,  0.5d,   5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats swordObsidianClaymore   = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.NETHERITE,6,  -3.3f,  0.5d,   5,  Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats swordTheStarlessNight   = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.NETHERITE,6,  -3.3f,  0.5d,   1,  Identifier.of("minecraft:netherite_scrap"));

    private IMcdwWeaponStats.MeleeStats swordBrokenSawblade     = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     3,  -2.4f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordMechanizedSawblade = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.DIAMOND,  3,  -2.4f,  0.0d,   1,  Identifier.of("minecraft:blaze_rod"));

    private IMcdwWeaponStats.MeleeStats swordCoralBlade         = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     3,  -2.4f,  0.0d,   10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordSpongeStriker      = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.DIAMOND,  3,  -2.4f,  0.0d,   5,  Identifier.of("minecraft:diamond"));

    private IMcdwWeaponStats.MeleeStats swordCutlass            = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     2,  -2.3f,  0.0d,   10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordNamelessBlade      = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     4,  -2.3f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordDancersSword       = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     3,  -2.0f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));

    private IMcdwWeaponStats.MeleeStats swordDarkKatana         = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.NETHERITE,4,  -2.9f,  0.25d,  5,  Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats swordKatana             = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     4,  -2.9f,  0.25d,  10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordMastersKatana      = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.DIAMOND,  4,  -2.9f,  0.25d,  5,  Identifier.of("minecraft:diamond"));

    private IMcdwWeaponStats.MeleeStats swordDiamondSwordVar    = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.DIAMOND,  3,  -2.4f,  0.0d,   10, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats swordHawkbrand          = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     6,  -2.9f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordIronSwordVar       = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     3,  -2.4f,  0.0d,   10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats swordSinisterSword      = IMcdwWeaponStats.meleeStats(true,   true, ToolMaterials.IRON,     6,  -2.9f,  0.0d,   1,  Identifier.of("minecraft:iron_ingot"));

    public IMcdwWeaponStats.MeleeStats getSwordBeeStinger() {
        return swordBeeStinger;
    }

    public IMcdwWeaponStats.MeleeStats getSwordFreezingFoil() {
        return swordFreezingFoil;
    }

    public IMcdwWeaponStats.MeleeStats getSwordRapier() {
        return swordRapier;
    }

    public IMcdwWeaponStats.MeleeStats getSwordBroadsword() {
        return swordBroadsword;
    }

    public IMcdwWeaponStats.MeleeStats getSwordClaymore() {
        return swordClaymore;
    }

    public IMcdwWeaponStats.MeleeStats getSwordFrostSlayer() {
        return swordFrostSlayer;
    }

    public IMcdwWeaponStats.MeleeStats getSwordGreatAxeblade() {
        return swordGreatAxeblade;
    }

    public IMcdwWeaponStats.MeleeStats getSwordHeartstealer() {
        return swordHeartstealer;
    }

    public IMcdwWeaponStats.MeleeStats getSwordObsidianClaymore() {
        return swordObsidianClaymore;
    }

    public IMcdwWeaponStats.MeleeStats getSwordTheStarlessNight() {
        return swordTheStarlessNight;
    }

    public IMcdwWeaponStats.MeleeStats getSwordBrokenSawblade() {
        return swordBrokenSawblade;
    }

    public IMcdwWeaponStats.MeleeStats getSwordMechanizedSawblade() {
        return swordMechanizedSawblade;
    }

    public IMcdwWeaponStats.MeleeStats getSwordCoralBlade() {
        return swordCoralBlade;
    }

    public IMcdwWeaponStats.MeleeStats getSwordSpongeStriker() {
        return swordSpongeStriker;
    }

    public IMcdwWeaponStats.MeleeStats getSwordCutlass() {
        return swordCutlass;
    }

    public IMcdwWeaponStats.MeleeStats getSwordNamelessBlade() {
        return swordNamelessBlade;
    }

    public IMcdwWeaponStats.MeleeStats getSwordDancersSword() {
        return swordDancersSword;
    }

    public IMcdwWeaponStats.MeleeStats getSwordDarkKatana() {
        return swordDarkKatana;
    }

    public IMcdwWeaponStats.MeleeStats getSwordKatana() {
        return swordKatana;
    }

    public IMcdwWeaponStats.MeleeStats getSwordMastersKatana() {
        return swordMastersKatana;
    }

    public IMcdwWeaponStats.MeleeStats getSwordDiamondSwordVar() {
        return swordDiamondSwordVar;
    }

    public IMcdwWeaponStats.MeleeStats getSwordHawkbrand() {
        return swordHawkbrand;
    }

    public IMcdwWeaponStats.MeleeStats getSwordIronSwordVar() {
        return swordIronSwordVar;
    }

    public IMcdwWeaponStats.MeleeStats getSwordSinisterSword() {
        return swordSinisterSword;
    }
}
