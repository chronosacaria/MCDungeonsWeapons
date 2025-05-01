/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import dev.timefall.mcdw.enums.ItemsID;
import dev.timefall.mcdw.registries.items.*;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

import java.util.List;

public class LootTablesRegistry {

    //public static final ArrayList<String> COMMON_LOOT_TABLES =
    //        new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.COMMON_LOOT_TABLES.get(SettingsID.COMMON_LOOT_TABLES)));
    //public static final ArrayList<String> UNCOMMON_LOOT_TABLES =
    //        new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.UNCOMMON_LOOT_TABLES.get(SettingsID.UNCOMMON_LOOT_TABLES)));
    //public static final ArrayList<String> RARE_LOOT_TABLES =
    //        new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.RARE_LOOT_TABLES.get(SettingsID.RARE_LOOT_TABLES)));
    //public static final ArrayList<String> EPIC_LOOT_TABLES =
    //        new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.EPIC_LOOT_TABLES.get(SettingsID.EPIC_LOOT_TABLES)));
    //public static final ArrayList<String> NETHER_TABLES =
    //        new ArrayList<>(List.of("minecraft:chests/nether_bridge", "minecraft:chests/bastion_bridge", "minecraft:chests/bastion_other", "minecraft:chests/bastion_treasure"));

    static final List<Item> COMMON_LOOT_POOL = List.of(
            McdwAxeItemRegistry.AXE_ANCHOR,
            McdwAxeItemRegistry.AXE_AXE,
            McdwDaggerItemRegistry.DAGGER_DAGGER,
            McdwDoubleAxeItemRegistry.DOUBLE_AXE_DOUBLE,
            McdwGauntletItemRegistry.GAUNTLET_GAUNTLET,
            McdwGlaiveItemRegistry.GLAIVE_GLAIVE,
            McdwHammerItemRegistry.HAMMER_GREAT_HAMMER,
            McdwHammerItemRegistry.HAMMER_MACE,
            McdwLongbowItemRegistry.LONGBOW_LONGBOW,
            McdwPickaxeItemRegistry.PICK_HOWLING_PICK,
            McdwPickaxeItemRegistry.PICK_MOUNTAINEER_PICK,
            McdwShortbowItemRegistry.SHORTBOW_SHORTBOW,
            McdwSickleItemRegistry.SICKLE_SICKLE,
            McdwSpearItemRegistry.SPEAR_SPEAR,
            McdwStaffItemRegistry.STAFF_BATTLESTAFF,
            McdwSwordItemRegistry.SWORD_CUTLASS,
            McdwSwordItemRegistry.SWORD_IRON_SWORD_VAR,
            McdwSwordItemRegistry.SWORD_KATANA,
            McdwSwordItemRegistry.SWORD_RAPIER
    );

