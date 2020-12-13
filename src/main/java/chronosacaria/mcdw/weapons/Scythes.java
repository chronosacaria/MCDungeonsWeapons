package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwScythe;
import chronosacaria.mcdw.bases.McdwSickle;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Scythes {
    public static McdwScythe SICKLE_FROST_SCYTHE;
    public static McdwScythe SICKLE_JAILORS_SCYTHE;
    public static McdwScythe SICKLE_SOUL_SCYTHE;

    public static void init() {
        SICKLE_JAILORS_SCYTHE = new McdwScythe(ToolMaterials.IRON, McdwStatsConfig.config.getJailorsScytheDamage(), -2.25F,
                "sickle_jailors_scythe");
        SICKLE_SOUL_SCYTHE = new McdwScythe(ToolMaterials.DIAMOND, McdwStatsConfig.config.getSoulScytheDamage(), -2.25F,
                "sickle_soul_scythe");
        SICKLE_FROST_SCYTHE = new McdwScythe(ToolMaterials.IRON, McdwStatsConfig.config.getFrostScytheDamage(), -2.5F,
                "sickle_frost_scythe");
    }
}
