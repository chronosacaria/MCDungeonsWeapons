
package chronosacaria.mcdw.weapons;


import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
public class Bows {


    public static McdwBow BOW_BONEBOW;
    //public static McdwBow BOW_TWIN_BOW;
    public static McdwBow BOW_HAUNTED_BOW;

    public static McdwBow BOW_LONGBOW;
    public static McdwBow BOW_RED_SNAKE;
    public static McdwBow BOW_GUARDIAN_BOW;

    public static McdwBow BOW_SHORTBOW;
    public static McdwBow BOW_MECHANICAL_SHORTBOW;
    public static McdwBow BOW_PURPLE_STORM;
    public static McdwBow BOW_LOVE_SPELL_BOW;

    public static McdwBow BOW_TRICKBOW;
    public static McdwBow BOW_GREEN_MENACE;
    public static McdwBow BOW_PINK_SCOUNDREL;

    public static McdwBow BOW_POWER_BOW;
    public static McdwBow BOW_ELITE_POWER_BOW;
    public static McdwBow BOW_SABREWING;

    public static McdwBow BOW_HUNTING_BOW;
    public static McdwBow BOW_MASTERS_BOW;
    public static McdwBow BOW_HUNTERS_PROMISE;
    public static McdwBow BOW_ANCIENT_BOW;

    public static McdwBow BOW_SOUL_BOW;
    public static McdwBow BOW_NOCTURNAL_BOW;
    public static McdwBow BOW_LOST_SOULS;

    //public static McdwBow BOW_WIND_BOW;
    //public static McdwBow BOW_ECHO_OF_THE_VALLEY;

    public static McdwBow BOW_SNOW_BOW;
    public static McdwBow BOW_WINTERS_TOUCH;


    public static void init() {
        BOW_BONEBOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_bonebow");
        //BOW_TWIN_BOW = new McdwBow(ToolMaterials.WOOD,
                //new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,16.0F, 3.2F, "bow_twin_bow");
        BOW_HAUNTED_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_haunted_bow");

        BOW_LONGBOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,20.0F, 6.4F ,"bow_longbow");
        BOW_RED_SNAKE = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,30.0F, 6.4F, "bow_red_snake");
        BOW_GUARDIAN_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,30.0F, 6.4F, "bow_guardian_bow");

        BOW_SHORTBOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,8.0F, 2.0F, "bow_shortbow");
        BOW_MECHANICAL_SHORTBOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,8.0F, 2.8F, "bow_mechanical_shortbow");
        BOW_PURPLE_STORM = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,8.0F, 2.0F, "bow_purple_storm");
        BOW_LOVE_SPELL_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350), 8.0F, 2.0F, "bow_love_spell_bow");

        BOW_TRICKBOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_trickbow");
        BOW_GREEN_MENACE = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_green_menace");
        BOW_PINK_SCOUNDREL = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_pink_scoundrel");

        BOW_POWER_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,20.0F, 6.4F, "bow_power_bow");
        BOW_ELITE_POWER_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,20.0F, 6.4F, "bow_elite_power_bow");
        BOW_SABREWING = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_sabrewing");

        BOW_HUNTING_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_hunting_bow");
        BOW_MASTERS_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_masters_bow");
        BOW_HUNTERS_PROMISE = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_hunters_promise");
        BOW_ANCIENT_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_ancient_bow");

        BOW_SOUL_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_soul_bow");
        BOW_NOCTURNAL_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_nocturnal_bow");
        BOW_LOST_SOULS = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_lost_souls");

        /*BOW_WIND_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_wind_bow");
        BOW_ECHO_OF_THE_VALLEY = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F,
                "bow_echo_of_the_valley");*/

        /*BOW_SNOW_BOW = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_snow_bow");
        BOW_WINTERS_TOUCH = new McdwBow(ToolMaterials.WOOD,
                new Item.Settings().group(Mcdw.RANGED).maxCount(1).maxDamage(350) ,15.0F, 3.2F, "bow_winters_touch");*/

    }
}


