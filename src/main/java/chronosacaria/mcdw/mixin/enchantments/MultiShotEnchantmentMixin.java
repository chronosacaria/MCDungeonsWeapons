package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enums.BowsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public class MultiShotEnchantmentMixin {

    // Bow Multi Shot
    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    private void createMultiShotArrows(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci){
        LivingEntity target = user.getAttacking();
        boolean uniqueWeaponFlag = stack.getItem() == ItemsInit.bowItems.get(BowsID.BOW_LOST_SOULS).asItem();
        if (McdwEnchantsConfig.getValue("multi_shot")){
            if (uniqueWeaponFlag) {
                ArrowItem arrowitem = (ArrowItem) (stack.getItem() instanceof ArrowItem ? stack.getItem() : Items.ARROW);
                PersistentProjectileEntity persistentProjectileEntity = arrowitem.createArrow(world, stack, user);
                if (!(target == null)) { // \/\/ Taken from AbstractSkeletonEntity
                    double d = target.getX() - user.getX();
                    double e = target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY();
                    double f = target.getZ() - user.getZ();
                    double g = MathHelper.sqrt((float) (d * d + f * f));
                    persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224D, f, 1.6F, (float) (14 - user.world.getDifficulty().getId() * 4));
                    persistentProjectileEntity.pickupType =
                            PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    world.spawnEntity(persistentProjectileEntity);
                }
            }
        }
    }
}
