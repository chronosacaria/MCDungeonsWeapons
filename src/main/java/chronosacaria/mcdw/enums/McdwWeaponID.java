package chronosacaria.mcdw.enums;

import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public interface McdwWeaponID {

    HashMap<? extends Enum<?>, Boolean> getEnabledItems();

    EnumMap<? extends Enum<?>, ? extends Item> getItemsEnum();

    HashMap<? extends Enum<?>, Float> getSpawnRates();

    Boolean isEnabled();

    Item getItem();

    Float getItemSpawnRate();
}
