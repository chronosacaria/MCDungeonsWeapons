/*
 * Timefall Development License 1.2
 * Copyright (c) 2025. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw;

import dev.timefall.mcdw.data.ConfigEnchantmentEnabledCondition;
import dev.timefall.mcdw.data.ConfigItemEnabledCondition;
import net.fabricmc.api.ModInitializer;

public class McdwFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        McdwCommon.initialize();
        ConfigEnchantmentEnabledCondition.register();
        ConfigItemEnabledCondition.register();
        DefaultAttributeRegistryFabric.registerAttributes();
        McdwFabricEvents.registerEvents();
        LootRegistryFabric.register();
    }
}