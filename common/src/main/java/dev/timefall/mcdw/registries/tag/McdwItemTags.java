/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.tag;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class McdwItemTags {

    public static final TagKey<Item> COMMON_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("common_loot_pool_items"));
    public static final TagKey<Item> UNCOMMON_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("uncommon_loot_pool_items"));
    public static final TagKey<Item> RARE_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("rare_loot_pool_items"));
    public static final TagKey<Item> EPIC_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("epic_loot_pool_items"));

    //except for tridents...?
    public static final TagKey<Item> ANY_WEAPON_ENCHANTABLE = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("any_weapon_enchantable"));

}