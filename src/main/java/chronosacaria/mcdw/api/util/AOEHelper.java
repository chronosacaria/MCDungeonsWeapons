package chronosacaria.mcdw.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.function.Predicate;


public class AOEHelper {

    /** Returns targets of an AOE effect from 'attacker' around 'center'. This includes 'center'. */
    public static List<LivingEntity> getEntitiesByPredicate(LivingEntity center, float distance, Predicate<? super LivingEntity> predicate) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(center.getBlockPos()).expand(distance), predicate
        );
    }

    public static List<? extends LivingEntity> getEntitiesByPredicate(Class<? extends LivingEntity> entityType,
                                                                      LivingEntity center, float distance, Predicate<? super LivingEntity> predicate) {
        return center.getEntityWorld().getEntitiesByClass(entityType,
                new Box(center.getBlockPos()).expand(distance), predicate
        );
    }

    public static void afflictNearbyEntities(LivingEntity user, StatusEffectInstance... statusEffectInstances) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(user, 5,
                (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())){
            for (StatusEffectInstance instance : statusEffectInstances)
                nearbyEntity.addStatusEffect(instance);
        }
    }

    public static void afflictNearbyEntities(Class<? extends LivingEntity> entityType, LivingEntity user, float distance,
                                             Predicate<? super LivingEntity> predicate, StatusEffectInstance... statusEffectInstances) {
        for (LivingEntity nearbyEntity : getEntitiesByPredicate(entityType, user, distance, predicate)) {
            for (StatusEffectInstance instance : statusEffectInstances)
                nearbyEntity.addStatusEffect(instance);
        }
    }

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
