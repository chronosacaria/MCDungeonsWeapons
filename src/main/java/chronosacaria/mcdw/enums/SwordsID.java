package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

public enum SwordsID implements McdwWeaponID {
    SWORD_BEESTINGER,
    SWORD_BROADSWORD,
    SWORD_BROKEN_SAWBLADE,
    SWORD_CLAYMORE,
    SWORD_CORAL_BLADE,
    SWORD_CUTLASS,
    SWORD_DANCERS_SWORD,
    SWORD_DARK_KATANA,
    SWORD_DIAMOND_SWORD_VAR,
    SWORD_FREEZING_FOIL,
    SWORD_FROST_SLAYER,
    SWORD_GREAT_AXEBLADE,
    SWORD_HAWKBRAND,
    SWORD_HEARTSTEALER,
    SWORD_IRON_SWORD_VAR,
    SWORD_KATANA,
    SWORD_MASTERS_KATANA,
    SWORD_MECHANIZED_SAWBLADE,
    SWORD_NAMELESS_BLADE,
    SWORD_OBSIDIAN_CLAYMORE,
    SWORD_RAPIER,
    SWORD_SINISTER,
    SWORD_SPONGE_STRIKER,
    SWORD_THE_STARLESS_NIGHT;

    public HashMap<SwordsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.swordsEnabled;
    }

    public EnumMap<SwordsID, McdwSword> getItemsEnum() {
        return ItemsInit.swordItems;
    }

    public HashMap<SwordsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.swordSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSword getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}