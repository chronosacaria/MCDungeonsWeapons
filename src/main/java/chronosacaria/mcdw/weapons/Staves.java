package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwStaff;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.ToolMaterials;

public class Staves {
    public static McdwStaff STAFF_BATTLESTAFF;
    public static McdwStaff STAFF_BATTLESTAFF_OF_TERROR;
    public static McdwStaff STAFF_GROWING_STAFF;

    public static void init(){
        STAFF_BATTLESTAFF =
                new McdwStaff(
                        ToolMaterials.WOOD,
                        McdwStatsConfig.config.getBattlestaffDamage(),
                        McdwStatsConfig.config.getBattlestaffSpeed(),
                        //() -> McdwEntities.STAFF_BATTLESTAFF,
                        "staff_battlestaff");
        STAFF_GROWING_STAFF =
                new McdwStaff(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getGrowingStaffDamage(),
                        McdwStatsConfig.config.getGrowingStaffSpeed(),
                        //() -> McdwEntities.STAFF_GROWING_STAFF,
                        "staff_growing_staff");
        STAFF_BATTLESTAFF_OF_TERROR =
                new McdwStaff(
                        ToolMaterials.IRON,
                        McdwStatsConfig.config.getBattlestaffOfTerrorDamage(),
                        McdwStatsConfig.config.getBattlestaffOfTerrorSpeed(),
                        //() -> McdwEntities.STAFF_BATTLESTAFF_OF_TERROR,
                        "staff_battlestaff_of_terror");
    }
}
