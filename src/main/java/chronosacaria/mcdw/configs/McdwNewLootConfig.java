package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.EnumMap;

@Config(name = "mcdw_loot_config")
public class McdwNewLootConfig implements ConfigData {
    public EnumMap<SettingsID, Boolean> weaponsEnabledInLootTables = new EnumMap<>(SettingsID.class);

    public EnumMap<SettingsID, ArrayList<String>> commonLootTables = new EnumMap<>(SettingsID.class);
    public EnumMap<SettingsID, ArrayList<String>> uncommonLootTables = new EnumMap<>(SettingsID.class);
    public EnumMap<SettingsID, ArrayList<String>> rareLootTables = new EnumMap<>(SettingsID.class);
    public EnumMap<SettingsID, ArrayList<String>> epicLootTables = new EnumMap<>(SettingsID.class);

    public EnumMap<AxesID, Float> axeSpawnRates = new EnumMap<>(AxesID.class);
    public EnumMap<DaggersID, Float> daggerSpawnRates = new EnumMap<>(DaggersID.class);
    public EnumMap<DoubleAxesID, Float> doubleAxeSpawnRates = new EnumMap<>(DoubleAxesID.class);
    public EnumMap<GauntletsID, Float> gauntletSpawnRates = new EnumMap<>(GauntletsID.class);
    public EnumMap<GlaivesID, Float> glaiveSpawnRates = new EnumMap<>(GlaivesID.class);
    public EnumMap<HammersID, Float> hammerSpawnRates = new EnumMap<>(HammersID.class);
    public EnumMap<PicksID, Float> pickSpawnRates = new EnumMap<>(PicksID.class);
    public EnumMap<ScythesID, Float> scytheSpawnRates = new EnumMap<>(ScythesID.class);
    public EnumMap<SicklesID, Float> sickleSpawnRates = new EnumMap<>(SicklesID.class);
    public EnumMap<SoulDaggersID, Float> soulDaggerSpawnRates = new EnumMap<>(SoulDaggersID.class);
    public EnumMap<SpearsID, Float> spearSpawnRates = new EnumMap<>(SpearsID.class);
    public EnumMap<StavesID, Float> staffSpawnRates = new EnumMap<>(StavesID.class);
    public EnumMap<SwordsID, Float> swordSpawnRates = new EnumMap<>(SwordsID.class);
    public EnumMap<WhipsID, Float> whipSpawnRates = new EnumMap<>(WhipsID.class);

    public EnumMap<BowsID, Float> bowSpawnRates = new EnumMap<>(BowsID.class);
    public EnumMap<LongBowsID, Float> longBowSpawnRates = new EnumMap<>(LongBowsID.class);
    public EnumMap<ShortBowsID, Float> shortBowSpawnRates = new EnumMap<>(ShortBowsID.class);
    public EnumMap<CrossbowsID, Float> crossbowSpawnRates = new EnumMap<>(CrossbowsID.class);

    public EnumMap<ShieldsID, Float> shieldSpawnRates = new EnumMap<>(ShieldsID.class);

