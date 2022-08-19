package chronosacaria.mcdw.mixin.client;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.statuseffects.StatusEffectsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ACCELERATE)) {
                int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, stack);
                if (accelerateLevel > 0) {
                    StatusEffectInstance accelerateInstance = entity.getStatusEffect(StatusEffectsRegistry.ACCELERATE);
                    int consecutiveShots = accelerateInstance != null ? accelerateInstance.getAmplifier() + 1 : 0;
                    useTicks = (int) (useTicks * (1f + (MathHelper.clamp(consecutiveShots * (6.0f + 2.0f * accelerateLevel), 0f, 100f) / 100f)));
                }
            }
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.OVERCHARGE)) {
                int overchargeLevel = EnchantmentHelper.getLevel(EnchantsRegistry.OVERCHARGE, stack);
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
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ACCELERATE)) {
                int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, stack);
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
