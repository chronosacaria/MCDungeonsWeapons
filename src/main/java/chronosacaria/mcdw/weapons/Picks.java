package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwPick;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Picks {
    public static McdwPick PICK_DIAMOND_PICKAXE;
    public static McdwPick PICK_MOUNTAINEER_AXE_VAR_1;
    public static McdwPick PICK_MOUNTAINEER_AXE_VAR_2;
    public static McdwPick PICK_MOUNTAINEER_AXE_VAR_3;


    public static void init() {
        PICK_DIAMOND_PICKAXE = new McdwPick(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getDiamondPickDamage(),
                McdwStatsConfig.config.getDiamondPickSpeed(),
                "pick_diamond_pickaxe_var");
        /*PICK_MOUNTAINEER_AXE_VAR_1 = new McdwPick(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getMountaineerAxeDamage(),
                McdwStatsConfig.config.getMountaineerAxeSpeed(),
                "pick_mountaineer_axe_var_1");
        PICK_MOUNTAINEER_AXE_VAR_2 = new McdwPick(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getMountaineerAxeDamage(),
                McdwStatsConfig.config.getMountaineerAxeSpeed(),
                "pick_mountaineer_axe_var_2");
        PICK_MOUNTAINEER_AXE_VAR_3 = new McdwPick(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getMountaineerAxeDamage(),
                McdwStatsConfig.config.getMountaineerAxeSpeed(),
                "pick_mountaineer_axe_var_3");
        */
    }

}
