package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.goals.GoalUtils;
import chronosacaria.mcdw.enums.SettingsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.HorseBaseEntity;
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

    public static boolean isPetOf(LivingEntity self, LivingEntity owner){
        if (self instanceof TameableEntity pet)
            return pet.getOwner() == owner;
        else if(self instanceof HorseBaseEntity horseBaseEntity)
            return GoalUtils.getOwner(horseBaseEntity) == owner;
        else
            return false;
    }

    private static boolean isVillagerOrIronGolem(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    public static boolean isAllyOf(LivingEntity self, LivingEntity other) {
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
        if (entity instanceof PlayerEntity player) {
            if (player.isCreative()) return true;
            return Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.AREA_OF_EFFECT_ENCHANTS_DONT_AFFECT_PLAYERS);
        }
        return false;
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