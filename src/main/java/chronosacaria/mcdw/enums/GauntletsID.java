package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwGauntlet;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum GauntletsID implements McdwWeaponID {
    GAUNTLET_GAUNTLET,
    GAUNTLET_MAULERS,
    GAUNTLET_SOUL_FISTS;

    public static HashMap<GauntletsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.gauntletsEnabled;
    }

    public static EnumMap<GauntletsID, McdwGauntlet> getItemsEnum() {
        return ItemsInit.gauntletItems;
    }

    public static HashMap<GauntletsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.gauntletSpawnRates;
    }

    public static HashMap<GauntletsID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.gauntletStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwGauntlet getItem() {
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
    public McdwGauntlet makeWeapon() {
        McdwGauntlet mcdwGauntlet = new McdwGauntlet(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwGauntlet);
        return mcdwGauntlet;
    }
}