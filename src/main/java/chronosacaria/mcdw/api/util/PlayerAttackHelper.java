package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.SicklesID;
import chronosacaria.mcdw.items.ItemsInit;
import chronosacaria.mcdw.networking.OffhandAttackPacket;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.SweepingEnchantment;
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
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
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
                ItemStack offhand = player.getOffHandStack();
                if (offhand.getItem() instanceof IOffhandAttack) {
                    if (hitResult instanceof EntityHitResult) {
                        if (mc.crosshairTarget != null && mc.interactionManager != null && mc.getNetworkHandler() != null) {
                            mc.getNetworkHandler().sendPacket(OffhandAttackPacket.offhandAttackPacket(((EntityHitResult) mc.crosshairTarget).getEntity()));
                        }
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
            if (playerEntity.getOffHandStack().getItem() == playerEntity.getMainHandStack().getItem()
                    || (playerEntity.getMainHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING) && playerEntity.getOffHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END))
                    || (playerEntity.getMainHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_END) && playerEntity.getOffHandStack().getItem() == ItemsInit.daggerItems.get(DaggersID.DAGGER_THE_BEGINNING))
                    || (playerEntity.getMainHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD) && playerEntity.getOffHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER))
                    || (playerEntity.getMainHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_SILVER) && playerEntity.getOffHandStack().getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_LAST_LAUGH_GOLD))) {

                /* f */
                float attackDamage = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                /* g */
                float enchantedAttackDamage = target instanceof LivingEntity ? EnchantmentHelper.getAttackDamage(playerEntity.getOffHandStack(), ((LivingEntity) target).getGroup())
                        : EnchantmentHelper.getAttackDamage(playerEntity.getOffHandStack(), EntityGroup.DEFAULT);
                /* h */
                //float offhandCooledAttackStrength = ((IDualWielding) playerEntity).getOffhandAttackCooldownProgress(0.5F);
                float offhandCooledAttackStrength = playerEntity.getAttackCooldownProgress(0.5f);
                enchantedAttackDamage *= offhandCooledAttackStrength;

                target.damage(DamageSource.player(playerEntity), attackDamage);
                playerEntity.getOffHandStack().damage(1, playerEntity, (entity) -> entity.sendToolBreakStatus(Hand.OFF_HAND));

                //((IDualWielding) playerEntity).resetLastOffhandAttackTicks();

                if ((attackDamage *= 0.2f + offhandCooledAttackStrength * offhandCooledAttackStrength * 0.5f) > 0.0f || enchantedAttackDamage > 0.0f) {
                    ItemStack offhandStackInHand = playerEntity.getStackInHand(Hand.OFF_HAND);
                    /* bl */
                    boolean offhandCooldownProgressBoolean = offhandCooledAttackStrength > 0.9f;
                    /* bl2 */
                    boolean applyEnchantmentBoolean = false;
                    /* i */
                    int knockbackLevel = 0;
                    knockbackLevel += EnchantmentHelper.getLevel(Enchantments.KNOCKBACK, offhandStackInHand);
                    if (playerEntity.isSprinting() && offhandCooldownProgressBoolean) {
                        playerEntity.world.playSound(
                                null,
                                playerEntity.getX(),
                                playerEntity.getY(),
                                playerEntity.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK,
                                playerEntity.getSoundCategory(),
                                1.0F,
                                1.0F);
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
                            && offhandStackInHand.getItem() instanceof IOffhandAttack) {
                        bl4 = true;
                    }

                    /* j */
                    float targetHealth = 0.0f;
                    boolean bl5 = false;
                    /* k */
                    int fireAspectLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, offhandStackInHand);
                    if (target instanceof LivingEntity) {
                        targetHealth = ((LivingEntity) target).getHealth();
                        if (fireAspectLevel > 0 && !target.isOnFire()) {
                            bl5 = true;
                            target.setOnFireFor(1);
                        }
                    }

                    Vec3d targetVelocity = target.getVelocity();
                    boolean bl6 = target.damage(DamageSource.player(playerEntity), attackDamage);
                    if (bl6) {
                        if (knockbackLevel > 0) {
                            if (target instanceof LivingEntity) {
                                ((LivingEntity) target).takeKnockback((float) knockbackLevel * 0.5f, MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180)),
                                        -MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180)));
                            } else {
                                target.addVelocity(-MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180)) * (float) knockbackLevel * 0.5f, 0.1,
                                        MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180)) * (float) knockbackLevel * 0.5f);
                            }
                            playerEntity.setVelocity(playerEntity.getVelocity().multiply(0.6, 1.0, 0.6));
                            playerEntity.setSprinting(false);
                        }

                        if (bl4) {
                            float sweepingEdgeMultiplierTimesDamage = 1.0f + SweepingEnchantment.getMultiplier(EnchantmentHelper.getLevel(Enchantments.SWEEPING, offhandStackInHand)) * attackDamage;
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
                            playerEntity.world.playSound(
                                    null,
                                    playerEntity.getX(),
                                    playerEntity.getY(),
                                    playerEntity.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                                    playerEntity.getSoundCategory(),
                                    1.0f,
                                    1.0f);
                            /* Offhand Sweeping Particles */
                            // Insert Code Here
                        }

                        if (target instanceof ServerPlayerEntity && target.velocityModified) {
                            ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                            target.velocityModified = false;
                            target.setVelocity(targetVelocity);
                        }
                        if (playerIsDealingCriticalDamage) {
                            playerEntity.world.playSound(
                                    null,
                                    playerEntity.getX(),
                                    playerEntity.getY(),
                                    playerEntity.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
                                    playerEntity.getSoundCategory(),
                                    1.0f,
                                    1.0f);
                            playerEntity.addCritParticles(target);
                        }

                        if (!playerIsDealingCriticalDamage && !bl4) {
                            if (offhandCooldownProgressBoolean) {
                                playerEntity.world.playSound(
                                        null,
                                        playerEntity.getX(),
                                        playerEntity.getY(),
                                        playerEntity.getZ(),
                                        SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
                                        playerEntity.getSoundCategory(),
                                        1.0f,
                                        1.0f);
                            } else {
                                playerEntity.world.playSound(
                                        null,
                                        playerEntity.getX(),
                                        playerEntity.getY(),
                                        playerEntity.getZ(),
                                        SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
                                        playerEntity.getSoundCategory(),
                                        1.0f,
                                        1.0f);
                            }
                        }

                        if (enchantedAttackDamage > 0.0f) {
                            playerEntity.addEnchantedHitParticles(target);
                        }

                        playerEntity.onAttacking(target);
                        if (target instanceof LivingEntity) {
                            EnchantmentHelper.onUserDamaged((LivingEntity) target, playerEntity);
                        }

                        EnchantmentHelper.onTargetDamaged(playerEntity, target);
                        ItemStack offhandStack = playerEntity.getOffHandStack();
                        Entity targetedEntity = target;
                        if (target instanceof EnderDragonPart) {
                            targetedEntity = ((EnderDragonPart) target).owner;
                        }

                        if (!playerEntity.world.isClient && !offhandStack.isEmpty() && targetedEntity instanceof LivingEntity) {
                            offhandStack.postHit((LivingEntity) targetedEntity, playerEntity);
                            if (offhandStack.isEmpty()) {
                                playerEntity.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                            }
                        }

                        if (target instanceof LivingEntity) {
                            /* m */
                            float targetCurrentHealth = targetHealth - ((LivingEntity) target).getHealth();
                            playerEntity.increaseStat(Stats.DAMAGE_DEALT, Math.round(targetCurrentHealth * 10.0f));
                            if (fireAspectLevel > 0) {
                                target.setOnFireFor(fireAspectLevel * 4);
                            }

                            if (playerEntity.world instanceof ServerWorld && targetCurrentHealth > 2.0f) {
                                int particleCount = (int) ((double) targetCurrentHealth * 0.5);
                                ((ServerWorld) playerEntity.world).spawnParticles(
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
                        playerEntity.world.playSound(
                                null,
                                playerEntity.getX(),
                                playerEntity.getY(),
                                playerEntity.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE,
                                playerEntity.getSoundCategory(),
                                1.0f,
                                1.0f);
                        if (bl5) {
                            target.extinguish();
                        }
                    }
                }
            }
        }
    }
}
