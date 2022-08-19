package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.HashMap;

@Config(name = "mcdw_loot_config")
public class McdwNewLootConfig implements ConfigData {

    @Comment("Chance to get a weapon from chests. Default = 0.10f")
    public Float findWeaponChance = 0.10f;

    @Comment("Higher Number makes Luck give you potential for extra MCDW loot. Default = 1.2f")
    public Float bonusRollsWithLuck = 1.2f;

    public final HashMap<SettingsID, Boolean> WEAPONS_ENABLED_IN_LOOTTABLES = new HashMap<>();
    public final HashMap<SettingsID, String[]> COMMON_LOOT_TABLES = new HashMap<>();
    public final HashMap<SettingsID, String[]> UNCOMMON_LOOT_TABLES = new HashMap<>();
    public final HashMap<SettingsID, String[]> RARE_LOOT_TABLES = new HashMap<>();
    public final HashMap<SettingsID, String[]> EPIC_LOOT_TABLES = new HashMap<>();
    public final HashMap<AxesID, Integer> AXE_SPAWN_RATES = new HashMap<>();
    public final HashMap<DaggersID, Integer> DAGGER_SPAWN_RATES = new HashMap<>();
    public final HashMap<DoubleAxesID, Integer> DOUBLE_AXE_SPAWN_RATES = new HashMap<>();
    public final HashMap<GauntletsID, Integer> GAUNTLET_SPAWN_RATES = new HashMap<>();
    public final HashMap<GlaivesID, Integer> GLAIVE_SPAWN_RATES = new HashMap<>();
    public final HashMap<HammersID, Integer> HAMMER_SPAWN_RATES = new HashMap<>();
    public final HashMap<PicksID, Integer> PICK_SPAWN_RATES = new HashMap<>();
    public final HashMap<ScythesID, Integer> SCYTHE_SPAWN_RATES = new HashMap<>();
    public final HashMap<SicklesID, Integer> SICKLE_SPAWN_RATES = new HashMap<>();
    public final HashMap<SoulDaggersID, Integer> SOUL_DAGGER_SPAWN_RATES = new HashMap<>();
    public final HashMap<SpearsID, Integer> SPEAR_SPAWN_RATES = new HashMap<>();
    public final HashMap<StavesID, Integer> STAFF_SPAWN_RATES = new HashMap<>();
    public final HashMap<SwordsID, Integer> SWORD_SPAWN_RATES = new HashMap<>();
    public final HashMap<WhipsID, Integer> WHIP_SPAWN_RATES = new HashMap<>();
    public final HashMap<BowsID, Integer> BOW_SPAWN_RATES = new HashMap<>();
    public final HashMap<LongbowsID, Integer> LONGBOW_SPAWN_RATES = new HashMap<>();
    public final HashMap<ShortbowsID, Integer> SHORTBOW_SPAWN_RATES = new HashMap<>();
    public final HashMap<CrossbowsID, Integer> CROSSBOW_SPAWN_RATES = new HashMap<>();
    public final HashMap<ShieldsID, Integer> SHIELD_SPAWN_RATES = new HashMap<>();

