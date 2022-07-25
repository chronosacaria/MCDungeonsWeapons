package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.bases.McdwShortBow;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static net.minecraft.item.CrossbowItem.hasProjectile;

public class RangedAttackHelper {

    public static float getVanillaArrowVelocity(ItemStack stack, int charge){
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }
        float arrowVelocity = (float) charge / bowChargeTime;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 3.0F;
        return Math.min(arrowVelocity, 1.0F);
    }

    public static float getVanillaBowChargeTime(ItemStack stack){
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        //int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, stack);

        float bowChargeTime = 30 * (Math.max(20.0F - 5 * quickChargeLevel, 0));
        long lastFiredtime = (long)(McdwBow.getPullProgress(22) * (Math.max(20.0F - 5 * quickChargeLevel, 0)));

        if (/*accelerateLevel > 0 &&*/ lastFiredtime > 0){
            return Math.max(bowChargeTime - 5 * quickChargeLevel, 0);
        } else {
            return Math.max(20.0F - 5 * quickChargeLevel, 0);
        }
    }

    public static float getShortBowChargeTime(ItemStack stack){
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        //int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, stack);

        float bowChargeTime = 15 * (Math.max(10.0F - 5 * quickChargeLevel, 0));
        long lastFiredtime = (long)(McdwShortBow.getPullProgress(11) * (Math.max(10.0F - 5 * quickChargeLevel, 0)));

        if (/*accelerateLevel > 0 &&*/ lastFiredtime > 0){
            return Math.max(bowChargeTime - 5 * quickChargeLevel, 0);
        } else {
            return Math.max(10.0F - 5 * quickChargeLevel, 0);
        }
    }


    public static float getVanillaOrModdedCrossbowArrowVelocity(ItemStack stack){
        float arrowVelocity;
        if (stack.getItem() instanceof McdwCrossbow mcdwCrossbow){
            arrowVelocity = mcdwCrossbow.getProjectileVelocity(stack);
        } else {
            arrowVelocity = hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
        }
        return arrowVelocity;
    }

    public static float getVanillaOrModdedBowArrowVelocity(ItemStack stack, int charge){
        float arrowVelocity;
        if (stack.getItem() instanceof McdwBow){
            arrowVelocity = McdwBow.getBowArrowVelocity(stack, charge);
        } else {
            arrowVelocity = getVanillaArrowVelocity(stack, charge);
        }
        return arrowVelocity;
    }
}
