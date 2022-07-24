package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum LongBowsID implements McdwWeaponID {
    BOW_GUARDIAN_BOW,
    BOW_LONGBOW,
    BOW_RED_SNAKE;

    public HashMap<LongBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.longBowsEnabled;
    }

    public EnumMap<LongBowsID, McdwLongBow> getItemsEnum() {
        return ItemsInit.longBowItems;
    }

    public HashMap<LongBowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.longBowSpawnRates;
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
}