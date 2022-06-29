package chronosacaria.mcdw.enchants.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

public class WildRageAttackGoal extends ActiveTargetGoal<LivingEntity> {
    public WildRageAttackGoal(MobEntity mob) {
        super(mob, LivingEntity.class, 0, true, true, LivingEntity::isMobOrPlayer);
    }

    @Override
    public boolean canStart() {
        return super.canStart();
    }

    @Override
    public void start() {
        super.start();
        this.mob.setDespawnCounter(0);
    }
}
