package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSoulDagger;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum SoulDaggersID implements McdwWeaponID {
    DAGGER_ETERNAL_KNIFE,
    DAGGER_SOUL_KNIFE,
    SWORD_TRUTHSEEKER;

    public HashMap<SoulDaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled;
    }

    public EnumMap<SoulDaggersID, McdwSoulDagger> getItemsEnum() {
        return ItemsInit.soulDaggerItems;
    }

    public HashMap<SoulDaggersID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates;
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