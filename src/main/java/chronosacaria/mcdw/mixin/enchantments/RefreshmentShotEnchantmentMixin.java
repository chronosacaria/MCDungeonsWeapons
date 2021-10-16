package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class RefreshmentShotEnchantmentMixin extends Entity {

    public RefreshmentShotEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    //TODO Figure out how to make more than one, but less than four bottles convert to potions
    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onRefreshmentShotEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        if (!(shooter instanceof PlayerEntity)){
            return;
        }
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT_SHOT, mainHandStack) > 0 )) {
            int level = EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT_SHOT, mainHandStack);
            PlayerInventory playerInventory = ((PlayerEntity)shooter).getInventory();
            for (int slotID = 0; slotID < playerInventory.size(); slotID++){
                ItemStack currentStack = playerInventory.getStack(slotID);
                if (currentStack.getItem() instanceof GlassBottleItem && currentStack.getCount() < 2){
                    ItemStack healthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.HEALING);
                    playerInventory.setStack(slotID, healthPotion);
                    if (healthPotion.getCount() <= level){
                        break;
                    }
                }
            }
        }
    }
}
