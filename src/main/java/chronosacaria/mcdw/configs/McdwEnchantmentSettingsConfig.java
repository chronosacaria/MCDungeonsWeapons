package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.EnchantStatsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SettingsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Config(name = "mcdw_enchantment_settings_config")
public class McdwEnchantmentSettingsConfig implements ConfigData {

    // Enchantment Settings
    public HashMap<SettingsID, Boolean> enableEnchantmentSettings = new HashMap<>();

    @Comment("ENIGMA_RESONATOR_DIVISOR -> Higher number = lower damage")
    public HashMap<EnchantStatsID, Float> enchantmentStatsSettings = new HashMap<>();

    @Comment("Enchantment Trigger Chances Base. This is not the % to occur, but the higher this number, the higher " +
            "the chance to trigger.")
    public LinkedHashMap<EnchantmentsID, Integer> enchantmentTriggerChanceBase = new LinkedHashMap<>();

    @Comment("Overall Enchantment Strength Slider")
    public float enchantmentStrength = 1.0f;

    public McdwEnchantmentSettingsConfig(){
        enableEnchantmentSettings.put(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING, false);
        enableEnchantmentSettings.put(SettingsID.LEECHING_CAN_BE_MIXED_WITH_HEALING, false);
        enableEnchantmentSettings.put(SettingsID.EXTRA_XP_HEALING, false);
        enableEnchantmentSettings.put(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS, true);
        enableEnchantmentSettings.put(SettingsID.ENABLE_INNATE_ENCHANTMENTS, true);

        enchantmentStatsSettings.put(EnchantStatsID.ENIGMA_RESONATOR_DIVISOR, 3.25f);

        enchantmentTriggerChanceBase.put(EnchantmentsID.CHAIN_REACTION, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.CHAINS, 20);
        enchantmentTriggerChanceBase.put(EnchantmentsID.CHARGE, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.COMMITTED, 30);
        enchantmentTriggerChanceBase.put(EnchantmentsID.CRITICAL_HIT, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.ECHO, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.EXPLODING, 20);
        enchantmentTriggerChanceBase.put(EnchantmentsID.FREEZING, 30);
        enchantmentTriggerChanceBase.put(EnchantmentsID.FUSE_SHOT, 20);
        enchantmentTriggerChanceBase.put(EnchantmentsID.GRAVITY, 30);
        enchantmentTriggerChanceBase.put(EnchantmentsID.POISONING, 30);
        enchantmentTriggerChanceBase.put(EnchantmentsID.POISON_CLOUD, 30);
        enchantmentTriggerChanceBase.put(EnchantmentsID.PROSPECTOR, 5);
        enchantmentTriggerChanceBase.put(EnchantmentsID.RADIANCE, 20);
        enchantmentTriggerChanceBase.put(EnchantmentsID.RAMPAGING, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.REPLENISH, 3);
        enchantmentTriggerChanceBase.put(EnchantmentsID.RUSHDOWN, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.SHOCKWAVE, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.SOUL_SIPHON, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.STUNNING, 20);
        enchantmentTriggerChanceBase.put(EnchantmentsID.SWIRLING, 10);
        enchantmentTriggerChanceBase.put(EnchantmentsID.THUNDERING, 20);
        enchantmentTriggerChanceBase.put(EnchantmentsID.VOID_SHOT, 25);
        enchantmentTriggerChanceBase.put(EnchantmentsID.VOID_STRIKE, 15);
        enchantmentTriggerChanceBase.put(EnchantmentsID.WEAKENING, 30);

    }
}
