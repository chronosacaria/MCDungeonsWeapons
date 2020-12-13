package chronosacaria.mcdw.configs;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name="mcdw_stats")
public class McdwStatsConfig implements ConfigData {

    static {
        AutoConfig.register(McdwStatsConfig.class, JanksonConfigSerializer::new);
    }
    public static final McdwStatsConfig config = AutoConfig.getConfigHolder(McdwStatsConfig.class).getConfig();


    @Comment("Axes Damage")
    public float AxeDamage = 6;
    public float FirebrandDamage = 5;
    public float HighlandAxeDamage = 6;

    @Comment("Claymore Damage")
    public int ClaymoreDamage = 5;
    public int BroadswordDamage = 4;
    public int HeartstealerDamage = 4;
    public int GreatAxebladeDamage = 6;

    @Comment("Curves Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Daggers Damage")
    public int DaggerDamage = 1;
    public int FangsOfFrostDamage = 1;
    public int MoonDaggerDamage = 1;
    public int SheerDaggerDamage = 1;

    @Comment("Double Axes Damage")
    public float DoubleAxeDamage = 6;
    public float WhirlwindDamage = 6;
    public float CursedAxeDamage = 7;

    @Comment("Gauntlets Damage")
    public int GauntletsDamage = 0;
    public int MaulersDamage = 0;
    public int SoulFistsDamage = 0;

    @Comment("Glaives Damage")
    public int GlaiveDamage = 3;
    public int GraveBaneDamage = 5;
    public int VenomGlaiveDamage = 5;

    @Comment("Hammers Damage")
    public int GreatHammerDamage = 4;
    public int StormlanderDamage = 5;
    public int HammerOfGravityDamage = 5;
    public int MaceDamage = 4;
    public int FlailDamage = 5;
    public int SunsGraceDamage = 4;

    @Comment("Katanas Damage")
    public int KatanaDamage = 1;
    public int MastersKatanaDamage = 0;
    public int DarkKatanaDamage = 0;

    @Comment("Picks Damage")
    public int DiamondPickDamage = 1;

    @Comment("Rapiers Damage")
    public int RapierDamage = 0;
    public int BeestingerDamage = 0;
    public int FreezingFoilDamage = 0;

    @Comment("Scythes Damage")
    public int JailorsScytheDamage = 4;
    public int SoulScytheDamage = 3;
    public int FrostScytheDamage = 4;

    @Comment("Sickles Damage")
    public int SickleDamage = 1;
    public int NightmaresBiteDamage = 3;
    public int LastLaughDamage = 4;

    @Comment("Soul Daggers Damage")
    public int SoulKnifeDamage = 1;
    public int EternalKnifeDamage = 0;
    public int TruthseekerDamage = 3;

    @Comment("Spears Damage")
    public int SpearDamage = 3;
    public int WhisperingSpearDamage = 5;
    public int FortuneSpearDamage = 4;

    @Comment("Staves Damage")
    public int BattlestaffDamage = 0;
    public int GrowingStaffDamage = 1;
    public int BattlestaffOfTerrorDamage = 0;

    @Comment("Swords Damage")
    public int IronSwordVarDamage = 3;
    public int DiamondSwordVarDamage = 3;
    public int HawkbrandDamage = 5;

    @Comment("Tempest Knives Damage")
    public int TempestKnifeDamage = 2;
    public int ChillGaleKnifeDamage = 4;
    public int ResoluteTempestKnifeDamage = 4;

    @Comment("Whips Damage")
    public int WhipDamage = 2;
    public int VineWhipDamage = 5;

