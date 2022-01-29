package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
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
public class VoidStrikeEnchantmentMixin {

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyVoidStrikeEnchantmentDamage(DamageSource source, float amount, CallbackInfo info) {
        if(!(source.getAttacker() instanceof PlayerEntity user))
            return;
        if(!((Object) this instanceof LivingEntity target))
            return;
        if (!(source.getSource() instanceof LivingEntity))
            return;

        if (amount != 0.0F) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_STRIKE)) {
                ItemStack mainHandStack = null;
                if (user != null)
                    mainHandStack = user.getMainHandStack();

                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.VOID_STRIKE, mainHandStack) >= 1)) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_STRIKE, mainHandStack);

                    float voidStrikeChance = 0.15f + level * 0.05F;
                    float voidStrikeRand = user.getRandom().nextFloat();

                    if (voidStrikeRand <= voidStrikeChance) {
                        float voidDamageModifier = 2.0F * level;
                        target.damage(DamageSource.GENERIC, amount * voidDamageModifier);
                        target.world.playSound(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                                SoundCategory.PLAYERS,
                                0.5F,
                                1.0F);
                    }
                }
            }
        }
    }
}