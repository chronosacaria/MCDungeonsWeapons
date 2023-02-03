package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.goals.GoalUtils;
import chronosacaria.mcdw.enums.SettingsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.VillagerEntity;
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

    public static boolean isPetOf(LivingEntity owner, LivingEntity animal){
        if (animal instanceof TameableEntity pet)
            return pet.getOwner() == owner;
        else if (animal instanceof AbstractHorseEntity horseBaseEntity)
            return GoalUtils.getOwner(horseBaseEntity) == owner;
        else
            return false;
    }

    public static boolean isTrueAlly(LivingEntity self, LivingEntity foreignEntity) {
        return self.isTeammate(foreignEntity)
                || isPetOf(self, foreignEntity)
                || isVillagerTyped(foreignEntity);
    }

    public static boolean isPotentialAlly(LivingEntity foreignEntity) {
        return isPet(foreignEntity)
                || isVillagerTyped(foreignEntity)
                || foreignEntity instanceof PlayerEntity;
    }


    private static boolean isPet(LivingEntity animal) {
        if (animal instanceof TameableEntity pet)
            return pet.getOwner() != null;
        else if (animal instanceof AbstractHorseEntity horseBaseEntity)
            return GoalUtils.getOwner(horseBaseEntity) != null;
        return false;
    }

    private static boolean isVillagerTyped(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    public static boolean isAoeTarget(LivingEntity self, LivingEntity foreignEntity) {
        return foreignEntity != self
                && foreignEntity.isAlive()
                && isAffectedByAoe(foreignEntity)
                && self.canSee(foreignEntity);
    }

    public static boolean isAoeTarget(LivingEntity center, LivingEntity owner, LivingEntity foreignEntity) {
        return foreignEntity != owner
                && foreignEntity.isAlive()
                && isAffectedByAoe(foreignEntity)
                && center.canSee(foreignEntity);
    }

    private static boolean isAffectedByAoe(LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            if (player.isCreative()) return false;
            return !Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS);
        }
        return true;
    }

    public static float getAnimaRepairAmount(float experience, int level) {
        experience *= 0.2 * level;
        return experience;
    }

    public static boolean entityCanCrit(LivingEntity livingEntity) {
        return !livingEntity.isClimbing()
                && !livingEntity.isTouchingWater()
                && !livingEntity.isOnGround()
                && !livingEntity.isSprinting()
                && !livingEntity.hasVehicle()
                && !livingEntity.hasStatusEffect(StatusEffects.BLINDNESS)
                && livingEntity.fallDistance > 0;
    }
}