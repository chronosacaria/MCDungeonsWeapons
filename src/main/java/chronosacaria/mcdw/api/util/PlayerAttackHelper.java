package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.damagesource.OffHandDamageSource;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import net.fabricmc.loader.api.FabricLoader;
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
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.List;

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

    public static void checkForOffhandAttack() {
        if (!FabricLoader.getInstance().isModLoaded("dualwielding") || !FabricLoader.getInstance().isModLoaded("bettercombat")) {
            MinecraftClient mc = MinecraftClient.getInstance();
            PlayerEntity player = mc.player;
            HitResult hitResult = mc.crosshairTarget;
            if (MinecraftClient.getInstance().world != null
                    && MinecraftClient.getInstance().currentScreen == null
                    && !MinecraftClient.getInstance().isPaused()
                    && player != null
                    && !player.isBlocking()) {

                if (hitResult instanceof EntityHitResult) {
                    if (mc.crosshairTarget != null && mc.interactionManager != null && mc.getNetworkHandler() != null) {
                        mc.getNetworkHandler().sendPacket(OffhandAttackPacket.offhandAttackPacket(((EntityHitResult) mc.crosshairTarget).getEntity()));
                    }
                }
            }
        }
    }

    public static void offhandAttack(PlayerEntity playerEntity, Entity target) {
        if (!FabricLoader.getInstance().isModLoaded("dualwielding") || !FabricLoader.getInstance().isModLoaded("bettercombat")) {
            if (!target.isAttackable()) {
                return;
            }
            if (target.handleAttack(playerEntity)) {
                return;
            }

            ItemStack offhandStack = playerEntity.getOffHandStack();

                /* f */
                // Begin Oof
                playerEntity.getAttributes().removeModifiers(playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND));
                playerEntity.getAttributes().addTemporaryModifiers(offhandStack.getAttributeModifiers(EquipmentSlot.MAINHAND));

                float attackDamage = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);

                playerEntity.getAttributes().removeModifiers(offhandStack.getAttributeModifiers(EquipmentSlot.MAINHAND));
                playerEntity.getAttributes().addTemporaryModifiers(playerEntity.getMainHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND));
                // End Oof

                /* g */
                float enchantedAttackDamage = target instanceof LivingEntity livingTarget ? EnchantmentHelper.getAttackDamage(offhandStack, livingTarget.getGroup())
                        : EnchantmentHelper.getAttackDamage(offhandStack, EntityGroup.DEFAULT);
                /* h */
                float offhandCooledAttackStrength = ((IDualWielding) playerEntity).getOffhandAttackCooldownProgress(0.5F);
                //float offhandCooledAttackStrength = playerEntity.getAttackCooldownProgress(0.5f);
                enchantedAttackDamage *= offhandCooledAttackStrength;

                target.damage(OffHandDamageSource.player(playerEntity), attackDamage);
                offhandStack.damage(1, playerEntity, (entity) -> entity.sendToolBreakStatus(Hand.OFF_HAND));

                ((IDualWielding) playerEntity).resetLastAttackedOffhandTicks();

                if ((attackDamage *= 0.2f + offhandCooledAttackStrength * offhandCooledAttackStrength * 0.5f) > 0.0f || enchantedAttackDamage > 0.0f) {
                    /* bl */
                    boolean offhandCooldownProgressBoolean = offhandCooledAttackStrength > 0.9f;
                    /* bl2 */
                    boolean applyEnchantmentBoolean = false;
                    /* i */
                    int knockbackLevel = 0;
                    knockbackLevel += EnchantmentHelper.getLevel(Enchantments.KNOCKBACK, offhandStack);
                    if (playerEntity.isSprinting() && offhandCooldownProgressBoolean) {
                        CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 1.0f);
                        ++knockbackLevel;
                        applyEnchantmentBoolean = true;
                    }

                    boolean playerIsDealingCriticalDamage = offhandCooldownProgressBoolean && playerEntity.fallDistance > 0.0f
                            && !playerEntity.isOnGround() && !playerEntity.isClimbing() && !playerEntity.isTouchingWater()
                            && !playerEntity.hasStatusEffect(StatusEffects.BLINDNESS) && !playerEntity.hasVehicle() && target instanceof LivingEntity;
                    if (playerIsDealingCriticalDamage && !playerEntity.isSprinting()) {
                        attackDamage *= 1.5f;
                    }

                    attackDamage *= enchantedAttackDamage;
                    boolean bl4 = false;
                    double playerCurrentHorizontalSpeed = playerEntity.horizontalSpeed - playerEntity.prevHorizontalSpeed;
                    if (offhandCooldownProgressBoolean && !playerIsDealingCriticalDamage && !applyEnchantmentBoolean
                            && playerEntity.isOnGround() && playerCurrentHorizontalSpeed < (double) playerEntity.getMovementSpeed()
                            && offhandStack.getItem() instanceof IOffhandAttack) {
                        bl4 = true;
                    }

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
                        if (knockbackLevel > 0) {
                            if (target instanceof LivingEntity livingTarget) {
                                livingTarget.takeKnockback((float) knockbackLevel * 0.5f, MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180)),
                                        -MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180)));
                            } else {
                                target.addVelocity(-MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180)) * (float) knockbackLevel * 0.5f, 0.1,
                                        MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180)) * (float) knockbackLevel * 0.5f);
                            }
                            playerEntity.setVelocity(playerEntity.getVelocity().multiply(0.6, 1.0, 0.6));
                            playerEntity.setSprinting(false);
                        }

                        if (bl4) {
                            float sweepingEdgeMultiplierTimesDamage = 1.0f + SweepingEnchantment.getMultiplier(EnchantmentHelper.getLevel(Enchantments.SWEEPING, offhandStack)) * attackDamage;
                            List<LivingEntity> sweptEntities = playerEntity.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0));
                            for (LivingEntity livingEntity : sweptEntities) {
                                if (livingEntity == playerEntity || livingEntity == target || playerEntity.isTeammate(livingEntity)
                                        || livingEntity instanceof ArmorStandEntity && ((ArmorStandEntity) livingEntity).isMarker()
                                        || !(playerEntity.squaredDistanceTo(livingEntity) < 9.0))
                                    continue;
                                livingEntity.takeKnockback(0.4f, MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180)),
                                        -MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180)));
                                livingEntity.damage(DamageSource.player(playerEntity), sweepingEdgeMultiplierTimesDamage);
                            }
                            CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
                            /* Offhand Sweeping Particles */
                            // Insert Code Here
                        }

                        if (target instanceof ServerPlayerEntity && target.velocityModified) {
                            ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                            target.velocityModified = false;
                            target.setVelocity(targetVelocity);
                        }
                        if (playerIsDealingCriticalDamage) {
                            CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
                            playerEntity.addCritParticles(target);
                        } else if (!bl4) {
                            CleanlinessHelper.playCenteredSound(playerEntity,
                                    offhandCooldownProgressBoolean ? SoundEvents.ENTITY_PLAYER_ATTACK_STRONG : SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
                                    1.0f, 1.0f);
                        }

                        if (enchantedAttackDamage > 0.0f) {
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
                                playerServerWorld.spawnParticles(
                                        ParticleTypes.DAMAGE_INDICATOR,
                                        target.getX(),
                                        target.getBodyY(0.5),
                                        target.getZ(),
                                        particleCount,
                                        0.1,
                                        0.0,
                                        0.1,
                                        0.2);
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
