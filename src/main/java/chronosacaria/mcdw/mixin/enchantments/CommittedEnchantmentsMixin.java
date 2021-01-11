package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.items.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (LivingEntity.class)
public class CommittedEnchantmentsMixin {

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyCommittedEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.isProjectile()) return;
        if (source.getSource() instanceof ArrowEntity) return;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (user != null) {
                    mainHandStack = user.getMainHandStack();
                }

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, mainHandStack) >= 1)) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.COMMITTED, mainHandStack);


                        float getTargetHealth = target.getHealth();
                        float getTargetMaxHealth = target.getMaxHealth();
                        float getTargetRemainingHealth = getTargetHealth / getTargetMaxHealth;
                        float getOriginalDamage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                        float extraDamageMultiplier = 0.1F + level * 0.1F;
                        float getExtraDamage = (getOriginalDamage * (1 - getTargetRemainingHealth) * extraDamageMultiplier);

                        float chance = user.getRandom().nextFloat();
                        if (chance <= 0.2) {
                            if ((Math.abs(getTargetHealth)) < (Math.abs(getTargetMaxHealth))) {
                                target.damage(DamageSource.mob( user),
                                        getExtraDamage);
                                target.world.playSound(
                                        null,
                                        target.getX(),
                                        target.getY(),
                                        target.getZ(),
                                        SoundEvents.ENTITY_GENERIC_EXPLODE,
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

