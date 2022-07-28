package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.configs.stats.RangedWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum LongBowsID implements McdwWeaponID {
    BOW_GUARDIAN_BOW,
    BOW_LONGBOW,
    BOW_RED_SNAKE;

    public static HashMap<LongBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.longBowsEnabled;
    }

    public static EnumMap<LongBowsID, McdwLongBow> getItemsEnum() {
        return ItemsInit.longBowItems;
    }

    public static HashMap<LongBowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.longBowSpawnRates;
    }

    public static HashMap<LongBowsID, RangedWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.longBowStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwLongBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    public RangedWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }
}