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
public class McdwScytheItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats scytheFrostScythe   = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.DIAMOND,  4,  -2.9f,  0.5d,5,     Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats scytheJailorsScythe = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.IRON,     4,  -2.9f,  0.5d,10,    Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats scytheSkullScythe   = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.DIAMOND,  4,  -2.9f,  0.5d,5,     Identifier.of("minecraft:diamond"));
    private IMcdwWeaponStats.MeleeStats scytheSoulScythe    = IMcdwWeaponStats.meleeStats(true, true,   ToolMaterials.DIAMOND,  3,  -2.9f,  0.5d,5,     Identifier.of("minecraft:diamond"));

    public IMcdwWeaponStats.MeleeStats getScytheFrostScythe() {
        return scytheFrostScythe;
    }

    public IMcdwWeaponStats.MeleeStats getScytheJailorsScythe() {
        return scytheJailorsScythe;
    }

    public IMcdwWeaponStats.MeleeStats getScytheSkullScythe() {
        return scytheSkullScythe;
    }

    public IMcdwWeaponStats.MeleeStats getScytheSoulScythe() {
        return scytheSoulScythe;
    }
}
