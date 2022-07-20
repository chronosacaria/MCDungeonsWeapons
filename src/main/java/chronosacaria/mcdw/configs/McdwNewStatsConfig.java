package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.configs.stats.RangedWeaponStats;
import chronosacaria.mcdw.configs.stats.ShieldStats;
import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.HashMap;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public final HashMap<SwordsID, MeleeWeaponStats> swordStats = new HashMap<>();
    public final HashMap<AxesID, MeleeWeaponStats> axeStats = new HashMap<>();
    public final HashMap<DoubleAxesID, MeleeWeaponStats> doubleAxeStats = new HashMap<>();
    public final HashMap<DaggersID, MeleeWeaponStats> daggerStats = new HashMap<>();
    public final HashMap<SoulDaggersID, MeleeWeaponStats> soulDaggerStats = new HashMap<>();
    public final HashMap<HammersID, MeleeWeaponStats> hammerStats = new HashMap<>();
    public final HashMap<GauntletsID, MeleeWeaponStats> gauntletStats = new HashMap<>();
    public final HashMap<SicklesID, MeleeWeaponStats> sickleStats = new HashMap<>();
    public final HashMap<ScythesID, MeleeWeaponStats> scytheStats = new HashMap<>();
    public final HashMap<PicksID, MeleeWeaponStats> pickStats = new HashMap<>();
    public final HashMap<GlaivesID, MeleeWeaponStats> glaiveStats = new HashMap<>();
    public final HashMap<SpearsID, MeleeWeaponStats> spearStats = new HashMap<>();
    public final HashMap<StavesID, MeleeWeaponStats> staffStats = new HashMap<>();
    public final HashMap<WhipsID, MeleeWeaponStats> whipStats = new HashMap<>();
    public final HashMap<BowsID, RangedWeaponStats> bowStats = new HashMap<>();
    public final HashMap<ShortBowsID, RangedWeaponStats> shortBowStats = new HashMap<>();
    public final HashMap<LongBowsID, RangedWeaponStats> longBowStats = new HashMap<>();
    public final HashMap<CrossbowsID, RangedWeaponStats> crossbowStats = new HashMap<>();
    public final HashMap<ShieldsID, ShieldStats> shieldStats = new HashMap<>();



    // convenience methods:
    protected MeleeWeaponStats swordStats(String material, int damage, float attackSpeed, SwordsID swordsID){
        return swordStats.get(swordsID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats axeStats(String material, int damage, float attackSpeed, AxesID axesID){
        return axeStats.get(axesID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats doubleAxeStats(String material, int damage, float attackSpeed, DoubleAxesID doubleAxesID){
        return doubleAxeStats.get(doubleAxesID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats daggerStats(String material, int damage, float attackSpeed, DaggersID daggersID){
        return daggerStats.get(daggersID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats soulDaggerStats(String material, int damage, float attackSpeed, SoulDaggersID soulDaggersID){
        return soulDaggerStats.get(soulDaggersID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats hammerStats(String material, int damage, float attackSpeed, HammersID hammersID){
        return hammerStats.get(hammersID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats gauntletStats(String material, int damage, float attackSpeed, GauntletsID gauntletsID){
        return gauntletStats.get(gauntletsID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats sickleStats(String material, int damage, float attackSpeed, SicklesID sicklesID){
        return sickleStats.get(sicklesID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats scytheStats(String material, int damage, float attackSpeed, ScythesID scythesID){
        return scytheStats.get(scythesID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats pickStats(String material, int damage, float attackSpeed, PicksID picksID){
        return pickStats.get(picksID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats glaiveStats(String material, int damage, float attackSpeed, GlaivesID glaivesID){
        return glaiveStats.get(glaivesID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats spearStats(String material, int damage, float attackSpeed, SpearsID spearsID){
        return spearStats.get(spearsID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats staffStats(String material, int damage, float attackSpeed, StavesID stavesID){
        return staffStats.get(stavesID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats whipStats(String material, int damage, float attackSpeed, WhipsID whipsID){
        return whipStats.get(whipsID).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected RangedWeaponStats bowStats(String material, int drawSpeed, float range, BowsID bowsID){
        return bowStats.get(bowsID).rangedWeaponStats(material, drawSpeed, range);
    }
    protected RangedWeaponStats shortBowStats(String material, int drawSpeed, float range, ShortBowsID shortBowsID){
        return shortBowStats.get(shortBowsID).rangedWeaponStats(material, drawSpeed, range);
    }
    protected RangedWeaponStats longBowStats(String material, int drawSpeed, float range, LongBowsID longBowsID){
        return longBowStats.get(longBowsID).rangedWeaponStats(material, drawSpeed, range);
    }
    protected RangedWeaponStats crossbowStats(String material, int drawSpeed, float range, CrossbowsID crossbowsID){
        return crossbowStats.get(crossbowsID).rangedWeaponStats(material, drawSpeed, range);
    }
    protected ShieldStats shieldStats(String material, ShieldsID shieldsID){
        return shieldStats.get(shieldsID).shieldStats(material);
    }

    public McdwNewStatsConfig() {
        for (SwordsID swordsID : SwordsID.values())
            swordStats.put(swordsID, new MeleeWeaponStats());

        for (AxesID axesID : AxesID.values())
            axeStats.put(axesID, new MeleeWeaponStats());

        for (DoubleAxesID doubleAxesID : DoubleAxesID.values())
            doubleAxeStats.put(doubleAxesID, new MeleeWeaponStats());

        for (DaggersID daggersID : DaggersID.values())
            daggerStats.put(daggersID, new MeleeWeaponStats());

        for (SoulDaggersID soulDaggersID : SoulDaggersID.values())
            soulDaggerStats.put(soulDaggersID, new MeleeWeaponStats());

        for (HammersID hammersID : HammersID.values())
            hammerStats.put(hammersID, new MeleeWeaponStats());

        for (GauntletsID gauntletsID : GauntletsID.values())
            gauntletStats.put(gauntletsID, new MeleeWeaponStats());

        for (SicklesID sicklesID : SicklesID.values())
            sickleStats.put(sicklesID, new MeleeWeaponStats());

        for (ScythesID scythesID : ScythesID.values())
            scytheStats.put(scythesID, new MeleeWeaponStats());

        for (PicksID picksID : PicksID.values())
            pickStats.put(picksID, new MeleeWeaponStats());

        for (GlaivesID glaivesID : GlaivesID.values())
            glaiveStats.put(glaivesID, new MeleeWeaponStats());

        for (SpearsID spearsID : SpearsID.values())
            spearStats.put(spearsID, new MeleeWeaponStats());

        for (StavesID stavesID : StavesID.values())
            staffStats.put(stavesID, new MeleeWeaponStats());

        for (WhipsID whipsID : WhipsID.values())
            whipStats.put(whipsID, new MeleeWeaponStats());

        for (BowsID bowsID : BowsID.values())
            bowStats.put(bowsID, new RangedWeaponStats());

        for (ShortBowsID shortBowsID : ShortBowsID.values())
            shortBowStats.put(shortBowsID, new RangedWeaponStats());

        for (LongBowsID longBowsID : LongBowsID.values())
            longBowStats.put(longBowsID, new RangedWeaponStats());

        for (CrossbowsID crossbowsID : CrossbowsID.values())
            crossbowStats.put(crossbowsID, new RangedWeaponStats());

        for (ShieldsID shieldsID : ShieldsID.values())
            shieldStats.put(shieldsID, new ShieldStats());

        swordStats(materialToString(ToolMaterials.IRON), 5, -3.0f, SwordsID.SWORD_CLAYMORE);
        swordStats(materialToString(ToolMaterials.IRON), 4, -3.0f, SwordsID.SWORD_BROADSWORD);
        swordStats(materialToString(ToolMaterials.DIAMOND), 5, -3.0f, SwordsID.SWORD_FROST_SLAYER);
        swordStats(materialToString(ToolMaterials.DIAMOND), 4, -3.0f, SwordsID.SWORD_HEARTSTEALER);
        swordStats(materialToString(ToolMaterials.IRON), 6, -3.0f, SwordsID.SWORD_GREAT_AXEBLADE);
        swordStats(materialToString(ToolMaterials.NETHERITE), 6, -3.0f, SwordsID.SWORD_OBSIDIAN_CLAYMORE);
        swordStats(materialToString(ToolMaterials.NETHERITE), 8, -3.0f,SwordsID.SWORD_THE_STARLESS_NIGHT);

        swordStats(materialToString(ToolMaterials.IRON),0, -0.9f, SwordsID.SWORD_RAPIER);
        swordStats(materialToString(ToolMaterials.IRON),0, -0.9f, SwordsID.SWORD_BEESTINGER);
        swordStats(materialToString(ToolMaterials.IRON),0, -0.9f, SwordsID.SWORD_FREEZING_FOIL);

        swordStats(materialToString(ToolMaterials.IRON),3, -2.7f, SwordsID.SWORD_CUTLASS);
        swordStats(materialToString(ToolMaterials.IRON),1, -1.7f, SwordsID.SWORD_NAMELESS_BLADE);
        swordStats(materialToString(ToolMaterials.IRON),1, -1.0f, SwordsID.SWORD_DANCERS_SWORD);

        swordStats(materialToString(ToolMaterials.IRON),1, -1.5f, SwordsID.SWORD_KATANA);
        swordStats(materialToString(ToolMaterials.DIAMOND),1, -1.1f, SwordsID.SWORD_MASTERS_KATANA);
        swordStats(materialToString(ToolMaterials.NETHERITE),2, -1.15f,SwordsID.SWORD_DARK_KATANA);

        swordStats(materialToString(ToolMaterials.IRON),3, -2.4f, SwordsID.SWORD_IRON_SWORD_VAR);
        swordStats(materialToString(ToolMaterials.DIAMOND),3, -2.4f, SwordsID.SWORD_DIAMOND_SWORD_VAR);
        swordStats(materialToString(ToolMaterials.IRON),5, -2.0f, SwordsID.SWORD_HAWKBRAND);
        swordStats(materialToString(ToolMaterials.IRON),5, -2.0f, SwordsID.SWORD_SINISTER);

        swordStats(materialToString(ToolMaterials.IRON),3, -2.4f, SwordsID.SWORD_BROKEN_SAWBLADE);
        swordStats(materialToString(ToolMaterials.DIAMOND),3, -2.4f, SwordsID.SWORD_MECHANIZED_SAWBLADE);

        swordStats(materialToString(ToolMaterials.IRON),3, -2.4f, SwordsID.SWORD_CORAL_BLADE);
        swordStats(materialToString(ToolMaterials.DIAMOND),3, -2.4f, SwordsID.SWORD_SPONGE_STRIKER);

        axeStats(materialToString(ToolMaterials.IRON),6, -3.1f, AxesID.AXE_AXE);
        axeStats(materialToString(ToolMaterials.DIAMOND),5, -2.9f, AxesID.AXE_FIREBRAND);
        axeStats(materialToString(ToolMaterials.IRON),6, -3.1f, AxesID.AXE_HIGHLAND);

        axeStats(materialToString(ToolMaterials.IRON),6, -3.1f, AxesID.AXE_ANCHOR);
        axeStats(materialToString(ToolMaterials.DIAMOND),5, -3.1f, AxesID.AXE_ENCRUSTED_ANCHOR);

        doubleAxeStats(materialToString(ToolMaterials.IRON),6, -3.1f, DoubleAxesID.AXE_DOUBLE);
        doubleAxeStats(materialToString(ToolMaterials.IRON),7, -3.1f, DoubleAxesID.AXE_CURSED);
        doubleAxeStats(materialToString(ToolMaterials.IRON),6, -2.9f, DoubleAxesID.AXE_WHIRLWIND);

        daggerStats(materialToString(ToolMaterials.IRON),1, -1.3f, DaggersID.DAGGER_DAGGER);
        daggerStats(materialToString(ToolMaterials.IRON),1, -1.0f, DaggersID.DAGGER_FANGS_OF_FROST);
        daggerStats(materialToString(ToolMaterials.IRON),1, -1.0f, DaggersID.DAGGER_MOON);
        daggerStats(materialToString(ToolMaterials.IRON),1, -1.3f, DaggersID.DAGGER_SHEAR_DAGGER);
        daggerStats(materialToString(ToolMaterials.DIAMOND),3, -1.2f, DaggersID.DAGGER_BACKSTABBER);
        daggerStats(materialToString(ToolMaterials.NETHERITE),4, -1.0f, DaggersID.DAGGER_SWIFT_STRIKER);
        daggerStats(materialToString(ToolMaterials.DIAMOND),4, -1.2f, DaggersID.DAGGER_VOID_TOUCHED_BLADE);
        daggerStats(materialToString(ToolMaterials.NETHERITE),4, -1.2f, DaggersID.DAGGER_THE_BEGINNING);
        daggerStats(materialToString(ToolMaterials.NETHERITE),4, -1.2f, DaggersID.DAGGER_THE_END);

        daggerStats(materialToString(ToolMaterials.IRON),2, -1.3f, DaggersID.DAGGER_TEMPEST_KNIFE);
        daggerStats(materialToString(ToolMaterials.IRON),3, -1.3f, DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE);
        daggerStats(materialToString(ToolMaterials.DIAMOND),3, -1.3f, DaggersID.DAGGER_CHILL_GALE_KNIFE);

        soulDaggerStats(materialToString(ToolMaterials.IRON),1, -1.1f, SoulDaggersID.DAGGER_SOUL_KNIFE);
        soulDaggerStats(materialToString(ToolMaterials.NETHERITE),4, -0.9f, SoulDaggersID.DAGGER_ETERNAL_KNIFE);
        soulDaggerStats(materialToString(ToolMaterials.NETHERITE),3, -1.5f, SoulDaggersID.SWORD_TRUTHSEEKER);

        hammerStats(materialToString(ToolMaterials.IRON),4, -3.0f,HammersID.HAMMER_GREAT_HAMMER);
        hammerStats(materialToString(ToolMaterials.DIAMOND),5, -3.0f,HammersID.HAMMER_STORMLANDER);
        hammerStats(materialToString(ToolMaterials.DIAMOND),5, -3.0f,HammersID.HAMMER_GRAVITY);
        hammerStats(materialToString(ToolMaterials.IRON),4, -2.9f,HammersID.HAMMER_MACE);
        hammerStats(materialToString(ToolMaterials.IRON),5, -2.9f,HammersID.HAMMER_FLAIL);
        hammerStats(materialToString(ToolMaterials.DIAMOND),4, -2.5f,HammersID.HAMMER_SUNS_GRACE);
        hammerStats(materialToString(ToolMaterials.IRON),5, -3.1f,HammersID.HAMMER_BONECLUB);
        hammerStats(materialToString(ToolMaterials.DIAMOND),5, -3.1f,HammersID.HAMMER_BONE_CUDGEL);

        gauntletStats(materialToString(ToolMaterials.IRON),0, -1.4f, GauntletsID.GAUNTLET_GAUNTLET);
        gauntletStats(materialToString(ToolMaterials.DIAMOND),0, -1.4f, GauntletsID.GAUNTLET_MAULERS);
        gauntletStats(materialToString(ToolMaterials.NETHERITE),0, -1.4f, GauntletsID.GAUNTLET_SOUL_FISTS);

        sickleStats(materialToString(ToolMaterials.IRON),1, -1.9f, SicklesID.SICKLE_SICKLE);
        sickleStats(materialToString(ToolMaterials.IRON),3, -1.9f, SicklesID.SICKLE_NIGHTMARES_BITE);
        sickleStats(materialToString(ToolMaterials.IRON),2, -1.9f, SicklesID.SICKLE_LAST_LAUGH_GOLD);
        sickleStats(materialToString(ToolMaterials.IRON),2, -1.9f, SicklesID.SICKLE_LAST_LAUGH_SILVER);

        scytheStats(materialToString(ToolMaterials.IRON),4, -2.25f, ScythesID.SICKLE_JAILORS_SCYTHE);
        scytheStats(materialToString(ToolMaterials.DIAMOND),3, -2.25f, ScythesID.SICKLE_SOUL_SCYTHE);
        scytheStats(materialToString(ToolMaterials.DIAMOND),4, -2.5f, ScythesID.SICKLE_FROST_SCYTHE);
        scytheStats(materialToString(ToolMaterials.DIAMOND),4, -2.5f, ScythesID.SICKLE_SKULL_SCYTHE);

        pickStats(materialToString(ToolMaterials.DIAMOND),1, -2.8f, PicksID.PICK_DIAMOND_PICKAXE_VAR);
        pickStats(materialToString(ToolMaterials.IRON),1, -2.8f, PicksID.PICK_MOUNTAINEER_PICK);
        pickStats(materialToString(ToolMaterials.IRON),1, -2.8f, PicksID.PICK_HOWLING_PICK);
        pickStats(materialToString(ToolMaterials.DIAMOND),1, -2.8f, PicksID.PICK_HAILING_PINNACLE);

        glaiveStats(materialToString(ToolMaterials.IRON),3, -2.7f, GlaivesID.SPEAR_GLAIVE);
        glaiveStats(materialToString(ToolMaterials.IRON),5, -2.4f, GlaivesID.SPEAR_GRAVE_BANE);
        glaiveStats(materialToString(ToolMaterials.IRON),5, -2.5f, GlaivesID.SPEAR_VENOM_GLAIVE);
        glaiveStats(materialToString(ToolMaterials.IRON),5, -2.4f, GlaivesID.SPEAR_CACKLING_BROOM);

        spearStats(materialToString(ToolMaterials.IRON),3, -2.5f, SpearsID.SPEAR_SPEAR);
        spearStats(materialToString(ToolMaterials.IRON),5, -2.5f, SpearsID.SPEAR_WHISPERING_SPEAR);
        spearStats(materialToString(ToolMaterials.IRON),4, -2.15f, SpearsID.SPEAR_FORTUNE);

        staffStats(materialToString(ToolMaterials.WOOD),0, -0.1f, StavesID.STAFF_BATTLESTAFF);
        staffStats(materialToString(ToolMaterials.IRON),1, -0.1f, StavesID.STAFF_GROWING_STAFF);
        staffStats(materialToString(ToolMaterials.IRON),0, -0.1f, StavesID.STAFF_BATTLESTAFF_OF_TERROR);

        whipStats(materialToString(ToolMaterials.IRON),2, -3.1f, WhipsID.WHIP_WHIP);
        whipStats(materialToString(ToolMaterials.IRON),5, -3.1f, WhipsID.WHIP_VINE_WHIP);

        bowStats(materialToString(ToolMaterials.NETHERITE),14, 18f, BowsID.BOW_ANCIENT_BOW);
        bowStats(materialToString(ToolMaterials.STONE),16, 12f, BowsID.BOW_BONEBOW);
        bowStats(materialToString(ToolMaterials.NETHERITE),12, 17f, BowsID.BOW_LOST_SOULS);
        bowStats(materialToString(ToolMaterials.IRON),20, 15f, BowsID.BOW_ELITE_POWER_BOW);
        bowStats(materialToString(ToolMaterials.NETHERITE),18, 16f, BowsID.BOW_HAUNTED_BOW);
        bowStats(materialToString(ToolMaterials.IRON),15, 10f, BowsID.BOW_HUNTERS_PROMISE);
        bowStats(materialToString(ToolMaterials.IRON),16, 11f, BowsID.BOW_HUNTING_BOW);
        bowStats(materialToString(ToolMaterials.IRON),17, 10f, BowsID.BOW_MASTERS_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),17, 14f, BowsID.BOW_NOCTURNAL_BOW);
        bowStats(materialToString(ToolMaterials.IRON),20, 14f, BowsID.BOW_POWER_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),10, 13f, BowsID.BOW_SABREWING);
        bowStats(materialToString(ToolMaterials.IRON),16, 11f, BowsID.BOW_SNOW_BOW);
        bowStats(materialToString(ToolMaterials.IRON),14, 10f, BowsID.BOW_SOUL_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),17, 13f, BowsID.BOW_GREEN_MENACE);
        bowStats(materialToString(ToolMaterials.DIAMOND),17, 13f, BowsID.BOW_PINK_SCOUNDREL);
        bowStats(materialToString(ToolMaterials.DIAMOND),12, 10f, BowsID.BOW_TRICKBOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),12, 10f, BowsID.BOW_TWIN_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),15, 13f, BowsID.BOW_WINTERS_TOUCH);
        bowStats(materialToString(ToolMaterials.DIAMOND),14, 10f, BowsID.BOW_SHIVERING_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),11, 15f, BowsID.BOW_WIND_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),11, 16f, BowsID.BOW_ECHO_OF_THE_VALLEY);
        bowStats(materialToString(ToolMaterials.DIAMOND),12, 16f, BowsID.BOW_BURST_GALE_BOW);
        bowStats(materialToString(ToolMaterials.IRON),15, 10f, BowsID.BOW_TWISTING_VINE_BOW);
        bowStats(materialToString(ToolMaterials.IRON),15, 10f, BowsID.BOW_WEEPING_VINE_BOW);
        bowStats(materialToString(ToolMaterials.IRON),15, 12f, BowsID.BOW_BUBBLE_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),15, 13f, BowsID.BOW_BUBBLE_BURSTER);
        bowStats(materialToString(ToolMaterials.DIAMOND),15, 15f, BowsID.BOW_VOID_BOW);
        bowStats(materialToString(ToolMaterials.NETHERITE),15, 16f, BowsID.BOW_CALL_OF_THE_VOID);
        bowStats(materialToString(ToolMaterials.DIAMOND),20, 14f, BowsID.BOW_PHANTOM_BOW);
        bowStats(materialToString(ToolMaterials.DIAMOND),15, 12f, BowsID.BOW_WEB_BOW);

        shortBowStats(materialToString(ToolMaterials.IRON), 9, 8f, ShortBowsID.BOW_SHORTBOW);
        shortBowStats(materialToString(ToolMaterials.IRON), 9, 8f, ShortBowsID.BOW_LOVE_SPELL_BOW);
        shortBowStats(materialToString(ToolMaterials.IRON), 9, 9f, ShortBowsID.BOW_MECHANICAL_SHORTBOW);
        shortBowStats(materialToString(ToolMaterials.IRON), 9, 8f, ShortBowsID.BOW_PURPLE_STORM);

        longBowStats(materialToString(ToolMaterials.IRON), 25, 17f, LongBowsID.BOW_LONGBOW);
        longBowStats(materialToString(ToolMaterials.DIAMOND), 30, 19f, LongBowsID.BOW_GUARDIAN_BOW);
        longBowStats(materialToString(ToolMaterials.DIAMOND), 30, 18f, LongBowsID.BOW_RED_SNAKE);

        crossbowStats(materialToString(ToolMaterials.IRON), 28, 10.2f, CrossbowsID.CROSSBOW_THE_SLICER);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.4f, CrossbowsID.CROSSBOW_AZURE_SEEKER);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 7.9f, CrossbowsID.CROSSBOW_FIREBOLT_THROWER);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_HEAVY_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.NETHERITE), 26, 8.0f, CrossbowsID.CROSSBOW_DOOM_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 26, 8.8f, CrossbowsID.CROSSBOW_SLAYER_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 20, 8.2f, CrossbowsID.CROSSBOW_RAPID_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.9f, CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_AUTO_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_SCATTER_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.6f, CrossbowsID.CROSSBOW_HARP_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 28, 14.2f, CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_SOUL_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 9.2f, CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 26, 12.5f, CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 24, 7.0f, CrossbowsID.CROSSBOW_DUAL_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.9f, CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 23, 7.2f, CrossbowsID.CROSSBOW_BABY_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.0f, CrossbowsID.CROSSBOW_BURST_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 28, 11.0f, CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.NETHERITE), 22, 14.0f, CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 8.4f, CrossbowsID.CROSSBOW_COG_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.NETHERITE), 20, 13.0f, CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS);
        crossbowStats(materialToString(ToolMaterials.IRON), 28, 11.0f, CrossbowsID.CROSSBOW_HARPOON_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 24, 14.0f, CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 25, 12.0f, CrossbowsID.CROSSBOW_SHADOW_CROSSBOW);
        crossbowStats(materialToString(ToolMaterials.DIAMOND), 22, 14.5f, CrossbowsID.CROSSBOW_VEILED_CROSSBOW);

        shieldStats(materialToString(ToolMaterials.DIAMOND), ShieldsID.SHIELD_ROYAL_GUARD);
        shieldStats(materialToString(ToolMaterials.DIAMOND), ShieldsID.SHIELD_VANGUARD);
    }

    private static String materialToString(ToolMaterial toolMaterial) {
        if (toolMaterial == ToolMaterials.WOOD)
            return "wood";
        else if (toolMaterial == ToolMaterials.STONE)
            return "stone";
        else if (toolMaterial == ToolMaterials.GOLD)
            return "gold";
        else if (toolMaterial == ToolMaterials.IRON)
            return "iron";
        else if (toolMaterial == ToolMaterials.DIAMOND)
            return "diamond";
        else if (toolMaterial == ToolMaterials.NETHERITE)
            return "netherite";
        else
            return "none";
    }
}
