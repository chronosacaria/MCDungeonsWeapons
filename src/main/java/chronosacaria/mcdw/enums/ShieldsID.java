package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwShield;
import chronosacaria.mcdw.configs.stats.ShieldStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ShieldsID implements IMcdwWeaponID {
    SHIELD_ROYAL_GUARD,
    SHIELD_VANGUARD;

    public static HashMap<ShieldsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.shieldsEnabled;
    }

    public static EnumMap<ShieldsID, McdwShield> getItemsEnum() {
        return ItemsInit.shieldItems;
    }

    public static HashMap<ShieldsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.shieldSpawnRates;
    }

    public static HashMap<ShieldsID, ShieldStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.shieldStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwShield getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    public ShieldStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public McdwShield makeWeapon() {
        McdwShield mcdwShield = new McdwShield(ItemsInit.stringToMaterial(this.getWeaponItemStats().material));

        getItemsEnum().put(this, mcdwShield);
        return mcdwShield;
    }
}