    static final List<Item> UNCOMMON_LOOT_POOL = List.of(
            McdwBowItemRegistry.BOW_BONEBOW,
            McdwBowItemRegistry.BOW_BUBBLE_BOW,
            McdwBowItemRegistry.BOW_HUNTERS_PROMISE,
            McdwBowItemRegistry.BOW_HUNTING_BOW,
            McdwBowItemRegistry.BOW_MASTERS_BOW,
            McdwBowItemRegistry.BOW_POWER_BOW,
            McdwBowItemRegistry.BOW_SNOW_BOW,
            McdwBowItemRegistry.BOW_SOUL_BOW,
            McdwBowItemRegistry.BOW_TWISTING_VINE_BOW,
            McdwBowItemRegistry.BOW_WEEPING_VINE_BOW,
            McdwBowItemRegistry.BOW_WIND_BOW,
            McdwCrossbowItemRegistry.CROSSBOW_AUTO_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_AZURE_SEEKER,
            McdwCrossbowItemRegistry.CROSSBOW_BURST_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_BUTTERFLY_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_DUAL_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_EXPLODING_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_FIREBOLT_THROWER,
            McdwCrossbowItemRegistry.CROSSBOW_HARPOON_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_HARP_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_HEAVY_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_IMPLODING_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_RAPID_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_SCATTER_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_SOUL_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_THE_SLICER,
            McdwDaggerItemRegistry.DAGGER_MOON,
            McdwDoubleAxeItemRegistry.DOUBLE_AXE_CURSED,
            McdwHammerItemRegistry.HAMMER_BONECLUB,
            McdwHammerItemRegistry.HAMMER_BONE_CUDGEL,
            McdwPickaxeItemRegistry.PICK_HOWLING_PICK,
            McdwScytheItemRegistry.SCYTHE_JAILORS_SCYTHE,
            McdwScytheItemRegistry.SCYTHE_SKULL_SCYTHE,
            McdwSickleItemRegistry.SICKLE_NIGHTMARES_BITE,
            McdwSwordItemRegistry.SWORD_BROADSWORD,
            McdwSwordItemRegistry.SWORD_BROKEN_SAWBLADE,
            McdwSwordItemRegistry.SWORD_CLAYMORE,
            McdwSwordItemRegistry.SWORD_DIAMOND_SWORD_VAR,
            McdwSwordItemRegistry.SWORD_MECHANIZED_SAWBLADE,
            McdwSwordItemRegistry.SWORD_NAMELESS_BLADE,
            McdwSwordItemRegistry.SWORD_SINISTER_SWORD,
            McdwWhipItemRegistry.WHIP_VINE_WHIP,
            McdwWhipItemRegistry.WHIP_WHIP
    );

    static final List<Item> RARE_LOOT_POOL = List.of(
            McdwAxeItemRegistry.AXE_ENCRUSTED_ANCHOR,
            McdwAxeItemRegistry.AXE_FIREBRAND,
            McdwAxeItemRegistry.AXE_HIGHLAND,
            McdwBowItemRegistry.BOW_BUBBLE_BURSTER,
            McdwBowItemRegistry.BOW_BURST_GALE_BOW,
            McdwBowItemRegistry.BOW_CALL_OF_THE_VOID,
            McdwBowItemRegistry.BOW_ECHO_OF_THE_VALLEY,
            McdwBowItemRegistry.BOW_ELITE_POWER_BOW,
            McdwBowItemRegistry.BOW_GREEN_MENACE,
            McdwBowItemRegistry.BOW_NOCTURNAL_BOW,
            McdwBowItemRegistry.BOW_PHANTOM_BOW,
            McdwBowItemRegistry.BOW_PINK_SCOUNDREL,
            McdwBowItemRegistry.BOW_SABREWING,
            McdwBowItemRegistry.BOW_SHIVERING_BOW,
            McdwBowItemRegistry.BOW_TRICKBOW,
            McdwBowItemRegistry.BOW_TWIN_BOW,
            McdwBowItemRegistry.BOW_VOID_BOW,
            McdwBowItemRegistry.BOW_WEB_BOW,
            McdwBowItemRegistry.BOW_WINTERS_TOUCH,
            McdwCrossbowItemRegistry.CROSSBOW_BABY_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_COG_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_FERAL_SOUL_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_LIGHTNING_HARP_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_NAUTICAL_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_SHADOW_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_SLAYER_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_SOUL_HUNTER_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_SPELLBOUND_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_VEILED_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_VOIDCALLER_CROSSBOW,
            McdwDaggerItemRegistry.DAGGER_CHILL_GALE_KNIFE,
            McdwDaggerItemRegistry.DAGGER_FANGS_OF_FROST,
            McdwDaggerItemRegistry.DAGGER_RESOLUTE_TEMPEST_KNIFE,
            McdwDaggerItemRegistry.DAGGER_SHEAR_DAGGER,
            McdwDaggerItemRegistry.DAGGER_TEMPEST_KNIFE,
            McdwDoubleAxeItemRegistry.DOUBLE_AXE_WHIRLWIND,
            McdwGauntletItemRegistry.GAUNTLET_MAULER,
            McdwGlaiveItemRegistry.GLAIVE_GRAVE_BANE,
            McdwGlaiveItemRegistry.GLAIVE_VENOM_GLAIVE,
            McdwHammerItemRegistry.HAMMER_FLAIL,
            McdwLongbowItemRegistry.LONGBOW_GUARDIAN_BOW,
            McdwLongbowItemRegistry.LONGBOW_RED_SNAKE,
            McdwPickaxeItemRegistry.PICK_DIAMOND_PICKAXE_VAR,
            McdwScytheItemRegistry.SCYTHE_FROST_SCYTHE,
            McdwShieldItemRegistry.SHIELD_ROYAL_GUARD_SHIELD,
            McdwShieldItemRegistry.SHIELD_VANGUARD_SHIELD,
            McdwShortbowItemRegistry.SHORTBOW_LOVE_SPELL_BOW,
            McdwShortbowItemRegistry.SHORTBOW_MECHANICAL_SHORTBOW,
            McdwShortbowItemRegistry.SHORTBOW_PURPLE_STORM,
            McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD,
            McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD,
            McdwSickleItemRegistry.SICKLE_LAST_LAUGH_SILVER,
            McdwSpearItemRegistry.SPEAR_FORTUNE_SPEAR,
            McdwSpearItemRegistry.SPEAR_WHISPERING_SPEAR,
            McdwStaffItemRegistry.STAFF_BATTLESTAFF_OF_TERROR,
            McdwStaffItemRegistry.STAFF_GROWING_STAFF,
            McdwSwordItemRegistry.SWORD_BEE_STINGER,
            McdwSwordItemRegistry.SWORD_CORAL_BLADE,
            McdwSwordItemRegistry.SWORD_DANCERS_SWORD,
            McdwSwordItemRegistry.SWORD_FREEZING_FOIL,
            McdwSwordItemRegistry.SWORD_FROST_SLAYER,
            McdwSwordItemRegistry.SWORD_GREAT_AXEBLADE,
            McdwSwordItemRegistry.SWORD_HAWKBRAND,
            McdwSwordItemRegistry.SWORD_HEARTSTEALER,
            McdwSwordItemRegistry.SWORD_MASTERS_KATANA,
            McdwSwordItemRegistry.SWORD_SPONGE_STRIKER
    );

