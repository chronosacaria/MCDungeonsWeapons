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
public class McdwShortbowItemStats extends ConfigSection {
    private IMcdwWeaponStats.RangedStats shortbowLoveSpellBow       = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON, 3,  9,  8,  5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats shortbowMechanicalShortbow = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON, 4,  9,  9,  5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats shortbowPurpleStorm        = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON, 3,  9,  8,  5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.RangedStats shortbowShortbow           = IMcdwWeaponStats.rangedStats(true,    true,   ToolMaterials.IRON, 3,  9,  8,  10, Identifier.of("minecraft:planks"));

    public IMcdwWeaponStats.RangedStats getShortbowLoveSpellBow() {
        return shortbowLoveSpellBow;
    }

    public IMcdwWeaponStats.RangedStats getShortbowMechanicalShortbow() {
        return shortbowMechanicalShortbow;
    }

    public IMcdwWeaponStats.RangedStats getShortbowPurpleStorm() {
        return shortbowPurpleStorm;
    }

    public IMcdwWeaponStats.RangedStats getShortbowShortbow() {
        return shortbowShortbow;
    }
}
