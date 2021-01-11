package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
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
public class JunglesPoisonEnchantmentMixin {

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyJunglesPoisonEnchantment(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag = false;
                if (McdwEnchantsConfig.getValue("mixin_jungle_poison")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.JUNGLE_POISON, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.3) {
                            StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 60, level - 1);
                            target.addStatusEffect(poison);
                        }
                    }
                }
            }
        }
    }
}
