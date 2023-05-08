package chronosacaria.mcdw.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Unique
    private static Enchantment mixedInEnchantment;

    // Add extra entries from those which were rejected by Vanilla logic
    @WrapOperation(method = "getPossibleEntries", at = @At(value = "FIELD", target = "Lnet/minecraft/enchantment/Enchantment;type:Lnet/minecraft/enchantment/EnchantmentTarget;"))
    private static EnchantmentTarget mcdw$getEnchantmentContext(Enchantment enchantment, Operation<EnchantmentTarget> type) {
        mixedInEnchantment = enchantment;
        return type.call();
    }

    @WrapOperation(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"))
    private static boolean mcdw$getPossibleEntries(EnchantmentTarget enchantmentTarget, Item item, Operation<Boolean> returnValue, int power, ItemStack stack, boolean treasureAllowed) {

        boolean isBook = stack.isOf(Items.BOOK);
        return (!(mixedInEnchantment.isTreasure()
                && !treasureAllowed
                || !mixedInEnchantment.isAvailableForRandomSelection()
                || !mixedInEnchantment.isAcceptableItem(stack) // Custom logic, replacing `!enchantment.type.isAcceptableItem(item)`
                && !isBook));

    }
}
