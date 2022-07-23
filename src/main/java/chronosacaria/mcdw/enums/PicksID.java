package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum PicksID implements McdwWeaponID {
    PICK_DIAMOND_PICKAXE_VAR,
    PICK_HAILING_PINNACLE,
    PICK_HOWLING_PICK,
    PICK_MOUNTAINEER_PICK;

    public EnumMap<? extends Enum<?>, ? extends Item> getItemsEnum() {
        return ItemsInit.pickItems;
    }

    public HashMap<? extends Enum<?>, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.pickSpawnRates;
    }

    public HashMap<? extends Enum<?>, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.picksEnabled;
    }
}