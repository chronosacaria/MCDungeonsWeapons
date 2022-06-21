package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.EnchantmentsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.HashMap;

@Config(name = "mcdw_enchantments_config")
public class McdwEnchantmentsConfig implements ConfigData {

    // Enable or Disable Enchantments
    public HashMap<EnchantmentsID, Boolean> enableEnchantments = new HashMap<>();
    public HashMap<EnchantmentsID, Boolean> enableVillageTrading = new HashMap<>();
    public HashMap<EnchantmentsID, Boolean> enableRandomSelection = new HashMap<>();

    public McdwEnchantmentsConfig(){
        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            enableEnchantments.put(enchantmentsID, true);
        enableEnchantments.replace(EnchantmentsID.ACCELERATE, false);

        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            enableVillageTrading.put(enchantmentsID, true);
        enableVillageTrading.replace(EnchantmentsID.DYNAMO, false);

        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            enableRandomSelection.put(enchantmentsID, true);
        enableRandomSelection.replace(EnchantmentsID.DYNAMO, false);
    }
}
