package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwGauntlet;
import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Gauntlets {
    public static McdwGauntlet GAUNTLET_GAUNTLET;
    public static McdwGauntlet GAUNTLET_MAULERS;
    public static McdwGauntlet GAUNTLET_SOUL_FISTS;

    public static void init() {
        GAUNTLET_GAUNTLET = new McdwGauntlet(ToolMaterials.IRON,
                McdwStatsConfig.config.getGauntletsDamage(),
                -1.4F,
                "gauntlet_gauntlet");
        GAUNTLET_MAULERS = new McdwGauntlet(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getMaulersDamage(),
                -1.4F,
                "gauntlet_maulers");
        GAUNTLET_SOUL_FISTS = new McdwGauntlet(ToolMaterials.NETHERITE,
                McdwStatsConfig.config.getSoulFistsDamage(),
                -1.4F,
                "gauntlet_soul_fists");

        }
}
