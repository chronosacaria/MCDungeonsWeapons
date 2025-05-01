/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.configs;

import net.fabricmc.loader.api.FabricLoader;

public class CompatibilityFlags {
    public static boolean noOffhandConflicts = true;
    public static boolean isReachExtensionEnabled = true;

    public static void init() {
        if(FabricLoader.getInstance().isModLoaded("dualwielding") || FabricLoader.getInstance().isModLoaded("bettercombat")) {
            noOffhandConflicts = false;
        }
        if(FabricLoader.getInstance().isModLoaded("bettercombat")) {
            isReachExtensionEnabled = false;
        }
    }
}
