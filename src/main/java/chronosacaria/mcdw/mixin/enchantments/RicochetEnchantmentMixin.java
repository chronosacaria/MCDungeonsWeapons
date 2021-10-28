package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.bases.McdwBow;
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

@Mixin({LivingEntity.class, PlayerEntity.class})
public class RicochetEnchantmentMixin {

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyRicochet(DamageSource source, float amount, CallbackInfo info) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;

        PlayerEntity attacker = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        ItemStack mainHandStack = attacker.getMainHandStack();

        if (McdwEnchantsConfig.getValue("ricochet"))  {
            if (mainHandStack != ItemStack.EMPTY && (EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, mainHandStack) >= 1)) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.RICOCHET, mainHandStack);
                if (level >= 1) {
                    float damageMultiplier = 0.1F + ((level - 1) * 0.07F);
                    float arrowVelocity = McdwBow.maxBowRange;
                    if (arrowVelocity > 0.1F) {
                        ProjectileEffectHelper.riochetArrowTowardsOtherEntity(target, 10, damageMultiplier, arrowVelocity);
                    }
                }
            }
        }
    }
}
