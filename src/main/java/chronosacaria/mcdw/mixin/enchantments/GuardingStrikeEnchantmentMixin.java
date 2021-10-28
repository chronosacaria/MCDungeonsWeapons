package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.PlayerAttackHelper;
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
public abstract class GuardingStrikeEnchantmentMixin {

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onGuardingStrikeEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        if (PlayerAttackHelper.isLikelyNotMeleeDamage(source)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("guarding_strike")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GUARDING_STRIKE, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.GUARDING_STRIKE, mainHandStack);
                    int shieldDuration = 20 + (20 * level);
                StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, shieldDuration, 2);
                user.addStatusEffect(shield);
            }
        }
    }
}
