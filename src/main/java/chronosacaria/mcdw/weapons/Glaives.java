package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwGlaive;
import net.minecraft.item.ToolMaterials;

public class Glaives {
    public static McdwGlaive SPEAR_GLAIVE;
    public static McdwGlaive SPEAR_VENOM_GLAIVE;
    public static McdwGlaive SPEAR_GRAVE_BANE;

    public static void init(){
        SPEAR_GLAIVE =
                new McdwGlaive(
                        ToolMaterials.IRON,
                        3,
                        -2.7F,
                        //() -> McdwEntities.SPEAR_GLAIVE,
                        "spear_glaive");
        SPEAR_GRAVE_BANE =
                new McdwGlaive(
                        ToolMaterials.IRON,
                        5,
                        -2.4F,
                        //() -> McdwEntities.SPEAR_GRAVE_BANE,
                        "spear_grave_bane");
        SPEAR_VENOM_GLAIVE =
                new McdwGlaive(
                        ToolMaterials.IRON,
                        5,
                        -2.5F,
                        //() -> McdwEntities.SPEAR_VENOM_GLAIVE,
                        "spear_venom_glaive");
    }
}
