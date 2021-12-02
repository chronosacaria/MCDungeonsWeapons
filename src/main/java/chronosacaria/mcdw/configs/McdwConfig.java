package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.Mcdw;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = Mcdw.MOD_ID)
public class McdwConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("mcdw_enchantment_settings_config")
    public McdwEnchantmentSettingsConfig mcdwEnchantmentSettingsConfig = new McdwEnchantmentSettingsConfig();

    @ConfigEntry.Category("mcdw_enchantments_config")
    public McdwEnchantmentsConfig mcdwEnchantmentsConfig = new McdwEnchantmentsConfig();

    public static void init() {
        AutoConfig.register(McdwConfig.class,
                PartitioningSerializer.wrap(JanksonConfigSerializer::new)
        );
    }

}
