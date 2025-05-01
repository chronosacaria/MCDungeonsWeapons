/*
 * Timefall Development License 1.2
 * Copyright (c) 2025. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModConstants {

    public static final String MOD_ID = "mcdw";
    public static Identifier id(String path){
        return Identifier.of(MOD_ID, path);
    }
    public static final RegistryKey<ItemGroup> MELEE = RegistryKey.of(RegistryKeys.ITEM_GROUP, ModConstants.id("melee"));
    public static final RegistryKey<ItemGroup> RANGED = RegistryKey.of(RegistryKeys.ITEM_GROUP, ModConstants.id("ranged"));
    public static final RegistryKey<ItemGroup> SHIELDS = RegistryKey.of(RegistryKeys.ITEM_GROUP, ModConstants.id("shields"));

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

}