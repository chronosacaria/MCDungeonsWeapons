package chronosacaria.mcdw.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.Box;

import java.util.List;


public class AOEHelper {

    /**
     * Returns targets of an AOE effect from 'attacker' around 'center'. This includes 'center'.
     */
    public static List<LivingEntity> getAoeTargets(LivingEntity center, LivingEntity attacker, float distance) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(center.getBlockPos()).expand(distance),
                nearbyEntity -> AbilityHelper.isAoeTarget(nearbyEntity, attacker, center)
        );
    }

    public static boolean satisfySweepConditions(LivingEntity attackingEntity, Entity targetEntity, LivingEntity collateralEntity, float distanceToCollateral) {
        return collateralEntity != attackingEntity && collateralEntity != targetEntity && !attackingEntity.isTeammate(collateralEntity)
                && !(collateralEntity instanceof ArmorStandEntity armorStand && armorStand.isMarker())
                && attackingEntity.distanceTo(collateralEntity) < distanceToCollateral;
    }
}
