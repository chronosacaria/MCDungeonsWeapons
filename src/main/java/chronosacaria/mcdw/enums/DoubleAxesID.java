package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum DoubleAxesID implements McdwWeaponID {
    AXE_CURSED,
    AXE_DOUBLE,
    AXE_WHIRLWIND;

    public HashMap<DoubleAxesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled;
    }

    public EnumMap<DoubleAxesID, McdwDoubleAxe> getItemsEnum() {
        return ItemsInit.doubleAxeItems;
    }

    public HashMap<DoubleAxesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwDoubleAxe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}