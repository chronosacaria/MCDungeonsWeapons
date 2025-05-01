/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enums;

import dev.timefall.mcdw.registries.ItemsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum ItemsID {
    ITEM_BEE_STINGER;

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<ItemsID, Item> getItemsEnum() {
        return ItemsRegistry.MCDW_ITEMS;
    }

    public Item makeItem(Item.Settings settings) {
        Item item = new Item(settings);
        getItemsEnum().put(this, item);
        return item;
    }

    public Item getItem() {
        return getItemsEnum().get(this);
    }
}
