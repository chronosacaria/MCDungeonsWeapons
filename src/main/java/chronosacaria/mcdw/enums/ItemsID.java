package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.item.Item;

import java.util.EnumMap;

public enum ItemsID {
    ITEM_BEE_STINGER;

    public static EnumMap<ItemsID, Item> getItemsEnum() {
        return ItemsRegistry.MCDW_ITEMS;
    }

    public Item makeItem(Item.Settings settings) {
        Item item = new Item(settings);
        getItemsEnum().put(this, item);
        return item;
    }

    public Item getItem() {
        return getItemsEnum().get(this);
    }
}
