/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.enchantment.types.RangedEnchantment;
import dev.timefall.mcdw.registries.items.McdwCrossbowItemRegistry;
import net.minecraft.item.ItemStack;

/*
 * Effects Needed:
 */

/*
 * Effects Present:
 */

public class ShadowShotEnchantment extends RangedEnchantment {
    public ShadowShotEnchantment(Properties properties) {
        super(properties);
    }
    //public ShadowShotEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SHADOW_SHOT).mcdw$getIsEnabled()) {
    //        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
    //            // For loop creates first 3 levels of enchanted books
    //            for (int i = 1; i <= getMaxLevel(); i++)
    //                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
    //        });
    //    }
    //}

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem().equals(McdwCrossbowItemRegistry.CROSSBOW_SHADOW_CROSSBOW) || stack.getItem().equals(McdwCrossbowItemRegistry.CROSSBOW_VEILED_CROSSBOW);
    }

}