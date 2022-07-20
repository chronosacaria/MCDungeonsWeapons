package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.bases.McdwShortBow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BowItem.class)
public abstract class BowItemMixin{
    private int overcharge;

    private LivingEntity livingEntity;

    public void setLivingEntity(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void mcdw$onStoppedUsingBow(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci){
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT)){
            if (McdwEnchantmentHelper.hasEnchantment(stack, EnchantsRegistry.BONUS_SHOT)){
                int bonusShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.BONUS_SHOT, stack);
                float damageMultiplier = 0.03F + (bonusShotLevel * 0.07F);
                float arrowVelocity = RangedAttackHelper.getVanillaOrModdedBowArrowVelocity(stack, remainingUseTicks);
                if (arrowVelocity >= 0.1F){
                    ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier, arrowVelocity);
                }
            }
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.MULTI_SHOT)) {
            int multiShotLevel = McdwEnchantmentHelper.mcdwEnchantmentLevel(user, Enchantments.MULTISHOT);
            if (multiShotLevel > 0) {
                ArrowItem arrowitem = (ArrowItem) (stack.getItem() instanceof ArrowItem ? stack.getItem() : Items.ARROW);
                PersistentProjectileEntity persistentProjectileEntity = arrowitem.createArrow(world, stack, user);
                LivingEntity target = user.getAttacking();
                if (!(target == null)) { // \/\/ Taken from AbstractSkeletonEntity
                    double d = target.getX() - user.getX();
                    double e = target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY();
                    double f = target.getZ() - user.getZ();
                    double g = MathHelper.sqrt((float) (d * d + f * f));
                    persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224D, f, 1.6F, (float) (14 - user.world.getDifficulty().getId() * 4));
                    persistentProjectileEntity.pickupType =
                            PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    world.spawnEntity(persistentProjectileEntity);
                }
            }
        }
    }

    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile" +
            "/PersistentProjectileEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void mcdw$applyBowEnchantmentLevel(ItemStack stack, World world, LivingEntity user, int remainingUseTicks,
                                               CallbackInfo ci, PlayerEntity playerEntity, boolean bl, ItemStack itemStack, int i, float f, boolean bl2,
                                               ArrowItem arrowItem, PersistentProjectileEntity ppe){

        // Not the level of Overcharge
        if (overcharge > 0) {
            ((IMcdwEnchantedArrow)ppe).setOvercharge(overcharge);
        }

        int chainReactionLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHAIN_REACTION, stack);
        if (chainReactionLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setChainReactionLevel(chainReactionLevel);
        }
        int chargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, stack);
        if (chargeLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setChargeLevel(chargeLevel);
        }
        int cobwebShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, stack);
        if (cobwebShotLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setCobwebShotLevel(cobwebShotLevel);
        }
        int dynamoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.DYNAMO, stack);
        if (dynamoLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setDynamoLevel(dynamoLevel);
        }
        int enigmaResonatorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, stack);
        if (enigmaResonatorLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setEnigmaResonatorLevel(enigmaResonatorLevel);
        }
        int fuseShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, stack);
        if (fuseShotLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setFuseShotLevel(fuseShotLevel);
        }
        int gravityLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, stack);
        if (gravityLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setGravityLevel(gravityLevel);
        }
        int growingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, stack);
        if (growingLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setGrowingLevel(growingLevel);
        }
        int levitationShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.LEVITATION_SHOT, stack);
        if (levitationShotLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setLevitationShotLevel(levitationShotLevel);
        }
        int phantomsMarkLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PHANTOMS_MARK, stack);
        if (phantomsMarkLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setPhantomsMarkLevel(phantomsMarkLevel);
        }
        int poisonCloudLevel = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, stack);
        if (poisonCloudLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setPoisonCloudLevel(poisonCloudLevel);
        }
        int radianceLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, stack);
        if (radianceLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setRadianceLevel(radianceLevel);
        }
        int replenishLevel = EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, stack);
        if (replenishLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setReplenishLevel(replenishLevel);
        }
        int ricochetLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, stack);
        if (ricochetLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setRicochetLevel(ricochetLevel);
        }
        int tempoTheftLevel = EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, stack);
        if (tempoTheftLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setTempoTheftLevel(tempoTheftLevel);
        }
        int voidShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, stack);
        if (voidShotLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setVoidShotLevel(voidShotLevel);
        }
        int wildRageLevel = EnchantmentHelper.getLevel(EnchantsRegistry.WILD_RAGE, stack);
        if (wildRageLevel > 0) {
            ((IMcdwEnchantedArrow)ppe).setWildRageLevel(wildRageLevel);
        }
    }

    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getMaxUseTime(Lnet/minecraft/item/ItemStack;)I"))
    private void mcdw$livingEntityGetter(ItemStack stack, World world, LivingEntity user,
                                         int remainingUseTicks, CallbackInfo ci){
        this.setLivingEntity(user);
    }

    @ModifyArg(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"), index = 0)
    private int mcdw$acceleratedPullProgress(int value){
        ItemStack bowStack = livingEntity.getActiveItem();

        if (bowStack.getItem() instanceof McdwShortBow mcdwShortBow) {
            value /= mcdwShortBow.getDrawSpeed() / 20;
        } else if (bowStack.getItem() instanceof McdwLongBow mcdwLongBow) {
            value /= mcdwLongBow.getDrawSpeed() / 20;
        } else if (bowStack.getItem() instanceof McdwBow mcdwBow) {
            value /= mcdwBow.getDrawSpeed() / 20;
        }

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
            int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, bowStack);
            if (accelerateLevel > 0) {
                StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;
                value = (int) (value * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));

                if (BowItem.getPullProgress(value) >= 1) {
                    StatusEffectInstance accelerateUpdateInstance =
                            new StatusEffectInstance(StatusEffectsRegistry.ACCELERATE, 60, consecutiveShots, false, false, true);
                    livingEntity.addStatusEffect(accelerateUpdateInstance);
                }
            }
        }

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)) {
            int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, bowStack);
            if (overchargeLevel > 0) {
                overcharge = Math.min((value / 20) - 1, overchargeLevel);
                value = overcharge == overchargeLevel ? value : value % 20;
            }
        }
        return value;
    }

    @ModifyArgs(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V"))
    private void mcdw$rangeHandler(Args args) {
        float velocity = args.get(4);
        ItemStack bowStack = livingEntity.getActiveItem();

        if (bowStack.getItem() instanceof McdwShortBow mcdwShortBow) {
            velocity *= mcdwShortBow.getRange() / 15f;
        } else if (bowStack.getItem() instanceof McdwLongBow mcdwLongBow) {
            velocity *= mcdwLongBow.getRange() / 15f;
        } else if (bowStack.getItem() instanceof McdwBow mcdwBow) {
            velocity *= mcdwBow.getRange() / 15f;
        }

        args.set(4, velocity);
    }
}