/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.summons.entity;

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

    public static DefaultAttributeContainer.Builder createSummonedBeeEntityAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
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
