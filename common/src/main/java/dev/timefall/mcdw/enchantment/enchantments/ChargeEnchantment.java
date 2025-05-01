/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.enchantment.types.RangedEnchantment;

/*
 * Effects Needed:
 * HEALTH_BASED_DAMAGE -> EnchantmentValueEffectType
 * would be like: ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffectType>>> DAMAGE
 */


/*
 * Effects Present:
 * ApplyMobEffectEnchantmentEffectType -> for charge application itself;
 * would be like: BANE_OF_ARTHROPODS
 * RandomChanceLootCondition
 */

public class ChargeEnchantment extends RangedEnchantment {
    public ChargeEnchantment(Properties properties) {
        super(properties);
    }

    //public ChargeEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.CHARGE).mcdw$getIsEnabled()) {
    //        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
    //            // For loop creates first 3 levels of enchanted books
    //            for (int i = 1; i <= getMaxLevel(); i++)
    //                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
    //        });
    //    }
    //}

}
