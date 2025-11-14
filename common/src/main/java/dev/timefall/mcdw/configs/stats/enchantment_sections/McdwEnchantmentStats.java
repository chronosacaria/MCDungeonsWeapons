/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.configs.stats.enchantment_sections;

import dev.timefall.mcdw.configs.stats.IMcdwEnchantmentStats;
import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.TomlHeaderComment;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;

@SuppressWarnings("FieldMayBeFinal")
@TomlHeaderComment(text = """
            Some notes to make sense of the config, below:
            
            offset:     This is a value that is unique to certain enchantments. What it does will be
                        explained in the "comment" section that is related to the enchantment.
            
            isEnabled:  Whether the enchantment is on or off. If set to "false", the enchantment
                        will not be possible to obtain in any way.
            
            isAvailableForEnchantedBookOffer: Whether the enchantment can be sold by villagers.
                        If set to "false", this means that Librarian villagers will not sell this
                        enchantment.
            
            isAvailableForRandomSelection: Whether the enchantment can be found as random loot or
                        obtained through the Enchanting Table. If set to "false", this means that the
                        enchantment will not be available via the Enchanting Table nor will it
                        generate as loot that is found in the world.
         
            maxLevel:   This is an integer (whole number) value that tells the game what the max
                        level the enchantment can be. The value for most enchantments in Minecraft
                        range from 1 to 5. Most MCDX enchantments are up to level 3 and whilst it
                        is possible to set this value above 3, it can have some unexpected results.
          
            procChance: Some enchantments trigger on a chance. Whilst this number is not a percentage
                        the higher the number, the more likely the enchantment is to trigger.
                        Therefore, if the procChance is set to 20, it is more likely to trigger than
                        if the procChance is set to 10. It is best to experiment with these values
                        if you plan to change them to see what works best for you.
            """)
