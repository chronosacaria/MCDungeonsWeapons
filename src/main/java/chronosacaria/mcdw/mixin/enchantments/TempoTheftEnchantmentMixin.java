package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AbilityHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class TempoTheftEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyTempoTheftEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("tempo_theft")) {
                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.TEMPO_THEFT, mainHandStack);
                        if (target instanceof LivingEntity) {
                            AbilityHelper.stealSpeedFromTarget(user, target, level);
                        }
                    }
                }
            }
        }
    }
}
