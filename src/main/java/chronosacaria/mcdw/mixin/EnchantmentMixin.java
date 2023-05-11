package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.api.util.EnchantmentRestriction;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    public void mcdw$isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        var enchantment = (Enchantment)((Object)this);

        if (EnchantmentRestriction.isProhibited(enchantment, stack)) {
            cir.setReturnValue(false);
            cir.cancel();
        }

        if (EnchantmentRestriction.isPermitted(enchantment, stack)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}

