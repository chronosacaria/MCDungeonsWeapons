package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.damagesource.ElectricShockDamageSource;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public class AOEHelper {

    /** Returns targets of an AOE effect from 'attacker' around 'center'. This includes 'center'. */
    public static List<LivingEntity> getAoeTargets(LivingEntity center, LivingEntity attacker, float distance) {
        return center.getEntityWorld().getEntitiesByClass(LivingEntity.class,
            new Box(center.getBlockPos()).expand(distance),
            (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, attacker, center)
        );
    }

    public static void pullTowards(Entity self, Entity target) {
        if (self instanceof PlayerEntity && ((PlayerEntity) self).abilities.creativeMode) return;
        double motionX = target.getX() - self.getX();
        double motionY = target.getX() - self.getY();
        double motionZ = target.getX() - self.getZ();
        Vec3d vec3d = new Vec3d(motionX, motionY, motionZ);

        self.setVelocity(vec3d);
    }

    //GRAVITY BEGIN
    public static void pullInNearbyEntities(LivingEntity user, LivingEntity target, float distance) {
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target) {
                pullTowards(nearbyEntity, target);
            }
        }
    } //GRAVITY END

    //THUNDERING BEGIN
    public static void createVisualLightningBoltOnEntity(Entity target) {
        World world = target.getEntityWorld();
        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
        if (lightningEntity != null){
            lightningEntity.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
            lightningEntity.setCosmetic(true);
            world.spawnEntity(lightningEntity);
        }
    }

    public static void electrocute(LivingEntity attacker, LivingEntity victim, float damageAmount){
        createVisualLightningBoltOnEntity(victim);
        ElectricShockDamageSource electricShockDamageSource =
                (ElectricShockDamageSource) new ElectricShockDamageSource(attacker).setUsesMagic();
        victim.damage(electricShockDamageSource, damageAmount);
    }

    public static void electrocuteNearbyEnemies(LivingEntity user, float distance, float damageAmount, int limit){
        List<LivingEntity> nearbyEntities = getAoeTargets(user, user, distance);
        if (nearbyEntities.isEmpty()) return;
        if (limit > nearbyEntities.size()) limit = nearbyEntities.size();
        user.world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                SoundCategory.WEATHER, 1.0F, 1.0F);
        user.world.playSound(null, user.getX(), user.getY(), user.getZ(),SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                SoundCategory.WEATHER, 1.0F, 1.0F);
        for (int i = 0; i < limit; i++){
            if (nearbyEntities.size() >= i + 1){
                LivingEntity nearbyEntity = nearbyEntities.get(i);
                electrocute(user, nearbyEntity, damageAmount);
            }
        }
    } //THUNDERING END

    //EXPLODING BEGIN
    public static void causeExplosionAttack(LivingEntity user, LivingEntity target, float damageAmount, float distance) {
        DamageSource explosion = DamageSource.explosion(user);
        if (!(target instanceof LivingEntity)){
            return;
        }
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) continue;
            nearbyEntity.damage(explosion, damageAmount);
        }
    }//EXPLODING END

    //CHAINING BEGIN
    public static void chainNearbyEntities(LivingEntity user, LivingEntity target, float distance, int timeMultiplier) {
        StatusEffectInstance chained = new StatusEffectInstance(StatusEffects.SLOWNESS, 100 * timeMultiplier, 100);
        target.addStatusEffect(chained);
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target) {
                pullTowards(nearbyEntity, target);
                nearbyEntity.addStatusEffect(chained);
            }
        }//END CHAINING
    }

    public static void causeEchoAttack(LivingEntity user, LivingEntity target, float distance, int echoLevel,
                                       float amount) {
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target) {
                if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) continue;
                nearbyEntity.damage(DamageSource.GENERIC, amount);
                echoLevel--;
                if (echoLevel <= 0) break;
            }
        }
    }

    public static void causeSwirlingAttack(PlayerEntity user, LivingEntity target, float distance, float amount) {
        for (LivingEntity nearbyEntity : getAoeTargets(user, user, distance)) {
            if (nearbyEntity != user) {
                if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) continue;
                nearbyEntity.damage(DamageSource.GENERIC, amount * 0.5F);
            }
        }
    }

    public static void causeShockwaveAttack(PlayerEntity user, LivingEntity target, float distance, float amount) {
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target) {
                if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) continue;
                nearbyEntity.damage(DamageSource.GENERIC, amount * 0.25f);
            }
        }
    }

    public static void causeSmitingAttack(PlayerEntity user, LivingEntity target, float distance, float amount) {
        for (LivingEntity nearbyEntity : getAoeTargets(target, user, distance)) {
            if (nearbyEntity != target && nearbyEntity.isUndead()) {
                nearbyEntity.damage(DamageSource.MAGIC, amount * 1.25F);
            }
        }
    }
}
