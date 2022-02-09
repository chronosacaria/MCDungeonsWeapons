package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.*;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class LivingEntityPlayerEntityMixin {

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    public float mcdwDamageModifiers(float amount, DamageSource source) {

        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return amount;
        if (!((Object) this instanceof LivingEntity livingEntity))
            return amount;

        if (attackingEntity == null)
            return amount;

        if (amount > 0) {

            if (source.getSource() instanceof LivingEntity) {
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.AMBUSH))
                    amount *= EnchantmentEffects.ambushDamage(attackingEntity, livingEntity);
            }

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COMMITTED))
                amount += EnchantmentEffects.committedDamage(attackingEntity, livingEntity);
        }

        return amount;
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void onApplyDamage(DamageSource source, float amount, CallbackInfo info) {

        if (!(source.getSource() instanceof LivingEntity))
            return;
        if (!(source.getAttacker() instanceof PlayerEntity playerEntity))
            return;

        if (playerEntity == null)
            return;

        if (amount > 0) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHARGE))
                EnchantmentEffects.applyCharge(playerEntity);
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCriticalHitEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CRITICAL_HIT)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, mainHandStack);

                        float criticalHitChance;
                        criticalHitChance = 0.5f + level * 0.05F;
                        float criticalHitRand = user.getRandom().nextFloat();
                        //float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float extraDamageMultiplier = 1.5F;
                        float h = target.getHealth();

                        if (criticalHitRand <= criticalHitChance) {
                            target.setHealth(h - (amount * extraDamageMultiplier));
                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEchoEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ECHO)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + level * 0.15) {
                            AOEHelper.causeEchoAttack(user,
                                    target,
                                    3.0f,
                                    level,
                                    amount);
                            user.world.playSound(
                                    null,
                                    user.getX(),
                                    user.getY(),
                                    user.getZ(),
                                    McdwSoundEvents.ECHO_SOUND_EVENT,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEnigmaResonatorEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ENIGMA_RESONATOR)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack);

                        int numSouls = ((PlayerEntity) source.getAttacker()).experienceLevel;

                        // You will never do less than the GENERIC_ATTACK_DAMAGE during an attack from Enigma Resonator
                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float extraDamageMultiplier = (float) (Math.log(numSouls * level))/1.75F;
                        float getExtraDamage = (attackDamage * (extraDamageMultiplier));

                        if (numSouls >= 1){

                            // You wouldn't think that you would need to do this, but haha! You thought wrong! Help
                            // me...
                            int lootingLevel = EnchantmentHelper.getLevel(Enchantments.LOOTING, mainHandStack);

                            if (lootingLevel >= 1 && EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR
                                    , mainHandStack) >= 1){
                                EnchantmentHelper.getLooting(user);
                            }
                            target.damage(DamageSource.GENERIC, getExtraDamage);
                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.PARTICLE_SOUL_ESCAPE,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")

    private void onExplodingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.EXPLODING)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, mainHandStack);
                float explodingDamage;
                explodingDamage = target.getMaxHealth() * 0.2f * level;
                float chance = user.getRandom().nextFloat();
                if (chance <= 0.2) {
                    target.world.playSound(
                            null,
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            SoundEvents.ENTITY_GENERIC_EXPLODE,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                    AOECloudHelper.spawnExplosionCloud(user, target, 3.0F);
                    AOEHelper.causeExplosionAttack(user, target, explodingDamage, 3.0F);
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyGravityEnchantment(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity && !source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GRAVITY)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.3) {
                            AOEHelper.pullInNearbyEntities(
                                    user,
                                    target,
                                    (level + 1) * 3);
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")

    private void onGuardingStrikeEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        if (PlayerAttackHelper.isLikelyNotMeleeDamage(source)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GUARDING_STRIKE)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GUARDING_STRIKE, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.GUARDING_STRIKE, mainHandStack);
                int shieldDuration = 20 + (20 * level);
                StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, shieldDuration, 2);
                user.addStatusEffect(shield);
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyJunglesPoisonEnchantment(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISONING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.3) {
                            StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, level - 1);
                            target.addStatusEffect(poison);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyPoisonCloudEnchantment(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISON_CLOUD)) {
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        //Spawn Poison Cloud @ 30% chance
                        if (target instanceof LivingEntity) {
                            if (chance <= 0.3) {
                                AOECloudHelper.spawnPoisonCloud(
                                        user,
                                        target,
                                        level - 1);
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyRadianceEnchantmentCloud(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RADIANCE)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        //Spawn Regen Cloud @ 20% chance
                        if (target instanceof LivingEntity) {
                            if (chance <= 0.2) {
                                AOECloudHelper.spawnRegenCloud(
                                        user,
                                        level - 1);
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onRampagingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RAMPAGING)) {

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RAMPAGING, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.RAMPAGING, mainHandStack);
                float rampagingRand = user.getRandom().nextFloat();
                if (rampagingRand <= 0.1F) {
                    StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, level * 100, 2,
                            false, false);
                    user.addStatusEffect(rampage);
                }
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
                                float replenishChance = 0;
                                if (level == 1) replenishChance = 0.10f;
                                if (level == 2) replenishChance = 0.17f;
                                if (level == 3) replenishChance = 0.24f;
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

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyShockwaveEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {

        if(!(source.getAttacker() instanceof PlayerEntity user)) return;


        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SHOCKWAVE)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + (level * 0.15)) {
                            AOEHelper.causeShockwaveAttack(
                                    user,
                                    target,
                                    3.0f,
                                    amount);

                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                                    SoundCategory.WEATHER,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyStunningEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.STUNNING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.2 + level * 0.15) {
                            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                            target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySwirlingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SWIRLING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + level * 0.15) {
                            AOEHelper.causeSwirlingAttack(
                                    (PlayerEntity) user,
                                    target,
                                    1.5f,
                                    amount);

                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyTempoTheftEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.TEMPO_THEFT)) {
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, mainHandStack);
                        if (target instanceof LivingEntity) {
                            AbilityHelper.stealSpeedFromTarget(user, target, level);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyThunderingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.THUNDERING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.THUNDERING, mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.THUNDERING, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.2F) {
                            AOEHelper.electrocuteNearbyEnemies(
                                    user,
                                    5 * level,
                                    amount,
                                    Integer.MAX_VALUE);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyVoidStrikeEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity user))
            return;
        if(!((Object) this instanceof LivingEntity target))
            return;
        if (!(source.getSource() instanceof LivingEntity))
            return;

        if (amount != 0.0F) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_STRIKE)) {
                ItemStack mainHandStack = null;
                if (user != null)
                    mainHandStack = user.getMainHandStack();

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.VOID_STRIKE, mainHandStack) >= 1)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_STRIKE, mainHandStack);

                    float voidStrikeChance = 0.15f + level * 0.05F;
                    float voidStrikeRand = user.getRandom().nextFloat();

                    if (voidStrikeRand <= voidStrikeChance) {
                        float voidDamageModifier = 2.0F * level;
                        target.damage(DamageSource.GENERIC, amount * voidDamageModifier);
                        target.world.playSound(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                                SoundCategory.PLAYERS,
                                0.5F,
                                1.0F);
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyWeakeningEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.WEAKENING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        //Spawn Weakening Cloud @ 30% chance
                        if (target instanceof LivingEntity) {
                            if (chance <= 0.3) {
                                AOECloudHelper.spawnWeakeningCloud(
                                        user,
                                        target,
                                        level - 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
