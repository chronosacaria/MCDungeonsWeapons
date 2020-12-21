
package chronosacaria.mcdw.weapons;


import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwCrossbow;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

public class Crossbows {


    public static McdwCrossbow CROSSBOW_BUTTERFLY_CROSSBOW;
    public static McdwCrossbow CROSSBOW_RAPID_CROSSBOW;

    public static void init() {
        CROSSBOW_BUTTERFLY_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "butterfly_crossbow");
        CROSSBOW_RAPID_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "rapid_crossbow");

    }
}


