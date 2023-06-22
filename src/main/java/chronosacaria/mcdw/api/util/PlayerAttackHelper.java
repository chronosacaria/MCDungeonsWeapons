package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.api.interfaces.IDualWielding;
import chronosacaria.mcdw.api.interfaces.IOffhandAttack;
import chronosacaria.mcdw.configs.CompatibilityFlags;
import chronosacaria.mcdw.registries.EntityAttributesRegistry;
import chronosacaria.mcdw.registries.ParticlesRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlayerAttackHelper {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isLikelyNotMeleeDamage(DamageSource damageSource){
        return damageSource.isOf(DamageTypes.ON_FIRE)
                || damageSource.isOf(DamageTypes.EXPLOSION)
                || damageSource.isOf(DamageTypes.MAGIC)
                || damageSource.isOf(DamageTypes.ARROW)
                || !isDirectDamage(damageSource);
    }

    private static boolean isDirectDamage(DamageSource damageSource){
        return damageSource.isOf(DamageTypes.MOB_ATTACK)
                || damageSource.isOf(DamageTypes.PLAYER_ATTACK);
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

                boolean playerShouldCrit = isMostlyCharged && AbilityHelper.entityCanCrit(playerEntity)
                        && target instanceof LivingEntity;
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
                        float sweepingEdgeMultiplierTimesDamage = 1.0f + SweepingEnchantment.getMultiplier(EnchantmentHelper.getLevel(Enchantments.SWEEPING, offhandStack)) * attackDamage;
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
                            serverWorld.spawnParticles(ParticlesRegistry.OFFHAND_SWEEP_PARTICLE, playerEntity.getX() + positionOne,
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

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L27">ReachEntityAttributes Lines 27-30</a>
     */

    public static double getReachDistance(LivingEntity livingEntity, double defaultReachDistance) {
        @Nullable
        EntityAttributeInstance reachDistance = livingEntity.getAttributeInstance(EntityAttributesRegistry.REACH);
        return (reachDistance != null) ? (defaultReachDistance + reachDistance.getValue()) : defaultReachDistance;
    }

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L32">ReachEntityAttributes Lines 32-35</a>
     */
    @SuppressWarnings("unused")
    public static double getSquaredReachDistance(LivingEntity livingEntity, double squareDefaultReachDistance) {
        double reachDistance = getReachDistance(livingEntity, Math.sqrt(squareDefaultReachDistance));
        return reachDistance * reachDistance;
    }

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L37">ReachEntityAttributes Lines 37-40</a>
     */
    public static double getAttackRange(LivingEntity livingEntity, double defaultAttackRange) {
        @Nullable
        EntityAttributeInstance attackRange = livingEntity.getAttributeInstance(EntityAttributesRegistry.ATTACK_RANGE);
        return (attackRange != null) ? (defaultAttackRange + attackRange.getValue()) : defaultAttackRange;
    }

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L42">ReachEntityAttributes Lines 42-45</a>
     */
    public static double getSquaredAttackRange(LivingEntity livingEntity, double squareDefaultAttackRange) {
        double attackRange = getAttackRange(livingEntity, Math.sqrt(squareDefaultAttackRange));
        return attackRange * attackRange;
    }

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L47">ReachEntityAttributes Lines 47-49</a>
     */
    @SuppressWarnings("unused")
    public static List<PlayerEntity> getPlayerEntitiesWithinReach(World world, int x, int y, int z, double defaultReachDistance) {
        return getPlayerEntitiesWithinReach(player -> true, world, x, y, z, defaultReachDistance);
    }

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L51">ReachEntityAttributes Lines 51-65</a>
     */
    public static List<PlayerEntity> getPlayerEntitiesWithinReach(Predicate<PlayerEntity> viewerPredicate, World world, int x, int y, int z, double defaultReachDistance) {
        List<PlayerEntity> playerEntitiesWithinReach = new ArrayList<>();
        for (PlayerEntity playerEntity : world.getPlayers()) {
            if (viewerPredicate.test(playerEntity)) {
                double reach = getReachDistance(playerEntity, defaultReachDistance);
                double dx = (x + 0.5) - playerEntity.getX();
                double dy = (y + 0.5) - playerEntity.getEyeY();
                double dz = (z + 0.5) - playerEntity.getZ();
                if (((dx * dx) + (dy * dy) + (dz * dz)) <= (reach * reach)) {
                    playerEntitiesWithinReach.add(playerEntity);
                }
            }
        }
        return playerEntitiesWithinReach;
    }

    /**
     * Copyright 2019 Erlend Åmdal
     * <br/><br/>
     * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
     * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
     * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
     * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
     * <br/><br/>
     * The above copyright notice and this permission notice shall be included in all copies or substantial portions
     * of the Software.
     * <br/><br/>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
     * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
     * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
     * IN THE SOFTWARE.
     * <br/><br/>
     * The following code is from Reach Entity Attributes and can be found here:
     * <a href = "https://github.com/JamiesWhiteShirt/reach-entity-attributes/blob/1.19/src/main/java/com/jamieswhiteshirt/reachentityattributes/ReachEntityAttributes.java#L67">ReachEntityAttributes Lines 67-69</a>
     */
    public static boolean isEntityWithinAttackRange(PlayerEntity playerEntity, Entity entity) {
        return playerEntity.squaredDistanceTo(entity) <= getSquaredAttackRange(playerEntity, 64);
    }
}
