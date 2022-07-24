package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwStaff;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum StavesID implements McdwWeaponID {
    STAFF_BATTLESTAFF,
    STAFF_BATTLESTAFF_OF_TERROR,
    STAFF_GROWING_STAFF;

    public HashMap<StavesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.stavesEnabled;
    }

    public EnumMap<StavesID, McdwStaff> getItemsEnum() {
        return ItemsInit.staffItems;
    }

    public HashMap<StavesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.staffSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public Item getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}