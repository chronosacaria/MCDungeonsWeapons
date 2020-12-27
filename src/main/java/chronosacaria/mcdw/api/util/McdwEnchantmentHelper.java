package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.enchants.lists.RangedEnchantmentList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;



import java.util.List;

public class McdwEnchantmentHelper {
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    public static boolean hasEnchantment(LivingEntity entity, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getEquipmentLevel(enchantment, entity) > 0;
    }

    public static boolean shooterIsLiving(ArrowEntity arrowEntity){
        return arrowEntity.collides();
    }

    public static int enchantmentTagToLevel(PersistentProjectileEntity arrowEntity, Enchantment enchantment){
        String enchantmentAsString = RangedEnchantmentList.rangedEnchantmentToStringMap.get(enchantment);
        for(int i = 1; i < 4; i++){
            String enchantmentTag = enchantmentAsString + i;
            if(arrowEntity.getScoreboardTags().contains(enchantmentTag)){
                return i;
            }
        }
        return 0;
    }

}
