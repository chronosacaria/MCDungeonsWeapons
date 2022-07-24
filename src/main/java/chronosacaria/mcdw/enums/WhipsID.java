package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwWhip;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum WhipsID implements McdwWeaponID {
    WHIP_VINE_WHIP,
    WHIP_WHIP;

    public HashMap<WhipsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.whipsEnabled;
    }

    public EnumMap<WhipsID, McdwWhip> getItemsEnum() {
        return ItemsInit.whipItems;
    }

    public HashMap<WhipsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.whipSpawnRates;
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
}