package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class AmbushEnchantmentMixin {

    @Shadow public abstract boolean isTarget(LivingEntity entity, TargetPredicate predicate);

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyAmbushHitEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;

        LivingEntity player = (LivingEntity) source.getAttacker();
        LivingEntity ambushee = (LivingEntity) (Object) this;

        if (source.getSource() instanceof LivingEntity) {
            if (amount != 0.0F) {
                ItemStack mainHandStack = null;
                if (player != null) {
                    mainHandStack = player.getMainHandStack();
                }
                if (McdwEnchantsConfig.getValue("ambush")) {

                    if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.AMBUSH, mainHandStack) >= 1 )) {
                        int level = EnchantmentHelper.getLevel(EnchantsRegistry.AMBUSH, mainHandStack);

                        float extraDamageMultiplier = 0.15f;
                        float h = ambushee.getHealth();

                        if (player.isInvisible() && player.isSneaking()) {
                            ambushee.setHealth(h - (amount * (1 + (level * extraDamageMultiplier))));
                            ambushee.world.playSound(
                                    null,
                                    ambushee.getX(),
                                    ambushee.getY(),
                                    ambushee.getZ(),
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
