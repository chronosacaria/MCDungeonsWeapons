package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwCustomWeaponBase;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow protected abstract String getOrCreateTranslationKey();

    @Shadow @Final public EnchantmentTarget type;

    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)

    private void mcdw$isAcceptablePlz(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if (stack.getItem() instanceof McdwAxe && mcdw$isEnchantment(Enchantments.FIRE_ASPECT)) {
            cir.setReturnValue(true);
        }
        if (stack.getItem() instanceof McdwCustomWeaponBase
                && this.type.equals(EnchantmentTarget.WEAPON)){
            cir.setReturnValue(true);
        }
        if (stack.isOf(ItemsInit.DAGGER_ITEMS.get(DaggersID.DAGGER_SWIFT_STRIKER))
                && mcdw$isEnchantment(EnchantsRegistry.ECHO, EnchantsRegistry.AMBUSH)){
            cir.setReturnValue(true);
        }
    }

    private boolean mcdw$isEnchantment(Enchantment ...enchantments){
        for (Enchantment enchantment : enchantments){
            if (Util.createTranslationKey("enchantment", Registry.ENCHANTMENT.getId(enchantment)).equals(this.getOrCreateTranslationKey())){
                return true;
            }
        }
        return false;
    }
}

