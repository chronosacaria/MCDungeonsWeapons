package chronosacaria.mcdw.enums;

import net.minecraft.item.Item;

public interface McdwWeaponID {

    Boolean isEnabled();

    Item getItem();

    Float getItemSpawnRate();
}
