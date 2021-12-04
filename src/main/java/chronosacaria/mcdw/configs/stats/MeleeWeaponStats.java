package chronosacaria.mcdw.configs.stats;

public class MeleeWeaponStats {
    public int damage;
    public float attackSpeed;

    public MeleeWeaponStats meleeWeaponStats (int damage, float attackSpeed) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        return this;
    }
}
