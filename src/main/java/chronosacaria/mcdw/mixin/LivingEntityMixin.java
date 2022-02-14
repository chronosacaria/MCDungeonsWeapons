package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enchants.summons.entity.SummonedBeeEntity;
import chronosacaria.mcdw.enchants.summons.registry.SummonedEntityRegistry;
import chronosacaria.mcdw.enums.*;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    public EntityType<SummonedBeeEntity> s_bee =
            SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    /* * * * * * * * * * * |
    |****STATUS REMOVAL****|
    | * * * * * * * * * * */
    @Inject(at = @At("HEAD"), method = "tick")
    private void mcdw$onTick(CallbackInfo ci) {
        if ((Object) this instanceof LivingEntity livingEntity) {
            ItemStack mainHand = livingEntity.getMainHandStack();


            if (EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, mainHand) > 0
                    || mainHand.getItem() == ItemsInit.sickleItems.get(SicklesID.SICKLE_NIGHTMARES_BITE).asItem()
                    || mainHand.getItem() == ItemsInit.glaiveItems.get(GlaivesID.SPEAR_VENOM_GLAIVE).asItem())
            {
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

    /* * * * * * * * * * * * * * * * *|
    |****HUNTER'S COMPANION EFFECT****|
    |* * * * * * * * * * * * * * * * */

    @Inject(method = "applyDamage", at = @At("HEAD"))
    public void onHuntersPromiseCompanionDamage(DamageSource source, float amount, CallbackInfo into){
        LivingEntity target = (LivingEntity) (Object) this;
        if (!(source.getSource() instanceof TameableEntity petSource))
            return;

        if (petSource == null)
            return;

        if (!(petSource.world instanceof ServerWorld serverWorld))
            return;
        if (!(petSource.getOwner() instanceof PlayerEntity owner))
            return;
        if (owner == null)
            return;
        UUID petOwnerUUID = owner.getUuid();
        ItemStack mainHandStack = owner.getMainHandStack();

        if (mainHandStack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_HUNTERS_PROMISE).asItem()){
            if (petOwnerUUID != null){
                Entity petOwner = serverWorld.getEntity(petOwnerUUID);
                if (petOwner instanceof LivingEntity){
                    float huntersPromiseCompanionFactor = 1.5f;
                    float newDamage = amount * huntersPromiseCompanionFactor;
                    float h = target.getHealth();
                    target.setHealth(h - newDamage);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onProspectorEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity attackingPlayer)) return;
        LivingEntity target = (LivingEntity) (Object) this;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.PROSPECTOR)) {
            int prospectorLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(attackingPlayer, EnchantsRegistry.SOUL_SIPHON);
            if (prospectorLevel > 0) {
                if (CleanlinessHelper.percentToOccur(5 * prospectorLevel)) {
                    if (target instanceof Monster){
                        ItemEntity emeraldDrop = new ItemEntity(target.world, target.getX(), target.getY(), target.getZ(),
                                new ItemStack(Items.EMERALD, 1));
                        attackingPlayer.world.spawnEntity(emeraldDrop);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onRushdownEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity attackingPlayer))
            return;
        if (attackingPlayer == null)
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RUSHDOWN)) {

            int rushdownLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(attackingPlayer, EnchantsRegistry.RUSHDOWN);
            if (rushdownLevel > 0) {

                if (CleanlinessHelper.percentToOccur(10)) {
                    StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, 100 * rushdownLevel, 2,
                            false, false);
                    attackingPlayer.addStatusEffect(rushdown);
                }
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if(target instanceof PlayerEntity) return;

        if (source.getSource() instanceof LivingEntity) {
            if (amount > 0) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SMITING)) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) >= 1 && !(EnchantmentHelper.getLevel(Enchantments.SMITE, mainHandStack) >= 1))) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack);
                        if (target.isUndead()) {
                            AOEHelper.causeSmitingAttack(
                                    (PlayerEntity) user,
                                    target,
                                    3.0f * level,
                                    amount);
                        }
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onSoulSiphonEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity attackingPlayer))
            return;
        if (attackingPlayer == null)
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_SIPHON)) {

            int soulLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(attackingPlayer, EnchantsRegistry.SOUL_SIPHON);
            if (soulLevel > 0) {

                if (CleanlinessHelper.percentToOccur(10)) {
                    attackingPlayer.addExperience(3 * soulLevel);
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
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_DEVOURER)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, mainHandStack) > 0)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, mainHandStack);
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