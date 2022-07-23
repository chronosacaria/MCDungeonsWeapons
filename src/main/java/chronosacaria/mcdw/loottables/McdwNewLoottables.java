package chronosacaria.mcdw.loottables;

import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
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
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (EntityType.BEE.getLootTableId().equals(id) && source.isBuiltin()) {
                if (CONFIG.mcdwEnableItemsConfig.itemsEnabled.get(ItemsID.ITEM_BEE_STINGER)) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, ItemsInit.mcdwItems.get(ItemsID.ITEM_BEE_STINGER), 1, 1f);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }
            if (EntityType.WITCH.getLootTableId().equals(id) && source.isBuiltin()) {
                if (CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(GlaivesID.SPEAR_CACKLING_BROOM)) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM), 1, 0.2F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }
            if (EntityType.WITHER.getLootTableId().equals(id) && source.isBuiltin()) {
                if (CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(BowsID.BOW_ANCIENT_BOW)) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW), 1, 0.1F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }

            if (CONFIG.mcdwNewlootConfig.weaponsEnabledInLootTables.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                if (COMMON_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addWeaponById(lootPoolBuilder, AxesID.AXE_AXE);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_DAGGER);
                    addWeaponById(lootPoolBuilder, DoubleAxesID.AXE_DOUBLE);
                    addWeaponById(lootPoolBuilder, GauntletsID.GAUNTLET_GAUNTLET);
                    addWeaponById(lootPoolBuilder, GlaivesID.SPEAR_GLAIVE);
                    addWeaponById(lootPoolBuilder, SpearsID.SPEAR_SPEAR);
                    addWeaponById(lootPoolBuilder, SicklesID.SICKLE_SICKLE);
                    addWeaponById(lootPoolBuilder, StavesID.STAFF_BATTLESTAFF);
                    addWeaponById(lootPoolBuilder, AxesID.AXE_ANCHOR);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_MACE);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_GREAT_HAMMER);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_IRON_SWORD_VAR);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_KATANA);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_RAPIER);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_CUTLASS);
                    addWeaponById(lootPoolBuilder, LongBowsID.BOW_LONGBOW);
                    addWeaponById(lootPoolBuilder, ShortBowsID.BOW_SHORTBOW);
                    addWeaponById(lootPoolBuilder, PicksID.PICK_MOUNTAINEER_PICK);
                    addWeaponById(lootPoolBuilder, PicksID.PICK_HOWLING_PICK);
                    tableBuilder.pool(lootPoolBuilder.build());
                }

                if (UNCOMMON_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addWeaponById(lootPoolBuilder, WhipsID.WHIP_WHIP);
                    addWeaponById(lootPoolBuilder, WhipsID.WHIP_VINE_WHIP);
                    addWeaponById(lootPoolBuilder, PicksID.PICK_HOWLING_PICK);
                    addWeaponById(lootPoolBuilder, DoubleAxesID.AXE_CURSED);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_BONECLUB);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_BONE_CUDGEL);
                    addWeaponById(lootPoolBuilder, ScythesID.SICKLE_SKULL_SCYTHE);
                    addWeaponById(lootPoolBuilder, SicklesID.SICKLE_NIGHTMARES_BITE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_BROADSWORD);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_CLAYMORE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_NAMELESS_BLADE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_DIAMOND_SWORD_VAR);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_SINISTER);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_BROKEN_SAWBLADE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_MECHANIZED_SAWBLADE);
                    addWeaponById(lootPoolBuilder, ScythesID.SICKLE_JAILORS_SCYTHE);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_BONEBOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_HUNTERS_PROMISE);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_HUNTING_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_MASTERS_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_POWER_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_SNOW_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_SOUL_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_WIND_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_TWISTING_VINE_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_WEEPING_VINE_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_BUBBLE_BOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_THE_SLICER);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_AZURE_SEEKER);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_FIREBOLT_THROWER);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_HEAVY_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_RAPID_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_AUTO_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_SCATTER_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_HARP_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_SOUL_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_DUAL_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_BURST_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_HARPOON_CROSSBOW);
                    tableBuilder.pool(lootPoolBuilder.build());
                }

                if (RARE_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addWeaponById(lootPoolBuilder, AxesID.AXE_HIGHLAND);
                    addWeaponById(lootPoolBuilder, AxesID.AXE_FIREBRAND);
                    addWeaponById(lootPoolBuilder, AxesID.AXE_ENCRUSTED_ANCHOR);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_FANGS_OF_FROST);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_TEMPEST_KNIFE);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_SHEAR_DAGGER);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_CHILL_GALE_KNIFE);
                    addWeaponById(lootPoolBuilder, DoubleAxesID.AXE_WHIRLWIND);
                    addWeaponById(lootPoolBuilder, GauntletsID.GAUNTLET_MAULERS);
                    addWeaponById(lootPoolBuilder, GlaivesID.SPEAR_GRAVE_BANE);
                    addWeaponById(lootPoolBuilder, GlaivesID.SPEAR_VENOM_GLAIVE);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_FLAIL);
                    addWeaponById(lootPoolBuilder, PicksID.PICK_DIAMOND_PICKAXE_VAR);
                    addWeaponById(lootPoolBuilder, PicksID.PICK_HAILING_PINNACLE);
                    addWeaponById(lootPoolBuilder, ScythesID.SICKLE_FROST_SCYTHE);
                    addWeaponById(lootPoolBuilder, SicklesID.SICKLE_LAST_LAUGH_SILVER);
                    addWeaponById(lootPoolBuilder, SicklesID.SICKLE_LAST_LAUGH_GOLD);
                    addWeaponById(lootPoolBuilder, SicklesID.SICKLE_LAST_LAUGH_GOLD);
                    addWeaponById(lootPoolBuilder, SpearsID.SPEAR_WHISPERING_SPEAR);
                    addWeaponById(lootPoolBuilder, SpearsID.SPEAR_FORTUNE);
                    addWeaponById(lootPoolBuilder, StavesID.STAFF_GROWING_STAFF);
                    addWeaponById(lootPoolBuilder, StavesID.STAFF_BATTLESTAFF_OF_TERROR);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_FROST_SLAYER);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_HEARTSTEALER);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_GREAT_AXEBLADE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_BEESTINGER);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_FREEZING_FOIL);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_DANCERS_SWORD);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_MASTERS_KATANA);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_HAWKBRAND);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_CORAL_BLADE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_SPONGE_STRIKER);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_ELITE_POWER_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_NOCTURNAL_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_SABREWING);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_GREEN_MENACE);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_PINK_SCOUNDREL);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_TRICKBOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_TWIN_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_WINTERS_TOUCH);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_SHIVERING_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_ECHO_OF_THE_VALLEY);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_BURST_GALE_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_BUBBLE_BURSTER);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_VOID_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_CALL_OF_THE_VOID);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_PHANTOM_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_WEB_BOW);
                    addWeaponById(lootPoolBuilder, LongBowsID.BOW_GUARDIAN_BOW);
                    addWeaponById(lootPoolBuilder, LongBowsID.BOW_RED_SNAKE);
                    addWeaponById(lootPoolBuilder, ShortBowsID.BOW_MECHANICAL_SHORTBOW);
                    addWeaponById(lootPoolBuilder, ShortBowsID.BOW_LOVE_SPELL_BOW);
                    addWeaponById(lootPoolBuilder, ShortBowsID.BOW_PURPLE_STORM);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_SLAYER_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_BABY_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_COG_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_SHADOW_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_VEILED_CROSSBOW);
                    addWeaponById(lootPoolBuilder, ShieldsID.SHIELD_VANGUARD);
                    addWeaponById(lootPoolBuilder, ShieldsID.SHIELD_ROYAL_GUARD);
                    tableBuilder.pool(lootPoolBuilder.build());
                }

                if (EPIC_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_BACKSTABBER);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_SWIFT_STRIKER);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_VOID_TOUCHED_BLADE);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_THE_BEGINNING);
                    addWeaponById(lootPoolBuilder, DaggersID.DAGGER_THE_END);
                    addWeaponById(lootPoolBuilder, GauntletsID.GAUNTLET_SOUL_FISTS);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_STORMLANDER);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_GRAVITY);
                    addWeaponById(lootPoolBuilder, SoulDaggersID.DAGGER_SOUL_KNIFE);
                    addWeaponById(lootPoolBuilder, SoulDaggersID.DAGGER_ETERNAL_KNIFE);
                    addWeaponById(lootPoolBuilder, SoulDaggersID.SWORD_TRUTHSEEKER);
                    addWeaponById(lootPoolBuilder, HammersID.HAMMER_SUNS_GRACE);
                    addWeaponById(lootPoolBuilder, ScythesID.SICKLE_SOUL_SCYTHE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_DARK_KATANA);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_OBSIDIAN_CLAYMORE);
                    addWeaponById(lootPoolBuilder, SwordsID.SWORD_THE_STARLESS_NIGHT);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_ANCIENT_BOW);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_LOST_SOULS);
                    addWeaponById(lootPoolBuilder, BowsID.BOW_HAUNTED_BOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_DOOM_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW);
                    addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }
        }));
    }

    public static void addWeapon(LootPool.Builder poolBuilder, Item weapon, float p) {
        poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
        poolBuilder.with(ItemEntry.builder(weapon).build());
    }

    public static void addWeaponById(LootPool.Builder poolBuilder, McdwWeaponID mcdwWeaponID) {
        if (mcdwWeaponID.getEnabledItems().get(mcdwWeaponID))
            addWeapon(poolBuilder, mcdwWeaponID.getItemsEnum().get(mcdwWeaponID), mcdwWeaponID.getSpawnRates().get(mcdwWeaponID));
    }

    public static void addItemDrop(LootPool.Builder poolBuilder, Item item, int n, float p) {
        poolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        poolBuilder.with(ItemEntry.builder(item).build());
    }
}
