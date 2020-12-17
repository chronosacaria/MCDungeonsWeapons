package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Katanas {

    public static McdwSword SWORD_DARK_KATANA;
    public static McdwSword SWORD_KATANA;
    public static McdwSword SWORD_MASTERS_KATANA;


    public static void init() {
        SWORD_KATANA = new McdwSword(ToolMaterials.IRON, McdwStatsConfig.config.getKatanaDamage(),
                McdwStatsConfig.config.getKatanaSpeed(), "sword_katana");
        SWORD_MASTERS_KATANA = new McdwSword(ToolMaterials.DIAMOND, McdwStatsConfig.config.getMastersKatanaDamage(),
                McdwStatsConfig.config.getMastersKatanaSpeed(),
                "sword_masters_katana");
        SWORD_DARK_KATANA = new McdwSword(ToolMaterials.NETHERITE, McdwStatsConfig.config.getDarkKatanaDamage(),
                McdwStatsConfig.config.getDarkKatanaSpeed(),
                "sword_dark_katana");
        }
}
