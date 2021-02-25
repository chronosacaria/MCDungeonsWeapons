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

    public static boolean isPetOfUser(LivingEntity possibleOwner, LivingEntity possiblePet){
        if (possiblePet instanceof TameableEntity){
            TameableEntity pet = (TameableEntity) possiblePet;
            return pet.getOwner() == possibleOwner;
        }
        //if(possiblePet instanceof IronGolemEntity){
        //    IronGolemEntity ironGolem = (IronGolemEntity) possiblePet;
        //    return GoalUtils.getOwner(ironGolem) == possibleOwner;
        //}
        if(possiblePet instanceof HorseBaseEntity){
            HorseBaseEntity horseBaseEntity = (HorseBaseEntity) possiblePet;
            return GoalUtils.getOwner(horseBaseEntity) == possibleOwner;
        }

        //if(possiblePet instanceof BatEntity){
        //    BatEntity batEntity = (BatEntity) possiblePet;
        //    return GoalUtils.getOwner(batEntity) == possibleOwner;
        //}
        //if(possiblePet instanceof BeeEntity){
        //    BeeEntity beeEntity = (BeeEntity) possiblePet;
        //    return GoalUtils.getOwner(beeEntity) == possibleOwner;
        //}
        //if(possiblePet instanceof SheepEntity){
        //    SheepEntity sheepEntity = (SheepEntity) possiblePet;
        //    return GoalUtils.getOwner(sheepEntity) == possibleOwner;
        //}

        return false;
    }

    private static boolean isVillagerOrIronGolem(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    private static boolean isNotTargetOrAttacker(LivingEntity user, LivingEntity target, LivingEntity nearbyEntity){
        return nearbyEntity != target
                && nearbyEntity != user;
    }

    private static boolean isNotPlayerOrCanApplyToPlayers(LivingEntity nearbyEntity){
        if (!(nearbyEntity instanceof PlayerEntity)){
            return true;
        } else {
            return McdwEnchantsConfig.getValue("aoe_dont_affect_players");
        }
    }

    public static boolean canHealEntity(LivingEntity healer, LivingEntity nearbyEntity){
        return nearbyEntity != healer
                && isAlly(healer, nearbyEntity)
                && isAliveAndCanBeSeen(nearbyEntity, healer);
    }

    private static boolean isAlly (LivingEntity healer, LivingEntity nearbyEntity){
        return isPetOfUser(healer, nearbyEntity)
                || isVillagerOrIronGolem(nearbyEntity)
                || healer.isTeammate(nearbyEntity);
    }

    private static boolean isAliveAndCanBeSeen (LivingEntity nearbyEntity, LivingEntity user){
        return nearbyEntity.isAlive() && user.canSee(nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity user, LivingEntity target, LivingEntity nearbyEntity) {
        return isNotTargetOrAttacker(user, target, nearbyEntity)
                && isAliveAndCanBeSeen(nearbyEntity, user)
                && !isAlly(user, nearbyEntity)
                && isNotPlayerOrCanApplyToPlayers(nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity attacker, LivingEntity nearbyEntity) {
        return nearbyEntity != attacker
                && isAliveAndCanBeSeen(nearbyEntity, attacker)
                && !isAlly(attacker, nearbyEntity)
                && isNotPlayerOrCanApplyToPlayers(nearbyEntity);
    }

    // Have to figure out how to access targetSelector or figure out a different way to do this...
    //public static void sendIntoWildRage(MobEntity mobEntity){
    //    mobEntity.targetSelector.add(0, new WildRageAttackGoal(mobEntity));
    //}



}
