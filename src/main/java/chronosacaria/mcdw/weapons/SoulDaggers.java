package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwDagger;
import net.minecraft.item.ToolMaterials;

public class SoulDaggers {
    public static McdwDagger DAGGER_ETERNAL_KNIFE;
    public static McdwDagger DAGGER_SOUL_KNIFE;
    public static McdwDagger SWORD_TRUTHSEEKER;

    public static void init() {
        DAGGER_SOUL_KNIFE = new McdwDagger(ToolMaterials.IRON, 5, -1.1F, "dagger_soul_knife");
        DAGGER_ETERNAL_KNIFE = new McdwDagger(ToolMaterials.DIAMOND, 5, -0.9F, "dagger_eternal_knife");
        SWORD_TRUTHSEEKER = new McdwDagger(ToolMaterials.IRON, 5, -1.5F, "sword_truthseeker");

    }
}


