package chronosacaria.mcdw.configs.stats;

public class ShieldStats extends McdwWeaponStats {
    public String material;

    public ShieldStats shieldStats (String material) {
        this.material = material;
        return this;
    }
}
