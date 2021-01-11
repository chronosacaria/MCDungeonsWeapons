package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
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
public class GravityEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyGravityEnchantment(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity && !source.isProjectile()) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_gravity")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.GRAVITY, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.3) {
                            AOEHelper.pullInNearbyEntities(
                                    user,
                                    target,
                                    (level + 1) * 3);
                        }
                    }
                }
            }
        }
    }
}
