package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Curves {
    public static McdwSword SWORD_CUTLASS;
    public static McdwSword SWORD_DANCERS_SWORD;
    public static McdwSword SWORD_NAMELESS_BLADE;

    public static void init() {
        SWORD_CUTLASS = new McdwSword(
                ToolMaterials.IRON,
                McdwStatsConfig.config.getCutlassDamage(),
                McdwStatsConfig.config.getCutlassSpeed(),
                "sword_cutlass");
        SWORD_NAMELESS_BLADE = new McdwSword(
                ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getNamelessBladeDamage(),
                McdwStatsConfig.config.getNamelessBladeSpeed(),
                "sword_nameless_blade");
        SWORD_DANCERS_SWORD = new McdwSword(
                ToolMaterials.IRON,
                McdwStatsConfig.config.getDancersSwordDamage(),
                McdwStatsConfig.config.getDancersSwordSpeed(),
                "sword_dancers_sword");

        }
}
