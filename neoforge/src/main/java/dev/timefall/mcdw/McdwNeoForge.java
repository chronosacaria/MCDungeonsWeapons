/*
 * Timefall Development License 1.2
 * Copyright (c) 2025. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ModConstants.MOD_ID)
public class McdwNeoForge {
    public McdwNeoForge(IEventBus modEventBus) {
        McdwCommon.initialize();

        /*
         * Game Event Bus Events
         */


        /*
         * Mod Event Bus Events
         */

        McdwCommon.LOGGER.info("Initializing MCDW on NeoForge!");
    }
}
