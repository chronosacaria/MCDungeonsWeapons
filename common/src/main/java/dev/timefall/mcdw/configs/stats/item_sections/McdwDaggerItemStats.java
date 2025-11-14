/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

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
public class McdwDaggerItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats daggerBackstabber           = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.DIAMOND,    1,  -1.7f,   -1.0d,    10,    Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats daggerChillGaleKnife        = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.DIAMOND,    2,  -2.2f,   -1.0d,    5,     Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats daggerDagger                = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.IRON,       1,  -1.5f,   -1.0d,    10,    Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats daggerFangsOfFrost          = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.IRON,       1,  -1.5f,   -1.0d,    5,     Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats daggerMoon                  = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.IRON,       1,  -1.5f,   -1.0d,    5,     Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats daggerResoluteTempestKnife  = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.IRON,       2,  -2.2f,   -1.0d,    5,     Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats daggerShearDagger           = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.IRON,       0,  -1.5f,   -1.0d,    10,    Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats daggerSwiftStriker          = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.NETHERITE,  1,  -1.7f,   -1.0d,    5,     Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats daggerTempestKnife          = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.IRON,       2,  -2.2f,   -1.0d,    10,    Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats daggerTheBeginning          = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.NETHERITE,  1,  -1.8f,   -1.0d,    5,     Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats daggerTheEnd                = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.NETHERITE,  1,  -1.8f,   -1.0d,    5,     Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats daggerVoidTouchedBlade      = IMcdwWeaponStats.meleeStats(true, true, ToolMaterials.DIAMOND,    1,  -1.8f,   -1.0d,    10,    Identifier.of("minecraft:diamond"));

    public IMcdwWeaponStats.MeleeStats getDaggerBackstabber() {
        return daggerBackstabber;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerChillGaleKnife() {
        return daggerChillGaleKnife;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerDagger() {
        return daggerDagger;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerFangsOfFrost() {
        return daggerFangsOfFrost;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerMoon() {
        return daggerMoon;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerResoluteTempestKnife() {
        return daggerResoluteTempestKnife;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerShearDagger() {
        return daggerShearDagger;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerSwiftStriker() {
        return daggerSwiftStriker;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerTempestKnife() {
        return daggerTempestKnife;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerTheBeginning() {
        return daggerTheBeginning;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerTheEnd() {
        return daggerTheEnd;
    }

    public IMcdwWeaponStats.MeleeStats getDaggerVoidTouchedBlade() {
        return daggerVoidTouchedBlade;
    }
}
