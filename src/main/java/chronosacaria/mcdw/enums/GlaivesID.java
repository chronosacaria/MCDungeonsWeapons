package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum GlaivesID implements McdwWeaponID {
    SPEAR_CACKLING_BROOM,
    SPEAR_GLAIVE,
    SPEAR_GRAVE_BANE,
    SPEAR_VENOM_GLAIVE;

    public EnumMap<? extends Enum<?>, ? extends Item> getItemsEnum() {
        return ItemsInit.glaiveItems;
    }

    public HashMap<? extends Enum<?>, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.glaiveSpawnRates;
    }

    public HashMap<? extends Enum<?>, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.glaivesEnabled;
    }
}