package chronosacaria.mcdw.enchants.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class WildRageAttackGoal extends FollowTargetGoal<LivingEntity> {
    public WildRageAttackGoal(MobEntity mobEntity){
        super (mobEntity, LivingEntity.class, 0, true, true, LivingEntity::isMobOrPlayer);
    }

    public boolean shouldExecute(){
        return (super.shouldContinue());
    }

    public void startExecuting(){
        super.start();
        this.mob.setDespawnCounter(0);
    }
}
