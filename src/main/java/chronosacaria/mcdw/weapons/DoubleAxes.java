package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwAxe;
import net.minecraft.item.ToolMaterials;

public class DoubleAxes {
    public static McdwAxe AXE_CURSED;
    public static McdwAxe AXE_DOUBLE;
    public static McdwAxe AXE_WHIRLWIND;

    public static void init() {
        AXE_DOUBLE = new McdwAxe(ToolMaterials.IRON, 6.0F, -3.1F, "axe_double");
        AXE_WHIRLWIND = new McdwAxe(ToolMaterials.IRON, 6.0F, -2.9F, "axe_whirlwind");
        AXE_CURSED = new McdwAxe(ToolMaterials.IRON, 7.0F, -3.1F, "axe_cursed");
    }
}
