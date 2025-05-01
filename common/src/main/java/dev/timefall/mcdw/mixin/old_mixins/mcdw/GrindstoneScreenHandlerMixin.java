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
@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {
    @ModifyArgs(method = "grind", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;set(Ljava/util/Map;Lnet/minecraft/item/ItemStack;)V"))
    private void mcdw$reapplyInnateEnchantmentOnGrind(Args args) {
        Map<Enchantment, Integer> map = args.get(0);
        if (args.get(1) instanceof ItemStack itemStack && itemStack.getItem() instanceof IInnateEnchantment) {
            Map<Enchantment, Integer> innateMap = ((IInnateEnchantment) itemStack.getItem()).getInnateEnchantments();
            if (innateMap == null) return;
            for (Enchantment enchantment : innateMap.keySet())
                map.put(enchantment, innateMap.get(enchantment));
            args.set(0, map);
        }
    }
}

 */
