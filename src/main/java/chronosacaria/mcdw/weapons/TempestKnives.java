package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwDagger;
import net.minecraft.item.ToolMaterials;

public class TempestKnives {
    public static McdwDagger DAGGER_TEMPEST_KNIFE;
    public static McdwDagger DAGGER_CHILL_GALE_KNIFE;
    public static McdwDagger DAGGER_RESOLUTE_TEMPEST_KNIFE;

    public static void init() {
        DAGGER_TEMPEST_KNIFE = new McdwDagger(ToolMaterials.IRON, 2, -1.3F, "dagger_tempest_knife");
        DAGGER_CHILL_GALE_KNIFE = new McdwDagger(ToolMaterials.IRON, 4, -1.3F, "dagger_chill_gale_knife");
        DAGGER_RESOLUTE_TEMPEST_KNIFE = new McdwDagger(ToolMaterials.IRON, 4, -1.3F, "dagger_resolute_tempest_knife");
    }
}


