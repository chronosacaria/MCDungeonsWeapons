package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.EnchantmentsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.LinkedHashMap;
import java.util.List;

@Config(name = "mcdw_enchantments_config")
public class McdwEnchantmentsConfig implements ConfigData {

    @Comment("""
            
            A slider value for damage modifying enchantments:
            Ambush, Critical Hit, Void Strike, Pain Cycle, Enigma Resonator, Dynamo, Shadow Form,
            Enigma Shot, Growing, Void Shot, Overcharge, Committed and Hunter's Promise.
            """)
    public float directDamageEnchantmentMultiplier = 1.0f;

    @Comment("""
            
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
    public final LinkedHashMap<EnchantmentsID, EnchantmentIDConfigHelper> ENCHANTMENT_CONFIG = new LinkedHashMap<>();

    public McdwEnchantmentsConfig(){
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.ACCELERATE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.AMBUSH,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.ANIMA_CONDUIT,
                new EnchantmentIDConfigHelper(
                        List.of("Effect Offset:  Healing Percentage,",
                                "Offset Note:    higher offset = more health recovered,",
                                "Default:        100f"
                        ),
                        true,
                        true,
                        true,
                        3,
                        null,
                        100f
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.BONUS_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.BURST_BOWSTRING,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        false,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.BUZZY_BEE,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        false,
                        1,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.CHAIN_REACTION,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.CHAINS,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        20
                )
        );
        ENCHANTMENT_CONFIG.put(
                    EnchantmentsID.CHARGE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.COBWEB_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        1,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.COMMITTED,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.CRITICAL_HIT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.DIPPING_POISON,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.DYNAMO,
                new EnchantmentIDConfigHelper(
                        List.of("Effect Offset:  Maximum Stacks,",
                                "Offset Note:    higher offset = more potential stacks,",
                                "Default:        20f"
                        ),
                        true,
                        false,
                        false,
                        3,
                        null,
                        20f
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.ECHO,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.ENIGMA_RESONATOR,
                new EnchantmentIDConfigHelper(
                        List.of("Effect Offset:  Damage Divisor,",
                                "Formula:        max((log_e(numSouls * level + 20) / offset) - 1, 0)",
                                "Offset Note:    higher offset = less damage,",
                                "Default:        3.25f"
                        ),
                        true,
                        true,
                        true,
                        3,
                        null,
                        3.25f
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.EXPLODING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        20
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.FREEZING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        30
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.FUSE_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        20
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.GRAVITY,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        30
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.GROWING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.GUARDING_STRIKE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.ILLAGERS_BANE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.POISONING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        30
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.LEECHING,
                new EnchantmentIDConfigHelper(
                        List.of("Effect Offset:  Healing Percentage,",
                                "Offset Note:    higher offset = more health recovered,",
                                "Default:        100f"
                        ),
                        true,
                        true,
                        true,
                        3,
                        null,
                        100f
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.LEVITATION_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.MULTI_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        1,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.OVERCHARGE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.PAIN_CYCLE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.PHANTOMS_MARK,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.POISON_CLOUD,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        30
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.PROSPECTOR,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        5
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.RADIANCE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        20
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.RAMPAGING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.REFRESHMENT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.REPLENISH,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        3
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.RICOCHET,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.RUSHDOWN,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SHADOW_BARB,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        false,
                        1,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SHADOW_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        false,
                        1,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SHARED_PAIN,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        false,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SHOCKWAVE,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SMITING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SOUL_DEVOURER,
                new EnchantmentIDConfigHelper(
                        List.of("Effect Offset:  Additional XP,",
                                "Formula:        amount * (1 + (level / offset)),",
                                "Offset Note:    higher offset = less additional XP,",
                                "Default:        3.0f"
                        ),
                        true,
                        true,
                        true,
                        3,
                        null,
                        3.0f
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SOUL_SIPHON,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.STUNNING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        20
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.SWIRLING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        10
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.TEMPO_THEFT,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        null
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.THUNDERING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        20
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.VOID_SHOT,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        true,
                        3,
                        25
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.VOID_STRIKE,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        true,
                        3,
                        15
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.WEAKENING,
                new EnchantmentIDConfigHelper(
                        true,
                        true,
                        true,
                        3,
                        30
                )
        );
        ENCHANTMENT_CONFIG.put(
                EnchantmentsID.WILD_RAGE,
                new EnchantmentIDConfigHelper(
                        true,
                        false,
                        true,
                        3,
                        10
                )
        );
    }
}
