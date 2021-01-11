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
public class SwirlingEnchantmentMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applySwirlingEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
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
                if (McdwEnchantsConfig.getValue("mixin_swirling")) {
                    if (mainHandStack != null) {
                        uniqueWeaponFlag = mainHandStack.getItem() == ItemRegistry.getItem("dagger_shear_dagger").asItem()
                                || mainHandStack.getItem() == ItemRegistry.getItem("sword_broadsword").asItem();
                    }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, mainHandStack) >= 1 || uniqueWeaponFlag)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.SWIRLING, mainHandStack);

                        float SWIRLING_DAMAGE_MULTIPLIER = 0.5F;


                        float attackDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float cooledAttackStrength = 0.5F;
                        attackDamage *= 0.2F + cooledAttackStrength * cooledAttackStrength * 0.8F;

                        float swirlingDamage = attackDamage * SWIRLING_DAMAGE_MULTIPLIER;
                        swirlingDamage *= (level + 1) / 2.0F;


                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.1 + level * 0.15) {
                            AOEHelper.causeSwirlingAttack(
                                    (PlayerEntity) user,
                                    target,
                                    swirlingDamage,
                                    1.5f);

                            target.world.playSound(
                                    null,
                                    target.getX(),
                                    target.getY(),
                                    target.getZ(),
                                    SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
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
