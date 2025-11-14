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
public class McdwGauntletItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats gauntletGauntlet = IMcdwWeaponStats.meleeStats(true,    true,   ToolMaterials.IRON,        0,  -1.5f,  -1.0d, 10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats gauntletMauler   = IMcdwWeaponStats.meleeStats(true,    true,   ToolMaterials.DIAMOND,     1,  -1.5f,  -1.0d, 5,  Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats gauntletSoulFist = IMcdwWeaponStats.meleeStats(true,    true,   ToolMaterials.NETHERITE,   0,  -1.5f,  -1.0d, 5,  Identifier.of("minecraft:netherite_scrap"));

    public IMcdwWeaponStats.MeleeStats getGauntletGauntlet() { return gauntletGauntlet; }
    public IMcdwWeaponStats.MeleeStats getGauntletMauler() { return gauntletMauler; }
    public IMcdwWeaponStats.MeleeStats getGauntletSoulFist() { return gauntletSoulFist; }
}
