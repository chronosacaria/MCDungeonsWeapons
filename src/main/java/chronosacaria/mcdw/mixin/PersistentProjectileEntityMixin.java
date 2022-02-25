package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.effects.EnchantmentEffects;
import chronosacaria.mcdw.enums.CrossbowsID;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void mcdw$onEntityHitTail(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity target))
            return;

        PersistentProjectileEntity persProjEntity = (PersistentProjectileEntity) (Object) this;
        if (persProjEntity.getOwner() instanceof LivingEntity shooter) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.CHAIN_REACTION))
                EnchantmentEffects.applyChainReaction(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COBWEB_SHOT))
                EnchantmentEffects.applyCobwebShotEntity(shooter, target);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.FUSE_SHOT))
                EnchantmentEffects.applyFuseShot(shooter, target, persProjEntity);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GRAVITY))
                EnchantmentEffects.applyGravityShot(shooter, target);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.LEVITATION_SHOT))
                EnchantmentEffects.applyLevitationShot(shooter, target);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.PHANTOMS_MARK))
                EnchantmentEffects.applyPhantomsMark(shooter, target);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.POISON_CLOUD))
                EnchantmentEffects.applyPoisonCloudShot(shooter, target);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RADIANCE))
                EnchantmentEffects.applyRadianceShot(shooter, target);
            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.RICOCHET))
                EnchantmentEffects.applyRicochet(shooter, target);
        }

        if (persProjEntity.getOwner() instanceof PlayerEntity shooter) {

            if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.REPLENISH))
                EnchantmentEffects.applyReplenish(shooter);
        }
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void mcdw$onBlockHitTail(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persProjEntity = (PersistentProjectileEntity) (Object) this;
        if (!(persProjEntity.getOwner() instanceof LivingEntity shooter))
            return;

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.COBWEB_SHOT))
            EnchantmentEffects.applyCobwebShotBlock(shooter, blockHitResult);
    }

    @Inject(method = "getDragInWater", at = @At("HEAD"), cancellable = true)
    public void onHarpoonArrowFire(CallbackInfoReturnable<Float> cir) {
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        if (persistentProjectileEntity.getOwner() instanceof LivingEntity shooter) {

            if (shooter.getMainHandStack().getItem() == ItemsInit.crossbowItems.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW)) {
                if (persistentProjectileEntity.isTouchingWater()) {
                    float m = 0.85f;
                    cir.setReturnValue(m);
                }
            }
        }
    }

}