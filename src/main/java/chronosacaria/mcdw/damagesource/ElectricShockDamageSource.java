package chronosacaria.mcdw.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.EntityDamageSource;

public class ElectricShockDamageSource extends EntityDamageSource {
    public ElectricShockDamageSource(Entity source){
        super("electricShock", source);
    }
}