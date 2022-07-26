package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.configs.stats.RangedWeaponStats;
import chronosacaria.mcdw.configs.stats.ShieldStats;
import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> swordStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> axeStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> doubleAxeStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> daggerStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> soulDaggerStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> hammerStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> gauntletStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> sickleStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> scytheStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> pickStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> glaiveStats = new HashMap<>();
    public final boolean doubleAttackReachOfGlaives = false;
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> spearStats = new HashMap<>();
    public final boolean doubleAttackReachOfSpears = false;
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> staffStats = new HashMap<>();
    public final boolean doubleAttackReachOfStaves = false;
    public final HashMap<IMeleeWeaponID, MeleeWeaponStats> whipStats = new HashMap<>();
    public final HashMap<BowsID, RangedWeaponStats> bowStats = new HashMap<>();
    public final HashMap<ShortBowsID, RangedWeaponStats> shortBowStats = new HashMap<>();
    public final HashMap<LongBowsID, RangedWeaponStats> longBowStats = new HashMap<>();
    public final HashMap<CrossbowsID, RangedWeaponStats> crossbowStats = new HashMap<>();
    public final HashMap<ShieldsID, ShieldStats> shieldStats = new HashMap<>();

    // convenience methods:
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

    protected MeleeWeaponStats meleeWeaponStats(String material, int damage, float attackSpeed, IMeleeWeaponID iMeleeWeaponID) {
        return iMeleeWeaponID.getWeaponItemStats(this).meleeWeaponStats(material, damage, attackSpeed);
    }
    protected MeleeWeaponStats advancedMeleeWeaponStats(IMeleeWeaponID iMeleeWeaponID) {
        return meleeWeaponStats(iMeleeWeaponID.getMaterial(), iMeleeWeaponID.getDamage(), iMeleeWeaponID.getAttackSpeed(), iMeleeWeaponID);
    }

    public McdwNewStatsConfig() {
        Arrays.stream(BowsID.values()).forEach(bowsID -> bowStats.put(bowsID, new RangedWeaponStats()));
        Arrays.stream(ShortBowsID.values()).forEach(shortBowsID -> shortBowStats.put(shortBowsID, new RangedWeaponStats()));
        Arrays.stream(LongBowsID.values()).forEach(longBowsID -> longBowStats.put(longBowsID, new RangedWeaponStats()));
        Arrays.stream(CrossbowsID.values()).forEach(crossbowsID -> crossbowStats.put(crossbowsID, new RangedWeaponStats()));
        Arrays.stream(ShieldsID.values()).forEach(shieldsID -> shieldStats.put(shieldsID, new ShieldStats()));

        Arrays.stream(IMeleeWeaponID.values()).forEach(iMeleeWeaponID ->
                iMeleeWeaponID.getWeaponStats(this).put(iMeleeWeaponID, new MeleeWeaponStats()));

        // Stats Hash Assign
        Arrays.stream(IMeleeWeaponID.values()).forEach(this::advancedMeleeWeaponStats);

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

    public static String materialToString(ToolMaterial toolMaterial) {
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
