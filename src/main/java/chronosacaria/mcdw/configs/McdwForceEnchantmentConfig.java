package chronosacaria.mcdw.configs;

import biom4st3r.mods.enchantment_force.ItemWithEnchantmentAssigner;
import biom4st3r.mods.enchantment_force.json.EnchantDesc;
import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;

import java.io.File;

public class McdwForceEnchantmentConfig {
    private static File getEnchantmentConfig(){
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), "forceEnchantments.json");
    }


    public static void forceEnchantments() {
        if (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.ENABLE_INNATE_ENCHANTMENTS)) {

            forceAxesEnchants();
            forceBowEnchants();
            forceCrossbowEnchants();
            forceDaggerEnchants();
            forceDoubleAxeEnchants();
            forceGauntletEnchants();
            forceGlaiveEnchants();
            forceHammerEnchants();
            forceSickleEnchants();
            forceScytheEnchants();
            forceSoulDaggerEnchants();
            forceSpearEnchants();
            forceStaffEnchants();
            forceSwordEnchants();
            forceWhipEnchants();
        }
    }

    private static void setForcedEnchant(Item item, EnchantDesc... enchantDesc) {
        // Allows you to specify level... new EnchantDesc(Enchantment, level)
        ((ItemWithEnchantmentAssigner) item).setEnchantments(enchantDesc);
    }
    private static void setForcedEnchant(Item item, Enchantment... enchantments) {
        // This does them at lvl 1
        if (!getEnchantmentConfig().canRead()) {
            ((ItemWithEnchantmentAssigner) item).setEnchantments(enchantments);
        }
    }

    /* Weapon Force Enchants Methods */
    private static void forceAxesEnchants() {
        setForcedEnchant(ItemsInit.axeItems.get(AxesID.AXE_FIREBRAND),
                Enchantments.FIRE_ASPECT);
        setForcedEnchant(ItemsInit.axeItems.get(AxesID.AXE_ENCRUSTED_ANCHOR),
                EnchantsRegistry.GRAVITY, EnchantsRegistry.JUNGLE_POISON);
        setForcedEnchant(ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND),
                EnchantsRegistry.STUNNING);
    }
    private static void forceBowEnchants() {
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_BONEBOW),
                EnchantsRegistry.GROWING);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_BUBBLE_BURSTER),
                EnchantsRegistry.RICOCHET);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_BURST_GALE_BOW),
                EnchantsRegistry.CHARGE);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_CALL_OF_THE_VOID),
                EnchantsRegistry.FUSE_SHOT, EnchantsRegistry.VOID_SHOT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_ECHO_OF_THE_VALLEY),
                EnchantsRegistry.RICOCHET);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_ELITE_POWER_BOW),
                Enchantments.POWER);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_GREEN_MENACE),
                EnchantsRegistry.POISON_CLOUD);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_HAUNTED_BOW),
                EnchantsRegistry.BONUS_SHOT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE),
                EnchantsRegistry.REPLENISH);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS),
                Enchantments.MULTISHOT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_MASTERS_BOW),
                Enchantments.POWER);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_NOCTURNAL_BOW),
                EnchantsRegistry.TEMPO_THEFT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_PHANTOM_BOW),
                EnchantsRegistry.PHANTOMS_MARK, Enchantments.POWER);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_SABREWING),
                EnchantsRegistry.RADIANCE);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_SHIVERING_BOW),
                EnchantsRegistry.TEMPO_THEFT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_TWIN_BOW),
                EnchantsRegistry.BONUS_SHOT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_VOID_BOW),
                EnchantsRegistry.VOID_SHOT);
        setForcedEnchant(ItemsInit.bowItems.get(BowsID.BOW_WEB_BOW),
                EnchantsRegistry.COBWEB_SHOT);

        setForcedEnchant(ItemsInit.shortBowItems.get(ShortBowsID.BOW_LOVE_SPELL_BOW),
                EnchantsRegistry.RADIANCE);

        setForcedEnchant(ItemsInit.longBowItems.get(LongBowsID.BOW_RED_SNAKE),
                EnchantsRegistry.FUSE_SHOT);
    }
    private static void forceCrossbowEnchants() {
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW),
                EnchantsRegistry.BONUS_SHOT);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW),
                EnchantsRegistry.GROWING);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW),
                EnchantsRegistry.BONUS_SHOT);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW),
                Enchantments.PUNCH);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW),
                EnchantsRegistry.FUSE_SHOT);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW),
                EnchantsRegistry.ENIGMA_RESONATOR);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER),
                EnchantsRegistry.CHAIN_REACTION);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW),
                Enchantments.MULTISHOT);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW),
                EnchantsRegistry.GRAVITY);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW),
                Enchantments.MULTISHOT, EnchantsRegistry.RICOCHET);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW),
                Enchantments.PIERCING);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS),
                Enchantments.PIERCING);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW),
                Enchantments.MULTISHOT);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW),
                EnchantsRegistry.RICOCHET);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_THE_SLICER),
                Enchantments.PIERCING);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW),
                EnchantsRegistry.ENIGMA_RESONATOR);
        setForcedEnchant(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW),
                EnchantsRegistry.GRAVITY);
    }
    private static void forceDaggerEnchants() {
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_BACKSTABBER),
                EnchantsRegistry.AMBUSH);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING),
                EnchantsRegistry.LEECHING);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END),
                EnchantsRegistry.VOID_STRIKE);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_CHILL_GALE_KNIFE),
                EnchantsRegistry.FREEZING);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_FANGS_OF_FROST),
                EnchantsRegistry.FREEZING);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_MOON),
                EnchantsRegistry.ENIGMA_RESONATOR);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE),
                EnchantsRegistry.COMMITTED, EnchantsRegistry.RUSHDOWN);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_SHEAR_DAGGER),
                EnchantsRegistry.SWIRLING);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_SWIFT_STRIKER),
                EnchantsRegistry.AMBUSH, EnchantsRegistry.ECHO);
        setForcedEnchant(ItemsInit.daggerItems.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE),
                EnchantsRegistry.VOID_STRIKE);
    }
    private static void forceDoubleAxeEnchants() {
        setForcedEnchant(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_CURSED),
                EnchantsRegistry.EXPLODING);
        setForcedEnchant(ItemsInit.doubleAxeItems.get(DoubleAxesID.AXE_WHIRLWIND),
                EnchantsRegistry.SHOCKWAVE);
    }
    private static void forceGauntletEnchants() {
        setForcedEnchant(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_MAULERS),
                EnchantsRegistry.RAMPAGING);
        setForcedEnchant(ItemsInit.gauntletItems.get(GauntletsID.GAUNTLET_SOUL_FISTS),
                EnchantsRegistry.ENIGMA_RESONATOR);
    }
    private static void forceGlaiveEnchants() {
        setForcedEnchant(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_CACKLING_BROOM),
                EnchantsRegistry.SMITING);
        setForcedEnchant(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_GRAVE_BANE),
                EnchantsRegistry.SMITING);
        setForcedEnchant(ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE),
                EnchantsRegistry.POISON_CLOUD);
    }
    private static void forceHammerEnchants() {
        setForcedEnchant(ItemsInit.hammerItems.get(HammersID.HAMMER_BONE_CUDGEL),
                EnchantsRegistry.ILLAGERS_BANE);
        setForcedEnchant(ItemsInit.hammerItems.get(HammersID.HAMMER_FLAIL),
                EnchantsRegistry.CHAINS);
        setForcedEnchant(ItemsInit.hammerItems.get(HammersID.HAMMER_GRAVITY),
                EnchantsRegistry.GRAVITY);
        setForcedEnchant(ItemsInit.hammerItems.get(HammersID.HAMMER_STORMLANDER),
                EnchantsRegistry.THUNDERING);
        setForcedEnchant(ItemsInit.hammerItems.get(HammersID.HAMMER_SUNS_GRACE),
                EnchantsRegistry.RADIANCE);
    }
    private static void forceSickleEnchants() {
        setForcedEnchant(ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD),
                EnchantsRegistry.PROSPECTOR);
        setForcedEnchant(ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER),
                EnchantsRegistry.PROSPECTOR);
        setForcedEnchant(ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE),
                EnchantsRegistry.POISON_CLOUD);
    }
    private static void forceScytheEnchants() {
        setForcedEnchant(ItemsInit.scytheItems.get(ScythesID.SICKLE_FROST_SCYTHE),
                EnchantsRegistry.FREEZING);
        setForcedEnchant(ItemsInit.scytheItems.get(ScythesID.SICKLE_JAILORS_SCYTHE),
                EnchantsRegistry.CHAINS);
        setForcedEnchant(ItemsInit.scytheItems.get(ScythesID.SICKLE_SKULL_SCYTHE),
                EnchantsRegistry.FREEZING);
        setForcedEnchant(ItemsInit.scytheItems.get(ScythesID.SICKLE_SOUL_SCYTHE),
                EnchantsRegistry.SOUL_DEVOURER);
    }
    private static void forceSoulDaggerEnchants() {
        setForcedEnchant(ItemsInit.soulDaggerItems.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE),
                EnchantsRegistry.SOUL_SIPHON);
        setForcedEnchant(ItemsInit.soulDaggerItems.get(SoulDaggersID.SWORD_TRUTHSEEKER),
                EnchantsRegistry.COMMITTED);
    }
    private static void forceSpearEnchants() {
        setForcedEnchant(ItemsInit.spearItems.get(SpearsID.SPEAR_FORTUNE),
                Enchantments.LOOTING);
        setForcedEnchant(ItemsInit.spearItems.get(SpearsID.SPEAR_WHISPERING_SPEAR),
                EnchantsRegistry.ECHO);
    }
    private static void forceStaffEnchants() {
        setForcedEnchant(ItemsInit.staffItems.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR),
                EnchantsRegistry.EXPLODING);
        setForcedEnchant(ItemsInit.staffItems.get(StavesID.STAFF_GROWING_STAFF),
                EnchantsRegistry.COMMITTED);
    }
    private static void forceSwordEnchants() {
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_BROADSWORD),
                EnchantsRegistry.SWIRLING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_DANCERS_SWORD),
                EnchantsRegistry.RAMPAGING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_DARK_KATANA),
                EnchantsRegistry.SMITING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_FREEZING_FOIL),
                EnchantsRegistry.FREEZING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_FROST_SLAYER),
                EnchantsRegistry.FREEZING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_HAWKBRAND),
                EnchantsRegistry.CRITICAL_HIT);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_HEARTSTEALER),
                EnchantsRegistry.LEECHING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_MASTERS_KATANA),
                EnchantsRegistry.CRITICAL_HIT);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE),
                EnchantsRegistry.WEAKENING);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_SINISTER),
                EnchantsRegistry.CRITICAL_HIT);
        setForcedEnchant(ItemsInit.swordItems.get(SwordsID.SWORD_SPONGE_STRIKER),
                EnchantsRegistry.ENIGMA_RESONATOR);
    }
    private static void forceWhipEnchants() {
        setForcedEnchant(ItemsInit.whipItems.get(WhipsID.WHIP_VINE_WHIP),
                EnchantsRegistry.JUNGLE_POISON);
    }

}
