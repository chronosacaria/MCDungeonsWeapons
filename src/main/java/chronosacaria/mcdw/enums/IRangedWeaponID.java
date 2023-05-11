package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;

public interface IRangedWeaponID extends IMcdwWeaponID {

    static IRangedWeaponID[] values() {
        return IMcdwWeaponID.rangedValues();
    }

    HashMap<? extends IRangedWeaponID, IRangedWeaponID.RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    IRangedWeaponID.RangedStats getWeaponItemStats();

    IRangedWeaponID.RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ToolMaterial getMaterial();
    double getProjectileDamage();
    int getDrawSpeed();
    float getRange();
    String[] getRepairIngredient();

    class RangedStats {
        public String material;
        public double projectileDamage;
        public int drawSpeed;
        public float range;
        String[] repairIngredient;

        public RangedStats rangedStats(String material, double projectileDamage, int drawSpeed, float range, String[] repairIngredient) {
            this.material = material;
            if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
                this.projectileDamage = projectileDamage;
            } else {
                this.projectileDamage = 0;
            }
            this.drawSpeed = drawSpeed;
            this.range = range;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}
