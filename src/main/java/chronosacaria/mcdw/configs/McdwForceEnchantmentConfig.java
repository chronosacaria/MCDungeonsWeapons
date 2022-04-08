package chronosacaria.mcdw.configs;

import biom4st3r.mods.enchantment_force.ItemWithEnchantmentAssigner;
import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

public class McdwForceEnchantmentConfig {
    public static void forceEnchantments() {
        if (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {

            // Axe Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.axeItems.get(AxesID.AXE_FIREBRAND))
                    .setEnchantments(new Enchantment[]{Enchantments.FIRE_ASPECT});
            ((ItemWithEnchantmentAssigner) ItemsInit.axeItems.get(AxesID.AXE_ENCRUSTED_ANCHOR))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.GRAVITY, EnchantsRegistry.JUNGLE_POISON});
            ((ItemWithEnchantmentAssigner) ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.STUNNING});

            // Bow Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_BONEBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.GROWING});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BURSTER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RICOCHET});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_BURST_GALE_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CHARGE});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FUSE_SHOT, EnchantsRegistry.VOID_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_ECHO_OF_THE_VALLEY))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RICOCHET});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_ELITE_POWER_BOW))
                    .setEnchantments(new Enchantment[]{Enchantments.POWER});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_GREEN_MENACE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.POISON_CLOUD});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_HAUNTED_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.BONUS_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.REPLENISH});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS))
                    .setEnchantments(new Enchantment[]{Enchantments.MULTISHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_MASTERS_BOW))
                    .setEnchantments(new Enchantment[]{Enchantments.POWER});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_NOCTURNAL_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.TEMPO_THEFT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.PHANTOMS_MARK, Enchantments.POWER});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_SABREWING))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RADIANCE});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_SHIVERING_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.TEMPO_THEFT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_TWIN_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.BONUS_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_VOID_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.VOID_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.bowItems.get(BowsID.BOW_WEB_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.COBWEB_SHOT});

            ((ItemWithEnchantmentAssigner) ItemsInit.shortBowItems.get(ShortBowsID.BOW_LOVE_SPELL_BOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RADIANCE});

            ((ItemWithEnchantmentAssigner) ItemsInit.longBowItems.get(LongBowsID.BOW_RED_SNAKE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FUSE_SHOT});

            // Crossbow Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.BONUS_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.GROWING});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.BONUS_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW))
                    .setEnchantments(new Enchantment[]{Enchantments.PUNCH});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FUSE_SHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ENIGMA_RESONATOR});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CHAIN_REACTION});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW))
                    .setEnchantments(new Enchantment[]{Enchantments.MULTISHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.GRAVITY});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW))
                    .setEnchantments(new Enchantment[]{Enchantments.MULTISHOT, EnchantsRegistry.RICOCHET});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW))
                    .setEnchantments(new Enchantment[]{Enchantments.PIERCING});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS))
                    .setEnchantments(new Enchantment[]{Enchantments.PIERCING});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW))
                    .setEnchantments(new Enchantment[]{Enchantments.MULTISHOT});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RICOCHET});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_THE_SLICER))
                    .setEnchantments(new Enchantment[]{Enchantments.PIERCING});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ENIGMA_RESONATOR});
            ((ItemWithEnchantmentAssigner) ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.GRAVITY});

            // Dagger Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_BACKSTABBER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.AMBUSH});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.LEECHING});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.VOID_STRIKE});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_CHILL_GALE_KNIFE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FREEZING});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_FANGS_OF_FROST))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FREEZING});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_MOON))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ENIGMA_RESONATOR});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.COMMITTED, EnchantsRegistry.RUSHDOWN});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_SHEAR_DAGGER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SWIRLING});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_SWIFT_STRIKER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.AMBUSH, EnchantsRegistry.ECHO});
            ((ItemWithEnchantmentAssigner) ItemsInit.daggerItems.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.VOID_STRIKE});

            // Double Axe
            ((ItemWithEnchantmentAssigner) ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_CURSED))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.EXPLODING});
            ((ItemWithEnchantmentAssigner) ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_WHIRLWIND))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SHOCKWAVE});

            // Gauntlet Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_MAULERS))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RAMPAGING});
            ((ItemWithEnchantmentAssigner) ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_SOUL_FISTS))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ENIGMA_RESONATOR});

            // Glaive Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SMITING});
            ((ItemWithEnchantmentAssigner) ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GRAVE_BANE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SMITING});
            ((ItemWithEnchantmentAssigner) ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.POISON_CLOUD});

            // Hammer Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.hammerItems.get(HammersID.HAMMER_BONE_CUDGEL))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ILLAGERS_BANE});
            ((ItemWithEnchantmentAssigner) ItemsInit.hammerItems.get(HammersID.HAMMER_FLAIL))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CHAINS});
            ((ItemWithEnchantmentAssigner) ItemsInit.hammerItems.get(HammersID.HAMMER_GRAVITY))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.GRAVITY});
            ((ItemWithEnchantmentAssigner) ItemsInit.hammerItems.get(HammersID.HAMMER_STORMLANDER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.THUNDERING});
            ((ItemWithEnchantmentAssigner) ItemsInit.hammerItems.get(HammersID.HAMMER_SUNS_GRACE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RADIANCE});

            // Sickle Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.PROSPECTOR});
            ((ItemWithEnchantmentAssigner) ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.PROSPECTOR});
            ((ItemWithEnchantmentAssigner) ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.POISON_CLOUD});

            // Scythe Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.scytheItems.get(ScythesID.SICKLE_FROST_SCYTHE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FREEZING});
            ((ItemWithEnchantmentAssigner) ItemsInit.scytheItems.get(ScythesID.SICKLE_JAILORS_SCYTHE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CHAINS});
            ((ItemWithEnchantmentAssigner) ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FREEZING});
            ((ItemWithEnchantmentAssigner) ItemsInit.scytheItems.get(ScythesID.SICKLE_SOUL_SCYTHE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SOUL_DEVOURER});

            // Soul Dagger Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SOUL_SIPHON});
            ((ItemWithEnchantmentAssigner) ItemsInit.soulDaggerItems.get(SoulDaggersID.SWORD_TRUTHSEEKER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.COMMITTED});

            // Spear Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.spearItems.get(SpearsID.SPEAR_FORTUNE))
                    .setEnchantments(new Enchantment[]{Enchantments.LOOTING});
            ((ItemWithEnchantmentAssigner) ItemsInit.spearItems.get(SpearsID.SPEAR_WHISPERING_SPEAR))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ECHO});

            // Staff Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.EXPLODING});
            ((ItemWithEnchantmentAssigner) ItemsInit.staffItems.get(StavesID.STAFF_GROWING_STAFF))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.COMMITTED});

            // Sword Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_BROADSWORD))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SWIRLING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_DANCERS_SWORD))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.RAMPAGING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_DARK_KATANA))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.SMITING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_FREEZING_FOIL))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FREEZING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_FROST_SLAYER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.FREEZING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_HAWKBRAND))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CRITICAL_HIT});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_HEARTSTEALER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.LEECHING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_MASTERS_KATANA))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CRITICAL_HIT});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.WEAKENING});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_SINISTER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.CRITICAL_HIT});
            ((ItemWithEnchantmentAssigner) ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.ENIGMA_RESONATOR});

            // Whip Enchantments
            ((ItemWithEnchantmentAssigner) ItemsInit.whipItems.get(WhipsID.WHIP_VINE_WHIP))
                    .setEnchantments(new Enchantment[]{EnchantsRegistry.JUNGLE_POISON});
        }
    }
}
