/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.EnumMap;

public class EnchantsRegistry {

    public static final EnumMap<EnchantmentsID, Enchantment> enchantments = new EnumMap<>(EnchantmentsID.class);
    public static void register() {
        /*
        for (EnchantmentsID enchantmentsID : EnchantmentsID.values()) {

            if (!Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(enchantmentsID).mcdw$getIsEnabled())
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

    private static void registerEnchantment(String id, Enchantment enchantment) {
        Registry.register(Registries.ENCHANTMENT, Mcdw.ID(id), enchantment);
    }
}