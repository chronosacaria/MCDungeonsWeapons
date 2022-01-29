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
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class VoidShotEnchantmentMixin extends Entity {

    public VoidShotEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Inject(method = "damage", at = @At("HEAD"))
    private void applyVoidShot(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof LivingEntity livingEntity))
            return;
        if (!(source.getSource() instanceof PersistentProjectileEntity persistentProjectileEntity))
            return;
        if (!(persistentProjectileEntity.getOwner() instanceof PlayerEntity shooter))
            return;
        if (amount > 0 && source.getAttacker() instanceof LivingEntity) {
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_SHOT)) {
                ItemStack mainHandStack = shooter.getMainHandStack();
                if (EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, mainHandStack) >= 1) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.VOID_SHOT, mainHandStack);
                    float voidShotChance = 0.25f + level * 0.05F;
                    float voidShotRand = shooter.getRandom().nextFloat();

                    if (voidShotRand <= voidShotChance) {
                        double damageModifier = 1.0D + level;
                        //TODO Find a way to make this source an arrow without looping it
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
}