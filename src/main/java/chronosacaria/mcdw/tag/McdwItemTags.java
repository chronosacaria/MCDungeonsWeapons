package chronosacaria.mcdw.tag;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.mixin.AccessorItemTags;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class McdwItemTags {
    public static final Tag.Identified<Item> SPEARS = get("spears");

    private static Tag.Identified<Item> get(String id) {
        return AccessorItemTags.callRegister(Mcdw.MOD_ID + ":" + id);
    }

    public static void load(){

    }
}
