package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IBowTimings;
import chronosacaria.mcdw.api.util.McdwEnchantmentHelper;
import chronosacaria.mcdw.api.util.ProjectileEffectHelper;
import chronosacaria.mcdw.api.util.RangedAttackHelper;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public abstract class BowItemMixin implements IBowTimings{
    private LivingEntity livingEntity;

    public LivingEntity getLivingEntity() { return this.livingEntity; }

    public void setLivingEntity(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void mcdw$createBonusShotArrowForBow(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci){
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT)){
            if (McdwEnchantmentHelper.hasEnchantment(stack, EnchantsRegistry.BONUS_SHOT)){
                int bonusShotLevel = EnchantmentHelper.getLevel(EnchantsRegistry.BONUS_SHOT, stack);
                float damageMultiplier = 0.1F + ((bonusShotLevel - 1) * 0.07F);
                float arrowVelocity = RangedAttackHelper.getVanillaOrModdedBowArrowVelocity(stack, remainingUseTicks);
                if (arrowVelocity >= 0.1F){
                    ProjectileEffectHelper.fireBonusShotTowardsOtherEntity(user, 10, damageMultiplier, arrowVelocity);
                }
            }
        }
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    private void mcdw$createMultiShotArrows(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci){
        LivingEntity target = user.getAttacking();
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.MULTI_SHOT)){
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
    
    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getMaxUseTime(Lnet/minecraft/item/ItemStack;)I"))
    private void mcdw$livingEntityGetter(ItemStack stack, World world, LivingEntity user,
                                                int remainingUseTicks, CallbackInfo ci){
        this.setLivingEntity(user);
    }

    @ModifyArg(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"), index = 0)
    private int mcdw$acceleratedPullProgress(int value){
        LivingEntity livingEntity = getLivingEntity();

        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
            int accelerateLevel = EnchantmentHelper.getLevel(EnchantsRegistry.ACCELERATE, livingEntity.getMainHandStack());

            return (int) (value * (1 + ((6.0f + 2.0f * accelerateLevel) / 100)));
        }
        return value;
    }
}
