package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class SoulSiphonEnchantmentMixin {
    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onSoulSiphonEnchantmentKill(DamageSource source, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("mixin_siphon")) {

            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.SOUL_SIPHON, mainHandStack);
                float siphonRand = user.getRandom().nextFloat();
                if (siphonRand <= 0.1F) {
                    if (attackingPlayer != null) {
                        attackingPlayer.addExperience(level * 3);
                    }
                }
            }
        }
    }
}
