package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.statuseffects.ShieldingStatusEffect;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import chronosacaria.mcdw.weapons.Rapiers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
    public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    @Final
    private DefaultedList<ItemStack> equippedHand;

    @Shadow
    public abstract ItemStack getOffHandStack();

    @Shadow
    private @Nullable LivingEntity attacker;

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Shadow
    @Final
    private DamageTracker damageTracker;
    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Shadow
    public abstract boolean isTarget(LivingEntity entity, TargetPredicate predicate);

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    //BEGIN BUSY BEE ENCHANTMENT
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
/*
    @Inject(
            at = @At("HEAD"),
            method = "damage",
            cancellable = true)

    private void onAttacking(DamageSource source, float damage, CallbackInfoReturnable<Boolean> ci) {
        DamageSource.player(attackingPlayer);
        PlayerEntity user = attackingPlayer;
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = getMainHandStack();

        int level = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack);

        if (EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack) >= 1 ) {

            //float getInitHealth = target.getHealth();
            //float getNewHealth = getInitHealth - damage;
            //float healthRegained;
            //healthRegained = (0.02F + 0.02F * level) * damage;
            //if (user != null) {
            //    user.heal(healthRegained);
            //}

            float getTargetHealth = target.getHealth();
            //float getNewHealth = getTargetHealth - damage;
            float healthRegained;
            healthRegained = (0.02F + 0.02F * level) * (damage - getTargetHealth);
            if (attacker != null) {
                attacker.heal(healthRegained);
            }

            //float damageDealt = (target.getMaxHealth() - (target.getHealth()));
            //float targetMaxHealth = target.getMaxHealth();
            //float healthRegained;
            //healthRegained = (0.02F + 0.02F * level) * damageDealt;
            //user.heal(healthRegained);
        }
    } //END LEECHING ENCHANTMENT

    /*@Inject(
            at = @At("HEAD"),
            method = "attackLivingEntity",
            cancellable = true)

    private void onAttacking(LivingEntity target, CallbackInfo ci) {
        LivingEntity user = attacker;

        ItemStack mainHandStack = equippedHand.get(0);

        int level = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack);

        if (EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack) >= 1 ) {

            float getInitHealth = target.getHealth();
            float getNewHealth = getInitHealth - mainHandStack.getDamage();
            float damageDealt = getInitHealth - getNewHealth;
            float healthRegained;
            healthRegained = (0.02F + 0.02F * level) * damageDealt;
            user.heal(healthRegained);

            //float damageDealt = (target.getMaxHealth() - (target.getHealth()));
            //float targetMaxHealth = target.getMaxHealth();
            //float healthRegained;
            //healthRegained = (0.02F + 0.02F * level) * damageDealt;
            //user.heal(healthRegained);
        }
    }*/

    /*@Inject(
            at = @At("HEAD"),
            method = "attackLivingEntity",
            cancellable = true)

    private void onAttacking(LivingEntity target, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) (Object) this;
        ItemStack mainHandStack = equippedHand.get(0);
        int level = EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack);

        if (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHandStack) >= 1) {
            //if (!(target instanceof LivingEntity)) return;
            float chance = user.getRandom().nextFloat();
            if (chance <= level * 0.05) {
                StatusEffectInstance stunned = new StatusEffectInstance(StatusEffectsRegistry.STUNNED, 60);
                StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 60);
                StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 5);
                target.addStatusEffect(stunned);
                target.addStatusEffect(nausea);
                target.addStatusEffect(slowness);
            }

            //float damageDealt = (target.getMaxHealth() - (target.getHealth()));
            //float targetMaxHealth = target.getMaxHealth();
            //float healthRegained;
            //healthRegained = (0.02F + 0.02F * level) * damageDealt;
            //user.heal(healthRegained);
        }
    }*/
}