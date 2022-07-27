package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;

import java.util.HashMap;

public interface IMeleeWeaponID extends IMcdwWeaponID {

    static IMeleeWeaponID[] values() {
        return IMcdwWeaponID.meleeValues();
    }

    HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    IMeleeWeaponID.MeleeStats getWeaponItemStats();

    IMeleeWeaponID.MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    int getDamage();

    String getMaterial();

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

/* Put in McdwNewStatsConfig.java to eventually replace for loop hell :( not working now */

/* Arrays.stream(IMeleeWeaponID.values()).forEach(iMeleeWeaponID ->
        {
            iMeleeWeaponID.getWeaponStats(this).put(iMeleeWeaponID, new MeleeWeaponStats());

        }); */
