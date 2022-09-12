package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
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
    float getAttackDamage();
    int getDrawSpeed();
    float getRange();
    String[] getRepairIngredient();

    class RangedStats {
        public String material;
        public float attackDamage;
        public int drawSpeed;
        public float range;
        String[] repairIngredient;

        public RangedStats rangedStats(String material, float attackDamage, int drawSpeed, float range, String[] repairIngredient) {
            this.material = material;
            this.attackDamage = attackDamage;
            this.drawSpeed = drawSpeed;
            this.range = range;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}
