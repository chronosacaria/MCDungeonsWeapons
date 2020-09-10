package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwAxe;
import net.minecraft.item.ToolMaterials;

public class Axes {
    public static McdwAxe AXE;
    public static McdwAxe AXE_CURSED;
    public static McdwAxe AXE_DOUBLE;
    public static McdwAxe AXE_FIREBRAND;
    public static McdwAxe AXE_HIGHLAND;
    public static McdwAxe AXE_WHIRLWIND;

    public static void init() {
        AXE = new McdwAxe(ToolMaterials.IRON, 6.0F, -3.1F, "axe");
        AXE_CURSED = new McdwAxe(ToolMaterials.IRON, 7.0F, -3.1F, "axe_cursed");
        AXE_DOUBLE = new McdwAxe(ToolMaterials.IRON, 6.0F, -3.1F, "axe_double");
        AXE_HIGHLAND = new McdwAxe(ToolMaterials.IRON, 6.0F, -3.1F, "axe_highland");
        AXE_WHIRLWIND = new McdwAxe(ToolMaterials.IRON, 6.0F, -2.9F, "axe_whirlwind");
        AXE_FIREBRAND = new McdwAxe(ToolMaterials.DIAMOND, 5.0F, -3.0F, "axe_firebrand");
    }
}
