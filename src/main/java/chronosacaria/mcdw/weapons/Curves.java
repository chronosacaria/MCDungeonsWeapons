package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Curves {
    public static McdwSword SWORD_CUTLASS;
    public static McdwSword SWORD_DANCERS_SWORD;
    public static McdwSword SWORD_NAMELESS_BLADE;

    public static void init() {
        SWORD_CUTLASS = new McdwSword(ToolMaterials.IRON, 3, -2.1F, "sword_cutlass");
        SWORD_NAMELESS_BLADE = new McdwSword(ToolMaterials.DIAMOND, 2, -1.7F, "sword_nameless_blade");
        SWORD_DANCERS_SWORD = new McdwSword(ToolMaterials.IRON, 1, -1F, "sword_dancers_sword");

        }
}
