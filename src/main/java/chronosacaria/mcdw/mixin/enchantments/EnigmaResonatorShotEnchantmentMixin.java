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
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class EnigmaResonatorShotEnchantmentMixin extends Entity {

    public EnigmaResonatorShotEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEnigmaShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity target)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();

        if (!(shooter instanceof PlayerEntity)) return;

        ItemStack mainHandStack = shooter.getMainHandStack();

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ENIGMA_RESONATOR)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.ENIGMA_RESONATOR, mainHandStack);

                int numSouls = ((PlayerEntity)shooter).experienceLevel;

                double extraDamageMultiplier =
                        (persistentProjectileEntity.getDamage()*(Math.log(numSouls * level)))*1.75D;

                if (numSouls >= 1){
                    target.damage(DamageSource.arrow(persistentProjectileEntity, shooter),
                            (float) extraDamageMultiplier);
                    shooter.world.playSound(
                            null,
                            shooter.getX(),
                            shooter.getY(),
                            shooter.getZ(),
                            SoundEvents.PARTICLE_SOUL_ESCAPE,
                            SoundCategory.PLAYERS,
                            0.5F,
                            1.0F);
                }
            }
        }
    }
}