    public McdwNewLootConfig(){
        WEAPONS_ENABLED_IN_LOOTTABLES.put(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES, true);

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

        /* SPAWN RATES */

        //AXES
        AXE_SPAWN_RATES.put(AxesID.AXE_AXE, 10);
        AXE_SPAWN_RATES.put(AxesID.AXE_FIREBRAND, 5);
        AXE_SPAWN_RATES.put(AxesID.AXE_HIGHLAND, 5);
        AXE_SPAWN_RATES.put(AxesID.AXE_ANCHOR, 10);
        AXE_SPAWN_RATES.put(AxesID.AXE_ENCRUSTED_ANCHOR, 5);

        //DAGGERS
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_DAGGER, 10);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_FANGS_OF_FROST, 5);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_MOON, 5);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_SHEAR_DAGGER, 10);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_TEMPEST_KNIFE, 10);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, 5);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_CHILL_GALE_KNIFE, 5);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_BACKSTABBER, 10);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_SWIFT_STRIKER, 5);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_VOID_TOUCHED_BLADE, 10);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_THE_BEGINNING, 5);
        DAGGER_SPAWN_RATES.put(DaggersID.DAGGER_THE_END, 5);

        //DOUBLE AXES
        DOUBLE_AXE_SPAWN_RATES.put(DoubleAxesID.AXE_DOUBLE, 10);
        DOUBLE_AXE_SPAWN_RATES.put(DoubleAxesID.AXE_CURSED, 5);
        DOUBLE_AXE_SPAWN_RATES.put(DoubleAxesID.AXE_WHIRLWIND, 5);
        GAUNTLET_SPAWN_RATES.put(GauntletsID.GAUNTLET_GAUNTLET, 10);
        GAUNTLET_SPAWN_RATES.put(GauntletsID.GAUNTLET_MAULERS, 5);
        GAUNTLET_SPAWN_RATES.put(GauntletsID.GAUNTLET_SOUL_FISTS, 5);

        //GLAIVES
        GLAIVE_SPAWN_RATES.put(GlaivesID.SPEAR_GLAIVE, 10);
        GLAIVE_SPAWN_RATES.put(GlaivesID.SPEAR_GRAVE_BANE, 5);
        GLAIVE_SPAWN_RATES.put(GlaivesID.SPEAR_VENOM_GLAIVE, 5);
        GLAIVE_SPAWN_RATES.put(GlaivesID.SPEAR_CACKLING_BROOM, 5);

        //HAMMERS
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_GREAT_HAMMER, 10);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_STORMLANDER, 5);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_GRAVITY, 5);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_MACE, 10);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_FLAIL, 5);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_SUNS_GRACE, 5);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_BONECLUB, 10);
        HAMMER_SPAWN_RATES.put(HammersID.HAMMER_BONE_CUDGEL, 5);

        //PICKS
        PICK_SPAWN_RATES.put(PicksID.PICK_DIAMOND_PICKAXE_VAR, 10);
        PICK_SPAWN_RATES.put(PicksID.PICK_MOUNTAINEER_PICK, 10);
        PICK_SPAWN_RATES.put(PicksID.PICK_HOWLING_PICK, 10);
        PICK_SPAWN_RATES.put(PicksID.PICK_HAILING_PINNACLE, 10);

        //SCYTHES
        SCYTHE_SPAWN_RATES.put(ScythesID.SICKLE_JAILORS_SCYTHE, 10);
        SCYTHE_SPAWN_RATES.put(ScythesID.SICKLE_SOUL_SCYTHE, 5);
        SCYTHE_SPAWN_RATES.put(ScythesID.SICKLE_FROST_SCYTHE, 5);
        SCYTHE_SPAWN_RATES.put(ScythesID.SICKLE_SKULL_SCYTHE, 5);

        //SICKLES
        SICKLE_SPAWN_RATES.put(SicklesID.SICKLE_SICKLE, 10);
        SICKLE_SPAWN_RATES.put(SicklesID.SICKLE_NIGHTMARES_BITE, 5);
        SICKLE_SPAWN_RATES.put(SicklesID.SICKLE_LAST_LAUGH_GOLD, 5);
        SICKLE_SPAWN_RATES.put(SicklesID.SICKLE_LAST_LAUGH_SILVER, 5);

        //SOUL DAGGERS
        SOUL_DAGGER_SPAWN_RATES.put(SoulDaggersID.DAGGER_SOUL_KNIFE, 10);
        SOUL_DAGGER_SPAWN_RATES.put(SoulDaggersID.DAGGER_ETERNAL_KNIFE, 5);
        SOUL_DAGGER_SPAWN_RATES.put(SoulDaggersID.SWORD_TRUTHSEEKER, 5);

        //SPEARS
        SPEAR_SPAWN_RATES.put(SpearsID.SPEAR_SPEAR, 10);
        SPEAR_SPAWN_RATES.put(SpearsID.SPEAR_WHISPERING_SPEAR, 5);
        SPEAR_SPAWN_RATES.put(SpearsID.SPEAR_FORTUNE, 5);

        //STAVES
        STAFF_SPAWN_RATES.put(StavesID.STAFF_BATTLESTAFF, 10);
        STAFF_SPAWN_RATES.put(StavesID.STAFF_GROWING_STAFF, 5);
        STAFF_SPAWN_RATES.put(StavesID.STAFF_BATTLESTAFF_OF_TERROR, 5);

        //SWORDS
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_BROADSWORD, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_CLAYMORE, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_FROST_SLAYER, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_HEARTSTEALER, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_GREAT_AXEBLADE, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_RAPIER, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_BEESTINGER, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_FREEZING_FOIL, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_CUTLASS, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_NAMELESS_BLADE, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_DANCERS_SWORD, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_KATANA, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_MASTERS_KATANA, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_DARK_KATANA, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_IRON_SWORD_VAR, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_DIAMOND_SWORD_VAR, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_HAWKBRAND, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_SINISTER, 1);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_BROKEN_SAWBLADE, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_MECHANIZED_SAWBLADE, 1);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_CORAL_BLADE, 10);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_SPONGE_STRIKER, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_OBSIDIAN_CLAYMORE, 5);
        SWORD_SPAWN_RATES.put(SwordsID.SWORD_THE_STARLESS_NIGHT, 1);

        //WHIPS
        WHIP_SPAWN_RATES.put(WhipsID.WHIP_WHIP, 10);
        WHIP_SPAWN_RATES.put(WhipsID.WHIP_VINE_WHIP, 5);

        //BOWS
        BOW_SPAWN_RATES.put(BowsID.BOW_ANCIENT_BOW, 0);
        BOW_SPAWN_RATES.put(BowsID.BOW_BONEBOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_LOST_SOULS, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_ELITE_POWER_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_HAUNTED_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_HUNTERS_PROMISE, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_HUNTING_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_MASTERS_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_NOCTURNAL_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_POWER_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_SABREWING, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_SNOW_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_SOUL_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_GREEN_MENACE, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_PINK_SCOUNDREL, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_TRICKBOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_TWIN_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_WINTERS_TOUCH, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_SHIVERING_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_WIND_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_ECHO_OF_THE_VALLEY, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_BURST_GALE_BOW, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_TWISTING_VINE_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_WEEPING_VINE_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_BUBBLE_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_BUBBLE_BURSTER, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_VOID_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_CALL_OF_THE_VOID, 5);
        BOW_SPAWN_RATES.put(BowsID.BOW_PHANTOM_BOW, 10);
        BOW_SPAWN_RATES.put(BowsID.BOW_WEB_BOW, 10);

        //LONG BOWS
        LONGBOW_SPAWN_RATES.put(LongbowsID.BOW_LONGBOW, 10);
        LONGBOW_SPAWN_RATES.put(LongbowsID.BOW_GUARDIAN_BOW, 5);
        LONGBOW_SPAWN_RATES.put(LongbowsID.BOW_RED_SNAKE, 5);

        //SHORT BOWS
        SHORTBOW_SPAWN_RATES.put(ShortbowsID.BOW_SHORTBOW, 10);
        SHORTBOW_SPAWN_RATES.put(ShortbowsID.BOW_MECHANICAL_SHORTBOW, 5);
        SHORTBOW_SPAWN_RATES.put(ShortbowsID.BOW_LOVE_SPELL_BOW, 5);
        SHORTBOW_SPAWN_RATES.put(ShortbowsID.BOW_PURPLE_STORM, 5);

        //CROSSBOWS
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_THE_SLICER, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_AZURE_SEEKER, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_FIREBOLT_THROWER, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_DOOM_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_RAPID_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_AUTO_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_HARP_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_SOUL_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_DUAL_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_BABY_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_BURST_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_COG_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW, 5);
        CROSSBOW_SPAWN_RATES.put(CrossbowsID.CROSSBOW_VEILED_CROSSBOW, 5);

        //SHIELDS
        SHIELD_SPAWN_RATES.put(ShieldsID.SHIELD_ROYAL_GUARD, 5);
        SHIELD_SPAWN_RATES.put(ShieldsID.SHIELD_VANGUARD, 5);
    }

}
