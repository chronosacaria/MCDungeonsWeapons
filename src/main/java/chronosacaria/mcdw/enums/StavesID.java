package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwStaff;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum StavesID implements McdwWeaponID {
    STAFF_BATTLESTAFF,
    STAFF_BATTLESTAFF_OF_TERROR,
    STAFF_GROWING_STAFF;

    public static HashMap<StavesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.stavesEnabled;
    }

    public static EnumMap<StavesID, McdwStaff> getItemsEnum() {
        return ItemsInit.staffItems;
    }

    public static HashMap<StavesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.staffSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwStaff getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}