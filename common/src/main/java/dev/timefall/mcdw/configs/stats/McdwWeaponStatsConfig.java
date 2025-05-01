/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */


package dev.timefall.mcdw.configs.stats;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.configs.stats.item_sections.*;
import me.fzzyhmstrs.fzzy_config.annotations.IgnoreVisibility;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.config.Config;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

@SuppressWarnings("FieldMayBeFinal")
@IgnoreVisibility
public class McdwWeaponStatsConfig extends Config {

    public static final McdwWeaponStatsConfig CONFIG = ConfigApi.registerAndLoadConfig(McdwWeaponStatsConfig::new);

    public McdwWeaponStatsConfig() {
        super(Mcdw.ID("mcdw_weapon_stats_config"));
    }

    @SuppressWarnings("CanBeFinal")
    transient private LinkedHashMap<Identifier, Supplier<Boolean>> itemEnabledMap = new LinkedHashMap<>();

    @ApiStatus.Internal
    public void registerItemEnableCheck(Identifier id, Supplier<Boolean> checker){
        itemEnabledMap.put(id,checker);
    }

    public boolean isItemEnabled(Identifier id) {
        //supplier returns isEnabled, we negate that for disabled check
        return itemEnabledMap.getOrDefault(id, () -> false).get();
    }

    @SuppressWarnings("CanBeFinal")
    private McdwAxeItemStats mcdwAxeItemStats = new McdwAxeItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwBowItemStats mcdwBowItemStats = new McdwBowItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwCrossbowItemStats mcdwCrossbowItemStats = new McdwCrossbowItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwDaggerItemStats mcdwDaggerItemStats = new McdwDaggerItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwDoubleAxeItemStats mcdwDoubleAxeItemStats = new McdwDoubleAxeItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwGauntletItemStats mcdwGauntletItemStats = new McdwGauntletItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwGlaiveItemStats mcdwGlaiveItemStats = new McdwGlaiveItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwHammerItemStats mcdwHammerItemStats = new McdwHammerItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwLongbowItemStats mcdwLongbowItemStats = new McdwLongbowItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwPickaxeItemStats mcdwPickaxeItemStats = new McdwPickaxeItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwScytheItemStats mcdwScytheItemStats = new McdwScytheItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwShieldItemStats mcdwShieldItemStats = new McdwShieldItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwShortbowItemStats mcdwShortbowItemStats = new McdwShortbowItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwSickleItemStats mcdwSickleItemStats = new McdwSickleItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwSoulDaggerItemStats mcdwSoulDaggerItemStats = new McdwSoulDaggerItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwSpearItemStats mcdwSpearItemStats = new McdwSpearItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwStaffItemStats mcdwStaffItemStats = new McdwStaffItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwSwordItemStats mcdwSwordItemStats = new McdwSwordItemStats();
    @SuppressWarnings("CanBeFinal")
    private McdwWhipItemStats mcdwWhipItemStats = new McdwWhipItemStats();


    public McdwAxeItemStats getAxeItemStats() {
        return mcdwAxeItemStats;
    }

    public McdwBowItemStats getBowItemStats() {
        return mcdwBowItemStats;
    }

    public McdwCrossbowItemStats getCrossbowItemStats() {
        return mcdwCrossbowItemStats;
    }

    public McdwDaggerItemStats getDaggerItemStats() {
        return mcdwDaggerItemStats;
    }

    public McdwDoubleAxeItemStats getDoubleAxeItemStats() {
        return mcdwDoubleAxeItemStats;
    }

    public McdwGauntletItemStats getGauntletItemStats() {
        return mcdwGauntletItemStats;
    }

    public McdwGlaiveItemStats getGlaiveItemStats() {
        return mcdwGlaiveItemStats;
    }

    public McdwHammerItemStats getHammerItemStats() {
        return mcdwHammerItemStats;
    }

    public McdwLongbowItemStats getLongbowItemStats() {
        return mcdwLongbowItemStats;
    }

    public McdwPickaxeItemStats getPickaxeItemStats() {
        return mcdwPickaxeItemStats;
    }

    public McdwScytheItemStats getScytheItemStats() {
        return mcdwScytheItemStats;
    }

    public McdwShieldItemStats getShieldItemStats() {
        return mcdwShieldItemStats;
    }

    public McdwShortbowItemStats getShortbowItemStats() {
        return mcdwShortbowItemStats;
    }

    public McdwSickleItemStats getSickleItemStats() {
        return mcdwSickleItemStats;
    }

    public McdwSoulDaggerItemStats getSoulDaggerItemStats() {
        return mcdwSoulDaggerItemStats;
    }

    public McdwSpearItemStats getSpearItemStats() {
        return mcdwSpearItemStats;
    }

    public McdwStaffItemStats getStaffItemStats() {
        return mcdwStaffItemStats;
    }

    public McdwSwordItemStats getSwordItemStats() {
        return mcdwSwordItemStats;
    }

    public McdwWhipItemStats getWhipItemStats() {
        return mcdwWhipItemStats;
    }
}