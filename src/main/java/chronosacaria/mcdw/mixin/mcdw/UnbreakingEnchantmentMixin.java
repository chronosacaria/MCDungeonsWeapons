package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.enums.SwordsID;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    public void mcdw$isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(SwordsID.SWORD_BROKEN_SAWBLADE.getItem()) || stack.isOf(SwordsID.SWORD_MECHANIZED_SAWBLADE.getItem()))
            cir.setReturnValue(false);
    }
}
