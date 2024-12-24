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
public class McdwAxeItemStats extends ConfigSection {
    @SuppressWarnings("CanBeFinal")
    private IMcdwWeaponStats.MeleeStats axeAnchor          = IMcdwWeaponStats.meleeStats(
            true,
            true,
            ToolMaterials.IRON,
            8,
            -3.4f,
            0.0d,
            10,
            Identifier.of("minecraft:iron_ingot"));
    @SuppressWarnings("CanBeFinal")
    private IMcdwWeaponStats.MeleeStats axeAxe             = IMcdwWeaponStats.meleeStats(true,  true,   ToolMaterials.IRON,     6,  -3.1f,  0.0d,   10, Identifier.of("minecraft:iron_ingot"));
    @SuppressWarnings("CanBeFinal")
    private IMcdwWeaponStats.MeleeStats axeEncrustedAnchor = IMcdwWeaponStats.meleeStats(true,  true,   ToolMaterials.DIAMOND,  8,  -3.4f,  0.0d,   5,  Identifier.of("minecraft:diamond"));
    @SuppressWarnings("CanBeFinal")
    private IMcdwWeaponStats.MeleeStats axeFirebrand       = IMcdwWeaponStats.meleeStats(true,  true,   ToolMaterials.DIAMOND,  4,  -2.9f,  0.0d,   5,  Identifier.of("minecraft:diamond"));
    @SuppressWarnings("CanBeFinal")
    private IMcdwWeaponStats.MeleeStats axeHighland        = IMcdwWeaponStats.meleeStats(true,  true,   ToolMaterials.IRON,     4,  -2.9f,  0.0d,   5,  Identifier.of("minecraft:iron_ingot"));

    public IMcdwWeaponStats.MeleeStats getAxeAnchor() { return axeAnchor; }
    public IMcdwWeaponStats.MeleeStats getAxeAxe() { return axeAxe; }
    public IMcdwWeaponStats.MeleeStats getAxeEncrustedAnchor() { return axeEncrustedAnchor; }
    public IMcdwWeaponStats.MeleeStats getAxeFirebrand() { return axeFirebrand; }
    public IMcdwWeaponStats.MeleeStats getAxeHighland() { return axeHighland; }
}
