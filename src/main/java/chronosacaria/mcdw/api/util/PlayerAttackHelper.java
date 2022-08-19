package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.damagesource.OffHandDamageSource;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import chronosacaria.mcdw.particles.ParticlesInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PlayerAttackHelper {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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

    /* Can you see me on severs?
    public void swingOffHand(LivingEntity livingEntity, Hand hand) {
        this.swingOffHand(livingEntity, hand, false);
    }
    public void swingOffHand(LivingEntity livingEntity, Hand hand, boolean fromServerPlayer) {
        if (!livingEntity.handSwinging || livingEntity.handSwingTicks >= this.getOffHandSwingDuration(livingEntity) / 2 || livingEntity.handSwingTicks < 0) {
            livingEntity.handSwingTicks = -1;
            livingEntity.handSwinging = true;
            livingEntity.preferredHand = hand;
            if (livingEntity.world instanceof ServerWorld) {
                EntityAnimationS2CPacket entityAnimationS2CPacket = new EntityAnimationS2CPacket(livingEntity, hand == Hand.OFF_HAND ? 0: 3);
                ServerChunkManager serverChunkManager = ((ServerWorld) livingEntity.world).getChunkManager();
                if (fromServerPlayer) {
                    serverChunkManager.sendToNearbyPlayers(livingEntity, entityAnimationS2CPacket);
                } else {
                    serverChunkManager.sendToOtherNearbyPlayers(livingEntity, entityAnimationS2CPacket);
                }
            }
        }
    }
    private int getOffHandSwingDuration(LivingEntity livingEntity) {
        if (StatusEffectUtil.hasHaste(livingEntity)) {
            return 6 - (1 + StatusEffectUtil.getHasteAmplifier(livingEntity));
        } else {
            return livingEntity.hasStatusEffect(StatusEffects.MINING_FATIGUE) ? 6 + (1 + livingEntity.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
        }
    }
    */

    public static void checkForOffhandAttack() {
        if (CompatibilityFlags.noOffhandConflicts) {
            MinecraftClient mc = MinecraftClient.getInstance();
            PlayerEntity player = mc.player;
            if (mc.world != null
                    && mc.currentScreen == null
                    && !mc.isPaused()
                    && player != null
                    && !player.isBlocking()) {

                if (mc.interactionManager != null && mc.getNetworkHandler() != null) {
                    mc.getNetworkHandler().sendPacket(mc.crosshairTarget instanceof EntityHitResult entityHitResult
                            ? OffhandAttackPacket.offhandAttackPacket(entityHitResult.getEntity())
                            : OffhandAttackPacket.offhandMissPacket());
                }
            }
        }
    }

    public static void switchModifiers(PlayerEntity player, ItemStack switchFrom, ItemStack switchTo) {
        player.getAttributes().removeModifiers(switchFrom.getAttributeModifiers(EquipmentSlot.MAINHAND));
        player.getAttributes().addTemporaryModifiers(switchTo.getAttributeModifiers(EquipmentSlot.MAINHAND));
    }

    public static void offhandAttack(PlayerEntity playerEntity, Entity target) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (!target.isAttackable())
                if (target.handleAttack(playerEntity))
                    return;

            ItemStack offhandStack = playerEntity.getOffHandStack();

            // use offhand modifiers
            switchModifiers(playerEntity, playerEntity.getMainHandStack(), offhandStack);

            float cooldownProgress = ((IDualWielding) playerEntity).getOffhandAttackCooldownProgress(0.5F);
            float attackDamage = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            attackDamage *= 0.2f + Math.pow(cooldownProgress, 2) * 0.8f;

            // use mainhand modifiers
            switchModifiers(playerEntity, offhandStack, playerEntity.getMainHandStack());

            float enchantBonusDamage = EnchantmentHelper.getAttackDamage(offhandStack, target instanceof LivingEntity livingTarget ?
                    livingTarget.getGroup() : EntityGroup.DEFAULT) * cooldownProgress;

            ((IDualWielding) playerEntity).resetLastAttackedOffhandTicks();

            if (attackDamage > 0.0f || enchantBonusDamage > 0.0f) {
                /* bl */
                boolean isMostlyCharged = cooldownProgress > 0.9f;

                /* i */
                int knockbackLevel = EnchantmentHelper.getLevel(Enchantments.KNOCKBACK, offhandStack);
                if (playerEntity.isSprinting() && isMostlyCharged) {
                    CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 1.0f);
                    ++knockbackLevel;
                }

                boolean playerShouldCrit = isMostlyCharged && playerEntity.fallDistance > 0.0f
                        && !playerEntity.isOnGround() && !playerEntity.isClimbing() && !playerEntity.isTouchingWater()
                        && !playerEntity.hasStatusEffect(StatusEffects.BLINDNESS) && !playerEntity.hasVehicle() && target instanceof LivingEntity;
                if (playerShouldCrit && !playerEntity.isSprinting()) {
                    attackDamage *= 1.5f;
                }

                attackDamage += enchantBonusDamage;
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
                if (target.damage(OffHandDamageSource.player(playerEntity), attackDamage)) {
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
                        float sweepingEdgeMultiplierTimesDamage = 1.0f + SweepingEnchantment.getMultiplier(EnchantmentHelper.getLevel(Enchantments.SWEEPING, offhandStack)) * attackDamage;
                        playerEntity.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0)).forEach(sweptEntity -> {
                            if (AOEHelper.satisfySweepConditions(playerEntity, target, sweptEntity, 3.0f)) {
                                sweptEntity.takeKnockback(0.4f, -positionOne, -positionTwo);
                                sweptEntity.damage(OffHandDamageSource.player(playerEntity), sweepingEdgeMultiplierTimesDamage);
                            }
                        });
                        CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
                        if (playerEntity.world instanceof ServerWorld serverWorld) {
                            serverWorld.spawnParticles(ParticlesInit.OFFHAND_SWEEP_PARTICLE, playerEntity.getX() + positionOne,
                                    playerEntity.getBodyY(0.5D), playerEntity.getZ() + positionTwo, 0, positionOne, 0.0D, positionTwo, 0.0D);
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

                    if (enchantBonusDamage > 0.0f) {
                        playerEntity.addEnchantedHitParticles(target);
                    }

                    playerEntity.onAttacking(target);
                    if (target instanceof LivingEntity livingTarget) {
                        EnchantmentHelper.onUserDamaged(livingTarget, playerEntity);
                    }

                    EnchantmentHelper.onTargetDamaged(playerEntity, target);
                    Entity targetedEntity = target;
                    if (target instanceof EnderDragonPart enderDragonPartTarget) {
                        targetedEntity = enderDragonPartTarget.owner;
                    }

                    if (!playerEntity.world.isClient && !offhandStack.isEmpty() && targetedEntity instanceof LivingEntity livingTarget) {
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

                        if (playerEntity.world instanceof ServerWorld playerServerWorld && targetCurrentHealth > 2.0f) {
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
}