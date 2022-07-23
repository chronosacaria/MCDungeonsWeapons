package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum ShieldsID implements McdwWeaponID {
    SHIELD_ROYAL_GUARD,
    SHIELD_VANGUARD;

    public EnumMap<? extends Enum<?>, ? extends Item> getItemsEnum() {
        return ItemsInit.shieldItems;
    }

    public HashMap<? extends Enum<?>, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.shieldSpawnRates;
    }

    public HashMap<? extends Enum<?>, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.shieldsEnabled;
    }
}
