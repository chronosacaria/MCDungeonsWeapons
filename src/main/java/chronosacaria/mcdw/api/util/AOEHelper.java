package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.damagesource.ElectricShockDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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

    public static void pullTowards(Entity self, Entity target) {
        if (self instanceof PlayerEntity && ((PlayerEntity) self).getAbilities().creativeMode) return;

        double motionX = MathHelper.clamp((target.getX() - self.getX()) * 0.15f, -5, 5);
        double motionZ = MathHelper.clamp((target.getZ() - self.getZ()) * 0.15f, -5, 5);
        Vec3d vec3d = new Vec3d(motionX, 0, motionZ);

        self.setVelocity(vec3d);
    }

    //GRAVITY BEGIN
    public static void pullInNearbyEntities(LivingEntity user, LivingEntity target, float distance) {
        getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> pullTowards(nearbyEntity, target));
    } //GRAVITY END

    //THUNDERING BEGIN
    public static void createVisualLightningBoltOnEntity(Entity target) {
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);

        if (lightningEntity != null) {
            lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocute(LivingEntity attacker, LivingEntity victim, float damageAmount) {
        createVisualLightningBoltOnEntity(victim);
        DamageSource electricShockDamageSource = new ElectricShockDamageSource(attacker).setUsesMagic();
        victim.damage(electricShockDamageSource, damageAmount);
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, float distance, float damageAmount, int limit) {
        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1f, 1f);
        CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 1f, 1f);

        for (LivingEntity nearbyEntity : getAoeTargets(user, user, distance)) {
            electrocute(user, nearbyEntity, damageAmount);

            limit--;
            if (limit <= 0) break;
        }
    } //THUNDERING END

    //EXPLODING BEGIN
    public static void causeExplosionAttack(LivingEntity user, LivingEntity target, float damageAmount, float distance) {
        getAoeTargets(target, user, distance)
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.explosion(user), damageAmount));
    } //EXPLODING END

    //CHAINING BEGIN
    public static void chainNearbyEntities(LivingEntity user, LivingEntity target, float distance, int timeMultiplier) {
        StatusEffectInstance chained = new StatusEffectInstance(StatusEffects.SLOWNESS, 100 * timeMultiplier, 100);

        target.addStatusEffect(chained);

        getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> {
                    pullTowards(nearbyEntity, target);
                    nearbyEntity.addStatusEffect(chained);
                });
    } //END CHAINING

    public static void causeEchoAttack(LivingEntity user, LivingEntity target, float distance, int echoLevel, float amount) {
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target) {
                nearbyEntity.damage(DamageSource.GENERIC, amount);

                echoLevel--;
                if (echoLevel <= 0) break;
            }
        }
    }

    public static void causeSwirlingAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        getAoeTargets(target, user, distance)
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.GENERIC, amount * 0.5F));
    }

    public static void causeShockwaveAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target)
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.GENERIC, amount * 0.25f));
    }

    public static void causeSmitingAttack(LivingEntity user, LivingEntity target, float distance, float amount) {
        getAoeTargets(target, user, distance).stream()
                .filter(nearbyEntity -> nearbyEntity != target && nearbyEntity.isUndead())
                .forEach(nearbyEntity -> nearbyEntity.damage(DamageSource.MAGIC, amount * 1.25F));
    }
}