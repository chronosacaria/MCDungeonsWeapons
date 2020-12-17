package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Daggers {
    public static McdwDagger DAGGER_DAGGER;
    public static McdwDagger DAGGER_FANGS_OF_FROST;
    public static McdwDagger DAGGER_MOON;
    public static McdwDagger DAGGER_SHEAR_DAGGER;

    public static void init() {
        DAGGER_DAGGER = new McdwDagger(ToolMaterials.IRON,
                McdwStatsConfig.config.getDaggerDamage(),
                McdwStatsConfig.config.getDaggerSpeed(),
                "dagger_dagger");
        DAGGER_FANGS_OF_FROST = new McdwDagger(ToolMaterials.IRON,
                McdwStatsConfig.config.getFangsOfFrostDamage(),
                McdwStatsConfig.config.getFangsOfFrostSpeed(),
                "dagger_fangs_of_frost");
        DAGGER_MOON = new McdwDagger(ToolMaterials.IRON,
                McdwStatsConfig.config.getMoonDaggerDamage(),
                McdwStatsConfig.config.getMoonDaggerSpeed(),
                "dagger_moon");
        DAGGER_SHEAR_DAGGER = new McdwDagger(ToolMaterials.IRON,
                McdwStatsConfig.config.getSheerDaggerDamage(),
                McdwStatsConfig.config.getSheerDaggerSpeed(),
                "dagger_shear_dagger");
    }
}


