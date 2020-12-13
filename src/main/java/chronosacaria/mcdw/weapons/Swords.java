package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Swords {
    public static McdwSword SWORD_DIAMOND_SWORD;
    public static McdwSword SWORD_HAWKBRAND;
    public static McdwSword SWORD_IRON_SWORD_VAR;

    public static void init() {
        SWORD_IRON_SWORD_VAR = new McdwSword(ToolMaterials.IRON, McdwStatsConfig.config.getIronSwordVarDamage(), -2.4F,
                "sword_iron_sword_var");
        SWORD_DIAMOND_SWORD = new McdwSword(ToolMaterials.DIAMOND, McdwStatsConfig.config.getDiamondSwordVarDamage(), -2.4F,
                "sword_diamond_sword_var");
        SWORD_HAWKBRAND = new McdwSword(ToolMaterials.IRON, McdwStatsConfig.config.getHawkbrandDamage(), -2.0F, "sword_hawkbrand");

        }
}
