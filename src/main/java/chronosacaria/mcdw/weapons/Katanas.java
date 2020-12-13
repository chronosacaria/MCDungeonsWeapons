package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Katanas {

    public static McdwSword SWORD_DARK_KATANA;
    public static McdwSword SWORD_KATANA;
    public static McdwSword SWORD_MASTERS_KATANA;


    public static void init() {
        SWORD_KATANA = new McdwSword(ToolMaterials.IRON, McdwStatsConfig.config.getKatanaDamage(), -1.5F, "sword_katana");
        SWORD_MASTERS_KATANA = new McdwSword(ToolMaterials.DIAMOND, McdwStatsConfig.config.getMastersKatanaDamage(), -1.1F,
                "sword_masters_katana");
        SWORD_DARK_KATANA = new McdwSword(ToolMaterials.NETHERITE, McdwStatsConfig.config.getDarkKatanaDamage(), -1.15F,
                "sword_dark_katana");
        }
}
