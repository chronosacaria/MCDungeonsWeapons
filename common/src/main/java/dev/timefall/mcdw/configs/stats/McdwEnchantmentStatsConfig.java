/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */


package dev.timefall.mcdw.configs.stats;

import dev.timefall.mcdw.ModConstants;
import dev.timefall.mcdw.configs.stats.enchantment_sections.McdwEnchantmentStats;
import kotlin.jvm.functions.Function0;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.config.Config;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwEnchantmentStatsConfig extends Config {

    public static final McdwEnchantmentStatsConfig CONFIG = ConfigApi.registerAndLoadConfig((Function0<? extends McdwEnchantmentStatsConfig>) McdwEnchantmentStatsConfig::new);

    public McdwEnchantmentStatsConfig() {
        super(ModConstants.id("mcdw_enchantment_stats_config"));
    }

    @SuppressWarnings("CanBeFinal")
    private McdwEnchantmentStats mcdwEnchantmentStats = new McdwEnchantmentStats();


    public McdwEnchantmentStats getMcdwEnchantmentStats() {
        return mcdwEnchantmentStats;
    }
}
