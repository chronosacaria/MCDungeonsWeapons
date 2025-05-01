/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.configs;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchantment.EnchantmentIds;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedList;
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier;
import net.minecraft.util.Identifier;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwEnchantmentSettingsConfig extends Config {
    public static final McdwEnchantmentSettingsConfig CONFIG = ConfigApi.registerAndLoadConfig(McdwEnchantmentSettingsConfig::new);

    public McdwEnchantmentSettingsConfig() {
        super(Mcdw.ID("mcdw_enchantment_settings_config"));
    }

    @SuppressWarnings("CanBeFinal")
    private ValidatedList<Identifier> disabledEnchantments = ValidatedIdentifier.ofSuppliedList(() -> EnchantmentIds.ENCHANTMENT_IDS).toList();

    public boolean isEnchantmentEnabled(Identifier id){
        return !disabledEnchantments.contains(id);
    }

}