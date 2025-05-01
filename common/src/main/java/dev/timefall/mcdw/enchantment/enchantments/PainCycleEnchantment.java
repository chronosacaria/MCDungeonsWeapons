/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.bases.McdwCustomWeaponItem;
import dev.timefall.mcdw.enchantment.types.DamageBoostEnchantment;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

/*
 * DONE
 */

/*
 * Effects Needed:
 * DAMAGE -> deal the damage
 *    > EntityPropertiesLootCondition -> Check if pain cycle is at stack 5
 *    > MultiplyEnchantmentEffectType -> multiply by constant 2
 */

/*
 * Effects Present:
 *
 * POST_ATTACK -> to apply the pain cycle
 *    > EntityPropertiesLootCondition -> Check if pain cycle is below 5,
 *    > ApplyStackingMobEffectEnchantmentEntityEffect > max 5 > damage event moved to the status itself
 * POST_ATTACK -> to remove the pain cycle
 *    > EntityPropertiesLootCondition -> Check if pain cycle is at stack 5
 *    > RemoveMobEffectEnchantmentEntityEffect > remove pain cycle
 */

public class PainCycleEnchantment extends DamageBoostEnchantment {
    public PainCycleEnchantment(Properties properties) {
        super(properties);
    }

    //public PainCycleEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.PAIN_CYCLE).mcdw$getIsEnabled()){
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