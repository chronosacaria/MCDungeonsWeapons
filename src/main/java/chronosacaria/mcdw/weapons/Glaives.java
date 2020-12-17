package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwGlaive;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Glaives {
    public static McdwGlaive SPEAR_GLAIVE;
    public static McdwGlaive SPEAR_VENOM_GLAIVE;
    public static McdwGlaive SPEAR_GRAVE_BANE;

    public static void init(){
        SPEAR_GLAIVE =
                new McdwGlaive(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getGlaiveDamage(),
                        McdwStatsConfig.config.getGlaiveSpeed(),
                        //() -> McdwEntities.SPEAR_GLAIVE,
                        "spear_glaive");
        SPEAR_GRAVE_BANE =
                new McdwGlaive(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getGraveBaneDamage(),
                        McdwStatsConfig.config.getGraveBaneSpeed(),
                        //() -> McdwEntities.SPEAR_GRAVE_BANE,
                        "spear_grave_bane");
        SPEAR_VENOM_GLAIVE =
                new McdwGlaive(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getVenomGlaiveDamage(),
                        McdwStatsConfig.config.getVenomGlaiveSpeed(),
                        //() -> McdwEntities.SPEAR_VENOM_GLAIVE,
                        "spear_venom_glaive");
    }
}
