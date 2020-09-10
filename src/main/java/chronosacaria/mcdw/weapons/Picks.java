package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwPick;
import net.minecraft.item.ToolMaterials;

public class Picks {
    public static McdwPick PICK_DIAMOND_PICKAXE;

    public static void init() {
        PICK_DIAMOND_PICKAXE = new McdwPick(ToolMaterials.DIAMOND, 1, -2.8F, "pick_diamond_pickaxe_var");
    }
}
