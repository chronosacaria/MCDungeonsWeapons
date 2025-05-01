/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin.old_mixins.mcdw;

/*
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
        if (itemStack.getItem() == ItemsRegistry.SWORD_ITEMS.get(McdwSwordItemRegistry.SWORD_MECHANIZED_SAWBLADE) && getDamage() + amount >= getMaxDamage()) {
            NbtList oldEnchantments = this.getEnchantments().copy();
            ItemStack brokenSawblade = new ItemStack(ItemsRegistry.SWORD_ITEMS.get(McdwSwordItemRegistry.SWORD_BROKEN_SAWBLADE));
            int oldRepairCost = itemStack.getRepairCost();
            brokenSawblade.setSubNbt(ItemStack.ENCHANTMENTS_KEY, oldEnchantments);
            CleanlinessHelper.mcdw$dropItem(entity, brokenSawblade);
            Map<Enchantment, Integer> brokenSawbladeEnchantments = EnchantmentHelper.get(brokenSawblade);
            brokenSawbladeEnchantments.remove(Enchantments.FIRE_ASPECT);
            EnchantmentHelper.set(brokenSawbladeEnchantments, brokenSawblade);
            brokenSawblade.setRepairCost(oldRepairCost);
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

 */