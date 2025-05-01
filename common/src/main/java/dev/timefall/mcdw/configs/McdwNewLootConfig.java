/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.configs;

import dev.timefall.mcdw.enums.SettingsID;

import java.util.LinkedHashMap;

public class McdwNewLootConfig{

    public final LinkedHashMap<SettingsID, Boolean> WEAPONS_ENABLED_IN_LOOTTABLES = new LinkedHashMap<>();
    public final LinkedHashMap<SettingsID, String[]> COMMON_LOOT_TABLES = new LinkedHashMap<>();
    public final LinkedHashMap<SettingsID, String[]> UNCOMMON_LOOT_TABLES = new LinkedHashMap<>();
    public final LinkedHashMap<SettingsID, String[]> RARE_LOOT_TABLES = new LinkedHashMap<>();
    public final LinkedHashMap<SettingsID, String[]> EPIC_LOOT_TABLES = new LinkedHashMap<>();

    public McdwNewLootConfig(){
        WEAPONS_ENABLED_IN_LOOTTABLES.put(SettingsID.ENABLE_WEAPONS_IN_LOOT_TABLES, true);

        /* LOOT TABLES */

        // COMMON
        COMMON_LOOT_TABLES.put(SettingsID.COMMON_LOOT_TABLES, new String[] {
                "minecraft:chests/abandoned_mineshaft",
                "minecraft:chests/shipwreck_supply",
                "minecraft:chests/shipwreck_treasure",
                "minecraft:chests/desert_pyramid",
                "minecraft:chests/village/village_weaponsmith"});

        //UNCOMMON
        UNCOMMON_LOOT_TABLES.put(SettingsID.UNCOMMON_LOOT_TABLES, new String[] {
                "minecraft:chests/jungle_temple",
                "minecraft:chests/nether_bridge",
                "minecraft:chests/bastion_bridge",
                "minecraft:chests/bastion_other",
                "minecraft:chests/bastion_treasure"});

        //RARE
        RARE_LOOT_TABLES.put(SettingsID.RARE_LOOT_TABLES, new String[] {
                "minecraft:chests/underwater_ruin_small",
                "minecraft:chests/underwater_ruin_big",
                "minecraft:chests/ruined_portal",
                "minecraft:chests/simple_dungeon",
                "minecraft:chests/igloo_chest",
                "minecraft:chests/pillager_outpost"});

        //EPIC
        EPIC_LOOT_TABLES.put(SettingsID.EPIC_LOOT_TABLES, new String[] {
                "minecraft:chests/stronghold_corridor",
                "minecraft:chests/stronghold_crossing",
                "minecraft:chests/stronghold_library",
                "minecraft:chests/end_city_treasure"});

    }

}
