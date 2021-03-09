package chronosacaria.mcdw.api.util;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public class AOEHelper {

    //GRAVITY BEGIN
    public static void pullInNearbyEntities(LivingEntity user, Entity target, int distance) {
        World world = target.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, (LivingEntity) target, nearbyEntity));
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            double motionX = target.getX() - (nearbyEntity.getX());
            double motionY = target.getX() - (nearbyEntity.getY());
            double motionZ = target.getX() - (nearbyEntity.getZ());
            Vec3d vec3d = new Vec3d(motionX, motionY, motionZ);

            nearbyEntity.setVelocity(vec3d);
        }
    } //GRAVITY END

    //THUNDERING BEGIN
    public static void createVisualLightningBoltOnEntity(LivingEntity user, Entity target) {
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(target.world);
        if (lightningEntity != null) {
            lightningEntity.pushAwayFrom(user);
            lightningEntity.teleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            target.world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, LivingEntity target, int distance) {
        createVisualLightningBoltOnEntity(user, target);
        World world = user.getEntityWorld();
        float h = target.getHealth();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper
                        .canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.setHealth(h - (10));
        }
    } //THUNDERING END

    //EXPLODING BEGIN
    public static void causeExplosionAttack(LivingEntity user, LivingEntity target, float damageAmount, float distance) {
        World world = target.getEntityWorld();
        DamageSource explosion = DamageSource.explosion(user);

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper
                        .canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.damage(explosion, damageAmount);
        }
    }//EXPLODING END

    //CHAINING BEGIN
    public static void chainNearbyEntities(LivingEntity user, LivingEntity target, float distance, int timeMultiplier) {
        World world = user.getEntityWorld();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));

        if (nearbyEntities.isEmpty()) return;
        StatusEffectInstance chained = new StatusEffectInstance(StatusEffects.SLOWNESS, 100 * timeMultiplier, 100);
        target.addStatusEffect(chained);
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            double motionX = target.getX() - (nearbyEntity.getX());
            double motionY = target.getX() - (nearbyEntity.getY());
            double motionZ = target.getX() - (nearbyEntity.getZ());
            Vec3d vec3d = new Vec3d(motionX, motionY, motionZ);

            nearbyEntity.setVelocity(vec3d);

            nearbyEntity.addStatusEffect(chained);
        }//END CHAINING
    }

    public static void causeEchoAttack(LivingEntity user, LivingEntity target, float distance, int echoLevel) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.setHealth(h - attackDamage);
            echoLevel--;
            if (echoLevel <= 0) return;
        }
    }

    public static void causeSwirlingAttack(PlayerEntity user, LivingEntity target, float distance) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.setHealth(h - (attackDamage * 0.5F));
        }
    }

    public static void causeShockwaveAttack(PlayerEntity user, LivingEntity target, float distance) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.setHealth(h - (attackDamage * 0.25F));
        }
    }

    public static void causeSmitingAttack(PlayerEntity user, LivingEntity target, float distance) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity.isUndead()) {
                nearbyEntity.setHealth(h - (attackDamage * 1.25F));
            }
        }
    }
}
