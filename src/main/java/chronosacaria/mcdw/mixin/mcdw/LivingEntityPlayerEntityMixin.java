package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import chronosacaria.mcdw.damagesources.OffHandDamageSource;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
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

            float storedAmount = amount * Mcdw.CONFIG.mcdwEnchantmentsConfig.directDamageEnchantmentMultiplier;

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.AMBUSH).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.ambushDamage(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.CRITICAL_HIT).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.criticalHitDamage(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.VOID_STRIKE).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.voidStrikeDamage(attackingEntity, victim, isOffHandAttack);
                if (!PlayerAttackHelper.mcdw$isLikelyNotMeleeDamage(source)) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.PAIN_CYCLE).mcdw$getIsEnabled())
                        amount += storedAmount * EnchantmentEffects.painCycleDamage(attackingEntity, isOffHandAttack);
                }
            }

            if (source.getSource() instanceof PlayerEntity attackingPlayer) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ENIGMA_RESONATOR).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.enigmaResonatorDamage(attackingPlayer, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.DYNAMO).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.dynamoDamage(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SHADOW_SHOT).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.shadowFormDamage(attackingEntity);
            }

            if (source.getSource() instanceof PersistentProjectileEntity ppe) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ENIGMA_RESONATOR).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.enigmaShotDamage(attackingEntity, victim, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.GROWING).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.growingDamage(attackingEntity, victim, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.VOID_SHOT).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.voidShotDamage(victim, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.DYNAMO).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.dynamoShotDamage(attackingEntity, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SHADOW_SHOT).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.shadowFormShotDamage(attackingEntity, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.OVERCHARGE).mcdw$getIsEnabled())
                    amount += storedAmount * EnchantmentEffects.overchargeDamage(ppe);
            }

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.COMMITTED).mcdw$getIsEnabled())
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

                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.FREEZING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyFreezing(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.POISONING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyPoisoning(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.POISON_CLOUD).mcdw$getIsEnabled())
                    EnchantmentEffects.applyPoisonCloud(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RADIANCE).mcdw$getIsEnabled())
                    EnchantmentEffects.applyRadianceCloud(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SHOCKWAVE).mcdw$getIsEnabled())
                    EnchantmentEffects.applyShockwave(attackingEntity, victim, amount, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.STUNNING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyStunning(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SWIRLING).mcdw$getIsEnabled())
                    EnchantmentEffects.applySwirling(attackingEntity, victim, amount, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.THUNDERING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyThundering(attackingEntity, amount, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.WEAKENING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyWeakeningCloud(attackingEntity, victim, isOffHandAttack);

                if (!source.isOf(DamageTypes.ARROW)) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.CHAINS).mcdw$getIsEnabled())
                        EnchantmentEffects.applyChains(attackingEntity, victim, isOffHandAttack);
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.GRAVITY).mcdw$getIsEnabled())
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
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ECHO).mcdw$getIsEnabled())
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
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.EXPLODING).mcdw$getIsEnabled())
                    EnchantmentEffects.explodingDamage(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RAMPAGING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyRampaging(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.LEECHING).mcdw$getIsEnabled())
                    EnchantmentEffects.applyLeeching(attackingEntity, victim, isOffHandAttack);

                if (!PlayerAttackHelper.mcdw$isLikelyNotMeleeDamage(source)) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.GUARDING_STRIKE).mcdw$getIsEnabled())
                        EnchantmentEffects.applyGuardingStrike(attackingEntity, isOffHandAttack);
                }
            }
        }

        if (source.getAttacker() instanceof PlayerEntity attackingEntity) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.REFRESHMENT).mcdw$getIsEnabled())
                    EnchantmentEffects.applyRefreshment(attackingEntity, isOffHandAttack);
            }

            if (source.getSource() instanceof PersistentProjectileEntity ppe) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.REFRESHMENT).mcdw$getIsEnabled())
                    EnchantmentEffects.applyRefreshment(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SHADOW_SHOT).mcdw$getIsEnabled())
                    EnchantmentEffects.applyShadowShotShadowForm(attackingEntity, ppe, 80);
            }
        }
    }
}