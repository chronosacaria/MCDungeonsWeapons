package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.IMeleeWeaponID;
import chronosacaria.mcdw.enums.IRangedWeaponID;
import chronosacaria.mcdw.enums.IShieldID;
import chronosacaria.mcdw.enums.ShieldsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> swordStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> axeStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> doubleAxeStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> daggerStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> soulDaggerStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> hammerStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> gauntletStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> sickleStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> scytheStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> pickStats = new HashMap<>();
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> glaiveStats = new HashMap<>();
    public final boolean doubleAttackReachOfGlaives = false;
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> spearStats = new HashMap<>();
    public final boolean doubleAttackReachOfSpears = false;
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> staffStats = new HashMap<>();
    public final boolean doubleAttackReachOfStaves = false;
    public final HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> whipStats = new HashMap<>();
    public final HashMap<IRangedWeaponID, IRangedWeaponID.RangedStats> bowStats = new HashMap<>();
    public final HashMap<IRangedWeaponID, IRangedWeaponID.RangedStats> shortBowStats = new HashMap<>();
    public final HashMap<IRangedWeaponID, IRangedWeaponID.RangedStats> longBowStats = new HashMap<>();
    public final HashMap<IRangedWeaponID, IRangedWeaponID.RangedStats> crossbowStats = new HashMap<>();
    public final HashMap<IShieldID, IShieldID.ShieldStats> shieldStats = new HashMap<>();

    // Stats Convenience Methods
    protected IMeleeWeaponID.MeleeStats meleeWeaponStats(String material, int damage, float attackSpeed, IMeleeWeaponID iMeleeWeaponID) {
        return iMeleeWeaponID.getWeaponItemStats(this).meleeStats(material, damage, attackSpeed);
    }
    protected IMeleeWeaponID.MeleeStats advancedMeleeWeaponStats(IMeleeWeaponID iMeleeWeaponID) {
        return meleeWeaponStats(materialToString(iMeleeWeaponID.getMaterial()), iMeleeWeaponID.getDamage(), iMeleeWeaponID.getAttackSpeed(), iMeleeWeaponID);
    }

    protected IRangedWeaponID.RangedStats rangedWeaponStats(String material, int drawSpeed, float range, IRangedWeaponID iRangedWeaponID) {
        return iRangedWeaponID.getWeaponItemStats(this).rangedStats(material, drawSpeed, range);
    }
    protected IRangedWeaponID.RangedStats advancedRangedWeaponStats(IRangedWeaponID iRangedWeaponID) {
        return rangedWeaponStats(materialToString(iRangedWeaponID.getMaterial()), iRangedWeaponID.getDrawSpeed(), iRangedWeaponID.getRange(), iRangedWeaponID);
    }

    protected IShieldID.ShieldStats shieldStats(String material, IShieldID iShieldID) {
        return iShieldID.getWeaponItemStats(this).shieldStats(material);
    }
    protected IShieldID.ShieldStats advancedShieldStats(IShieldID iShieldID) {
        return shieldStats(materialToString(iShieldID.getMaterial()), iShieldID);
    }

    public McdwNewStatsConfig() {

        Arrays.stream(IMeleeWeaponID.values()).forEach(iMeleeWeaponID ->
                iMeleeWeaponID.getWeaponStats(this).put(iMeleeWeaponID, new IMeleeWeaponID.MeleeStats()));
        Arrays.stream(IRangedWeaponID.values()).forEach(iRangedWeaponID ->
                iRangedWeaponID.getWeaponStats(this).put(iRangedWeaponID, new IRangedWeaponID.RangedStats()));
        Arrays.stream(ShieldsID.values()).forEach(shieldsID -> shieldStats.put(shieldsID, new IShieldID.ShieldStats()));

        // Stats Hash Assign
        Arrays.stream(IMeleeWeaponID.values()).forEach(this::advancedMeleeWeaponStats);
        Arrays.stream(IRangedWeaponID.values()).forEach(this::advancedRangedWeaponStats);
        Arrays.stream(ShieldsID.values()).forEach(this::advancedShieldStats);
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
