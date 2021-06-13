package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class AnimaConduitShotEnchantmentMixin extends Entity {

    protected abstract int getCurrentExperience(PlayerEntity player);

    public AnimaConduitShotEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onAnimaConduitShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("anima")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.ANIMA_CONDUIT, mainHandStack);
                float healthRegained;

                //ANIMA CONDUIT AS PER KILL
                if (shooter.getHealth() < shooter.getMaxHealth()) {
                    healthRegained = (float) (getCurrentExperience((PlayerEntity) shooter) * (0.2 * level));
                    shooter.heal(healthRegained);
                    ((PlayerEntity) shooter).addExperienceLevels(-999999999);
                    //this.world.sendEntityStatus(this,(byte)35);
                }
            }
        }
    }
}
