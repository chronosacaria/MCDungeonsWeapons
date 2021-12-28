package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.Mcdw;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = Mcdw.MOD_ID)
public class McdwConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("mcdw_items_registry")
    public McdwEnableItemsConfig mcdwEnableItemsConfig = new McdwEnableItemsConfig();

    @ConfigEntry.Category("mcdw_enchantment_settings_config")
    public McdwEnchantmentSettingsConfig mcdwEnchantmentSettingsConfig = new McdwEnchantmentSettingsConfig();

    @ConfigEntry.Category("mcdw_enchantments_config")
    public McdwEnchantmentsConfig mcdwEnchantmentsConfig = new McdwEnchantmentsConfig();

    @ConfigEntry.Category("mcdw_stats_config")
    public McdwNewStatsConfig mcdwNewStatsConfig = new McdwNewStatsConfig();

    @ConfigEntry.Category("mcdw_loot_config")
    public McdwNewLootConfig mcdwNewlootConfig = new McdwNewLootConfig();

    public static void init() {
        AutoConfig.register(McdwConfig.class,
                PartitioningSerializer.wrap(GsonConfigSerializer::new)
        );
    }

}
