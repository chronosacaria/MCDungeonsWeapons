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
    public static final EnumMap<ShortBowsID, McdwShortBow> shortBowItems = new EnumMap<>(ShortBowsID.class);
    public static final EnumMap<LongBowsID, McdwLongBow> longBowItems = new EnumMap<>(LongBowsID.class);
    public static final EnumMap<CrossbowsID, McdwCrossbow> crossbowItems = new EnumMap<>(CrossbowsID.class);
    public static final EnumMap<ShieldsID, McdwShield> shieldItems = new EnumMap<>(ShieldsID.class);
    public static final EnumMap<ItemsID, BeeStingerItem> mcdwItems = new EnumMap<>(ItemsID.class);

    public static void init() {
        for (SwordsID swordsID : SwordsID.values()) {
            if (!swordsID.isEnabled())
                continue;

            McdwSword weapon = new McdwSword(stringToMaterial(swordsID.getWeaponItemStats().material),
                    swordsID.getWeaponItemStats().damage, swordsID.getWeaponItemStats().attackSpeed);

            swordItems.put(swordsID, weapon);
            registerItem(swordsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (AxesID axesID : AxesID.values()) {
            if (!axesID.isEnabled())
                continue;

            McdwAxe weapon = new McdwAxe(stringToMaterial(axesID.getWeaponItemStats().material),
                    axesID.getWeaponItemStats().damage, axesID.getWeaponItemStats().attackSpeed);

            axeItems.put(axesID, weapon);
            registerItem(axesID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            if (!doubleAxesID.isEnabled())
                continue;

            McdwDoubleAxe weapon = new McdwDoubleAxe(stringToMaterial(doubleAxesID.getWeaponItemStats().material),
                    doubleAxesID.getWeaponItemStats().damage, doubleAxesID.getWeaponItemStats().attackSpeed);

            doubleAxeItems.put(doubleAxesID, weapon);
            registerItem(doubleAxesID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (DaggersID daggersID : DaggersID.values()) {
            if (!daggersID.isEnabled())
                continue;

            McdwDagger weapon = new McdwDagger(stringToMaterial(daggersID.getWeaponItemStats().material),
                    daggersID.getWeaponItemStats().damage, daggersID.getWeaponItemStats().attackSpeed);

            daggerItems.put(daggersID, weapon);
            registerItem(daggersID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values()) {
            if (!soulDaggersID.isEnabled())
                continue;

            McdwSoulDagger weapon = new McdwSoulDagger(stringToMaterial(soulDaggersID.getWeaponItemStats().material),
                    soulDaggersID.getWeaponItemStats().damage, soulDaggersID.getWeaponItemStats().attackSpeed);

            soulDaggerItems.put(soulDaggersID, weapon);
            registerItem(soulDaggersID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (HammersID hammersID : HammersID.values()) {
            if (!hammersID.isEnabled())
                continue;

            McdwHammer weapon = new McdwHammer(stringToMaterial(hammersID.getWeaponItemStats().material),
                    hammersID.getWeaponItemStats().damage, hammersID.getWeaponItemStats().attackSpeed);

            hammerItems.put(hammersID, weapon);
            registerItem(hammersID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (GauntletsID gauntletsID : GauntletsID.values()) {
            if (!gauntletsID.isEnabled())
                continue;

            McdwGauntlet weapon = new McdwGauntlet(stringToMaterial(gauntletsID.getWeaponItemStats().material),
                    gauntletsID.getWeaponItemStats().damage, gauntletsID.getWeaponItemStats().attackSpeed);

            gauntletItems.put(gauntletsID, weapon);
            registerItem(gauntletsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (SicklesID sicklesID : SicklesID.values()) {
            if (!sicklesID.isEnabled())
                continue;

            McdwSickle weapon = new McdwSickle(stringToMaterial(sicklesID.getWeaponItemStats().material),
                    sicklesID.getWeaponItemStats().damage, sicklesID.getWeaponItemStats().attackSpeed);

            sickleItems.put(sicklesID, weapon);
            registerItem(sicklesID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (ScythesID scythesID : ScythesID.values()) {
            if (!scythesID.isEnabled())
                continue;

            McdwScythe weapon = new McdwScythe(stringToMaterial(scythesID.getWeaponItemStats().material),
                    scythesID.getWeaponItemStats().damage, scythesID.getWeaponItemStats().attackSpeed);

            scytheItems.put(scythesID, weapon);
            registerItem(scythesID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (PicksID picksID : PicksID.values()) {
            if (!picksID.isEnabled())
                continue;

            McdwPick weapon = new McdwPick(stringToMaterial(picksID.getWeaponItemStats().material),
                    picksID.getWeaponItemStats().damage, picksID.getWeaponItemStats().attackSpeed);

            pickItems.put(picksID, weapon);
            registerItem(picksID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (GlaivesID glaivesID : GlaivesID.values()) {
            if (!glaivesID.isEnabled())
                continue;

            McdwGlaive weapon = new McdwGlaive(stringToMaterial(glaivesID.getWeaponItemStats().material),
                    glaivesID.getWeaponItemStats().damage, glaivesID.getWeaponItemStats().attackSpeed);

            glaiveItems.put(glaivesID, weapon);
            registerItem(glaivesID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (SpearsID spearsID : SpearsID.values()) {
            if (!spearsID.isEnabled())
                continue;

            McdwSpear weapon = new McdwSpear(stringToMaterial(spearsID.getWeaponItemStats().material),
                    spearsID.getWeaponItemStats().damage, spearsID.getWeaponItemStats().attackSpeed);

            spearItems.put(spearsID, weapon);
            registerItem(spearsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (StavesID stavesID : StavesID.values()) {
            if (!stavesID.isEnabled())
                continue;

            McdwStaff weapon = new McdwStaff(stringToMaterial(stavesID.getWeaponItemStats().material),
                    stavesID.getWeaponItemStats().damage, stavesID.getWeaponItemStats().attackSpeed);

            staffItems.put(stavesID, weapon);
            registerItem(stavesID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (WhipsID whipsID : WhipsID.values()) {
            if (!whipsID.isEnabled())
                continue;

            McdwWhip weapon = new McdwWhip(stringToMaterial(whipsID.getWeaponItemStats().material),
                    whipsID.getWeaponItemStats().damage, whipsID.getWeaponItemStats().attackSpeed);

            whipItems.put(whipsID, weapon);
            registerItem(whipsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (BowsID bowsID : BowsID.values()) {
            if (!bowsID.isEnabled())
                continue;

            McdwBow weapon = new McdwBow(stringToMaterial(bowsID.getWeaponItemStats().material),
                    bowsID.getWeaponItemStats().drawSpeed, bowsID.getWeaponItemStats().range);

            bowItems.put(bowsID, weapon);
            registerItem(bowsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (ShortBowsID shortBowsID : ShortBowsID.values()) {
            if (!shortBowsID.isEnabled())
                continue;

            McdwShortBow weapon = new McdwShortBow(stringToMaterial(shortBowsID.getWeaponItemStats().material),
                    shortBowsID.getWeaponItemStats().drawSpeed, shortBowsID.getWeaponItemStats().range);

            shortBowItems.put(shortBowsID, weapon);
            registerItem(shortBowsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (LongBowsID longBowsID : LongBowsID.values()) {
            if (!longBowsID.isEnabled())
                continue;

            McdwLongBow weapon = new McdwLongBow(stringToMaterial(longBowsID.getWeaponItemStats().material),
                    longBowsID.getWeaponItemStats().drawSpeed, longBowsID.getWeaponItemStats().range);

            longBowItems.put(longBowsID, weapon);
            registerItem(longBowsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (CrossbowsID crossbowsID : CrossbowsID.values()) {
            if (!crossbowsID.isEnabled())
                continue;

            McdwCrossbow weapon = new McdwCrossbow(stringToMaterial(crossbowsID.getWeaponItemStats().material),
                    crossbowsID.getWeaponItemStats().drawSpeed, crossbowsID.getWeaponItemStats().range);

            crossbowItems.put(crossbowsID, weapon);
            registerItem(crossbowsID.toString().toLowerCase(Locale.ROOT), weapon);
        }
        for (ShieldsID shieldsID : ShieldsID.values()) {
            if (!shieldsID.isEnabled())
                continue;

            McdwShield shield = new McdwShield(stringToMaterial(shieldsID.getWeaponItemStats().material));

            shieldItems.put(shieldsID, shield);
            registerItem(shieldsID.toString().toLowerCase(Locale.ROOT), shield);
        }
        for (ItemsID itemsID : ItemsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.itemsEnabled.get(itemsID))
                continue;

            BeeStingerItem beeStingerItem = new BeeStingerItem(new Item.Settings().group(ItemGroup.MISC).maxCount(64));

            mcdwItems.put(itemsID, beeStingerItem);
            registerItem(itemsID.toString().toLowerCase(Locale.ROOT), beeStingerItem);
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, ID(id), item);
    }

    private static ToolMaterial stringToMaterial(String material) {
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
