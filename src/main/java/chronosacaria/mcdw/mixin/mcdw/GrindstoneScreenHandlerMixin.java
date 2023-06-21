package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {
    @ModifyArgs(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;set(Ljava/util/Map;Lnet/minecraft/item/ItemStack;)V"))
    private void mcdw$reapplyInnateEnchantmentOnGrind(Args args) {
        Map<Enchantment, Integer> map = args.get(0);
        if (args.get(1) instanceof ItemStack itemStack && itemStack.getItem() instanceof IInnateEnchantment) {
            Map<Enchantment, Integer> innateMap = ((IInnateEnchantment) itemStack.getItem()).getInnateEnchantments();
            if (innateMap == null) return;
            for (Enchantment enchantment : innateMap.keySet())
                map.put(enchantment, innateMap.get(enchantment));
            args.set(0, map);
        }
    }
}
