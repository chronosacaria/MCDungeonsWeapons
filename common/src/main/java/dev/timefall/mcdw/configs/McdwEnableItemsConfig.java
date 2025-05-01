/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.configs;

import dev.timefall.mcdw.enums.ItemsID;

import java.util.LinkedHashMap;

public class McdwEnableItemsConfig {
    public final LinkedHashMap<ItemsID, Boolean> ITEMS_ENABLED = new LinkedHashMap<>();

    public McdwEnableItemsConfig() {
        for (ItemsID itemsID : ItemsID.values())
            ITEMS_ENABLED.put(itemsID, true);
    }
}
