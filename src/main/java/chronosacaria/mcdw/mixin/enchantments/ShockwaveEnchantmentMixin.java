package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
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

@Mixin(LivingEntity.class)
public class ShockwaveEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyShockwaveEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {

        if(!(source.getAttacker() instanceof PlayerEntity)) return;


        PlayerEntity user = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_shockwave")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SHOCKWAVE, mainHandStack);

                        float SHOCKWAVE_DAMAGE_MULTIPLIER = 0.25F;


                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float cooledAttackStrength = 0.5F;
                        attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

                        float shockwaveDamage = attackDamage * SHOCKWAVE_DAMAGE_MULTIPLIER;
                        shockwaveDamage *= (level + 1) / 2.0F;


                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + level * 0.15) {
                            AOEHelper.causeShockwaveAttack(
                                    user,
                                    target,
                                    shockwaveDamage,
                                    3.0f);

                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
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
