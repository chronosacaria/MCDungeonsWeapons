package chronosacaria.mcdw.configs;

import net.fabricmc.loader.api.FabricLoader;

public class FeatureFlags {
    public static boolean isRangeAttributeUsageEnabled = true;
    public static boolean isDualWieldingEnabled = true;

    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("bettercombat")) {
            isRangeAttributeUsageEnabled = false;
            isDualWieldingEnabled = false;
        }
    }
}
