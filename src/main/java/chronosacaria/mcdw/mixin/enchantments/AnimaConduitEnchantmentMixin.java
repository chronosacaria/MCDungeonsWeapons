package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class AnimaConduitEnchantmentMixin {

    @Shadow
    protected abstract int getCurrentExperience(PlayerEntity player);

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onAnimaConduitEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        PiglinEntity piglinEntity = null;

        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("mixin_anima")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack);
                float healthRegained;

                //ANIMA CONDUIT AS PER KILL
                if (user.getHealth() < user.getMaxHealth()) {
                    healthRegained = (float) (getCurrentExperience((PlayerEntity) user) * (0.2 * level));
                    user.heal(healthRegained);
                    ((PlayerEntity) user).addExperienceLevels(-999999999);
                    //this.world.sendEntityStatus(this,(byte)35);
                }
            }
        }
    }
}
