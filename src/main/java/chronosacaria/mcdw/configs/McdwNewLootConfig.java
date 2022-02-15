package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.HashMap;

@Config(name = "mcdw_loot_config")
public class McdwNewLootConfig implements ConfigData {
    public HashMap<SettingsID, Boolean> weaponsEnabledInLootTables = new HashMap<>();

    public HashMap<SettingsID, ArrayList<String>> commonLootTables = new HashMap<>();
    public HashMap<SettingsID, ArrayList<String>> uncommonLootTables = new HashMap<>();
    public HashMap<SettingsID, ArrayList<String>> rareLootTables = new HashMap<>();
    public HashMap<SettingsID, ArrayList<String>> epicLootTables = new HashMap<>();

    public HashMap<AxesID, Float> axeSpawnRates = new HashMap<>();
    public HashMap<DaggersID, Float> daggerSpawnRates = new HashMap<>();
    public HashMap<DoubleAxesID, Float> doubleAxeSpawnRates = new HashMap<>();
    public HashMap<GauntletsID, Float> gauntletSpawnRates = new HashMap<>();
    public HashMap<GlaivesID, Float> glaiveSpawnRates = new HashMap<>();
    public HashMap<HammersID, Float> hammerSpawnRates = new HashMap<>();
    public HashMap<PicksID, Float> pickSpawnRates = new HashMap<>();
    public HashMap<ScythesID, Float> scytheSpawnRates = new HashMap<>();
    public HashMap<SicklesID, Float> sickleSpawnRates = new HashMap<>();
    public HashMap<SoulDaggersID, Float> soulDaggerSpawnRates = new HashMap<>();
    public HashMap<SpearsID, Float> spearSpawnRates = new HashMap<>();
    public HashMap<StavesID, Float> staffSpawnRates = new HashMap<>();
    public HashMap<SwordsID, Float> swordSpawnRates = new HashMap<>();
    public HashMap<WhipsID, Float> whipSpawnRates = new HashMap<>();
    public HashMap<BowsID, Float> bowSpawnRates = new HashMap<>();
    public HashMap<LongBowsID, Float> longBowSpawnRates = new HashMap<>();
    public HashMap<ShortBowsID, Float> shortBowSpawnRates = new HashMap<>();
    public HashMap<CrossbowsID, Float> crossbowSpawnRates = new HashMap<>();
    public HashMap<ShieldsID, Float> shieldSpawnRates = new HashMap<>();

