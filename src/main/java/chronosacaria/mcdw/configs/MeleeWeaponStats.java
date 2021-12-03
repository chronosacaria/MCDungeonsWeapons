package chronosacaria.mcdw.configs;

import net.minecraft.item.ToolMaterial;

public class MeleeWeaponStats {
    // Sword Stats
    public int damage;
    public float attackSpeed;
    public ToolMaterial toolMaterial;

    public MeleeWeaponStats meleeWeaponStats (int damage, float attackSpeed, ToolMaterial toolMaterial) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.toolMaterial = toolMaterial;
        return this;
    }
}
