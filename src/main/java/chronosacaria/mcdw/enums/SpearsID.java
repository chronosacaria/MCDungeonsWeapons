package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SpearsID implements McdwWeaponID {
    SPEAR_SPEAR,
    SPEAR_WHISPERING_SPEAR,
    SPEAR_FORTUNE;

    public static HashMap<SpearsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.spearsEnabled;
    }

    public static EnumMap<SpearsID, McdwSpear> getItemsEnum() {
        return ItemsInit.spearItems;
    }

    public static HashMap<SpearsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.spearSpawnRates;
    }

    public static HashMap<SpearsID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.spearStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSpear getItem() {
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
