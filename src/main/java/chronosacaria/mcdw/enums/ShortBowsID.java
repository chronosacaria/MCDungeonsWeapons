package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwShortBow;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum ShortBowsID implements McdwWeaponID {
    BOW_LOVE_SPELL_BOW,
    BOW_MECHANICAL_SHORTBOW,
    BOW_PURPLE_STORM,
    BOW_SHORTBOW;

    public HashMap<ShortBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.shortBowsEnabled;
    }

    public EnumMap<ShortBowsID, McdwShortBow> getItemsEnum() {
        return ItemsInit.shortBowItems;
    }

    public HashMap<ShortBowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.shortBowSpawnRates;
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