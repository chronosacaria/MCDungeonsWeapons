package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwPick;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Picks {
    public static McdwPick PICK_DIAMOND_PICKAXE;
    public static McdwPick PICK_MOUNTAINEER_PICK;
    public static McdwPick PICK_HOWLING_PICK;
    public static McdwPick PICK_HAILING_PINNACLE;


    public static void init() {
        PICK_DIAMOND_PICKAXE = new McdwPick(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getDiamondPickDamage(),
                McdwStatsConfig.config.getDiamondPickSpeed(),
                "pick_diamond_pickaxe_var");
        PICK_MOUNTAINEER_PICK = new McdwPick(ToolMaterials.IRON,
                McdwStatsConfig.config.getMountaineerPickDamage(),
                McdwStatsConfig.config.getMountaineerPickSpeed(),
                "pick_mountaineer_pick");
        PICK_HOWLING_PICK = new McdwPick(ToolMaterials.IRON,
                McdwStatsConfig.config.getHowlingPickDamage(),
                McdwStatsConfig.config.getHowlingPickSpeed(),
                "pick_howling_pick");
        PICK_HAILING_PINNACLE = new McdwPick(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getHailingPinnacleDamage(),
                McdwStatsConfig.config.getHailingPinnacleSpeed(),
                "pick_hailing_pinnacle");

    }

}
