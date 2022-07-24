package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwScythe;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum ScythesID implements McdwWeaponID {
    SICKLE_FROST_SCYTHE,
    SICKLE_JAILORS_SCYTHE,
    SICKLE_SKULL_SCYTHE,
    SICKLE_SOUL_SCYTHE;

    public HashMap<ScythesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.scythesEnabled;
    }

    public EnumMap<ScythesID, McdwScythe> getItemsEnum() {
        return ItemsInit.scytheItems;
    }

    public HashMap<ScythesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.scytheSpawnRates;
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