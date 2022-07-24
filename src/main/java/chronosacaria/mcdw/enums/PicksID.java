package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwPick;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum PicksID implements McdwWeaponID {
    PICK_DIAMOND_PICKAXE_VAR,
    PICK_HAILING_PINNACLE,
    PICK_HOWLING_PICK,
    PICK_MOUNTAINEER_PICK;

    public static HashMap<PicksID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.picksEnabled;
    }

    public static EnumMap<PicksID, McdwPick> getItemsEnum() {
        return ItemsInit.pickItems;
    }

    public static HashMap<PicksID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.pickSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwPick getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}