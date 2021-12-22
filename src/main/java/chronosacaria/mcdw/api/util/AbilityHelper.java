package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.goals.GoalUtils;
import chronosacaria.mcdw.enums.SettingsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;

public class AbilityHelper {

    public static void stealSpeedFromTarget(LivingEntity user, LivingEntity target, int amplifier){
        StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 80, amplifier);
        StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 80, amplifier);
        user.addStatusEffect(speed);
        target.addStatusEffect(slowness);

    }

    public static void causeFreezing(LivingEntity target, int amplifier){
        StatusEffectInstance freezing = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, amplifier);
        StatusEffectInstance miningFatigue = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 60, amplifier);
        target.addStatusEffect(freezing);
        target.addStatusEffect(miningFatigue);
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
            && nearbyEntity.isAlive()
            && healer.canSee(nearbyEntity);
    }

    private static boolean isAllyOf(LivingEntity self, LivingEntity other) {
        return self.isTeammate(other)
            || isPetOf(self, other)
            || isVillagerOrIronGolem(other);
    }

    public static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center) {
        return self != attacker
            && self.isAlive()
            && !isAllyOf(attacker, self)
            && !isUnaffectedByAoe(self)
            && center.canSee(self);
    }

    private static boolean isUnaffectedByAoe(LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.isCreative()) return true;
            return Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS);
        }

        return false;
    }
}
