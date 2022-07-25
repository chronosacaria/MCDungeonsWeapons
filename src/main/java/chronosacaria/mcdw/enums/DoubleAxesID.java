package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum DoubleAxesID implements McdwWeaponID {
    AXE_CURSED,
    AXE_DOUBLE,
    AXE_WHIRLWIND;

    public static HashMap<DoubleAxesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled;
    }

    public static EnumMap<DoubleAxesID, McdwDoubleAxe> getItemsEnum() {
        return ItemsInit.doubleAxeItems;
    }

    public static HashMap<DoubleAxesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates;
    }

    public static HashMap<DoubleAxesID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.doubleAxeStats;
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

    public MeleeWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public McdwDoubleAxe makeWeapon() {
        McdwDoubleAxe mcdwDoubleAxe = new McdwDoubleAxe(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwDoubleAxe);
        return mcdwDoubleAxe;
    }
}