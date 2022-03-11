package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    public EntityType<SummonedBeeEntity> s_bee =
            SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    public float mcdw$damageModifiers(float amount, DamageSource source) {
        if (!(source.getAttacker() instanceof LivingEntity attackingEntity))
            return amount;

        if (amount > 0) {

            if (attackingEntity instanceof TameableEntity petSource
                    && petSource.world instanceof ServerWorld serverWorld
                    && petSource.getOwner() instanceof PlayerEntity owner) {

                amount *= EnchantmentEffects.huntersPromiseDamage(owner, serverWorld);
            }
        }

        return amount;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void mcdw$onTick(CallbackInfo ci) {
        if ((Object) this instanceof LivingEntity livingEntity) {
            ItemStack mainHand = livingEntity.getMainHandStack();

            if (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHand) > 0
                    || mainHand.getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE).asItem()
                    || mainHand.getItem() == ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE).asItem()) {
                livingEntity.removeStatusEffect(StatusEffects.POISON);
            }
            if (EnchantmentHelper.getLevel(EnchantsRegistry.STUNNING, mainHand) > 0
                    || mainHand.getItem() == ItemsInit.axeItems.get(AxesID.AXE_HIGHLAND).asItem()) {
                livingEntity.removeStatusEffect(StatusEffects.NAUSEA);
                livingEntity.removeStatusEffect(StatusEffects.SLOWNESS);
            }
            if (EnchantmentHelper.getLevel(EnchantsRegistry.WEAKENING, mainHand) > 0
                    || mainHand.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_NAMELESS_BLADE).asItem()) {
                livingEntity.removeStatusEffect(StatusEffects.WEAKNESS);
            }
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void mcdw$onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity victim = (LivingEntity) (Object) this;

        if (source.getAttacker() instanceof LivingEntity attackingEntity) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.PROSPECTOR))
                EnchantmentEffects.applyProspector(attackingEntity, victim);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RUSHDOWN))
                EnchantmentEffects.applyRushdown(attackingEntity);
        }

        if (source.getAttacker() instanceof PlayerEntity attackingPlayer) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_SIPHON))
                EnchantmentEffects.applySoulSiphon(attackingPlayer);
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof LivingEntity user))
            return;

        LivingEntity target = (LivingEntity) (Object) this;

        if(target instanceof PlayerEntity) return;

        if (source.getSource() instanceof LivingEntity) {
            if (amount > 0) {
                ItemStack mainHandStack = user.getMainHandStack();
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SMITING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) > 0
                            && !(EnchantmentHelper.getLevel(Enchantments.SMITE, mainHandStack) > 0))) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack);
                        if (target.isUndead()) {
                            AOEHelper.causeSmitingAttack(user, target,
                                    3.0f * level, amount);
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;)V")
    private void swingHand(Hand hand, CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity attackingPlayer))
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BUZZY_BEE)) {
            ItemStack mainHandStack = attackingPlayer.getMainHandStack();
            ItemStack offHandStack = attackingPlayer.getOffHandStack();
            if (mainHandStack.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_BEESTINGER) && offHandStack.getItem() == ItemsInit.mcdwItems.get(ItemsID.ITEM_BEE_STINGER)) {
                offHandStack.decrement(1);
                SummonedBeeEntity summonedBeeEntity_1 = s_bee.create(attackingPlayer.world);
                if (summonedBeeEntity_1 != null) {
                    summonedBeeEntity_1.setSummoner(attackingPlayer);
                    summonedBeeEntity_1.refreshPositionAndAngles(attackingPlayer.getX(), attackingPlayer.getY() + 1, attackingPlayer.getZ(), 0, 0);
                    attackingPlayer.world.spawnEntity(summonedBeeEntity_1);
                }
            }
        }
    }

    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void applyDippingPoisonPotionConsumption(CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity user))
            return;

        ItemStack mainHandStack = null;

        ItemStack poisonTippedArrow = PotionUtil.setPotion(new ItemStack(Items.TIPPED_ARROW, 8), Potions.POISON);

        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.DIPPING_POISON)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.DIPPING_POISON, mainHandStack) > 0)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.DIPPING_POISON, mainHandStack);
                if (user instanceof PlayerEntity) {
                    if (level > 0) {
                        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(user.getOffHandStack());
                        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
                            ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(),
                                    user.getZ(),
                                    poisonTippedArrow);
                            user.world.spawnEntity(arrowDrop);
                        }
                    }
                }
            }
        }
    }
}