    public McdwNewLootConfig(){
        for (SettingsID weaponsEnabled : SettingsID.values()){
            weaponsEnabledInLootTables.put(SettingsID.ENABLE_WEAPONS_IN_LOOTTABLES, true);
        }

        for (SettingsID commonLootTable : SettingsID.values()) {
            commonLootTables.put(SettingsID.COMMON_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/abandoned_mineshaft",
                    "minecraft:chests/shipwreck_supply",
                    "minecraft:chests/shipwreck_treasure",
                    "minecraft:chests/desert_pyramid",
                    "minecraft:chests/village/village_weaponsmith"));
        }
        for (SettingsID uncommonLootTable : SettingsID.values()) {
            uncommonLootTables.put(SettingsID.UNCOMMON_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/jungle_temple",
                    "minecraft:chests/nether_bridge",
                    "minecraft:chests/bastion"));
        }
        for (SettingsID rareLootTable : SettingsID.values()) {
            rareLootTables.put(SettingsID.RARE_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/underwater_ruin",
                    "minecraft:chests/ruined_portal",
                    "minecraft:chests/simple_dungeon",
                    "minecraft:chests/igloo_chest",
                    "minecraft:chests/pillager_outpost"));
        }
        for (SettingsID epicLootTable : SettingsID.values()) {
            epicLootTables.put(SettingsID.EPIC_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:stronghold",
                    "minecraft:chests/end_city_treasure"));
        }

        for (AxesID axesID : AxesID.values()) {
            axeSpawnRates.put(AxesID.AXE, 0.1f);
            axeSpawnRates.put(AxesID.AXE_FIREBRAND, 0.05f);
            axeSpawnRates.put(AxesID.AXE_HIGHLAND, 0.05f);
            axeSpawnRates.put(AxesID.AXE_ANCHOR, 0.1f);
            axeSpawnRates.put(AxesID.AXE_ENCRUSTED_ANCHOR, 0.05f);
        }
        for (DaggersID daggersID : DaggersID.values()) {
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
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            doubleAxeSpawnRates.put(DoubleAxesID.AXE_DOUBLE, 0.1f);
            doubleAxeSpawnRates.put(DoubleAxesID.AXE_CURSED, 0.05f);
            doubleAxeSpawnRates.put(DoubleAxesID.AXE_WHIRLWIND, 0.05f);
        }
        for (GauntletsID gauntletsID : GauntletsID.values()) {
            gauntletSpawnRates.put(GauntletsID.GAUNTLET_GAUNTLET, 0.1f);
            gauntletSpawnRates.put(GauntletsID.GAUNTLET_MAULERS, 0.05f);
            gauntletSpawnRates.put(GauntletsID.GAUNTLET_SOUL_FISTS, 0.05f);
        }
        for (GlaivesID glaivesID : GlaivesID.values()) {
            glaiveSpawnRates.put(GlaivesID.SPEAR_GLAIVE, 0.1f);
            glaiveSpawnRates.put(GlaivesID.SPEAR_GRAVE_BANE, 0.05f);
            glaiveSpawnRates.put(GlaivesID.SPEAR_VENOM_GLAIVE, 0.05f);
            glaiveSpawnRates.put(GlaivesID.SPEAR_CACKLING_BROOM, 0.05f);
        }
        for (HammersID hammersID : HammersID.values()) {
            hammerSpawnRates.put(HammersID.HAMMER_GREAT, 0.1f);
            hammerSpawnRates.put(HammersID.HAMMER_STORMLANDER, 0.05f);
            hammerSpawnRates.put(HammersID.HAMMER_GRAVITY, 0.05f);
            hammerSpawnRates.put(HammersID.HAMMER_MACE, 0.1f);
            hammerSpawnRates.put(HammersID.HAMMER_FLAIL, 0.05f);
            hammerSpawnRates.put(HammersID.HAMMER_SUNS_GRACE, 0.00f);
            hammerSpawnRates.put(HammersID.HAMMER_BONECLUB, 0.1f);
            hammerSpawnRates.put(HammersID.HAMMER_BONE_CUDGEL, 0.05f);
        }
        for (PicksID picksID : PicksID.values()) {
            pickSpawnRates.put(PicksID.PICK_DIAMOND_PICKAXE_VAR, 0.1f);
            pickSpawnRates.put(PicksID.PICK_MOUNTAINEER_PICK, 0.1f);
            pickSpawnRates.put(PicksID.PICK_HOWLING_PICK, 0.1f);
            pickSpawnRates.put(PicksID.PICK_HAILING_PINNACLE, 0.1f);
        }
        for (ScythesID scythesID : ScythesID.values()) {
            scytheSpawnRates.put(ScythesID.SICKLE_JAILORS_SCYTHE, 0.1f);
            scytheSpawnRates.put(ScythesID.SICKLE_SOUL_SCYTHE, 0.05f);
            scytheSpawnRates.put(ScythesID.SICKLE_FROST_SCYTHE, 0.05f);
            scytheSpawnRates.put(ScythesID.SICKLE_SKULL_SCYTHE, 0.05f);
        }
        for (SicklesID sicklesID : SicklesID.values()) {
            sickleSpawnRates.put(SicklesID.SICKLE_SICKLE, 0.1f);
            sickleSpawnRates.put(SicklesID.SICKLE_NIGHTMARES_BITE, 0.05f);
            sickleSpawnRates.put(SicklesID.SICKLE_LAST_LAUGH_GOLD, 0.05f);
            sickleSpawnRates.put(SicklesID.SICKLE_LAST_LAUGH_SILVER, 0.05f);
        }
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values()) {
            soulDaggerSpawnRates.put(SoulDaggersID.DAGGER_SOUL_KNIFE, 0.1f);
            soulDaggerSpawnRates.put(SoulDaggersID.DAGGER_ETERNAL_KNIFE, 0.05f);
            soulDaggerSpawnRates.put(SoulDaggersID.SWORD_TRUTHSEEKER, 0.05f);
        }
        for (SpearsID spearsID : SpearsID.values()) {
            spearSpawnRates.put(SpearsID.SPEAR_SPEAR, 0.1f);
            spearSpawnRates.put(SpearsID.SPEAR_WHISPERING_SPEAR, 0.05f);
            spearSpawnRates.put(SpearsID.SPEAR_FORTUNE, 0.05f);
        }
        for (StavesID stavesID : StavesID.values()) {
            staffSpawnRates.put(StavesID.STAFF_BATTLESTAFF, 0.1f);
            staffSpawnRates.put(StavesID.STAFF_GROWING_STAFF, 0.05f);
            staffSpawnRates.put(StavesID.STAFF_BATTLESTAFF_OF_TERROR, 0.05f);
        }
        for (SwordsID swordsID : SwordsID.values()) {
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
        }
        for (WhipsID whipsID : WhipsID.values()) {
            whipSpawnRates.put(WhipsID.WHIP_WHIP, 0.1f);
            whipSpawnRates.put(WhipsID.WHIP_VINE_WHIP, 0.05f);
        }
        for (BowsID bowsID : BowsID.values()) {
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
            bowSpawnRates.put(BowsID.BOW_BUBBLE_BURSTER, 0.5f);
            bowSpawnRates.put(BowsID.BOW_VOID_BOW, 0.1f);
            bowSpawnRates.put(BowsID.BOW_CALL_OF_THE_VOID, 0.05f);
            bowSpawnRates.put(BowsID.BOW_PHANTOM_BOW, 0.1f);
            bowSpawnRates.put(BowsID.BOW_WEB_BOW, 0.1f);
        }
        for (LongBowsID longBowsID : LongBowsID.values()){
            longBowSpawnRates.put(LongBowsID.BOW_LONGBOW, 0.1f);
            longBowSpawnRates.put(LongBowsID.BOW_GUARDIAN_BOW, 0.05f);
            longBowSpawnRates.put(LongBowsID.BOW_RED_SNAKE, 0.05f);
        }
        for (ShortBowsID shortBowsID : ShortBowsID.values()){
            shortBowSpawnRates.put(ShortBowsID.BOW_SHORTBOW, 0.1f);
            shortBowSpawnRates.put(ShortBowsID.BOW_MECHANICAL_SHORTBOW, 0.05f);
            shortBowSpawnRates.put(ShortBowsID.BOW_LOVE_SPELL_BOW, 0.05f);
            shortBowSpawnRates.put(ShortBowsID.BOW_PURPLE_STORM, 0.05f);
        }
        for (CrossbowsID crossbowsID : CrossbowsID.values()){
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
        }
    }

}
