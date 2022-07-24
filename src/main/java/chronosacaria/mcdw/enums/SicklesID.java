package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSickle;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum SicklesID implements McdwWeaponID {
    SICKLE_LAST_LAUGH_GOLD,
    SICKLE_LAST_LAUGH_SILVER,
    SICKLE_NIGHTMARES_BITE,
    SICKLE_SICKLE;

    public HashMap<SicklesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.sicklesEnabled;
    }

    public EnumMap<SicklesID, McdwSickle> getItemsEnum() {
        return ItemsInit.sickleItems;
    }

    public HashMap<SicklesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.sickleSpawnRates;
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