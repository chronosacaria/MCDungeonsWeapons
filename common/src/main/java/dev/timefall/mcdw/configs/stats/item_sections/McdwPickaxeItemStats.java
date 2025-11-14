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
public class McdwPickaxeItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats pickaxeDiamondPickaxeVar    = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.DIAMOND,  1,  -2.8f,  0.0d, 10,   Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats pickaxeHailingPinnacle      = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.DIAMOND,  1,  -2.8f,  0.0d, 10,   Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats pickaxeHowlingPick          = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,     1,  -2.8f,  0.0d, 10,   Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats pickaxeMountineerPick       = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,     1,  -2.8f,  0.0d, 10,   Identifier.of("minecraft:iron_ingot"));

    public IMcdwWeaponStats.MeleeStats getPickaxeDiamondPickaxeVar() {
        return pickaxeDiamondPickaxeVar;
    }

    public IMcdwWeaponStats.MeleeStats getPickaxeHailingPinnacle() {
        return pickaxeHailingPinnacle;
    }

    public IMcdwWeaponStats.MeleeStats getPickaxeHowlingPick() {
        return pickaxeHowlingPick;
    }

    public IMcdwWeaponStats.MeleeStats getPickaxeMountineerPick() {
        return pickaxeMountineerPick;
    }

}
