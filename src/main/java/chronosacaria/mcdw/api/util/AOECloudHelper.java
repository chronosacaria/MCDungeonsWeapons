package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.api.interfaces.IExclusiveAOECloud;
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
                center.getWorld(), center.getX(), center.getY(), center.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(
                statusEffect, 60, amplifier));
        center.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    public static void spawnPickyStatusCloud(LivingEntity user, LivingEntity center,
                                             StatusEffect statusEffect, int duration, int amplifier,
                                             boolean exclOwner, boolean exclAllies, boolean exclEnemy) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                center.getWorld(), center.getX(), center.getY(), center.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(
                statusEffect, duration, amplifier));
        ((IExclusiveAOECloud) areaEffectCloudEntity).mcdw$setExclusions(exclOwner, exclAllies, exclEnemy);
        center.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    //Exploding
    public static void spawnExplosionCloud(LivingEntity user, LivingEntity target, float radius) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                target.getWorld(), target.getX(), target.getY(), target.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(0);
        user.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    //Regen Arrow
    public static void spawnRegenCloudAtPos(LivingEntity user, boolean arrow, BlockPos blockPos, int amplifier) {
        int inGroundMitigator = arrow ? 1 : 0;
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                user.getWorld(), blockPos.getX(), blockPos.getY() + inGroundMitigator, blockPos.getZ());
        areaEffectCloudEntity.setOwner(user);
        areaEffectCloudEntity.setRadius(5.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(60);
        StatusEffectInstance regeneration = new StatusEffectInstance(StatusEffects.REGENERATION, 100, amplifier);
        areaEffectCloudEntity.addEffect(regeneration);
        ((IExclusiveAOECloud) areaEffectCloudEntity).mcdw$setExclusions(false, false, true);
        user.getWorld().spawnEntity(areaEffectCloudEntity);
    }
}