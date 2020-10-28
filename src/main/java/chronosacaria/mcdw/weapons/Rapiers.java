package chronosacaria.mcdw.weapons;

import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.item.ToolMaterials;


public class Rapiers {
    public static McdwSword SWORD_BEESTINGER;
    public static McdwSword SWORD_FREEZING_FOIL;
    public static McdwSword SWORD_RAPIER;

    public static void init() {
        SWORD_RAPIER = new McdwSword(ToolMaterials.IRON,0, -0.9F, "sword_rapier");
        SWORD_BEESTINGER = new McdwSword(ToolMaterials.DIAMOND, 0,-0.9F, "sword_beestinger");
        SWORD_FREEZING_FOIL = new McdwSword(ToolMaterials.DIAMOND, 0,-0.9F, "sword_freezing_foil");
        }
}
