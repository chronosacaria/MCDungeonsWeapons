package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.function.Predicate;


public class AOEHelper {

    // Owner is center
    public static List<LivingEntity> getEntitiesByConfig(LivingEntity center, float distance) {
        int permissionLevel = Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.aoePermission;
        //noinspection DuplicatedCode
        Predicate<? super LivingEntity> predicate = livingEntity -> AbilityHelper.isAoeTarget(center, livingEntity) &&
                switch (permissionLevel) {
                    case 1 -> !AbilityHelper.isTrueAlly(center, livingEntity);
                    case 2 -> !AbilityHelper.isTrueAlly(center, livingEntity) && !(livingEntity instanceof AnimalEntity);
                    case 3 -> !AbilityHelper.isPotentialAlly(livingEntity);
                    case 4 -> livingEntity instanceof HostileEntity;
                    // case 0 has no further restrictions
                    default -> true;
        };
        return getEntitiesByPredicate(center, distance, predicate);
    }

    // Owner and center are different
    public static List<LivingEntity> getEntitiesByConfig(LivingEntity center, LivingEntity owner, float distance) {
        int permissionLevel = Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.aoePermission;
        //noinspection DuplicatedCode
        Predicate<? super LivingEntity> predicate = livingEntity -> AbilityHelper.isAoeTarget(center, owner, livingEntity) &&
                switch (permissionLevel) {
                    case 1 -> !AbilityHelper.isTrueAlly(owner, livingEntity);
                    case 2 -> !AbilityHelper.isTrueAlly(owner, livingEntity) && !(livingEntity instanceof AnimalEntity);
                    case 3 -> !AbilityHelper.isPotentialAlly(livingEntity);
                    case 4 -> livingEntity instanceof HostileEntity;
                    // case 0 has no further restrictions
                    default -> true;
                };
        return getEntitiesByPredicate(center, distance, predicate);
    }

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

    public static void afflictNearbyEntities(LivingEntity user, float distance, StatusEffectInstance... statusEffectInstances) {
        for (LivingEntity nearbyEntity : getEntitiesByConfig(user, distance)) {
            for (StatusEffectInstance instance : statusEffectInstances)
                nearbyEntity.addStatusEffect(instance);
        }
    }

    public static boolean satisfySweepConditions(LivingEntity attackingEntity, Entity targetEntity, LivingEntity collateralEntity, float distanceToCollateral) {
        return collateralEntity != attackingEntity && collateralEntity != targetEntity && !attackingEntity.isTeammate(collateralEntity)
                && !(collateralEntity instanceof ArmorStandEntity armorStand && armorStand.isMarker())
                && attackingEntity.distanceTo(collateralEntity) < distanceToCollateral;
    }
}
