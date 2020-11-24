package chronosacaria.mcdw.enchants.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class McdwEnchantmentHelper {
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    public static boolean hasEnchantment(LivingEntity entity, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getEquipmentLevel(enchantment, entity) > 0;
    }
}
