package chronosacaria.mcdw.configs;

import net.fabricmc.loader.api.FabricLoader;

public class CompatibilityFlags {
    public static boolean noOffhandConflicts = true;
    public static boolean isReachExtensionEnabled = true;

    public static void init() {
        if(FabricLoader.getInstance().isModLoaded("dualwielding") || FabricLoader.getInstance().isModLoaded("bettercombat")) {
            noOffhandConflicts = false;
        }
        if(FabricLoader.getInstance().isModLoaded("bettercombat")) {
            isReachExtensionEnabled = false;
        }
    }
}
