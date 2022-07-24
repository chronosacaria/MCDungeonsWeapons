package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum CrossbowsID implements McdwWeaponID {
    CROSSBOW_AUTO_CROSSBOW,
    CROSSBOW_AZURE_SEEKER,
    CROSSBOW_BABY_CROSSBOW,
    CROSSBOW_BURST_CROSSBOW,
    CROSSBOW_BUTTERFLY_CROSSBOW,
    CROSSBOW_COG_CROSSBOW,
    CROSSBOW_CORRUPTED_CROSSBOW,
    CROSSBOW_DOOM_CROSSBOW,
    CROSSBOW_DUAL_CROSSBOW,
    CROSSBOW_EXPLODING_CROSSBOW,
    CROSSBOW_FERAL_SOUL_CROSSBOW,
    CROSSBOW_FIREBOLT_THROWER,
    CROSSBOW_HARPOON_CROSSBOW,
    CROSSBOW_HARP_CROSSBOW,
    CROSSBOW_HEAVY_CROSSBOW,
    CROSSBOW_IMPLODING_CROSSBOW,
    CROSSBOW_LIGHTNING_HARP_CROSSBOW,
    CROSSBOW_NAUTICAL_CROSSBOW,
    CROSSBOW_PRIDE_OF_THE_PIGLINS,
    CROSSBOW_RAPID_CROSSBOW,
    CROSSBOW_SCATTER_CROSSBOW,
    CROSSBOW_SHADOW_CROSSBOW,
    CROSSBOW_SLAYER_CROSSBOW,
    CROSSBOW_SOUL_CROSSBOW,
    CROSSBOW_SOUL_HUNTER_CROSSBOW,
    CROSSBOW_SPELLBOUND_CROSSBOW,
    CROSSBOW_THE_SLICER,
    CROSSBOW_VEILED_CROSSBOW,
    CROSSBOW_VOIDCALLER_CROSSBOW;

    public static HashMap<CrossbowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.crossbowsEnabled;
    }

    public static EnumMap<CrossbowsID, McdwCrossbow> getItemsEnum() {
        return ItemsInit.crossbowItems;
    }

    public static HashMap<CrossbowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.crossbowSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwCrossbow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}