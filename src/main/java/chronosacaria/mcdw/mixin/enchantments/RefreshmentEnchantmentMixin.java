package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.api.util.AOECloudHelper;
import chronosacaria.mcdw.api.util.AOEHelper;
import chronosacaria.mcdw.api.util.PlayerAttackHelper;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class RefreshmentEnchantmentMixin {

    //TODO Figure out how to make more than one, but less than four bottles convert to potions
    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void onRefreshmentEnchantmentKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getSource() instanceof PlayerEntity)) return;

        LivingEntity user = (LivingEntity) source.getSource();
        if (source.getSource() instanceof PlayerEntity){
            ItemStack mainHandStack = null;
            if (user != null) {
                mainHandStack = user.getMainHandStack();
            }
            if (McdwEnchantsConfig.getValue("refreshment")) {
                if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT, mainHandStack) > 0 )) {
                    int level = EnchantmentHelper.getLevel(EnchantsRegistry.REFRESHMENT, mainHandStack);
                    PlayerInventory playerInventory = ((PlayerEntity)user).getInventory();
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
    }
}
