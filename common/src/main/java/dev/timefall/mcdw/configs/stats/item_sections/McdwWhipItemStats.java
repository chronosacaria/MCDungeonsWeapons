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
public class McdwWhipItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats whipVineWhip  = IMcdwWeaponStats.meleeStats(true,   true,   ToolMaterials.IRON, 5,  -3.1f,  1.5d,   5,  Identifier.of("minecraft:vine"));
    private IMcdwWeaponStats.MeleeStats whipWhip      = IMcdwWeaponStats.meleeStats(true,   true,   ToolMaterials.IRON, 3,  -3.1f,  1.5d,   10, Identifier.of("minecraft:string"));

    public IMcdwWeaponStats.MeleeStats getWhipWhip(){
        return whipWhip;
    }
    public IMcdwWeaponStats.MeleeStats getWhipVineWhip(){
        return whipVineWhip;
    }
    
}
