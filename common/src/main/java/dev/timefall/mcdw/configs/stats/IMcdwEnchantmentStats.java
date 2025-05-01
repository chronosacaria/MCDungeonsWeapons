/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.configs.stats;

import me.fzzyhmstrs.fzzy_config.util.Walkable;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

@SuppressWarnings({"DuplicatedCode", "JavaExistingMethodCanBeUsed"})
public interface IMcdwEnchantmentStats {

    class EnchantmentStats implements Walkable {
        public boolean isEnabled = true;
        public boolean isAvailableForRandomSelection = true;
        public boolean isAvailableForEnchantedBookOffer = true;
        @ValidatedInt.Restrict(min = 1)
            public int maxLevel = 3;
        @ValidatedInt.Restrict(min = 0)
            public int procWeight = 0;
        @ValidatedFloat.Restrict(min = 0)
            public float effectOffset = 0.0f;
    }

    static EnchantmentStats enchantmentStats(
            boolean isEnabled,
            boolean isAvailableForRandomSelection,
            boolean isAvailableForEnchantedBookOffer,
            int maxLevel) {
        EnchantmentStats stats = new EnchantmentStats();
        stats.isEnabled = isEnabled;
        stats.isAvailableForRandomSelection = isAvailableForRandomSelection;
        stats.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        stats.maxLevel = maxLevel;
        return stats;
    }

    static EnchantmentStats enchantmentStats(
            boolean isEnabled,
            boolean isAvailableForRandomSelection,
            boolean isAvailableForEnchantedBookOffer,
            int maxLevel,
            int procWeight) {
        EnchantmentStats stats = new EnchantmentStats();
        stats.isEnabled = isEnabled;
        stats.isAvailableForRandomSelection = isAvailableForRandomSelection;
        stats.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        stats.maxLevel = maxLevel;
        stats.procWeight = procWeight;
        return stats;
    }

    static EnchantmentStats enchantmentStats(
            boolean isEnabled,
            boolean isAvailableForRandomSelection,
            boolean isAvailableForEnchantedBookOffer,
            int maxLevel,
            float effectOffset) {
        EnchantmentStats stats = new EnchantmentStats();
        stats.isEnabled = isEnabled;
        stats.isAvailableForRandomSelection = isAvailableForRandomSelection;
        stats.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        stats.maxLevel = maxLevel;
        stats.effectOffset = effectOffset;
        return stats;
    }

    static EnchantmentStats enchantmentStats(
            boolean isEnabled,
            boolean isAvailableForRandomSelection,
            boolean isAvailableForEnchantedBookOffer,
            int maxLevel,
            int procWeight,
            float effectOffset)
    {
        EnchantmentStats stats = new EnchantmentStats();
        stats.isEnabled = isEnabled;
        stats.isAvailableForRandomSelection = isAvailableForRandomSelection;
        stats.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        stats.maxLevel = maxLevel;
        stats.procWeight = procWeight;
        stats.effectOffset = effectOffset;
        return stats;
    }
}