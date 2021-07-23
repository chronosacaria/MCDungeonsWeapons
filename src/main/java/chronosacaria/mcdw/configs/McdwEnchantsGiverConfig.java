package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wraith.enchant_giver.EnchantsList;

public class McdwEnchantsGiverConfig {
    public static void appendEnchants(){
        if (McdwEnchantsConfig.getValue("enable_innate_enchants")) {
            // BONUS SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_auto_crossbow")),
                    new Identifier("mcdw:bonus_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_butterfly_crossbow")),
                    new Identifier("mcdw:bonus_shot"), 1, true);
            // CHAINS ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_flail")),
                    new Identifier("mcdw:chains"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_jailors_scythe")),
                    new Identifier("mcdw:chains"), 1, true);

            // CHAIN REACTION ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_firebolt_thrower")),
                    new Identifier("mcdw:chain_reaction"), 1, true);

            // CHARGE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_burst_gale_bow")),
                    new Identifier("mcdw:charge"), 1, true);

            // COMMITTED ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_truthseeker")),
                    new Identifier("mcdw:committed"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("staff_growing_staff")),
                    new Identifier("mcdw:committed"), 1, true);

            // CRITICAL HIT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_hawkbrand")),
                    new Identifier("mcdw:critical_hit"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_masters_katana")),
                    new Identifier("mcdw:critical_hit"), 1, true);

            // ECHO ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_whispering_spear")),
                    new Identifier("mcdw:echo"), 1, true);

            // ENIGMA RESONATOR ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_moon")),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("gauntlet_soul_fists")),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_sponge_striker")),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_feral_soul_crossbow")),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_soul_hunter_crossbow")),
                    new Identifier("mcdw:enigma_resonator"), 1, true);

            // EXPLODING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_cursed")),
                    new Identifier("mcdw:exploding"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("staff_battlestaff_of_terror")),
                    new Identifier("mcdw:exploding"), 1, true);

            // FIRE ASPECT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_firebrand")),
                    new Identifier("minecraft:fire_aspect"), 1, true);

            // FREEZING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_fangs_of_frost")),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_frost_scythe")),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_freezing_foil")),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_chill_gale_knife")),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_frost_slayer")),
                    new Identifier("mcdw:freezing"), 1, true);

            // FUSE SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_exploding_crossbow")),
                    new Identifier("mcdw:fuse_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_red_snake")),
                    new Identifier("mcdw:fuse_shot"), 1, true);

            // GRAVITY ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_gravity")),
                    new Identifier("mcdw:gravity"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_imploding_crossbow")),
                    new Identifier("mcdw:gravity"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_voidcaller_crossbow")),
                    new Identifier("mcdw:gravity"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_encrusted_anchor")),
                    new Identifier("mcdw:gravity"), 1, true);

            // GROWING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_bonebow")),
                    new Identifier("mcdw:growing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_baby_crossbow")),
                    new Identifier("mcdw:growing"), 1, true);

            // ILLAGER'S BANE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_bone_cudgel")),
                    new Identifier("mcdw:illagers_bane"), 1, true);

            // JUNGLE'S POISON ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("whip_vine_whip")),
                    new Identifier("mcdw:jungle_poison"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_encrusted_anchor")),
                    new Identifier("mcdw:jungle_poison"), 1, false);

            // LEECHING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_heartstealer")),
                    new Identifier("mcdw:leeching"), 1, true);

            // LOOTING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_fortune")),
                    new Identifier("minecraft:looting"), 1, true);

            // MULTISHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_lost_souls")),
                    new Identifier("minecraft:multishot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_harp_crossbow")),
                    new Identifier("minecraft:multishot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_scatter_crossbow")),
                    new Identifier("minecraft:multishot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_lightning_harp_crossbow")),
                    new Identifier("minecraft:multishot"), 1, true);

            // PIERCING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_the_slicer")),
                    new Identifier("minecraft:piercing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_pride_of_the_piglins")),
                    new Identifier("minecraft:piercing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_nautical_crossbow")),
                    new Identifier("minecraft:piercing"), 1, true);

            // POISON CLOUD ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_nightmares_bite")),
                    new Identifier("mcdw:poison_cloud"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_venom_glaive")),
                    new Identifier("mcdw:poison_cloud"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_green_menace")),
                    new Identifier("mcdw:poison_cloud"), 1, true);

            // POWER ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_masters_bow")),
                    new Identifier("minecraft:power"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_elite_power_bow")),
                    new Identifier("minecraft:power"), 1, true);

            // PROSPECTOR ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_last_laugh_gold")),
                    new Identifier("mcdw:prospector"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_last_laugh_silver")),
                    new Identifier("mcdw:prospector"), 1, true);

            // PUNCH ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_doom_crossbow")),
                    new Identifier("minecraft:punch"), 1, true);

            // RADIANCE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_suns_grace")),
                    new Identifier("mcdw:radiance"), 1, true);

            // RADIANCE SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_sabrewing")),
                    new Identifier("mcdw:radiance_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_love_spell_bow")),
                    new Identifier("mcdw:radiance_shot"), 1, true);

            // RAMPAGING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_dancers_sword")),
                    new Identifier("mcdw:rampaging"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("gauntlet_maulers")),
                    new Identifier("mcdw:rampaging"), 1, true);

            // REPLENISH ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_hunters_promise")),
                    new Identifier("mcdw:replenish"), 1, true);

            // RICOCHET ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_lightning_harp_crossbow")),
                    new Identifier("mcdw:ricochet"), 1, false);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_slayer_crossbow")),
                    new Identifier("mcdw:ricochet"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_echo_of_the_valley")),
                    new Identifier("mcdw:ricochet"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_bubble_burster")),
                    new Identifier("mcdw:ricochet"), 1, true);

            // RUSHDOWN ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_resolute_tempest_knife")),
                    new Identifier("mcdw:rushdown"), 1, true);

            // SHOCKWAVE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_whirlwind")),
                    new Identifier("mcdw:shockwave"), 1, true);

            // SMITING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_grave_bane")),
                    new Identifier("mcdw:smiting"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_dark_katana")),
                    new Identifier("mcdw:smiting"), 1, true);

            // SOUL SIPHON ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_eternal_knife")),
                    new Identifier("mcdw:soul_siphon"), 1, true);

            // STUNNING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_highland")),
                    new Identifier("mcdw:stunning"), 1, true);

            // SWIRLING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_shear_dagger")),
                    new Identifier("mcdw:swirling"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_broadsword")),
                    new Identifier("mcdw:swirling"), 1, true);

            // TEMPO THEFT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_nocturnal_bow")),
                    new Identifier("mcdw:tempo_theft"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_shivering_bow")),
                    new Identifier("mcdw:tempo_theft"), 1, true);

            // THUNDERING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_stormlander")),
                    new Identifier("mcdw:thundering"), 1, true);

            // WEAKENING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_nameless_blade")),
                    new Identifier("mcdw:weakening"), 1, true);
        }
    }
}