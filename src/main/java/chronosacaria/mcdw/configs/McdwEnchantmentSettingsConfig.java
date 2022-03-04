package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.EnchantStatsID;
import chronosacaria.mcdw.enums.SettingsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.HashMap;

@Config(name = "mcdw_enchantment_settings_config")
public class McdwEnchantmentSettingsConfig implements ConfigData {

    // Enchantment Settings
    public HashMap<SettingsID, Boolean> enableEnchantmentSettings = new HashMap<>();

    @Comment("VOID_STRIKE_DIVISOR -> Higher number = lower damage")
    public HashMap<EnchantStatsID, Float> enchantmentStatsSettings = new HashMap<>();

    public McdwEnchantmentSettingsConfig(){
        enableEnchantmentSettings.put(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING, false);
        enableEnchantmentSettings.put(SettingsID.LEECHING_CAN_BE_MIXED_WITH_HEALING, false);
        enableEnchantmentSettings.put(SettingsID.EXTRA_XP_HEALING, false);
        enableEnchantmentSettings.put(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS, true);
        enableEnchantmentSettings.put(SettingsID.ENABLE_INNATE_ENCHANTMENTS, true);

        enchantmentStatsSettings.put(EnchantStatsID.VOID_STRIKE_DIVISOR, 3.25f);
    }
}
