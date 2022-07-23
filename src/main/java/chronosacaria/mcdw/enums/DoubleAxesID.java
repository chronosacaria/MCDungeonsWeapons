package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum DoubleAxesID implements McdwWeaponID {
    AXE_CURSED,
    AXE_DOUBLE,
    AXE_WHIRLWIND;

    public EnumMap<? extends Enum<?>, ? extends Item> getItemsEnum() {
        return ItemsInit.doubleAxeItems;
    }

    public HashMap<? extends Enum<?>, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.doubleAxeSpawnRates;
    }

    public HashMap<? extends Enum<?>, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled;
    }
}