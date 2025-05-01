/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.util;

import dev.timefall.mcdw.api.interfaces.IDualWielding;
import dev.timefall.mcdw.api.interfaces.IOffhandAttack;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.registries.items.McdwDaggerItemRegistry;
import dev.timefall.mcdw.registries.items.McdwSickleItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PlayerAttackHelper {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean mcdw$isLikelyNotMeleeDamage(DamageSource damageSource){
        return damageSource.isOf(DamageTypes.ON_FIRE)
                || damageSource.isOf(DamageTypes.EXPLOSION)
                || damageSource.isOf(DamageTypes.MAGIC)
                || damageSource.isOf(DamageTypes.ARROW)
                || !mcdw$isDirectDamage(damageSource);
    }

    private static boolean mcdw$isDirectDamage(DamageSource damageSource){
        return damageSource.isOf(DamageTypes.MOB_ATTACK)
                || damageSource.isOf(DamageTypes.PLAYER_ATTACK);
    }

    //public static void mcdw$switchModifiers(PlayerEntity player, ItemStack switchFrom, ItemStack switchTo) {
    //    player.getAttributes().removeModifiers(switchFrom.getAttributeModifiers(EquipmentSlot.MAINHAND));
    //    player.getAttributes().addTemporaryModifiers(switchTo.getAttributeModifiers(EquipmentSlot.MAINHAND));
    //}

    public static void mcdw$offhandAttack(PlayerEntity playerEntity, Entity target) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (!target.isAttackable())
                if (target.handleAttack(playerEntity))
                    return;

            ItemStack offhandStack = playerEntity.getOffHandStack();

            // use offhand modifiers
            //mcdw$switchModifiers(playerEntity, playerEntity.getMainHandStack(), offhandStack);

            float cooldownProgress = ((IDualWielding) playerEntity).mcdw$getOffhandAttackCooldownProgress(0.5F);
            float attackDamage = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            attackDamage *= (float) (0.2f + Math.pow(cooldownProgress, 2) * 0.8f);

            // use mainhand modifiers
            //mcdw$switchModifiers(playerEntity, offhandStack, playerEntity.getMainHandStack());

            //float enchantBonusDamage = EnchantmentHelper.getAttackDamage(offhandStack, target instanceof LivingEntity livingTarget ?
            //        livingTarget.getGroup() : EntityGroup.DEFAULT) * cooldownProgress;

            ((IDualWielding) playerEntity).mcdw$resetLastAttackedOffhandTicks();

            if (attackDamage > 0.0f /*|| enchantBonusDamage > 0.0f*/) {
                /* bl */
                boolean isMostlyCharged = cooldownProgress > 0.9f;

                /* i */
                int knockbackLevel = EnchantmentHelper.getLevel(Enchantments.KNOCKBACK, offhandStack);
                if (playerEntity.isSprinting() && isMostlyCharged) {
                    CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 1.0f);
                    ++knockbackLevel;
                }

                boolean playerShouldCrit = isMostlyCharged && AbilityHelper.entityCanCrit(playerEntity)
                        && target instanceof LivingEntity;
                if (playerShouldCrit && !playerEntity.isSprinting()) {
                    attackDamage *= 1.5f;
                }

                //attackDamage += enchantBonusDamage;
                boolean playerShouldSweep = isMostlyCharged && !playerShouldCrit && !playerEntity.isSprinting() && playerEntity.isOnGround()
                        && playerEntity.horizontalSpeed - playerEntity.prevHorizontalSpeed < (double) playerEntity.getMovementSpeed()
                        && offhandStack.getItem() instanceof IOffhandAttack;

                /* j */
                float targetHealth = 0.0f;
                boolean bl5 = false;
                /* k */
                int fireAspectLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, offhandStack);
                if (target instanceof LivingEntity livingTarget) {
                    targetHealth = livingTarget.getHealth();
                    if (fireAspectLevel > 0 && !livingTarget.isOnFire()) {
                        bl5 = true;
                        livingTarget.setOnFireFor(1);
                    }
                }

                Vec3d targetVelocity = target.getVelocity();
                if (target.damage(target.getWorld().getDamageSources().playerAttack(playerEntity), attackDamage)) {
                    double positionOne = -MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180));
                    double positionTwo = MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180));
                    if (knockbackLevel > 0) {
                        if (target instanceof LivingEntity livingTarget) {
                            livingTarget.takeKnockback((float) knockbackLevel * 0.5f, -positionOne, -positionTwo);
                        } else {
                            target.addVelocity(positionOne * (float) knockbackLevel * 0.5f, 0.1,
                                    positionTwo * (float) knockbackLevel * 0.5f);
                        }
                        playerEntity.setVelocity(playerEntity.getVelocity().multiply(0.6, 1.0, 0.6));
                        playerEntity.setSprinting(false);
                    }

                    if (playerShouldSweep) {
                        float sweepingEdgeMultiplierTimesDamage = 1.0f + EnchantmentHelper.getSweepingMultiplier(EnchantmentHelper.getLevel(Enchantments.SWEEPING_EDGE, offhandStack)) * attackDamage;
                        playerEntity.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0)).forEach(sweptEntity -> {
                            if (AOEHelper.satisfySweepConditions(playerEntity, target, sweptEntity, 3.0f)) {
                                sweptEntity.takeKnockback(0.4f, -positionOne, -positionTwo);
                                sweptEntity.damage(
                                        sweptEntity.getWorld().getDamageSources().playerAttack(playerEntity),
                                        sweepingEdgeMultiplierTimesDamage);
                            }
                        });
                        CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
                        if (playerEntity.getWorld() instanceof ServerWorld serverWorld) {
                            //serverWorld.spawnParticles(ParticlesRegistry.OFFHAND_SWEEP_PARTICLE, playerEntity.getX() + positionOne,
                            //        playerEntity.getBodyY(0.5D), playerEntity.getZ() + positionTwo, 0, positionOne, 0.0D, positionTwo, 0.0D);
                        }
                    }

                    if (target instanceof ServerPlayerEntity && target.velocityModified) {
                        ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                        target.velocityModified = false;
                        target.setVelocity(targetVelocity);
                    }
                    if (playerShouldCrit) {
                        CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
                        playerEntity.addCritParticles(target);
                    } else if (!playerShouldSweep) {
                        CleanlinessHelper.playCenteredSound(playerEntity,
                                isMostlyCharged ? SoundEvents.ENTITY_PLAYER_ATTACK_STRONG : SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
                                1.0f, 1.0f);
                    }

                    //if (enchantBonusDamage > 0.0f) {
                    //    playerEntity.addEnchantedHitParticles(target);
                    //}

                    playerEntity.onAttacking(target);
                    if (target instanceof LivingEntity livingTarget) {
                        EnchantmentHelper.onUserDamaged(livingTarget, playerEntity);
                    }

                    EnchantmentHelper.onTargetDamaged(playerEntity, target);
                    Entity targetedEntity = target;
                    if (target instanceof EnderDragonPart enderDragonPartTarget) {
                        targetedEntity = enderDragonPartTarget.owner;
                    }

                    if (!playerEntity.getWorld().isClient && !offhandStack.isEmpty() && targetedEntity instanceof LivingEntity livingTarget) {
                        offhandStack.postHit(livingTarget, playerEntity);
                        if (offhandStack.isEmpty()) {
                            playerEntity.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                        }
                    }

                    if (target instanceof LivingEntity livingTarget) {
                        /* m */
                        float targetCurrentHealth = targetHealth - livingTarget.getHealth();
                        playerEntity.increaseStat(Stats.DAMAGE_DEALT, Math.round(targetCurrentHealth * 10.0f));
                        if (fireAspectLevel > 0) {
                            target.setOnFireFor(fireAspectLevel * 4);
                        }

                        if (playerEntity.getWorld() instanceof ServerWorld playerServerWorld && targetCurrentHealth > 2.0f) {
                            int particleCount = (int) ((double) targetCurrentHealth * 0.5);
                            playerServerWorld.spawnParticles(ParticleTypes.DAMAGE_INDICATOR,
                                    target.getX(), target.getBodyY(0.5), target.getZ(),
                                    particleCount, 0.1, 0.0, 0.1, 0.2);
                        }
                    }
                    playerEntity.addExhaustion(0.1f);
                } else {
                    CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 1.0f);
                    if (bl5) {
                        target.extinguish();
                    }
                }
            }
        }
    }

    public static boolean mixAndMatchWeapons(PlayerEntity playerEntity) {
            return (playerEntity.getOffHandStack().isOf(playerEntity.getMainHandStack().getItem())
                    || (playerEntity.getMainHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_BEGINNING) && playerEntity.getOffHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_END))
                    || (playerEntity.getMainHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_END) && playerEntity.getOffHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_BEGINNING))
                    || (playerEntity.getMainHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD) && playerEntity.getOffHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_SILVER))
                    || (playerEntity.getMainHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_SILVER) && playerEntity.getOffHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD)));
    }

}
