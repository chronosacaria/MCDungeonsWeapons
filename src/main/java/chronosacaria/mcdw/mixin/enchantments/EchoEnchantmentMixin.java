package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.sounds.McdwSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class EchoEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEchoEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (McdwEnchantsConfig.getValue("echo")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.ECHO, mainHandStack);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + level * 0.15) {
                            AOEHelper.causeEchoAttack(user,
                                    target,
                                    3.0f,
                                    level,
                                    amount);
                            user.world.playSound(
                                    null,
                                    user.getX(),
                                    user.getY(),
                                    user.getZ(),
                                    McdwSoundEvents.ECHO_SOUND_EVENT,
                                    SoundCategory.PLAYERS,
                                    0.5F,
                                    1.0F);
                        }
                    }
                }
            }
        }
    }
}
