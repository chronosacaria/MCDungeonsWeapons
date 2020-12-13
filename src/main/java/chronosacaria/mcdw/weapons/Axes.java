package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.item.ToolMaterials;

public class Axes {
    //McdwStatsConfig config = AutoConfig.getConfigHolder(McdwStatsConfig.class).getConfig();

    public static McdwAxe AXE;
    public static McdwAxe AXE_FIREBRAND;
    public static McdwAxe AXE_HIGHLAND;

    public static void init() {
        AXE = new McdwAxe(
                ToolMaterials.IRON,
                McdwStatsConfig.config.getAxeDamage(),
                -3.1F,
                "axe");
        AXE_FIREBRAND = new McdwAxe(
                ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getFirebrandDamage(),
                -3.0F,
                "axe_firebrand");
        AXE_HIGHLAND = new McdwAxe(
                ToolMaterials.IRON,
                McdwStatsConfig.config.getHighlandAxeDamage(),
                -3.1F,
                "axe_highland");
    }
}
