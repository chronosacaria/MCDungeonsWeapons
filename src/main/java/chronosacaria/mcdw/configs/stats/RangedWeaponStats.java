package chronosacaria.mcdw.configs.stats;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

public class RangedWeaponStats {
    public String material;
    public float drawSpeed;
    public float range;

    public RangedWeaponStats rangedWeaponStats (String material, float drawSpeed, float range) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
        return this;
    }
}
