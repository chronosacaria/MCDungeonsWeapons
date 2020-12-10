package chronosacaria.mcdw.api.util;

import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.weapons.Bows;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

public class RangedAttackHelper {

    public static float getVanillaArrowVelocity(ItemStack stack, int charge){
        float bowChargeTime = getVanillaBowChargeTime(stack);
        if (bowChargeTime <= 0){
            bowChargeTime = 1;
        }
        float arrowVelocity = (float) charge / bowChargeTime;
        arrowVelocity = (arrowVelocity * arrowVelocity + arrowVelocity * 2.0F) / 3.0F;
        if (arrowVelocity > 1.0F){
            arrowVelocity = 1.0F;
        }
        return arrowVelocity;
    }

    public static float getVanillaBowChargeTime(ItemStack stack){
        int quickChargeLevel = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        //int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, stack);

        float bowChargeTime = McdwBow.chargeTime * (Math.max(20.0F -5 * quickChargeLevel, 0));
        long lastFiredtime = (long)(McdwBow.getPullProgress(22) * (Math.max(20.0F -5 * quickChargeLevel, 0)));

        if (/*accelerateLevel > 0 &&*/ lastFiredtime > 0){
            return Math.max(bowChargeTime - 5 * quickChargeLevel, 0);
        } else {
            return Math.max(20.0F - 5 * quickChargeLevel, 0);
        }
    }

    public static void addWeaponTags(ArrowEntity arrowEntity, ItemStack stack){
        if (stack.getItem() == Bows.BOW_ANCIENT_BOW.asItem()) arrowEntity.addScoreboardTag("AncientBow");
        if (stack.getItem() == Bows.BOW_BONEBOW.asItem()) arrowEntity.addScoreboardTag("Bonebow");
        if (stack.getItem() == Bows.BOW_ELITE_POWER_BOW.asItem()) arrowEntity.addScoreboardTag("ElitePowerBow");
        if (stack.getItem() == Bows.BOW_GUARDIAN_BOW.asItem()) arrowEntity.addScoreboardTag("GuardianBow");
        if (stack.getItem() == Bows.BOW_HUNTERS_PROMISE.asItem()) arrowEntity.addScoreboardTag("HuntersPromise");
        if (stack.getItem() == Bows.BOW_MASTERS_BOW.asItem()) arrowEntity.addScoreboardTag("MastersBow");
        if (stack.getItem() == Bows.BOW_NOCTURNAL_BOW.asItem()) arrowEntity.addScoreboardTag("NocturnalBow");
        if (stack.getItem() == Bows.BOW_RED_SNAKE.asItem()) arrowEntity.addScoreboardTag("RedSnake");
        if (stack.getItem() == Bows.BOW_SABREWING.asItem()) arrowEntity.addScoreboardTag("Sabrewing");
        if (stack.getItem() == Bows.BOW_GREEN_MENACE.asItem()) arrowEntity.addScoreboardTag("GreenMenace");
        if (stack.getItem() == Bows.BOW_PINK_SCOUNDREL.asItem()) arrowEntity.addScoreboardTag("PinkScoundrel");

    }
}
