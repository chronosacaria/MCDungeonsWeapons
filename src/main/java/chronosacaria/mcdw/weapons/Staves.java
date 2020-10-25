package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwLong;
import chronosacaria.mcdw.entity.McdwEntities;
import net.minecraft.item.ToolMaterials;

public class Staves {
    public static McdwLong STAFF_BATTLESTAFF;
    public static McdwLong STAFF_BATTLESTAFF_OF_TERROR;
    public static McdwLong STAFF_GROWING_STAFF;

    public static void init(){
        STAFF_BATTLESTAFF = new McdwLong(ToolMaterials.WOOD, 0.0F, -0.1F, () -> McdwEntities.STAFF_BATTLESTAFF,
                "staff_battlestaff");
        STAFF_GROWING_STAFF = new McdwLong(ToolMaterials.GOLD, 1.0F, -0.1F, () -> McdwEntities.STAFF_GROWING_STAFF,
                "staff_growing_staff");
        STAFF_BATTLESTAFF_OF_TERROR = new McdwLong(ToolMaterials.IRON, 0.0F, -0.1F,
                () -> McdwEntities.STAFF_BATTLESTAFF_OF_TERROR,
                "staff_battlestaff_of_terror");
    }
}
