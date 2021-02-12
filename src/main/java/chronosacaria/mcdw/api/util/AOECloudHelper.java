package chronosacaria.mcdw.api.util;

import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class AOECloudHelper {

    //Poison Cloud
    public static void spawnPoisonCloud(
            LivingEntity user,
            LivingEntity target,
            int amplifier) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                target.world,
                target.getX(),
                target.getY(),
                target.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        StatusEffectInstance poison = new StatusEffectInstance(
                StatusEffects.POISON,
                60,
                amplifier);
        areaEffectCloudEntity.addEffect(poison);
        target.world.spawnEntity(areaEffectCloudEntity);
    }


    //Exploding
    public static void spawnExplosionCloud(LivingEntity user, LivingEntity target, float radius) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                target.world,
                target.getX(),
                target.getY(),
                target.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        user.world.spawnEntity(areaEffectCloudEntity);
    }

    //Regen Cloud
    public static void spawnRegenCloud(LivingEntity user, int amplifier) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                user.world,
                user.getX(),
                user.getY(),
                user.getZ()
        );
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(100);
        StatusEffectInstance regeneration = new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier);
        areaEffectCloudEntity.addEffect(regeneration);
        user.world.spawnEntity(areaEffectCloudEntity);
    }

    //Regen Arrow
    public static void spawnRegenCloudAtPos(LivingEntity user, boolean arrow, BlockPos blockPos, int amplifier) {
        int inGroundMitigator = arrow ? 1 : 0;
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                user.world,
                blockPos.getX(),
                blockPos.getY() + inGroundMitigator,
                blockPos.getZ()
        );
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(100);
        StatusEffectInstance regeneration = new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier);
        areaEffectCloudEntity.addEffect(regeneration);
        user.world.spawnEntity(areaEffectCloudEntity);

    }

    //Poison Cloud
    public static void spawnWeakeningCloud(
            LivingEntity user,
            LivingEntity target,
            int amplifier) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                target.world,
                target.getX(),
                target.getY(),
                target.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        StatusEffectInstance weakening = new StatusEffectInstance(
                StatusEffects.WEAKNESS,
                60,
                amplifier);
        areaEffectCloudEntity.addEffect(weakening);
        target.world.spawnEntity(areaEffectCloudEntity);
    }
}
