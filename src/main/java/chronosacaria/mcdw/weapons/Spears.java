package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSpear;
//import chronosacaria.mcdw.entity.McdwEntities;
import net.minecraft.item.ToolMaterials;

public class Spears {
    public static McdwSpear SPEAR_FORTUNE;
    public static McdwSpear SPEAR_SPEAR;
    public static McdwSpear SPEAR_WHISPERING_SPEAR;

    public static void init(){
        SPEAR_SPEAR =
                new McdwSpear(
                        ToolMaterials.IRON,
                        3,
                        -2.5F,
                        //() -> McdwEntities.SPEAR_SPEAR,
                        "spear_spear");
        SPEAR_WHISPERING_SPEAR =
                new McdwSpear(
                        ToolMaterials.IRON,
                        5,
                        -2.5F,
                        //() -> McdwEntities.SPEAR_WHISPERING_SPEAR,
                        "spear_whispering_spear");
        SPEAR_FORTUNE =
                new McdwSpear(
                        ToolMaterials.GOLD,
                        4,
                        -2.15F,
                        //() -> McdwEntities.SPEAR_FORTUNE,
                        "spear_fortune");

    }
}
