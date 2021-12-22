package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
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
            if ("minecraft:entities/bee".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 1.0F))
                        .with(ItemEntry.builder(ItemsInit.mcdwItems.get(ItemsID.ITEM_BEE_STINGER)));

                supplier.pool(poolBuilder);
            }

            if ("minecraft:entities/witch".equals(id.toString())) {
                LootPool poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.20f))
                        .with(ItemEntry.builder(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM)))
                        .build();
                supplier.withPool(poolBuilder);
            }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                for (int i = 0; i < COMMON_LOOT_TABLES.size(); i++) {
                    if (COMMON_LOOT_TABLES.get(i).equals(id.toString())) {
                        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE)))
                                .with(ItemEntry.builder(ItemsInit.axeItems.get(AxesID.AXE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.daggerSpawnRates.get(DaggersID.DAGGER_DAGGER)))
                                .with(ItemEntry.builder(ItemsInit.daggerItems.get(DaggersID.DAGGER_DAGGER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates.get(DoubleAxesID.AXE_DOUBLE)))
                                .with(ItemEntry.builder(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_DOUBLE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.gauntletSpawnRates.get(GauntletsID.GAUNTLET_GAUNTLET)))
                                .with(ItemEntry.builder(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_GAUNTLET)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.glaiveSpawnRates.get(GlaivesID.SPEAR_GLAIVE)))
                                .with(ItemEntry.builder(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GLAIVE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.spearSpawnRates.get(SpearsID.SPEAR_SPEAR)))
                                .with(ItemEntry.builder(ItemsInit.spearItems.get(SpearsID.SPEAR_SPEAR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_SICKLE)))
                                .with(ItemEntry.builder(ItemsInit.sickleItems.get(SicklesID.SICKLE_SICKLE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.staffSpawnRates.get(StavesID.STAFF_BATTLESTAFF)))
                                .with(ItemEntry.builder(ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.axeSpawnRates.get(AxesID.AXE_ANCHOR)))
                                .with(ItemEntry.builder(ItemsInit.axeItems.get(AxesID.AXE_ANCHOR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_MACE)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_MACE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_GREAT)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_GREAT)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_IRON_SWORD_VAR)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_IRON_SWORD_VAR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_KATANA)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_KATANA)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_RAPIER)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_RAPIER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_CUTLASS)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_CUTLASS)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.longBowSpawnRates.get(LongBowsID.BOW_LONGBOW)))
                                .with(ItemEntry.builder(ItemsInit.longBowItems.get(LongBowsID.BOW_LONGBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.shortBowSpawnRates.get(ShortBowsID.BOW_SHORTBOW)))
                                .with(ItemEntry.builder(ItemsInit.shortBowItems.get(ShortBowsID.BOW_SHORTBOW)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_MOUNTAINEER_PICK)))
                                .with(ItemEntry.builder(ItemsInit.pickItems.get(PicksID.PICK_MOUNTAINEER_PICK)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.pickSpawnRates.get(PicksID.PICK_HOWLING_PICK)))
                                .with(ItemEntry.builder(ItemsInit.pickItems.get(PicksID.PICK_HOWLING_PICK)));

                        supplier.pool(poolBuilder);
                    }
                }
            }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                for (int i = 0; i < UNCOMMON_LOOT_TABLES.size(); i++) {
                    if (UNCOMMON_LOOT_TABLES.get(i).equals(id.toString())) {

                        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.whipSpawnRates.get(WhipsID.WHIP_WHIP)))
                                .with(ItemEntry.builder(ItemsInit.whipItems.get(WhipsID.WHIP_WHIP)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.whipSpawnRates.get(WhipsID.WHIP_VINE_WHIP)))
                                .with(ItemEntry.builder(ItemsInit.whipItems.get(WhipsID.WHIP_VINE_WHIP)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates.get(DoubleAxesID.AXE_CURSED)))
                                .with(ItemEntry.builder(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_CURSED)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_BONECLUB)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_BONECLUB)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.hammerSpawnRates.get(HammersID.HAMMER_BONE_CUDGEL)))
                                .with(ItemEntry.builder(ItemsInit.hammerItems.get(HammersID.HAMMER_BONE_CUDGEL)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_SKULL_SCYTHE)))
                                .with(ItemEntry.builder(ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.sickleSpawnRates.get(SicklesID.SICKLE_NIGHTMARES_BITE)))
                                .with(ItemEntry.builder(ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_SKULL_SCYTHE)))
                                .with(ItemEntry.builder(ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_BROADSWORD)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_BROADSWORD)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_CLAYMORE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_CLAYMORE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_NAMELESS_BLADE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_DIAMOND_SWORD_VAR)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_SINISTER)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_SINISTER)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_BROKEN_SAWBLADE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_BROKEN_SAWBLADE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.swordSpawnRates.get(SwordsID.SWORD_MECHANIZED_SAWBLADE)))
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_MECHANIZED_SAWBLADE)))

                                .rolls(BinomialLootNumberProvider.create(1,
                                        CONFIG.mcdwNewlootConfig.scytheSpawnRates.get(ScythesID.SICKLE_JAILORS_SCYTHE)))
                                .with(ItemEntry.builder(ItemsInit.scytheItems.get(ScythesID.SICKLE_JAILORS_SCYTHE)));

                        supplier.pool(poolBuilder);
                    }
                }
           }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
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
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER)));

                        supplier.pool(poolBuilder);
                    }
                }
            }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
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
                                .with(ItemEntry.builder(ItemsInit.swordItems.get(SwordsID.SWORD_THE_STARLESS_NIGHT)));

                        supplier.pool(poolBuilder);
                    }
                }
            }
        }));
    }
}


