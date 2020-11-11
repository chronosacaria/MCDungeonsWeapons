package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Katanas {

    public static McdwSword SWORD_DARK_KATANA;
    public static McdwSword SWORD_KATANA;
    public static McdwSword SWORD_MASTERS_KATANA;


    public static void init() {
        SWORD_KATANA = new McdwSword(ToolMaterials.IRON, 1, -1.5F, "sword_katana");
        SWORD_MASTERS_KATANA = new McdwSword(ToolMaterials.DIAMOND, 0, -1.1F, "sword_masters_katana");
        SWORD_DARK_KATANA = new McdwSword(ToolMaterials.NETHERITE, 0, -1.15F, "sword_dark_katana");
        }
}
