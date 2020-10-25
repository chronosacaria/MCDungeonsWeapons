package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSickle;
import net.minecraft.item.ToolMaterials;

public class Scythes {
    public static McdwSickle SICKLE_FROST_SCYTHE;
    public static McdwSickle SICKLE_JAILORS_SCYTHE;
    public static McdwSickle SICKLE_SOUL_SCYTHE;

    public static void init() {
        SICKLE_JAILORS_SCYTHE = new McdwSickle(ToolMaterials.IRON, 4, -2.25F, "sickle_jailors_scythe");
        SICKLE_SOUL_SCYTHE = new McdwSickle(ToolMaterials.DIAMOND, 5, -2.25F, "sickle_soul_scythe");
        SICKLE_FROST_SCYTHE = new McdwSickle(ToolMaterials.IRON, 4, -2.5F, "sickle_frost_scythe");
    }
}
