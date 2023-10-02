package chronosacaria.mcdw.mixin.mcdw;

import chronosacaria.mcdw.api.interfaces.*;
import chronosacaria.mcdw.api.util.CleanlinessHelper;
import chronosacaria.mcdw.enums.SwordsID;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.Map;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();
    @Shadow public abstract int getDamage();
    @Shadow public abstract int getMaxDamage();
    @Shadow public abstract NbtList getEnchantments();
    
    // When the Mechanised Sawblade breaks, it "becomes" the Broken Sawblade
    @Inject(at = @At("HEAD"), method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V")
    public <T extends LivingEntity> void mcdw$damage(int amount, T entity, Consumer<T> breakCallback, CallbackInfo ci) {
        ItemStack itemStack = this.getItem().getDefaultStack();
        if (itemStack.getItem() == ItemsRegistry.SWORD_ITEMS.get(SwordsID.SWORD_MECHANIZED_SAWBLADE) && getDamage() + amount >= getMaxDamage()) {
            NbtList oldEnchantments = this.getEnchantments().copy();
            ItemStack brokenSawblade = new ItemStack(ItemsRegistry.SWORD_ITEMS.get(SwordsID.SWORD_BROKEN_SAWBLADE));
            brokenSawblade.setSubNbt(ItemStack.ENCHANTMENTS_KEY, oldEnchantments);
            CleanlinessHelper.mcdw$dropItem(entity, brokenSawblade);
            Map<Enchantment, Integer> brokenSawbladeEnchantments = EnchantmentHelper.get(brokenSawblade);
            brokenSawbladeEnchantments.remove(Enchantments.FIRE_ASPECT);
            EnchantmentHelper.set(brokenSawbladeEnchantments, brokenSawblade);
        }
    }
    
    // The enchantment table does not allow enchanting items that already have enchantments applied
    // This mixin changes items, that only got their IInnateEnchantments to still be enchantable
    @Inject(method = "isEnchantable()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasEnchantments()Z"), cancellable = true)
    public void mcdw$isEnchantable(CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.getItem() instanceof IInnateEnchantment iInnateEnchantment && iInnateEnchantment.onlyHasInnateEnchantments(stack)) {
            cir.setReturnValue(true);
        }
    }
}