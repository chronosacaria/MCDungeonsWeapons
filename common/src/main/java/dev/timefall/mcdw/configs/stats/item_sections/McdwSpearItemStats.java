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
public class McdwSpearItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats spearSpear              = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON, 4,  -2.5f,  1.0d,   10, Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats spearWhisperingSpear    = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON, 5,  -2.5f,  1.0d,   5,  Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats spearFortuneSpear       = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON, 5,  -2.5f,  1.0d,   5,  Identifier.of("minecraft:iron_ingot"));

    public IMcdwWeaponStats.MeleeStats getSpearSpear() {
        return spearSpear;
    }

    public IMcdwWeaponStats.MeleeStats getSpearWhisperingSpear() {
        return spearWhisperingSpear;
    }

    public IMcdwWeaponStats.MeleeStats getSpearFortuneSpear() {
        return spearFortuneSpear;
    }
}
