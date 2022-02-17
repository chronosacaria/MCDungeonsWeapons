package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
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
            FabricLootPoolBuilder poolBuilder;

            if (id.getNamespace().equals("minecraft")) {
                switch (id.getPath()) {
                    case "entities/bee" -> {
                        if (CONFIG.mcdwEnableItemsConfig.itemsEnabled.get(ItemsID.ITEM_BEE_STINGER)) {
                            poolBuilder = FabricLootPoolBuilder.builder();
                            addItemDrop(poolBuilder, ItemsInit.mcdwItems.get(ItemsID.ITEM_BEE_STINGER), 1, 1f);
                            supplier.pool(poolBuilder);
                        }
                    }
                    case "entities/witch" -> {
                        if (CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(GlaivesID.SPEAR_CACKLING_BROOM)) {
                            poolBuilder = FabricLootPoolBuilder.builder();
                            addItemDrop(poolBuilder, ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM), 1, 0.2F);
                            supplier.pool(poolBuilder);
                        }
                    }
                    case "entities/wither" -> {
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_ANCIENT_BOW)) {
                            poolBuilder = FabricLootPoolBuilder.builder();
                            addItemDrop(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW), 1, 0.1F);
                            supplier.pool(poolBuilder);
                        }
                    }
                }
            }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {

                for (String commonLootTable : COMMON_LOOT_TABLES) {
                    if (commonLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        if (CONFIG.mcdwEnableItemsConfig.axesEnabled.get(AxesID.AXE_AXE)) {
                            addWeapon(poolBuilder, ItemsInit.axeItems.get(AxesID.AXE_AXE),
                                    CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_AXE));
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
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_GREAT_HAMMER)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_GREAT_HAMMER),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_GREAT_HAMMER));
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

                for (String uncommonLootTable : UNCOMMON_LOOT_TABLES) {
                    if (uncommonLootTable.equals(id.toString())) {
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
                    }
                }

                for (String rareLootTable : RARE_LOOT_TABLES) {
                    if (rareLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        if (CONFIG.mcdwEnableItemsConfig.axesEnabled.get(AxesID.AXE_HIGHLAND)) {
                            addWeapon(poolBuilder, ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND),
                                    CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_HIGHLAND));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.axesEnabled.get(AxesID.AXE_FIREBRAND)) {
                            addWeapon(poolBuilder, ItemsInit.axeItems.get(AxesID.AXE_FIREBRAND),
                                    CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_FIREBRAND));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.axesEnabled.get(AxesID.AXE_ENCRUSTED_ANCHOR)) {
                            addWeapon(poolBuilder, ItemsInit.axeItems.get(AxesID.AXE_ENCRUSTED_ANCHOR),
                                    CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_ENCRUSTED_ANCHOR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_FANGS_OF_FROST)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_FANGS_OF_FROST),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_FANGS_OF_FROST));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_TEMPEST_KNIFE)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_TEMPEST_KNIFE),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_TEMPEST_KNIFE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_SHEAR_DAGGER)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_SHEAR_DAGGER),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_SHEAR_DAGGER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_CHILL_GALE_KNIFE)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_CHILL_GALE_KNIFE),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_CHILL_GALE_KNIFE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled.get(DoubleAxesID.AXE_WHIRLWIND)) {
                            addWeapon(poolBuilder, ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_WHIRLWIND),
                                    CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates.get(DoubleAxesID.AXE_WHIRLWIND));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.gauntletsEnabled.get(GauntletsID.GAUNTLET_MAULERS)) {
                            addWeapon(poolBuilder, ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_MAULERS),
                                    CONFIG.mcdwNewlootConfig.gauntletSpawnRates.get(GauntletsID.GAUNTLET_MAULERS));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(GlaivesID.SPEAR_GRAVE_BANE)) {
                            addWeapon(poolBuilder, ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GRAVE_BANE),
                                    CONFIG.mcdwNewlootConfig.glaiveSpawnRates.get(GlaivesID.SPEAR_GRAVE_BANE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(GlaivesID.SPEAR_VENOM_GLAIVE)) {
                            addWeapon(poolBuilder, ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE),
                                    CONFIG.mcdwNewlootConfig.glaiveSpawnRates.get(GlaivesID.SPEAR_VENOM_GLAIVE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_FLAIL)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_FLAIL),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_FLAIL));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.picksEnabled.get(PicksID.PICK_DIAMOND_PICKAXE_VAR)) {
                            addWeapon(poolBuilder, ItemsInit.pickItems.get(PicksID.PICK_DIAMOND_PICKAXE_VAR),
                                    CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_DIAMOND_PICKAXE_VAR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.picksEnabled.get(PicksID.PICK_HAILING_PINNACLE)) {
                            addWeapon(poolBuilder, ItemsInit.pickItems.get(PicksID.PICK_HAILING_PINNACLE),
                                    CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_HAILING_PINNACLE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.scythesEnabled.get(ScythesID.SICKLE_FROST_SCYTHE)) {
                            addWeapon(poolBuilder, ItemsInit.scytheItems.get(ScythesID.SICKLE_FROST_SCYTHE),
                                    CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_FROST_SCYTHE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(SicklesID.SICKLE_LAST_LAUGH_SILVER)) {
                            addWeapon(poolBuilder, ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER),
                                    CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_LAST_LAUGH_SILVER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(SicklesID.SICKLE_LAST_LAUGH_GOLD)) {
                            addWeapon(poolBuilder, ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD),
                                    CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_LAST_LAUGH_GOLD));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(SicklesID.SICKLE_LAST_LAUGH_GOLD)) {
                            addWeapon(poolBuilder, ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD),
                                    CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_LAST_LAUGH_GOLD));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.spearsEnabled.get(SpearsID.SPEAR_WHISPERING_SPEAR)) {
                            addWeapon(poolBuilder, ItemsInit.spearItems.get(SpearsID.SPEAR_WHISPERING_SPEAR),
                                    CONFIG.mcdwNewlootConfig.spearSpawnRates.get(SpearsID.SPEAR_WHISPERING_SPEAR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.spearsEnabled.get(SpearsID.SPEAR_FORTUNE)) {
                            addWeapon(poolBuilder, ItemsInit.spearItems.get(SpearsID.SPEAR_FORTUNE),
                                    CONFIG.mcdwNewlootConfig.spearSpawnRates.get(SpearsID.SPEAR_FORTUNE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.stavesEnabled.get(StavesID.STAFF_GROWING_STAFF)) {
                            addWeapon(poolBuilder, ItemsInit.staffItems.get(StavesID.STAFF_GROWING_STAFF),
                                    CONFIG.mcdwNewlootConfig.staffSpawnRates.get(StavesID.STAFF_GROWING_STAFF));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.stavesEnabled.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR)) {
                            addWeapon(poolBuilder, ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR),
                                    CONFIG.mcdwNewlootConfig.staffSpawnRates.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_FROST_SLAYER)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_FROST_SLAYER),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_FROST_SLAYER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_HEARTSTEALER)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_HEARTSTEALER),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_HEARTSTEALER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_GREAT_AXEBLADE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_GREAT_AXEBLADE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_GREAT_AXEBLADE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_BEESTINGER)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_BEESTINGER),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_BEESTINGER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_FREEZING_FOIL)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_FREEZING_FOIL),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_FREEZING_FOIL));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_DANCERS_SWORD)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_DANCERS_SWORD),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_DANCERS_SWORD));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_MASTERS_KATANA)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_MASTERS_KATANA),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_MASTERS_KATANA));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_HAWKBRAND)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_HAWKBRAND),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_HAWKBRAND));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_CORAL_BLADE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_CORAL_BLADE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_CORAL_BLADE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_SPONGE_STRIKER)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_SPONGE_STRIKER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_ELITE_POWER_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_ELITE_POWER_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_ELITE_POWER_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_NOCTURNAL_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_NOCTURNAL_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_NOCTURNAL_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_SABREWING)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_SABREWING),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_SABREWING));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_GREEN_MENACE)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_GREEN_MENACE),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_GREEN_MENACE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_PINK_SCOUNDREL)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_PINK_SCOUNDREL),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_PINK_SCOUNDREL));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_TRICKBOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_TRICKBOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_TRICKBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_TWIN_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_TWIN_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_TWIN_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_WINTERS_TOUCH)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_WINTERS_TOUCH),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_WINTERS_TOUCH));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_SHIVERING_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_SHIVERING_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_SHIVERING_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_ECHO_OF_THE_VALLEY)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_ECHO_OF_THE_VALLEY),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_ECHO_OF_THE_VALLEY));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_BURST_GALE_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_BURST_GALE_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_BURST_GALE_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_BUBBLE_BURSTER)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BURSTER),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_BUBBLE_BURSTER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_VOID_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_VOID_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_VOID_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_CALL_OF_THE_VOID)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_CALL_OF_THE_VOID));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_PHANTOM_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_PHANTOM_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_WEB_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_WEB_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_WEB_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.longBowsEnabled.get(LongBowsID.BOW_GUARDIAN_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.longBowItems.get(LongBowsID.BOW_GUARDIAN_BOW),
                                    CONFIG.mcdwNewlootConfig.longBowSpawnRates.get(LongBowsID.BOW_GUARDIAN_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.longBowsEnabled.get(LongBowsID.BOW_RED_SNAKE)) {
                            addWeapon(poolBuilder, ItemsInit.longBowItems.get(LongBowsID.BOW_RED_SNAKE),
                                    CONFIG.mcdwNewlootConfig.longBowSpawnRates.get(LongBowsID.BOW_RED_SNAKE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.shortBowsEnabled.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW)) {
                            addWeapon(poolBuilder, ItemsInit.shortBowItems.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW),
                                    CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.shortBowsEnabled.get(ShortBowsID.BOW_LOVE_SPELL_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.shortBowItems.get(ShortBowsID.BOW_LOVE_SPELL_BOW),
                                    CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_LOVE_SPELL_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.shortBowsEnabled.get(ShortBowsID.BOW_PURPLE_STORM)) {
                            addWeapon(poolBuilder, ItemsInit.shortBowItems.get(ShortBowsID.BOW_PURPLE_STORM),
                                    CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_PURPLE_STORM));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_COG_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_COG_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_COG_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.shieldsEnabled.get(ShieldsID.SHIELD_VANGUARD)) {
                            addWeapon(poolBuilder, ItemsInit.shieldItems.get(ShieldsID.SHIELD_VANGUARD),
                                    CONFIG.mcdwNewlootConfig.shieldSpawnRates.get(ShieldsID.SHIELD_VANGUARD));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.shieldsEnabled.get(ShieldsID.SHIELD_ROYAL_GUARD)) {
                            addWeapon(poolBuilder, ItemsInit.shieldItems.get(ShieldsID.SHIELD_ROYAL_GUARD),
                                    CONFIG.mcdwNewlootConfig.shieldSpawnRates.get(ShieldsID.SHIELD_ROYAL_GUARD));
                        }
                        supplier.pool(poolBuilder);
                    }
                }

                for (String epicLootTable : EPIC_LOOT_TABLES) {
                    if (epicLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_BACKSTABBER)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_BACKSTABBER),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_BACKSTABBER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_SWIFT_STRIKER)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_SWIFT_STRIKER),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_SWIFT_STRIKER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_THE_BEGINNING)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_THE_BEGINNING));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(DaggersID.DAGGER_THE_END)) {
                            addWeapon(poolBuilder, ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END),
                                    CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_THE_END));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.gauntletsEnabled.get(GauntletsID.GAUNTLET_SOUL_FISTS)) {
                            addWeapon(poolBuilder, ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_SOUL_FISTS),
                                    CONFIG.mcdwNewlootConfig.gauntletSpawnRates.get(GauntletsID.GAUNTLET_SOUL_FISTS));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_STORMLANDER)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_STORMLANDER),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_STORMLANDER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_GRAVITY)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_GRAVITY),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_GRAVITY));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled.get(SoulDaggersID.DAGGER_SOUL_KNIFE)) {
                            addWeapon(poolBuilder, ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_SOUL_KNIFE),
                                    CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates.get(SoulDaggersID.DAGGER_SOUL_KNIFE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE)) {
                            addWeapon(poolBuilder, ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE),
                                    CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled.get(SoulDaggersID.SWORD_TRUTHSEEKER)) {
                            addWeapon(poolBuilder, ItemsInit.soulDaggerItems.get(SoulDaggersID.SWORD_TRUTHSEEKER),
                                    CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates.get(SoulDaggersID.SWORD_TRUTHSEEKER));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(HammersID.HAMMER_SUNS_GRACE)) {
                            addWeapon(poolBuilder, ItemsInit.hammerItems.get(HammersID.HAMMER_SUNS_GRACE),
                                    CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_SUNS_GRACE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.scythesEnabled.get(ScythesID.SICKLE_SOUL_SCYTHE)) {
                            addWeapon(poolBuilder, ItemsInit.scytheItems.get(ScythesID.SICKLE_SOUL_SCYTHE),
                                    CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_SOUL_SCYTHE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_DARK_KATANA)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_DARK_KATANA),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_DARK_KATANA));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(SwordsID.SWORD_THE_STARLESS_NIGHT)) {
                            addWeapon(poolBuilder, ItemsInit.swordItems.get(SwordsID.SWORD_THE_STARLESS_NIGHT),
                                    CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_THE_STARLESS_NIGHT));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_ANCIENT_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_ANCIENT_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_LOST_SOULS)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_LOST_SOULS));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_HAUNTED_BOW)) {
                            addWeapon(poolBuilder, ItemsInit.bowItems.get(BowsID.BOW_HAUNTED_BOW),
                                    CONFIG.mcdwNewlootConfig.bowSpawnRates.get(BowsID.BOW_HAUNTED_BOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW));
                        }
                        if (CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS)) {
                            addWeapon(poolBuilder, ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS),
                                    CONFIG.mcdwNewlootConfig.crossbowSpawnRates.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS));
                        }
                        supplier.pool(poolBuilder);
                    }
                }
            }
        }));
    }

    public static void addWeapon(FabricLootPoolBuilder poolBuilder, Item weapon, float p){
        poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
        poolBuilder.withEntry(ItemEntry.builder(weapon).build());
    }

    public static void addItemDrop(FabricLootPoolBuilder poolBuilder, Item item, int n, float p){
        poolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        poolBuilder.withEntry(ItemEntry.builder(item).build());
    }
}
