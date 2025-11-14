/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.ModConstants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentRegistry {
    public static final List<Identifier> ENCHANTMENTS = new ArrayList<>();

    public static RegistryKey<Enchantment> ACCELERATE         = of("accelerate");
    public static RegistryKey<Enchantment> AMBUSH             = of("ambush");
    public static RegistryKey<Enchantment> ANIMA_CONDUIT      = of("anima_conduit");
    public static RegistryKey<Enchantment> BONUS_SHOT         = of("bonus_shot");
    public static RegistryKey<Enchantment> BURST_BOWSTRING    = of("burst_bowstring");
    public static RegistryKey<Enchantment> BUSY_BEE           = of("busy_bee");
    public static RegistryKey<Enchantment> CHAIN_REACTION     = of("chain_reaction");
    public static RegistryKey<Enchantment> CHAINS             = of("chains");
    public static RegistryKey<Enchantment> CHARGE             = of("charge");
    public static RegistryKey<Enchantment> COBWEB_SHOT        = of("cobweb_shot");
    public static RegistryKey<Enchantment> COMMITTED          = of("committed");
    public static RegistryKey<Enchantment> CRITICAL_HIT       = of("critical_hit");
    public static RegistryKey<Enchantment> DIPPING_POISON     = of("dipping_poison");
    public static RegistryKey<Enchantment> DYNAMO             = of("dynamo");
    public static RegistryKey<Enchantment> ECHO               = of("echo");
    public static RegistryKey<Enchantment> ENIGMA_RESONATOR   = of("enigma_resonator");
    public static RegistryKey<Enchantment> EXPLODING          = of("exploding");
    public static RegistryKey<Enchantment> FREEZING           = of("freezing");
    public static RegistryKey<Enchantment> FUSE_SHOT          = of("fuse_shot");
    public static RegistryKey<Enchantment> GRAVITY            = of("gravity");
    public static RegistryKey<Enchantment> GROWING            = of("growing");
    public static RegistryKey<Enchantment> GUARDING_STRIKE    = of("guarding_strike");
    public static RegistryKey<Enchantment> ILLAGERS_BANE      = of("illagers_bane");
    public static RegistryKey<Enchantment> JUNGLE_POISON      = of("jungle_poison");
    public static RegistryKey<Enchantment> LEECHING           = of("leeching");
    public static RegistryKey<Enchantment> LEVITATION_SHOT    = of("levitation_shot");
    public static RegistryKey<Enchantment> MULTISHOT_BOW      = of("multishot");
    public static RegistryKey<Enchantment> OVERCHARGE         = of("overcharge");
    public static RegistryKey<Enchantment> PAIN_CYCLE         = of("pain_cycle");
    public static RegistryKey<Enchantment> PHANTOMS_MARK      = of("phantoms_mark");
    public static RegistryKey<Enchantment> POISON_CLOUD       = of("poison_cloud");
    public static RegistryKey<Enchantment> PROSPECTOR         = of("prospector");
    public static RegistryKey<Enchantment> RADIANCE           = of("radiance");
    public static RegistryKey<Enchantment> RAMPAGING          = of("rampaging");
    public static RegistryKey<Enchantment> REFRESHMENT        = of("refreshment");
    public static RegistryKey<Enchantment> REPLENISH          = of("replenish");
    public static RegistryKey<Enchantment> RICOCHET           = of("ricochet");
    public static RegistryKey<Enchantment> RUSHDOWN           = of("rushdown");
    public static RegistryKey<Enchantment> SHADOW_BARB        = of("shadow_barb");
    public static RegistryKey<Enchantment> SHADOW_SHOT        = of("shadow_shot");
    public static RegistryKey<Enchantment> SHARED_PAIN        = of("shared_pain");
    public static RegistryKey<Enchantment> SHOCKWAVE          = of("shockwave");
    public static RegistryKey<Enchantment> SMITING            = of("smiting");
    public static RegistryKey<Enchantment> SOUL_DEVOURER      = of("soul_devourer");
    public static RegistryKey<Enchantment> SOUL_SIPHON        = of("soul_siphon");
    public static RegistryKey<Enchantment> STUNNING           = of("stunning");
    public static RegistryKey<Enchantment> SWIRLING           = of("swirling");
    public static RegistryKey<Enchantment> TEMPO_THEFT        = of("tempo_theft");
    public static RegistryKey<Enchantment> THUNDERING         = of("thundering");
    public static RegistryKey<Enchantment> VOID_SHOT          = of("void_shot");
    public static RegistryKey<Enchantment> VOID_STRIKE        = of("void_strike");
    public static RegistryKey<Enchantment> WEAKENING          = of("weakening");
    public static RegistryKey<Enchantment> WILD_RAGE          = of("wild_rage");

    public static void register() {
        /*
        for (EnchantmentsID enchantmentsID : EnchantmentsID.values()) {

            if (!McdwCommon.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(enchantmentsID).mcdw$getIsEnabled())
                continue;

            Enchantment enchantment = switch (enchantmentsID) {
                case AMBUSH -> new AmbushEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case ANIMA_CONDUIT -> new AnimaConduitEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case BUSY_BEE -> new BusyBeeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case CRITICAL_HIT -> new CriticalHitEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case CHAINS -> new ChainsEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case COMMITTED -> new CommittedEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case ECHO -> new EchoEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case ENIGMA_RESONATOR -> new EnigmaResonatorEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case EXPLODING -> new ExplodingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case FREEZING -> new FreezingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case GRAVITY -> new GravityEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case GUARDING_STRIKE -> new GuardingStrikeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case ILLAGERS_BANE -> new IllagersBaneEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case JUNGLE_POISON -> new JunglePoisonEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case LEECHING -> new LeechingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case PAIN_CYCLE -> new PainCycleEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case POISON_CLOUD -> new PoisonCloudEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case PROSPECTOR -> new ProspectorEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case RADIANCE -> new RadianceEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case RAMPAGING -> new RampagingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case REFRESHMENT -> new RefreshmentEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case RUSHDOWN -> new RushdownEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SHARED_PAIN -> new SharedPainEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SHOCKWAVE -> new ShockwaveEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SMITING -> new SmitingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SOUL_DEVOURER -> new SoulDevourerEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SOUL_SIPHON -> new SoulSiphonEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case STUNNING -> new StunningEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SWIRLING -> new SwirlingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case THUNDERING -> new ThunderingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case VOID_STRIKE -> new VoidStrikeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case WEAKENING -> new WeakeningEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});

                case ACCELERATE -> new AccelerateEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case BONUS_SHOT -> new BonusShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case BURST_BOWSTRING -> new BurstBowstringEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case CHAIN_REACTION -> new ChainReactionEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case CHARGE -> new ChargeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case COBWEB_SHOT -> new CobwebShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case DIPPING_POISON -> new DippingPoisonEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case DYNAMO -> new DynamoEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case FUSE_SHOT -> new FuseShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case GROWING -> new GrowingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case LEVITATION_SHOT -> new LevitationShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case MULTI_SHOT -> new MultishotBowEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case OVERCHARGE -> new OverchargeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case PHANTOMS_MARK -> new PhantomsMarkEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case REPLENISH -> new ReplenishEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case RICOCHET -> new RicochetEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SHADOW_SHOT -> new ShadowShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case SHADOW_BARB -> new ShadowBarbEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case TEMPO_THEFT -> new TempoTheftEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case VOID_SHOT -> new VoidShotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
                case WILD_RAGE -> new WildRageEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.BOW,
                        new EquipmentSlot[]{EquipmentSlot.MAINHAND});
            };
            enchantments.put(enchantmentsID, enchantment);
            registerEnchantment(enchantmentsID.toString().toLowerCase(Locale.ROOT), enchantment);
        }

         */
    }

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = ModConstants.id(path);
        ENCHANTMENTS.add(id);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }
}