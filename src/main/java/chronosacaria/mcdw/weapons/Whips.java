package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwWhip;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Whips {
    public static McdwWhip WHIP_WHIP;
    public static McdwWhip WHIP_VINE_WHIP;

    public static void init(){
        WHIP_WHIP =
                new McdwWhip(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getWhipDamage(),
                        McdwStatsConfig.config.getWhipSpeed(),
                        "whip_whip");
        WHIP_VINE_WHIP =
                new McdwWhip(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getVineWhipDamage(),
                        McdwStatsConfig.config.getVineWhipSpeed(),
                        "whip_vine_whip");
    }
}
