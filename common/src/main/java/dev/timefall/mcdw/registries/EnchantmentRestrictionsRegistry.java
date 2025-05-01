/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.api.util.EnchantmentRestriction;
import dev.timefall.mcdw.bases.McdwAxeItem;
import dev.timefall.mcdw.bases.McdwDoubleAxeItem;
import dev.timefall.mcdw.bases.McdwSpearItem;
import dev.timefall.mcdw.registries.items.McdwSwordItemRegistry;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantments;

public class EnchantmentRestrictionsRegistry {
    public static void register() {
        // Permit individual enchantments for specific items
        EnchantmentRestriction.permit(Enchantments.FIRE_ASPECT, itemStack -> itemStack.getItem() instanceof McdwAxeItem || itemStack.getItem() instanceof McdwDoubleAxeItem);
        EnchantmentRestriction.permit(Enchantments.EFFICIENCY, itemStack -> itemStack.isOf(McdwSwordItemRegistry.SWORD_MECHANIZED_SAWBLADE));

        // Permit specific enchantment types for certain items
        EnchantmentRestriction.permitTarget((enchantment, itemStack) -> enchantment instanceof DamageEnchantment && itemStack.getItem() instanceof McdwSpearItem);

        // Prohibit individual enchantments for specific items
        EnchantmentRestriction.prohibit(Enchantments.EFFICIENCY, itemStack -> itemStack.isOf(McdwSwordItemRegistry.SWORD_BROKEN_SAWBLADE));
    }
}