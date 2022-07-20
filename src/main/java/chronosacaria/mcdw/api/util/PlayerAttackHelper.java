package chronosacaria.mcdw.api.util;

import net.minecraft.entity.damage.DamageSource;

public class PlayerAttackHelper {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isLikelyNotMeleeDamage(DamageSource damageSource){
        return damageSource.isFire()
                || damageSource.isExplosive()
                || damageSource.isMagic()
                || damageSource.isProjectile()
                || !isDirectDamage(damageSource);
    }

    private static boolean isDirectDamage(DamageSource damageSource){
        return damageSource.name.equals("mob")
                || damageSource.name.equals("player");
    }
}
