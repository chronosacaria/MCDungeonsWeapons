package chronosacaria.mcdw.enchants.util;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.weapons.Glaives;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

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

    //Thundering
    public static void spawnLightning(
            //World world,
            LivingEntity user,
            LivingEntity target
            //int amplifier
    ) {
        Entity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, target.world);
        lightningEntity.teleport(target.getX(), target.getY(), target.getZ());
        target.world.spawnEntity(lightningEntity);
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

    //Exploding Arrow
    public static void spawnExplosionCloudAtPos(LivingEntity user, boolean arrow, BlockPos blockPos, float radius) {
        int inGroundMitigator = arrow ? 1 : 0;
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(
                user.world,
                blockPos.getX(),
                blockPos.getY() + inGroundMitigator,
                blockPos.getZ()
        );
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
}
