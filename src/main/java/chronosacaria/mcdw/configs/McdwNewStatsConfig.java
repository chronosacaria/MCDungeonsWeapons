package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.configs.stats.RangedWeaponStats;
import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public EnumMap<SwordsID, MeleeWeaponStats> swordStats = new EnumMap<>(SwordsID.class);
    public EnumMap<AxesID, MeleeWeaponStats> axeStats = new EnumMap<>(AxesID.class);
    public EnumMap<DoubleAxesID, MeleeWeaponStats> doubleAxeStats = new EnumMap<>(DoubleAxesID.class);
    public EnumMap<DaggersID, MeleeWeaponStats> daggerStats = new EnumMap<>(DaggersID.class);
    public EnumMap<SoulDaggersID, MeleeWeaponStats> soulDaggerStats = new EnumMap<>(SoulDaggersID.class);
    public EnumMap<HammersID, MeleeWeaponStats> hammerStats = new EnumMap<>(HammersID.class);
    public EnumMap<GauntletsID, MeleeWeaponStats> gauntletStats = new EnumMap<>(GauntletsID.class);
    public EnumMap<SicklesID, MeleeWeaponStats> sickleStats = new EnumMap<>(SicklesID.class);
    public EnumMap<ScythesID, MeleeWeaponStats> scytheStats = new EnumMap<>(ScythesID.class);
    public EnumMap<PicksID, MeleeWeaponStats> pickStats = new EnumMap<>(PicksID.class);
    public EnumMap<GlaivesID, MeleeWeaponStats> glaiveStats = new EnumMap<>(GlaivesID.class);
    public EnumMap<SpearsID, MeleeWeaponStats> spearStats = new EnumMap<>(SpearsID.class);
    public EnumMap<StavesID, MeleeWeaponStats> staffStats = new EnumMap<>(StavesID.class);
    public EnumMap<WhipsID, MeleeWeaponStats> whipStats = new EnumMap<>(WhipsID.class);
    public EnumMap<BowsID, RangedWeaponStats> bowStats = new EnumMap<>(BowsID.class);

    // convenience methods:
    protected MeleeWeaponStats swordStats(int damage, float attackSpeed, SwordsID swordsID){
        return swordStats.get(swordsID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats axeStats(int damage, float attackSpeed, AxesID axesID){
        return axeStats.get(axesID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats doubleAxeStats(int damage, float attackSpeed, DoubleAxesID doubleAxesID){
        return doubleAxeStats.get(doubleAxesID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats daggerStats(int damage, float attackSpeed, DaggersID daggersID){
        return daggerStats.get(daggersID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats soulDaggerStats(int damage, float attackSpeed, SoulDaggersID soulDaggersID){
        return soulDaggerStats.get(soulDaggersID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats hammerStats(int damage, float attackSpeed, HammersID hammersID){
        return hammerStats.get(hammersID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats gauntletStats(int damage, float attackSpeed, GauntletsID gauntletsID){
        return gauntletStats.get(gauntletsID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats sickleStats(int damage, float attackSpeed, SicklesID sicklesID){
        return sickleStats.get(sicklesID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats scytheStats(int damage, float attackSpeed, ScythesID scythesID){
        return scytheStats.get(scythesID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats pickStats(int damage, float attackSpeed, PicksID picksID){
        return pickStats.get(picksID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats glaiveStats(int damage, float attackSpeed, GlaivesID glaivesID){
        return glaiveStats.get(glaivesID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats spearStats(int damage, float attackSpeed, SpearsID spearsID){
        return spearStats.get(spearsID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats staffStats(int damage, float attackSpeed, StavesID stavesID){
        return staffStats.get(stavesID).meleeWeaponStats(damage, attackSpeed);
    }
    protected MeleeWeaponStats whipStats(int damage, float attackSpeed, WhipsID whipsID){
        return whipStats.get(whipsID).meleeWeaponStats(damage, attackSpeed);
    }
    protected RangedWeaponStats bowStats(float drawSpeed, float range, BowsID bowsID){
        return bowStats.get(bowsID).rangedWeaponStats(drawSpeed, range);
    }

    public McdwNewStatsConfig() {
        for (SwordsID swordsID : SwordsID.values()) {
            swordStats.put(swordsID, new MeleeWeaponStats());
        }

        for (AxesID axesID : AxesID.values()) {
            axeStats.put(axesID, new MeleeWeaponStats());
        }

        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            doubleAxeStats.put(doubleAxesID, new MeleeWeaponStats());
        }

        for (DaggersID daggersID : DaggersID.values()) {
            daggerStats.put(daggersID, new MeleeWeaponStats());
        }

        for (SoulDaggersID soulDaggersID : SoulDaggersID.values()) {
            soulDaggerStats.put(soulDaggersID, new MeleeWeaponStats());
        }

        for (HammersID hammersID : HammersID.values()) {
            hammerStats.put(hammersID, new MeleeWeaponStats());
        }

        for (GauntletsID gauntletsID : GauntletsID.values()) {
            gauntletStats.put(gauntletsID, new MeleeWeaponStats());
        }

        for (SicklesID sicklesID : SicklesID.values()) {
            sickleStats.put(sicklesID, new MeleeWeaponStats());
        }

        for (ScythesID scythesID : ScythesID.values()) {
            scytheStats.put(scythesID, new MeleeWeaponStats());
        }

        for (PicksID picksID : PicksID.values()) {
            pickStats.put(picksID, new MeleeWeaponStats());
        }

        for (GlaivesID glaivesID : GlaivesID.values()) {
            glaiveStats.put(glaivesID, new MeleeWeaponStats());
        }

        for (SpearsID spearsID : SpearsID.values()) {
            spearStats.put(spearsID, new MeleeWeaponStats());
        }

        for (StavesID stavesID : StavesID.values()) {
            staffStats.put(stavesID, new MeleeWeaponStats());
        }

        for (WhipsID whipsID : WhipsID.values()) {
            whipStats.put(whipsID, new MeleeWeaponStats());
        }

        for (BowsID bowsID : BowsID.values()) {
            bowStats.put(bowsID, new RangedWeaponStats());
        }

        swordStats(5, -3.0f, SwordsID.SWORD_CLAYMORE);
        swordStats(4, -3.0f, SwordsID.SWORD_BROADSWORD);
        swordStats(5, -3.0f, SwordsID.SWORD_FROST_SLAYER);
        swordStats(4, -3.0f, SwordsID.SWORD_HEARTSTEALER);
        swordStats(6, -3.0f, SwordsID.SWORD_GREAT_AXEBLADE);
        swordStats(8, -3.0f, SwordsID.SWORD_OBSIDIAN_CLAYMORE);
        swordStats(10, -3.0f,SwordsID.SWORD_THE_STARLESS_NIGHT);

        swordStats(0, -0.9f, SwordsID.SWORD_RAPIER);
        swordStats(0, -0.9f, SwordsID.SWORD_BEESTINGER);
        swordStats(0, -0.9f, SwordsID.SWORD_FREEZING_FOIL);

        swordStats(3, -2.7f, SwordsID.SWORD_CUTLASS);
        swordStats(1, -1.7f, SwordsID.SWORD_NAMELESS_BLADE);
        swordStats(1, -1.0f, SwordsID.SWORD_DANCERS_SWORD);

        //TODO Revisit Katana Stats
        swordStats(1, -1.5f, SwordsID.SWORD_KATANA);
        swordStats(0, -1.1f, SwordsID.SWORD_MASTERS_KATANA);
        swordStats(0, -1.15f,SwordsID.SWORD_DARK_KATANA);

        swordStats(3, -2.4f, SwordsID.SWORD_IRON_SWORD_VAR);
        swordStats(3, -2.4f, SwordsID.SWORD_DIAMOND_SWORD_VAR);
        swordStats(5, -2.0f, SwordsID.SWORD_HAWKBRAND);
        swordStats(5, -2.0f, SwordsID.SWORD_SINISTER);

        swordStats(3, -2.4f, SwordsID.SWORD_BROKEN_SAWBLADE);
        swordStats(3, -2.4f, SwordsID.SWORD_MECHANIZED_SAWBLADE);

        swordStats(3, -2.4f, SwordsID.SWORD_CORAL_BLADE);
        swordStats(3, -2.4f, SwordsID.SWORD_SPONGE_STRIKER);

        axeStats(6, -3.1f, AxesID.AXE);
        axeStats(5, -2.9f, AxesID.AXE_FIREBRAND);
        axeStats(6, -3.1f, AxesID.AXE_HIGHLAND);

        axeStats(6, -3.1f, AxesID.AXE_ANCHOR);
        axeStats(5, -3.1f, AxesID.AXE_ENCRUSTED_ANCHOR);

        doubleAxeStats(6, -3.1f, DoubleAxesID.AXE_DOUBLE);
        doubleAxeStats(7, -3.1f, DoubleAxesID.AXE_CURSED);
        doubleAxeStats(6, -2.9f, DoubleAxesID.AXE_WHIRLWIND);

        daggerStats(1, -1.3f, DaggersID.DAGGER_DAGGER);
        daggerStats(1, -1.0f, DaggersID.DAGGER_FANGS_OF_FROST);
        daggerStats(1, -1.0f, DaggersID.DAGGER_MOON);
        daggerStats(1, -1.3f, DaggersID.DAGGER_SHEAR_DAGGER);
        daggerStats(3, -1.2f, DaggersID.DAGGER_BACKSTABBER);
        daggerStats(4, -1.0f, DaggersID.DAGGER_SWIFT_STRIKER);
        daggerStats(4, -1.2f, DaggersID.DAGGER_VOID_TOUCHED_BLADE);
        daggerStats(4, -1.2f, DaggersID.DAGGER_THE_BEGINNING);
        daggerStats(4, -1.2f, DaggersID.DAGGER_THE_END);

        daggerStats(2, -1.3f, DaggersID.DAGGER_TEMPEST_KNIFE);
        daggerStats(3, -1.3f, DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE);
        daggerStats(4, -1.3f, DaggersID.DAGGER_CHILL_GALE_KNIFE);

        soulDaggerStats(1, -1.1f, SoulDaggersID.DAGGER_SOUL_KNIFE);
        soulDaggerStats(4, -0.9f, SoulDaggersID.DAGGER_ETERNAL_KNIFE);
        soulDaggerStats(3, -1.5f, SoulDaggersID.SWORD_TRUTHSEEKER);

        hammerStats(4, -3.0f,HammersID.HAMMER_GREAT);
        hammerStats(5, -3.0f,HammersID.HAMMER_STORMLANDER);
        hammerStats(5, -3.0f,HammersID.HAMMER_GRAVITY);
        hammerStats(4, -2.9f,HammersID.HAMMER_MACE);
        hammerStats(5, -2.9f,HammersID.HAMMER_FLAIL);
        hammerStats(4, -2.5f,HammersID.HAMMER_SUNS_GRACE);
        hammerStats(5, -3.1f,HammersID.HAMMER_BONECLUB);
        hammerStats(5, -3.1f,HammersID.HAMMER_BONE_CUDGEL);

        gauntletStats(0, -1.4f, GauntletsID.GAUNTLET_GAUNTLET);
        gauntletStats(0, -1.4f, GauntletsID.GAUNTLET_MAULERS);
        gauntletStats(0, -1.4f, GauntletsID.GAUNTLET_SOUL_FISTS);

        sickleStats(1, -1.9f, SicklesID.SICKLE_SICKLE);
        sickleStats(3, -1.9f, SicklesID.SICKLE_NIGHTMARES_BITE);
        sickleStats(2, -1.9f, SicklesID.SICKLE_LAST_LAUGH_GOLD);
        sickleStats(2, -1.9f, SicklesID.SICKLE_LAST_LAUGH_SILVER);

        scytheStats(4, -2.25f, ScythesID.SICKLE_JAILORS_SCYTHE);
        scytheStats(3, -2.25f, ScythesID.SICKLE_SOUL_SCYTHE);
        scytheStats(4, -2.5f, ScythesID.SICKLE_FROST_SCYTHE);
        scytheStats(4, -2.5f, ScythesID.SICKLE_SKULL_SCYTHE);

        pickStats(1, -2.8f, PicksID.PICK_DIAMOND_PICKAXE_VAR);
        pickStats(1, -2.8f, PicksID.PICK_MOUNTAINEER_PICK);
        pickStats(1, -2.8f, PicksID.PICK_HOWLING_PICK);
        pickStats(1, -2.8f, PicksID.PICK_HAILING_PINNACLE);

        glaiveStats(3, -2.7f, GlaivesID.SPEAR_GLAIVE);
        glaiveStats(5, -2.4f, GlaivesID.SPEAR_GRAVE_BANE);
        glaiveStats(5, -2.5f, GlaivesID.SPEAR_VENOM_GLAIVE);
        glaiveStats(5, -2.4f, GlaivesID.SPEAR_CACKLING_BROOM);

        spearStats(3, -2.5f, SpearsID.SPEAR_SPEAR);
        spearStats(5, -2.5f, SpearsID.SPEAR_WHISPERING_SPEAR);
        spearStats(4, -2.15f, SpearsID.SPEAR_FORTUNE);

        staffStats(0, -0.1f, StavesID.STAFF_BATTLESTAFF);
        staffStats(1, -0.1f, StavesID.STAFF_GROWING_STAFF);
        staffStats(0, -0.1f, StavesID.STAFF_BATTLESTAFF_OF_TERROR);

        whipStats(2, -3.1f, WhipsID.WHIP_WHIP);
        whipStats(5, -3.1f, WhipsID.WHIP_VINE_WHIP);

        bowStats(12.0f, 7.6f, BowsID.BOW_ANCIENT_BOW);
        bowStats(16.0f, 3.4f, BowsID.BOW_BONEBOW);
        bowStats(8.0f, 7.4f, BowsID.BOW_LOST_SOULS);
        bowStats(20.0f, 6.4f, BowsID.BOW_ELITE_POWER_BOW);
        bowStats(28.0f, 7.0f, BowsID.BOW_HAUNTED_BOW);
        bowStats(13.0f, 5.0f, BowsID.BOW_HUNTERS_PROMISE);
        bowStats(14.0f, 4.6f, BowsID.BOW_HUNTING_BOW);
        bowStats(15.0f, 4.4f, BowsID.BOW_MASTERS_BOW);
        bowStats(15.0f, 6.0f, BowsID.BOW_NOCTURNAL_BOW);
        bowStats(20.0f, 6.4f, BowsID.BOW_POWER_BOW);
        bowStats(8.0f, 9.0f, BowsID.BOW_SABREWING);
        bowStats(14.0f, 4.8f, BowsID.BOW_SNOW_BOW);
        bowStats(12.0f, 4.0f, BowsID.BOW_SOUL_BOW);
        bowStats(15.0f, 5.6f, BowsID.BOW_GREEN_MENACE);
        bowStats(15.0f, 5.6f, BowsID.BOW_PINK_SCOUNDREL);
        bowStats(10.0f, 4.2f, BowsID.BOW_TRICKBOW);
        bowStats(10.0f, 4.2f, BowsID.BOW_TWIN_BOW);
        bowStats(13.0f, 5.2f, BowsID.BOW_WINTERS_TOUCH);
        bowStats(12.0f, 4.0f, BowsID.BOW_SHIVERING_BOW);
        bowStats(9.0f, 6.8f, BowsID.BOW_WIND_BOW);
        bowStats(9.0f, 7.4f, BowsID.BOW_ECHO_OF_THE_VALLEY);
        bowStats(10.0f, 7.2f, BowsID.BOW_BURST_GALE_BOW);
        bowStats(13.0f, 4.0f, BowsID.BOW_TWISTING_VINE_BOW);
        bowStats(13.0f, 4.0f, BowsID.BOW_WEEPING_VINE_BOW);
        bowStats(13.0f, 4.0f, BowsID.BOW_BUBBLE_BOW);
        bowStats(13.0f, 4.2f, BowsID.BOW_BUBBLE_BURSTER);
        bowStats(13.0f, 4.1f, BowsID.BOW_VOID_BOW);
        bowStats(13.0f, 4.3f, BowsID.BOW_CALL_OF_THE_VOID);
        bowStats(20.0f, 6.4f, BowsID.BOW_PHANTOM_BOW);
        bowStats(13.0f, 5.2f, BowsID.BOW_WEB_BOW);
    }
}
