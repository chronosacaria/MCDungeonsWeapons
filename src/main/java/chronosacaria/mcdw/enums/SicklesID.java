package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSickle;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SicklesID implements McdwWeaponID {
    SICKLE_LAST_LAUGH_GOLD,
    SICKLE_LAST_LAUGH_SILVER,
    SICKLE_NIGHTMARES_BITE,
    SICKLE_SICKLE;

    public static HashMap<SicklesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.sicklesEnabled;
    }

    public static EnumMap<SicklesID, McdwSickle> getItemsEnum() {
        return ItemsInit.sickleItems;
    }

    public static HashMap<SicklesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.sickleSpawnRates;
    }

    public static HashMap<SicklesID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.sickleStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSickle getItem() {
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
    public McdwSickle makeWeapon() {
        McdwSickle mcdwSickle = new McdwSickle(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSickle);
        return mcdwSickle;
    }
}