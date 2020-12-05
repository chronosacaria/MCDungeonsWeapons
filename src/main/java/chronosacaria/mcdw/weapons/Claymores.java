package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Claymores {
    public static McdwSword SWORD_BROADSWORD;
    public static McdwSword SWORD_CLAYMORE;
    public static McdwSword SWORD_HEARTSTEALER;
    public static McdwSword SWORD_GREAT_AXEBLADE;

    public static void init() {
        SWORD_CLAYMORE = new McdwSword(ToolMaterials.IRON, 5, -3.0F, "sword_claymore");
        SWORD_BROADSWORD = new McdwSword(ToolMaterials.IRON, 4, -3.0F, "sword_broadsword");
        SWORD_HEARTSTEALER = new McdwSword(ToolMaterials.DIAMOND, 4, -3.0F, "sword_heartstealer");
        SWORD_GREAT_AXEBLADE = new McdwSword(ToolMaterials.IRON, 6, -3.0F, "sword_great_axeblade");
        }
}
