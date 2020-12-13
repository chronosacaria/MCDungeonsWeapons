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
    public int DoubleAxeDamage = 6;
    public int WhirlwindDamage = 6;
    public int CursedAxeDamage = 7;

    @Comment("Gauntlets Damage")
    public int GauntletsDamage = 0;
    public int MaulersDamage = 0;
    public int SoulFistsDamage = 0;

    @Comment("Glaives Damage")
    public int GlaiveDamage = 3;
    public int GraveBaneDamage = 5;
    public int VenomGlaiveDamage = 5;

    @Comment("Hammers Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Katanas Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Picks Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Rapiers Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Scythes Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Sickles Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Soul Daggers Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Spears Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Staves Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Swords Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Tempest Knives Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

    @Comment("Whips Damage")
    public int CutlassDamage = 3;
    public int NamelessBladeDamage = 1;
    public int DancersSwordDamage = 1;

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
    public int getDoubleAxeDamage() {
        return DoubleAxeDamage;
    }
    public int getWhirlwindDamage() {
        return WhirlwindDamage;
    }
    public int getCursedAxeDamage() {
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
}