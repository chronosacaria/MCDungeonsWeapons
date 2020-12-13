package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Claymores {
    public static McdwSword SWORD_BROADSWORD;
    public static McdwSword SWORD_CLAYMORE;
    public static McdwSword SWORD_HEARTSTEALER;
    public static McdwSword SWORD_GREAT_AXEBLADE;

    public static void init() {
        SWORD_CLAYMORE = new McdwSword(ToolMaterials.IRON,
                McdwStatsConfig.config.getClaymoreDamage(),
                -3.0F,
                "sword_claymore");
        SWORD_BROADSWORD = new McdwSword(ToolMaterials.IRON,
                McdwStatsConfig.config.getBroadswordDamage(),
                -3.0F,
                "sword_broadsword");
        SWORD_HEARTSTEALER = new McdwSword(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getHeartstealerDamage(),
                -3.0F,
                "sword_heartstealer");
        SWORD_GREAT_AXEBLADE = new McdwSword(ToolMaterials.IRON,
                McdwStatsConfig.config.getGreatAxebladeDamage(),
                -3.0F,
                "sword_great_axeblade");
        }
}
