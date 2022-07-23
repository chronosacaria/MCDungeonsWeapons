package chronosacaria.mcdw.enums;

import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public interface McdwWeaponID {

    EnumMap<? extends Enum<?>, ? extends Item> getItemsEnum();

    HashMap<? extends Enum<?>, Float> getSpawnRates();

    HashMap<? extends Enum<?>, Boolean> getEnabledItems();
}
