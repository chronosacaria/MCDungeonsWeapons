package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IMcdwEnchantedArrow;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.CrossbowsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.items.ItemsInit;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    private LivingEntity livingEntity;

    public void mcdw$setLivingEntity(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @Inject(method = "createArrow", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void mcdw$applyCrossbowEnchantmentLevel(World world, LivingEntity user, ItemStack crossbow, ItemStack arrow,
                                    CallbackInfoReturnable<PersistentProjectileEntity> cir, ArrowItem arrowItem,
                                    PersistentProjectileEntity ppe){
        if (CrossbowItem.isCharged(crossbow)) {

            int chainReactionLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHAIN_REACTION, crossbow);
            if (chainReactionLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setChainReactionLevel(chainReactionLevel);
            }
            int chargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, crossbow);
            if (chargeLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setChargeLevel(chargeLevel);
            }
            int cobwebShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, crossbow);
            if (cobwebShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setCobwebShotLevel(cobwebShotLevel);
            }
            int dynamoLevel = EnchantmentHelper.getLevel(EnchantsRegistry.DYNAMO, crossbow);
            if (dynamoLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setDynamoLevel(dynamoLevel);
            }
            int enigmaResonatorLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, crossbow);
            if (enigmaResonatorLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setEnigmaResonatorLevel(enigmaResonatorLevel);
            }
            int fuseShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, crossbow);
            if (fuseShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setFuseShotLevel(fuseShotLevel);
            }
            int gravityLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, crossbow);
            if (gravityLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setGravityLevel(gravityLevel);
            }
            int growingLevel = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, crossbow);
            if (growingLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setGrowingLevel(growingLevel);
            }
            int levitationShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.LEVITATION_SHOT, crossbow);
            if (levitationShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setLevitationShotLevel(levitationShotLevel);
            }
            // For Drag in Water for Nautilus Crossbow
            ((IMcdwEnchantedArrow)ppe).setNautilusBoolean(crossbow.isOf(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)));
            int phantomsMarkLevel = EnchantmentHelper.getLevel(EnchantsRegistry.PHANTOMS_MARK, crossbow);
            if (phantomsMarkLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setPhantomsMarkLevel(phantomsMarkLevel);
            }
            int poisonCloudLevel = EnchantmentHelper.getLevel(EnchantsRegistry.POISON_CLOUD, crossbow);
            if (poisonCloudLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setPoisonCloudLevel(poisonCloudLevel);
            }
            int radianceLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RADIANCE, crossbow);
            if (radianceLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setRadianceLevel(radianceLevel);
            }
            int replenishLevel = EnchantmentHelper.getLevel(EnchantsRegistry.REPLENISH, crossbow);
            if (replenishLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setReplenishLevel(replenishLevel);
            }
            int ricochetLevel = EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, crossbow);
            if (ricochetLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setRicochetLevel(ricochetLevel);
            }
            ((IMcdwEnchantedArrow)ppe).setShadowBarbBoolean(crossbow.isOf(ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW)));
            int shadowShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SHADOW_SHOT, crossbow);
            if (shadowShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setShadowShotLevel(shadowShotLevel);
            }
            int tempoTheftLevel = EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, crossbow);
            if (tempoTheftLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setTempoTheftLevel(tempoTheftLevel);
            }
            int voidShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, crossbow);
            if (voidShotLevel > 0) {
                ((IMcdwEnchantedArrow)ppe).setVoidShotLevel(voidShotLevel);
            }
            int wildRageLevel = EnchantmentHelper.getLevel(EnchantsRegistry.WILD_RAGE, crossbow);
            if (wildRageLevel > 0){
                ((IMcdwEnchantedArrow)ppe).setWildRageLevel(wildRageLevel);
            }

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT)) {
                int bonusShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.BONUS_SHOT, crossbow);
                if (bonusShotLevel > 0) {
                    float damageMultiplier = 0.1F + ((bonusShotLevel - 1) * 0.07F);

                    float arrowVelocity = RangedAttackHelper.getVanillaOrModdedCrossbowArrowVelocity(crossbow);
                    ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier
                    );
                }
            }
        }
    }
    
    @Inject(method = "onStoppedUsing", at = @At(value = "HEAD"))
    private void mcdw$livingEntityGetter(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        this.mcdw$setLivingEntity(user);
    }

    @ModifyArg(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;getPullProgress(ILnet/minecraft/item/ItemStack;)F"), index = 0)
    private int mcdw$acceleratedPullProgress(int useTicks) {
        ItemStack crossbowStack = livingEntity.getActiveItem();

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
            int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, crossbowStack);
            if (accelerateLevel > 0) {
                StatusEffectInstance accelerateInstance = livingEntity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));

                if ((float)useTicks / (float)CrossbowItem.getPullTime(crossbowStack) >= 1) {
                    StatusEffectInstance accelerateUpdateInstance =
                            new StatusEffectInstance(StatusEffectsRegistry.ACCELERATE, 60, consecutiveShots, false, false, true);
                    livingEntity.addStatusEffect(accelerateUpdateInstance);
                }
            }
        }
        return useTicks;
    }

    @ModifyArgs(method = "shootAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;shoot(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;FZFFF)V"))
    private static void mcdw$crossbowRangeHandler(Args args) {
        float velocity = args.get(7);
        ItemStack crossbowStack = args.get(3);
        if (crossbowStack.getItem() instanceof McdwCrossbow mcdwCrossbow) {
            velocity *= mcdwCrossbow.getRange() / 8f;
        }

        args.set(7, velocity);
    }

    @Inject(method = "getPullTime", at = @At(value = "RETURN"), cancellable = true)
    private static void mcdw$crossbowPullTimeHandler(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        int pullTime = cir.getReturnValue();
        if (stack.getItem() instanceof McdwCrossbow mcdwCrossbow) {
            pullTime += mcdwCrossbow.getDrawSpeed() - 25;
        }
        cir.setReturnValue(Math.max(0, pullTime));
    }
}
