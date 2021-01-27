package chronosacaria.mcdw.api.util;

import net.minecraft.entity.*;
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
    public static void pullInNearbyEntities(LivingEntity user, Entity target, int distance){
        World world = target.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, (LivingEntity) target, nearbyEntity));
        for (LivingEntity nearbyEntity : nearbyEntities){
            double motionX = target.getX() - (nearbyEntity.getX());
            double motionY = target.getX() - (nearbyEntity.getY());
            double motionZ = target.getX() - (nearbyEntity.getZ());
            Vec3d vec3d = new Vec3d(motionX, motionY, motionZ);

            nearbyEntity.setVelocity(vec3d);
        }
    } //GRAVITY END

    //THUNDERING BEGIN
    public static void createVisualLightningBoltOnEntity(LivingEntity user, Entity target){
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(target.world);
        if (lightningEntity != null){
            lightningEntity.pushAwayFrom(user);
            lightningEntity.teleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            target.world.spawnEntity(lightningEntity);
        }
    }

    /*public static void electrocute(LivingEntity user, LivingEntity target, float damageAmount){
        createVisualLightningBoltOnEntity(user, target);
        ElectricShockDamageSource lightning =
                (ElectricShockDamageSource) new ElectricShockDamageSource(user).setUsesMagic();
        target.damage(lightning, damageAmount);
    }*/

    public static void electrocuteNearbyEnemies(LivingEntity user, Entity target, int distance,
                                                float damageAmount){
        createVisualLightningBoltOnEntity(user, target);
        World world = user.getEntityWorld();
        DamageSource explosion = DamageSource.explosion(user);

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper
                        .canApplyToEnemy(user, (LivingEntity) target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(explosion, damageAmount);
        }
    } //THUNDERING END

    //EXPLODING BEGIN
    public static void causeExplosionAttack(LivingEntity user, LivingEntity target, float damageAmount, float distance){
        World world = target.getEntityWorld();
        DamageSource explosion = DamageSource.explosion(user);

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper
                        .canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
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
            double motionX = target.getX() - (nearbyEntity.getX());
            double motionY = target.getX() - (nearbyEntity.getY());
            double motionZ = target.getX() - (nearbyEntity.getZ());
            Vec3d vec3d = new Vec3d(motionX, motionY, motionZ);

            nearbyEntity.setVelocity(vec3d);

            nearbyEntity.setVelocity(vec3d);
            nearbyEntity.addStatusEffect(chained);
        }//END CHAINING
    }

    public static void weakenNearbyEntities(LivingEntity user, Entity target, int distance, int amplifier){
        World world = target.getEntityWorld();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, (LivingEntity) target, nearbyEntity));

        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            StatusEffectInstance weakness = new StatusEffectInstance(StatusEffects.WEAKNESS, 100, amplifier);
            nearbyEntity.addStatusEffect(weakness);
        }
    }

    public static void causeEchoAttack(LivingEntity user, Entity target, float damageAmount, float distance
            , int echoLevel){
        World world = target.getEntityWorld();
        DamageSource echo = DamageSource.mob(user);
        if (user instanceof PlayerEntity){
            echo = DamageSource.player((PlayerEntity)user);
        }
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, (LivingEntity) target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            if (nearbyEntity == null) return;
            nearbyEntity.damage(echo, damageAmount);
            echoLevel--;
            if (echoLevel <=0) return;
        }
    }
    public static void causeSwirlingAttack(PlayerEntity user, LivingEntity target, float damageAmound, float distance){
        World world = target.getEntityWorld();
        DamageSource swirling = DamageSource.mob(user);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(swirling, damageAmound);
        }
    }

    public static void causeShockwaveAttack(PlayerEntity user, LivingEntity target, float damageAmound, float distance){
        World world = target.getEntityWorld();
        DamageSource shockwave = DamageSource.explosion(user);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(shockwave, damageAmound);
        }
    }

    public static void causeSmitingAttack(PlayerEntity user, LivingEntity target, float damageAmount, float distance){
        World world = target.getEntityWorld();
        DamageSource smiting = DamageSource.explosion(user);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, target, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(smiting, damageAmount);
        }
    }

    public static void causeExplosionAttackAtPos(LivingEntity user, boolean arrow, BlockPos blockPos,
                                                 float damageAmount, float distance){
        int inGroundMitigator = arrow ? 1:0;
        World world = user. getEntityWorld();
        DamageSource explosion;
        explosion = DamageSource.explosion(user);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(
                        blockPos.getX() - distance,
                        blockPos.getY() + inGroundMitigator - distance,
                        blockPos.getZ() - distance,
                        blockPos.getX() + distance,
                        blockPos.getY() + inGroundMitigator + distance,
                        blockPos.getZ() + distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(user, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(explosion, damageAmount);
        }


    }
}
