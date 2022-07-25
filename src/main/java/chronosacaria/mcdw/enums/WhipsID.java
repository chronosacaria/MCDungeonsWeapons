package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwWhip;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum WhipsID implements McdwWeaponID {
    WHIP_VINE_WHIP,
    WHIP_WHIP;

    public static HashMap<WhipsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.whipsEnabled;
    }

    public static EnumMap<WhipsID, McdwWhip> getItemsEnum() {
        return ItemsInit.whipItems;
    }

    public static HashMap<WhipsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.whipSpawnRates;
    }

    public static HashMap<WhipsID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.whipStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwWhip getItem() {
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
    public McdwWhip makeWeapon() {
        McdwWhip mcdwWhip = new McdwWhip(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwWhip);
        return mcdwWhip;
    }
}