package chronosacaria.mcdw.api.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

import java.util.Iterator;
import java.util.List;

public class PlayerAttackHelper {

    public static void swingArm(ServerPlayerEntity serverPlayerEntity, Entity target){
        if (serverPlayerEntity.interactionManager.getGameMode() == GameMode.SPECTATOR){
            serverPlayerEntity.setCameraEntity(target);
        } else {
            attackTargetEntityWitCurrentOffhandItemAsSuper(serverPlayerEntity, target);
        }
    }

    public static void attackTargetEntityWitCurrentOffhandItemAsSuper (PlayerEntity player, Entity target){
        if (target.isAttackable() && !target.handleAttack(player)){
            //get attack damage attribute value
            //player.getAttributes().removeModifiers(player.getMainHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND));
            //player.getAttributes().addTemporaryModifiers(player.getOffHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND));
            float f_attackDamage = (float)player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            float h_enchantmentEffectsTargetBonus;
            if (target instanceof LivingEntity){
                h_enchantmentEffectsTargetBonus = EnchantmentHelper.getAttackDamage(player.getOffHandStack(),
                        ((LivingEntity)target).getGroup());
            } else {
                h_enchantmentEffectsTargetBonus = EnchantmentHelper.getAttackDamage(player.getOffHandStack(),
                        EntityGroup.DEFAULT);
            }

            float i_cooledAttackStrength = player.getAttackCooldownProgress(0.5F);
            f_attackDamage *= 0.2F + i_cooledAttackStrength * i_cooledAttackStrength *0.8F;
            h_enchantmentEffectsTargetBonus *= i_cooledAttackStrength;

            if (f_attackDamage > 0.0F || h_enchantmentEffectsTargetBonus > 0.0F){
                boolean bl_flag = i_cooledAttackStrength > 0.9F;
                boolean bl2_flag1 = false;
                int j = 0;
                j = j + EnchantmentHelper.getKnockback(player);
                if (player.isSprinting() && bl_flag){
                    player.world.playSound(
                            null,
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F);
                    ++j;
                    bl2_flag1 = true;
                }

                boolean bl3_flag2 = bl_flag
                        && player.fallDistance > 0.0F
                        && !player.isOnGround()
                        && !player.isHoldingOntoLadder()
                        && !player.isSwimming()
                        && !player.hasStatusEffect(StatusEffects.BLINDNESS)
                        && !player.hasVehicle()
                        && target instanceof LivingEntity;
                bl3_flag2 = bl3_flag2 && !player.isSprinting();
                if (bl3_flag2){
                    f_attackDamage *= 1.5f;
                }

                f_attackDamage += h_enchantmentEffectsTargetBonus;
                boolean bl4 = false;
                double d = (double)(player.horizontalSpeed - player.prevHorizontalSpeed);
                if (bl_flag && !bl3_flag2 && !bl2_flag1 && player.isOnGround() && d < (double)player.getMovementSpeed()) {
                    ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
                    if (itemStack.getItem() instanceof SwordItem) {
                        bl4 = true;
                    }
                }

                float k = 0.0F;
                boolean bl5 = false;
                int l = EnchantmentHelper.getFireAspect(player);
                if (target instanceof LivingEntity) {
                    k = ((LivingEntity)target).getHealth();
                    if (l > 0 && !target.isOnFire()) {
                        bl5 = true;
                        target.setOnFireFor(1);
                    }
                }

                Vec3d vec3d = target.getVelocity();
                boolean bl6 = target.damage(DamageSource.player(player), f_attackDamage);
                if (bl6) {
                    if (j > 0) {
                        if (target instanceof LivingEntity) {
                            ((LivingEntity)target).takeKnockback((float)j * 0.5F,
                                    (double) MathHelper.sin(player.getYaw() * 0.017453292F),
                                    (double)(-MathHelper.cos(player.getYaw() * 0.017453292F)));
                        } else {
                            target.addVelocity(
                                    (double)(-MathHelper.sin(player.getYaw() * 0.017453292F) * (float)j * 0.5F),
                                    0.1D,
                                    (double)(MathHelper.cos(player.getYaw() * 0.017453292F) * (float)j * 0.5F));
                        }

                        player.setVelocity(player.getVelocity().multiply(0.6D, 1.0D, 0.6D));
                        player.setSprinting(false);
                    }

                    if (bl4) {
                        float m = 1.0F + EnchantmentHelper.getSweepingMultiplier(player) * f_attackDamage;
                        List<LivingEntity> list = player.world.getNonSpectatingEntities(
                                LivingEntity.class,
                                target.getBoundingBox().expand(
                                        1.0D,
                                        0.25D,
                                        1.0D));
                        Iterator var19 = list.iterator();

                        label166:
                        while(true) {
                            LivingEntity livingEntity;
                            do {
                                do {
                                    do {
                                        do {
                                            if (!var19.hasNext()) {
                                                player.world.playSound(
                                                        (PlayerEntity)null,
                                                        player.getX(),
                                                        player.getY(),
                                                        player.getZ(),
                                                        SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                                                        player.getSoundCategory(),
                                                        1.0F,
                                                        1.0F);
                                                player.spawnSweepAttackParticles();
                                                break label166;
                                            }

                                            livingEntity = (LivingEntity)var19.next();
                                        } while(livingEntity == player);
                                    } while(livingEntity == target);
                                } while(player.isTeammate(livingEntity));
                            } while(livingEntity instanceof ArmorStandEntity && ((ArmorStandEntity)livingEntity).isMarker());

                            if (player.squaredDistanceTo(livingEntity) < 9.0D) {
                                livingEntity.takeKnockback(
                                        0.4F,
                                        (double)MathHelper.sin(
                                                player.getYaw() * 0.017453292F),
                                        (double)(-MathHelper.cos(
                                                player.getYaw() * 0.017453292F)));
                                livingEntity.damage(DamageSource.player(player), m);
                            }
                        }
                    }

                    if (target instanceof ServerPlayerEntity && target.velocityModified) {
                        ((ServerPlayerEntity)target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                        target.velocityModified = false;
                        target.setVelocity(vec3d);
                    }

                    if (bl3_flag2) {
                        player.world.playSound(
                                (PlayerEntity)null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
                                player.getSoundCategory(),
                                1.0F,
                                1.0F);
                        player.addCritParticles(target);
                    }

                    if (!bl3_flag2 && !bl4) {
                        if (bl_flag) {
                            player.world.playSound(
                                    (PlayerEntity)null,
                                    player.getX(),
                                    player.getY(),
                                    player.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
                                    player.getSoundCategory(),
                                    1.0F,
                                    1.0F);
                        } else {
                            player.world.playSound(
                                    (PlayerEntity)null,
                                    player.getX(),
                                    player.getY(),
                                    player.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
                                    player.getSoundCategory(),
                                    1.0F,
                                    1.0F);
                        }
                    }

                    if (h_enchantmentEffectsTargetBonus > 0.0F) {
                        player.addEnchantedHitParticles(target);
                    }

                    player.onAttacking(target);
                    if (target instanceof LivingEntity) {
                        EnchantmentHelper.onUserDamaged((LivingEntity)target, player);
                    }

                    EnchantmentHelper.onTargetDamaged(player, target);
                    ItemStack itemStack2 = player.getMainHandStack();
                    Entity entity = target;
                    if (target instanceof EnderDragonPart) {
                        entity = ((EnderDragonPart)target).owner;
                    }

                    if (!player.world.isClient && !itemStack2.isEmpty() && entity instanceof LivingEntity) {
                        itemStack2.postHit((LivingEntity)entity, player);
                        if (itemStack2.isEmpty()) {
                            player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                        }
                    }

                    if (target instanceof LivingEntity) {
                        float n = k - ((LivingEntity)target).getHealth();
                        player.increaseStat(Stats.DAMAGE_DEALT, Math.round(n * 10.0F));
                        if (l > 0) {
                            target.setOnFireFor(l * 4);
                        }

                        if (player.world instanceof ServerWorld && n > 2.0F) {
                            int o = (int)((double)n * 0.5D);
                            ((ServerWorld)player.world).spawnParticles(
                                    ParticleTypes.DAMAGE_INDICATOR,
                                    target.getX(),
                                    target.getBodyY(0.5D),
                                    target.getZ(),
                                    o,
                                    0.1D,
                                    0.0D,
                                    0.1D,
                                    0.2D);
                        }
                    }

                    player.addExhaustion(0.1F);
                } else {
                    player.world.playSound(
                            (PlayerEntity)null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE,
                            player.getSoundCategory(),
                            1.0F,
                            1.0F);
                    if (bl5) {
                        target.extinguish();
                    }
                }

            }
        }
    }

    public static boolean isLikelyNotMeleeDamage(DamageSource damageSource){
        return damageSource.isFire()
                || damageSource.isExplosive()
                || damageSource.isMagic()
                || damageSource.isProjectile()
                || !isDirectDamage(damageSource);
    }

    private static boolean isDirectDamage(DamageSource damageSource){
        return damageSource.name.equals("mob")
                || damageSource.name.equals("player");
    }
}
