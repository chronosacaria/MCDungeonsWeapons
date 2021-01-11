package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AbilityHelper;
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
public class FuseShotEnchantmentMixin {

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)

    private void applyFuseShotEnchantment(DamageSource source, float amount, CallbackInfo info)  {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        boolean uniqueWeaponFlag =
                false;
        if (McdwEnchantsConfig.getValue("mixin_fuse_shot")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.FUSE_SHOT, mainHandStack);
                float chance = user.getRandom().nextFloat();
                if (chance <= (0.2 + level * 0.15)) {
                    AbilityHelper.causeFuseShot(user, target, level);
                }
            }
        }
    }
}
