package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.HashMap;

public interface IShieldID extends IMcdwWeaponID {

    HashMap<IShieldID, ShieldStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ShieldStats getWeaponItemStats();

    ShieldStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ToolMaterial getMaterial();

    class ShieldStats {
        String material;

        public ShieldStats shieldStats(String material) {
            this.material = material;
            return this;
        }
    }

}
