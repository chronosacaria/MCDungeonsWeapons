package chronosacaria.mcdw.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * This code is used with the permission of <a href = "https://github.com/ZsoltMolnarrr">Daedelus</a>. <br />
 * The original code is from SpellEngine and can be found <a href = "https://github.com/ZsoltMolnarrr/SpellEngine/blob/1.19.2/common/src/main/java/net/spell_engine/mixin/enchant/EnchantmentHelperMixin.java">here</a>.
 */

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getPossibleEntries", at = @At(value = "RETURN"), cancellable = true)
    private static void mcdw$permitInnateEnchantmentsToBeEnchantedButNotWithConflicts(int power, ItemStack stack, boolean treasureAllowed,
                                                                                      CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> list = cir.getReturnValue();
        for (NbtElement enchantmentNbt : stack.getEnchantments()) {
            NbtCompound nbtCompound = (NbtCompound) enchantmentNbt;
            Identifier identifier = EnchantmentHelper.getIdFromNbt(nbtCompound);
            Enchantment enchantmentOnStack = Registry.ENCHANTMENT.get(identifier);
            // If can find enchants, remove incompatible options from the list of randomly enchant-able enchantments
            if (enchantmentOnStack != null)
                list.removeIf(enchantmentLevelEntry -> !enchantmentLevelEntry.enchantment.canCombine(enchantmentOnStack));
        }
        cir.setReturnValue(list);

        // Or should this be used? \/\/
        var currentEntries = cir.getReturnValue();

        // This logic is mostly copied from EnchantmentHelper.getPossibleEntries
        Item item = stack.getItem();
        boolean isBook = stack.isOf(Items.BOOK);
        block0: for (Enchantment enchantment : Registry.ENCHANTMENT) {
            // Don't check things already added
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
                continue block0;
            }
        }
    }
}
