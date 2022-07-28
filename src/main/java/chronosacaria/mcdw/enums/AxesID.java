package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum AxesID implements McdwWeaponID {
    AXE_ANCHOR,
    AXE_AXE,
    AXE_ENCRUSTED_ANCHOR,
    AXE_FIREBRAND,
    AXE_HIGHLAND;

    public static HashMap<AxesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.axesEnabled;
    }

    public static EnumMap<AxesID, McdwAxe> getItemsEnum() {
        return ItemsInit.axeItems;
    }

    public static HashMap<AxesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.axeSpawnRates;
    }

    public static HashMap<AxesID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.axeStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwAxe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    public MeleeWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }
}