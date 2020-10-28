package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Claymores {
    public static McdwSword SWORD_BROADSWORD;
    public static McdwSword SWORD_CLAYMORE;
    public static McdwSword SWORD_HEARTSTEALER;

    public static void init() {
        SWORD_CLAYMORE = new McdwSword(ToolMaterials.IRON, 6, -2.4F, "sword_claymore");
        SWORD_BROADSWORD = new McdwSword(ToolMaterials.IRON, 5, -1.4F, "sword_broadsword");
        SWORD_HEARTSTEALER = new McdwSword(ToolMaterials.DIAMOND, 4, -1.8F, "sword_heartstealer");
        }
}
