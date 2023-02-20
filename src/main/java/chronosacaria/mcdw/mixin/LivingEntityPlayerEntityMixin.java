package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import chronosacaria.mcdw.damagesources.OffHandDamageSource;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
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

            float storedAmount = amount * Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.directDamageEnchantmentMultiplier;

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.AMBUSH))
                    amount += storedAmount * EnchantmentEffects.ambushDamage(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.CRITICAL_HIT))
                    amount += storedAmount * EnchantmentEffects.criticalHitDamage(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.VOID_STRIKE))
                    amount += storedAmount * EnchantmentEffects.voidStrikeDamage(attackingEntity, victim, isOffHandAttack);
                if (!PlayerAttackHelper.isLikelyNotMeleeDamage(source)) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.PAIN_CYCLE))
                        amount += storedAmount * EnchantmentEffects.painCycleDamage(attackingEntity, isOffHandAttack);
                }
            }

            if (source.getSource() instanceof PlayerEntity attackingPlayer) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ENIGMA_RESONATOR))
                    amount += storedAmount * EnchantmentEffects.enigmaResonatorDamage(attackingPlayer, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.DYNAMO))
                    amount += storedAmount * EnchantmentEffects.dynamoDamage(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHADOW_SHOT))
                    amount += storedAmount * EnchantmentEffects.shadowFormDamage(attackingEntity);
            }

            if (source.getSource() instanceof PersistentProjectileEntity ppe) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ENIGMA_RESONATOR))
                    amount += storedAmount * EnchantmentEffects.enigmaShotDamage(attackingEntity, victim, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.GROWING))
                    amount += storedAmount * EnchantmentEffects.growingDamage(attackingEntity, victim, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.VOID_SHOT))
                    amount += storedAmount * EnchantmentEffects.voidShotDamage(victim, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.DYNAMO))
                    amount += storedAmount * EnchantmentEffects.dynamoShotDamage(attackingEntity, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHADOW_SHOT))
                    amount += storedAmount * EnchantmentEffects.shadowFormShotDamage(attackingEntity, ppe);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.OVERCHARGE))
                    amount += storedAmount * EnchantmentEffects.overchargeDamage(ppe);
            }

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.COMMITTED))
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

            if (source.getSource() instanceof LivingEntity) {

                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.FREEZING))
                    EnchantmentEffects.applyFreezing(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.POISONING))
                    EnchantmentEffects.applyPoisoning(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.POISON_CLOUD))
                    EnchantmentEffects.applyPoisonCloud(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.RADIANCE))
                    EnchantmentEffects.applyRadianceCloud(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHOCKWAVE))
                    EnchantmentEffects.applyShockwave(attackingEntity, victim, amount, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.STUNNING))
                    EnchantmentEffects.applyStunning(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SWIRLING))
                    EnchantmentEffects.applySwirling(attackingEntity, victim, amount, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.THUNDERING))
                    EnchantmentEffects.applyThundering(attackingEntity, amount, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.WEAKENING))
                    EnchantmentEffects.applyWeakeningCloud(attackingEntity, victim, isOffHandAttack);

                if (!source.isProjectile()) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.CHAINS))
                        EnchantmentEffects.applyChains(attackingEntity, victim, isOffHandAttack);
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.GRAVITY))
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

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ECHO))
                    EnchantmentEffects.echoDamage(attackingEntity, victim, amount, isOffHandAttack);
            }
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void mcdw$onDeath(DamageSource source, CallbackInfo ci) {
        boolean isOffHandAttack = source instanceof OffHandDamageSource;
        if (source.getAttacker() instanceof LivingEntity attackingEntity) {

            LivingEntity victim = (LivingEntity) (Object) this;

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.EXPLODING))
                    EnchantmentEffects.explodingDamage(attackingEntity, victim, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.RAMPAGING))
                    EnchantmentEffects.applyRampaging(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.LEECHING))
                    EnchantmentEffects.applyLeeching(attackingEntity, victim, isOffHandAttack);

                if (!PlayerAttackHelper.isLikelyNotMeleeDamage(source)) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.GUARDING_STRIKE))
                        EnchantmentEffects.applyGuardingStrike(attackingEntity, isOffHandAttack);
                }
            }
        }

        if (source.getAttacker() instanceof PlayerEntity attackingEntity) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.REFRESHMENT))
                    EnchantmentEffects.applyRefreshment(attackingEntity, isOffHandAttack);
            }

            if (source.getSource() instanceof PersistentProjectileEntity ppe) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.REFRESHMENT))
                    EnchantmentEffects.applyRefreshment(attackingEntity, isOffHandAttack);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHADOW_SHOT))
                    EnchantmentEffects.applyShadowShotShadowForm(attackingEntity, ppe, 80);
            }
        }
    }

}