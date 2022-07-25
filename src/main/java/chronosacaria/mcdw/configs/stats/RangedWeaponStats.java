package chronosacaria.mcdw.configs.stats;

public class RangedWeaponStats extends McdwWeaponStats {
    public String material;
    public int drawSpeed;
    public float range;

    public RangedWeaponStats rangedWeaponStats (String material, int drawSpeed, float range) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
        return this;
    }
}
