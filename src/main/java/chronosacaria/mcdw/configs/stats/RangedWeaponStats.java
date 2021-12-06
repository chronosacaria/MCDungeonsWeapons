package chronosacaria.mcdw.configs.stats;

public class RangedWeaponStats {
    public float drawSpeed;
    public float range;

    public RangedWeaponStats rangedWeaponStats (float drawSpeed, float range) {
        this.drawSpeed = drawSpeed;
        this.range = range;
        return this;
    }
}
