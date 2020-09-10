package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSpear;
import net.minecraft.item.ToolMaterials;

public class Spears {
    public static McdwSpear SPEAR_FORTUNE;
    public static McdwSpear SPEAR_GLAIVE;
    public static McdwSpear SPEAR_GRAVE_BANE;
    public static McdwSpear SPEAR_SPEAR;
    public static McdwSpear SPEAR_VENOM_GLAIVE;
    public static McdwSpear SPEAR_WHISPERING_SPEAR;

    public static void init(){
        SPEAR_SPEAR = new McdwSpear(ToolMaterials.IRON, 3, -2.5F, "spear_spear");
        SPEAR_FORTUNE = new McdwSpear(ToolMaterials.GOLD,4,-2.15F,"spear_fortune");
        SPEAR_WHISPERING_SPEAR = new McdwSpear(ToolMaterials.IRON, 5, -2.5F, "spear_whispering_spear");
        SPEAR_GLAIVE = new McdwSpear(ToolMaterials.IRON, 3, -2.7F, "spear_glaive");
        SPEAR_GRAVE_BANE = new McdwSpear(ToolMaterials.GOLD, 6, -2.5F, "spear_grave_bane");
        SPEAR_VENOM_GLAIVE = new McdwSpear(ToolMaterials.IRON, 5, -2.5F, "spear_venom_glaive");

    }
}
