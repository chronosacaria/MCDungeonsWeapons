package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.EnchantmentsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.HashMap;

@Config(name = "mcdw_enchantments_config")
public class McdwEnchantmentsConfig implements ConfigData {

    // TODO Change nomenclature to final convention for hashmaps and subsequent usages
    // Enable or Disable Enchantments
    public final HashMap<EnchantmentsID, Boolean> enableEnchantments = new HashMap<>();
    public final HashMap<EnchantmentsID, Boolean> enableVillageTrading = new HashMap<>();
    public final HashMap<EnchantmentsID, Boolean> enableRandomSelection = new HashMap<>();

    public McdwEnchantmentsConfig(){
        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            enableEnchantments.put(enchantmentsID, true);

        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            enableVillageTrading.put(enchantmentsID, true);
        enableVillageTrading.replace(EnchantmentsID.DYNAMO, false);

        for (EnchantmentsID enchantmentsID : EnchantmentsID.values())
            enableRandomSelection.put(enchantmentsID, true);
        enableRandomSelection.replace(EnchantmentsID.BURST_BOWSTRING, false);
        enableRandomSelection.replace(EnchantmentsID.DYNAMO, false);
    }
}
