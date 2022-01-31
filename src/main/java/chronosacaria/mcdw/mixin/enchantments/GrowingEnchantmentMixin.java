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
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*
@Mixin(LivingEntity.class)
public abstract class GrowingEnchantmentMixin extends Entity {
    public GrowingEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Inject(method = "damage", at = @At("HEAD"))
    private void onGrowingEnchantment(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof LivingEntity livingEntity))
            return;
        if (!(source.getSource() instanceof PersistentProjectileEntity persistentProjectileEntity))
            return;
        if (!(persistentProjectileEntity.getOwner() instanceof PlayerEntity shooter))
            return;
        if (amount > 0 && source.getAttacker() instanceof LivingEntity) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GROWING)) {
                ItemStack mainHandStack = shooter.getMainHandStack();
                if (EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack) >= 1) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack);

                    double damageModifier = 1.0D + (0.25D * level);
                    double distance = shooter.distanceTo(livingEntity);
                    damageModifier *= distance * 0.1D;
                    //TODO This mixin can likely be converted to a modify variable (see below) and simply modify amount.
                    livingEntity.damage(DamageSource.GENERIC, (float) (amount * damageModifier));
                    livingEntity.world.playSound(
                            null,
                            livingEntity.getX(),
                            livingEntity.getY(),
                            livingEntity.getZ(),
                            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                    persistentProjectileEntity.remove(RemovalReason.KILLED);
                }
            }
        }
    }
}
 */

@Mixin(PersistentProjectileEntity.class)
public class GrowingEnchantmentMixin {

    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float injected(float amount) {

        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity shooter = persistentProjectileEntity.getOwner();

        if (EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.GROWING, (LivingEntity) shooter) >= 1) {
            int level = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.GROWING, (LivingEntity) shooter);

            // Checks and stuff
            double damageModifier = 1.0D + (0.25D * level);
            double distance = shooter.distanceTo();
            damageModifier *= distance * 0.1D;

            return (float) (amount * MathHelper.clamp(damageModifier, 1, 1 + level));
        }
        return amount;
    }
}
