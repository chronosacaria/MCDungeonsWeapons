package chronosacaria.mcdw.enchants.util;

import chronosacaria.mcdw.enchants.goals.GoalUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class AbilityHelper {
    public static boolean isPetOfUser(LivingEntity possibleOwner, LivingEntity possiblePet){
        if (possiblePet instanceof TameableEntity){
            TameableEntity pet = (TameableEntity) possiblePet;
            return pet.getOwner() == possibleOwner;
        }
        if(possiblePet instanceof HorseBaseEntity){
            HorseBaseEntity horseBaseEntity = (HorseBaseEntity) possiblePet;
            return GoalUtils.getOwner(horseBaseEntity) == possibleOwner;
        }
        /*if(possiblePet instanceof IronGolemEntity){
            IronGolemEntity ironGolem = (IronGolemEntity) possiblePet;
            return GoalUtils.getOwner(ironGolem) == possibleOwner;
        }
        if(possiblePet instanceof BatEntity){
            BatEntity batEntity = (BatEntity) possiblePet;
            return GoalUtils.getOwner(batEntity) == possibleOwner;
        }
        if(possiblePet instanceof BeeEntity){
            BeeEntity beeEntity = (BeeEntity) possiblePet;
            return GoalUtils.getOwner(beeEntity) == possibleOwner;
        }
        if(possiblePet instanceof SheepEntity){
            SheepEntity sheepEntity = (SheepEntity) possiblePet;
            return GoalUtils.getOwner(sheepEntity) == possibleOwner;
        }*/

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
        }
        else {
            return true;
            //return McdwConfig.ENABLE_AREA_OF_EFFECT_ON_PLAYERS.get();
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


}
