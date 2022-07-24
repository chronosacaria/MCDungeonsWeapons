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
import java.util.List;

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

    static List<McdwWeaponID> COMMON_LOOT_POOL = List.of(AxesID.AXE_AXE, DaggersID.DAGGER_DAGGER, DoubleAxesID.AXE_DOUBLE, GauntletsID.GAUNTLET_GAUNTLET, GlaivesID.SPEAR_GLAIVE,
            SpearsID.SPEAR_SPEAR, SicklesID.SICKLE_SICKLE, StavesID.STAFF_BATTLESTAFF, AxesID.AXE_ANCHOR, HammersID.HAMMER_MACE, HammersID.HAMMER_GREAT_HAMMER,
            SwordsID.SWORD_IRON_SWORD_VAR, SwordsID.SWORD_KATANA, SwordsID.SWORD_RAPIER, SwordsID.SWORD_CUTLASS, LongBowsID.BOW_LONGBOW, ShortBowsID.BOW_SHORTBOW,
            PicksID.PICK_MOUNTAINEER_PICK, PicksID.PICK_HOWLING_PICK);

    static List<McdwWeaponID> UNCOMMON_LOOT_POOL = List.of(WhipsID.WHIP_WHIP, WhipsID.WHIP_VINE_WHIP, PicksID.PICK_HOWLING_PICK, DoubleAxesID.AXE_CURSED, HammersID.HAMMER_BONECLUB,
            HammersID.HAMMER_BONE_CUDGEL, ScythesID.SICKLE_SKULL_SCYTHE, SicklesID.SICKLE_NIGHTMARES_BITE, SwordsID.SWORD_BROADSWORD, SwordsID.SWORD_CLAYMORE,
            SwordsID.SWORD_NAMELESS_BLADE, SwordsID.SWORD_DIAMOND_SWORD_VAR, SwordsID.SWORD_SINISTER, SwordsID.SWORD_BROKEN_SAWBLADE, SwordsID.SWORD_MECHANIZED_SAWBLADE,
            ScythesID.SICKLE_JAILORS_SCYTHE, BowsID.BOW_BONEBOW, BowsID.BOW_HUNTERS_PROMISE, BowsID.BOW_HUNTING_BOW, BowsID.BOW_MASTERS_BOW, BowsID.BOW_POWER_BOW, BowsID.BOW_SNOW_BOW,
            BowsID.BOW_SOUL_BOW, BowsID.BOW_WIND_BOW, BowsID.BOW_TWISTING_VINE_BOW, BowsID.BOW_WEEPING_VINE_BOW, BowsID.BOW_BUBBLE_BOW, CrossbowsID.CROSSBOW_THE_SLICER,
            CrossbowsID.CROSSBOW_AZURE_SEEKER, CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW, CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW, CrossbowsID.CROSSBOW_FIREBOLT_THROWER,
            CrossbowsID.CROSSBOW_HEAVY_CROSSBOW, CrossbowsID.CROSSBOW_RAPID_CROSSBOW, CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW, CrossbowsID.CROSSBOW_AUTO_CROSSBOW,
            CrossbowsID.CROSSBOW_SCATTER_CROSSBOW, CrossbowsID.CROSSBOW_HARP_CROSSBOW, CrossbowsID.CROSSBOW_SOUL_CROSSBOW, CrossbowsID.CROSSBOW_DUAL_CROSSBOW,
            CrossbowsID.CROSSBOW_BURST_CROSSBOW, CrossbowsID.CROSSBOW_HARPOON_CROSSBOW);

    static List<McdwWeaponID> RARE_LOOT_POOL = List.of(AxesID.AXE_HIGHLAND, AxesID.AXE_FIREBRAND, AxesID.AXE_ENCRUSTED_ANCHOR, DaggersID.DAGGER_FANGS_OF_FROST,
            DaggersID.DAGGER_TEMPEST_KNIFE, DaggersID.DAGGER_SHEAR_DAGGER, DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, DaggersID.DAGGER_CHILL_GALE_KNIFE, DoubleAxesID.AXE_WHIRLWIND,
            GauntletsID.GAUNTLET_MAULERS, GlaivesID.SPEAR_GRAVE_BANE, GlaivesID.SPEAR_VENOM_GLAIVE, HammersID.HAMMER_FLAIL, PicksID.PICK_DIAMOND_PICKAXE_VAR,
            PicksID.PICK_HAILING_PINNACLE, ScythesID.SICKLE_FROST_SCYTHE, SicklesID.SICKLE_LAST_LAUGH_SILVER, SicklesID.SICKLE_LAST_LAUGH_GOLD, SicklesID.SICKLE_LAST_LAUGH_GOLD,
            SpearsID.SPEAR_WHISPERING_SPEAR, SpearsID.SPEAR_FORTUNE, StavesID.STAFF_GROWING_STAFF, StavesID.STAFF_BATTLESTAFF_OF_TERROR, SwordsID.SWORD_FROST_SLAYER,
            SwordsID.SWORD_HEARTSTEALER, SwordsID.SWORD_GREAT_AXEBLADE, SwordsID.SWORD_BEESTINGER, SwordsID.SWORD_FREEZING_FOIL, SwordsID.SWORD_DANCERS_SWORD,
            SwordsID.SWORD_MASTERS_KATANA, SwordsID.SWORD_HAWKBRAND, SwordsID.SWORD_CORAL_BLADE, SwordsID.SWORD_SPONGE_STRIKER, BowsID.BOW_ELITE_POWER_BOW, BowsID.BOW_NOCTURNAL_BOW,
            BowsID.BOW_SABREWING, BowsID.BOW_GREEN_MENACE, BowsID.BOW_PINK_SCOUNDREL, BowsID.BOW_TRICKBOW, BowsID.BOW_TWIN_BOW, BowsID.BOW_WINTERS_TOUCH, BowsID.BOW_SHIVERING_BOW,
            BowsID.BOW_ECHO_OF_THE_VALLEY, BowsID.BOW_BURST_GALE_BOW, BowsID.BOW_BUBBLE_BURSTER, BowsID.BOW_VOID_BOW, BowsID.BOW_CALL_OF_THE_VOID, BowsID.BOW_PHANTOM_BOW,
            BowsID.BOW_WEB_BOW, LongBowsID.BOW_GUARDIAN_BOW, LongBowsID.BOW_RED_SNAKE, ShortBowsID.BOW_MECHANICAL_SHORTBOW, ShortBowsID.BOW_LOVE_SPELL_BOW,
            ShortBowsID.BOW_PURPLE_STORM, CrossbowsID.CROSSBOW_SLAYER_CROSSBOW, CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW,
            CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW, CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW, CrossbowsID.CROSSBOW_BABY_CROSSBOW, CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW,
            CrossbowsID.CROSSBOW_COG_CROSSBOW, CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW, CrossbowsID.CROSSBOW_SHADOW_CROSSBOW, CrossbowsID.CROSSBOW_VEILED_CROSSBOW,
            ShieldsID.SHIELD_VANGUARD, ShieldsID.SHIELD_ROYAL_GUARD);

    static List<McdwWeaponID> EPIC_LOOT_POOL = List.of(DaggersID.DAGGER_BACKSTABBER, DaggersID.DAGGER_SWIFT_STRIKER, DaggersID.DAGGER_VOID_TOUCHED_BLADE, DaggersID.DAGGER_THE_BEGINNING,
            DaggersID.DAGGER_THE_END, GauntletsID.GAUNTLET_SOUL_FISTS, HammersID.HAMMER_STORMLANDER, HammersID.HAMMER_GRAVITY, SoulDaggersID.DAGGER_SOUL_KNIFE,
            SoulDaggersID.DAGGER_ETERNAL_KNIFE, SoulDaggersID.SWORD_TRUTHSEEKER, HammersID.HAMMER_SUNS_GRACE, ScythesID.SICKLE_SOUL_SCYTHE, SwordsID.SWORD_DARK_KATANA,
            SwordsID.SWORD_OBSIDIAN_CLAYMORE, SwordsID.SWORD_THE_STARLESS_NIGHT, BowsID.BOW_ANCIENT_BOW, BowsID.BOW_LOST_SOULS, BowsID.BOW_HAUNTED_BOW,
            CrossbowsID.CROSSBOW_DOOM_CROSSBOW, CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW, CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS);

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
                    COMMON_LOOT_POOL.forEach(lootId -> addWeaponById(lootPoolBuilder, lootId));
                    tableBuilder.pool(lootPoolBuilder.build());
                }

                if (UNCOMMON_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    UNCOMMON_LOOT_POOL.forEach(lootId -> addWeaponById(lootPoolBuilder, lootId));
                    tableBuilder.pool(lootPoolBuilder.build());
                }

                if (RARE_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    RARE_LOOT_POOL.forEach(lootID -> addWeaponById(lootPoolBuilder, lootID));
                    tableBuilder.pool(lootPoolBuilder.build());
                }

                if (EPIC_LOOT_TABLES.contains(id.toString())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    EPIC_LOOT_POOL.forEach(lootID -> addWeaponById(lootPoolBuilder, lootID));
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
        if (mcdwWeaponID.isEnabled())
            addWeapon(poolBuilder, mcdwWeaponID.getItem(), mcdwWeaponID.getItemSpawnRate());
    }

    public static void addItemDrop(LootPool.Builder poolBuilder, Item item, int n, float p) {
        poolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        poolBuilder.with(ItemEntry.builder(item).build());
    }
}