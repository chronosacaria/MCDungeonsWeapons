package chronosacaria.mcdw.api.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

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

}
