package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.SettingsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.LinkedHashMap;

@Config(name = "mcdw_enchantment_settings_config")
public class McdwEnchantmentSettingsConfig implements ConfigData {

    @Comment("""
            Default (0): Everything other than self
            Next Permission (1): Not self and not teammates or pets of self
            Next Permission (2): Not self and not teammates or pets of self or AnimalEntities (not hitting farm animals is not present in permission 3)
            Next Permission (3): Not self and not Potential allies (pets of any player, iron golems, villagers, players)
            Final Permission (4): Only hostile mobs
            WARNING: LOOKS AT HOSTILE ENTITY CLASS WHICH DOES NOT INCLUDE ENDER DRAGON AND OTHERS.
            If anything else is put, it will be treated as default""")
    public int aoePermission = 0;

    // Enchantment Settings
    public final LinkedHashMap<SettingsID, Boolean> ENABLE_ENCHANTMENT_SETTINGS = new LinkedHashMap<>();

    public McdwEnchantmentSettingsConfig(){
        ENABLE_ENCHANTMENT_SETTINGS.put(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING, false);
        ENABLE_ENCHANTMENT_SETTINGS.put(SettingsID.LEECHING_CAN_BE_MIXED_WITH_HEALING, false);
        ENABLE_ENCHANTMENT_SETTINGS.put(SettingsID.EXTRA_XP_HEALING, false);
        ENABLE_ENCHANTMENT_SETTINGS.put(SettingsID.SHARED_PAIN_CAN_DAMAGE_USER, false);
        ENABLE_ENCHANTMENT_SETTINGS.put(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS, true);
        ENABLE_ENCHANTMENT_SETTINGS.put(SettingsID.ENABLE_INNATE_ENCHANTMENTS, true);
    }
}
