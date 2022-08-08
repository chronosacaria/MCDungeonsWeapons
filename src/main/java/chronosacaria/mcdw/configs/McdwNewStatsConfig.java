package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public final HashMap<SwordsID, IMeleeWeaponID.MeleeStats> swordStats = new HashMap<>();
    public final HashMap<AxesID, IMeleeWeaponID.MeleeStats> axeStats = new HashMap<>();
    public final HashMap<DoubleAxesID, IMeleeWeaponID.MeleeStats> doubleAxeStats = new HashMap<>();
    public final HashMap<DaggersID, IMeleeWeaponID.MeleeStats> daggerStats = new HashMap<>();
    public final HashMap<SoulDaggersID, IMeleeWeaponID.MeleeStats> soulDaggerStats = new HashMap<>();
    public final HashMap<HammersID, IMeleeWeaponID.MeleeStats> hammerStats = new HashMap<>();
    public final HashMap<GauntletsID, IMeleeWeaponID.MeleeStats> gauntletStats = new HashMap<>();
    public final HashMap<SicklesID, IMeleeWeaponID.MeleeStats> sickleStats = new HashMap<>();
    public final HashMap<ScythesID, IMeleeWeaponID.MeleeStats> scytheStats = new HashMap<>();
    public final HashMap<PicksID, IMeleeWeaponID.MeleeStats> pickStats = new HashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfGlaives = 1.0D;
    public final HashMap<GlaivesID, IMeleeWeaponID.MeleeStats> glaiveStats = new HashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfSpears = 1.0D;
    public final HashMap<SpearsID, IMeleeWeaponID.MeleeStats> spearStats = new HashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfStaves = 1.0D;
    public final HashMap<StavesID, IMeleeWeaponID.MeleeStats> staffStats = new HashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.5")
    public final double extraAttackReachOfWhips = 1.5D;
    public final HashMap<WhipsID, IMeleeWeaponID.MeleeStats> whipStats = new HashMap<>();
    public final HashMap<BowsID, IRangedWeaponID.RangedStats> bowStats = new HashMap<>();
    public final HashMap<ShortBowsID, IRangedWeaponID.RangedStats> shortBowStats = new HashMap<>();
    public final HashMap<LongBowsID, IRangedWeaponID.RangedStats> longBowStats = new HashMap<>();
    public final HashMap<CrossbowsID, IRangedWeaponID.RangedStats> crossbowStats = new HashMap<>();
    public final HashMap<ShieldsID, IShieldID.ShieldStats> shieldStats = new HashMap<>();

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

        for (SwordsID swordsID : SwordsID.values())
            swordStats.put(swordsID, new IMeleeWeaponID.MeleeStats());
        for (AxesID axesID : AxesID.values())
            axeStats.put(axesID, new IMeleeWeaponID.MeleeStats());
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values())
            doubleAxeStats.put(doubleAxesID, new IMeleeWeaponID.MeleeStats());
        for (DaggersID daggersID : DaggersID.values())
            daggerStats.put(daggersID, new IMeleeWeaponID.MeleeStats());
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values())
            soulDaggerStats.put(soulDaggersID, new IMeleeWeaponID.MeleeStats());
        for (HammersID hammersID : HammersID.values())
            hammerStats.put(hammersID, new IMeleeWeaponID.MeleeStats());
        for (GauntletsID gauntletsID : GauntletsID.values())
            gauntletStats.put(gauntletsID, new IMeleeWeaponID.MeleeStats());
        for (SicklesID sicklesID : SicklesID.values())
            sickleStats.put(sicklesID, new IMeleeWeaponID.MeleeStats());
        for (ScythesID scythesID : ScythesID.values())
            scytheStats.put(scythesID, new IMeleeWeaponID.MeleeStats());
        for (PicksID picksID : PicksID.values())
            pickStats.put(picksID, new IMeleeWeaponID.MeleeStats());
        for (GlaivesID glaivesID : GlaivesID.values())
            glaiveStats.put(glaivesID, new IMeleeWeaponID.MeleeStats());
        for (SpearsID spearsID : SpearsID.values())
            spearStats.put(spearsID, new IMeleeWeaponID.MeleeStats());
        for (StavesID stavesID : StavesID.values())
            staffStats.put(stavesID, new IMeleeWeaponID.MeleeStats());
        for (WhipsID whipsID : WhipsID.values())
            whipStats.put(whipsID, new IMeleeWeaponID.MeleeStats());
        for (BowsID bowsID : BowsID.values())
            bowStats.put(bowsID, new IRangedWeaponID.RangedStats());
        for (ShortBowsID shortBowsID : ShortBowsID.values())
            shortBowStats.put(shortBowsID, new IRangedWeaponID.RangedStats());
        for (LongBowsID longBowsID : LongBowsID.values())
            longBowStats.put(longBowsID, new IRangedWeaponID.RangedStats());
        for (CrossbowsID crossbowsID : CrossbowsID.values())
            crossbowStats.put(crossbowsID, new IRangedWeaponID.RangedStats());
        for (ShieldsID shieldsID : ShieldsID.values())
            shieldStats.put(shieldsID, new IShieldID.ShieldStats());

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
