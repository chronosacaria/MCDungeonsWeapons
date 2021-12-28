package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.SettingsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;
import java.util.HashMap;

@Config(name = "mcdw_enchantment_settings_config")
public class McdwEnchantmentSettingsConfig implements ConfigData {

    // Enchantment Settings
    public HashMap<SettingsID, Boolean> enableEnchantmentSettings = new HashMap<>();

    public McdwEnchantmentSettingsConfig(){
        for (SettingsID settingsEnum : SettingsID.values()) {
            enableEnchantmentSettings.put(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING, false);
            enableEnchantmentSettings.put(SettingsID.LEECHING_CAN_BE_MIXED_WITH_HEALING, false);
            enableEnchantmentSettings.put(SettingsID.EXTRA_XP_HEALING, false);
            enableEnchantmentSettings.put(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS, true);
            enableEnchantmentSettings.put(SettingsID.ENABLE_INNATE_ENCHANTMENTS, true);
        }
    }
}
