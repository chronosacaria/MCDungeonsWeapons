package chronosacaria.mcdw.enchants.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GoalUtils {

    @Nullable
    public static LivingEntity getOwner(HorseBaseEntity horseBaseEntity){
        try{
            UUID ownerUniqueId = horseBaseEntity.getOwnerUuid();
            return ownerUniqueId == null ? null : horseBaseEntity.world.getPlayerByUuid(ownerUniqueId);
        }catch (IllegalArgumentException var2) {
            return null;
        }
    }

    public static boolean shouldAttackEntity(LivingEntity target, LivingEntity owner){
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof WolfEntity){
                WolfEntity wolfEntity = (WolfEntity)target;
                if (wolfEntity.isTamed() && wolfEntity.getOwner() == owner) {
                    return false;
                }
            }
            /*if (target instanceof IronGolemEntity){
                IronGolemEntity ironGolemEntity = (IronGolemEntity)target;
                if (ironGolemEntity.isPlayerCreated() && getOwner(ironGolemEntity) == owner) {
                    return false;
                }
            }
            if (target instanceof LlamaEntity) {
                LlamaEntity llamaEntity = (LlamaEntity)target;
                if (llamaEntity.isTame() && getOwner(llamaEntity) == owner) {
                    return false;
                }
            }
            if (target instanceof BatEntity) {
                BatEntity llamaEntity = (BatEntity)target;
                if (getOwner(llamaEntity) == owner) {
                    return false;
                }
            }
            if (target instanceof BeeEntity) {
                BeeEntity llamaEntity = (BeeEntity)target;
                if (getOwner(llamaEntity) == owner) {
                    return false;
                }
            }*/

            if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).tryAttack((PlayerEntity)target)){
                return false;
            } else if (target instanceof  HorseBaseEntity && ((HorseBaseEntity)target).isTame()){
                return false;
            } else {
                return !(target instanceof CatEntity) || !((CatEntity)target).isTamed();
            }
        } else {
            return false;
        }
    }
}
