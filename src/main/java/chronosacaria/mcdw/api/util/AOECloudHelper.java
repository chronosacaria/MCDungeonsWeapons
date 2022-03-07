package chronosacaria.mcdw.api.util;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class AOECloudHelper {

    public static void spawnStatusCloud(LivingEntity user, LivingEntity center, StatusEffect statusEffect, int amplifier) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                center.world, center.getX(), center.getY(), center.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(
                statusEffect, 60, amplifier));
        center.world.spawnEntity(areaEffectCloudEntity);
    }

    public static void spawnStatusCloud(LivingEntity user, LivingEntity center, StatusEffect statusEffect, int duration, int amplifier) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                center.world, center.getX(), center.getY(), center.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(
                statusEffect, duration, amplifier));
        center.world.spawnEntity(areaEffectCloudEntity);
    }

    //Exploding
    public static void spawnExplosionCloud(LivingEntity user, LivingEntity target, float radius) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                target.world, target.getX(), target.getY(), target.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        user.world.spawnEntity(areaEffectCloudEntity);
    }

    //Regen Arrow
    public static void spawnRegenCloudAtPos(LivingEntity user, boolean arrow, BlockPos blockPos, int amplifier) {
        int inGroundMitigator = arrow ? 1 : 0;
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                user.world, blockPos.getX(), blockPos.getY() + inGroundMitigator, blockPos.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        StatusEffectInstance regeneration = new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier);
        areaEffectCloudEntity.addEffect(regeneration);
        user.world.spawnEntity(areaEffectCloudEntity);
    }
}
