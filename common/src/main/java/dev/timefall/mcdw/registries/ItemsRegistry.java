/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.enums.ItemsID;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

import static dev.timefall.mcdw.Mcdw.ID;

public class ItemsRegistry {

    public static final EnumMap<ItemsID, Item> MCDW_ITEMS = new EnumMap<>(ItemsID.class);

    public static void register() {

        for (ItemsID itemID : ItemsID.values()) {
            //if (!CONFIG.mcdwEnableItemsConfig.ITEMS_ENABLED.get(itemID))
            //    continue;
            Item item = itemID.makeItem(new Item.Settings());
            registerItem(itemID.toString().toLowerCase(Locale.ROOT), item);
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(itemID.getItem()));
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registries.ITEM, ID(id), item);
    }

}
