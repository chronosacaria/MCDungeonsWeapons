package chronosacaria.mcdw.items;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.configs.McdwStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class ItemRegistry {

    public static final HashMap<String, Item> ITEMS = new HashMap<>();

    private static final HashSet<String> SWORDS = new HashSet<String>() {{
        //add("sword_claymore");
        //add("sword_broadsword");
        //add("sword_frost_slayer");
        //add("sword_heartstealer");
        //add("sword_great_axeblade");
        //add("sword_rapier");
        //add("sword_beestinger");
        //add("sword_freezing_foil");
        //add("sword_cutlass");
        //add("sword_nameless_blade");
        //add("sword_dancers_sword");
        //add("sword_katana");
        //add("sword_masters_katana");
        //add("sword_dark_katana");
        //add("sword_iron_sword_var");
        //add("sword_diamond_sword_var");
        //add("sword_hawkbrand");
        //add("sword_sinister");
        //add("sword_broken_sawblade");
        //add("sword_mechanized_sawblade");
        //add("sword_coral_blade");
        //add("sword_sponge_striker");
        //add("sword_obsidian_claymore");
        //add("sword_the_starless_night");
    }};
    private static final HashSet<String> AXES = new HashSet<String>() {{
        //add("axe");
        //add("axe_firebrand");
        //add("axe_highland");
        //add("axe_anchor");
        //add("axe_encrusted_anchor");
    }};
    private static final HashSet<String> DOUBLE_AXES = new HashSet<String>() {{
        //add("axe_cursed");
        //add("axe_double");
        //add("axe_whirlwind");
    }};
    private static final HashSet<String> DAGGERS = new HashSet<String>(){{
        //add("dagger_dagger");
        //add("dagger_fangs_of_frost");
        //add("dagger_moon");
        //add("dagger_shear_dagger");
        //add("dagger_tempest_knife");
        //add("dagger_resolute_tempest_knife");
        //add("dagger_chill_gale_knife");
        //add("dagger_backstabber");
        //add("dagger_swift_striker");
        //add("dagger_void_touched_blade");
        //add("dagger_the_beginning");
        //add("dagger_the_end");
    }};
    private static final HashSet<String> SOUL_DAGGERS = new HashSet<String>(){{
        //add("dagger_soul_knife");
        //add("dagger_eternal_knife");
        //add("sword_truthseeker");
    }};
    private static final HashSet<String> HAMMERS = new HashSet<String>(){{
        //add("hammer_great");
        //add("hammer_stormlander");
        //add("hammer_gravity");
        //add("hammer_mace");
        //add("hammer_flail");
        //add("hammer_suns_grace");
        //add("hammer_boneclub");
        //add("hammer_bone_cudgel");
    }};
    private static final HashSet<String> GAUNTLETS = new HashSet<String>(){{
        //add("gauntlet_gauntlet");
        //add("gauntlet_maulers");
        //add("gauntlet_soul_fists");
        //add("gauntlet_fighters_bindings");
    }};
    private static final HashSet<String> SICKLES = new HashSet<String>(){{
        //add("sickle_sickle");
        //add("sickle_nightmares_bite");
        //add("sickle_last_laugh_gold");
        //add("sickle_last_laugh_silver");
    }};
    private static final HashSet<String> SCYTHES = new HashSet<String>(){{
        //add("sickle_jailors_scythe");
        //add("sickle_soul_scythe");
        //add("sickle_frost_scythe");
        //add("sickle_skull_scythe");
    }};
    private static final HashSet<String> PICKS = new HashSet<String>(){{
        //add("pick_diamond_pickaxe_var");
        //add("pick_mountaineer_pick");
        //add("pick_howling_pick");
        //add("pick_hailing_pinnacle");
    }};
    private static final HashSet<String> GLAIVES = new HashSet<String>(){{
        //add("spear_glaive");
        //add("spear_grave_bane");
        //add("spear_venom_glaive");
        //add("spear_cackling_broom");
    }};
    private static final HashSet<String> SPEARS = new HashSet<String>(){{
        //add("spear_spear");
        //add("spear_whispering_spear");
        //add("spear_fortune");
    }};
    private static final HashSet<String> STAVES = new HashSet<String>(){{
        //add("staff_battlestaff");
        //add("staff_growing_staff");
        //add("staff_battlestaff_of_terror");
    }};
    private static final HashSet<String> WHIPS = new HashSet<String>(){{
        //add("whip_whip");
        //add("whip_vine_whip");
    }};
    public static final HashSet<String> BOWS = new HashSet<String>(){{
        add("bow_ancient_bow");
        add("bow_bonebow");
        add("bow_burst_gale_bow");
        add("bow_echo_of_the_valley");
        add("bow_elite_power_bow");
        add("bow_green_menace");
        add("bow_guardian_bow");
        add("bow_haunted_bow");
        add("bow_hunters_promise");
        add("bow_hunting_bow");
        add("bow_longbow");
        add("bow_lost_souls");
        add("bow_masters_bow");
        add("bow_nocturnal_bow");
        add("bow_pink_scoundrel");
        add("bow_power_bow");
        add("bow_sabrewing");
        add("bow_shivering_bow");
        add("bow_soul_bow");
        add("bow_trickbow");
        add("bow_twin_bow");
        add("bow_wind_bow");
        add("bow_snow_bow");
        add("bow_winters_touch");
        add("bow_twisting_vine_bow");
        add("bow_weeping_vine_bow");
        add("bow_bubble_bow");
        add("bow_bubble_burster");
        add("bow_void_bow");
        add("bow_call_of_the_void");
        add("bow_phantom_bow");
        add("bow_web_bow");
    }};
    public static final HashSet<String> SHORTBOWS = new HashSet<String>(){{
        add("bow_love_spell_bow");
        add("bow_shortbow");
        add("bow_mechanical_shortbow");
        add("bow_purple_storm");
    }};
    public static final HashSet<String> LONGBOWS = new HashSet<String>(){{
        add("bow_red_snake");
    }};
    public static final HashSet<String> CROSSBOWS = new HashSet<String>(){{
        add("crossbow_the_slicer");
        add("crossbow_azure_seeker");
        add("crossbow_exploding_crossbow");
        add("crossbow_imploding_crossbow");
        add("crossbow_firebolt_thrower");
        add("crossbow_heavy_crossbow");
        add("crossbow_doom_crossbow");
        add("crossbow_slayer_crossbow");
        add("crossbow_rapid_crossbow");
        add("crossbow_butterfly_crossbow");
        add("crossbow_auto_crossbow");
        add("crossbow_scatter_crossbow");
        add("crossbow_harp_crossbow");
        add("crossbow_lightning_harp_crossbow");
        add("crossbow_soul_crossbow");
        add("crossbow_feral_soul_crossbow");
        add("crossbow_voidcaller_crossbow");
        add("crossbow_dual_crossbow");
        add("crossbow_spellbound_crossbow");
        add("crossbow_baby_crossbow");
        add("crossbow_burst_crossbow");
        add("crossbow_soul_hunter_crossbow");
        add("crossbow_corrupted_crossbow");
        add("crossbow_cog_crossbow");
        add("crossbow_pride_of_the_piglins");
        add("crossbow_harpoon_crossbow");
        add("crossbow_nautical_crossbow");
        add("crossbow_shadow_crossbow");
        add("crossbow_veiled_crossbow");
    }};
    public static final HashSet<String> SHIELDS = new HashSet<String>(){{
        add("shield_royal_guard");
        add("shield_vanguard");
    }};

    public static Item getItem(String id) {
        return ITEMS.getOrDefault(id, Items.AIR);
    }

    public static void addItems() {
        //Other
        ITEMS.put("item_bee_stinger", new BeeStingerItem(new Item.Settings().group(ItemGroup.MISC).maxCount(64)));

        //Swords
        for (String itemID : SWORDS) {
            ITEMS.put(itemID, new McdwSword(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //AXES
        for (String itemID : AXES) {
            ITEMS.put(itemID, new McdwAxe(McdwStatsConfig.getMaterial(itemID), McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //DOUBLE AXES
        for (String itemID : DOUBLE_AXES) {
            ITEMS.put(itemID, new McdwDoubleAxe(McdwStatsConfig.getMaterial(itemID), McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //DAGGERS
        for (String itemID : DAGGERS) {
            ITEMS.put(itemID, new McdwDagger(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //SOUL DAGGERS
        for (String itemID : SOUL_DAGGERS) {
            ITEMS.put(itemID, new McdwSoulDagger(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //HAMMERS
        for (String itemID : HAMMERS) {
            ITEMS.put(itemID, new McdwHammer(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //GAUNTLETS
        for (String itemID : GAUNTLETS) {
            ITEMS.put(itemID, new McdwGauntlet(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //SICKLES
        for (String itemID : SICKLES) {
            ITEMS.put(itemID, new McdwSickle(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //SCYTHES
        for (String itemID : SCYTHES) {
            ITEMS.put(itemID, new McdwScythe(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //PICKS
        for (String itemID : PICKS) {
            ITEMS.put(itemID, new McdwPick(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //GLAIVES
        for (String itemID : GLAIVES) {
            ITEMS.put(itemID, new McdwGlaive(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //SPEARS
        for (String itemID : SPEARS) {
            ITEMS.put(itemID, new McdwSpear(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //STAVES
        for (String itemID : STAVES) {
            ITEMS.put(itemID, new McdwStaff(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //WHIPS
        for (String itemID : WHIPS) {
            ITEMS.put(itemID, new McdwWhip(McdwStatsConfig.getMaterial(itemID), (int) McdwStatsConfig.getDamage(itemID), McdwStatsConfig.getSpeed(itemID)));
        }
        //BOWS
        for (String itemID : BOWS) {
            ITEMS.put(itemID, new McdwBow(McdwStatsConfig.getMaterial(itemID), McdwStatsConfig.getDrawSpeed(itemID), McdwStatsConfig.getMaxRange(itemID)));
        }
        //SHORTBOWS
        for (String itemID : SHORTBOWS) {
            ITEMS.put(itemID, new McdwShortBow(McdwStatsConfig.getMaterial(itemID), McdwStatsConfig.getDrawSpeed(itemID), McdwStatsConfig.getMaxRange(itemID)));
        }
        //LONGBOWS
        for (String itemID : LONGBOWS) {
            ITEMS.put(itemID, new McdwLongBow(McdwStatsConfig.getMaterial(itemID),McdwStatsConfig.getDrawSpeed(itemID), McdwStatsConfig.getMaxRange(itemID)));
        }
        //CROSSBOWS
        for (String itemID : CROSSBOWS) {
            ITEMS.put(itemID, new McdwCrossbow(McdwStatsConfig.getMaterial(itemID), McdwStatsConfig.getDrawSpeed(itemID), McdwStatsConfig.getMaxRange(itemID)));
        }
        //SHIELDS
        for (String itemID : SHIELDS) {
            ITEMS.put(itemID, new McdwShield(McdwStatsConfig.getMaterial(itemID)));
        }

    }

    public static void registerItems() {
        ArrayList<String> IDs = new ArrayList<>(ITEMS.keySet());
        Collections.sort(IDs);
        for (String id : IDs) {
            Registry.register(Registry.ITEM, Mcdw.ID(id), ITEMS.get(id));
        }
    }

}
