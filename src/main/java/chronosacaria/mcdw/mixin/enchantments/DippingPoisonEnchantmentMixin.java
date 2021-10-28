/*package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
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
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity user = (PlayerEntity) (Object) this;

        ItemStack mainHandStack = null;

        ItemStack poisonTippedArrow = PotionUtil.setPotion(new ItemStack(Items.TIPPED_ARROW), Potions.POISON);

        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("dipping_poison")) {
           if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.DIPPING_POISON, mainHandStack) >= 1 )) {
               int level = EnchantmentHelper.getLevel(EnchantsRegistry.DIPPING_POISON, mainHandStack);
               if (user instanceof PlayerEntity) {
                   if (level >= 1) {
                       List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getOffHandStack());
                       if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
                           ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(),
                                   user.getZ(),
                                   //TODO MAKE POISON TIPPED ARROWS DROP
                                   new ItemStack(Items.TIPPED_ARROW, 8));
                           user.world.spawnEntity(arrowDrop);
                       }
                   }
               }
           }
        }
    }
}
*/