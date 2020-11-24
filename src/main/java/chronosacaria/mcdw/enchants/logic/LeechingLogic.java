package chronosacaria.mcdw.enchants.logic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class LeechingLogic {
    public void onTargetDamaged(LivingEntity user, Entity target, int level){
        if (!(target instanceof LivingEntity)) return;
        float damageDealt = ((LivingEntity) target).getMaxHealth() - ((LivingEntity)target).getHealth();
        //float targetMaxHealth = ((LivingEntity) target).getMaxHealth();
        float healthRegained;
        healthRegained = (0.02F + 0.02F * level) * damageDealt;
        user.heal(healthRegained);
    }
}
