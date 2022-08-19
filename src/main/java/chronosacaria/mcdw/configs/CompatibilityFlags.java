package chronosacaria.mcdw.configs;

import net.fabricmc.loader.api.FabricLoader;

public class CompatibilityFlags {
    public static boolean isOffHandAttackEnabled = true;
    public static boolean isReachEntityAttributeEnabled = true;

    public static void init() {
        if(FabricLoader.getInstance().isModLoaded("dualwielding") || FabricLoader.getInstance().isModLoaded("bettercombat")) {
            isOffHandAttackEnabled = false;
        }
        if(FabricLoader.getInstance().isModLoaded("bettercombat")) {
            isReachEntityAttributeEnabled = false;
        }
    }
}
