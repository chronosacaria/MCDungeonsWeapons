
package chronosacaria.mcdw.weapons;


import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwCrossbow;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

public class Crossbows {



    public static McdwCrossbow CROSSBOW_AUTO_CROSSBOW;
    public static McdwCrossbow CROSSBOW_AZURE_SEEKER;
    public static McdwCrossbow CROSSBOW_BABY_CROSSBOW;
    public static McdwCrossbow CROSSBOW_BURST_CROSSBOW;
    public static McdwCrossbow CROSSBOW_BUTTERFLY_CROSSBOW;

    public static McdwCrossbow CROSSBOW_CORRUPTED_CROSSBOW;
    public static McdwCrossbow CROSSBOW_RAPID_CROSSBOW;

    public static void init() {
        CROSSBOW_AUTO_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_auto_crossbow");
        CROSSBOW_AZURE_SEEKER = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_azure_seeker");
        CROSSBOW_BABY_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_baby_crossbow");
        CROSSBOW_BURST_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_burst_crossbow");
        CROSSBOW_BUTTERFLY_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_butterfly_crossbow");
        CROSSBOW_CORRUPTED_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_corrupted_crossbow");

        CROSSBOW_RAPID_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_rapid_crossbow");

    }
}


