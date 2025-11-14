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
public class McdwBowItemStats extends ConfigSection {
    private IMcdwWeaponStats.RangedStats bowAncientBow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,    7,  14, 18, 0,  Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats bowBonebow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.STONE,        4,  16, 12, 5,  Identifier.of("minecraft:bone"));
    private IMcdwWeaponStats.RangedStats bowBubbleBow       = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  15, 12, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowBubbleBurster   = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  15, 13, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowBurstGaleBow    = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  12, 16, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowCallOfTheVoid   = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,    6,  15, 16, 5,  Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats bowEchoOfTheValley = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  11, 16, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowElitePowerBow   = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  20, 15, 5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowGreenMenace     = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  17, 13, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowHauntedBow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,    7,  18, 16, 5,  Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats bowHuntersPromise  = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  15, 16, 5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowHuntingBow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  16, 15, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowLostSouls       = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.NETHERITE,    7,  12, 17, 5,  Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.RangedStats bowMastersBow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  17, 16, 5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowNocturnalBow    = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  17, 14, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowPhantomBow      = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  20, 14, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowPinkScoundrel   = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  17, 13, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowPowerBow        = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  20, 14, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowSabrewing       = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  10, 13, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowShiveringBow    = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  14, 15, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowSnowBow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         4,  16, 13, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowSoulBow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         4,  14, 15, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowTrickbow        = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      5,  12, 12, 10, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowTwinBow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      5,  12, 12, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowTwistingVineBow = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         4,  15, 13, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowVoidBow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  15, 16, 10, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowWebBow          = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  15, 12, 10, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowWeepingVineBow  = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON,         5,  15, 13, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats bowWindBow         = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  11, 15, 10, Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats bowWintersTouch    = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.DIAMOND,      6,  15, 14, 5,  Identifier.of("minecraft:diamond"));

    public IMcdwWeaponStats.RangedStats getBowAncientBow() {
        return bowAncientBow;
    }

    public IMcdwWeaponStats.RangedStats getBowBonebow() {
        return bowBonebow;
    }

    public IMcdwWeaponStats.RangedStats getBowBubbleBow() {
        return bowBubbleBow;
    }

    public IMcdwWeaponStats.RangedStats getBowBubbleBurster() {
        return bowBubbleBurster;
    }

    public IMcdwWeaponStats.RangedStats getBowBurstGaleBow() {
        return bowBurstGaleBow;
    }

    public IMcdwWeaponStats.RangedStats getBowCallOfTheVoid() {
        return bowCallOfTheVoid;
    }

    public IMcdwWeaponStats.RangedStats getBowEchoOfTheValley() {
        return bowEchoOfTheValley;
    }

    public IMcdwWeaponStats.RangedStats getBowElitePowerBow() {
        return bowElitePowerBow;
    }

    public IMcdwWeaponStats.RangedStats getBowGreenMenace() {
        return bowGreenMenace;
    }

    public IMcdwWeaponStats.RangedStats getBowHauntedBow() {
        return bowHauntedBow;
    }

    public IMcdwWeaponStats.RangedStats getBowHuntersPromise() {
        return bowHuntersPromise;
    }

    public IMcdwWeaponStats.RangedStats getBowHuntingBow() {
        return bowHuntingBow;
    }

    public IMcdwWeaponStats.RangedStats getBowLostSouls() {
        return bowLostSouls;
    }

    public IMcdwWeaponStats.RangedStats getBowMastersBow() {
        return bowMastersBow;
    }

    public IMcdwWeaponStats.RangedStats getBowNocturnalBow() {
        return bowNocturnalBow;
    }

    public IMcdwWeaponStats.RangedStats getBowPhantomBow() {
        return bowPhantomBow;
    }

    public IMcdwWeaponStats.RangedStats getBowPinkScoundrel() {
        return bowPinkScoundrel;
    }

    public IMcdwWeaponStats.RangedStats getBowPowerBow() {
        return bowPowerBow;
    }

    public IMcdwWeaponStats.RangedStats getBowSabrewing() {
        return bowSabrewing;
    }

    public IMcdwWeaponStats.RangedStats getBowShiveringBow() {
        return bowShiveringBow;
    }

    public IMcdwWeaponStats.RangedStats getBowSnowBow() {
        return bowSnowBow;
    }

    public IMcdwWeaponStats.RangedStats getBowSoulBow() {
        return bowSoulBow;
    }

    public IMcdwWeaponStats.RangedStats getBowTrickbow() {
        return bowTrickbow;
    }

    public IMcdwWeaponStats.RangedStats getBowTwinBow() {
        return bowTwinBow;
    }

    public IMcdwWeaponStats.RangedStats getBowTwistingVineBow() {
        return bowTwistingVineBow;
    }

    public IMcdwWeaponStats.RangedStats getBowVoidBow() {
        return bowVoidBow;
    }

    public IMcdwWeaponStats.RangedStats getBowWebBow() {
        return bowWebBow;
    }

    public IMcdwWeaponStats.RangedStats getBowWeepingVineBow() {
        return bowWeepingVineBow;
    }

    public IMcdwWeaponStats.RangedStats getBowWindBow() {
        return bowWindBow;
    }

    public IMcdwWeaponStats.RangedStats getBowWintersTouch() {
        return bowWintersTouch;
    }
}
