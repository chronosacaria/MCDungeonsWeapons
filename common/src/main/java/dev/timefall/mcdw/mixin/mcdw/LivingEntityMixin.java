/*
 * Timefall Development License 1.2
 * Copyright (c) 2025. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin.mcdw;


import dev.timefall.mcdw.api.util.AOEHelper;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.api.util.move_to_mcdx.EnchantHelper;
import dev.timefall.mcdw.configs.stats.McdwEnchantmentStatsConfig;
import dev.timefall.mcdw.damagesources.OffHandDamageSource;
import dev.timefall.mcdw.effects.EnchantmentEffects;
import dev.timefall.mcdw.enchantment.summons.IBeeSummoning;
import dev.timefall.mcdw.enchantment.summons.entity.SummonedBeeEntity;
import dev.timefall.mcdw.enums.ItemsID;
import dev.timefall.mcdw.registries.EnchantmentRegistry;
import dev.timefall.mcdw.registries.ItemsRegistry;
import dev.timefall.mcdw.registries.SummonedEntityRegistry;
import dev.timefall.mcdw.registries.items.McdwSwordItemRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("ConstantValue")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Unique
    public final EntityType<SummonedBeeEntity> mcdw$summoned_bee =
            SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    public float mcdw$damageModifiers(float amount, DamageSource source) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return amount;

        if (amount > 0) {
            // TODO Consider readding a configurable multiplier for stored damage
            float storedAmount = amount;
            if (attackingEntity instanceof TameableEntity petSource
                    && petSource.getWorld() instanceof ServerWorld serverWorld
                    && petSource.getOwner() instanceof PlayerEntity owner) {

                amount += storedAmount * EnchantmentEffects.huntersPromiseDamage(owner, serverWorld);
            }
        }

        return amount;
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void mcdw$onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity victim = (LivingEntity) (Object) this;
        boolean isOffHandAttack = source instanceof OffHandDamageSource;

        if (source.getAttacker() instanceof LivingEntity attackingEntity) {

            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getProspectorEnchantment().isEnabled)
                EnchantmentEffects.applyProspector(attackingEntity, victim, isOffHandAttack);
            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getRushdownEnchantment().isEnabled)
                EnchantmentEffects.applyRushdown(attackingEntity, isOffHandAttack);
        }

        if (source.getAttacker() instanceof PlayerEntity attackingPlayer) {

            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getSoulSiphonEnchantment().isEnabled)
                EnchantmentEffects.applySoulSiphon(attackingPlayer, isOffHandAttack);
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdw$applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof LivingEntity user))
            return;


        if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getSmitingEnchantment().isEnabled) {
            LivingEntity target = (LivingEntity) (Object) this;

            if(target instanceof PlayerEntity) return;

            if (source.getSource() instanceof LivingEntity) {
                if (amount > 0) {
                    ItemStack mainHandStack = user.getMainHandStack();
                    ItemStack offHandStack = user.getOffHandStack();

                    mcdw$applySmite(amount, user, target, mainHandStack);
                    mcdw$applySmite(amount, user, target, offHandStack);
                }
            }
        }
    }

    @Unique
    private void mcdw$applySmite(float amount, LivingEntity user, LivingEntity target, ItemStack itemStack) {
        if (itemStack != null
                && (EnchantHelper.mcdx$getEnchantmentLevel(EnchantmentRegistry.SMITING, user, false) > 0
                && !(EnchantHelper.mcdx$getLevel(Enchantments.SMITE, user.getEntityWorld(), itemStack) > 0))) {
            int smitingLevel = EnchantHelper.mcdx$getLevel(EnchantmentRegistry.SMITING, user.getEntityWorld(), itemStack);
            if (target.getType().isIn(EntityTypeTags.SENSITIVE_TO_SMITE)) {
                EnchantmentEffects.causeSmitingAttack(user, target,
                        3.0f * smitingLevel, amount);
            }
        }
    }

    @Inject(method = "applyDamage", at = @At("HEAD"))
    private void mcdw$onAttack(DamageSource source, float amount, CallbackInfo ci) {
        var attacker = source.getAttacker();
        var target = (LivingEntity) ((Object)this);
        if (target.isInvulnerableTo(source)) {
            return;
        }

        if(!(attacker instanceof PlayerEntity attackingPlayer))
            return;

        if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getBusyBeeEnchantment().isEnabled
                && ((IBeeSummoning)attackingPlayer).isReadyForBeeSummon(attackingPlayer.age)) {
            ItemStack mainHandStack = attackingPlayer.getMainHandStack();
            ItemStack offHandStack = attackingPlayer.getOffHandStack();
            if (mainHandStack.getItem() == McdwSwordItemRegistry.SWORD_BEE_STINGER.asItem()
                    && offHandStack.getItem() == ItemsRegistry.MCDW_ITEMS.get(ItemsID.ITEM_BEE_STINGER)) {
                offHandStack.decrement(1);
                SummonedBeeEntity summonedBeeEntity_1 = mcdw$summoned_bee.create(attackingPlayer.getWorld());
                if (summonedBeeEntity_1 != null) {
                    summonedBeeEntity_1.setSummoner(attackingPlayer);
                    summonedBeeEntity_1.refreshPositionAndAngles(attackingPlayer.getX(), attackingPlayer.getY() + 1, attackingPlayer.getZ(), 0, 0);
                    attackingPlayer.getWorld().spawnEntity(summonedBeeEntity_1);
                }
            }
        }
    }

    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void mcdw$applyDippingPoisonPotionConsumption(CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity user))
            return;

        ItemStack poisonTippedArrow = PotionContentsComponent.createStack(Items.TIPPED_ARROW, Potions.POISON /*8*/);

        if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getDippingPoisonEnchantment().isEnabled) {
            if (!(user.getMainHandStack().getItem() instanceof PotionItem))
                return;

            if (user.getOffHandStack() != null
                    && (EnchantHelper.mcdx$getLevel(EnchantmentRegistry.DIPPING_POISON, user.getEntityWorld(), user.getOffHandStack()) > 0)
            ) {
                int dippingPoisonLevel = EnchantHelper.mcdx$getLevel(EnchantmentRegistry.DIPPING_POISON, user.getEntityWorld(), user.getOffHandStack());
                if (dippingPoisonLevel > 0) {
                    Iterator<StatusEffectInstance> potionEffects = user.getMainHandStack().get(DataComponentTypes.POTION_CONTENTS).getEffects().iterator();
                    if (!(potionEffects == StatusEffects.INSTANT_HEALTH)) {
                        return;
                    }
                    if (potionEffects == StatusEffects.INSTANT_HEALTH) {
                        CleanlinessHelper.mcdw$dropItem(user, poisonTippedArrow.getItem(), 8);
                    }
                }

            }
        }
    }

    @Inject(method = "jump", at = @At("HEAD"))
    public void mcdw$onJumpEffects(CallbackInfo ci){
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;

        if (playerEntity != null) {
            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getBurstBowstringEnchantment().isEnabled)
                EnchantmentEffects.activateBurstBowstringOnJump(playerEntity);
            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getDynamoEnchantment().isEnabled)
                EnchantmentEffects.handleAddDynamoEffect(playerEntity);
        }
    }

    @Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V"))
    public void mcdw$applySharedPainDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (source.getSource() instanceof PlayerEntity player) {
            int sharedPainLevel = EnchantHelper.mcdx$getEnchantmentLevel(EnchantmentRegistry.SHARED_PAIN, player, false);
            if (sharedPainLevel <= 0) return;
            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getSharedPainEnchantment().isEnabled) {
                if ((Object) this instanceof LivingEntity target) {
                    float targetHealth = target.getHealth() - amount;
                    if (targetHealth < 0) {
                        float overkillDamage = Math.abs(targetHealth);
                        List<LivingEntity> nearbyEntities = AOEHelper.getEntitiesByConfig(target, 6);
                        if (nearbyEntities.isEmpty()) {
                            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getSharedPainEnchantment().canAffectUser) {
                                player.damage(player.getWorld().getDamageSources().magic(), overkillDamage);
                            }
                        } else {
                            nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(target)));
                            nearbyEntities.get(0).damage(nearbyEntities.get(0).getWorld().getDamageSources().magic(), overkillDamage);
                        }
                    }
                }
            }
        }
    }
}
