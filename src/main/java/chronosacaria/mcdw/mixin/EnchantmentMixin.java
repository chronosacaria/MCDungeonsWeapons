package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwCustomWeaponBase;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow @Final public EnchantmentTarget type;

    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)

    private void mcdw$isAcceptablePlz(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if ((stack.getItem() instanceof McdwAxe || stack.getItem() instanceof McdwDoubleAxe)
                && mcdw$isEnchantment(Enchantments.FIRE_ASPECT)) {
            cir.setReturnValue(true);
        }
        if (stack.getItem() instanceof McdwCustomWeaponBase
                && this.type.equals(EnchantmentTarget.WEAPON)){
            cir.setReturnValue(true);
        }
        if (stack.isOf(DaggersID.DAGGER_SWIFT_STRIKER.getItem())
                && mcdw$isEnchantment(EnchantsRegistry.ECHO, EnchantsRegistry.AMBUSH)){
            cir.setReturnValue(true);
        }
    }

    private boolean mcdw$isEnchantment(Enchantment ...enchantments){
        return Arrays.stream(enchantments).toList().contains((Enchantment) (Object) this);
    }
}

