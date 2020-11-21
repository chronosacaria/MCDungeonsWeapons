package chronosacaria.mcdw.damagesources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.EntityDamageSource;

public class ElectricShockDamageSource extends EntityDamageSource {
    public  ElectricShockDamageSource(Entity damageSourceEntityIn) {
        super("electricShock", damageSourceEntityIn);
    }
}
