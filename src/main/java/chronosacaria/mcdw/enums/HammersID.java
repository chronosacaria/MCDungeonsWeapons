package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwHammer;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum HammersID implements McdwWeaponID {
    HAMMER_BONECLUB,
    HAMMER_BONE_CUDGEL,
    HAMMER_FLAIL,
    HAMMER_GRAVITY,
    HAMMER_GREAT_HAMMER,
    HAMMER_MACE,
    HAMMER_STORMLANDER,
    HAMMER_SUNS_GRACE;

    public HashMap<HammersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.hammersEnabled;
    }

    public EnumMap<HammersID, McdwHammer> getItemsEnum() {
        return ItemsInit.hammerItems;
    }

    public HashMap<HammersID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.hammerSpawnRates;
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