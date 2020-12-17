package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;


public class Rapiers {
    public static McdwSword SWORD_BEESTINGER;
    public static McdwSword SWORD_FREEZING_FOIL;
    public static McdwSword SWORD_RAPIER;

    public static void init() {
        SWORD_RAPIER = new McdwSword(ToolMaterials.IRON, McdwStatsConfig.config.getRapierDamage(),
                McdwStatsConfig.config.getRapierSpeed(), "sword_rapier");
        SWORD_BEESTINGER = new McdwSword(ToolMaterials.DIAMOND, McdwStatsConfig.config.getBeestingerDamage(),
                McdwStatsConfig.config.getBeestingerSpeed(), "sword_beestinger");
        SWORD_FREEZING_FOIL = new McdwSword(ToolMaterials.DIAMOND, McdwStatsConfig.config.getFreezingFoilDamage(),
                McdwStatsConfig.config.getFreezingFoilSpeed(),
                "sword_freezing_foil");
        }
}
