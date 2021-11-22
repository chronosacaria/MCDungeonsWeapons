package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class CobwebShotEnchantmentMixin extends Entity {
    public CobwebShotEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onCobwebShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        World targetWorld = target.getEntityWorld();
        BlockPos targetPos = target.getBlockPos();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("cobweb_shot")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, mainHandStack) >= 1)) {
                if (targetWorld.getBlockState(targetPos) == Blocks.AIR.getDefaultState()) {
                    targetWorld.setBlockState(targetPos, Blocks.COBWEB.getDefaultState());
                }
            }
        }
    }

    @Inject(method = "onBlockHit", at = @At("TAIL"))
    private void onCobWebShotEnchantmentBlockHit(BlockHitResult blockHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack;
        if (shooter != null) {
            World shooterWorld = shooter.getEntityWorld();
            mainHandStack = shooter.getMainHandStack();
            Direction side = blockHitResult.getSide();
            if (McdwEnchantsConfig.getValue("cobweb_shot")) {
                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.COBWEB_SHOT, mainHandStack) >= 1)) {
                    if (shooterWorld.getBlockState(blockHitResult.getBlockPos().offset(side)) == Blocks.AIR.getDefaultState()){
                        shooterWorld.setBlockState(blockHitResult.getBlockPos().offset(side),
                                Blocks.COBWEB.getDefaultState());
                    }
                }
            }
        }
    }
}
