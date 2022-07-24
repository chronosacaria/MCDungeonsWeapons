package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum BowsID implements McdwWeaponID {
    BOW_ANCIENT_BOW,
    BOW_BONEBOW,
    BOW_BUBBLE_BOW,
    BOW_BUBBLE_BURSTER,
    BOW_BURST_GALE_BOW,
    BOW_CALL_OF_THE_VOID,
    BOW_ECHO_OF_THE_VALLEY,
    BOW_ELITE_POWER_BOW,
    BOW_GREEN_MENACE,
    BOW_HAUNTED_BOW,
    BOW_HUNTERS_PROMISE,
    BOW_HUNTING_BOW,
    BOW_LOST_SOULS,
    BOW_MASTERS_BOW,
    BOW_NOCTURNAL_BOW,
    BOW_PHANTOM_BOW,
    BOW_PINK_SCOUNDREL,
    BOW_POWER_BOW,
    BOW_SABREWING,
    BOW_SHIVERING_BOW,
    BOW_SNOW_BOW,
    BOW_SOUL_BOW,
    BOW_TRICKBOW,
    BOW_TWIN_BOW,
    BOW_TWISTING_VINE_BOW,
    BOW_VOID_BOW,
    BOW_WEB_BOW,
    BOW_WEEPING_VINE_BOW,
    BOW_WIND_BOW,
    BOW_WINTERS_TOUCH;

    public static HashMap<BowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.bowsEnabled;
    }

    public static EnumMap<BowsID, McdwBow> getItemsEnum() {
        return ItemsInit.bowItems;
    }

    public static HashMap<BowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.bowSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}