    static final List<Item> EPIC_LOOT_POOL = List.of(
            McdwBowItemRegistry.BOW_ANCIENT_BOW,
            McdwBowItemRegistry.BOW_HAUNTED_BOW,
            McdwBowItemRegistry.BOW_LOST_SOULS,
            McdwCrossbowItemRegistry.CROSSBOW_CORRUPTED_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_DOOM_CROSSBOW,
            McdwCrossbowItemRegistry.CROSSBOW_PRIDE_OF_THE_PIGLINS,
            McdwDaggerItemRegistry.DAGGER_BACKSTABBER,
            McdwDaggerItemRegistry.DAGGER_SWIFT_STRIKER,
            McdwDaggerItemRegistry.DAGGER_THE_BEGINNING,
            McdwDaggerItemRegistry.DAGGER_THE_END,
            McdwDaggerItemRegistry.DAGGER_VOID_TOUCHED_BLADE,
            McdwGauntletItemRegistry.GAUNTLET_SOUL_FIST,
            McdwHammerItemRegistry.HAMMER_GRAVITY,
            McdwHammerItemRegistry.HAMMER_STORMLANDER,
            McdwHammerItemRegistry.HAMMER_SUNS_GRACE,
            McdwScytheItemRegistry.SCYTHE_SOUL_SCYTHE,
            McdwSoulDaggerItemRegistry.SOUL_DAGGER_ETERNAL_KNIFE,
            McdwSoulDaggerItemRegistry.SOUL_DAGGER_SOUL_KNIFE,
            McdwSoulDaggerItemRegistry.SOUL_DAGGER_TRUTHSEEKER,
            McdwSwordItemRegistry.SWORD_DARK_KATANA,
            McdwSwordItemRegistry.SWORD_OBSIDIAN_CLAYMORE,
            McdwSwordItemRegistry.SWORD_THE_STARLESS_NIGHT
    );

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (key.equals(EntityType.BEE.getLootTableId()) && source.isBuiltin()) {
                // TODO Find a way to check if the bee has stung.
                //  If it has, don't drop a stinger.
                //  `BeeEntity#hasStung` can be used to check this
                if (McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBeeStinger().isEnabled) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, ItemsRegistry.MCDW_ITEMS.get(ItemsID.ITEM_BEE_STINGER), 1, 1f);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }

            if (key.equals(EntityType.WITCH.getLootTableId()) && source.isBuiltin()) {
                if (McdwWeaponStatsConfig.CONFIG.getGlaiveItemStats().getGlaiveCacklingBroom().isEnabled) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, McdwGlaiveItemRegistry.GLAIVE_CACKLING_BROOM, 1, 0.2F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }

            if (key.equals(EntityType.WITHER.getLootTableId()) && source.isBuiltin()) {
                if (McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowAncientBow().isEnabled) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, McdwBowItemRegistry.BOW_ANCIENT_BOW, 1, 0.1F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }

            //if (CONFIG.mcdwNewlootConfig.WEAPONS_ENABLED_IN_LOOTTABLES.get(SettingsID.ENABLE_WEAPONS_IN_LOOT_TABLES)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(0.10f));
                lootPoolBuilder.bonusRolls(ConstantLootNumberProvider.create(1.2f));

                //if (COMMON_LOOT_TABLES.contains(key.toString())) {
                //    COMMON_LOOT_POOL.forEach(lootId -> addWeaponById(lootPoolBuilder, lootId));
                //} else if (UNCOMMON_LOOT_TABLES.contains(key.toString())) {
                //    UNCOMMON_LOOT_POOL.forEach(lootId -> addWeaponById(lootPoolBuilder, lootId));
                //} else if (RARE_LOOT_TABLES.contains(key.toString())) {
                //    RARE_LOOT_POOL.forEach(lootID -> addWeaponById(lootPoolBuilder, lootID));
                //} else if (EPIC_LOOT_TABLES.contains(key.toString())) {
                //    EPIC_LOOT_POOL.forEach(lootID -> addWeaponById(lootPoolBuilder, lootID));
                //} else if (NETHER_TABLES.contains(key.toString())) {
                //    if (Arrays.stream(lootPoolBuilder.build().entries).noneMatch(lootPoolEntry ->
                //            lootPoolEntry.equals(ItemEntry.builder(McdwCrossbowItemRegistry.CROSSBOW_PRIDE_OF_THE_PIGLINS)
                //                    .weight(McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowPrideOfThePiglins().weaponSpawnWeight)
                //                    .build()))) {
                //        addWeaponById(lootPoolBuilder, McdwCrossbowItemRegistry.CROSSBOW_PRIDE_OF_THE_PIGLINS);
                //    }
                //}

                tableBuilder.pool(lootPoolBuilder.build());
            //}


        });
    }

    public static void addWeapon(LootPool.Builder lootPoolBuilder, Item weapon, int weight) {
        lootPoolBuilder.with(ItemEntry.builder(weapon).weight(weight));
    }
/*
    public static void addWeaponById(LootPool.Builder lootPoolBuilder, IMcdwWeaponID weaponID) {
        if (weaponID.getIsEnabled())
            addWeapon(lootPoolBuilder, weaponID.getItem(), weaponID.getItemSpawnRate());
    }
*/
    public static void addItemDrop(LootPool.Builder lootPoolBuilder, Item item, int count, float probability) {
        if (item == null)
            return;
        lootPoolBuilder.rolls(ConstantLootNumberProvider.create(count));
        lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(probability));
        lootPoolBuilder.with(ItemEntry.builder(item));
    }
}