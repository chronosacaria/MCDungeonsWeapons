package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwHammer;
import net.minecraft.item.ToolMaterials;

public class Hammers {
    public static McdwHammer HAMMER_FLAIL;
    public static McdwHammer HAMMER_GRAVITY;
    public static McdwHammer HAMMER_GREAT;
    public static McdwHammer HAMMER_STORMLANDER;
    public static McdwHammer HAMMER_SUNS_GRACE;

    public static void init() {
        HAMMER_FLAIL = new McdwHammer(ToolMaterials.IRON, 5, -2.9F, "hammer_flail");
        HAMMER_GRAVITY = new McdwHammer(ToolMaterials.DIAMOND, 9, -3.0F, "hammer_gravity");
        HAMMER_GREAT = new McdwHammer(ToolMaterials.IRON, 6, -3.0F, "hammer_great");
        HAMMER_STORMLANDER = new McdwHammer(ToolMaterials.DIAMOND, 9, -3.0F, "hammer_stormlander");
        HAMMER_SUNS_GRACE = new McdwHammer(ToolMaterials.DIAMOND, 7, -2.5F, "hammer_suns_grace");
    }
}
