/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
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
public class McdwCrossbowItemStats extends ConfigSection {
    private IMcdwWeaponStats.RangedStats crossbowAutoCrossbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowAzureSeeker            = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        10,   28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowBabyCrossbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        8,    23,   7,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowBurstCrossbow          = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowButterflyCrossbow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        10,   28,   9,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowCogCrossbow            = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        10,   28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowCorruptedCrossbow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,   16,   22,   14,   5, Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats crossbowDoomCrossbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,   9,    26,   8,    5, Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats crossbowDualCrossbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        8,    24,   7,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowExplodingCrossbow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowFeralSoulCrossbow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        10,   28,   9,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowFireboltThrower        = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowHarpoonCrossbow        = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        12,   28,   11,   5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowHarpCrossbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        10,   28,   9,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowHeavyCrossbow          = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowImplodingCrossbow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowLightningHarpCrossbow  = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     16,   28,   14,   5, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats crossbowNauticalCrossbow       = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     16,   24,   14,   5, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats crossbowPrideOfThePiglins      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,   15,   20,   13,   5, Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats crossbowRapidCrossbow          = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    20,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowScatterCrossbow        = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowShadowCrossbow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     14,   25,   12,   5, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats crossbowSlayerCrossbow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     10,   26,   9,    5, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats crossbowSoulCrossbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        9,    28,   8,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowSoulHunterCrossbow     = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     12,   28,   11,   5, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats crossbowSpellboundCrossbow     = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        10,   28,   9,    5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowTheSlicer              = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,        12,   28,   10,   5, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats crossbowVeiledCrossbow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     16,   22,   15,   5, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats crossbowVoidcallerCrossbow     = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,     14,   26,   13,   5, Identifier.of("minecraft:diamond"));

    public IMcdwWeaponStats.RangedStats getCrossbowAutoCrossbow() {
        return crossbowAutoCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowAzureSeeker() {
        return crossbowAzureSeeker;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowBabyCrossbow() {
        return crossbowBabyCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowBurstCrossbow() {
        return crossbowBurstCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowButterflyCrossbow() {
        return crossbowButterflyCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowCogCrossbow() {
        return crossbowCogCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowCorruptedCrossbow() {
        return crossbowCorruptedCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowDoomCrossbow() {
        return crossbowDoomCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowDualCrossbow() {
        return crossbowDualCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowExplodingCrossbow() {
        return crossbowExplodingCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowFeralSoulCrossbow() {
        return crossbowFeralSoulCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowFireboltThrower() {
        return crossbowFireboltThrower;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowHarpoonCrossbow() {
        return crossbowHarpoonCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowHarpCrossbow() {
        return crossbowHarpCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowHeavyCrossbow() {
        return crossbowHeavyCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowImplodingCrossbow() {
        return crossbowImplodingCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowLightningHarpCrossbow() {
        return crossbowLightningHarpCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowNauticalCrossbow() {
        return crossbowNauticalCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowPrideOfThePiglins() {
        return crossbowPrideOfThePiglins;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowRapidCrossbow() {
        return crossbowRapidCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowScatterCrossbow() {
        return crossbowScatterCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowShadowCrossbow() {
        return crossbowShadowCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowSlayerCrossbow() {
        return crossbowSlayerCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowSoulCrossbow() {
        return crossbowSoulCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowSoulHunterCrossbow() {
        return crossbowSoulHunterCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowSpellboundCrossbow() {
        return crossbowSpellboundCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowTheSlicer() {
        return crossbowTheSlicer;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowVeiledCrossbow() {
        return crossbowVeiledCrossbow;
    }

    public IMcdwWeaponStats.RangedStats getCrossbowVoidcallerCrossbow() {
        return crossbowVoidcallerCrossbow;
    }
}
