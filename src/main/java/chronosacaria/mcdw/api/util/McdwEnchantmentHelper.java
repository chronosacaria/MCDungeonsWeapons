package chronosacaria.mcdw.api.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class McdwEnchantmentHelper {
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    public static int mcdwEnchantmentLevel(LivingEntity livingEntity, Enchantment enchantment) {
        //ItemStack mainHandStack = livingEntity.getMainHandStack();
        ItemStack mainHandStack;
        ItemStack offhandStack;
        //if (livingEntity instanceof PlayerEntity)
        //    mainHandStack = livingEntity.getStackInHand(livingEntity.preferredHand);
        //else
        mainHandStack = livingEntity.getMainHandStack();
        offhandStack = livingEntity.getOffHandStack();
        // Instead of always mainhand, check for which hand was used, and apply that below
        if (mainHandStack != null)
            return EnchantmentHelper.getLevel(enchantment, mainHandStack);
        if (offhandStack != null)
            return EnchantmentHelper.getLevel(enchantment, offhandStack);
        return 0;

    }

}