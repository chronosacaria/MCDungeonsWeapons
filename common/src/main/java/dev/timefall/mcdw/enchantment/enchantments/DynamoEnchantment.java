/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.enchantments;

import dev.timefall.mcdw.bases.McdwCustomWeaponItem;
import dev.timefall.mcdw.enchantment.types.DamageBoostEnchantment;
import net.minecraft.item.*;

/*
 * DONE
 */

/*
 * Effects Needed:
 * ON_JUMP -> ComponentType<List<EnchantmentEffectEntry<EnchantmentEntityEffectType>>>
 * MultiplyStatusValueEffect -> deal the damage
 * RemoveMobEffectEnchantmentEntityEffect -> remove the status
 */

/*
 * Effects Present:
 * DAMAGE -> deal the damage
 * POST_ATTACK -> remove the status
 */

public class DynamoEnchantment extends DamageBoostEnchantment {
    public DynamoEnchantment(Properties properties) {
        super(properties);
    }
    //public DynamoEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
    //    super(rarity, enchantmentTarget, equipmentSlots);
    //    if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.DYNAMO).mcdw$getIsEnabled()) {
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