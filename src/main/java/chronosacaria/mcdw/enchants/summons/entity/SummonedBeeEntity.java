package chronosacaria.mcdw.enchants.summons.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SummonedBeeEntity extends BeeEntity {

    private Entity summoner;

    public SummonedBeeEntity(EntityType<? extends SummonedBeeEntity> type, World world){
        super(EntityType.BEE, world);
    }

    public void setSummoner(Entity user){
        summoner = user;
    }

    protected void mobTick(){
        if(summoner instanceof PlayerEntity){
            if(((PlayerEntity)summoner).getAttacker() != null){
                this.setBeeAttacker(((PlayerEntity)summoner).getAttacker());
            }

            if (((PlayerEntity)summoner).getAttacking() != null){
                this.setBeeAttacker(((PlayerEntity)summoner).getAttacking());
            }
        }
        super.mobTick();
    }

    @SuppressWarnings("UnusedReturnValue")
    private boolean setBeeAttacker(LivingEntity attacker){
        if(attacker.equals(summoner)){
            return false;
        }
        setAttacker(attacker);
        return true;
    }
    public boolean tryAttack(Entity target) {

        if(target.equals(summoner)) {
            return false;
        }
        else if (this.hasStung()){

            return false;

        }
        else {

            return super.tryAttack(target);

        }

    }

}
