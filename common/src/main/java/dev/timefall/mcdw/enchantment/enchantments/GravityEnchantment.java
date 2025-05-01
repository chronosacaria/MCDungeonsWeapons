/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.bases.McdwCustomWeaponItem;
import dev.timefall.mcdw.enchantment.types.AoEEnchantment;
import net.minecraft.item.*;

/*
 * Effects Needed:
 *  AREA_OF_EFFECT_MULTIPLIER -> MultiplyEnchantmentEffectType -> ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffectType>>>
 */


/*
 * Effects Present:
 *  ApplyMobEffectEnchantmentEffectType -> for chains application itself
 *  RandomChanceLootCondition
 */

public class GravityEnchantment extends AoEEnchantment {
    public GravityEnchantment(Properties properties) {
        super(properties);
    }

    //public GravityEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.GRAVITY).mcdw$getIsEnabled()) {
    //        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
    //            // For loop creates first 3 levels of enchanted books
    //            for (int i = 1; i <= getMaxLevel(); i++)
    //                entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
    //        });
    //    }
    //}

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof McdwCustomWeaponItem
                || stack.getItem() instanceof BowItem || stack.getItem() instanceof CrossbowItem;
    }

}
