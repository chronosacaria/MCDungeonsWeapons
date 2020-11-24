package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enchants.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.items.ItemRegistry;
import chronosacaria.mcdw.statuseffects.ShieldingStatusEffect;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import chronosacaria.mcdw.weapons.*;
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
import net.minecraft.item.Item;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    private void onCommittedDamage(DamageSource source, float damage, CallbackInfoReturnable<Boolean> ci) {
        LivingEntity attacker = (LivingEntity)source.getSource();
        LivingEntity victim = (LivingEntity)source.getSource();
        if (!(victim.getMaxHealth() < victim.getMaxHealth())) return;
        ItemStack mainhand = attacker.getMainHandStack();
        boolean uniqueWeaponFlag =
                mainhand.getItem() == SoulDaggers.SWORD_TRUTHSEEKER || mainhand.getItem() == Staves.STAFF_GROWING_STAFF;
        if ((McdwEnchantmentHelper.hasEnchantment(mainhand, EnchantsRegistry.COMMITTED)) || uniqueWeaponFlag){
            int committedLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, mainhand);
            float victimRemainingHealth = victim.getHealth() / victim.getMaxHealth();
            float originalDamage = damage;
            float extraDamageMultiplier;
            extraDamageMultiplier = 0.25F + committedLevel * 0.25F;
            float extraDamage = (originalDamage * (1 - victimRemainingHealth)) * extraDamageMultiplier;
            victim.damage(DamageSource.player(attackingPlayer), (originalDamage + extraDamage));
        }
    } //END LEECHING ENCHANTMENT*/
}