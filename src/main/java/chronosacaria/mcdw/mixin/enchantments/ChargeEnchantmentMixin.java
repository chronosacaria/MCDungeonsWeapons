package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class ChargeEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCharge(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;

        PlayerEntity user = (PlayerEntity) source.getAttacker();
        ItemStack mainHandStack = null;

        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("charge")) {

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.CHARGE, mainHandStack);
                float chargeRand = user.getRandom().nextFloat();
                if (chargeRand <= 0.1F) {
                    StatusEffectInstance charge = new StatusEffectInstance(StatusEffects.SPEED, level * 20, 4);
                    user.addStatusEffect(charge);
                }
            }
        }
    }
}
