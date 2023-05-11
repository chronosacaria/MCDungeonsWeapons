package chronosacaria.mcdw.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    // This mixin is to fix Vanilla Enchantment table not checking `isAcceptableItem` function
    @Inject(method = "getPossibleEntries", at = @At("RETURN"))
    private static void getPossibleEntries_RETURN_SpellEngine(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        var currentEntries = cir.getReturnValue();

        // 1. REMOVING ENCHANT ENTRIES ADDED INCORRECTLY

        var toRemove = new ArrayList<EnchantmentLevelEntry>();
        for (var entry: currentEntries) {
            if (!entry.enchantment.isAcceptableItem(stack)) {
                toRemove.add(entry);
            }
        }
        currentEntries.removeAll(toRemove);

        // 2. ADDING ENCHANT ENTRIES LEFT OUT INITIALLY

        // This logic is mostly copied from EnchantmentHelper.getPossibleEntries
        boolean isBook = stack.isOf(Items.BOOK);
        for (Enchantment enchantment : Registry.ENCHANTMENT) {
            // Don't check entries already added
            boolean alreadyAdded = currentEntries.stream().anyMatch(entry -> entry.enchantment.equals(enchantment));
            if (alreadyAdded) { continue; }

            if (enchantment.isTreasure()
                    && !treasureAllowed
                    || !enchantment.isAvailableForRandomSelection()
                    || !enchantment.isAcceptableItem(stack) // Custom logic, replacing `!enchantment.type.isAcceptableItem(item)`
                    && !isBook) continue;
            for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) continue;
                currentEntries.add(new EnchantmentLevelEntry(enchantment, i));
            }
        }
    }
}