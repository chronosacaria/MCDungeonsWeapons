package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSickle;
import net.minecraft.item.ToolMaterials;

public class Sickles {
    public static McdwSickle SICKLE_FROST_SCYTHE;
    public static McdwSickle SICKLE_JAILORS_SCYTHE;
    public static McdwSickle SICKLE_LAST_LAUGH_GOLD;
    public static McdwSickle SICKLE_LAST_LAUGH_SILVER;
    public static McdwSickle SICKLE_NIGHTMARES_BITE;
    public static McdwSickle SICKLE_SICKLE;
    public static McdwSickle SICKLE_SOUL_SCYTHE;

    public static void init() {

        SICKLE_SICKLE = new McdwSickle(ToolMaterials.IRON, 1, -2.4F, "sickle_sickle");
        SICKLE_NIGHTMARES_BITE = new McdwSickle(ToolMaterials.IRON, 3, -2.4F, "sickle_nightmares_bite");
        SICKLE_LAST_LAUGH_GOLD = new McdwSickle(ToolMaterials.GOLD, 4, -1.9F,"sickle_last_laugh_gold");
        SICKLE_LAST_LAUGH_SILVER = new McdwSickle(ToolMaterials.GOLD, 4, -1.9F, "sickle_last_laugh_silver");
        SICKLE_FROST_SCYTHE = new McdwSickle(ToolMaterials.IRON, 4, -2.5F, "sickle_frost_scythe");
        SICKLE_JAILORS_SCYTHE = new McdwSickle(ToolMaterials.IRON, 4, -2.25F, "sickle_jailors_scythe");
        SICKLE_SOUL_SCYTHE = new McdwSickle(ToolMaterials.DIAMOND, 5, -2.25F, "sickle_soul_scythe");
    }
}
