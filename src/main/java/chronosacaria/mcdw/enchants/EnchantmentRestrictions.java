package chronosacaria.mcdw.enchants;

import chronosacaria.mcdw.api.EnchantmentRestriction;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.bases.McdwSword;
import net.minecraft.enchantment.Enchantments;

public class EnchantmentRestrictions {
    public static void register() {
        EnchantmentRestriction.permit(Enchantments.SHARPNESS, itemStack -> {
            return itemStack.getItem() instanceof McdwSpear;
        });
        EnchantmentRestriction.prohibit(Enchantments.SHARPNESS, itemStack -> {
            return itemStack.getItem() instanceof McdwSword;
        });
    }
}
