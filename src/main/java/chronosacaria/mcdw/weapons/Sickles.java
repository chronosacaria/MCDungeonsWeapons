package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSickle;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Sickles {
    public static McdwSickle SICKLE_LAST_LAUGH_GOLD;
    public static McdwSickle SICKLE_LAST_LAUGH_SILVER;
    public static McdwSickle SICKLE_NIGHTMARES_BITE;
    public static McdwSickle SICKLE_SICKLE;

    public static void init() {

        SICKLE_SICKLE = new McdwSickle(ToolMaterials.IRON, McdwStatsConfig.config.getSickleDamage(),
                McdwStatsConfig.config.getSickleSpeed(), "sickle_sickle");
        SICKLE_NIGHTMARES_BITE = new McdwSickle(ToolMaterials.IRON, McdwStatsConfig.config.getNightmaresBiteDamage(),
                McdwStatsConfig.config.getNightmaresBiteSpeed(),
                "sickle_nightmares_bite");
        SICKLE_LAST_LAUGH_GOLD = new McdwSickle(ToolMaterials.GOLD, McdwStatsConfig.config.getLastLaughDamage(),
                McdwStatsConfig.config.getLastLaughSpeed(),
                "sickle_last_laugh_gold");
        SICKLE_LAST_LAUGH_SILVER = new McdwSickle(ToolMaterials.GOLD, McdwStatsConfig.config.getLastLaughDamage(),
                McdwStatsConfig.config.getLastLaughSpeed(),
                "sickle_last_laugh_silver");
    }
}
