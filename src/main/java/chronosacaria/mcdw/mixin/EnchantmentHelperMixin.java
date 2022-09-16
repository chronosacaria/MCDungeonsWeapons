package chronosacaria.mcdw.mixin;

import com.google.gson.JsonSyntaxException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getPossibleEntries", at = @At(value = "RETURN"), cancellable = true)
    private static void mcdw$permitInnateEnchantmentsToBeEnchantedButNotWithConflicts(int power, ItemStack stack, boolean treasureAllowed,
                                                                                      CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> list = cir.getReturnValue();
        for (NbtElement enchantmentNbt : stack.getEnchantments()) {
            NbtCompound nbtCompound = (NbtCompound) enchantmentNbt;
            Identifier identifier = EnchantmentHelper.getIdFromNbt(nbtCompound);
            Enchantment enchantmentOnStack = Registry.ENCHANTMENT.getOrEmpty(identifier).orElseThrow(() -> {
                throw new JsonSyntaxException("Unknown enchantment '" + identifier + "'");
            });
            list.removeIf(enchantmentLevelEntry -> !enchantmentLevelEntry.enchantment.canCombine(enchantmentOnStack));
        }
        cir.setReturnValue(list);
    }
}
