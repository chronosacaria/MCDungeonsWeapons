package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class VoidShotEnchantmentMixin {

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float applyVoidShot(float amount, DamageSource source) {
        if (!((Object) this instanceof LivingEntity livingEntity))
            return amount;
        if (!(source.getSource() instanceof PersistentProjectileEntity persistentProjectileEntity))
            return amount;
        if (!(persistentProjectileEntity.getOwner() instanceof PlayerEntity shooter))
            return amount;
        if (amount > 0 && source.getAttacker() instanceof LivingEntity) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_SHOT)) {
                ItemStack mainHandStack = shooter.getMainHandStack();
                if (EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, mainHandStack) >= 1) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, mainHandStack);
                    float voidShotChance = 0.25f + level * 0.05F;
                    float voidShotRand = shooter.getRandom().nextFloat();

                    if (voidShotRand <= voidShotChance) {
                        float damageModifier = 1.0F + level;
                        livingEntity.world.playSound(
                                null,
                                livingEntity.getX(),
                                livingEntity.getY(),
                                livingEntity.getZ(),
                                SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                                SoundCategory.PLAYERS,
                                0.5F,
                                1.0F);
                        return amount * damageModifier;
                    }
                }
            }
        }
        return amount;
    }
}