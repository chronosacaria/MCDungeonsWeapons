/*package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class SmitingEnchantmentMixin {

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySmitingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }
                boolean uniqueWeaponFlag =
                        false;
                if (McdwEnchantsConfig.getValue("mixin_smiting")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack) >= 1 && !(EnchantmentHelper.getLevel(Enchantments.SMITE, mainHandStack) >= 1))) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SMITING, mainHandStack);

                        float SMITING_DAMAGE_MULTIPLIER = 1.25F;


                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        //float cooledAttackStrength = 0.5F;
                        //attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

                        float smitingDamage = attackDamage * SMITING_DAMAGE_MULTIPLIER;
                        float h = target.getHealth();
                        smitingDamage *= (h - (level + 1)) / 2.0F;



                        float chance = user.getRandom().nextFloat();
                        if (chance <= 1 && target.isUndead()) {
                            AOEHelper.causeSmitingAttack(
                                    (PlayerEntity) user,
                                    target,
                                    smitingDamage,
                                    3.0f);
                        }
                    }
                }
            }
        }
    }
}
*/