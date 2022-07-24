package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum DaggersID implements McdwWeaponID {
    DAGGER_BACKSTABBER,
    DAGGER_CHILL_GALE_KNIFE,
    DAGGER_DAGGER,
    DAGGER_FANGS_OF_FROST,
    DAGGER_MOON,
    DAGGER_RESOLUTE_TEMPEST_KNIFE,
    DAGGER_SHEAR_DAGGER,
    DAGGER_SWIFT_STRIKER,
    DAGGER_TEMPEST_KNIFE,
    DAGGER_THE_BEGINNING,
    DAGGER_THE_END,
    DAGGER_VOID_TOUCHED_BLADE;

    public HashMap<DaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.daggersEnabled;
    }

    public EnumMap<DaggersID, McdwDagger> getItemsEnum() {
        return ItemsInit.daggerItems;
    }

    public HashMap<DaggersID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.daggerSpawnRates;
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