package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Katanas {

    public static McdwSword SWORD_DARK_KATANA;
    public static McdwSword SWORD_KATANA;
    public static McdwSword SWORD_MASTERS_KATANA;


    public static void init() {
        SWORD_KATANA = new McdwSword(ToolMaterials.IRON, 2, -1.5F, "sword_katana");
        SWORD_MASTERS_KATANA = new McdwSword(ToolMaterials.DIAMOND, 4, -1.35F, "sword_masters_katana");
        SWORD_DARK_KATANA = new McdwSword(ToolMaterials.NETHERITE, 5, -1.15F, "sword_dark_katana");
        }
}
