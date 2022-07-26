package chronosacaria.mcdw.configs.stats;

public class MeleeWeaponStats {
    public String material;
    public int damage;
    public float attackSpeed;

    public MeleeWeaponStats meleeWeaponStats (String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        return this;
    }
}
