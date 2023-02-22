package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.EnchantmentsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.LinkedHashMap;

@Config(name = "mcdw_enchantments_config")
public class McdwEnchantmentsConfig implements ConfigData {

    // Enable or Disable Enchantments
    public final LinkedHashMap<EnchantmentsID, Boolean> ENABLE_ENCHANTMENTS = new LinkedHashMap<>();
    public final LinkedHashMap<EnchantmentsID, Boolean> ENABLE_VILLAGER_TRADING = new LinkedHashMap<>();
    public final LinkedHashMap<EnchantmentsID, Boolean> ENABLE_RANDOM_SELECTION = new LinkedHashMap<>();

    public McdwEnchantmentsConfig(){
        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            ENABLE_ENCHANTMENTS.put(enchantmentsID, true);

        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            ENABLE_VILLAGER_TRADING.put(enchantmentsID, true);
        ENABLE_VILLAGER_TRADING.replace(EnchantmentsID.DYNAMO, false);
        ENABLE_VILLAGER_TRADING.replace(EnchantmentsID.SHARED_PAIN, false);

        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            ENABLE_RANDOM_SELECTION.put(enchantmentsID, true);
        ENABLE_RANDOM_SELECTION.replace(EnchantmentsID.BURST_BOWSTRING, false);
        ENABLE_RANDOM_SELECTION.replace(EnchantmentsID.DYNAMO, false);
        ENABLE_RANDOM_SELECTION.replace(EnchantmentsID.SHARED_PAIN, false);
    }
}
