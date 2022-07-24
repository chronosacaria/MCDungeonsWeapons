package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum AxesID implements McdwWeaponID {
    AXE_ANCHOR,
    AXE_AXE,
    AXE_ENCRUSTED_ANCHOR,
    AXE_FIREBRAND,
    AXE_HIGHLAND;

    public HashMap<AxesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.axesEnabled;
    }

    public EnumMap<AxesID, McdwAxe> getItemsEnum() {
        return ItemsInit.axeItems;
    }

    public HashMap<AxesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.axeSpawnRates;
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