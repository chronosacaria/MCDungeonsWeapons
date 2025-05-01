/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.enchantment.types.RangedEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;


/*
 * Effects Needed:
 * ACCELERATE_CHARGE_TIME -> New Effect Type, Custom CODEC, pain: MultiplyStatusValueEffect
 * APPLY_STACKING_MOB_EFFECT -> Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE
 * FORMULA = shots * (0.06 + (0.02 * lvl))
 * BOUNDS = 0..1 > maximum 2x speed up
 */

/*
 * Effects Present:
 */

public class AccelerateEnchantment extends RangedEnchantment {
    public AccelerateEnchantment(Properties properties) {
        super(properties);
    }


    //public AccelerateEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ACCELERATE).mcdw$getIsEnabled()) {
    //        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
    //            // For loop creates first 3 levels of enchanted books
    //            for (int i = 1; i <= getMaxLevel(); i++)
    //                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
    //        });
    //    }
    //}

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof BowItem || stack.getItem() instanceof CrossbowItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof MultishotEnchantment || other instanceof dev.timefall.mcdw.enchantment.enchantments.OverchargeEnchantment);
    }
}