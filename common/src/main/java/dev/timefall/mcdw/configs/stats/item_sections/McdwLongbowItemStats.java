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
public class McdwLongbowItemStats extends ConfigSection {
    private IMcdwWeaponStats.RangedStats longbowGuardianBow = IMcdwWeaponStats.rangedStats(true, true,  ToolMaterials.DIAMOND, 8, 30, 19, 5,    Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.RangedStats longbowLongbow     = IMcdwWeaponStats.rangedStats(true, true,  ToolMaterials.IRON,    7, 25, 17, 10,   Identifier.of("minecraft:planks"));
    private IMcdwWeaponStats.RangedStats longbowRedSnake    = IMcdwWeaponStats.rangedStats(true, true,  ToolMaterials.DIAMOND, 7, 30, 18, 5,    Identifier.of("minecraft:diamond"));

    public IMcdwWeaponStats.RangedStats getLongbowGuardianBow() {
        return longbowGuardianBow;
    }

    public IMcdwWeaponStats.RangedStats getLongbowLongbow() {
        return longbowLongbow;
    }

    public IMcdwWeaponStats.RangedStats getLongbowRedSnake() {
        return longbowRedSnake;
    }
}
