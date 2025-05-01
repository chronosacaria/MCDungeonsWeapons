/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.bases.McdwCustomWeaponItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

/*
 * DONE
 */

/*
 * Effects Needed:
 */

/*
 * Effects Present:
 * MOB_EXPERIENCE component type
 * MultiplyEnchantmentEffectType -> Linear base 1.333333, perLevelAboveFirst 0.33333 based on default 3.0 factor
 */

public class SoulDevourerEnchantment extends Enchantment {
    public SoulDevourerEnchantment(Properties properties) {
        super(properties);
    }
    //public SoulDevourerEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SOUL_DEVOURER).mcdw$getIsEnabled()) {
    //        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
    //            // For loop creates first 3 levels of enchanted books
    //            for (int i = 1; i <= getMaxLevel(); i++)
    //                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
    //        });
    //    }
    //}

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof McdwCustomWeaponItem;
    }

}