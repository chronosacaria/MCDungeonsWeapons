/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
/*
package dev.timefall.mcdw.compat;

import dev.timefall.mcdw.enums.*;
import dev.timefall.mcdw.registries.items.McdwBowItemRegistry;
import dev.timefall.mcdw.registries.items.McdwCrossbowItemRegistry;
import dev.timefall.mcdw.registries.items.McdwLongbowItemRegistry;
import dev.timefall.mcdw.registries.items.McdwShortbowItemRegistry;
import net.fabric_extras.ranged_weapon.api.CustomRangedWeapon;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;

import java.util.HashMap;

public class RangedWeaponAPICompat {
    public static void init() {
        var items = new HashMap<IRangedWeaponID, Item>();
        items.putAll(McdwBowItemRegistry.getItemsEnum());
        items.putAll(McdwShortbowItemRegistry.getItemsEnum());
        items.putAll(McdwLongbowItemRegistry.getItemsEnum());
        items.putAll(McdwCrossbowItemRegistry.getItemsEnum());

        for (var entry: items.entrySet()) {
            var id = entry.getKey();
            if (!id.getIsEnabled()) {
                continue;
            }
            var isCrossbow = id instanceof McdwCrossbowItemRegistry;
            var item = entry.getValue();
            var damage = id.getWeaponItemStats().projectileDamage;
            var speed = id.getWeaponItemStats().drawSpeed;
            float standardPullTime = isCrossbow ? 25F : 20F;
            var pullTime = isCrossbow // Speed seems to have inverse effects on crossbows compared to bows
                    ? speed
                    : standardPullTime * (20.0 / (float)speed);
            var velocity = (id.getWeaponItemStats().range / 15.0f) * 3.0;
            ((CustomRangedWeapon)item).setRangedWeaponConfig(new RangedConfig((int) pullTime, (float) damage, (float) velocity));
        }
    }
}
 */
