/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin.old_mixins.mcdw.client;

/*
@SuppressWarnings("InjectCouldBeOverwrite")
@Environment(EnvType.CLIENT)
@Mixin(ModelPredicateProviderRegistry.class)
public class ModelPredicateProviderRegistryMixin {

    // This Inject allows for the visuals of Accelerate and Overcharge to work on Vanilla Bows.
    // This @link ModelPredicateProviderRegistry#method_27890 is the Bow "pull" predicate
    @Inject(method = "method_27890(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)F", at = @At("HEAD"), cancellable = true)
    private static void mcdw$blackMagicFuckery(ItemStack stack, ClientWorld world, LivingEntity entity, int seed, CallbackInfoReturnable<Float> cir){
        cir.cancel();
        if (entity == null) {
            cir.setReturnValue(0.0F);
        } else {
            int useTicks = stack.getMaxUseTime() - entity.getItemUseTimeLeft();
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ACCELERATE).mcdw$getIsEnabled()) {
                int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ACCELERATE), stack);
                if (accelerateLevel > 0) {
                    StatusEffectInstance accelerateInstance = entity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                    int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;
                    useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                }
            }
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.OVERCHARGE).mcdw$getIsEnabled()) {
                int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.OVERCHARGE), stack);
                if (overchargeLevel > 0) {
                    int overcharge = Math.min((useTicks / 20) - 1, overchargeLevel);
                    useTicks = overcharge == overchargeLevel ? useTicks : (useTicks % 20);
                }
            }
            cir.setReturnValue(entity.getActiveItem() != stack ? 0.0F :
                    (float)(useTicks) / 20);
        }
    }

    // This Inject allows for the visuals of Accelerate to work on Vanilla Crossbows.
    // This @link ModelPredicateProviderRegistry#method_27888 is the Crossbow "pull" predicate
    @Inject(method = "method_27888(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)F", at = @At("HEAD"), cancellable = true)
    private static void mcdw$blackMagicFuckeryII(ItemStack stack, ClientWorld world, LivingEntity entity, int seed, CallbackInfoReturnable<Float> cir){
        cir.cancel();
        if (entity == null) {
            cir.setReturnValue(0.0F);
        } else {
            int useTicks = stack.getMaxUseTime() - entity.getItemUseTimeLeft();
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ACCELERATE).mcdw$getIsEnabled()) {
                int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.enchantments.get(EnchantmentsID.ACCELERATE), stack);
                if (accelerateLevel > 0) {
                    StatusEffectInstance accelerateInstance = entity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                    int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;

                    useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                }
            }
            cir.setReturnValue(CrossbowItem.isCharged(stack) ? 0.0F :
                    (float) (useTicks) / CrossbowItem.getPullTime(stack));
        }
    }
}


 */