package chronosacaria.mcdw.api.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class McdwEnchantmentHelper {
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    public static int mcdwEnchantmentLevel(LivingEntity livingEntity, Enchantment enchantment) {
        //ItemStack mainHandStack = livingEntity.getMainHandStack();
        ItemStack handStack;
        //if (livingEntity instanceof PlayerEntity)
        //    handStack = livingEntity.getStackInHand(livingEntity.preferredHand);
        //else
            handStack = livingEntity.getMainHandStack();
        // Instead of always mainhand, check for which hand was used, and apply that below
        if (handStack != null)
            return EnchantmentHelper.getLevel(enchantment, handStack);
        return 0;
    }

}