package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;

import java.util.ArrayList;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public class McdwNewLoottables {

    public static final ArrayList<String> COMMON_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdwNewlootConfig.commonLootTables.get(SettingsID.COMMON_LOOT_TABLES));
    public static final ArrayList<String> UNCOMMON_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdwNewlootConfig.uncommonLootTables.get(SettingsID.UNCOMMON_LOOT_TABLES));
    public static final ArrayList<String> RARE_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdwNewlootConfig.rareLootTables.get(SettingsID.RARE_LOOT_TABLES));
    public static final ArrayList<String> EPIC_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdwNewlootConfig.epicLootTables.get(SettingsID.EPIC_LOOT_TABLES));

    public static void init() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:entities/bee".equals(id.toString()) && CONFIG.mcdwEnableItemsConfig.itemsEnabled.get(ItemsID.ITEM_BEE_STINGER)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 1.0F))
                        .with(ItemEntry.builder(ItemsInit.mcdwItems.get(ItemsID.ITEM_BEE_STINGER)));

                supplier.pool(poolBuilder);
            }

            if ("minecraft:entities/witch".equals(id.toString()) && CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(GlaivesID.SPEAR_CACKLING_BROOM)) {
                LootPool poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.20f))
                        .with(ItemEntry.builder(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM)))
                        .build();
                supplier.withPool(poolBuilder);
            }

            if ("minecraft:entities/wither".equals(id.toString()) && CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_ANCIENT_BOW)) {
                LootPool poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.1f))
                        .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW)))
                        .build();
                supplier.withPool(poolBuilder);
            }

            FabricLootPoolBuilder poolBuilder;

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                for (int i = 0; i < COMMON_LOOT_TABLES.size(); i++) {
                    if (COMMON_LOOT_TABLES.get(i).equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        if (CONFIG.mcdwEnableItemsConfig.axesEnabled.get(AxesID.AXE)) {
                            addWeapon(poolBuilder, ItemsInit.axeItems.get(AxesID.AXE),
                                    CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_DAGGER)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_DAGGER),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_DAGGER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled.get(DoubleAxesID.AXE_DOUBLE)) {
                            addWeapon(poolBuilder, ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_DOUBLE),
                                    CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates.get(DoubleAxesID.AXE_DOUBLE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.gauntletsEnabled.get(GauntletsID.GAUNTLET_GAUNTLET)) {
                            addWeapon(poolBuilder, ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_GAUNTLET),
                                    CONFIG.mcdwNewlootConfig.gauntletSpawnRates.get(GauntletsID.GAUNTLET_GAUNTLET));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(GlaivesID.SPEAR_GLAIVE)) {
                            addWeapon(poolBuilder, ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GLAIVE),
                                    CONFIG.mcdwNewlootConfig.glaiveSpawnRates.get(GlaivesID.SPEAR_GLAIVE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.spearsEnabled.get(SpearsID.SPEAR_SPEAR)) {
                            addWeapon(poolBuilder, ItemsInit.spearItems.get(SpearsID.SPEAR_SPEAR),
                                    CONFIG.mcdwNewlootConfig.spearSpawnRates.get(SpearsID.SPEAR_SPEAR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(SicklesID.SICKLE_SICKLE)) {
                            addWeapon(poolBuilder, ItemsInit.sickleItems.get(SicklesID.SICKLE_SICKLE),
                                    CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_SICKLE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.stavesEnabled.get(StavesID.STAFF_BATTLESTAFF)) {
                            addWeapon(poolBuilder, ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF),
                                    CONFIG.mcdwNewlootConfig.staffSpawnRates.get(StavesID.STAFF_BATTLESTAFF));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.axesEnabled.get(AxesID.AXE_ANCHOR)) {
                            addWeapon(poolBuilder, ItemsInit.axeItems.get(AxesID.AXE_ANCHOR),
                                    CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_ANCHOR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_MACE)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_MACE),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_MACE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_GREAT)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_GREAT),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_GREAT));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_IRON_SWORD_VAR)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_IRON_SWORD_VAR),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_IRON_SWORD_VAR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_KATANA)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_KATANA),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_KATANA));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_RAPIER)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_RAPIER),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_RAPIER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_CUTLASS)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_CUTLASS),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_CUTLASS));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.longBowsEnabled.get(LongBowsID.BOW_LONGBOW)) {
                            addWeapon(poolBuilder, ItemsInit.longBowItems.get(LongBowsID.BOW_LONGBOW),
                                    CONFIG.mcdwNewlootConfig.longBowSpawnRates.get(LongBowsID.BOW_LONGBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.shortBowsEnabled.get(ShortBowsID.BOW_SHORTBOW)) {
                            addWeapon(poolBuilder, ItemsInit.shortBowItems.get(ShortBowsID.BOW_SHORTBOW),
                                    CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_SHORTBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.picksEnabled.get(PicksID.PICK_MOUNTAINEER_PICK)) {
                            addWeapon(poolBuilder, ItemsInit.pickItems.get(PicksID.PICK_MOUNTAINEER_PICK),
                                    CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_MOUNTAINEER_PICK));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.picksEnabled.get(PicksID.PICK_HOWLING_PICK)) {
                            addWeapon(poolBuilder, ItemsInit.pickItems.get(PicksID.PICK_HOWLING_PICK),
                                    CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_HOWLING_PICK));
                        }

                        supplier.pool(poolBuilder);
                    }
                }
            }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                for (int i = 0; i < UNCOMMON_LOOT_TABLES.size(); i++) {
                    if (UNCOMMON_LOOT_TABLES.get(i).equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        if (CONFIG.mcdwEnableItemsConfig.whipsEnabled.get(WhipsID.WHIP_WHIP)) {
                            addWeapon(poolBuilder, ItemsInit.whipItems.get(WhipsID.WHIP_WHIP),
                                    CONFIG.mcdwNewlootConfig.whipSpawnRates.get(WhipsID.WHIP_WHIP));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.whipsEnabled.get(WhipsID.WHIP_VINE_WHIP)) {
                            addWeapon(poolBuilder, ItemsInit.whipItems.get(WhipsID.WHIP_VINE_WHIP),
                                    CONFIG.mcdwNewlootConfig.whipSpawnRates.get(WhipsID.WHIP_VINE_WHIP));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.picksEnabled.get(PicksID.PICK_HOWLING_PICK)) {
                            addWeapon(poolBuilder, ItemsInit.pickItems.get(PicksID.PICK_HOWLING_PICK),
                                    CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_HOWLING_PICK));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled.get(DoubleAxesID.AXE_CURSED)) {
                            addWeapon(poolBuilder, ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_CURSED),
                                    CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates.get(DoubleAxesID.AXE_CURSED));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_BONECLUB)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_BONECLUB),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_BONECLUB));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_BONE_CUDGEL)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_BONE_CUDGEL),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_BONE_CUDGEL));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.scythesEnabled.get(ScythesID.SICKLE_SKULL_SCYTHE)) {
                            addWeapon(poolBuilder, ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE),
                                    CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_SKULL_SCYTHE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(SicklesID.SICKLE_NIGHTMARES_BITE)) {
                            addWeapon(poolBuilder, ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE),
                                    CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_NIGHTMARES_BITE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_BROADSWORD)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_BROADSWORD),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_BROADSWORD));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_CLAYMORE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_CLAYMORE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_CLAYMORE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_NAMELESS_BLADE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_NAMELESS_BLADE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_DIAMOND_SWORD_VAR),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_DIAMOND_SWORD_VAR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_SINISTER)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_SINISTER),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_SINISTER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_BROKEN_SAWBLADE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_BROKEN_SAWBLADE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_BROKEN_SAWBLADE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_MECHANIZED_SAWBLADE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_MECHANIZED_SAWBLADE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_MECHANIZED_SAWBLADE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.scythesEnabled.get(ScythesID.SICKLE_JAILORS_SCYTHE)) {
                            addWeapon(poolBuilder, ItemsInit.scytheItems.get(ScythesID.SICKLE_JAILORS_SCYTHE),
                                    CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_JAILORS_SCYTHE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_BONEBOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_BONEBOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_BONEBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_HUNTERS_PROMISE)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_HUNTERS_PROMISE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_HUNTING_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_HUNTING_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_HUNTING_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_MASTERS_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_MASTERS_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_MASTERS_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_POWER_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_POWER_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_POWER_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_SNOW_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_SNOW_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_SNOW_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_SOUL_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_SOUL_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_SOUL_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_WIND_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_WIND_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_WIND_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_TWISTING_VINE_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_TWISTING_VINE_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_TWISTING_VINE_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_WEEPING_VINE_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_WEEPING_VINE_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_WEEPING_VINE_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_BUBBLE_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_BUBBLE_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_THE_SLICER)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_THE_SLICER),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_THE_SLICER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_AZURE_SEEKER)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AZURE_SEEKER),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_AZURE_SEEKER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW));
                        }
                               /* .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shieldSpawnRates.get(ShieldsID.SHIELD_VANGUARD)))
                                .with(ItemEntry.builder(ItemsInit.shieldItems.get(ShieldsID.SHIELD_VANGUARD)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shieldSpawnRates.get(ShieldsID.SHIELD_ROYAL_GUARD)))
                                .with(ItemEntry.builder(ItemsInit.shieldItems.get(ShieldsID.SHIELD_ROYAL_GUARD))); */

                        supplier.pool(poolBuilder);
                    }
                }
           }

            /*if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                for (int i = 0; i < RARE_LOOT_TABLES.size(); i++) {
                    if (RARE_LOOT_TABLES.get(i).equals(id.toString())) {

                        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_HIGHLAND)))
                                .with(ItemEntry.builder(ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_FIREBRAND)))
                                .with(ItemEntry.builder(ItemsInit.axeItems.get(AxesID.AXE_FIREBRAND)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_ENCRUSTED_ANCHOR)))
                                .with(ItemEntry.builder(ItemsInit.axeItems.get(AxesID.AXE_ENCRUSTED_ANCHOR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_FANGS_OF_FROST)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_FANGS_OF_FROST)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_MOON)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_MOON)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_SHEAR_DAGGER)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_SHEAR_DAGGER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_TEMPEST_KNIFE)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_TEMPEST_KNIFE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_CHILL_GALE_KNIFE)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_CHILL_GALE_KNIFE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_FANGS_OF_FROST)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_FANGS_OF_FROST)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates.get(DoubleAxesID.AXE_WHIRLWIND)))
                                .with(ItemEntry.builder(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_WHIRLWIND)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.gauntletSpawnRates.get(GauntletsID.GAUNTLET_MAULERS)))
                                .with(ItemEntry.builder(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_MAULERS)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.glaiveSpawnRates.get(GlaivesID.SPEAR_GRAVE_BANE)))
                                .with(ItemEntry.builder(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GRAVE_BANE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.glaiveSpawnRates.get(GlaivesID.SPEAR_VENOM_GLAIVE)))
                                .with(ItemEntry.builder(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_FLAIL)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_FLAIL)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_DIAMOND_PICKAXE_VAR)))
                                .with(ItemEntry.builder(ItemsInit.pickItems.get(PicksID.PICK_DIAMOND_PICKAXE_VAR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_HAILING_PINNACLE)))
                                .with(ItemEntry.builder(ItemsInit.pickItems.get(PicksID.PICK_HAILING_PINNACLE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_FROST_SCYTHE)))
                                .with(ItemEntry.builder(ItemsInit.scytheItems.get(ScythesID.SICKLE_FROST_SCYTHE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_LAST_LAUGH_SILVER)))
                                .with(ItemEntry.builder(ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_LAST_LAUGH_GOLD)))
                                .with(ItemEntry.builder(ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.spearSpawnRates.get(SpearsID.SPEAR_WHISPERING_SPEAR)))
                                .with(ItemEntry.builder(ItemsInit.spearItems.get(SpearsID.SPEAR_WHISPERING_SPEAR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.spearSpawnRates.get(SpearsID.SPEAR_FORTUNE)))
                                .with(ItemEntry.builder(ItemsInit.spearItems.get(SpearsID.SPEAR_FORTUNE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.staffSpawnRates.get(StavesID.STAFF_GROWING_STAFF)))
                                .with(ItemEntry.builder(ItemsInit.staffItems.get(StavesID.STAFF_GROWING_STAFF)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.staffSpawnRates.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR)))
                                .with(ItemEntry.builder(ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_FROST_SLAYER)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_FROST_SLAYER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_HEARTSTEALER)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_HEARTSTEALER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_GREAT_AXEBLADE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_GREAT_AXEBLADE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_BEESTINGER)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_BEESTINGER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_FREEZING_FOIL)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_FREEZING_FOIL)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_DANCERS_SWORD)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_DANCERS_SWORD)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_MASTERS_KATANA)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_MASTERS_KATANA)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_HAWKBRAND)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_HAWKBRAND)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_CORAL_BLADE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_CORAL_BLADE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_SPONGE_STRIKER)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_ELITE_POWER_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_ELITE_POWER_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_NOCTURNAL_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_NOCTURNAL_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_SABREWING)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_SABREWING)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_GREEN_MENACE)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_GREEN_MENACE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_PINK_SCOUNDREL)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_PINK_SCOUNDREL)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_TRICKBOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_TRICKBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_TWIN_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_TWIN_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_WINTERS_TOUCH)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_WINTERS_TOUCH)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_SHIVERING_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_SHIVERING_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_ECHO_OF_THE_VALLEY)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_ECHO_OF_THE_VALLEY)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_BURST_GALE_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_BURST_GALE_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_BUBBLE_BURSTER)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BURSTER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_VOID_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_VOID_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_CALL_OF_THE_VOID)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_PHANTOM_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_WEB_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_WEB_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.longBowSpawnRates.get(LongBowsID.BOW_GUARDIAN_BOW)))
                                .with(ItemEntry.builder(ItemsInit.longBowItems.get(LongBowsID.BOW_GUARDIAN_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.longBowSpawnRates.get(LongBowsID.BOW_RED_SNAKE)))
                                .with(ItemEntry.builder(ItemsInit.longBowItems.get(LongBowsID.BOW_RED_SNAKE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW)))
                                .with(ItemEntry.builder(ItemsInit.shortBowItems.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_LOVE_SPELL_BOW)))
                                .with(ItemEntry.builder(ItemsInit.shortBowItems.get(ShortBowsID.BOW_LOVE_SPELL_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_PURPLE_STORM)))
                                .with(ItemEntry.builder(ItemsInit.shortBowItems.get(ShortBowsID.BOW_PURPLE_STORM)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_COG_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_COG_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shieldSpawnRates.get(ShieldsID.SHIELD_VANGUARD)))
                                .with(ItemEntry.builder(ItemsInit.shieldItems.get(ShieldsID.SHIELD_VANGUARD)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shieldSpawnRates.get(ShieldsID.SHIELD_ROYAL_GUARD)))
                                .with(ItemEntry.builder(ItemsInit.shieldItems.get(ShieldsID.SHIELD_ROYAL_GUARD)));

                        supplier.pool(poolBuilder);
                    }
                }
            }*/

            /*if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                for (int i = 0; i < EPIC_LOOT_TABLES.size(); i++) {
                    if (EPIC_LOOT_TABLES.get(i).equals(id.toString())) {

                        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_BACKSTABBER)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_BACKSTABBER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_SWIFT_STRIKER)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_SWIFT_STRIKER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_THE_BEGINNING)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_THE_END)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.gauntletSpawnRates.get(GauntletsID.GAUNTLET_SOUL_FISTS)))
                                .with(ItemEntry.builder(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_SOUL_FISTS)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_STORMLANDER)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_STORMLANDER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_GRAVITY)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_GRAVITY)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates.get(SoulDaggersID.DAGGER_SOUL_KNIFE)))
                                .with(ItemEntry.builder(ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_SOUL_KNIFE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE)))
                                .with(ItemEntry.builder(ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates.get(SoulDaggersID.SWORD_TRUTHSEEKER)))
                                .with(ItemEntry.builder(ItemsInit.soulDaggerItems.get(SoulDaggersID.SWORD_TRUTHSEEKER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_SUNS_GRACE)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_SUNS_GRACE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_SOUL_SCYTHE)))
                                .with(ItemEntry.builder(ItemsInit.scytheItems.get(ScythesID.SICKLE_SOUL_SCYTHE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_DARK_KATANA)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_DARK_KATANA)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_THE_STARLESS_NIGHT)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_THE_STARLESS_NIGHT)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_ANCIENT_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_LOST_SOULS)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_HAUNTED_BOW)))
                                .with(ItemEntry.builder(ItemsInit.bowItems.get(BowsID.BOW_HAUNTED_BOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS)))
                                .with(ItemEntry.builder(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS)));

                        supplier.pool(poolBuilder);
                    }
                }
            }*/
        }));
    }

    public static void addWeapon(FabricLootPoolBuilder poolBuilder, Item weapon, float p){
        poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
        poolBuilder.withEntry(ItemEntry.builder(weapon).build());
    }
}


