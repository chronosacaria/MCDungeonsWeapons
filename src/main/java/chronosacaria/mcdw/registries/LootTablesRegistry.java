package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.enums.*;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public class LootTablesRegistry {

    public static final ArrayList<String> COMMON_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.COMMON_LOOT_TABLES.get(SettingsID.COMMON_LOOT_TABLES)));
    public static final ArrayList<String> UNCOMMON_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.UNCOMMON_LOOT_TABLES.get(SettingsID.UNCOMMON_LOOT_TABLES)));
    public static final ArrayList<String> RARE_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.RARE_LOOT_TABLES.get(SettingsID.RARE_LOOT_TABLES)));
    public static final ArrayList<String> EPIC_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdwNewlootConfig.EPIC_LOOT_TABLES.get(SettingsID.EPIC_LOOT_TABLES)));
    public static final ArrayList<String> NETHER_TABLES =
            new ArrayList<>(List.of("minecraft:chests/nether_bridge", "minecraft:chests/bastion_bridge", "minecraft:chests/bastion_other", "minecraft:chests/bastion_treasure"));

    static List<IMcdwWeaponID> COMMON_LOOT_POOL = List.of(AxesID.AXE_ANCHOR, AxesID.AXE_AXE, DaggersID.DAGGER_DAGGER, DoubleAxesID.AXE_DOUBLE, GauntletsID.GAUNTLET_GAUNTLET,
            GlaivesID.SPEAR_GLAIVE, HammersID.HAMMER_GREAT_HAMMER, HammersID.HAMMER_MACE, LongbowsID.BOW_LONGBOW, PicksID.PICK_HOWLING_PICK, PicksID.PICK_MOUNTAINEER_PICK,
            ShortbowsID.BOW_SHORTBOW, SicklesID.SICKLE_SICKLE, SpearsID.SPEAR_SPEAR, StavesID.STAFF_BATTLESTAFF, SwordsID.SWORD_CUTLASS, SwordsID.SWORD_IRON_SWORD_VAR,
            SwordsID.SWORD_KATANA, SwordsID.SWORD_RAPIER);

    static List<IMcdwWeaponID> UNCOMMON_LOOT_POOL = List.of(BowsID.BOW_BONEBOW, BowsID.BOW_BUBBLE_BOW, BowsID.BOW_HUNTERS_PROMISE, BowsID.BOW_HUNTING_BOW, BowsID.BOW_MASTERS_BOW,
            BowsID.BOW_POWER_BOW, BowsID.BOW_SNOW_BOW, BowsID.BOW_SOUL_BOW, BowsID.BOW_TWISTING_VINE_BOW, BowsID.BOW_WEEPING_VINE_BOW, BowsID.BOW_WIND_BOW,
            CrossbowsID.CROSSBOW_AUTO_CROSSBOW, CrossbowsID.CROSSBOW_AZURE_SEEKER, CrossbowsID.CROSSBOW_BURST_CROSSBOW, CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW,
            CrossbowsID.CROSSBOW_DUAL_CROSSBOW, CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW, CrossbowsID.CROSSBOW_FIREBOLT_THROWER, CrossbowsID.CROSSBOW_HARPOON_CROSSBOW,
            CrossbowsID.CROSSBOW_HARP_CROSSBOW, CrossbowsID.CROSSBOW_HEAVY_CROSSBOW, CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW, CrossbowsID.CROSSBOW_RAPID_CROSSBOW,
            CrossbowsID.CROSSBOW_SCATTER_CROSSBOW, CrossbowsID.CROSSBOW_SOUL_CROSSBOW, CrossbowsID.CROSSBOW_THE_SLICER, DoubleAxesID.AXE_CURSED, HammersID.HAMMER_BONECLUB,
            HammersID.HAMMER_BONE_CUDGEL, PicksID.PICK_HOWLING_PICK, ScythesID.SICKLE_JAILORS_SCYTHE, ScythesID.SICKLE_SKULL_SCYTHE, SicklesID.SICKLE_NIGHTMARES_BITE,
            SwordsID.SWORD_BROADSWORD, SwordsID.SWORD_BROKEN_SAWBLADE, SwordsID.SWORD_CLAYMORE, SwordsID.SWORD_DIAMOND_SWORD_VAR, SwordsID.SWORD_MECHANIZED_SAWBLADE,
            SwordsID.SWORD_NAMELESS_BLADE, SwordsID.SWORD_SINISTER, WhipsID.WHIP_VINE_WHIP, WhipsID.WHIP_WHIP);

    static List<IMcdwWeaponID> RARE_LOOT_POOL = List.of(AxesID.AXE_ENCRUSTED_ANCHOR, AxesID.AXE_FIREBRAND, AxesID.AXE_HIGHLAND, BowsID.BOW_BUBBLE_BURSTER, BowsID.BOW_BURST_GALE_BOW,
            BowsID.BOW_CALL_OF_THE_VOID, BowsID.BOW_ECHO_OF_THE_VALLEY, BowsID.BOW_ELITE_POWER_BOW, BowsID.BOW_GREEN_MENACE, BowsID.BOW_NOCTURNAL_BOW, BowsID.BOW_PHANTOM_BOW,
            BowsID.BOW_PINK_SCOUNDREL, BowsID.BOW_SABREWING, BowsID.BOW_SHIVERING_BOW, BowsID.BOW_TRICKBOW, BowsID.BOW_TWIN_BOW, BowsID.BOW_VOID_BOW, BowsID.BOW_WEB_BOW,
            BowsID.BOW_WINTERS_TOUCH, CrossbowsID.CROSSBOW_BABY_CROSSBOW, CrossbowsID.CROSSBOW_COG_CROSSBOW, CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW,
            CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW, CrossbowsID.CROSSBOW_SHADOW_CROSSBOW, CrossbowsID.CROSSBOW_SLAYER_CROSSBOW,
            CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW, CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW, CrossbowsID.CROSSBOW_VEILED_CROSSBOW, CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW,
            DaggersID.DAGGER_CHILL_GALE_KNIFE, DaggersID.DAGGER_FANGS_OF_FROST, DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, DaggersID.DAGGER_SHEAR_DAGGER,
            DaggersID.DAGGER_TEMPEST_KNIFE, DoubleAxesID.AXE_WHIRLWIND, GauntletsID.GAUNTLET_MAULERS, GlaivesID.SPEAR_GRAVE_BANE, GlaivesID.SPEAR_VENOM_GLAIVE,
            HammersID.HAMMER_FLAIL, LongbowsID.BOW_GUARDIAN_BOW, LongbowsID.BOW_RED_SNAKE, PicksID.PICK_DIAMOND_PICKAXE_VAR, PicksID.PICK_HAILING_PINNACLE,
            ScythesID.SICKLE_FROST_SCYTHE, ShieldsID.SHIELD_ROYAL_GUARD, ShieldsID.SHIELD_VANGUARD, ShortbowsID.BOW_LOVE_SPELL_BOW, ShortbowsID.BOW_MECHANICAL_SHORTBOW,
            ShortbowsID.BOW_PURPLE_STORM, SicklesID.SICKLE_LAST_LAUGH_GOLD, SicklesID.SICKLE_LAST_LAUGH_GOLD, SicklesID.SICKLE_LAST_LAUGH_SILVER, SpearsID.SPEAR_FORTUNE,
            SpearsID.SPEAR_WHISPERING_SPEAR, StavesID.STAFF_BATTLESTAFF_OF_TERROR, StavesID.STAFF_GROWING_STAFF, SwordsID.SWORD_BEESTINGER, SwordsID.SWORD_CORAL_BLADE,
            SwordsID.SWORD_DANCERS_SWORD, SwordsID.SWORD_FREEZING_FOIL, SwordsID.SWORD_FROST_SLAYER, SwordsID.SWORD_GREAT_AXEBLADE, SwordsID.SWORD_HAWKBRAND,
            SwordsID.SWORD_HEARTSTEALER, SwordsID.SWORD_MASTERS_KATANA, SwordsID.SWORD_SPONGE_STRIKER);

    static List<IMcdwWeaponID> EPIC_LOOT_POOL = List.of(BowsID.BOW_ANCIENT_BOW, BowsID.BOW_HAUNTED_BOW, BowsID.BOW_LOST_SOULS, CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW,
            CrossbowsID.CROSSBOW_DOOM_CROSSBOW, CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS, DaggersID.DAGGER_BACKSTABBER, DaggersID.DAGGER_SWIFT_STRIKER,
            DaggersID.DAGGER_THE_BEGINNING, DaggersID.DAGGER_THE_END, DaggersID.DAGGER_VOID_TOUCHED_BLADE, GauntletsID.GAUNTLET_SOUL_FISTS, HammersID.HAMMER_GRAVITY,
            HammersID.HAMMER_STORMLANDER, HammersID.HAMMER_SUNS_GRACE, ScythesID.SICKLE_SOUL_SCYTHE, SoulDaggersID.DAGGER_ETERNAL_KNIFE, SoulDaggersID.DAGGER_SOUL_KNIFE,
            SoulDaggersID.SWORD_TRUTHSEEKER, SwordsID.SWORD_DARK_KATANA, SwordsID.SWORD_OBSIDIAN_CLAYMORE, SwordsID.SWORD_THE_STARLESS_NIGHT);

    public static void init() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (EntityType.BEE.getLootTableId().equals(id) && source.isBuiltin())
                if (CONFIG.mcdwEnableItemsConfig.ITEMS_ENABLED.get(ItemsID.ITEM_BEE_STINGER)) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, ItemsRegistry.MCDW_ITEMS.get(ItemsID.ITEM_BEE_STINGER), 1, 1f);
                    tableBuilder.pool(lootPoolBuilder.build());
                }

            if (EntityType.WITCH.getLootTableId().equals(id) && source.isBuiltin())
                if (GlaivesID.SPEAR_CACKLING_BROOM.isEnabled()) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, GlaivesID.SPEAR_CACKLING_BROOM.getItem(), 1, 0.2F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }

            if (EntityType.WITHER.getLootTableId().equals(id) && source.isBuiltin())
                if (BowsID.BOW_ANCIENT_BOW.isEnabled()) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, BowsID.BOW_ANCIENT_BOW.getItem(), 1, 0.1F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }

            if (CONFIG.mcdwNewlootConfig.WEAPONS_ENABLED_IN_LOOTTABLES.get(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES)) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
                lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(CONFIG.mcdwNewlootConfig.findWeaponChance));
                lootPoolBuilder.bonusRolls(ConstantLootNumberProvider.create(CONFIG.mcdwNewlootConfig.bonusRollsWithLuck));

                if (COMMON_LOOT_TABLES.contains(id.toString()))
                    COMMON_LOOT_POOL.forEach(lootId -> addWeaponById(lootPoolBuilder, lootId));

                if (UNCOMMON_LOOT_TABLES.contains(id.toString()))
                    UNCOMMON_LOOT_POOL.forEach(lootId -> addWeaponById(lootPoolBuilder, lootId));

                if (RARE_LOOT_TABLES.contains(id.toString()))
                    RARE_LOOT_POOL.forEach(lootID -> addWeaponById(lootPoolBuilder, lootID));

                if (EPIC_LOOT_TABLES.contains(id.toString()))
                    EPIC_LOOT_POOL.forEach(lootID -> addWeaponById(lootPoolBuilder, lootID));

                if (NETHER_TABLES.contains(id.toString())) {
                    if (Arrays.stream(lootPoolBuilder.build().entries).noneMatch(lootPoolEntry ->
                            lootPoolEntry.equals(ItemEntry.builder(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS.getItem())
                                    .weight(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS.getItemSpawnRate()).build()))) {
                        addWeaponById(lootPoolBuilder, CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS);
                    }
                }

                tableBuilder.pool(lootPoolBuilder.build());
            }
        }));
    }

    public static void addWeapon(LootPool.Builder poolBuilder, Item weapon, int weight) {
        poolBuilder.with(ItemEntry.builder(weapon).weight(weight));
    }

    public static void addWeaponById(LootPool.Builder poolBuilder, IMcdwWeaponID mcdwWeaponID) {
        if (mcdwWeaponID.isEnabled())
            addWeapon(poolBuilder, mcdwWeaponID.getItem(), mcdwWeaponID.getItemSpawnRate());
    }

    public static void addItemDrop(LootPool.Builder poolBuilder, Item item, int n, float p) {
        poolBuilder.rolls(ConstantLootNumberProvider.create(n));
        poolBuilder.conditionally(RandomChanceLootCondition.builder(p));
        poolBuilder.with(ItemEntry.builder(item));
    }
}