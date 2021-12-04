package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.enums.AxesID;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.DoubleAxesID;
import chronosacaria.mcdw.enums.SwordsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public EnumMap<SwordsID, MeleeWeaponStats> swordStats = new EnumMap<>(SwordsID.class);
    public EnumMap<AxesID, MeleeWeaponStats> axeStats = new EnumMap<>(AxesID.class);
    public EnumMap<DoubleAxesID, MeleeWeaponStats> doubleAxeStats = new EnumMap<>(DoubleAxesID.class);
    public EnumMap<DaggersID, MeleeWeaponStats> daggerStats = new EnumMap<>(DaggersID.class);

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
//
        daggerStats(2, -1.3f, DaggersID.DAGGER_TEMPEST_KNIFE);
        daggerStats(3, -1.3f, DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE);
        daggerStats(4, -1.3f, DaggersID.DAGGER_CHILL_GALE_KNIFE);
//
        //soulDaggerStats(1, -1.1f, SoulDaggersID.DAGGER_SOUL_KNIFE);
        //soulDaggerStats(4, -0.9f, SoulDaggersID.DAGGER_ETERNAL_KNIFE);
        //soulDaggerStats(3, -1.5f, SoulDaggersID.SWORD_TRUTHSEEKER);
//
        //meleeWeaponStats(4, -3.0f, MeleeWeaponID.HAMMER_GREAT);
        //meleeWeaponStats(5, -3.0f, MeleeWeaponID.HAMMER_STORMLANDER);
        //meleeWeaponStats(5, -3.0f, MeleeWeaponID.HAMMER_GRAVITY);
        //meleeWeaponStats(4, -2.9f, MeleeWeaponID.HAMMER_MACE);
        //meleeWeaponStats(5, -2.9f, MeleeWeaponID.HAMMER_FLAIL);
        //meleeWeaponStats(4, -2.5f, MeleeWeaponID.HAMMER_SUNS_GRACE);
        //meleeWeaponStats(5, -3.1f, MeleeWeaponID.HAMMER_BONECLUB);
        //meleeWeaponStats(5, -3.1f, MeleeWeaponID.HAMMER_BONE_CUDGEL);
//
        //meleeWeaponStats(0, -1.4f, MeleeWeaponID.GAUNTLET_GAUNTLET);
        //meleeWeaponStats(0, -1.4f, MeleeWeaponID.GAUNTLET_MAULERS);
        //meleeWeaponStats(0, -1.4f, MeleeWeaponID.GAUNTLET_SOUL_FISTS);
//
        //meleeWeaponStats(1, -1.9f, MeleeWeaponID.SICKLE_SICKLE);
        //meleeWeaponStats(3, -1.9f, MeleeWeaponID.SICKLE_NIGHTMARES_BITE);
        //meleeWeaponStats(4, -1.9f, MeleeWeaponID.SICKLE_LAST_LAUGH_GOLD);
        //meleeWeaponStats(4, -1.9f, MeleeWeaponID.SICKLE_LAST_LAUGH_SILVER);
//
        //meleeWeaponStats(4, -2.25f, MeleeWeaponID.SICKLE_JAILORS_SCYTHE);
        //meleeWeaponStats(3, -2.25f, MeleeWeaponID.SICKLE_SOUL_SCYTHE);
        //meleeWeaponStats(4, -2.5f, MeleeWeaponID.SICKLE_FROST_SCYTHE);
        //meleeWeaponStats(4, -2.5f, MeleeWeaponID.SICKLE_SKULL_SCYTHE);
//
        //meleeWeaponStats(1, -2.8f, MeleeWeaponID.PICK_DIAMOND_PICKAXE_VAR);
        //meleeWeaponStats(1, -2.8f, MeleeWeaponID.PICK_MOUNTAINEER_PICK);
        //meleeWeaponStats(1, -2.8f, MeleeWeaponID.PICK_HOWLING_PICK);
        //meleeWeaponStats(1, -2.8f, MeleeWeaponID.PICK_HAILING_PINNACLE);
//
        //meleeWeaponStats(3, -2.7f, MeleeWeaponID.SPEAR_GLAIVE);
        //meleeWeaponStats(5, -2.4f, MeleeWeaponID.SPEAR_GRAVE_BANE);
        //meleeWeaponStats(5, -2.5f, MeleeWeaponID.SPEAR_VENOM_GLAIVE);
        //meleeWeaponStats(5, -2.4f, MeleeWeaponID.SPEAR_CACKLING_BROOM);
//
        //meleeWeaponStats(3, -2.5f, MeleeWeaponID.SPEAR_SPEAR);
        //meleeWeaponStats(5, -2.5f, MeleeWeaponID.SPEAR_WHISPERING_SPEAR);
        //meleeWeaponStats(4, -2.15f, MeleeWeaponID.SPEAR_FORTUNE);
//
        //meleeWeaponStats(0, -0.1f, MeleeWeaponID.STAFF_BATTLESTAFF);
        //meleeWeaponStats(1, -0.1f, MeleeWeaponID.STAFF_GROWING_STAFF);
        //meleeWeaponStats(0, -0.1f, MeleeWeaponID.STAFF_BATTLESTAFF_OF_TERROR);
//
        //meleeWeaponStats(2, -3.1f, MeleeWeaponID.WHIP_WHIP);
        //meleeWeaponStats(5, -3.1f, MeleeWeaponID.WHIP_VINE_WHIP);
    }


}