    public McdwNewLootConfig(){
        weaponsEnabledInLootTables.put(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES, true);

        /* LOOT TABLES */

        // COMMON
        commonLootTables.put(SettingsID.COMMON_LOOT_TABLES, Lists.newArrayList(
                "minecraft:chests/abandoned_mineshaft",
                "minecraft:chests/shipwreck_supply",
                "minecraft:chests/shipwreck_treasure",
                "minecraft:chests/desert_pyramid",
                "minecraft:chests/village/village_weaponsmith"));

        //UNCOMMON
        uncommonLootTables.put(SettingsID.UNCOMMON_LOOT_TABLES, Lists.newArrayList(
                "minecraft:chests/jungle_temple",
                "minecraft:chests/nether_bridge",
                "minecraft:chests/bastion_bridge",
                "minecraft:chests/bastion_other",
                "minecraft:chests/bastion_treasure"));

        //RARE
        rareLootTables.put(SettingsID.RARE_LOOT_TABLES, Lists.newArrayList(
                "minecraft:chests/underwater_ruin_small",
                "minecraft:chests/underwater_ruin_big",
                "minecraft:chests/ruined_portal",
                "minecraft:chests/simple_dungeon",
                "minecraft:chests/igloo_chest",
                "minecraft:chests/pillager_outpost"));

        //EPIC
        epicLootTables.put(SettingsID.EPIC_LOOT_TABLES, Lists.newArrayList(
                "minecraft:chests/stronghold_corridor",
                "minecraft:chests/stronghold_crossing",
                "minecraft:chests/stronghold_library",
                "minecraft:chests/end_city_treasure"));

        /* SPAWN RATES */

        //AXES
        axeSpawnRates.put(AxesID.AXE, 0.1f);
        axeSpawnRates.put(AxesID.AXE_FIREBRAND, 0.05f);
        axeSpawnRates.put(AxesID.AXE_HIGHLAND, 0.05f);
        axeSpawnRates.put(AxesID.AXE_ANCHOR, 0.1f);
        axeSpawnRates.put(AxesID.AXE_ENCRUSTED_ANCHOR, 0.05f);

        //DAGGERS
        daggerSpawnRates.put(DaggersID.DAGGER_DAGGER, 0.1f);
        daggerSpawnRates.put(DaggersID.DAGGER_FANGS_OF_FROST, 0.05f);
        daggerSpawnRates.put(DaggersID.DAGGER_MOON, 0.05f);
        daggerSpawnRates.put(DaggersID.DAGGER_SHEAR_DAGGER, 0.1f);
        daggerSpawnRates.put(DaggersID.DAGGER_TEMPEST_KNIFE, 0.1f);
        daggerSpawnRates.put(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, 0.05f);
        daggerSpawnRates.put(DaggersID.DAGGER_CHILL_GALE_KNIFE, 0.05f);
        daggerSpawnRates.put(DaggersID.DAGGER_BACKSTABBER, 0.1f);
        daggerSpawnRates.put(DaggersID.DAGGER_SWIFT_STRIKER, 0.05f);
        daggerSpawnRates.put(DaggersID.DAGGER_VOID_TOUCHED_BLADE, 0.1f);
        daggerSpawnRates.put(DaggersID.DAGGER_THE_BEGINNING, 0.05f);
        daggerSpawnRates.put(DaggersID.DAGGER_THE_END, 0.05f);

        //DOUBLE AXES
        doubleAxeSpawnRates.put(DoubleAxesID.AXE_DOUBLE, 0.1f);
        doubleAxeSpawnRates.put(DoubleAxesID.AXE_CURSED, 0.05f);
        doubleAxeSpawnRates.put(DoubleAxesID.AXE_WHIRLWIND, 0.05f);
        gauntletSpawnRates.put(GauntletsID.GAUNTLET_GAUNTLET, 0.1f);
        gauntletSpawnRates.put(GauntletsID.GAUNTLET_MAULERS, 0.05f);
        gauntletSpawnRates.put(GauntletsID.GAUNTLET_SOUL_FISTS, 0.05f);

        //GLAIVES
        glaiveSpawnRates.put(GlaivesID.SPEAR_GLAIVE, 0.1f);
        glaiveSpawnRates.put(GlaivesID.SPEAR_GRAVE_BANE, 0.05f);
        glaiveSpawnRates.put(GlaivesID.SPEAR_VENOM_GLAIVE, 0.05f);
        glaiveSpawnRates.put(GlaivesID.SPEAR_CACKLING_BROOM, 0.05f);

        //HAMMERS
        hammerSpawnRates.put(HammersID.HAMMER_GREAT, 0.1f);
        hammerSpawnRates.put(HammersID.HAMMER_STORMLANDER, 0.05f);
        hammerSpawnRates.put(HammersID.HAMMER_GRAVITY, 0.05f);
        hammerSpawnRates.put(HammersID.HAMMER_MACE, 0.1f);
        hammerSpawnRates.put(HammersID.HAMMER_FLAIL, 0.05f);
        hammerSpawnRates.put(HammersID.HAMMER_SUNS_GRACE, 0.00f);
        hammerSpawnRates.put(HammersID.HAMMER_BONECLUB, 0.1f);
        hammerSpawnRates.put(HammersID.HAMMER_BONE_CUDGEL, 0.05f);

        //PICKS
        pickSpawnRates.put(PicksID.PICK_DIAMOND_PICKAXE_VAR, 0.1f);
        pickSpawnRates.put(PicksID.PICK_MOUNTAINEER_PICK, 0.1f);
        pickSpawnRates.put(PicksID.PICK_HOWLING_PICK, 0.1f);
        pickSpawnRates.put(PicksID.PICK_HAILING_PINNACLE, 0.1f);

        //SCYTHES
        scytheSpawnRates.put(ScythesID.SICKLE_JAILORS_SCYTHE, 0.1f);
        scytheSpawnRates.put(ScythesID.SICKLE_SOUL_SCYTHE, 0.05f);
        scytheSpawnRates.put(ScythesID.SICKLE_FROST_SCYTHE, 0.05f);
        scytheSpawnRates.put(ScythesID.SICKLE_SKULL_SCYTHE, 0.05f);

        //SICKLES
        sickleSpawnRates.put(SicklesID.SICKLE_SICKLE, 0.1f);
        sickleSpawnRates.put(SicklesID.SICKLE_NIGHTMARES_BITE, 0.05f);
        sickleSpawnRates.put(SicklesID.SICKLE_LAST_LAUGH_GOLD, 0.05f);
        sickleSpawnRates.put(SicklesID.SICKLE_LAST_LAUGH_SILVER, 0.05f);

        //SOUL DAGGERS
        soulDaggerSpawnRates.put(SoulDaggersID.DAGGER_SOUL_KNIFE, 0.1f);
        soulDaggerSpawnRates.put(SoulDaggersID.DAGGER_ETERNAL_KNIFE, 0.05f);
        soulDaggerSpawnRates.put(SoulDaggersID.SWORD_TRUTHSEEKER, 0.05f);

        //SPEARS
        spearSpawnRates.put(SpearsID.SPEAR_SPEAR, 0.1f);
        spearSpawnRates.put(SpearsID.SPEAR_WHISPERING_SPEAR, 0.05f);
        spearSpawnRates.put(SpearsID.SPEAR_FORTUNE, 0.05f);

        //STAVES
        staffSpawnRates.put(StavesID.STAFF_BATTLESTAFF, 0.1f);
        staffSpawnRates.put(StavesID.STAFF_GROWING_STAFF, 0.05f);
        staffSpawnRates.put(StavesID.STAFF_BATTLESTAFF_OF_TERROR, 0.05f);

        //SWORDS
        swordSpawnRates.put(SwordsID.SWORD_BROADSWORD, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_CLAYMORE, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_FROST_SLAYER, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_HEARTSTEALER, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_GREAT_AXEBLADE, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_RAPIER, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_BEESTINGER, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_FREEZING_FOIL, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_CUTLASS, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_NAMELESS_BLADE, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_DANCERS_SWORD, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_KATANA, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_MASTERS_KATANA, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_DARK_KATANA, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_IRON_SWORD_VAR, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_DIAMOND_SWORD_VAR, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_HAWKBRAND, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_SINISTER, 0.01f);
        swordSpawnRates.put(SwordsID.SWORD_BROKEN_SAWBLADE, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_MECHANIZED_SAWBLADE, 0.01f);
        swordSpawnRates.put(SwordsID.SWORD_CORAL_BLADE, 0.1f);
        swordSpawnRates.put(SwordsID.SWORD_SPONGE_STRIKER, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_OBSIDIAN_CLAYMORE, 0.05f);
        swordSpawnRates.put(SwordsID.SWORD_THE_STARLESS_NIGHT, 0.01f);

        //WHIPS
        whipSpawnRates.put(WhipsID.WHIP_WHIP, 0.1f);
        whipSpawnRates.put(WhipsID.WHIP_VINE_WHIP, 0.05f);

        //BOWS
        bowSpawnRates.put(BowsID.BOW_ANCIENT_BOW, 0.0f);
        bowSpawnRates.put(BowsID.BOW_BONEBOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_LOST_SOULS, 0.05f);
        bowSpawnRates.put(BowsID.BOW_ELITE_POWER_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_HAUNTED_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_HUNTERS_PROMISE, 0.05f);
        bowSpawnRates.put(BowsID.BOW_HUNTING_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_MASTERS_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_NOCTURNAL_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_POWER_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_SABREWING, 0.05f);
        bowSpawnRates.put(BowsID.BOW_SNOW_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_SOUL_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_GREEN_MENACE, 0.05f);
        bowSpawnRates.put(BowsID.BOW_PINK_SCOUNDREL, 0.05f);
        bowSpawnRates.put(BowsID.BOW_TRICKBOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_TWIN_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_WINTERS_TOUCH, 0.05f);
        bowSpawnRates.put(BowsID.BOW_SHIVERING_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_WIND_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_ECHO_OF_THE_VALLEY, 0.05f);
        bowSpawnRates.put(BowsID.BOW_BURST_GALE_BOW, 0.05f);
        bowSpawnRates.put(BowsID.BOW_TWISTING_VINE_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_WEEPING_VINE_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_BUBBLE_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_BUBBLE_BURSTER, 0.05f);
        bowSpawnRates.put(BowsID.BOW_VOID_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_CALL_OF_THE_VOID, 0.05f);
        bowSpawnRates.put(BowsID.BOW_PHANTOM_BOW, 0.1f);
        bowSpawnRates.put(BowsID.BOW_WEB_BOW, 0.1f);

        //LONG BOWS
        longBowSpawnRates.put(LongBowsID.BOW_LONGBOW, 0.1f);
        longBowSpawnRates.put(LongBowsID.BOW_GUARDIAN_BOW, 0.05f);
        longBowSpawnRates.put(LongBowsID.BOW_RED_SNAKE, 0.05f);

        //SHORT BOWS
        shortBowSpawnRates.put(ShortBowsID.BOW_SHORTBOW, 0.1f);
        shortBowSpawnRates.put(ShortBowsID.BOW_MECHANICAL_SHORTBOW, 0.05f);
        shortBowSpawnRates.put(ShortBowsID.BOW_LOVE_SPELL_BOW, 0.05f);
        shortBowSpawnRates.put(ShortBowsID.BOW_PURPLE_STORM, 0.05f);

        //CROSSBOWS
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_THE_SLICER, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_AZURE_SEEKER, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_FIREBOLT_THROWER, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_DOOM_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_RAPID_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_AUTO_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_HARP_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SOUL_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_DUAL_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_BABY_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_BURST_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_COG_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW, 0.05f);
        crossbowSpawnRates.put(CrossbowsID.CROSSBOW_VEILED_CROSSBOW, 0.05f);

        //SHIELDS
        shieldSpawnRates.put(ShieldsID.SHIELD_ROYAL_GUARD, 0.05f);
        shieldSpawnRates.put(ShieldsID.SHIELD_VANGUARD, 0.05f);
    }

}
