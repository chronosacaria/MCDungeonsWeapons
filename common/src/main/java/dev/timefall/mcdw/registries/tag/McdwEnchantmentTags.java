/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.tag;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class McdwEnchantmentTags {

    public static final TagKey<Enchantment> AOE_EXCLUSIVE            = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("aoe_exclusive"));
    public static final TagKey<Enchantment> AOE_AND_DAMAGE_EXCLUSIVE = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("aoe_and_damage_exclusive"));
    public static final TagKey<Enchantment> DAMAGE_EXCLUSIVE         = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("damage_exclusive"));
    public static final TagKey<Enchantment> EXPERIENCE_EXCLUSIVE     = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("experience_exclusive"));
    public static final TagKey<Enchantment> HEALING_EXCLUSIVE        = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("healing_exclusive"));
}