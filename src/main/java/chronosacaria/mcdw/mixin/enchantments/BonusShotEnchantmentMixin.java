package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.enchants.lists.RangedEnchantmentList;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class BonusShotEnchantmentMixin {

    // Bonus Shot
    @Inject(method = "createArrow", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void createBonusShotArrow(World world, LivingEntity user, ItemStack crossbow, ItemStack arrow,
                                    CallbackInfoReturnable<PersistentProjectileEntity> cir, ArrowItem arrowItem,
                                    PersistentProjectileEntity persistentProjectileEntity){
        ItemStack stack = user.getMainHandStack();
        if (stack.getItem() instanceof CrossbowItem){
            if (CrossbowItem.isCharged(stack)){
                boolean uniqueWeaponFlag = stack.getItem() == ItemRegistry.getItem("crossbow_butterfly_crossbow").asItem();
                if (McdwEnchantmentHelper.hasEnchantment(stack, RangedEnchantmentList.BONUS_SHOT) || uniqueWeaponFlag) {
                    int bonusShotLevel = EnchantmentHelper.getLevel(RangedEnchantmentList.BONUS_SHOT, stack);
                    float damageMultiplier;
                    damageMultiplier = 0.1F + (bonusShotLevel - 1 * 0.07F);
                    if (uniqueWeaponFlag) {
                        damageMultiplier += 0.1F;
                    }
                    float arrowVelocity = RangedAttackHelper.getVanillaOrModdedCrossbowArrowVelocity(stack);
                    ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier,
                            arrowVelocity);
                }
            }
        }
    }
}
