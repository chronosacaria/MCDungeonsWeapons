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


import dev.timefall.mcdw.api.util.PlayerAttackHelper;
import dev.timefall.mcdw.configs.stats.McdwEnchantmentStatsConfig;
import dev.timefall.mcdw.damagesources.OffHandDamageSource;
import dev.timefall.mcdw.effects.EnchantmentEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class LivingEntityPlayerEntityMixin {

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    public float mcdw$damageModifiers(float amount, DamageSource source) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return amount;

        LivingEntity victim = (LivingEntity) (Object) this;

        boolean isOffHandAttack = source instanceof OffHandDamageSource;

        if (amount > 0) {

            // TODO Consider readding a configurable multiplier for stored damage
            float storedAmount = amount;

            if (source.getSource() instanceof LivingEntity) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getAmbushEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.ambushDamage(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getCriticalHitEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.criticalHitDamage(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getVoidStrikeEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.voidStrikeDamage(attackingEntity, victim, isOffHandAttack);
                if (!PlayerAttackHelper.mcdw$isLikelyNotMeleeDamage(source)) {
                    if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getPainCycleEnchantment().isEnabled)
                        amount += storedAmount * EnchantmentEffects.painCycleDamage(attackingEntity, isOffHandAttack);
                }
            }

            if (source.getSource() instanceof PlayerEntity attackingPlayer) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getEnigmaResonatorEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.enigmaResonatorDamage(attackingPlayer, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getDynamoEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.dynamoDamage(attackingEntity, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getShadowShotEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.shadowFormDamage(attackingEntity);
            }

            if (source.getSource() instanceof PersistentProjectileEntity ppe) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getEnigmaResonatorEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.enigmaShotDamage(attackingEntity, victim, ppe);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getGrowingEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.growingDamage(attackingEntity, victim, ppe);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getVoidShotEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.voidShotDamage(victim, ppe);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getDynamoEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.dynamoShotDamage(attackingEntity, ppe);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getShadowShotEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.shadowFormShotDamage(attackingEntity, ppe);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getOverchargeEnchantment().isEnabled)
                    amount += storedAmount * EnchantmentEffects.overchargeDamage(ppe);
            }

            if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getCommittedEnchantment().isEnabled)
                amount += EnchantmentEffects.committedDamage(attackingEntity, victim, isOffHandAttack);
        }

        return amount;
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdw$onApplyDamageHead(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return;

        LivingEntity victim = (LivingEntity) (Object) this;

        boolean isOffHandAttack = source instanceof OffHandDamageSource;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity sourceEntity
                    && !(sourceEntity.getMainHandStack().getItem() instanceof BowItem
                         || sourceEntity.getMainHandStack().getItem() instanceof CrossbowItem)
            ) {

                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getFreezingEnchantment().isEnabled)
                    EnchantmentEffects.applyFreezing(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getJunglePoisonEnchantment().isEnabled)
                    EnchantmentEffects.applyPoisoning(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getPoisonCloudEnchantment().isEnabled)
                    EnchantmentEffects.applyPoisonCloud(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getRadianceEnchantment().isEnabled)
                    EnchantmentEffects.applyRadianceCloud(attackingEntity, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getShockwaveEnchantment().isEnabled)
                    EnchantmentEffects.applyShockwave(attackingEntity, victim, amount, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getStunningEnchantment().isEnabled)
                    EnchantmentEffects.applyStunning(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getSwirlingEnchantment().isEnabled)
                    EnchantmentEffects.applySwirling(attackingEntity, victim, amount, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getThunderingEnchantment().isEnabled)
                    EnchantmentEffects.applyThundering(attackingEntity, amount, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getWeakeningEnchantment().isEnabled)
                    EnchantmentEffects.applyWeakeningCloud(attackingEntity, victim, isOffHandAttack);

                if (!source.isOf(DamageTypes.ARROW)) {
                    if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getChainsEnchantment().isEnabled)
                        EnchantmentEffects.applyChains(attackingEntity, victim, isOffHandAttack);
                    if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getGravityEnchantment().isEnabled)
                        EnchantmentEffects.applyGravity(attackingEntity, victim, isOffHandAttack);
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("TAIL"))
    public void mcdw$onApplyDamageTail(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return;

        LivingEntity victim = (LivingEntity) (Object) this;

        boolean isOffHandAttack = source instanceof OffHandDamageSource;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity sourceEntity
                    && !(sourceEntity.getMainHandStack().getItem() instanceof BowItem
                    || sourceEntity.getMainHandStack().getItem() instanceof CrossbowItem)
            ) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getEchoEnchantment().isEnabled)
                    EnchantmentEffects.echoDamage(attackingEntity, victim, amount, isOffHandAttack);
            }
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void mcdw$onDeath(DamageSource source, CallbackInfo ci) {
        boolean isOffHandAttack = source instanceof OffHandDamageSource;
        if (source.getAttacker() instanceof LivingEntity attackingEntity) {

            LivingEntity victim = (LivingEntity) (Object) this;

            if (source.getSource() instanceof LivingEntity sourceEntity
                    && !(sourceEntity.getMainHandStack().getItem() instanceof BowItem
                    || sourceEntity.getMainHandStack().getItem() instanceof CrossbowItem)
            ) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getExplodingEnchantment().isEnabled)
                    EnchantmentEffects.explodingDamage(attackingEntity, victim, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getRampagingEnchantment().isEnabled)
                    EnchantmentEffects.applyRampaging(attackingEntity, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getLeechingEnchantment().isEnabled)
                    EnchantmentEffects.applyLeeching(attackingEntity, victim, isOffHandAttack);

                if (!PlayerAttackHelper.mcdw$isLikelyNotMeleeDamage(source)) {
                    if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getGuardingStrikeEnchantment().isEnabled)
                        EnchantmentEffects.applyGuardingStrike(attackingEntity, isOffHandAttack);
                }
            }
        }

        if (source.getAttacker() instanceof PlayerEntity attackingEntity) {

            if (source.getSource() instanceof LivingEntity) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getRefreshmentEnchantment().isEnabled)
                    EnchantmentEffects.applyRefreshment(attackingEntity, isOffHandAttack);
            }

            if (source.getSource() instanceof PersistentProjectileEntity ppe) {
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getRefreshmentEnchantment().isEnabled)
                    EnchantmentEffects.applyRefreshment(attackingEntity, isOffHandAttack);
                if (McdwEnchantmentStatsConfig.CONFIG.getMcdwEnchantmentStats().getShadowShotEnchantment().isEnabled)
                    EnchantmentEffects.applyShadowShotShadowForm(attackingEntity, ppe, 80);
            }
        }
    }
}

