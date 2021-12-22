package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class LeechingEnchantmentMixin {
    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onLeechingEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEECHING)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.LEECHING, mainHandStack);
                float healthRegained;
                float targetMaxHealth = target.getMaxHealth();

                //LEECHING AS PER KILL
                if (user.getHealth() < user.getMaxHealth()) {
                    healthRegained = (0.2F + 0.2F * level) * targetMaxHealth;
                    user.heal(healthRegained);
                }
            }
        }
    }
}
