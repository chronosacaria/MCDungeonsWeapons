package chronosacaria.mcdw.items;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static final BeeStingerItem BEE_STINGER_ITEM = new BeeStingerItem(
            new Item.Settings().group(ItemGroup.MISC).maxCount(64));

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(Mcdw.MOD_ID, "item_bee_stinger"), BEE_STINGER_ITEM);
    }
}