package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.goals.GoalUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class AbilityHelper {

    public static void stealSpeedFromTarget(LivingEntity user, LivingEntity target, int amplifier){
        StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 80, amplifier);
        StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 80, amplifier);
        user.addStatusEffect(speed);
        target.addStatusEffect(slowness);

    }

    public static void causeFreesing(LivingEntity target, int amplifier){
        StatusEffectInstance freezing = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, amplifier);
        StatusEffectInstance miningFatigue = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 60, amplifier);
        target.addStatusEffect(freezing);
        target.addStatusEffect(miningFatigue);
    }

    public static void causeFuseShot(LivingEntity user, LivingEntity target, int level){
        float explodingDamage;
        explodingDamage = target.getMaxHealth() * 0.2f * level;
        target.world.playSound(
                null,
                target.getX(),
                target.getY(),
                target.getZ(),
                SoundEvents.ENTITY_GENERIC_EXPLODE,
                SoundCategory.PLAYERS,
                0.5F,
                1.0F);
        AOECloudHelper.spawnExplosionCloud(user, target, 3.0F);
        AOEHelper.causeExplosionAttack(user, target, explodingDamage, 3.0F);
    }

    public static boolean isPetOf(LivingEntity self, LivingEntity owner){
        if (self instanceof TameableEntity){
            TameableEntity pet = (TameableEntity) self;
            return pet.getOwner() == owner;
        }
        //if(self instanceof IronGolemEntity){
        //    IronGolemEntity ironGolem = (IronGolemEntity) self;
        //    return GoalUtils.getOwner(ironGolem) == owner;
        //}
        if(self instanceof HorseBaseEntity){
            HorseBaseEntity horseBaseEntity = (HorseBaseEntity) self;
            return GoalUtils.getOwner(horseBaseEntity) == owner;
        }

        //if(self instanceof BatEntity){
        //    BatEntity batEntity = (BatEntity) self;
        //    return GoalUtils.getOwner(batEntity) == owner;
        //}
        //if(self instanceof BeeEntity){
        //    BeeEntity beeEntity = (BeeEntity) self;
        //    return GoalUtils.getOwner(beeEntity) == owner;
        //}
        //if(self instanceof SheepEntity){
        //    SheepEntity sheepEntity = (SheepEntity) self;
        //    return GoalUtils.getOwner(sheepEntity) == owner;
        //}

        return false;
    }

    private static boolean isVillagerOrIronGolem(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    public static boolean canHealEntity(LivingEntity healer, LivingEntity nearbyEntity){
        return nearbyEntity != healer
                && isAllyOf(healer, nearbyEntity)
                && isAliveAndCanBeSeen(nearbyEntity, healer);
    }

    private static boolean isAllyOf(LivingEntity self, LivingEntity other) {
        return self.isTeammate(other)
            || isPetOf(self, other)
            || isVillagerOrIronGolem(other);
    }

    private static boolean isAliveAndCanBeSeen (LivingEntity nearbyEntity, LivingEntity user){
        return nearbyEntity.isAlive() && user.canSee(nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity user, LivingEntity target, LivingEntity nearbyEntity) {
        return nearbyEntity != target && canApplyToEnemy(user, nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity attacker, LivingEntity nearbyEntity) {
        return nearbyEntity != attacker
                && isAliveAndCanBeSeen(nearbyEntity, attacker)
                && !isAllyOf(attacker, nearbyEntity)
                && !isUnaffectedByAoe(nearbyEntity);
    }

    private static boolean isUnaffectedByAoe(LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.isCreative()) return true;
            return McdwEnchantsConfig.getValue("aoe_dont_affect_players");
        }

        return false;
    }

    // Have to figure out how to access targetSelector or figure out a different way to do this...
    //public static void sendIntoWildRage(MobEntity mobEntity){
    //    mobEntity.targetSelector.add(0, new WildRageAttackGoal(mobEntity));
    //}



}
