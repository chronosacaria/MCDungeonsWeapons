package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wraith.enchant_giver.EnchantsList;

import java.util.Arrays;

public class McdwEnchantGiverConfig {
    public static void appendEnchants(){

        if (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {
            // ACCELERATE ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_AUTO_CROSSBOW, EnchantsRegistry.ACCELERATE, true);
            addInnateEnchant(ShortBowsID.BOW_MECHANICAL_SHORTBOW, EnchantsRegistry.ACCELERATE, true);

            // AMBUSH ENCHANTMENT
            addInnateEnchant(DaggersID.DAGGER_BACKSTABBER, EnchantsRegistry.AMBUSH, true);
            addInnateEnchant(DaggersID.DAGGER_SWIFT_STRIKER, EnchantsRegistry.AMBUSH, false);

            // BONUS SHOT ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_AUTO_CROSSBOW, EnchantsRegistry.BONUS_SHOT, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW, EnchantsRegistry.BONUS_SHOT, true);
            addInnateEnchant(BowsID.BOW_HAUNTED_BOW, EnchantsRegistry.BONUS_SHOT, true);
            addInnateEnchant(BowsID.BOW_TWIN_BOW, EnchantsRegistry.BONUS_SHOT, true);

            // CHAINS ENCHANTMENT
            addInnateEnchant(HammersID.HAMMER_FLAIL, EnchantsRegistry.CHAINS, true);
            addInnateEnchant(ScythesID.SICKLE_JAILORS_SCYTHE, EnchantsRegistry.CHAINS, true);

            // CHAIN REACTION ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_FIREBOLT_THROWER, EnchantsRegistry.CHAIN_REACTION, true);

            // CHARGE ENCHANTMENT
            addInnateEnchant(BowsID.BOW_BURST_GALE_BOW, EnchantsRegistry.CHARGE, true);

            // COBWEB SHOT ENCHANTMENT
            addInnateEnchant(BowsID.BOW_WEB_BOW, EnchantsRegistry.COBWEB_SHOT, true);

            // COMMITTED ENCHANTMENT
            addInnateEnchant(SoulDaggersID.SWORD_TRUTHSEEKER, EnchantsRegistry.COMMITTED, true);
            addInnateEnchant(StavesID.STAFF_GROWING_STAFF, EnchantsRegistry.COMMITTED, true);
            addInnateEnchant(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, EnchantsRegistry.COMMITTED, false);

            // CRITICAL HIT ENCHANTMENT
            addInnateEnchant(SwordsID.SWORD_HAWKBRAND, EnchantsRegistry.CRITICAL_HIT, true);
            addInnateEnchant(SwordsID.SWORD_SINISTER, EnchantsRegistry.CRITICAL_HIT, true);
            addInnateEnchant(SwordsID.SWORD_MASTERS_KATANA, EnchantsRegistry.CRITICAL_HIT, true);

            // DYNAMO ENCHANTMENT
            addInnateEnchant(SwordsID.SWORD_GREAT_AXEBLADE, EnchantsRegistry.DYNAMO, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW, EnchantsRegistry.DYNAMO, true);
            addInnateEnchant(BowsID.BOW_ANCIENT_BOW, EnchantsRegistry.DYNAMO, true);

            // ECHO ENCHANTMENT
            addInnateEnchant(SpearsID.SPEAR_WHISPERING_SPEAR, EnchantsRegistry.ECHO, true);
            addInnateEnchant(DaggersID.DAGGER_SWIFT_STRIKER, EnchantsRegistry.ECHO, false);

            // ENIGMA RESONATOR ENCHANTMENT
            addInnateEnchant(DaggersID.DAGGER_MOON, EnchantsRegistry.ENIGMA_RESONATOR, true);
            addInnateEnchant(GauntletsID.GAUNTLET_SOUL_FISTS, EnchantsRegistry.ENIGMA_RESONATOR, true);
            addInnateEnchant(SwordsID.SWORD_SPONGE_STRIKER, EnchantsRegistry.ENIGMA_RESONATOR, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW, EnchantsRegistry.ENIGMA_RESONATOR, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW, EnchantsRegistry.ENIGMA_RESONATOR, true);

            // EXPLODING ENCHANTMENT
            addInnateEnchant(DoubleAxesID.AXE_CURSED, EnchantsRegistry.EXPLODING, true);
            addInnateEnchant(StavesID.STAFF_BATTLESTAFF_OF_TERROR, EnchantsRegistry.EXPLODING, true);

            // FIRE ASPECT ENCHANTMENT
            addInnateEnchant(AxesID.AXE_FIREBRAND, Enchantments.FIRE_ASPECT, true);

            // FREEZING ENCHANTMENT
            addInnateEnchant(DaggersID.DAGGER_FANGS_OF_FROST, EnchantsRegistry.FREEZING, true);
            addInnateEnchant(ScythesID.SICKLE_FROST_SCYTHE, EnchantsRegistry.FREEZING, true);
            addInnateEnchant(SwordsID.SWORD_FREEZING_FOIL, EnchantsRegistry.FREEZING, true);
            addInnateEnchant(DaggersID.DAGGER_CHILL_GALE_KNIFE, EnchantsRegistry.FREEZING, true);
            addInnateEnchant(SwordsID.SWORD_FROST_SLAYER, EnchantsRegistry.FREEZING, true);
            addInnateEnchant(ScythesID.SICKLE_SKULL_SCYTHE, EnchantsRegistry.FREEZING, true);

            // FUSE SHOT ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW, EnchantsRegistry.FUSE_SHOT, true);
            addInnateEnchant(LongBowsID.BOW_RED_SNAKE, EnchantsRegistry.FUSE_SHOT, true);
            addInnateEnchant(BowsID.BOW_CALL_OF_THE_VOID, EnchantsRegistry.FUSE_SHOT, false);

            // GRAVITY ENCHANTMENT
            addInnateEnchant(HammersID.HAMMER_GRAVITY, EnchantsRegistry.GRAVITY, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW, EnchantsRegistry.GRAVITY, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW, EnchantsRegistry.GRAVITY, true);
            addInnateEnchant(AxesID.AXE_ENCRUSTED_ANCHOR, EnchantsRegistry.GRAVITY, true);

            // GROWING ENCHANTMENT
            addInnateEnchant(BowsID.BOW_BONEBOW, EnchantsRegistry.GROWING, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_BABY_CROSSBOW, EnchantsRegistry.GROWING, true);

            // ILLAGER'S BANE ENCHANTMENT
            addInnateEnchant(HammersID.HAMMER_BONE_CUDGEL, EnchantsRegistry.ILLAGERS_BANE, true);

            // JUNGLE'S POISON ENCHANTMENT
            addInnateEnchant(WhipsID.WHIP_VINE_WHIP, EnchantsRegistry.JUNGLE_POISON, true);
            addInnateEnchant(AxesID.AXE_ENCRUSTED_ANCHOR, EnchantsRegistry.JUNGLE_POISON, false);

            // LEECHING ENCHANTMENT
            addInnateEnchant(SwordsID.SWORD_HEARTSTEALER, EnchantsRegistry.LEECHING, true);
            addInnateEnchant(DaggersID.DAGGER_THE_BEGINNING, EnchantsRegistry.LEECHING, true);

            // LOOTING ENCHANTMENT
            addInnateEnchant(SpearsID.SPEAR_FORTUNE, Enchantments.LOOTING, true);

            // MULTISHOT ENCHANTMENT
            addInnateEnchant(BowsID.BOW_LOST_SOULS, Enchantments.MULTISHOT, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_HARP_CROSSBOW, Enchantments.MULTISHOT, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW, Enchantments.MULTISHOT, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, Enchantments.MULTISHOT, true);

            // PHANTOM'S MARK ENCHANTMENT
            addInnateEnchant(BowsID.BOW_PHANTOM_BOW, EnchantsRegistry.PHANTOMS_MARK, true);

            // PIERCING ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_THE_SLICER, Enchantments.PIERCING, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS, Enchantments.PIERCING, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW, Enchantments.PIERCING, true);

            // POISON CLOUD ENCHANTMENT
            addInnateEnchant(SicklesID.SICKLE_NIGHTMARES_BITE, EnchantsRegistry.POISON_CLOUD, true);
            addInnateEnchant(GlaivesID.SPEAR_VENOM_GLAIVE, EnchantsRegistry.POISON_CLOUD, true);
            addInnateEnchant(BowsID.BOW_GREEN_MENACE, EnchantsRegistry.POISON_CLOUD, true);

            // POWER ENCHANTMENT
            addInnateEnchant(BowsID.BOW_MASTERS_BOW, Enchantments.POWER, true);
            addInnateEnchant(BowsID.BOW_ELITE_POWER_BOW, Enchantments.POWER, true);
            addInnateEnchant(BowsID.BOW_PHANTOM_BOW, Enchantments.POWER, false);

            // PROSPECTOR ENCHANTMENT
            addInnateEnchant(SicklesID.SICKLE_LAST_LAUGH_GOLD, EnchantsRegistry.PROSPECTOR, true);
            addInnateEnchant(SicklesID.SICKLE_LAST_LAUGH_SILVER, EnchantsRegistry.PROSPECTOR, true);

            // PUNCH ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_DOOM_CROSSBOW, Enchantments.PUNCH, true);

            // RADIANCE ENCHANTMENT
            addInnateEnchant(HammersID.HAMMER_SUNS_GRACE, EnchantsRegistry.RADIANCE, true);
            addInnateEnchant(BowsID.BOW_SABREWING, EnchantsRegistry.RADIANCE, true);
            addInnateEnchant(ShortBowsID.BOW_LOVE_SPELL_BOW, EnchantsRegistry.RADIANCE, true);

            // RAMPAGING ENCHANTMENT
            addInnateEnchant(SwordsID.SWORD_DANCERS_SWORD, EnchantsRegistry.RAMPAGING, true);
            addInnateEnchant(GauntletsID.GAUNTLET_MAULERS, EnchantsRegistry.RAMPAGING, true);

            // REPLENISH ENCHANTMENT
            addInnateEnchant(BowsID.BOW_HUNTERS_PROMISE, EnchantsRegistry.REPLENISH, true);

            // RICOCHET ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW, EnchantsRegistry.RICOCHET, false);
            addInnateEnchant(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW, EnchantsRegistry.RICOCHET, true);
            addInnateEnchant(BowsID.BOW_ECHO_OF_THE_VALLEY, EnchantsRegistry.RICOCHET, true);
            addInnateEnchant(BowsID.BOW_BUBBLE_BURSTER, EnchantsRegistry.RICOCHET, true);
            addInnateEnchant(BowsID.BOW_PINK_SCOUNDREL, EnchantsRegistry.RICOCHET, false);

            // RUSHDOWN ENCHANTMENT
            addInnateEnchant(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, EnchantsRegistry.RUSHDOWN, false);

            // SHADOW BARB ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_VEILED_CROSSBOW, EnchantsRegistry.SHADOW_BARB, false);

            // SHADOW SHOT ENCHANTMENT
            addInnateEnchant(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW, EnchantsRegistry.SHADOW_SHOT, true);
            addInnateEnchant(CrossbowsID.CROSSBOW_VEILED_CROSSBOW, EnchantsRegistry.SHADOW_SHOT, true);

            // SHOCKWAVE ENCHANTMENT
            addInnateEnchant(DoubleAxesID.AXE_WHIRLWIND, EnchantsRegistry.SHOCKWAVE, true);

            // SMITING ENCHANTMENT
            addInnateEnchant(GlaivesID.SPEAR_GRAVE_BANE, EnchantsRegistry.SMITING, true);
            addInnateEnchant(SwordsID.SWORD_DARK_KATANA, EnchantsRegistry.SMITING, true);
            addInnateEnchant(GlaivesID.SPEAR_CACKLING_BROOM, EnchantsRegistry.SMITING, true);

            // SOUL DEVOURER ENCHANTMENT
            addInnateEnchant(ScythesID.SICKLE_SOUL_SCYTHE, EnchantsRegistry.SOUL_DEVOURER, true);

            // SOUL SIPHON ENCHANTMENT
            addInnateEnchant(SoulDaggersID.DAGGER_ETERNAL_KNIFE, EnchantsRegistry.SOUL_SIPHON, true);

            // STUNNING ENCHANTMENT
            addInnateEnchant(AxesID.AXE_HIGHLAND, EnchantsRegistry.STUNNING, true);

            // SWIRLING ENCHANTMENT
            addInnateEnchant(DaggersID.DAGGER_SHEAR_DAGGER, EnchantsRegistry.SWIRLING, true);
            addInnateEnchant(SwordsID.SWORD_BROADSWORD, EnchantsRegistry.SWIRLING, true);

            // TEMPO THEFT ENCHANTMENT
            addInnateEnchant(BowsID.BOW_NOCTURNAL_BOW, EnchantsRegistry.TEMPO_THEFT, true);
            addInnateEnchant(BowsID.BOW_SHIVERING_BOW, EnchantsRegistry.TEMPO_THEFT, true);

            // THUNDERING ENCHANTMENT
            addInnateEnchant(HammersID.HAMMER_STORMLANDER, EnchantsRegistry.THUNDERING, true);

            // VOID SHOT ENCHANTMENT
            addInnateEnchant(BowsID.BOW_VOID_BOW, EnchantsRegistry.VOID_SHOT, true);
            addInnateEnchant(BowsID.BOW_CALL_OF_THE_VOID, EnchantsRegistry.VOID_SHOT, false);

            // VOID STRIKE ENCHANTMENT
            addInnateEnchant(DaggersID.DAGGER_VOID_TOUCHED_BLADE, EnchantsRegistry.VOID_STRIKE, true);
            addInnateEnchant(DaggersID.DAGGER_THE_END, EnchantsRegistry.VOID_STRIKE, true);

            // WEAKENING ENCHANTMENT
            addInnateEnchant(SwordsID.SWORD_NAMELESS_BLADE, EnchantsRegistry.WEAKENING, true);

            // WILD RAGE ENCHANTMENT
            addInnateEnchant(BowsID.BOW_PINK_SCOUNDREL, EnchantsRegistry.WILD_RAGE, true);
        }
    }

    private static void addInnateEnchant(McdwWeaponID weaponID, Enchantment enchantment, Boolean replace) {
        addInnateEnchant(weaponID.getItem(), enchantment, replace);
    }

    private static void addInnateEnchant(Item item, Enchantment enchantment, Boolean replace) {
        addInnateEnchant(item, enchantment, 1, replace);
    }

    private static void addInnateEnchant(Item item, Enchantment enchantment, int level, Boolean replace) {
        EnchantsList.addEnchant(Registry.ITEM.getId(item), Registry.ENCHANTMENT.getId(enchantment), level, replace);
    }
}
