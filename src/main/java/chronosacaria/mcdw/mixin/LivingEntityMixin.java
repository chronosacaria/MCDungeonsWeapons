package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.damagesource.OffHandDamageSource;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.ItemsID;
import chronosacaria.mcdw.enums.SettingsID;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    public final EntityType<SummonedBeeEntity> mcdw$summoned_bee =
            SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    public float mcdw$damageModifiers(float amount, DamageSource source) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return amount;

        if (amount > 0) {
            float storedAmount = amount * Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enchantmentStrength;
            if (attackingEntity instanceof TameableEntity petSource
                    && petSource.world instanceof ServerWorld serverWorld
                    && petSource.getOwner() instanceof PlayerEntity owner) {

                amount += storedAmount * EnchantmentEffects.huntersPromiseDamage(owner, serverWorld);
            }
        }

        return amount;
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void mcdw$onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity victim = (LivingEntity) (Object) this;
        boolean isOffHandAttack = source instanceof OffHandDamageSource;

        if (source.getAttacker() instanceof LivingEntity attackingEntity) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.PROSPECTOR))
                EnchantmentEffects.applyProspector(attackingEntity, victim, isOffHandAttack);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.RUSHDOWN))
                EnchantmentEffects.applyRushdown(attackingEntity, isOffHandAttack);
        }

        if (source.getAttacker() instanceof PlayerEntity attackingPlayer) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SOUL_SIPHON))
                EnchantmentEffects.applySoulSiphon(attackingPlayer, isOffHandAttack);
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdw$applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof LivingEntity user))
            return;

        LivingEntity target = (LivingEntity) (Object) this;

        if(target instanceof PlayerEntity) return;

        if (source.getSource() instanceof LivingEntity) {
            if (amount > 0) {
                ItemStack mainHandStack = user.getMainHandStack();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SMITING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) > 0
                            && !(EnchantmentHelper.getLevel(Enchantments.SMITE, mainHandStack) > 0))) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack);
                        if (target.isUndead()) {
                            EnchantmentEffects.causeSmitingAttack(user, target,
                                    3.0f * level, amount);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdw$applySmitingEnchantmentDamageFromOffHand(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof LivingEntity user))
            return;

        LivingEntity target = (LivingEntity) (Object) this;

        if(target instanceof PlayerEntity) return;

        if (source.getSource() instanceof LivingEntity) {
            if (amount > 0) {
                ItemStack offHandStack = user.getOffHandStack();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SMITING)) {

                    if (offHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, offHandStack) > 0
                            && !(EnchantmentHelper.getLevel(Enchantments.SMITE, offHandStack) > 0))) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, offHandStack);
                        if (target.isUndead()) {
                            EnchantmentEffects.causeSmitingAttack(user, target,
                                    3.0f * level, amount);
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;)V")
    private void mcdw$swingHand(Hand hand, CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity attackingPlayer))
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.BUZZY_BEE)) {
            ItemStack mainHandStack = attackingPlayer.getMainHandStack();
            ItemStack offHandStack = attackingPlayer.getOffHandStack();
            if (mainHandStack.getItem() == ItemsInit.SWORD_ITEMS.get(SwordsID.SWORD_BEESTINGER) && offHandStack.getItem() == ItemsInit.MCDW_ITEMS.get(ItemsID.ITEM_BEE_STINGER)) {
                offHandStack.decrement(1);
                SummonedBeeEntity summonedBeeEntity_1 = mcdw$summoned_bee.create(attackingPlayer.world);
                if (summonedBeeEntity_1 != null) {
                    summonedBeeEntity_1.setSummoner(attackingPlayer);
                    summonedBeeEntity_1.refreshPositionAndAngles(attackingPlayer.getX(), attackingPlayer.getY() + 1, attackingPlayer.getZ(), 0, 0);
                    attackingPlayer.world.spawnEntity(summonedBeeEntity_1);
                }
            }
        }
    }

    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void mcdw$applyDippingPoisonPotionConsumption(CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity user))
            return;

        ItemStack poisonTippedArrow = PotionUtil.setPotion(new ItemStack(Items.TIPPED_ARROW, 8), Potions.POISON);

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.DIPPING_POISON)) {
            if (user.getOffHandStack() != null && (EnchantmentHelper.getLevel(EnchantsRegistry.DIPPING_POISON, user.getOffHandStack()) > 0)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.DIPPING_POISON, user.getOffHandStack());
                if (level > 0) {
                    List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(user.getMainHandStack());
                    if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
                        CleanlinessHelper.mcdw$dropItem(user, poisonTippedArrow);
                    }
                }

            }
        }
    }

    @Inject(method = "jump", at = @At("HEAD"))
    public void mcdw$onJumpEffects(CallbackInfo ci){
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;

        if (playerEntity != null) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.BURST_BOWSTRING))
                EnchantmentEffects.activateBurstBowstringOnJump(playerEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.DYNAMO))
                EnchantmentEffects.handleAddDynamoEffect(playerEntity);
        }
    }

    @Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V"))
    public void overwhammer(DamageSource source, float amount, CallbackInfo ci) {
        if (source.getSource() instanceof PlayerEntity player) {
            int sharedPainLevel = EnchantmentEffects.mcdw$getEnchantmentLevel(EnchantsRegistry.SHARED_PAIN, player, false);
            if (sharedPainLevel <= 0) return;
            if ((Object) this instanceof LivingEntity target) {
                float targetHealth = target.getHealth() - amount;
                if (targetHealth < 0) {
                    float overkillDamage = Math.abs(targetHealth);
                    List<LivingEntity> nearbyEntities = AOEHelper.getAoeTargets(target, target, 6);
                    if (nearbyEntities.size() == 0) {
                        if (Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.ENABLE_ENCHANTMENT_SETTINGS.get(SettingsID.SHARED_PAIN_CAN_DAMAGE_USER)) {
                            player.damage(DamageSource.MAGIC, overkillDamage);
                        }
                    } else {
                            nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(target)));
                            nearbyEntities.get(0).damage(DamageSource.MAGIC, overkillDamage);
                    }
                }
            }
        }
    }
}
