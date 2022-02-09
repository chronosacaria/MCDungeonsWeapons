package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        if (!((Object) this instanceof LivingEntity victim))
            return amount;

        if (attackingEntity == null)
            return amount;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.AMBUSH))
                    amount *= EnchantmentEffects.ambushDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CRITICAL_HIT))
                    amount *= EnchantmentEffects.criticalHitDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ENIGMA_RESONATOR))
                    amount *= EnchantmentEffects.enigmaResonatorDamage(attackingEntity, victim);
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_STRIKE))
                    amount *= EnchantmentEffects.voidStrikeDamage(attackingEntity, victim);
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
        if (!((Object) this instanceof LivingEntity victim))
            return;

        if (attackingEntity == null)
            return;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHARGE))
                    EnchantmentEffects.applyCharge((PlayerEntity) attackingEntity);
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
                    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GRAVITY))
                        EnchantmentEffects.applyGravity((PlayerEntity) attackingEntity, victim);
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
        if (!((Object) this instanceof LivingEntity victim))
            return;

        if (attackingEntity == null)
            return;

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
        if (!((Object) this instanceof LivingEntity victim))
            return;

        if (attackingEntity == null)
            return;

        if (source.getSource() instanceof LivingEntity) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.EXPLODING))
                EnchantmentEffects.explodingDamage(attackingEntity, victim);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RAMPAGING))
                EnchantmentEffects.applyRampaging(attackingEntity);


            if (!PlayerAttackHelper.isLikelyNotMeleeDamage(source)) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GUARDING_STRIKE))
                    EnchantmentEffects.applyGuardingStrike(attackingEntity);
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyReplenishEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();

        if (source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REPLENISH)) {
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH,
                            mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, mainHandStack);
                        if (user instanceof PlayerEntity) {
                            if (level >= 1) {
                                float replenishRand = user.getRandom().nextFloat();
                                float replenishChance = 0.03f + (0.07f * level);
                                if (replenishRand <= replenishChance) {
                                    ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(),
                                            user.getZ(),
                                            new ItemStack(Items.ARROW));
                                    user.world.spawnEntity(arrowDrop);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyRicochet(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof PlayerEntity attacker)) return;

        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack mainHandStack = attacker.getMainHandStack();

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RICOCHET))  {
            if (mainHandStack != ItemStack.EMPTY && (EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, mainHandStack);
                if (level >= 1) {
                    float damageMultiplier = 0.1F + ((level - 1) * 0.07F);
                    float arrowVelocity = McdwBow.maxBowRange;
                    if (arrowVelocity > 0.1F) {
                        ProjectileEffectHelper.riochetArrowTowardsOtherEntity(target, 10, damageMultiplier, arrowVelocity);
                    }
                }
            }
        }
    }
}
