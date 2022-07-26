package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;

import java.util.HashMap;

public interface IMeleeWeaponID extends IMcdwWeaponID {

    static IMeleeWeaponID[] values() {
        return IMcdwWeaponID.meleeValues();
    }

    HashMap<IMeleeWeaponID, MeleeWeaponStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    MeleeWeaponStats getWeaponItemStats();

    MeleeWeaponStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    int getDamage();

    String getMaterial();

    float getAttackSpeed();
}

/* Put in McdwNewStatsConfig.java to eventually replace for loop hell :( not working now */

/* Arrays.stream(IMeleeWeaponID.values()).forEach(iMeleeWeaponID ->
        {
            iMeleeWeaponID.getWeaponStats(this).put(iMeleeWeaponID, new MeleeWeaponStats());

        }); */
