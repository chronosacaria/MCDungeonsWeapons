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

    @Comment("Axes Speed")
    public float AxeSpeed = -3.1F;
    public float FirebrandSpeed = -3.1F;
    public float HighlandAxeSpeed = -3.1F;

    @Comment("Claymore Damage")
    public int ClaymoreDamage = 5;
    public int BroadswordDamage = 4;
    public int FrostSlayerDamage = 5;
    public int HeartstealerDamage = 4;
    public int GreatAxebladeDamage = 6;

    @Comment("Claymore Speed")
    public float ClaymoreSpeed = -3.0F;
    public float BroadswordSpeed = -3.0F;
    public float FrostSlayerSpeed = -3.0F;
    public float HeartstealerSpeed = -3.0F;
    public float GreatAxebladeSpeed = -3.0F;

    @Comment("Curves Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Curves Speed")
    public float CutlassSpeed = -2.1f;
    public float NamelessBladeSpeed = -1.7f;
    public float DancersSwordSpeed = -1.0f;

    @Comment("Daggers Damage")
    public int DaggerDamage = 1;
    public int FangsOfFrostDamage = 1;
    public int MoonDaggerDamage = 1;
    public int SheerDaggerDamage = 1;

    @Comment("Daggers Speed")
    public float DaggerSpeed = -1.3f;
    public float FangsOfFrostSpeed = -1.0f;
    public float MoonDaggerSpeed = -1.0f;
    public float SheerDaggerSpeed = -1.3f;

    @Comment("Double Axes Damage")
    public float DoubleAxeDamage = 6;
    public float WhirlwindDamage = 6;
    public float CursedAxeDamage = 7;

    @Comment("Double Axes Speed")
    public float DoubleAxeSpeed = -3.1f;
    public float WhirlwindSpeed = -2.9f;
    public float CursedAxeSpeed = -3.1f;

    @Comment("Gauntlets Damage")
    public int GauntletsDamage = 0;
    public int FightersBindingDamage = 0;
    public int MaulersDamage = 0;
    public int SoulFistsDamage = 0;

    @Comment("Gauntlets Speed")
    public float GauntletsSpeed = -1.4f;
    public float FightersBindingSpeed = -1.4f;
    public float MaulersSpeed = -1.4f;
    public float SoulFistsSpeed = -1.4f;

    @Comment("Glaives Damage")
    public int GlaiveDamage = 3;
    public int GraveBaneDamage = 5;
    public int VenomGlaiveDamage = 5;

    @Comment("Glaives Speed")
    public float GlaiveSpeed = -2.7f;
    public float GraveBaneSpeed = -2.4f;
    public float VenomGlaiveSpeed = -2.5f;

    @Comment("Hammers Damage")
    public int GreatHammerDamage = 4;
    public int StormlanderDamage = 5;
    public int HammerOfGravityDamage = 5;
    public int MaceDamage = 4;
    public int FlailDamage = 5;
    public int SunsGraceDamage = 4;

    @Comment("Hammers Speed")
    public float GreatHammerSpeed = -3.0f;
    public float StormlanderSpeed = -3.0f;
    public float HammerOfGravitySpeed = -3.0f;
    public float MaceSpeed = -2.9f;
    public float FlailSpeed = -2.9f;
    public float SunsGraceSpeed = -2.5f;

    @Comment("Katanas Damage")
    public int KatanaDamage = 1;
    public int MastersKatanaDamage = 0;
    public int DarkKatanaDamage = 0;

    @Comment("Katanas Speed")
    public float KatanaSpeed = -1.5f;
    public float MastersKatanaSpeed = -1.1f;
    public float DarkKatanaSpeed = -1.15f;

    @Comment("Picks Damage")
    public int DiamondPickDamage = 1;

    @Comment("Picks Speed")
    public float DiamondPickSpeed = -2.8f;

    @Comment("Rapiers Damage")
    public int RapierDamage = 0;
    public int BeestingerDamage = 0;
    public int FreezingFoilDamage = 0;

    @Comment("Rapiers Speed")
    public float RapierSpeed = -0.9f;
    public float BeestingerSpeed = -0.9f;
    public float FreezingFoilSpeed = -0.9f;

    @Comment("Scythes Damage")
    public int JailorsScytheDamage = 4;
    public int SoulScytheDamage = 3;
    public int FrostScytheDamage = 4;

    @Comment("Scythes Speed")
    public float JailorsScytheSpeed = -2.25f;
    public float SoulScytheSpeed = -2.25f;
    public float FrostScytheSpeed = -2.5f;

    @Comment("Sickles Damage")
    public int SickleDamage = 1;
    public int NightmaresBiteDamage = 3;
    public int LastLaughDamage = 4;
   
    @Comment("Sickles Speed")
    public float SickleSpeed = -1.9f;
    public float NightmaresBiteSpeed = -1.9f;
    public float LastLaughSpeed = -1.9f;

    @Comment("Soul Daggers Damage")
    public int SoulKnifeDamage = 1;
    public int EternalKnifeDamage = 0;
    public int TruthseekerDamage = 3;

    @Comment("Soul Daggers Speed")
    public float SoulKnifeSpeed = -1.1f;
    public float EternalKnifeSpeed = -0.9f;
    public float TruthseekerSpeed = -1.5f;
    
    @Comment("Spears Damage")
    public int SpearDamage = 3;
    public int WhisperingSpearDamage = 5;
    public int FortuneSpearDamage = 4;

    @Comment("Spears Speed")
    public float SpearSpeed = -2.5f;
    public float WhisperingSpearSpeed = -2.5f;
    public float FortuneSpearSpeed = -2.15f;

    @Comment("Staves Damage")
    public int BattlestaffDamage = 0;
    public int GrowingStaffDamage = 1;
    public int BattlestaffOfTerrorDamage = 0;

    @Comment("Staves Speed")
    public float BattlestaffSpeed = -0.1f;
    public float GrowingStaffSpeed = -0.1f;
    public float BattlestaffOfTerrorSpeed = -0.1f;

    @Comment("Swords Damage")
    public int IronSwordVarDamage = 3;
    public int DiamondSwordVarDamage = 3;
    public int HawkbrandDamage = 5;

    @Comment("Swords Speed")
    public float IronSwordVarSpeed = -2.4f;
    public float DiamondSwordVarSpeed = -2.4f;
    public float HawkbrandSpeed = -2.0f;

    @Comment("Tempest Knives Damage")
    public int TempestKnifeDamage = 2;
    public int ResoluteTempestKnifeDamage = 3;
    public int ChillGaleKnifeDamage = 4;

    @Comment("Tempest Knives Speed")
    public float TempestKnifeSpeed = -1.3f;
    public float ResoluteTempestKnifeSpeed = -1.3f;
    public float ChillGaleKnifeSpeed = -1.3f;

    @Comment("Whips Damage")
    public int WhipDamage = 2;
    public int VineWhipDamage = 5;

    @Comment("Whips Speed")
    public float WhipSpeed = -3.1f;
    public float VineWhipSpeed = -3.1f;

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
    public int getFrostSlayerDamage() {return FrostSlayerDamage; }
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
    public int getFightersBindingDamage() {return FightersBindingDamage;}
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

    public float getAxeSpeed() {
        return AxeSpeed;
    }
    public float getFirebrandSpeed() {
        return FirebrandSpeed;
    }
    public float getHighlandAxeSpeed() {
        return HighlandAxeSpeed;
    }
    public float getClaymoreSpeed() {
        return ClaymoreSpeed;
    }
    public float getBroadswordSpeed() {return BroadswordSpeed;}
    public float getFrostSlayerSpeed() {return FrostSlayerSpeed;}
    public float getHeartstealerSpeed() {
        return HeartstealerSpeed;
    }
    public float getGreatAxebladeSpeed() {
        return GreatAxebladeSpeed;
    }
    public float getCutlassSpeed() {
        return CutlassSpeed;
    }
    public float getNamelessBladeSpeed() {
        return NamelessBladeSpeed;
    }
    public float getDancersSwordSpeed() {
        return DancersSwordSpeed;
    }
    public float getDaggerSpeed() {
        return DaggerSpeed;
    }
    public float getFangsOfFrostSpeed() {
        return FangsOfFrostSpeed;
    }
    public float getMoonDaggerSpeed() {
        return MoonDaggerSpeed;
    }
    public float getSheerDaggerSpeed() {
        return SheerDaggerSpeed;
    }
    public float getDoubleAxeSpeed() {
        return DoubleAxeSpeed;
    }
    public float getWhirlwindSpeed() {
        return WhirlwindSpeed;
    }
    public float getCursedAxeSpeed() {
        return CursedAxeSpeed;
    }
    public float getGauntletsSpeed() {
        return GauntletsSpeed;
    }
    public float getFightersBindingSpeed() {return FightersBindingSpeed;}
    public float getMaulersSpeed() {
        return MaulersSpeed;
    }
    public float getSoulFistsSpeed() {
        return SoulFistsSpeed;
    }
    public float getGlaiveSpeed() {
        return GlaiveSpeed;
    }
    public float getGraveBaneSpeed() {
        return GraveBaneSpeed;
    }
    public float getVenomGlaiveSpeed() {
        return VenomGlaiveSpeed;
    }
    public float getGreatHammerSpeed() {return GreatHammerSpeed;}
    public float getStormlanderSpeed() {return StormlanderSpeed;}
    public float getHammerOfGravitySpeed() {
        return HammerOfGravitySpeed;
    }
    public float getMaceSpeed() {
        return MaceSpeed;
    }
    public float getFlailSpeed() {
        return FlailSpeed;
    }
    public float getSunsGraceSpeed() {
        return SunsGraceSpeed;
    }
    public float getKatanaSpeed() {
        return KatanaSpeed;
    }
    public float getMastersKatanaSpeed() {
        return MastersKatanaSpeed;
    }
    public float getDarkKatanaSpeed() {
        return DarkKatanaSpeed;
    }
    public float getDiamondPickSpeed() {
        return DiamondPickSpeed;
    }
    public float getRapierSpeed() {
        return RapierSpeed;
    }
    public float getBeestingerSpeed() {
        return BeestingerSpeed;
    }
    public float getFreezingFoilSpeed() {
        return FreezingFoilSpeed;
    }
    public float getJailorsScytheSpeed() {
        return JailorsScytheSpeed;
    }
    public float getSoulScytheSpeed() {
        return SoulScytheSpeed;
    }
    public float getFrostScytheSpeed() {
        return FrostScytheSpeed;
    }
    public float getSickleSpeed() {
        return SickleSpeed;
    }
    public float getNightmaresBiteSpeed() {
        return NightmaresBiteSpeed;
    }
    public float getLastLaughSpeed() {
        return LastLaughSpeed;
    }
    public float getSoulKnifeSpeed() {
        return SoulKnifeSpeed;
    }
    public float getEternalKnifeSpeed() {
        return EternalKnifeSpeed;
    }
    public float getTruthseekerSpeed() {
        return TruthseekerSpeed;
    }
    public float getSpearSpeed() {
        return SpearSpeed;
    }
    public float getWhisperingSpearSpeed() {
        return WhisperingSpearSpeed;
    }
    public float getFortuneSpearSpeed() {
        return FortuneSpearSpeed;
    }
    public float getBattlestaffSpeed() {
        return BattlestaffSpeed;
    }
    public float getGrowingStaffSpeed() {
        return GrowingStaffSpeed;
    }
    public float getBattlestaffOfTerrorSpeed() {
        return BattlestaffOfTerrorSpeed;
    }
    public float getIronSwordVarSpeed() {
        return IronSwordVarSpeed;
    }
    public float getDiamondSwordVarSpeed() {
        return DiamondSwordVarSpeed;
    }
    public float getHawkbrandSpeed() {
        return HawkbrandSpeed;
    }
    public float getTempestKnifeSpeed() {
        return TempestKnifeSpeed;
    }
    public float getChillGaleKnifeSpeed() {
        return ChillGaleKnifeSpeed;
    }
    public float getResoluteTempestKnifeSpeed() {
        return ResoluteTempestKnifeSpeed;
    }
    public float getWhipSpeed() {
        return WhipSpeed;
    }
    public float getVineWhipSpeed() {
        return VineWhipSpeed;
    }
}