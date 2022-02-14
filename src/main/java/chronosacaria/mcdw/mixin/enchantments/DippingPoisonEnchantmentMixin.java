package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class DippingPoisonEnchantmentMixin {
    @Shadow public abstract ItemStack getOffHandStack();

    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void applyDippingPoisonPotionConsumption(CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity user))
            return;

        ItemStack mainHandStack = null;

        ItemStack poisonTippedArrow = PotionUtil.setPotion(new ItemStack(Items.TIPPED_ARROW, 8), Potions.POISON);

        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_DEVOURER)) {
           if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, mainHandStack) > 0)) {
               int level = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_DEVOURER, mainHandStack);
               if (user instanceof PlayerEntity) {
                   if (level > 0) {
                       List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getOffHandStack());
                       if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
                           ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(),
                                   user.getZ(),
                                   poisonTippedArrow);
                           user.world.spawnEntity(arrowDrop);
                       }
                   }
               }
           }
        }
    }
}
