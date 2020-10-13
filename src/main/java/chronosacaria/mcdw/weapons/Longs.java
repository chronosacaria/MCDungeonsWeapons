package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwLong;
import chronosacaria.mcdw.entity.McdwEntities;
import net.minecraft.item.ToolMaterials;

public class Longs {
    public static McdwLong SPEAR_FORTUNE;
    public static McdwLong SPEAR_GLAIVE;
    public static McdwLong SPEAR_GRAVE_BANE;
    public static McdwLong SPEAR_SPEAR;
    public static McdwLong SPEAR_VENOM_GLAIVE;
    public static McdwLong SPEAR_WHISPERING_SPEAR;
    public static McdwLong STAFF_BATTLESTAFF;
    public static McdwLong STAFF_BATTLESTAFF_OF_TERROR;
    public static McdwLong STAFF_GROWING_STAFF;

    public static void init(){
        SPEAR_SPEAR = new McdwLong(ToolMaterials.IRON, 3.0F, -2.5F, () -> McdwEntities.SPEAR_SPEAR,
                "spear_spear");
        SPEAR_FORTUNE = new McdwLong(ToolMaterials.GOLD, 4.0F, -2.15F, () -> McdwEntities.SPEAR_FORTUNE,
                "spear_fortune");
        SPEAR_WHISPERING_SPEAR =
                new McdwLong(ToolMaterials.IRON, 5.0F, -2.5F, () -> McdwEntities.SPEAR_WHISPERING_SPEAR,
                "spear_whispering_spear");
        SPEAR_GLAIVE =
                new McdwLong(ToolMaterials.IRON, 3.0F, -2.7F, () -> McdwEntities.SPEAR_GLAIVE,
                        "spear_glaive");
        SPEAR_GRAVE_BANE =
                new McdwLong(ToolMaterials.GOLD, 6.0F, -2.5F, () -> McdwEntities.SPEAR_GRAVE_BANE,
                        "spear_grave_bane");
        SPEAR_VENOM_GLAIVE =
                new McdwLong(ToolMaterials.IRON, 5.0F, -2.5F, () -> McdwEntities.SPEAR_VENOM_GLAIVE,
                        "spear_venom_glaive");
        STAFF_BATTLESTAFF = new McdwLong(ToolMaterials.WOOD, 0.0F, -0.1F, () -> McdwEntities.STAFF_BATTLESTAFF,
                "staff_battlestaff");
        STAFF_BATTLESTAFF_OF_TERROR = new McdwLong(ToolMaterials.IRON, 0.0F, -0.1F,
                () -> McdwEntities.STAFF_BATTLESTAFF_OF_TERROR,
                "staff_battlestaff_of_terror");
        STAFF_GROWING_STAFF = new McdwLong(ToolMaterials.GOLD, 1.0F, -0.1F, () -> McdwEntities.STAFF_GROWING_STAFF,
                "staff_growing_staff");
    }
}
