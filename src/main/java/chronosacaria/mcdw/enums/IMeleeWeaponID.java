package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;

public interface IMeleeWeaponID extends IMcdwWeaponID {

    static IMeleeWeaponID[] values() {
        return IMcdwWeaponID.meleeValues();
    }

    HashMap<? extends IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    MeleeStats getWeaponItemStats();

    MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ToolMaterial getMaterial();
    int getDamage();
    float getAttackSpeed();
    String[] getRepairIngredient();

    class MeleeStats {
        String material;
        int damage;
        float attackSpeed;
        String[] repairIngredient;

        public MeleeStats meleeStats(String material, int damage, float attackSpeed, String[] repairIngredient) {
            this.material = material;
            this.damage = damage;
            this.attackSpeed = attackSpeed;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}