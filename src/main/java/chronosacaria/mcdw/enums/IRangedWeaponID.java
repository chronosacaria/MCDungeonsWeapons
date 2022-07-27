package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;

import java.util.HashMap;

public interface IRangedWeaponID extends IMcdwWeaponID {

    static IRangedWeaponID[] values() {
        return IMcdwWeaponID.rangedValues();
    }

    HashMap<IRangedWeaponID, IRangedWeaponID.RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    IRangedWeaponID.RangedStats getWeaponItemStats();

    IRangedWeaponID.RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    String getMaterial();
    int getDrawSpeed();
    float getRange();

    class RangedStats {
        public String material;
        public int drawSpeed;
        public float range;

        public RangedStats rangedStats(String material, int drawSpeed, float range) {
            this.material = material;
            this.drawSpeed = drawSpeed;
            this.range = range;
            return this;
        }
    }
}
