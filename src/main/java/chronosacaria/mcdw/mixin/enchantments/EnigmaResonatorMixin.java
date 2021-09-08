
package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LivingEntity.class, PlayerEntity.class})
public abstract class EnigmaResonatorMixin {

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyEnigmaResonatorEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                if (McdwEnchantsConfig.getValue("enigma_resonator")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack);

                        int numSouls = ((PlayerEntity) source.getAttacker()).experienceLevel;

                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float extraDamageMultiplier = (float) (Math.log(numSouls * level))/1.75F;
                        float getExtraDamage = (attackDamage * (extraDamageMultiplier));

                        if (numSouls >= 1){

                            target.damage(DamageSource.GENERIC, getExtraDamage);
                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.PARTICLE_SOUL_ESCAPE,
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
