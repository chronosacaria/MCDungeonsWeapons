package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enchants.util.AOECloudHelper;
import chronosacaria.mcdw.enchants.util.AOEHelper;
import chronosacaria.mcdw.enchants.util.AbilityHelper;
import chronosacaria.mcdw.enchants.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import chronosacaria.mcdw.weapons.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    @Final
    private DefaultedList<ItemStack> equippedHand;

    @Shadow
    public abstract ItemStack getOffHandStack();

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);


    @Shadow
    public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow
    public abstract void onDeath(DamageSource source);

    @Shadow
    private @Nullable LivingEntity attacker;
    @Shadow
    protected float lastDamageTaken;
    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Shadow
    public abstract float getHealth();

    @Shadow
    public abstract float getMaxHealth();

    @Shadow
    protected abstract void spawnItemParticles(ItemStack stack, int count);

    @Shadow
    protected abstract int getCurrentExperience(PlayerEntity player);


    /* * * * * * * * * * * * * * * * * * * *|
    |**** ENCHANTMENTS -- ANIMA CONDUIT ****|
    |* * * * * * * * * * * * * * * * * * * */

    @Inject(
            at = @At("HEAD"),
            method = "onDeath",
            cancellable = true)

    private void onAnimaConduitKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack) >= 1)) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack);
            float healthRegained;

            //ANIMA CONDUIT AS PER KILL
            if (user.getHealth() < user.getMaxHealth()) {
                healthRegained = (float) (getCurrentExperience((PlayerEntity) user) * (0.2 * level));
                user.heal(healthRegained);
                ((PlayerEntity) user).addExperienceLevels(-999999999);
                //this.world.sendEntityStatus(this,(byte)35);
            }
        }
    }


    /* * * * * * * * * * * * * * * * * |
    |**** ENCHANTMENTS -- BUSY BEE ****|
    | * * * * * * * * * * * * * * * * */

    public EntityType<SummonedBeeEntity> s_bee =
            SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // -- For summoning Bee with Bee Stinger Item
    @Inject(
            at = @At("HEAD"),
            method = "swingHand(Lnet/minecraft/util/Hand;)V",
            cancellable = true)

    private void swingHand(Hand hand, CallbackInfo ci) {
        ItemStack mainHandStack = equippedHand.get(0);
        ItemStack offHandStack = getOffHandStack();
        if (mainHandStack.getItem() == Rapiers.SWORD_BEESTINGER && offHandStack.getItem() == ItemRegistry.BEE_STINGER_ITEM) {
            SummonedBeeEntity summonedBeeEntity_1 = s_bee.create(world);
            summonedBeeEntity_1.setSummoner(this);
            summonedBeeEntity_1.refreshPositionAndAngles(this.getX(), this.getY() + 1, this.getZ(), 0, 0);
            world.spawnEntity(summonedBeeEntity_1);
        }
        if ((offHandStack.getItem() == ItemRegistry.BEE_STINGER_ITEM && (mainHandStack.getItem() == Rapiers.SWORD_BEESTINGER))) {
            offHandStack.decrement(1);
        }
    } //END BUSY BEE ENCHANTMENT

    /* * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- CHAINS *****|
    | * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyChains(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Hammers.HAMMER_FLAIL.asItem()
                            || mainHandStack.getItem() == Scythes.SICKLE_JAILORS_SCYTHE.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CHAINS, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.CHAINS, mainHandStack);

                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.2) {
                        AOEHelper.chainNearbyEntities(
                                user,
                                target,
                                1.5F,
                                level);
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- COMMITTED *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCommittedDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() ==  SoulDaggers.SWORD_TRUTHSEEKER.asItem()
                            || mainHandStack.getItem() == Staves.STAFF_GROWING_STAFF.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, mainHandStack);


                    float getTargetHealth = target.getHealth();
                    float getTargetMaxHealth = target.getMaxHealth();
                    float getTargetRemainingHealth = getTargetHealth / getTargetMaxHealth;
                    float getOriginalDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    float extraDamageMultiplier = 0.1F + level * 0.1F;
                    float getExtraDamage = (getOriginalDamage * (1 - getTargetRemainingHealth) * extraDamageMultiplier);

                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.2) {
                        if ((Math.abs(getTargetHealth)) < (Math.abs(getTargetMaxHealth))) {
                            target.damage(DamageSource.player((PlayerEntity) user),
                                    getExtraDamage);
                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.ENTITY_GENERIC_EXPLODE,
                                    SoundCategory.PLAYERS,
                                    1.0F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- CRITICAL HIT *****|
    | * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCriticalHitDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Swords.SWORD_HAWKBRAND.asItem()
                    || mainHandStack.getItem() == Katanas.SWORD_MASTERS_KATANA.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.CRITICAL_HIT, mainHandStack);

                    float criticalHitChance;
                    criticalHitChance = 0.5f + level * 0.05F;
                    float criticalHitRand = user.getRandom().nextFloat();
                    float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    float extraDamageMultiplier = 1.5F;
                    float getExtraDamage = (attackDamage * (extraDamageMultiplier));

                    if (criticalHitRand <= criticalHitChance) {
                        target.damage(DamageSource.player((PlayerEntity) user),
                                getExtraDamage);
                        target.world.playSound(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
                                SoundCategory.PLAYERS,
                                64.0F,
                                1.0F);
                    }
                }
            }
        }
    }


    /* * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- ECHO *****|
    | * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEchoDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Spears.SPEAR_WHISPERING_SPEAR.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack);

                    float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    float cooledAttackStrength = 0.5F;
                    attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;
                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.1 * level) {
                        AOEHelper.causeEchoAttack(user, target, attackDamage, 3.0f, level);
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


    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- EXPLODING *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(
            at = @At("HEAD"),
            method = "onDeath",
            cancellable = true)

    private void onExplodingKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (mainHandStack != null) {
            uniqueWeaponFlag = mainHandStack.getItem() == DoubleAxes.AXE_CURSED.asItem()
                    || mainHandStack.getItem() == Staves.STAFF_BATTLESTAFF_OF_TERROR.asItem();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, mainHandStack);
            float explodingDamage;
            explodingDamage = target.getMaxHealth() * 0.2f * level;
            float chance = user.getRandom().nextFloat();
            if (chance <= 0.2) {
                if (uniqueWeaponFlag) explodingDamage += (target.getMaxHealth() * 0.2F);
                target.world.playSound(
                        null,
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        SoundEvents.ENTITY_GENERIC_EXPLODE,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F);
                AOECloudHelper.spawnExplosionCloud(user, target, 3.0F);
                AOEHelper.causeExplosionAttack(user, target, explodingDamage, 3.0F);
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- FIRE ASPECT (CUSTOM) *****|
    | * * * * * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyFireAspectCustom(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Axes.AXE_FIREBRAND.asItem();
                }
                if (user != null && mainHandStack != null && uniqueWeaponFlag) {
                    boolean burning = false;
                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.1) {
                        if (target instanceof LivingEntity) {
                            if (!target.isOnFire()) {
                                burning = true;
                                target.setOnFireFor(4);
                            }
                        }
                    }
                }
            }
        }
    }


    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- FREEZING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyFreezing(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Daggers.DAGGER_FANGS_OF_FROST.asItem()
                            || mainHandStack.getItem() == Scythes.SICKLE_FROST_SCYTHE.asItem()
                            || mainHandStack.getItem() == Rapiers.SWORD_FREEZING_FOIL.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING, mainHandStack);

                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.3) {
                        StatusEffectInstance freezing = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, level - 1);
                        StatusEffectInstance miningFatigue = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 60, level - 1);
                        target.addStatusEffect(freezing);
                        target.addStatusEffect(miningFatigue);
                    }
                }
            }
        }
    }


    /* * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- GRAVITY *****|
    |* * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyGravity(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Hammers.HAMMER_GRAVITY.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CHAINS, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.CHAINS, mainHandStack);

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

    /* * * * * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- JUNGLE'S POISON *****|
    |* * * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyJunglesPoison(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Whips.WHIP_VINE_WHIP.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, mainHandStack) >= 1 || uniqueWeaponFlag)) {
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


    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- LEECHING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(
            at = @At("HEAD"),
            method = "onDeath",
            cancellable = true)

    private void onLeechingKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (mainHandStack != null) {
            uniqueWeaponFlag = mainHandStack.getItem() == Claymores.SWORD_HEARTSTEALER.asItem();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack);
            float healthRegained;
            float targetMaxHealth = target.getMaxHealth();

            //LEECHING AS PER KILL
            if (user.getHealth() < user.getMaxHealth()) {
                healthRegained = (0.2F + 0.2F * level) * targetMaxHealth;
                if (uniqueWeaponFlag) healthRegained += 0.04F * targetMaxHealth;
                user.heal(healthRegained);
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- POISON CLOUD *****|
    | * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyPoisonCloud(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Sickles.SICKLE_NIGHTMARES_BITE.asItem()
                            || mainHandStack.getItem() == Glaives.SPEAR_VENOM_GLAIVE.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHandStack) >= 1 || uniqueWeaponFlag)) {
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

    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- RADIANCE *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyRadianceCloud(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Hammers.HAMMER_SUNS_GRACE.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, mainHandStack) >= 1 || uniqueWeaponFlag)) {
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

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- RAMPAGING *****|
    |* * * * * * * * * * * * * * * * * * */
    @Inject(
            at = @At("HEAD"),
            method = "onDeath",
            cancellable = true)

    private void onRampagingKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (mainHandStack != null) {
            uniqueWeaponFlag = mainHandStack.getItem() == Curves.SWORD_DANCERS_SWORD.asItem();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RAMPAGING, mainHandStack) >= 1)) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.RAMPAGING, mainHandStack);
            float rampagingRand = user.getRandom().nextFloat();
            if (rampagingRand <= 0.1F) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, level * 100, 4);
                user.addStatusEffect(rampage);
            }
        }
        if (uniqueWeaponFlag) {
            float rampagingRand = user.getRandom().nextFloat();
            if (rampagingRand <= 0.1F) {
                StatusEffectInstance rampage = new StatusEffectInstance(StatusEffects.HASTE, 100, 4);
                user.addStatusEffect(rampage);
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- SHOCKWAVE *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyShockwaveDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == DoubleAxes.AXE_WHIRLWIND.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, mainHandStack);

                    float SHOCKWAVE_DAMAGE_MULTIPLIER = 0.25F;


                    float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    float cooledAttackStrength = 0.5F;
                    attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

                    float shockwaveDamage = attackDamage * SHOCKWAVE_DAMAGE_MULTIPLIER;
                    shockwaveDamage *= (level + 1) / 2.0F;


                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.1 * level) {
                        AOEHelper.causeShockwaveAttack(
                                (PlayerEntity) user,
                                target,
                                shockwaveDamage,
                                3.0f);

                        target.world.playSound(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                                SoundCategory.PLAYERS,
                                1.0F,
                                1.0F);
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- SMITE (CUSTOM) *****|
    | * * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySmitingDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Glaives.SPEAR_GRAVE_BANE.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack);

                    float SMITING_DAMAGE_MULTIPLIER = 1.25F;


                    float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    //float cooledAttackStrength = 0.5F;
                    //attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

                    float smitingDamage = attackDamage * SMITING_DAMAGE_MULTIPLIER;
                    smitingDamage *= (level + 1) / 2.0F;


                    float chance = user.getRandom().nextFloat();
                    if (chance <= 1 && target.isUndead()) {
                        AOEHelper.causeSmitingAttack(
                                (PlayerEntity) user,
                                target,
                                smitingDamage,
                                3.0f);
                    }
                }
            }
        }
    }


    /* * * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- SOUL SIPHON *****|
    |* * * * * * * * * * * * * * * * * * * */

    @Inject(
            at = @At("HEAD"),
            method = "onDeath",
            cancellable = true)

    private void onSoulSiphonKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity)source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (mainHandStack != null) {
            uniqueWeaponFlag = mainHandStack.getItem() == SoulDaggers.DAGGER_ETERNAL_KNIFE.asItem();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack) >= 1)) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack);
            float siphonRand = user.getRandom().nextFloat();
            if (siphonRand <= 0.1F){
                if (attackingPlayer != null) {
                    attackingPlayer.addExperience(level * 3);
                }
            }
        }
        if (uniqueWeaponFlag) {
            float siphonRand = user.getRandom().nextFloat();
            if (siphonRand <= 0.1F) {
                if (attackingPlayer != null) {
                    attackingPlayer.addExperience(3);
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- STUNNING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyStunningDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Axes.AXE_HIGHLAND.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack);

                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.2 * level) {
                        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
                        //this.world.sendEntityStatus(this,(byte)35);
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- SWIRLING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySwirlingDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Daggers.DAGGER_SHEAR_DAGGER.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, mainHandStack);

                    float SWIRLING_DAMAGE_MULTIPLIER = 0.5F;


                    float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    float cooledAttackStrength = 0.5F;
                    attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

                    float swirlingDamage = attackDamage * SWIRLING_DAMAGE_MULTIPLIER;
                    swirlingDamage *= (level + 1) / 2.0F;


                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.1 * level) {
                        AOEHelper.causeSwirlingAttack(
                                (PlayerEntity) user,
                                target,
                                swirlingDamage,
                                1.5f);

                        target.world.playSound(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                                SoundCategory.PLAYERS,
                                64.0F,
                                1.0F);
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- THUNDERING *****|
    | * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyThunderingDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Hammers.HAMMER_STORMLANDER.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.THUNDERING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.THUNDERING, mainHandStack);

                    float chance = user.getRandom().nextFloat();
                    if (chance <= 0.2) {
                        AOEHelper.electrocuteNearbyEnemies(
                                user,
                                target,
                                5 * level,
                                10);
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- WEAKENING *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyWeakeningDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (mainHandStack != null) {
                    uniqueWeaponFlag = mainHandStack.getItem() == Curves.SWORD_NAMELESS_BLADE.asItem();
                }

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
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

    /* * * * * * * * * * * |
    |****STATUS REMOVAL****|
    | * * * * * * * * * * */
// Remove Poison Effect if Player has weapon with Poison Cloud Enchantment
    @Inject(
            at = @At("HEAD"),
            method = "tick",
            cancellable = true)

    private void removePoisonIfPCEnchant(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            PlayerEntity entity = (PlayerEntity) (Object) this;
            ItemStack mainHand = getMainHandStack();


            if (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHand) >= 1
                    || mainHand.getItem() == Sickles.SICKLE_NIGHTMARES_BITE.asItem()
                    || mainHand.getItem() == Glaives.SPEAR_VENOM_GLAIVE.asItem())
            {
                this.removeStatusEffect(StatusEffects.POISON);
            }
        }
    }
    // Remove Stunned Effects if Player has weapon with Stunning Enchantment

    @Inject(
            at = @At("HEAD"),
            method = "tick",
            cancellable = true)

    private void removeStunnedIfPCEnchant(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            PlayerEntity entity = (PlayerEntity) (Object) this;
            ItemStack mainHand = getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHand) >= 1
                    || mainHand.getItem() == Axes.AXE_HIGHLAND.asItem()) {
                this.removeStatusEffect(StatusEffects.NAUSEA);
                this.removeStatusEffect(StatusEffects.SLOWNESS);
            }
        }
    }

    // Remove Weakness Effect if Player has weapon with Weakening Enchantment

    @Inject(
            at = @At("HEAD"),
            method = "tick",
            cancellable = true)

    private void removeWeakenedIfPCEnchant(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity) {
            PlayerEntity entity = (PlayerEntity) (Object) this;
            ItemStack mainHand = getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHand) >= 1
                    || mainHand.getItem() == Curves.SWORD_NAMELESS_BLADE.asItem()) {
                this.removeStatusEffect(StatusEffects.WEAKNESS);
            }
        }
    }
}