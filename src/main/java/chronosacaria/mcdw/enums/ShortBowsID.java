package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwShortBow;
import chronosacaria.mcdw.configs.stats.RangedWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ShortBowsID implements IMcdwWeaponID {
    BOW_LOVE_SPELL_BOW,
    BOW_MECHANICAL_SHORTBOW,
    BOW_PURPLE_STORM,
    BOW_SHORTBOW;

    public static HashMap<ShortBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.shortBowsEnabled;
    }

    public static EnumMap<ShortBowsID, McdwShortBow> getItemsEnum() {
        return ItemsInit.shortBowItems;
    }

    public static HashMap<ShortBowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.shortBowSpawnRates;
    }

    public static HashMap<ShortBowsID, RangedWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.shortBowStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwShortBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    public RangedWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public McdwShortBow makeWeapon() {
        McdwShortBow mcdwShortBow = new McdwShortBow(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range);

        getItemsEnum().put(this, mcdwShortBow);
        return mcdwShortBow;
    }
}