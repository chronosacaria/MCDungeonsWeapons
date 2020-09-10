package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Swords {
    public static McdwSword SWORD_BROADSWORD;
    public static McdwSword SWORD_CLAYMORE;
    public static McdwSword SWORD_CUTLASS;
    public static McdwSword SWORD_DARK_KATANA;
    public static McdwSword SWORD_DANCERS_SWORD;
    public static McdwSword SWORD_DIAMOND_SWORD;
    public static McdwSword SWORD_HAWKBRAND;
    public static McdwSword SWORD_HEARTSTEALER;
    public static McdwSword SWORD_IRON_SWORD_VAR;
    public static McdwSword SWORD_KATANA;
    public static McdwSword SWORD_MASTERS_KATANA;
    public static McdwSword SWORD_NAMELESS_BLADE;
    public static McdwSword SWORD_TRUTHSEEKER;

    public static void init() {
        SWORD_IRON_SWORD_VAR = new McdwSword(ToolMaterials.IRON, 3, -2.4F, "sword_iron_sword_var");
        SWORD_HAWKBRAND = new McdwSword(ToolMaterials.IRON, 5, -2.0F, "sword_hawkbrand");
        SWORD_BROADSWORD = new McdwSword(ToolMaterials.IRON, 5, -1.4F, "sword_broadsword");
        SWORD_CLAYMORE = new McdwSword(ToolMaterials.IRON, 6, -2.4F, "sword_claymore");
        SWORD_HEARTSTEALER = new McdwSword(ToolMaterials.DIAMOND, 4, -1.8F, "sword_heartstealer");
        SWORD_CUTLASS = new McdwSword(ToolMaterials.IRON, 3, -2.1F, "sword_cutlass");
        SWORD_DANCERS_SWORD = new McdwSword(ToolMaterials.IRON, 1, -1F, "sword_dancers_sword");
        SWORD_DIAMOND_SWORD = new McdwSword(ToolMaterials.DIAMOND, 3, -2.4F, "sword_diamond_sword_var");
        SWORD_NAMELESS_BLADE = new McdwSword(ToolMaterials.DIAMOND, 2, -1.7F, "sword_nameless_blade");
        SWORD_KATANA = new McdwSword(ToolMaterials.IRON, 2, -1.5F, "sword_katana");
        SWORD_MASTERS_KATANA = new McdwSword(ToolMaterials.DIAMOND, 4, -1.35F, "sword_masters_katana");
        SWORD_DARK_KATANA = new McdwSword(ToolMaterials.DIAMOND, 5, -1.15F, "sword_dark_katana");
        SWORD_TRUTHSEEKER = new McdwSword(ToolMaterials.IRON, 5, -1.5F, "sword_truthseeker");
    }
}
