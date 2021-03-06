package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.lists.RangedEnchantmentList;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public class BonusShotBowEnchantmentMixin {
    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void createBonusShotArrowForBow(ItemStack stack, World world, LivingEntity user,
                                                   int remainingUseTicks, CallbackInfo ci){
        boolean uniqueWeaponFlag = stack.getItem() == ItemRegistry.getItem("bow_haunted_bow").asItem();
        if (McdwEnchantsConfig.getValue("bonus_shot")){
            if (McdwEnchantmentHelper.hasEnchantment(stack, RangedEnchantmentList.BONUS_SHOT) || uniqueWeaponFlag){
                int bonusShotLevel = EnchantmentHelper.getLevel(RangedEnchantmentList.BONUS_SHOT, stack);
                float damageMultiplier = 0.1F + (bonusShotLevel - 1 * 0.07F);
                float arrowVelocity = RangedAttackHelper.getVanillaOrModdedBowArrowVelocity(stack, remainingUseTicks);
                if (arrowVelocity >= 0.1F){
                    ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier, arrowVelocity);
                }
            }
        }
    }
}
