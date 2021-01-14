package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOECloudHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class GrowingEnchantmentMixin extends Entity {
    public GrowingEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        ArrowEntity arrowEntity = (ArrowEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) arrowEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (McdwEnchantsConfig.getValue("mixin_growing")) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack);
                double originalDamage = arrowEntity.getDamage();
                double damageModifier = 0;
                if (level == 1) damageModifier = 1.25D;
                if (level == 2) damageModifier = 1.5D;
                if (level == 3) damageModifier = 1.75D;
                double squareDistanceTo = shooter.distanceTo(target);
                double distance = Math.sqrt(squareDistanceTo);
                double distanceTraveledModifier = distance * 0.1;
                arrowEntity.setDamage(originalDamage * Math.min(distanceTraveledModifier, damageModifier));
            }
        }
    }
}
