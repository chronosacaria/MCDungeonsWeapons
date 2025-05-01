/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.enchantment.types.DamageBoostEnchantment;
import dev.timefall.mcdw.registries.items.McdwSwordItemRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

/*
 * Effects Needed:
 */

/*
 * Effects Present:
 */

public class SharedPainEnchantment extends Enchantment {
    public SharedPainEnchantment(Properties properties) {
        super(properties);
    }
    //public SharedPainEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SHARED_PAIN).mcdw$getIsEnabled()) {
    //        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
    //            // For loop creates first 3 levels of enchanted books
    //            for (int i = 1; i <= getMaxLevel(); i++)
    //                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
    //        });
    //    }
    //}


    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(McdwSwordItemRegistry.SWORD_THE_STARLESS_NIGHT);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof DamageBoostEnchantment);
    }

}