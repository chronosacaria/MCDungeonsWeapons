package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wraith.enchant_giver.EnchantsList;

import java.util.HashMap;

public class McdwEnchantsGiverConfig {

    public static void createConfig(){
        HashMap<Identifier, HashMap<Identifier, Integer>> enchantmentgiverconfig = new HashMap<Identifier, HashMap<Identifier, Integer>>(){{

            // CHAINS ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_flail")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:chained"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_jailors_scythe")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:chained"), 1);
            }});

            // CHARGE ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_burst_gale_bow")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:charge"), 1);
            }});

            // COMMITTED ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_truthseeker")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:committed"), 1);
                    }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("staff_growing_staff")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:committed"), 1);
                    }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_resolute_tempest_knife")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:committed"), 1);
                    }});

            // CRITICAL HIT ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_hawkbrand")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:critical_hit"), 1);
                    }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_masters_katana")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:critical_hit"), 1);
                    }});

            // ECHO ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_whispering_spear")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:echo"), 1);
                    }});

            // ENIGMA RESONATOR ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_moon")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:enigma_resonator"), 1);
                    }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("gauntlet_soul_fists")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:enigma_resonator"), 1);
                    }});

            // EXPLODING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_cursed")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:exploding"), 1);
                    }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("staff_battlestaff_of_terror")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:exploding"), 1);
                    }});

            // FIRE ASPECT ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_firebrand")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("minecraft:fire_aspect"), 1);
                    }});

            // FREEZING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_fangs_of_frost")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:freezing"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_frost_scythe")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:freezing"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_freezing_foil")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:freezing"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_chill_gale_knife")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:freezing"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_frost_slayer")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:freezing"), 1);
            }});

            // FUSE SHOT ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_exploding_crossbow")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:fuse_shot"), 1);
            }});

            // GRAVITY ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_gravity")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:gravity"), 1);
            }});

            // JUNGLE'S POISON ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("whip_vine_whip")), new HashMap<Identifier, Integer>(){{
                        put(new Identifier("mcdw:jungle_poison"), 1);
                    }});

            // LEECHING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_heartstealer")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:leeching"), 1);
            }});

            // LOOTING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_fortune")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("minecraft:looting"), 1);
            }});

            // POISON CLOUD ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sickle_nightmares_bite")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:poison_cloud"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_venom_glaive")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:poison_cloud"), 1);
            }});

            // RADIANCE ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_suns_grace")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:radiance"), 1);
            }});

            // RAMPAGING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_dancers_sword")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:rampaging"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("gauntlet_maulers")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:rampaging"), 1);
            }});

            // REPLENISH ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_hunters_promise")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:replenish"), 1);
            }});

            // RICOCHET ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_lightning_harp_crossbow")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:ricochet"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("crossbow_slayer_crossbow")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:ricochet"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_echo_of_the_valley")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:ricochet"), 1);
            }});

            // SHOCKWAVE ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_whirlwind")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:shockwave"), 1);
            }});

            // SMITING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("spear_grave_bane")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:smiting"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_dark_katana")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:smiting"), 1);
            }});

            // SOUL SIPHON ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_eternal_knife")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:soul_siphon"), 1);
            }});

            // STUNNING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("axe_highland")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:stunning"), 1);
            }});

            // SWIRLING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("dagger_shear_dagger")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:swirling"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_broadsword")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:swirling"), 1);
            }});

            // TEMPO THEFT ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_nocturnal_bow")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:tempo_theft"), 1);
            }});
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("bow_shivering_bow")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:tempo_theft"), 1);
            }});

            // THUNDERING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("hammer_stormlander")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:thundering"), 1);
            }});

            // WEAKENING ENCHANTMENT
            put(Registry.ITEM.getId(ItemRegistry.ITEMS.get("sword_nameless_blade")), new HashMap<Identifier, Integer>(){{
                put(new Identifier("mcdw:weakening"), 1);
            }});

        }};
        EnchantsList.createConfig(Mcdw.MOD_ID, enchantmentgiverconfig, false);

    }
}