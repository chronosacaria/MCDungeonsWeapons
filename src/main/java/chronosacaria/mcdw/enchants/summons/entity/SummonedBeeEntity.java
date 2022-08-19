package chronosacaria.mcdw.enchants.summons.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
        if(summoner instanceof PlayerEntity summoningPlayer){
            if(summoningPlayer.getAttacker() != null){
                this.setBeeAttacker(summoningPlayer.getAttacker());
            }

            if (summoningPlayer.getAttacking() != null){
                this.setBeeAttacker(summoningPlayer.getAttacking());
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
        return !target.equals(summoner) && !this.hasStung() && super.tryAttack(target);
    }
}