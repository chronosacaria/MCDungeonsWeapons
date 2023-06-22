package chronosacaria.mcdw;

import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.configs.McdwConfig;
import chronosacaria.mcdw.data.ConfigItemEnabledCondition;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import chronosacaria.mcdw.registries.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mcdw implements ModInitializer {

    public static final String MOD_ID = "mcdw";
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogManager.getLogger();
    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }
    public static McdwConfig CONFIG;

    @Override
    public void onInitialize() {
        CompatibilityFlags.init();
        EntityAttributesRegistry.register();
        McdwConfig.register();
        CONFIG = AutoConfig.getConfigHolder(McdwConfig.class).getConfig();
        ConfigItemEnabledCondition.register();
        ParticlesRegistry.registerOnServer();
        ItemGroupRegistry.register();
        ItemsRegistry.register();
        OffhandAttackPacket.register();
        LootTablesRegistry.register();
        EnchantsRegistry.register();
        SoundEventsRegistry.register();
        SummonedEntityRegistry.register();
        StatusEffectsRegistry.register();
        EnchantmentRestrictionsRegistry.register();
    }
}