    public float getAxeDamage() {
        return AxeDamage;
    }
    public float getFirebrandDamage() {
        return FirebrandDamage;
    }
    public float getHighlandAxeDamage() {
        return HighlandAxeDamage;
    }
    public int getClaymoreDamage() {
        return ClaymoreDamage;
    }
    public int getBroadswordDamage() {
        return BroadswordDamage;
    }
    public int getHeartstealerDamage() {
        return HeartstealerDamage;
    }
    public int getGreatAxebladeDamage() {
        return GreatAxebladeDamage;
    }
    public int getCutlassDamage() {
        return CutlassDamage;
    }
    public int getNamelessBladeDamage() {
        return NamelessBladeDamage;
    }
    public int getDancersSwordDamage() {
        return DancersSwordDamage;
    }
    public int getDaggerDamage() {
        return DaggerDamage;
    }
    public int getFangsOfFrostDamage() {
        return FangsOfFrostDamage;
    }
    public int getMoonDaggerDamage() {
        return MoonDaggerDamage;
    }
    public int getSheerDaggerDamage() {
        return SheerDaggerDamage;
    }
    public float getDoubleAxeDamage() {
        return DoubleAxeDamage;
    }
    public float getWhirlwindDamage() {
        return WhirlwindDamage;
    }
    public float getCursedAxeDamage() {
        return CursedAxeDamage;
    }
    public int getGauntletsDamage() {
        return GauntletsDamage;
    }
    public int getMaulersDamage() {
        return MaulersDamage;
    }
    public int getSoulFistsDamage() {
        return SoulFistsDamage;
    }
    public int getGlaiveDamage() {
        return GlaiveDamage;
    }
    public int getGraveBaneDamage() {
        return GraveBaneDamage;
    }
    public int getVenomGlaiveDamage() {
        return VenomGlaiveDamage;
    }
    public int getGreatHammerDamage() {
        return GreatHammerDamage;
    }
    public int getStormlanderDamage() {
        return StormlanderDamage;
    }
    public int getHammerOfGravityDamage() {
        return HammerOfGravityDamage;
    }
    public int getMaceDamage() {
        return MaceDamage;
    }
    public int getFlailDamage() {
        return FlailDamage;
    }
    public int getSunsGraceDamage() {
        return SunsGraceDamage;
    }
    public int getKatanaDamage() {
        return KatanaDamage;
    }
    public int getMastersKatanaDamage() {
        return MastersKatanaDamage;
    }
    public int getDarkKatanaDamage() {
        return DarkKatanaDamage;
    }
    public int getDiamondPickDamage() {
        return DiamondPickDamage;
    }
    public int getRapierDamage() {
        return RapierDamage;
    }
    public int getBeestingerDamage() {
        return BeestingerDamage;
    }
    public int getFreezingFoilDamage() {
        return FreezingFoilDamage;
    }
    public int getJailorsScytheDamage() {
        return JailorsScytheDamage;
    }
    public int getSoulScytheDamage() {
        return SoulScytheDamage;
    }
    public int getFrostScytheDamage() {
        return FrostScytheDamage;
    }
    public int getSickleDamage() {
        return SickleDamage;
    }
    public int getNightmaresBiteDamage() {
        return NightmaresBiteDamage;
    }
    public int getLastLaughDamage() {
        return LastLaughDamage;
    }
    public int getSoulKnifeDamage() {
        return SoulKnifeDamage;
    }
    public int getEternalKnifeDamage() {
        return EternalKnifeDamage;
    }
    public int getTruthseekerDamage() {
        return TruthseekerDamage;
    }
    public int getSpearDamage() {
        return SpearDamage;
    }
    public int getWhisperingSpearDamage() {
        return WhisperingSpearDamage;
    }
    public int getFortuneSpearDamage() {
        return FortuneSpearDamage;
    }
    public int getBattlestaffDamage() {
        return BattlestaffDamage;
    }
    public int getGrowingStaffDamage() {
        return GrowingStaffDamage;
    }
    public int getBattlestaffOfTerrorDamage() {
        return BattlestaffOfTerrorDamage;
    }
    public int getIronSwordVarDamage() {
        return IronSwordVarDamage;
    }
    public int getDiamondSwordVarDamage() {
        return DiamondSwordVarDamage;
    }
    public int getHawkbrandDamage() {
        return HawkbrandDamage;
    }
    public int getTempestKnifeDamage() {
        return TempestKnifeDamage;
    }
    public int getChillGaleKnifeDamage() {
        return ChillGaleKnifeDamage;
    }
    public int getResoluteTempestKnifeDamage() {
        return ResoluteTempestKnifeDamage;
    }
    public int getWhipDamage() {
        return WhipDamage;
    }
    public int getVineWhipDamage() {
        return VineWhipDamage;
    }
}