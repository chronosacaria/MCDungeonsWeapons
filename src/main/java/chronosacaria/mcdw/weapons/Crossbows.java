
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
    public static McdwCrossbow CROSSBOW_DOOM_CROSSBOW;
    public static McdwCrossbow CROSSBOW_DUAL_CROSSBOW;
    public static McdwCrossbow CROSSBOW_EXPLODING_CROSSBOW;
    public static McdwCrossbow CROSSBOW_FERAL_CROSSBOW;
    public static McdwCrossbow CROSSBOW_FIREBOLT_THROWER;
    public static McdwCrossbow CROSSBOW_HARP_CROSSBOW;
    public static McdwCrossbow CROSSBOW_HEAVY_CROSSBOW;
    public static McdwCrossbow CROSSBOW_IMPLODING_CROSSBOW;
    public static McdwCrossbow CROSSBOW_LIGHTNING_HARP_CROSSBOW;
    public static McdwCrossbow CROSSBOW_RAPID_CROSSBOW;
    public static McdwCrossbow CROSSBOW_SCATTER_CROSSBOW;
    public static McdwCrossbow CROSSBOW_SLAYER_CROSSBOW;
    public static McdwCrossbow CROSSBOW_THE_SLICER_CROSSBOW;
    public static McdwCrossbow CROSSBOW_SOUL_CROSSBOW;
    public static McdwCrossbow CROSSBOW_SOUL_HUNTER_CROSSBOW;
    public static McdwCrossbow CROSSBOW_SPELLBOUND_CROSSBOW;
    public static McdwCrossbow CROSSBOW_VOID_CALLER_CROSSBOW;

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
        CROSSBOW_DOOM_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_doom_crossbow");
        CROSSBOW_DUAL_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_dual_crossbow");
        CROSSBOW_EXPLODING_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_exploding_crossbow");
        CROSSBOW_FERAL_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_feral_soul_crossbow");
        CROSSBOW_FIREBOLT_THROWER = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_firebolt_thrower");
        CROSSBOW_HARP_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_harp_crossbow");
        CROSSBOW_HEAVY_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_heavy_crossbow");
        CROSSBOW_IMPLODING_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_imploding_crossbow");
        CROSSBOW_LIGHTNING_HARP_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_lightning_harp_crossbow");
        CROSSBOW_RAPID_CROSSBOW = new McdwCrossbow(
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), "crossbow_rapid_crossbow");
    }
}


