package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.api.util.AOECloudHelper;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.api.util.AbilityHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Shadow
    protected abstract void spawnItemParticles(ItemStack stack, int count);

    @Shadow
    protected abstract int getCurrentExperience(PlayerEntity player);


    /* * * * * * * * * * * * * * * * * * * *|
    |**** ENCHANTMENTS -- ANIMA CONDUIT ****|
    |* * * * * * * * * * * * * * * * * * * */

    @Shadow public abstract boolean isMobOrPlayer();

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onAnimaConduitEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        PiglinEntity piglinEntity = null;

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("mixin_anima")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack) >= 1) && user != piglinEntity) {
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
    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;)V", cancellable = true)

    private void swingHand(Hand hand, CallbackInfo ci) {
        ItemStack mainHandStack = equippedHand.get(0);
        ItemStack offHandStack = getOffHandStack();
        if (McdwEnchantsConfig.getValue("mixin_bee")) {
            if (mainHandStack.getItem() == ItemRegistry.getItem("sword_beestinger") && offHandStack.getItem() == ItemRegistry.getItem("item_bee_stinger")) {
                SummonedBeeEntity summonedBeeEntity_1 = s_bee.create(world);
                if (summonedBeeEntity_1 != null) {
                    summonedBeeEntity_1.setSummoner(this);
                    summonedBeeEntity_1.refreshPositionAndAngles(this.getX(), this.getY() + 1, this.getZ(), 0, 0);
                    world.spawnEntity(summonedBeeEntity_1);
                }
            }
            if ((offHandStack.getItem() == ItemRegistry.getItem("item_bee_stinger") && (mainHandStack.getItem() == ItemRegistry.getItem("sword_beestinger")))) {
                offHandStack.decrement(1);
            }
        }
    } //END BUSY BEE ENCHANTMENT

    /* * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- CHAINS *****|
    | * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyChainsEnchantment(DamageSource source, float amount, CallbackInfo info) {

        if (source.getSource() instanceof ArrowEntity) {
            return;
        }
        if (!(source.getAttacker() instanceof LivingEntity)) {
            return;
        }

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity && !source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag = false;
                if (McdwEnchantsConfig.getValue("mixin_chains")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("hammer_flail").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sickle_jailors_scythe").asItem();
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
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- COMMITTED *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCommittedEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (!(source.getAttacker() instanceof PlayerEntity)) {
            return;
        }

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_committed")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("sword_truthseeker").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("staff_growing_staff").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("dagger_resolute_tempest_knife").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, mainHandStack);


                        float getTargetHealth = target.getHealth();
                        float getTargetMaxHealth = target.getMaxHealth();
                        float getTargetRemainingHealth = getTargetHealth / getTargetMaxHealth;
                        float getOriginalDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float extraDamageMultiplier = 0.1F + level * 0.1F;
                        float getExtraDamage = (getOriginalDamage * (1 - getTargetRemainingHealth) * extraDamageMultiplier);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.2) {
                            if ((Math.abs(getTargetHealth)) < (Math.abs(getTargetMaxHealth))) {
                                target.damage(DamageSource.mob( user),
                                        getExtraDamage);
                                target.world.playSound(
                                        null,
                                        target.getX(),
                                        target.getY(),
                                        target.getZ(),
                                        SoundEvents.ENTITY_GENERIC_EXPLODE,
                                        SoundCategory.PLAYERS,
                                        0.5F,
                                        1.0F);
                            }
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
    public void applyCriticalHitEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag = false;
                if (McdwEnchantsConfig.getValue("mixin_critical")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("sword_hawkbrand").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sword_masters_katana").asItem();
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
                            target.damage(DamageSource.mob( user),
                                    getExtraDamage);
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


    /* * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- ECHO *****|
    | * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEchoEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_echo")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("spear_whispering_spear").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack);

                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float cooledAttackStrength = 0.5F;
                        attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;
                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + level * 0.15) {
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
    }

    /* * * * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- ENIGMA RESONATOR *****|
    | * * * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEnigmaResonatorEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_enigma")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("dagger_moon").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("gauntlet_soul_fists").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack);

                        int numSouls = Math.min((getCurrentExperience((PlayerEntity) user)), 50);
                        float soulsCriticalBoostChanceCap;
                        soulsCriticalBoostChanceCap = 0.1F + 0.05F * level;

                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float extraDamageMultiplier = attackDamage == attackDamage ? 1.5F : 3.0F;
                        float getExtraDamage = (attackDamage * (extraDamageMultiplier));

                        float chance = user.getRandom().nextFloat();
                        if (chance <= Math.min((numSouls / 50.0), soulsCriticalBoostChanceCap)) {
                            target.damage(DamageSource.mob( user),
                                    getExtraDamage);
                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.BLOCK_ENDER_CHEST_OPEN,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- EXPLODING *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onExplodingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag = false;
        if (McdwEnchantsConfig.getValue("mixin_exploding")) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("axe_cursed").asItem()
                        || mainHandStack.getItem() == ItemRegistry.getItem("staff_battlestaff_of_terror").asItem();
            }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.EXPLODING, mainHandStack);
                float explodingDamage;
                explodingDamage = target.getMaxHealth() * 0.2f * level;
                float chance = user.getRandom().nextFloat();
                if (chance <= 0.2) {
                    if (uniqueWeaponFlag) {
                        explodingDamage += (target.getMaxHealth() * 0.2F);
                    }
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

    /* * * * * * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- FIRE ASPECT (CUSTOM) *****|
    | * * * * * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyFireAspectEnchantmentCustom(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_custom_fire_aspect")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("axe_firebrand").asItem();
                    }
                    if (user != null
                            && mainHandStack != null
                            && uniqueWeaponFlag
                            && !(EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, mainHandStack) >= 1)) {
                        float chance = user.getRandom().nextFloat();
                        if (chance <= 1) {
                            if (target instanceof LivingEntity) {
                                if (!target.isOnFire()) {
                                    target.setOnFireFor(4);
                                }
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
    public void applyFreezingEnchantment(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag = false;
                if (McdwEnchantsConfig.getValue("mixin_freezing")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("dagger_fangs_of_frost").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sickle_frost_scythe").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sword_freezing_foil").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("dagger_chill_gale_knife").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sword_frost_slayer").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.3 + (level * 0.1)) {
                            AbilityHelper.causeFreesing(target, 100);
                        }
                    }
                }
            }
        }
    }


    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- FUSE SHOT *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)

    private void applyFuseShotEnchantment(DamageSource source, float amount, CallbackInfo info)  {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_fuse_shot")) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("crossbow_exploding_crossbow").asItem();
            }
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, mainHandStack);
                float chance = user.getRandom().nextFloat();
                    if (chance <= (0.2 + level * 0.15)) {
                        AbilityHelper.causeFuseShot(user, target, level);
                    }
                }
            }
        }


    /* * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- GRAVITY *****|
    |* * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyGravityEnchantment(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity && !source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_gravity")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("hammer_gravity").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack) >= 1 || uniqueWeaponFlag)) {
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

    /* * * * * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- JUNGLE'S POISON *****|
    |* * * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyJunglesPoisonEnchantment(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag = false;
                if (McdwEnchantsConfig.getValue("mixin_jungle_poison")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("whip_vine_whip").asItem();
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
    }

    /* * * * * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- KNOCKBACK (CUSTOM) *****|
    | * * * * * * * * * * * * * * * * * * * * * * */

    /*@Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyKnockbackEnchantmentCustom(DamageSource source, float amount, CallbackInfo info) {
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
                if (config.mixinCustomFireAspect) {
                    if (mainHandStack != null) {
                        //uniqueWeaponFlag = mainHandStack.getItem() == Staves.STAFF_BATTLESTAFF.asItem();
                    }
                    if (user != null
                            && mainHandStack != null
                            //&& uniqueWeaponFlag
                            && !(EnchantmentHelper.getLevel(Enchantments.KNOCKBACK, mainHandStack) >= 1)) {
                        float chance = user.getRandom().nextFloat();
                        if (chance <= 1) {
                            if (target instanceof LivingEntity) {
                                double motionX = target.getX() - (user.getX());
                                double motionY = target.getY() - (user.getY());
                                double motionZ = target.getZ() - (user.getZ());
                                Vec3d vec3d = new Vec3d(motionX, motionY, motionZ);

                                target.setVelocity(vec3d);
                            }
                        }
                    }
                }
                }
            }
        }
    }*/


    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- LEECHING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onLeechingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_leeching")) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("sword_hearstealer").asItem();
            }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack);
                float healthRegained;
                float targetMaxHealth = target.getMaxHealth();

                //LEECHING AS PER KILL
                if (user.getHealth() < user.getMaxHealth()) {
                    healthRegained = (0.2F + 0.2F * level) * targetMaxHealth;
                    if (uniqueWeaponFlag) {
                        healthRegained += 0.04F * targetMaxHealth;
                    }
                    user.heal(healthRegained);
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * |
    |**** ENCHANTMENTS -- LOOTING (CUSTOM) ****|
    | * * * * * * * * * * * * * * * * * * * * */

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    public void onCustomLootingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_custom_looting")) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("spear_fortune").asItem();
            }
            if (user != null
                    && mainHandStack != null
                    && uniqueWeaponFlag
                    && !(EnchantmentHelper.getLevel(Enchantments.LOOTING, mainHandStack) >= 1)) {
                if (target instanceof LivingEntity) {
                        EnchantmentHelper.getLooting(user);
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- POISON CLOUD *****|
    | * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyPoisonCloudEnchantment(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_poison")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("sickle_nightmares_bite").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("spear_venom_glaive").asItem();
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
    }

    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- RADIANCE *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyRadianceEnchantmentCloud(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_radiance")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("hammer_suns_grace").asItem();
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
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- RAMPAGING *****|
    |* * * * * * * * * * * * * * * * * * */
    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onRampagingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_rampaging")) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("sword_dancers_sword").asItem()
                        || mainHandStack.getItem() == ItemRegistry.getItem("gauntlet_maulers").asItem();
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
    }

    /* * * * * * * * * * * * * * * * * * * *|
    |*****  ENCHANTMENTS -- REPLENISH  *****|
    |* * * * * * * * * * * * * * * * * * * */

    /*@Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyReplenishEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        //LivingEntity target = (LivingEntity) (Object) this;

        if (source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (config.mixinReplenish) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("bow_hunters_promise").asItem();
                    }
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, mainHandStack);
                        if (user instanceof PlayerEntity) {
                            if (level >= 1) {
                                float replenishRand = user.getRandom().nextFloat();
                                float replenishChance = 0;
                                if (level == 1) replenishChance = 0.10f;
                                if (level == 2) replenishChance = 0.17f;
                                if (level == 3) replenishChance = 0.24f;
                                if (replenishRand <= replenishChance) {
                                    ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(), user.getZ(),
                                            new ItemStack(Items.ARROW));
                                    user.world.spawnEntity(arrowDrop);
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/

    /* * * * * * * * * * * * * * * * * * * |
    |*****  ENCHANTMENTS -- RICOCHET  *****|
    | * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyRicochet(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;

        PlayerEntity user = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;

        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_ricochet"))  {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("crossbow_lightning_harp_crossbow").asItem()
                        || mainHandStack.getItem() == ItemRegistry.getItem("crossbow_slayer_crossbow").asItem()
                        || mainHandStack.getItem() == ItemRegistry.getItem("bow_echo_of_the_valley").asItem();
            }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, mainHandStack);
                float damageMultiplier;
                damageMultiplier = 0.1F + (level - 1 * 0.07F);
                float arrowVelocity = McdwBow.maxBowRange;
                if (arrowVelocity > 0.1F) {
                    ProjectileEffectHelper.riochetArrowTowardsOtherEntity(target, 10, damageMultiplier,
                            arrowVelocity);
                }
            }
            if (uniqueWeaponFlag) {
                float damageMultiplier;
                damageMultiplier = 0.1F + 0.07F;
                float arrowVelocity = McdwBow.maxBowRange;
                if (arrowVelocity > 0.1F) {
                    ProjectileEffectHelper.riochetArrowTowardsOtherEntity(target, 10, damageMultiplier,
                            arrowVelocity);
                }
            }
        }
    }


    /* * * * * * * * * * * * * * * * * * * * *|
    |*****  ENCHANTMENTS -- ROLL CHARGE  *****|
    |* * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCharge(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;

        PlayerEntity user = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;

        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        //if (config.mixinCharge) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("bow_burst_gale_bow").asItem();
            }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, mainHandStack);
                float chargeRand = user.getRandom().nextFloat();
                if (chargeRand <= 0.1F) {
                    StatusEffectInstance charge = new StatusEffectInstance(StatusEffects.SPEED, level * 20, 4);
                    user.addStatusEffect(charge);
                }
            }
            if (uniqueWeaponFlag) {
                float chargeRand = user.getRandom().nextFloat();
                if (chargeRand <= 0.1F) {
                    StatusEffectInstance charge = new StatusEffectInstance(StatusEffects.SPEED, 20, 4);
                    user.addStatusEffect(charge);
                }
            }
        }
    //}


    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- SHOCKWAVE *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyShockwaveEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {

        if (!(source.getAttacker() instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity user = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_shockwave")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("axe_whirlwind").asItem();
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
                        if (chance <= 0.1 + level * 0.15) {
                            AOEHelper.causeShockwaveAttack(
                                    user,
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
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * *|
    |*****    ENCHANTMENTS -- SMITING    *****|
    |* * * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_smiting")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("spear_grave_bane").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sword_dark_katana").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) >= 1 || uniqueWeaponFlag && !(EnchantmentHelper.getLevel(Enchantments.SMITE, mainHandStack) >= 1))) {
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
    }


    /* * * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- SOUL SIPHON *****|
    |* * * * * * * * * * * * * * * * * * * */

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onSoulSiphonEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_siphon")) {
            if (mainHandStack != null) {
                uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("dagger_eternal_knife").asItem();
            }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack);
                float siphonRand = user.getRandom().nextFloat();
                if (siphonRand <= 0.1F) {
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
    }

    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- STUNNING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyStunningEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_stunning")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("axe_highland").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.2 + level * 0.15) {
                            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10));
                            target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 1));
                            //this.world.sendEntityStatus(this,(byte)35);
                        }
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- SWIRLING *****|
    | * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySwirlingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_swirling")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("dagger_shear_dagger").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sword_broadsword").asItem();
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
                        if (chance <= 0.1 + level * 0.15) {
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
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- TEMPO THEFT *****|
    |* * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyTempoTheftEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_tempo_theft")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("bow_nocturnal_bow").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("bow_shivering_bow").asItem();
                    }
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, mainHandStack);
                        if (target instanceof LivingEntity) {
                            AbilityHelper.stealSpeedFromTarget(user, target, level);
                        }
                    }
                }
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * |
    |***** ENCHANTMENTS -- THUNDERING *****|
    | * * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyThunderingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_thundering")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("hammer_stormlander").asItem();
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
    }

    /* * * * * * * * * * * * * * * * * * *|
    |***** ENCHANTMENTS -- WEAKENING *****|
    |* * * * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyWeakeningEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_weakening")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("sword_nameless_blade").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHandStack) >= 1
                            || uniqueWeaponFlag)) {
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
                    || mainHand.getItem() == ItemRegistry.getItem("sickle_nightmares_bite").asItem()
                    || mainHand.getItem() == ItemRegistry.getItem("spear_venom_glaive").asItem())
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
                    || mainHand.getItem() == ItemRegistry.getItem("axe_highland").asItem()) {
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
                    || mainHand.getItem() == ItemRegistry.getItem("sword_nameless_blade").asItem()) {
                this.removeStatusEffect(StatusEffects.WEAKNESS);
            }
        }
    }
}