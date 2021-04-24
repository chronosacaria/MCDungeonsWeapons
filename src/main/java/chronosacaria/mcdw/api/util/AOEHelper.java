package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.damagesource.ElectricShockDamageSource;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
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

    //GRAVITY BEGIN
    public static void pullInNearbyEntities(LivingEntity user, Entity target, int distance) {
        World world = target.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));
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
        World world = user.getEntityWorld();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isAoeTarget(nearbyEntity, user));
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
        World world = target.getEntityWorld();
        DamageSource explosion = DamageSource.explosion(user);

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));
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
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));

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

    public static void causeEchoAttack(LivingEntity user, LivingEntity target, float distance, int echoLevel,
                                       float amount) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.damage(DamageSource.GENERIC, amount);
            echoLevel--;
            if (echoLevel <= 0) return;
        }
    }

    public static void causeSwirlingAttack(PlayerEntity user, LivingEntity target, float distance, float amount) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.damage(DamageSource.GENERIC, amount * 0.5F);
        }
    }

    public static void causeShockwaveAttack(PlayerEntity user, LivingEntity target, float distance, float amount) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity == null) return;
            if (nearbyEntity instanceof PlayerEntity && ((PlayerEntity) nearbyEntity).abilities.creativeMode) return;
            nearbyEntity.damage(DamageSource.GENERIC, amount * 0.25f);
        }
    }

    public static void causeSmitingAttack(PlayerEntity user, LivingEntity target, float distance, float amount) {
        World world = target.getEntityWorld();
        float h = target.getHealth();
        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(target.getBlockPos()).expand(distance),
            (nearbyEntity) -> nearbyEntity != target && AbilityHelper.isAoeTarget(nearbyEntity, user));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (nearbyEntity.isUndead()) {
                nearbyEntity.damage(DamageSource.MAGIC, amount * 1.25F);
            }
        }
    }
}
