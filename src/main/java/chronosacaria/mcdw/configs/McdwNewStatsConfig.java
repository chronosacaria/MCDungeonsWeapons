package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.Arrays;
import java.util.LinkedHashMap;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    @Comment("Can use the following tags as repair ingredient -- minecraft:planks, minecraft:stone_crafting_materials. Other tags do not work.")
    public final LinkedHashMap<SwordsID, IMeleeWeaponID.MeleeStats> swordStats = new LinkedHashMap<>();
    public final LinkedHashMap<AxesID, IMeleeWeaponID.MeleeStats> axeStats = new LinkedHashMap<>();
    public final LinkedHashMap<DoubleAxesID, IMeleeWeaponID.MeleeStats> doubleAxeStats = new LinkedHashMap<>();
    public final LinkedHashMap<DaggersID, IMeleeWeaponID.MeleeStats> daggerStats = new LinkedHashMap<>();
    public final LinkedHashMap<SoulDaggersID, IMeleeWeaponID.MeleeStats> soulDaggerStats = new LinkedHashMap<>();
    public final LinkedHashMap<HammersID, IMeleeWeaponID.MeleeStats> hammerStats = new LinkedHashMap<>();
    public final LinkedHashMap<GauntletsID, IMeleeWeaponID.MeleeStats> gauntletStats = new LinkedHashMap<>();
    public final LinkedHashMap<SicklesID, IMeleeWeaponID.MeleeStats> sickleStats = new LinkedHashMap<>();
    public final LinkedHashMap<ScythesID, IMeleeWeaponID.MeleeStats> scytheStats = new LinkedHashMap<>();
    public final LinkedHashMap<PicksID, IMeleeWeaponID.MeleeStats> pickStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfGlaives = 1.0D;
    public final LinkedHashMap<GlaivesID, IMeleeWeaponID.MeleeStats> glaiveStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfSpears = 1.0D;
    public final LinkedHashMap<SpearsID, IMeleeWeaponID.MeleeStats> spearStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.0")
    public final double extraAttackReachOfStaves = 1.0D;
    public final LinkedHashMap<StavesID, IMeleeWeaponID.MeleeStats> staffStats = new LinkedHashMap<>();
    @Comment("This value is ignored if Better Combat is installed! Default: 1.5")
    public final double extraAttackReachOfWhips = 1.5D;
    public final LinkedHashMap<WhipsID, IMeleeWeaponID.MeleeStats> whipStats = new LinkedHashMap<>();
    public final LinkedHashMap<BowsID, IRangedWeaponID.RangedStats> bowStats = new LinkedHashMap<>();
    public final LinkedHashMap<ShortbowsID, IRangedWeaponID.RangedStats> shortbowStats = new LinkedHashMap<>();
    public final LinkedHashMap<LongbowsID, IRangedWeaponID.RangedStats> longbowStats = new LinkedHashMap<>();
    public final LinkedHashMap<CrossbowsID, IRangedWeaponID.RangedStats> crossbowStats = new LinkedHashMap<>();
    public final LinkedHashMap<ShieldsID, IShieldID.ShieldStats> shieldStats = new LinkedHashMap<>();

    // Stats Convenience Methods
    protected IMeleeWeaponID.MeleeStats meleeWeaponStats(String material, int damage, float attackSpeed, String[] repairIngredient, IMeleeWeaponID iMeleeWeaponID) {
        return iMeleeWeaponID.getWeaponItemStats(this).meleeStats(material, damage, attackSpeed, repairIngredient);
    }
    protected IMeleeWeaponID.MeleeStats advancedMeleeWeaponStats(IMeleeWeaponID iMeleeWeaponID) {
        return meleeWeaponStats(materialToString(iMeleeWeaponID.getMaterial()), iMeleeWeaponID.getDamage(), iMeleeWeaponID.getAttackSpeed(), iMeleeWeaponID.getRepairIngredient(), iMeleeWeaponID);
    }

    protected IRangedWeaponID.RangedStats rangedWeaponStats(String material, double projectileDamage, int drawSpeed, float range, String[] repairIngredient, IRangedWeaponID iRangedWeaponID) {
        return iRangedWeaponID.getWeaponItemStats(this).rangedStats(material, projectileDamage, drawSpeed, range, repairIngredient);
    }
    protected IRangedWeaponID.RangedStats advancedRangedWeaponStats(IRangedWeaponID iRangedWeaponID) {
        return rangedWeaponStats(materialToString(iRangedWeaponID.getMaterial()), iRangedWeaponID.getProjectileDamage(), iRangedWeaponID.getDrawSpeed(), iRangedWeaponID.getRange(), iRangedWeaponID.getRepairIngredient(), iRangedWeaponID);
    }

    protected IShieldID.ShieldStats shieldStats(String material, String[] repairIngredient, IShieldID iShieldID) {
        return iShieldID.getWeaponItemStats(this).shieldStats(material, repairIngredient);
    }
    protected IShieldID.ShieldStats advancedShieldStats(IShieldID iShieldID) {
        return shieldStats(materialToString(iShieldID.getMaterial()), iShieldID.getRepairIngredient(), iShieldID);
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
        for (ShortbowsID shortBowsID : ShortbowsID.values())
            shortbowStats.put(shortBowsID, new IRangedWeaponID.RangedStats());
        for (LongbowsID longBowsID : LongbowsID.values())
            longbowStats.put(longBowsID, new IRangedWeaponID.RangedStats());
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
