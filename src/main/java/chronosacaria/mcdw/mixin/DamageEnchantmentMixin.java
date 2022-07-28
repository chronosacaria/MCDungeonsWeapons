package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.bases.McdwSpear;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    public void mcdw$enableDamageEnchantmentsForSpears(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if (stack.getItem() instanceof McdwSpear){
            cir.setReturnValue(true);
        }
    }
}
