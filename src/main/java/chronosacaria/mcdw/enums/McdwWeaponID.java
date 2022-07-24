package chronosacaria.mcdw.enums;

import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public interface McdwWeaponID {

    Boolean isEnabled();

    Item getItem();

    Float getItemSpawnRate();
}
