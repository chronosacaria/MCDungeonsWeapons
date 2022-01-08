package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
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
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT)) {
            if (stack.getItem() instanceof CrossbowItem){
                if (CrossbowItem.isCharged(stack)) {
                    if (McdwEnchantmentHelper.hasEnchantment(stack, EnchantsRegistry.BONUS_SHOT)) {
                        int bonusShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.BONUS_SHOT, stack);
                        float damageMultiplier = 0.1F + ((bonusShotLevel - 1) * 0.07F);

                        float arrowVelocity = RangedAttackHelper.getVanillaOrModdedCrossbowArrowVelocity(stack);
                        ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier,
                                arrowVelocity);
                    }
                }
            }
        }
    }
}
