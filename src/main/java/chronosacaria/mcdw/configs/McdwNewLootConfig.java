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

    // TODO Change nomenclature to final convention for hashmaps and subsequent usages
    public final HashMap<SettingsID, Boolean> weaponsEnabledInLootTables = new HashMap<>();
    public final HashMap<SettingsID, String[]> commonLootTables = new HashMap<>();
    public final HashMap<SettingsID, String[]> uncommonLootTables = new HashMap<>();
    public final HashMap<SettingsID, String[]> rareLootTables = new HashMap<>();
    public final HashMap<SettingsID, String[]> epicLootTables = new HashMap<>();
    public final HashMap<AxesID, Integer> axeSpawnRates = new HashMap<>();
    public final HashMap<DaggersID, Integer> daggerSpawnRates = new HashMap<>();
    public final HashMap<DoubleAxesID, Integer> doubleAxeSpawnRates = new HashMap<>();
    public final HashMap<GauntletsID, Integer> gauntletSpawnRates = new HashMap<>();
    public final HashMap<GlaivesID, Integer> glaiveSpawnRates = new HashMap<>();
    public final HashMap<HammersID, Integer> hammerSpawnRates = new HashMap<>();
    public final HashMap<PicksID, Integer> pickSpawnRates = new HashMap<>();
    public final HashMap<ScythesID, Integer> scytheSpawnRates = new HashMap<>();
    public final HashMap<SicklesID, Integer> sickleSpawnRates = new HashMap<>();
    public final HashMap<SoulDaggersID, Integer> soulDaggerSpawnRates = new HashMap<>();
    public final HashMap<SpearsID, Integer> spearSpawnRates = new HashMap<>();
    public final HashMap<StavesID, Integer> staffSpawnRates = new HashMap<>();
    public final HashMap<SwordsID, Integer> swordSpawnRates = new HashMap<>();
    public final HashMap<WhipsID, Integer> whipSpawnRates = new HashMap<>();
    public final HashMap<BowsID, Integer> bowSpawnRates = new HashMap<>();
    public final HashMap<LongBowsID, Integer> longBowSpawnRates = new HashMap<>();
    public final HashMap<ShortBowsID, Integer> shortBowSpawnRates = new HashMap<>();
    public final HashMap<CrossbowsID, Integer> crossbowSpawnRates = new HashMap<>();
    public final HashMap<ShieldsID, Integer> shieldSpawnRates = new HashMap<>();

    public McdwNewLootConfig(){
        weaponsEnabledInLootTables.put(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES, true);

        /* LOOT TABLES */

        // COMMON
        commonLootTables.put(SettingsID.COMMON_LOOT_TABLES, new String[] {
                "minecraft:chests/abandoned_mineshaft",
                "minecraft:chests/shipwreck_supply",
                "minecraft:chests/shipwreck_treasure",
                "minecraft:chests/desert_pyramid",
                "minecraft:chests/village/village_weaponsmith"});

        //UNCOMMON
        uncommonLootTables.put(SettingsID.UNCOMMON_LOOT_TABLES, new String[] {
                "minecraft:chests/jungle_temple",
                "minecraft:chests/nether_bridge",
                "minecraft:chests/bastion_bridge",
                "minecraft:chests/bastion_other",
                "minecraft:chests/bastion_treasure"});

        //RARE
        rareLootTables.put(SettingsID.RARE_LOOT_TABLES, new String[] {
                "minecraft:chests/underwater_ruin_small",
                "minecraft:chests/underwater_ruin_big",
                "minecraft:chests/ruined_portal",
                "minecraft:chests/simple_dungeon",
                "minecraft:chests/igloo_chest",
                "minecraft:chests/pillager_outpost"});

        //EPIC
        epicLootTables.put(SettingsID.EPIC_LOOT_TABLES, new String[] {
                "minecraft:chests/stronghold_corridor",
                "minecraft:chests/stronghold_crossing",
                "minecraft:chests/stronghold_library",
                "minecraft:chests/end_city_treasure"});

        /* SPAWN RATES */

        //AXES
        axeSpawnRates.put(AxesID.AXE_AXE, 10);
        axeSpawnRates.put(AxesID.AXE_FIREBRAND, 5);
        axeSpawnRates.put(AxesID.AXE_HIGHLAND, 5);
        axeSpawnRates.put(AxesID.AXE_ANCHOR, 10);
        axeSpawnRates.put(AxesID.AXE_ENCRUSTED_ANCHOR, 5);

        //DAGGERS
        daggerSpawnRates.put(DaggersID.DAGGER_DAGGER, 10);
        daggerSpawnRates.put(DaggersID.DAGGER_FANGS_OF_FROST, 5);
        daggerSpawnRates.put(DaggersID.DAGGER_MOON, 5);
        daggerSpawnRates.put(DaggersID.DAGGER_SHEAR_DAGGER, 10);
        daggerSpawnRates.put(DaggersID.DAGGER_TEMPEST_KNIFE, 10);
        daggerSpawnRates.put(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, 5);
        daggerSpawnRates.put(DaggersID.DAGGER_CHILL_GALE_KNIFE, 5);
        daggerSpawnRates.put(DaggersID.DAGGER_BACKSTABBER, 10);
        daggerSpawnRates.put(DaggersID.DAGGER_SWIFT_STRIKER, 5);
        daggerSpawnRates.put(DaggersID.DAGGER_VOID_TOUCHED_BLADE, 10);
        daggerSpawnRates.put(DaggersID.DAGGER_THE_BEGINNING, 5);
        daggerSpawnRates.put(DaggersID.DAGGER_THE_END, 5);

        //DOUBLE AXES
        doubleAxeSpawnRates.put(DoubleAxesID.AXE_DOUBLE, 10);
        doubleAxeSpawnRates.put(DoubleAxesID.AXE_CURSED, 5);
        doubleAxeSpawnRates.put(DoubleAxesID.AXE_WHIRLWIND, 5);
        gauntletSpawnRates.put(GauntletsID.GAUNTLET_GAUNTLET, 10);
        gauntletSpawnRates.put(GauntletsID.GAUNTLET_MAULERS, 5);
        gauntletSpawnRates.put(GauntletsID.GAUNTLET_SOUL_FISTS, 5);

        //GLAIVES
        glaiveSpawnRates.put(GlaivesID.SPEAR_GLAIVE, 10);
        glaiveSpawnRates.put(GlaivesID.SPEAR_GRAVE_BANE, 5);
        glaiveSpawnRates.put(GlaivesID.SPEAR_VENOM_GLAIVE, 5);
        glaiveSpawnRates.put(GlaivesID.SPEAR_CACKLING_BROOM, 5);

        //HAMMERS
        hammerSpawnRates.put(HammersID.HAMMER_GREAT_HAMMER, 10);
        hammerSpawnRates.put(HammersID.HAMMER_STORMLANDER, 5);
        hammerSpawnRates.put(HammersID.HAMMER_GRAVITY, 5);
        hammerSpawnRates.put(HammersID.HAMMER_MACE, 10);
        hammerSpawnRates.put(HammersID.HAMMER_FLAIL, 5);
        hammerSpawnRates.put(HammersID.HAMMER_SUNS_GRACE, 5);
        hammerSpawnRates.put(HammersID.HAMMER_BONECLUB, 10);
        hammerSpawnRates.put(HammersID.HAMMER_BONE_CUDGEL, 5);

        //PICKS
        pickSpawnRates.put(PicksID.PICK_DIAMOND_PICKAXE_VAR, 10);
        pickSpawnRates.put(PicksID.PICK_MOUNTAINEER_PICK, 10);
        pickSpawnRates.put(PicksID.PICK_HOWLING_PICK, 10);
        pickSpawnRates.put(PicksID.PICK_HAILING_PINNACLE, 10);

        //SCYTHES
        scytheSpawnRates.put(ScythesID.SICKLE_JAILORS_SCYTHE, 10);
        scytheSpawnRates.put(ScythesID.SICKLE_SOUL_SCYTHE, 5);
        scytheSpawnRates.put(ScythesID.SICKLE_FROST_SCYTHE, 5);
        scytheSpawnRates.put(ScythesID.SICKLE_SKULL_SCYTHE, 5);

        //SICKLES
        sickleSpawnRates.put(SicklesID.SICKLE_SICKLE, 10);
        sickleSpawnRates.put(SicklesID.SICKLE_NIGHTMARES_BITE, 5);
        sickleSpawnRates.put(SicklesID.SICKLE_LAST_LAUGH_GOLD, 5);
        sickleSpawnRates.put(SicklesID.SICKLE_LAST_LAUGH_SILVER, 5);

        //SOUL DAGGERS
        soulDaggerSpawnRates.put(SoulDaggersID.DAGGER_SOUL_KNIFE, 10);
        soulDaggerSpawnRates.put(SoulDaggersID.DAGGER_ETERNAL_KNIFE, 5);
        soulDaggerSpawnRates.put(SoulDaggersID.SWORD_TRUTHSEEKER, 5);

        //SPEARS
        spearSpawnRates.put(SpearsID.SPEAR_SPEAR, 10);
        spearSpawnRates.put(SpearsID.SPEAR_WHISPERING_SPEAR, 5);
        spearSpawnRates.put(SpearsID.SPEAR_FORTUNE, 5);

        //STAVES
        staffSpawnRates.put(StavesID.STAFF_BATTLESTAFF, 10);
        staffSpawnRates.put(StavesID.STAFF_GROWING_STAFF, 5);
        staffSpawnRates.put(StavesID.STAFF_BATTLESTAFF_OF_TERROR, 5);

        //SWORDS
        swordSpawnRates.put(SwordsID.SWORD_BROADSWORD, 10);
        swordSpawnRates.put(SwordsID.SWORD_CLAYMORE, 5);
        swordSpawnRates.put(SwordsID.SWORD_FROST_SLAYER, 5);
        swordSpawnRates.put(SwordsID.SWORD_HEARTSTEALER, 5);
        swordSpawnRates.put(SwordsID.SWORD_GREAT_AXEBLADE, 5);
        swordSpawnRates.put(SwordsID.SWORD_RAPIER, 10);
        swordSpawnRates.put(SwordsID.SWORD_BEESTINGER, 5);
        swordSpawnRates.put(SwordsID.SWORD_FREEZING_FOIL, 5);
        swordSpawnRates.put(SwordsID.SWORD_CUTLASS, 10);
        swordSpawnRates.put(SwordsID.SWORD_NAMELESS_BLADE, 5);
        swordSpawnRates.put(SwordsID.SWORD_DANCERS_SWORD, 5);
        swordSpawnRates.put(SwordsID.SWORD_KATANA, 10);
        swordSpawnRates.put(SwordsID.SWORD_MASTERS_KATANA, 5);
        swordSpawnRates.put(SwordsID.SWORD_DARK_KATANA, 5);
        swordSpawnRates.put(SwordsID.SWORD_IRON_SWORD_VAR, 10);
        swordSpawnRates.put(SwordsID.SWORD_DIAMOND_SWORD_VAR, 10);
        swordSpawnRates.put(SwordsID.SWORD_HAWKBRAND, 5);
        swordSpawnRates.put(SwordsID.SWORD_SINISTER, 1);
        swordSpawnRates.put(SwordsID.SWORD_BROKEN_SAWBLADE, 5);
        swordSpawnRates.put(SwordsID.SWORD_MECHANIZED_SAWBLADE, 1);
        swordSpawnRates.put(SwordsID.SWORD_CORAL_BLADE, 10);
        swordSpawnRates.put(SwordsID.SWORD_SPONGE_STRIKER, 5);
        swordSpawnRates.put(SwordsID.SWORD_OBSIDIAN_CLAYMORE, 5);
        swordSpawnRates.put(SwordsID.SWORD_THE_STARLESS_NIGHT, 1);

        //WHIPS
        whipSpawnRates.put(WhipsID.WHIP_WHIP, 10);
        whipSpawnRates.put(WhipsID.WHIP_VINE_WHIP, 5);

        //BOWS
        bowSpawnRates.put(BowsID.BOW_ANCIENT_BOW, 0);
        bowSpawnRates.put(BowsID.BOW_BONEBOW, 5);
        bowSpawnRates.put(BowsID.BOW_LOST_SOULS, 5);
        bowSpawnRates.put(BowsID.BOW_ELITE_POWER_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_HAUNTED_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_HUNTERS_PROMISE, 5);
        bowSpawnRates.put(BowsID.BOW_HUNTING_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_MASTERS_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_NOCTURNAL_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_POWER_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_SABREWING, 5);
        bowSpawnRates.put(BowsID.BOW_SNOW_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_SOUL_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_GREEN_MENACE, 5);
        bowSpawnRates.put(BowsID.BOW_PINK_SCOUNDREL, 5);
        bowSpawnRates.put(BowsID.BOW_TRICKBOW, 10);
        bowSpawnRates.put(BowsID.BOW_TWIN_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_WINTERS_TOUCH, 5);
        bowSpawnRates.put(BowsID.BOW_SHIVERING_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_WIND_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_ECHO_OF_THE_VALLEY, 5);
        bowSpawnRates.put(BowsID.BOW_BURST_GALE_BOW, 5);
        bowSpawnRates.put(BowsID.BOW_TWISTING_VINE_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_WEEPING_VINE_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_BUBBLE_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_BUBBLE_BURSTER, 5);
        bowSpawnRates.put(BowsID.BOW_VOID_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_CALL_OF_THE_VOID, 5);
        bowSpawnRates.put(BowsID.BOW_PHANTOM_BOW, 10);
        bowSpawnRates.put(BowsID.BOW_WEB_BOW, 10);

        //LONG BOWS
        longBowSpawnRates.put(LongBowsID.BOW_LONGBOW, 10);
        longBowSpawnRates.put(LongBowsID.BOW_GUARDIAN_BOW, 5);
        longBowSpawnRates.put(LongBowsID.BOW_RED_SNAKE, 5);

        //SHORT BOWS
        shortBowSpawnRates.put(ShortBowsID.BOW_SHORTBOW, 10);
        shortBowSpawnRates.put(ShortBowsID.BOW_MECHANICAL_SHORTBOW, 5);
        shortBowSpawnRates.put(ShortBowsID.BOW_LOVE_SPELL_BOW, 5);
        shortBowSpawnRates.put(ShortBowsID.BOW_PURPLE_STORM, 5);

        //CROSSBOWS
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_THE_SLICER, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_AZURE_SEEKER, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_FIREBOLT_THROWER, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_DOOM_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_RAPID_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_AUTO_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_HARP_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SOUL_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_DUAL_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_BABY_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_BURST_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_COG_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW, 5);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_VEILED_CROSSBOW, 5);

        //SHIELDS
        shieldSpawnRates.put(ShieldsID.SHIELD_ROYAL_GUARD, 5);
        shieldSpawnRates.put(ShieldsID.SHIELD_VANGUARD, 5);
    }

}
