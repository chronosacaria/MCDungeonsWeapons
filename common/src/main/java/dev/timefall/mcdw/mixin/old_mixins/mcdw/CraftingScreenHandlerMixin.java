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
@Mixin(net.minecraft.screen.CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {
    @ModifyVariable(method = "updateResult", at = @At(value = "STORE"), ordinal = 1)
    private static ItemStack mcdw$innateItemStack(ItemStack itemStack) {
        if (itemStack.getItem() instanceof IInnateEnchantment innateEnchantedItem) {
            Map<Enchantment, Integer> map = innateEnchantedItem.getInnateEnchantments();
            if (map == null) return itemStack;
            for (Enchantment enchantment : map.keySet())
                itemStack.addEnchantment(enchantment, map.get(enchantment));
        }
        return itemStack;
    }

}

 */
