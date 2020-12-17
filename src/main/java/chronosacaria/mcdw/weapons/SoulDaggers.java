package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.bases.McdwSoulDagger;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class SoulDaggers {
    public static McdwSoulDagger DAGGER_ETERNAL_KNIFE;
    public static McdwSoulDagger DAGGER_SOUL_KNIFE;
    public static McdwSoulDagger SWORD_TRUTHSEEKER;

    public static void init() {
        DAGGER_SOUL_KNIFE = new McdwSoulDagger(ToolMaterials.IRON, McdwStatsConfig.config.getSoulKnifeDamage(),
                McdwStatsConfig.config.getSoulKnifeSpeed(),
                "dagger_soul_knife");
        DAGGER_ETERNAL_KNIFE = new McdwSoulDagger(ToolMaterials.DIAMOND,
                McdwStatsConfig.config.getEternalKnifeDamage(), McdwStatsConfig.config.getEternalKnifeSpeed(),
                "dagger_eternal_knife");
        SWORD_TRUTHSEEKER = new McdwSoulDagger(ToolMaterials.IRON, McdwStatsConfig.config.getTruthseekerDamage(),
                McdwStatsConfig.config.getTruthseekerSpeed(),
                "sword_truthseeker");

    }
}


