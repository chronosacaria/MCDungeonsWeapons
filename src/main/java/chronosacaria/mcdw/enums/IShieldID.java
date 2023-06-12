package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;

public interface IShieldID extends IMcdwWeaponID {

    HashMap<ShieldsID, ShieldStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ShieldStats getWeaponItemStats();

    ShieldStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ToolMaterial getMaterial();

    String[] getRepairIngredient();

    class ShieldStats {
        String material;
        String[] repairIngredient;

        public ShieldStats shieldStats(String material, String[] repairIngredient) {
            this.material = material;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}
