package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import net.minecraft.item.ToolMaterials;

public class DoubleAxes {
    public static McdwDoubleAxe AXE_CURSED;
    public static McdwDoubleAxe AXE_DOUBLE;
    public static McdwDoubleAxe AXE_WHIRLWIND;

    public static void init() {
        AXE_DOUBLE = new McdwDoubleAxe(ToolMaterials.IRON, 6.0F, -3.1F, "axe_double");
        AXE_WHIRLWIND = new McdwDoubleAxe(ToolMaterials.IRON, 6.0F, -2.9F, "axe_whirlwind");
        AXE_CURSED = new McdwDoubleAxe(ToolMaterials.IRON, 7.0F, -3.1F, "axe_cursed");
    }
}
