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
public class McdwSoulDaggerItemStats extends ConfigSection {
    private IMcdwWeaponStats.MeleeStats soul_dagger_eternal_knife   = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.NETHERITE,    4,  -2.8f,  -1.0d,  5,     Identifier.of("minecraft:netherite_scrap"));
    private IMcdwWeaponStats.MeleeStats soul_dagger_soul_knife      = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.IRON,         4,  -2.8f,  -1.0d,  10,    Identifier.of("minecraft:iron_ingot"));
    private IMcdwWeaponStats.MeleeStats soul_dagger_truthseeker     = IMcdwWeaponStats.meleeStats(true,  true,  ToolMaterials.NETHERITE,    3,  -2.8f,   0.0d,  5,     Identifier.of("minecraft:netherite_scrap"));

    public IMcdwWeaponStats.MeleeStats getSoul_dagger_eternal_knife() {
        return soul_dagger_eternal_knife;
    }

    public IMcdwWeaponStats.MeleeStats getSoul_dagger_soul_knife() {
        return soul_dagger_soul_knife;
    }

    public IMcdwWeaponStats.MeleeStats getSoul_dagger_truthseeker() {
        return soul_dagger_truthseeker;
    }
}
