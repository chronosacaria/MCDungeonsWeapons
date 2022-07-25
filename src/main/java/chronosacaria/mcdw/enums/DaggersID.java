package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

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

    public static HashMap<DaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.daggersEnabled;
    }

    public static EnumMap<DaggersID, McdwDagger> getItemsEnum() {
        return ItemsInit.daggerItems;
    }

    public static HashMap<DaggersID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.daggerSpawnRates;
    }

    public static HashMap<DaggersID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.daggerStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwDagger getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    public MeleeWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public McdwDagger makeWeapon() {
        McdwDagger mcdwDagger = new McdwDagger(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwDagger);
        return mcdwDagger;
    }
}