package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

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

    public static HashMap<SwordsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.swordsEnabled;
    }

    public static EnumMap<SwordsID, McdwSword> getItemsEnum() {
        return ItemsInit.swordItems;
    }

    public static HashMap<SwordsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.swordSpawnRates;
    }

    public static HashMap<SwordsID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.swordStats;
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

    public MeleeWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    public McdwSword makeWeapon() {
        McdwSword mcdwSword = new McdwSword(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSword);
        return mcdwSword;
    }
}