package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
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

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.AMBUSH))
                    amount *= EnchantmentEffects.ambushDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CRITICAL_HIT))
                    amount *= EnchantmentEffects.criticalHitDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_STRIKE))
                    amount *= EnchantmentEffects.voidStrikeDamage(attackingEntity, victim);
            }

            if (source.getSource() instanceof PlayerEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ENIGMA_RESONATOR))
                    amount *= EnchantmentEffects.enigmaResonatorDamage(attackingEntity, victim);
            }

            if (source.isProjectile()) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ENIGMA_RESONATOR))
                    amount *= EnchantmentEffects.enigmaShotDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GROWING))
                    amount *= EnchantmentEffects.growingDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_SHOT))
                    amount *= EnchantmentEffects.voidShotDamage(attackingEntity, victim);
            }

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COMMITTED))
                amount += EnchantmentEffects.committedDamage(attackingEntity, victim);
        }

        return amount;
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdw$onApplyDamageHead(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return;

        LivingEntity victim = (LivingEntity) (Object) this;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHARGE))
                    EnchantmentEffects.applyCharge(attackingEntity);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.FREEZING))
                    EnchantmentEffects.applyFreezing(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISONING))
                    EnchantmentEffects.applyPoisoning(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISON_CLOUD))
                    EnchantmentEffects.applyPoisonCloud(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RADIANCE))
                    EnchantmentEffects.applyRadianceCloud(attackingEntity);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SHOCKWAVE))
                    EnchantmentEffects.applyShockwave(attackingEntity, victim, amount);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.STUNNING))
                    EnchantmentEffects.applyStunning(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SWIRLING))
                    EnchantmentEffects.applySwirling(attackingEntity, victim, amount);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.THUNDERING))
                    EnchantmentEffects.applyThundering(attackingEntity, amount);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.WEAKENING))
                    EnchantmentEffects.applyWeakeningCloud(attackingEntity, victim);

                if (!source.isProjectile()) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHAINS))
                        EnchantmentEffects.applyChains(attackingEntity, victim);
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GRAVITY))
                        EnchantmentEffects.applyGravity(attackingEntity, victim);
                }

                if (source.isProjectile()) {
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.TEMPO_THEFT))
                        EnchantmentEffects.applyTempoTheft(attackingEntity, victim);
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("TAIL"))
    public void mcdw$onApplyDamageTail(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return;

        LivingEntity victim = (LivingEntity) (Object) this;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ECHO))
                    EnchantmentEffects.echoDamage(attackingEntity, victim, amount);
            }
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void mcdw$onDeath(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return;

        LivingEntity victim = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.EXPLODING))
                EnchantmentEffects.explodingDamage(attackingEntity, victim);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RAMPAGING))
                EnchantmentEffects.applyRampaging(attackingEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEECHING))
                EnchantmentEffects.applyLeeching(attackingEntity, victim);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REFRESHMENT))
                EnchantmentEffects.applyRefreshment((PlayerEntity) attackingEntity);

            if (!PlayerAttackHelper.isLikelyNotMeleeDamage(source)) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GUARDING_STRIKE))
                    EnchantmentEffects.applyGuardingStrike(attackingEntity);
            }
        }

        if (source.isProjectile()){
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REFRESHMENT))
                EnchantmentEffects.applyRefreshment((PlayerEntity) attackingEntity);
        }
    }

}