public class McdwEnchantmentStats extends ConfigSection {
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats accelerateEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats ambushEnchantment            = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    @Comment("""
            Effect Offset:  Healing Percentage,
            Offset Note:    higher offset == more health recovered,
            Default:        100.0f
            """)
    private IMcdwEnchantmentStats.EnchantmentStats animaConduitEnchantment      = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  100.0f);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats bonusShotEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats burstBowstringEnchantment    = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats busyBeeEnchantment           = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  1);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats chainReactionEnchantment     = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats chainsEnchantment            = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  20);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats chargeEnchantment            = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats cobwebShotEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   1);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats committedEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats criticalHitEnchantment       = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats dippingPoisonEnchantment     = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    @Comment("""
            Effect Offset:  Maximum Stacks,
            Offset Note:    higher offset == more potential stacks,
            Default:        20.0f
            """)
    private IMcdwEnchantmentStats.EnchantmentStats dynamoEnchantment            = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  3,  20.0f);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats echoEnchantment              = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    @Comment("""
            Effect Offset:  Damage Divisor,
            Formula:        max((log_e(numSouls * level + 20) / offset) - 1, 0),
            Offset Note:    higher offset == less damage,
            Default:        3.25f
            """)
    private IMcdwEnchantmentStats.EnchantmentStats enigmaResonatorEnchantment   = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  3.25f);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats explodingEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  20);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats freezingEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  30);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats fuseShotEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  20);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats gravityEnchantment           = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  30);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats growingEnchantment           = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats guardingStrikeEnchantment    = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats illagersBaneEnchantment      = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats junglePoisonEnchantment      = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  30);
    @SuppressWarnings("CanBeFinal")
    @Comment("""
            Effect Offset:  Healing Percentage,
            Offset Note:    higher offset == more health recovered,
            Default:        100.0f
            """)
    private IMcdwEnchantmentStats.EnchantmentStats leechingEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  100.0f);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats levitationShotEnchantment    = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats MultiShotEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  1);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats overchargeEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats painCycleEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats phantomsMarkEnchantment      = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats poisonCloudEnchantment       = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  30);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats prospectorEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  5);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats radianceEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  20);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats rampagingEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats refreshmentEnchantment       = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats replenishEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats ricochetEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats rushdownEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats shadowBarbEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  1);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats shadowShotEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  1);
    @SuppressWarnings("CanBeFinal")
    @Comment("""
            Effect:                 If a player does not damage a living entity within a certain amount of time they will begin to take damage,
            canAffectPlayer Note:   true == yes ; false == no,
            Default:                true
            """)
    private IMcdwEnchantmentStats.EnchantmentStats sharedPainEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  false,  false,  3,  true);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats shockwaveEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats smitingEnchantment           = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    @Comment("""
            Effect Offset:  Additional XP,
            Formula:        amount * (1 + (level / offset)),
            Offset Note:    higher offset == less additional XP,
            Default:        3.0f
            """)
    private IMcdwEnchantmentStats.EnchantmentStats soulDevourerEnchantment      = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  3.0f);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats soulSiphonEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats stunningEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  20);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats swirlingEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  10);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats tempoTheftEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats thunderingEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  20);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats voidShotEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   false,  3,  25);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats voidStrikeEnchantment        = IMcdwEnchantmentStats.enchantmentStats(true,  true,   false,  3,  15);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats weakeningEnchantment         = IMcdwEnchantmentStats.enchantmentStats(true,  true,   true,   3,  30);
    @SuppressWarnings("CanBeFinal")
    private IMcdwEnchantmentStats.EnchantmentStats wildRageEnchantment          = IMcdwEnchantmentStats.enchantmentStats(true,  true,   false,  3,  10);

    public IMcdwEnchantmentStats.EnchantmentStats getAccelerateEnchantment() {
        return accelerateEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getAmbushEnchantment() {
        return ambushEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getAnimaConduitEnchantment() {
        return animaConduitEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getBonusShotEnchantment() {
        return bonusShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getBurstBowstringEnchantment() {
        return burstBowstringEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getBusyBeeEnchantment() {
        return busyBeeEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getChainReactionEnchantment() {
        return chainReactionEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getChainsEnchantment() {
        return chainsEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getChargeEnchantment() {
        return chargeEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getCobwebShotEnchantment() {
        return cobwebShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getCommittedEnchantment() {
        return committedEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getCriticalHitEnchantment() {
        return criticalHitEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getDippingPoisonEnchantment() {
        return dippingPoisonEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getDynamoEnchantment() {
        return dynamoEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getEchoEnchantment() {
        return echoEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getEnigmaResonatorEnchantment() {
        return enigmaResonatorEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getExplodingEnchantment() {
        return explodingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getFreezingEnchantment() {
        return freezingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getFuseShotEnchantment() {
        return fuseShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getGravityEnchantment() {
        return gravityEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getGrowingEnchantment() {
        return growingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getGuardingStrikeEnchantment() {
        return guardingStrikeEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getIllagersBaneEnchantment() {
        return illagersBaneEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getJunglePoisonEnchantment() {
        return junglePoisonEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getLeechingEnchantment() {
        return leechingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getLevitationShotEnchantment() {
        return levitationShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getMultiShotEnchantment() {
        return MultiShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getOverchargeEnchantment() {
        return overchargeEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getPainCycleEnchantment() {
        return painCycleEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getPhantomsMarkEnchantment() {
        return phantomsMarkEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getPoisonCloudEnchantment() {
        return poisonCloudEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getProspectorEnchantment() {
        return prospectorEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getRadianceEnchantment() {
        return radianceEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getRampagingEnchantment() {
        return rampagingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getRefreshmentEnchantment() {
        return refreshmentEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getReplenishEnchantment() {
        return replenishEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getRicochetEnchantment() {
        return ricochetEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getRushdownEnchantment() {
        return rushdownEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getShadowBarbEnchantment() {
        return shadowBarbEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getShadowShotEnchantment() {
        return shadowShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getSharedPainEnchantment() {
        return sharedPainEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getShockwaveEnchantment() {
        return shockwaveEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getSmitingEnchantment() {
        return smitingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getSoulDevourerEnchantment() {
        return soulDevourerEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getSoulSiphonEnchantment() {
        return soulSiphonEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getStunningEnchantment() {
        return stunningEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getSwirlingEnchantment() {
        return swirlingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getTempoTheftEnchantment() {
        return tempoTheftEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getThunderingEnchantment() {
        return thunderingEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getVoidShotEnchantment() {
        return voidShotEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getVoidStrikeEnchantment() {
        return voidStrikeEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getWeakeningEnchantment() {
        return weakeningEnchantment;
    }

    public IMcdwEnchantmentStats.EnchantmentStats getWildRageEnchantment() {
        return wildRageEnchantment;
    }
}
