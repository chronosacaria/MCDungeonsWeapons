package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwHammer;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Hammers {
    public static McdwHammer HAMMER_GRAVITY;
    public static McdwHammer HAMMER_GREAT;
    public static McdwHammer HAMMER_STORMLANDER;
    //public static McdwHammer HAMMER_MACE;
    public static McdwHammer HAMMER_FLAIL;
    public static McdwHammer HAMMER_SUNS_GRACE;

    public static void init() {
        HAMMER_GREAT = new McdwHammer(ToolMaterials.IRON, McdwStatsConfig.config.getGreatHammerDamage(),
                McdwStatsConfig.config.getGreatHammerSpeed(), "hammer_great");
        HAMMER_STORMLANDER = new McdwHammer(ToolMaterials.DIAMOND, McdwStatsConfig.config.getStormlanderDamage(),
                McdwStatsConfig.config.getStormlanderSpeed(),
                "hammer_stormlander");
        HAMMER_GRAVITY = new McdwHammer(ToolMaterials.DIAMOND, McdwStatsConfig.config.getHammerOfGravityDamage(),
                McdwStatsConfig.config.getHammerOfGravitySpeed(), "hammer_gravity");
        //HAMMER_MACE = new McdwHammer(ToolMaterials.IRON,McdwStatsConfig.config.getMaceDamage(),McdwStatsConfig.config.getMaceSpeed(),"hammer_mace");
        HAMMER_FLAIL = new McdwHammer(ToolMaterials.IRON, McdwStatsConfig.config.getFlailDamage(),
                McdwStatsConfig.config.getFlailSpeed(), "hammer_flail");
        HAMMER_SUNS_GRACE = new McdwHammer(ToolMaterials.DIAMOND, McdwStatsConfig.config.getSunsGraceDamage(),
                McdwStatsConfig.config.getSunsGraceSpeed(),
                "hammer_suns_grace");
    }
}
