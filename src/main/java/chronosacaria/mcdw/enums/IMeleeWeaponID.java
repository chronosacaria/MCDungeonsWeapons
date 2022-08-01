package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.HashMap;

public interface IMeleeWeaponID extends IMcdwWeaponID {

    static IMeleeWeaponID[] values() {
        return IMcdwWeaponID.meleeValues();
    }

    HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    MeleeStats getWeaponItemStats();

    MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ToolMaterial getMaterial();
    int getDamage();
    float getAttackSpeed();

    class MeleeStats {
        String material;
        int damage;
        float attackSpeed;

        public MeleeStats meleeStats(String material, int damage, float attackSpeed) {
            this.material = material;
            this.damage = damage;
            this.attackSpeed = attackSpeed;
            return this;
        }
    }
}
