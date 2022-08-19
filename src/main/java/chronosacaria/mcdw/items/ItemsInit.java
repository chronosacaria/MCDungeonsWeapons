package chronosacaria.mcdw.items;

import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enums.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

import static chronosacaria.mcdw.Mcdw.CONFIG;
import static chronosacaria.mcdw.Mcdw.ID;

public class ItemsInit {

    public static final EnumMap<SwordsID, McdwSword> swordItems = new EnumMap<>(SwordsID.class);
    public static final EnumMap<AxesID, McdwAxe> axeItems = new EnumMap<>(AxesID.class);
    public static final EnumMap<DoubleAxesID, McdwDoubleAxe> doubleAxeItems = new EnumMap<>(DoubleAxesID.class);
    public static final EnumMap<DaggersID, McdwDagger> daggerItems = new EnumMap<>(DaggersID.class);
    public static final EnumMap<SoulDaggersID, McdwSoulDagger> soulDaggerItems = new EnumMap<>(SoulDaggersID.class);
    public static final EnumMap<HammersID, McdwHammer> hammerItems = new EnumMap<>(HammersID.class);
    public static final EnumMap<GauntletsID, McdwGauntlet> gauntletItems = new EnumMap<>(GauntletsID.class);
    public static final EnumMap<SicklesID, McdwSickle> sickleItems = new EnumMap<>(SicklesID.class);
    public static final EnumMap<ScythesID, McdwScythe> scytheItems = new EnumMap<>(ScythesID.class);
    public static final EnumMap<PicksID, McdwPick> pickItems = new EnumMap<>(PicksID.class);
    public static final EnumMap<GlaivesID, McdwGlaive> glaiveItems = new EnumMap<>(GlaivesID.class);
    public static final EnumMap<SpearsID, McdwSpear> spearItems = new EnumMap<>(SpearsID.class);
    public static final EnumMap<StavesID, McdwStaff> staffItems = new EnumMap<>(StavesID.class);
    public static final EnumMap<WhipsID, McdwWhip> whipItems = new EnumMap<>(WhipsID.class);
    public static final EnumMap<BowsID, McdwBow> bowItems = new EnumMap<>(BowsID.class);
    public static final EnumMap<ShortbowsID, McdwShortbow> shortbowItems = new EnumMap<>(ShortbowsID.class);
    public static final EnumMap<LongbowsID, McdwLongbow> longbowItems = new EnumMap<>(LongbowsID.class);
    public static final EnumMap<CrossbowsID, McdwCrossbow> crossbowItems = new EnumMap<>(CrossbowsID.class);
    public static final EnumMap<ShieldsID, McdwShield> shieldItems = new EnumMap<>(ShieldsID.class);
    public static final EnumMap<ItemsID, BeeStingerItem> mcdwItems = new EnumMap<>(ItemsID.class);

    public static void init() {
        for (IMcdwWeaponID mcdwWeaponID : IMcdwWeaponID.values()) {
            if (mcdwWeaponID.isEnabled()) {
                Item weapon = mcdwWeaponID.makeWeapon();
                registerItem(mcdwWeaponID.toString().toLowerCase(Locale.ROOT), weapon);
            }
        }

        for (ItemsID itemsID : ItemsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.ITEMS_ENABLED.get(itemsID))
                continue;

            BeeStingerItem beeStingerItem = new BeeStingerItem(new Item.Settings().group(ItemGroup.MISC).maxCount(64));

            mcdwItems.put(itemsID, beeStingerItem);
            registerItem(itemsID.toString().toLowerCase(Locale.ROOT), beeStingerItem);
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, ID(id), item);
    }

    public static ToolMaterial stringToMaterial(String material) {
        return switch (material) {
            case "wood" -> ToolMaterials.WOOD;
            case "stone" -> ToolMaterials.STONE;
            case "gold" -> ToolMaterials.GOLD;
            case "diamond" -> ToolMaterials.DIAMOND;
            case "netherite" -> ToolMaterials.NETHERITE;
            default -> ToolMaterials.IRON;
        };
    }
}