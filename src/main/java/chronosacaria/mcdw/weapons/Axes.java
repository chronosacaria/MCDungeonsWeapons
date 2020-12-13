package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.configs.McdwConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.item.ToolMaterials;

public class Axes {

    public static McdwAxe AXE;
    public static McdwAxe AXE_FIREBRAND;
    public static McdwAxe AXE_HIGHLAND;

    public static void init() {
        AXE = new McdwAxe(
                ToolMaterials.IRON,
                McdwConfig.config.statsConfig.Axe_Damage,
                -3.1F,
                "axe");
        AXE_FIREBRAND = new McdwAxe(
                ToolMaterials.DIAMOND,
                5.0F,
                -3.0F,
                "axe_firebrand");
        AXE_HIGHLAND = new McdwAxe(
                ToolMaterials.IRON,
                6.0F,
                -3.1F,
                "axe_highland");
    }
}
