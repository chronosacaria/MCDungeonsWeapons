package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
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

@Mixin(LivingEntity.class)
public class RushdownEnchantmentMixin {
    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onRushdownEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RUSHDOWN)) {

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.RUSHDOWN, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.RUSHDOWN, mainHandStack);
                float rushdownRand = user.getRandom().nextFloat();
                if (rushdownRand <= 0.1F) {
                    StatusEffectInstance rushdown = new StatusEffectInstance(StatusEffects.SPEED, level * 100, 2,
                            false, false);
                    user.addStatusEffect(rushdown);
                }
            }
        }
    }
}
