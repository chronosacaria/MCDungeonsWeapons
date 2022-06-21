package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wraith.enchant_giver.EnchantsList;

public class McdwEnchantGiverConfig {
    public static void appendEnchants(){

        if (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {
            // AMBUSH ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_BACKSTABBER)),
                    new Identifier("mcdw:ambush"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_SWIFT_STRIKER)),
                    new Identifier("mcdw:ambush"), 1, false);

            // BONUS SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW)),
                    new Identifier("mcdw:bonus_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW)),
                    new Identifier("mcdw:bonus_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_HAUNTED_BOW)),
                    new Identifier("minecraft:bonus_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_TWIN_BOW)),
                    new Identifier("minecraft:bonus_shot"), 1, true);

            // CHAINS ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.hammerItems.get(HammersID.HAMMER_FLAIL)),
                    new Identifier("mcdw:chains"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.scytheItems.get(ScythesID.SICKLE_JAILORS_SCYTHE)),
                    new Identifier("mcdw:chains"), 1, true);

            // CHAIN REACTION ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER)),
                    new Identifier("mcdw:chain_reaction"), 1, true);

            // CHARGE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_BURST_GALE_BOW)),
                    new Identifier("mcdw:charge"), 1, true);

            // COBWEB SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_WEB_BOW)),
                    new Identifier("mcdw:cobweb_shot"), 1, true);

            // COMMITTED ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.soulDaggerItems.get(SoulDaggersID.SWORD_TRUTHSEEKER)),
                    new Identifier("mcdw:committed"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.staffItems.get(StavesID.STAFF_GROWING_STAFF)),
                    new Identifier("mcdw:committed"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE)),
                    new Identifier("mcdw:committed"), 1, false);

            // CRITICAL HIT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_HAWKBRAND)),
                    new Identifier("mcdw:critical_hit"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_SINISTER)),
                    new Identifier("mcdw:critical_hit"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_MASTERS_KATANA)),
                    new Identifier("mcdw:critical_hit"), 1, true);

            // DYNAMO ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_GREAT_AXEBLADE)),
                    new Identifier("mcdw:dynamo"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW)),
                    new Identifier("mcdw:dynamo"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_ANCIENT_BOW)),
                    new Identifier("mcdw:dynamo"), 1, true);

            // ECHO ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.spearItems.get(SpearsID.SPEAR_WHISPERING_SPEAR)),
                    new Identifier("mcdw:echo"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_SWIFT_STRIKER)),
                    new Identifier("mcdw:echo"), 1, false);

            // ENIGMA RESONATOR ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_MOON)),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_SOUL_FISTS)),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER)),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW)),
                    new Identifier("mcdw:enigma_resonator"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW)),
                    new Identifier("mcdw:enigma_resonator"), 1, true);

            // EXPLODING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_CURSED)),
                    new Identifier("mcdw:exploding"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR)),
                    new Identifier("mcdw:exploding"), 1, true);

            // FIRE ASPECT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.axeItems.get(AxesID.AXE_FIREBRAND)),
                    new Identifier("minecraft:fire_aspect"), 1, true);

            // FREEZING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_FANGS_OF_FROST)),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.scytheItems.get(ScythesID.SICKLE_FROST_SCYTHE)),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_FREEZING_FOIL)),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_CHILL_GALE_KNIFE)),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_FROST_SLAYER)),
                    new Identifier("mcdw:freezing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE)),
                    new Identifier("mcdw:freezing"), 1, true);

            // FUSE SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW)),
                    new Identifier("mcdw:fuse_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.longBowItems.get(LongBowsID.BOW_RED_SNAKE)),
                    new Identifier("mcdw:fuse_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID)),
                    new Identifier("mcdw:fuse_shot"), 1, false);

            // GRAVITY ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.hammerItems.get(HammersID.HAMMER_GRAVITY)),
                    new Identifier("mcdw:gravity"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW)),
                    new Identifier("mcdw:gravity"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW)),
                    new Identifier("mcdw:gravity"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.axeItems.get(AxesID.AXE_ENCRUSTED_ANCHOR)),
                    new Identifier("mcdw:gravity"), 1, true);

            // GROWING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_BONEBOW)),
                    new Identifier("mcdw:growing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW)),
                    new Identifier("mcdw:growing"), 1, true);

            // ILLAGER'S BANE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.hammerItems.get(HammersID.HAMMER_BONE_CUDGEL)),
                    new Identifier("mcdw:illagers_bane"), 1, true);

            // JUNGLE'S POISON ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.whipItems.get(WhipsID.WHIP_VINE_WHIP)),
                    new Identifier("mcdw:jungle_poison"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.axeItems.get(AxesID.AXE_ENCRUSTED_ANCHOR)),
                    new Identifier("mcdw:jungle_poison"), 1, false);

            // LEECHING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_HEARTSTEALER)),
                    new Identifier("mcdw:leeching"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING)),
                    new Identifier("mcdw:leeching"), 1, true);

            // LOOTING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.spearItems.get(SpearsID.SPEAR_FORTUNE)),
                    new Identifier("minecraft:looting"), 1, true);

            // MULTISHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS)),
                    new Identifier("minecraft:multishot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW)),
                    new Identifier("minecraft:multishot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW)),
                    new Identifier("minecraft:multishot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)),
                    new Identifier("minecraft:multishot"), 1, true);

            // PHANTOM'S MARK ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW)),
                    new Identifier("mcdw:phantoms_mark"), 1, true);

            // PIERCING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_THE_SLICER)),
                    new Identifier("minecraft:piercing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS)),
                    new Identifier("minecraft:piercing"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)),
                    new Identifier("minecraft:piercing"), 1, true);

            // POISON CLOUD ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE)),
                    new Identifier("mcdw:poison_cloud"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE)),
                    new Identifier("mcdw:poison_cloud"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_GREEN_MENACE)),
                    new Identifier("mcdw:poison_cloud"), 1, true);

            // POWER ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_MASTERS_BOW)),
                    new Identifier("minecraft:power"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_ELITE_POWER_BOW)),
                    new Identifier("minecraft:power"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW)),
                    new Identifier("minecraft:power"), 1, false);

            // PROSPECTOR ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD)),
                    new Identifier("mcdw:prospector"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER)),
                    new Identifier("mcdw:prospector"), 1, true);

            // PUNCH ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW)),
                    new Identifier("minecraft:punch"), 1, true);

            // RADIANCE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.hammerItems.get(HammersID.HAMMER_SUNS_GRACE)),
                    new Identifier("mcdw:radiance"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_SABREWING)),
                    new Identifier("mcdw:radiance"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.shortBowItems.get(ShortBowsID.BOW_LOVE_SPELL_BOW)),
                    new Identifier("mcdw:radiance"), 1, true);

            // RAMPAGING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_DANCERS_SWORD)),
                    new Identifier("mcdw:rampaging"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_MAULERS)),
                    new Identifier("mcdw:rampaging"), 1, true);

            // REPLENISH ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE)),
                    new Identifier("mcdw:replenish"), 1, true);

            // RICOCHET ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW)),
                    new Identifier("mcdw:ricochet"), 1, false);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW)),
                    new Identifier("mcdw:ricochet"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_ECHO_OF_THE_VALLEY)),
                    new Identifier("mcdw:ricochet"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BURSTER)),
                    new Identifier("mcdw:ricochet"), 1, true);

            // RUSHDOWN ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE)),
                    new Identifier("mcdw:rushdown"), 1, false);

            // SHOCKWAVE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_WHIRLWIND)),
                    new Identifier("mcdw:shockwave"), 1, true);

            // SMITING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GRAVE_BANE)),
                    new Identifier("mcdw:smiting"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_DARK_KATANA)),
                    new Identifier("mcdw:smiting"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM)),
                    new Identifier("mcdw:smiting"), 1, true);

            // SOUL DEVOURER ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.scytheItems.get(ScythesID.SICKLE_SOUL_SCYTHE)),
                    new Identifier("mcdw:soul_devourer"), 1, true);

            // SOUL SIPHON ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE)),
                    new Identifier("mcdw:soul_siphon"), 1, true);

            // STUNNING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND)),
                    new Identifier("mcdw:stunning"), 1, true);

            // SWIRLING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_SHEAR_DAGGER)),
                    new Identifier("mcdw:swirling"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_BROADSWORD)),
                    new Identifier("mcdw:swirling"), 1, true);

            // TEMPO THEFT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_NOCTURNAL_BOW)),
                    new Identifier("mcdw:tempo_theft"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_SHIVERING_BOW)),
                    new Identifier("mcdw:tempo_theft"), 1, true);

            // THUNDERING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.hammerItems.get(HammersID.HAMMER_STORMLANDER)),
                    new Identifier("mcdw:thundering"), 1, true);

            // VOID SHOT ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_VOID_BOW)),
                    new Identifier("mcdw:void_shot"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID)),
                    new Identifier("mcdw:void_shot"), 1, false);

            // VOID STRIKE ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE)),
                    new Identifier("mcdw:void_strike"), 1, true);
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END)),
                    new Identifier("mcdw:void_strike"), 1, true);

            // WEAKENING ENCHANTMENT
            EnchantsList.addEnchant(Registry.ITEM.getId(ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE)),
                    new Identifier("mcdw:weakening"), 1, true);
        }
    }
}
