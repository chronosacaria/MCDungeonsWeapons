package chronosacaria.mcdw.mixin;

import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();
    @Shadow public abstract int getDamage();
    @Shadow public abstract int getMaxDamage();
    @Shadow public abstract NbtList getEnchantments();

    // When the Mechanised Sawblade breaks, it "becomes" the Broken Sawblade
    @Inject(at = @At("HEAD"), method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V")
    public <T extends LivingEntity> void damage(int amount, T entity, Consumer<T> breakCallback, CallbackInfo ci) {
        if (this.getItem() == ItemsInit.swordItems.get(SwordsID.SWORD_MECHANIZED_SAWBLADE) && getDamage() + amount >= getMaxDamage()) {
            NbtList oldEnchantments = this.getEnchantments().copy();
            ItemStack brokenSawblade = new ItemStack(ItemsInit.swordItems.get(SwordsID.SWORD_BROKEN_SAWBLADE));
            brokenSawblade.setSubNbt("Enchantments", oldEnchantments);
            CleanlinessHelper.mcdw$dropItem(entity, brokenSawblade);
        }
    }
}