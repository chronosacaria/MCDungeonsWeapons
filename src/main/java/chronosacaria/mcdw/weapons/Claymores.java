package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Claymores {
    public static McdwSword SWORD_BROADSWORD;
    public static McdwSword SWORD_CLAYMORE;
    public static McdwSword SWORD_FROST_SLAYER;
    public static McdwSword SWORD_HEARTSTEALER;
    public static McdwSword SWORD_GREAT_AXEBLADE;

    public static void init() {
        SWORD_CLAYMORE = new McdwSword(ToolMaterials.IRON,
                McdwStatsConfig.config.getClaymoreDamage(),
                McdwStatsConfig.config.getClaymoreSpeed(),
                "sword_claymore");
        SWORD_BROADSWORD = new McdwSword(ToolMaterials.IRON,
                McdwStatsConfig.config.getBroadswordDamage(),
                McdwStatsConfig.config.getBroadswordSpeed(),
                "sword_broadsword");
        SWORD_FROST_SLAYER = new McdwSword(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getFrostSlayerDamage(),
                McdwStatsConfig.config.getFrostSlayerSpeed(),
                "sword_frost_slayer");
        SWORD_HEARTSTEALER = new McdwSword(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getHeartstealerDamage(),
                McdwStatsConfig.config.getHeartstealerSpeed(),
                "sword_heartstealer");
        SWORD_GREAT_AXEBLADE = new McdwSword(ToolMaterials.IRON,
                McdwStatsConfig.config.getGreatAxebladeDamage(),
                McdwStatsConfig.config.getGreatAxebladeSpeed(),
                "sword_great_axeblade");
        }
}
