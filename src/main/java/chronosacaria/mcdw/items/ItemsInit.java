package chronosacaria.mcdw.items;

import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.enums.SwordsID;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;

import static chronosacaria.mcdw.Mcdw.*;

public class ItemsInit {

    public static final EnumMap<SwordsID, McdwSword> swordItems = new EnumMap<>(SwordsID.class);

    public static void init() {
        for (SwordsID swordsID : SwordsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.enableSwords.get(swordsID))
                continue;

            McdwSword mcdwSword;

            switch (swordsID) {
                case SWORD_CLAYMORE:
                    mcdwSword = new McdwSword(CONFIG.mcdwNewStatsConfig.meleeWeaponStats.get(SwordsID.SWORD_CLAYMORE).toolMaterial,
                                    CONFIG.mcdwNewStatsConfig.meleeWeaponStats.get(SwordsID.SWORD_CLAYMORE).damage,
                                    CONFIG.mcdwNewStatsConfig.meleeWeaponStats.get(SwordsID.SWORD_CLAYMORE).attackSpeed);
                    break;
                default:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            swordItems.put(swordsID, mcdwSword);
            registerItem(swordsID.toString().toLowerCase(), mcdwSword);
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, ID(id), item);
    }
}
