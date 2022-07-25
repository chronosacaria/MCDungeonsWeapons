package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwPick;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

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

    public static HashMap<PicksID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.pickStats;
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

    public MeleeWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public McdwPick makeWeapon() {
        McdwPick mcdwPick = new McdwPick(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwPick);
        return mcdwPick;
    }
}