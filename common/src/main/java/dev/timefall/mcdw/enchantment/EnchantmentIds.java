/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentIds {

    public static final List<Identifier> ENCHANTMENT_IDS = new ArrayList<>();

    /////////////////

    public static final RegistryKey<Enchantment> ACCELERATE         = EnchantmentIds.of("accelerate");
    public static final RegistryKey<Enchantment> AMBUSH             = EnchantmentIds.of("ambush");
    public static final RegistryKey<Enchantment> ANIMA_CONDUIT      = EnchantmentIds.of("anima_conduit");
    public static final RegistryKey<Enchantment> BONUS_SHOT         = EnchantmentIds.of("bonus_shot");
    public static final RegistryKey<Enchantment> BURST_BOWSTRING    = EnchantmentIds.of("burst_bowstring");
    public static final RegistryKey<Enchantment> BUSY_BEE           = EnchantmentIds.of("busy_bee");
    public static final RegistryKey<Enchantment> CHAIN_REACTION     = EnchantmentIds.of("chain_reaction");
    public static final RegistryKey<Enchantment> CHAINS             = EnchantmentIds.of("chains");
    public static final RegistryKey<Enchantment> CHARGE             = EnchantmentIds.of("charge");
    public static final RegistryKey<Enchantment> COBWEB_SHOT        = EnchantmentIds.of("cobweb_shot");
    public static final RegistryKey<Enchantment> COMMITTED          = EnchantmentIds.of("committed");
    public static final RegistryKey<Enchantment> CRITICAL_HIT       = EnchantmentIds.of("critical_hit");
    public static final RegistryKey<Enchantment> DIPPING_POISON     = EnchantmentIds.of("dipping_poison");
    public static final RegistryKey<Enchantment> DYNAMO             = EnchantmentIds.of("dynamo");
    public static final RegistryKey<Enchantment> ECHO               = EnchantmentIds.of("echo");
    public static final RegistryKey<Enchantment> ENIGMA_RESONATOR   = EnchantmentIds.of("enigma_resonator");
    public static final RegistryKey<Enchantment> EXPLODING          = EnchantmentIds.of("exploding");
    public static final RegistryKey<Enchantment> FREEZING           = EnchantmentIds.of("freezing");
    public static final RegistryKey<Enchantment> FUSE_SHOT          = EnchantmentIds.of("fuse_shot");
    public static final RegistryKey<Enchantment> GRAVITY            = EnchantmentIds.of("gravity");
    public static final RegistryKey<Enchantment> GROWING            = EnchantmentIds.of("growing");
    public static final RegistryKey<Enchantment> GUARDING_STRIKE    = EnchantmentIds.of("guarding_strike");
    public static final RegistryKey<Enchantment> ILLAGERS_BANE      = EnchantmentIds.of("illagers_bane");
    public static final RegistryKey<Enchantment> JUNGLE_POISON      = EnchantmentIds.of("jungle_poison");
    public static final RegistryKey<Enchantment> LEECHING           = EnchantmentIds.of("leeching");
    public static final RegistryKey<Enchantment> LEVITATION_SHOT    = EnchantmentIds.of("levitation_shot");
    public static final RegistryKey<Enchantment> MULTISHOT_BOW      = EnchantmentIds.of("multishot");
    public static final RegistryKey<Enchantment> OVERCHARGE         = EnchantmentIds.of("overcharge");
    public static final RegistryKey<Enchantment> PAIN_CYCLE         = EnchantmentIds.of("pain_cycle");
    public static final RegistryKey<Enchantment> PHANTOMS_MARK      = EnchantmentIds.of("phantoms_mark");
    public static final RegistryKey<Enchantment> POISON_CLOUD       = EnchantmentIds.of("poison_cloud");
    public static final RegistryKey<Enchantment> PROSPECTOR         = EnchantmentIds.of("prospector");
    public static final RegistryKey<Enchantment> RADIANCE           = EnchantmentIds.of("radiance");
    public static final RegistryKey<Enchantment> RAMPAGING          = EnchantmentIds.of("rampaging");
    public static final RegistryKey<Enchantment> REFRESHMENT        = EnchantmentIds.of("refreshment");
    public static final RegistryKey<Enchantment> REPLENISH          = EnchantmentIds.of("replenish");
    public static final RegistryKey<Enchantment> RICOCHET           = EnchantmentIds.of("ricochet");
    public static final RegistryKey<Enchantment> RUSHDOWN           = EnchantmentIds.of("rushdown");
    public static final RegistryKey<Enchantment> SHADOW_BARB        = EnchantmentIds.of("shadow_barb");
    public static final RegistryKey<Enchantment> SHADOW_SHOT        = EnchantmentIds.of("shadow_shot");
    public static final RegistryKey<Enchantment> SHARED_PAIN        = EnchantmentIds.of("shared_pain");
    public static final RegistryKey<Enchantment> SHOCKWAVE          = EnchantmentIds.of("shockwave");
    public static final RegistryKey<Enchantment> SMITING            = EnchantmentIds.of("smiting");
    public static final RegistryKey<Enchantment> SOUL_DEVOURER      = EnchantmentIds.of("soul_devourer");
    public static final RegistryKey<Enchantment> SOUL_SIPHON        = EnchantmentIds.of("soul_siphon");
    public static final RegistryKey<Enchantment> STUNNING           = EnchantmentIds.of("stunning");
    public static final RegistryKey<Enchantment> SWIRLING           = EnchantmentIds.of("swirling");
    public static final RegistryKey<Enchantment> TEMPO_THEFT        = EnchantmentIds.of("tempo_theft");
    public static final RegistryKey<Enchantment> THUNDERING         = EnchantmentIds.of("thundering");
    public static final RegistryKey<Enchantment> VOID_SHOT          = EnchantmentIds.of("void_shot");
    public static final RegistryKey<Enchantment> VOID_STRIKE        = EnchantmentIds.of("void_strike");
    public static final RegistryKey<Enchantment> WEAKENING          = EnchantmentIds.of("weakening");
    public static final RegistryKey<Enchantment> WILD_RAGE          = EnchantmentIds.of("wild_rage");

    public static void init(){}

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Mcdw.ID(path);
        ENCHANTMENT_IDS.add(id);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }
}