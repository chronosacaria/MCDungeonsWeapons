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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class GrowingEnchantmentMixin extends Entity {
    public GrowingEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getMainHandStack();

    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float onGrowingEnchantment(float amount, DamageSource source) {
        if (!((Object) this instanceof LivingEntity livingEntity))
            return amount;
        if (!(source.getSource() instanceof PersistentProjectileEntity persistentProjectileEntity))
            return amount;
        if (!(persistentProjectileEntity.getOwner() instanceof PlayerEntity shooter))
            return amount;
        if (amount > 0 && source.getAttacker() instanceof LivingEntity) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GROWING)) {
                ItemStack mainHandStack = shooter.getMainHandStack();
                if (EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack) >= 1) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack);

                    double damageModifier = 1.0D + (0.25D * level);
                    damageModifier *= shooter.distanceTo(livingEntity) * 0.1D;
                    livingEntity.world.playSound(
                            null,
                            livingEntity.getX(),
                            livingEntity.getY(),
                            livingEntity.getZ(),
                            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                    return amount * (float) MathHelper.clamp(damageModifier, 1, 1 + level);
                }
            }
        }
        return amount;
    }
}