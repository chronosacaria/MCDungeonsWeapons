package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

@Mixin(net.minecraft.screen.CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {
    @ModifyVariable(method = "updateResult", at = @At(value = "STORE"), ordinal = 1)
    private static ItemStack innateItemStack(ItemStack itemStack) {
        if (itemStack.getItem() instanceof IInnateEnchantment innateEnchantedItem) {
            Map<Enchantment, Integer> map = innateEnchantedItem.getInnateEnchantments();
            for (Enchantment enchantment : map.keySet())
                itemStack.addEnchantment(enchantment, map.get(enchantment));
        }
        return itemStack;
    }
}
