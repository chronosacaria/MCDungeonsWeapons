package chronosacaria.mcdw.registries;

import chronosacaria.mcdw.api.util.EnchantmentRestriction;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.bases.McdwSpear;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantments;

public class EnchantmentRestrictionsRegistry {
    public static void init() {
        EnchantmentRestriction.permit(Enchantments.FIRE_ASPECT, itemStack -> itemStack.getItem() instanceof McdwAxe || itemStack.getItem() instanceof McdwDoubleAxe);
        EnchantmentRestriction.permitTarget((enchantment, itemStack) -> enchantment instanceof DamageEnchantment && itemStack.getItem() instanceof McdwSpear);
    }
}