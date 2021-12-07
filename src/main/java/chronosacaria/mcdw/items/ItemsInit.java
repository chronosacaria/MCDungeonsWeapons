package chronosacaria.mcdw.items;

import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enums.*;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;

import static chronosacaria.mcdw.Mcdw.*;

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

    public static void init() {
        for (SwordsID swordsID : SwordsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(swordsID))
                continue;

            McdwSword weapon;

            switch (swordsID) {
                case SWORD_CLAYMORE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).material),
                                    CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).damage,
                                    CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).attackSpeed);
                    break;
                case SWORD_BROADSWORD:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).attackSpeed);
                    break;
                case SWORD_FROST_SLAYER:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).attackSpeed);
                    break;
                case SWORD_HEARTSTEALER:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).attackSpeed);
                    break;
                case SWORD_GREAT_AXEBLADE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).attackSpeed);
                    break;
                case SWORD_RAPIER:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).attackSpeed);
                    break;
                case SWORD_BEESTINGER:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).attackSpeed);
                    break;
                case SWORD_FREEZING_FOIL:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).attackSpeed);
                    break;
                case SWORD_CUTLASS:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).attackSpeed);
                    break;
                case SWORD_NAMELESS_BLADE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).attackSpeed);
                    break;
                case SWORD_DANCERS_SWORD:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).attackSpeed);
                    break;
                case SWORD_KATANA:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).attackSpeed);
                    break;
                case SWORD_MASTERS_KATANA:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).attackSpeed);
                    break;
                case SWORD_DARK_KATANA:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).attackSpeed);
                    break;
                case SWORD_IRON_SWORD_VAR:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).attackSpeed);
                    break;
                case SWORD_DIAMOND_SWORD_VAR:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).attackSpeed);
                    break;
                case SWORD_HAWKBRAND:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).attackSpeed);
                    break;
                case SWORD_SINISTER:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).attackSpeed);
                    break;
                case SWORD_BROKEN_SAWBLADE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).attackSpeed);
                    break;
                case SWORD_MECHANIZED_SAWBLADE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).attackSpeed);
                    break;
                case SWORD_CORAL_BLADE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).attackSpeed);
                    break;
                case SWORD_SPONGE_STRIKER:
                    weapon = new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).attackSpeed);
                    break;
                case SWORD_OBSIDIAN_CLAYMORE:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).attackSpeed);
                    break;
                case SWORD_THE_STARLESS_NIGHT:
                    weapon =
                            new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).material),
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).attackSpeed);
                    break;
                default:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            swordItems.put(swordsID, weapon);
            registerItem(swordsID.toString().toLowerCase(), weapon);
        }
        for (AxesID axesID : AxesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.axesEnabled.get(axesID))
                continue;

            McdwAxe weapon;

            switch (axesID) {
                case AXE:
                    weapon =
                            new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).material),
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).attackSpeed);
                    break;
                case AXE_FIREBRAND:
                    weapon =
                            new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).material),
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).attackSpeed);
                    break;
                case AXE_HIGHLAND:
                    weapon =
                            new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).material),
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).attackSpeed);
                    break;
                case AXE_ANCHOR:
                    weapon =
                            new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).material),
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).attackSpeed);
                    break;
                case AXE_ENCRUSTED_ANCHOR:
                    weapon =
                            new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).material),
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).attackSpeed);
                    break;
                default:
                    weapon = new McdwAxe(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            axeItems.put(axesID, weapon);
            registerItem(axesID.toString().toLowerCase(), weapon);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled.get(doubleAxesID))
                continue;

            McdwDoubleAxe weapon;

            switch (doubleAxesID) {
                case AXE_DOUBLE:
                    weapon =
                            new McdwDoubleAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_DOUBLE).material),
                                    CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_DOUBLE).damage,
                                    CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_DOUBLE).attackSpeed);
                    break;
                case AXE_CURSED:
                    weapon =
                            new McdwDoubleAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_CURSED).material),
                                    CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_CURSED).damage,
                                    CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_CURSED).attackSpeed);
                    break;
                case AXE_WHIRLWIND:
                    weapon =
                            new McdwDoubleAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_WHIRLWIND).material),
                                    CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_WHIRLWIND).damage,
                                    CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_WHIRLWIND).attackSpeed);
                    break;
                default:
                    weapon = new McdwDoubleAxe(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            doubleAxeItems.put(doubleAxesID, weapon);
            registerItem(doubleAxesID.toString().toLowerCase(), weapon);
        }
        for (DaggersID daggersID : DaggersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(daggersID))
                continue;

            McdwDagger weapon;

            switch (daggersID) {
                case DAGGER_DAGGER:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).attackSpeed);
                    break;
                case DAGGER_FANGS_OF_FROST:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).attackSpeed);
                    break;
                case DAGGER_MOON:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).attackSpeed);
                    break;
                case DAGGER_SHEAR_DAGGER:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).attackSpeed);
                    break;
                case DAGGER_TEMPEST_KNIFE:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).attackSpeed);
                    break;
                case DAGGER_RESOLUTE_TEMPEST_KNIFE:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).attackSpeed);
                    break;
                case DAGGER_CHILL_GALE_KNIFE:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).attackSpeed);
                    break;
                case DAGGER_BACKSTABBER:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).attackSpeed);
                    break;
                case DAGGER_SWIFT_STRIKER:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).attackSpeed);
                    break;
                case DAGGER_VOID_TOUCHED_BLADE:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).attackSpeed);
                    break;
                case DAGGER_THE_BEGINNING:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).attackSpeed);
                    break;
                case DAGGER_THE_END:
                    weapon =
                            new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).material),
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).attackSpeed);
                    break;
                default:
                    weapon = new McdwDagger(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            daggerItems.put(daggersID, weapon);
            registerItem(daggersID.toString().toLowerCase(), weapon);
        }
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled.get(soulDaggersID))
                continue;

            McdwSoulDagger weapon;

            switch (soulDaggersID) {
                case DAGGER_SOUL_KNIFE:
                    weapon =
                            new McdwSoulDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).material),
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).attackSpeed);
                    break;
                case DAGGER_ETERNAL_KNIFE:
                    weapon =
                            new McdwSoulDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).material),
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).attackSpeed);
                    break;
                case SWORD_TRUTHSEEKER:
                    weapon =
                            new McdwSoulDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.SWORD_TRUTHSEEKER).material),
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.SWORD_TRUTHSEEKER).damage,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.SWORD_TRUTHSEEKER).attackSpeed);
                    break;
                default:
                    weapon = new McdwSoulDagger(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            soulDaggerItems.put(soulDaggersID, weapon);
            registerItem(soulDaggersID.toString().toLowerCase(), weapon);
        }
        for (HammersID hammersID : HammersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(hammersID))
                continue;

            McdwHammer weapon;

            switch (hammersID) {
                case HAMMER_GREAT:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).attackSpeed);
                    break;
                case HAMMER_STORMLANDER:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).attackSpeed);
                    break;
                case HAMMER_GRAVITY:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).attackSpeed);
                    break;
                case HAMMER_MACE:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).attackSpeed);
                    break;
                case HAMMER_FLAIL:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).attackSpeed);
                    break;
                case HAMMER_SUNS_GRACE:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).attackSpeed);
                    break;
                case HAMMER_BONECLUB:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).attackSpeed);
                    break;
                case HAMMER_BONE_CUDGEL:
                    weapon =
                            new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONE_CUDGEL).material),
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONE_CUDGEL).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONE_CUDGEL).attackSpeed);
                    break;
                default:
                    weapon = new McdwHammer(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            hammerItems.put(hammersID, weapon);
            registerItem(hammersID.toString().toLowerCase(), weapon);
        }
        for (GauntletsID gauntletsID : GauntletsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.gauntletsEnabled.get(gauntletsID))
                continue;

            McdwGauntlet weapon;

            switch (gauntletsID) {
                case GAUNTLET_GAUNTLET:
                    weapon =
                            new McdwGauntlet(stringToMaterial(CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).material),
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).damage,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).attackSpeed);
                    break;
                case GAUNTLET_MAULERS:
                    weapon =
                            new McdwGauntlet(stringToMaterial(CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).material),
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).damage,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).attackSpeed);
                    break;
                case GAUNTLET_SOUL_FISTS:
                    weapon =
                            new McdwGauntlet(stringToMaterial(CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_SOUL_FISTS).material),
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_SOUL_FISTS).damage,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_SOUL_FISTS).attackSpeed);
                    break;
                default:
                    weapon = new McdwGauntlet(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            gauntletItems.put(gauntletsID, weapon);
            registerItem(gauntletsID.toString().toLowerCase(), weapon);
        }
        for (SicklesID sicklesID : SicklesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(sicklesID))
                continue;

            McdwSickle weapon;

            switch (sicklesID) {
                case SICKLE_SICKLE:
                    weapon =
                            new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).material),
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).attackSpeed);
                    break;
                case SICKLE_NIGHTMARES_BITE:
                    weapon =
                            new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).material),
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).attackSpeed);
                    break;
                case SICKLE_LAST_LAUGH_GOLD:
                    weapon =
                            new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).material),
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).attackSpeed);
                    break;
                case SICKLE_LAST_LAUGH_SILVER:
                    weapon =
                            new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_SILVER).material),
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_SILVER).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_SILVER).attackSpeed);
                    break;
                default:
                    weapon = new McdwSickle(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            sickleItems.put(sicklesID, weapon);
            registerItem(sicklesID.toString().toLowerCase(), weapon);
        }
        for (ScythesID scythesID : ScythesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.scythesEnabled.get(scythesID))
                continue;

            McdwScythe weapon;

            switch (scythesID) {
                case SICKLE_JAILORS_SCYTHE:
                    weapon =
                            new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).material),
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).attackSpeed);
                    break;
                case SICKLE_SOUL_SCYTHE:
                    weapon =
                            new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).material),
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).attackSpeed);
                    break;
                case SICKLE_FROST_SCYTHE:
                    weapon =
                            new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).material),
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).attackSpeed);
                    break;
                case SICKLE_SKULL_SCYTHE:
                    weapon =
                            new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SKULL_SCYTHE).material),
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SKULL_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SKULL_SCYTHE).attackSpeed);
                    break;
                default:
                    weapon = new McdwScythe(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            scytheItems.put(scythesID, weapon);
            registerItem(scythesID.toString().toLowerCase(), weapon);
        }
        for (PicksID picksID : PicksID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.picksEnabled.get(picksID))
                continue;

            McdwPick weapon;

            switch (picksID) {
                case PICK_DIAMOND_PICKAXE_VAR:
                    weapon =
                            new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).material),
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).attackSpeed);
                    break;
                case PICK_MOUNTAINEER_PICK:
                    weapon =
                            new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).material),
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).attackSpeed);
                    break;
                case PICK_HOWLING_PICK:
                    weapon =
                            new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).material),
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).attackSpeed);
                    break;
                case PICK_HAILING_PINNACLE:
                    weapon =
                            new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HAILING_PINNACLE).material),
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HAILING_PINNACLE).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HAILING_PINNACLE).attackSpeed);
                    break;
                default:
                    weapon = new McdwPick(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            pickItems.put(picksID, weapon);
            registerItem(picksID.toString().toLowerCase(), weapon);
        }
        for (GlaivesID glaivesID : GlaivesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(glaivesID))
                continue;

            McdwGlaive weapon;

            switch (glaivesID) {
                case SPEAR_GLAIVE:
                    weapon =
                            new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).material),
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).attackSpeed);
                    break;
                case SPEAR_GRAVE_BANE:
                    weapon =
                            new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).material),
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).attackSpeed);
                    break;
                case SPEAR_VENOM_GLAIVE:
                    weapon =
                            new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).material),
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).attackSpeed);
                    break;
                case SPEAR_CACKLING_BROOM:
                    weapon =
                            new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_CACKLING_BROOM).material),
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_CACKLING_BROOM).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_CACKLING_BROOM).attackSpeed);
                    break;
                default:
                    weapon = new McdwGlaive(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            glaiveItems.put(glaivesID, weapon);
            registerItem(glaivesID.toString().toLowerCase(), weapon);
        }
        for (SpearsID spearsID : SpearsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.spearsEnabled.get(spearsID))
                continue;

            McdwSpear weapon;

            switch (spearsID) {
                case SPEAR_SPEAR:
                    weapon =
                            new McdwSpear(stringToMaterial(CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).material),
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).damage,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).attackSpeed);
                    break;
                case SPEAR_WHISPERING_SPEAR:
                    weapon =
                            new McdwSpear(stringToMaterial(CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).material),
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).damage,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).attackSpeed);
                    break;
                case SPEAR_FORTUNE:
                    weapon =
                            new McdwSpear(stringToMaterial(CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_FORTUNE).material),
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_FORTUNE).damage,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_FORTUNE).attackSpeed);
                    break;
                default:
                    weapon = new McdwSpear(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            spearItems.put(spearsID, weapon);
            registerItem(spearsID.toString().toLowerCase(), weapon);
        }
        for (StavesID stavesID : StavesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.stavesEnabled.get(stavesID))
                continue;

            McdwStaff weapon;

            switch (stavesID) {
                case STAFF_BATTLESTAFF:
                    weapon =
                            new McdwStaff(stringToMaterial(CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).material),
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).damage,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).attackSpeed);
                    break;
                case STAFF_GROWING_STAFF:
                    weapon =
                            new McdwStaff(stringToMaterial(CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).material),
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).damage,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).attackSpeed);
                    break;
                case STAFF_BATTLESTAFF_OF_TERROR:
                    weapon =
                            new McdwStaff(stringToMaterial(CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR).material),
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR).damage,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR).attackSpeed);
                    break;
                default:
                    weapon = new McdwStaff(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            staffItems.put(stavesID, weapon);
            registerItem(stavesID.toString().toLowerCase(), weapon);
        }
        for (WhipsID whipsID : WhipsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.whipsEnabled.get(whipsID))
                continue;

            McdwWhip weapon;

            switch (whipsID) {
                case WHIP_WHIP:
                    weapon =
                            new McdwWhip(stringToMaterial(CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).material),
                                    CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).damage,
                                    CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).attackSpeed);
                    break;
                case WHIP_VINE_WHIP:
                    weapon =
                            new McdwWhip(stringToMaterial(CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_VINE_WHIP).material),
                                    CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_VINE_WHIP).damage,
                                    CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_VINE_WHIP).attackSpeed);
                    break;
                default:
                    weapon = new McdwWhip(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            whipItems.put(whipsID, weapon);
            registerItem(whipsID.toString().toLowerCase(), weapon);
        }
        for (BowsID bowsID : BowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(bowsID))
                continue;

            McdwBow weapon;

            switch (bowsID) {
                case BOW_ANCIENT_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ANCIENT_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ANCIENT_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ANCIENT_BOW).range);
                    break;
                case BOW_BONEBOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BONEBOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BONEBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BONEBOW).range);
                    break;
                case BOW_LOST_SOULS:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_LOST_SOULS).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_LOST_SOULS).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_LOST_SOULS).range);
                    break;
                case BOW_ELITE_POWER_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ELITE_POWER_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ELITE_POWER_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ELITE_POWER_BOW).range);
                    break;
                case BOW_HAUNTED_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HAUNTED_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HAUNTED_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HAUNTED_BOW).range);
                    break;
                case BOW_HUNTERS_PROMISE:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTERS_PROMISE).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTERS_PROMISE).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTERS_PROMISE).range);
                    break;
                case BOW_HUNTING_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTING_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTING_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTING_BOW).range);
                    break;
                case BOW_MASTERS_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_MASTERS_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_MASTERS_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_MASTERS_BOW).range);
                    break;
                case BOW_NOCTURNAL_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_NOCTURNAL_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_NOCTURNAL_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_NOCTURNAL_BOW).range);
                    break;
                case BOW_POWER_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_POWER_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_POWER_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_POWER_BOW).range);
                    break;
                case BOW_SABREWING:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SABREWING).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SABREWING).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SABREWING).range);
                    break;
                case BOW_SNOW_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SNOW_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SNOW_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SNOW_BOW).range);
                    break;
                case BOW_SOUL_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SOUL_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SOUL_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SOUL_BOW).range);
                    break;
                case BOW_GREEN_MENACE:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_GREEN_MENACE).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_GREEN_MENACE).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_GREEN_MENACE).range);
                    break;
                case BOW_PINK_SCOUNDREL:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PINK_SCOUNDREL).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PINK_SCOUNDREL).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PINK_SCOUNDREL).range);
                    break;
                case BOW_TRICKBOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TRICKBOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TRICKBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TRICKBOW).range);
                    break;
                case BOW_TWIN_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWIN_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWIN_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWIN_BOW).range);
                    break;
                case BOW_WINTERS_TOUCH:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WINTERS_TOUCH).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WINTERS_TOUCH).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WINTERS_TOUCH).range);
                    break;
                case BOW_SHIVERING_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SHIVERING_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SHIVERING_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SHIVERING_BOW).range);
                    break;
                case BOW_WIND_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WIND_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WIND_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WIND_BOW).range);
                    break;
                case BOW_ECHO_OF_THE_VALLEY:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ECHO_OF_THE_VALLEY).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ECHO_OF_THE_VALLEY).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ECHO_OF_THE_VALLEY).range);
                    break;
                case BOW_BURST_GALE_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BURST_GALE_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BURST_GALE_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BURST_GALE_BOW).range);
                    break;
                case BOW_TWISTING_VINE_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWISTING_VINE_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWISTING_VINE_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWISTING_VINE_BOW).range);
                    break;
                case BOW_WEEPING_VINE_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEEPING_VINE_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEEPING_VINE_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEEPING_VINE_BOW).range);
                    break;
                case BOW_BUBBLE_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BOW).range);
                    break;
                case BOW_BUBBLE_BURSTER:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BURSTER).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BURSTER).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BURSTER).range);
                    break;
                case BOW_VOID_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_VOID_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_VOID_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_VOID_BOW).range);
                    break;
                case BOW_CALL_OF_THE_VOID:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_CALL_OF_THE_VOID).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_CALL_OF_THE_VOID).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_CALL_OF_THE_VOID).range);
                    break;
                case BOW_PHANTOM_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PHANTOM_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PHANTOM_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PHANTOM_BOW).range);
                    break;
                case BOW_WEB_BOW:
                    weapon =
                            new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEB_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEB_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEB_BOW).range);
                    break;
                default:
                    weapon = new McdwBow(ToolMaterials.IRON,
                            0.0f,
                            0.0f);
            }

            bowItems.put(bowsID, weapon);
            registerItem(bowsID.toString().toLowerCase(), weapon);
        }
        for (ShortBowsID shortBowsID : ShortBowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.shortBowsEnabled.get(shortBowsID))
                continue;

            McdwShortBow weapon;

            switch (shortBowsID) {
                case BOW_SHORTBOW:
                    weapon =
                            new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_SHORTBOW).material),
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_SHORTBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_SHORTBOW).range);
                    break;
                case BOW_LOVE_SPELL_BOW:
                    weapon =
                            new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_LOVE_SPELL_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_LOVE_SPELL_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_LOVE_SPELL_BOW).range);
                    break;
                case BOW_MECHANICAL_SHORTBOW:
                    weapon =
                            new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW).material),
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW).range);
                    break;
                case BOW_PURPLE_STORM:
                    weapon =
                            new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_PURPLE_STORM).material),
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_PURPLE_STORM).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_PURPLE_STORM).range);
                    break;
                default:
                    weapon = new McdwShortBow(ToolMaterials.WOOD,
                            0.0f,
                            0.0f);
            }

            shortBowItems.put(shortBowsID, weapon);
            registerItem(shortBowsID.toString().toLowerCase(), weapon);
        }
        for (LongBowsID longBowsID : LongBowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.longBowsEnabled.get(longBowsID))
                continue;

            McdwLongBow weapon;

            switch (longBowsID) {
                case BOW_LONGBOW:
                    weapon =
                            new McdwLongBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_LONGBOW).material),
                                    CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_LONGBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_LONGBOW).range);
                    break;
                case BOW_GUARDIAN_BOW:
                    weapon =
                            new McdwLongBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_GUARDIAN_BOW).material),
                                    CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_GUARDIAN_BOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_GUARDIAN_BOW).range);
                    break;
                case BOW_RED_SNAKE:
                    weapon =
                            new McdwLongBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_RED_SNAKE).material),
                                    CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_RED_SNAKE).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_RED_SNAKE).range);
                    break;
                default:
                    weapon = new McdwLongBow(ToolMaterials.WOOD,
                            0.0f,
                            0.0f);
            }

            longBowItems.put(longBowsID, weapon);
            registerItem(longBowsID.toString().toLowerCase(), weapon);
        }
        for (CrossbowsID crossbowsID : CrossbowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(crossbowsID))
                continue;

            McdwCrossbow weapon;

            switch (crossbowsID) {
                case CROSSBOW_THE_SLICER:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_THE_SLICER).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_THE_SLICER).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).range);
                    break;
                case CROSSBOW_AZURE_SEEKER:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AZURE_SEEKER).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AZURE_SEEKER).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AZURE_SEEKER).range);
                    break;
                case CROSSBOW_EXPLODING_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW).range);
                    break;
                case CROSSBOW_IMPLODING_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW).range);
                    break;
                case CROSSBOW_FIREBOLT_THROWER:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER).range);
                    break;
                case CROSSBOW_HEAVY_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW).range);
                    break;
                case CROSSBOW_DOOM_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW).range);
                    break;
                case CROSSBOW_SLAYER_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).range);
                    break;
                case CROSSBOW_RAPID_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW).range);
                    break;
                case CROSSBOW_BUTTERFLY_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW).range);
                    break;
                case CROSSBOW_AUTO_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW).range);
                    break;
                case CROSSBOW_SCATTER_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW).range);
                    break;
                case CROSSBOW_HARP_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW).range);
                    break;
                case CROSSBOW_LIGHTNING_HARP_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW).range);
                    break;
                case CROSSBOW_SOUL_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW).range);
                    break;
                case CROSSBOW_FERAL_SOUL_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW).range);
                    break;
                case CROSSBOW_VOIDCALLER_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW).range);
                    break;
                case CROSSBOW_DUAL_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW).range);
                    break;
                case CROSSBOW_SPELLBOUND_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW).range);
                    break;
                case CROSSBOW_BABY_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW).range);
                    break;
                case CROSSBOW_BURST_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW).range);
                    break;
                case CROSSBOW_SOUL_HUNTER_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW).range);
                    break;
                case CROSSBOW_CORRUPTED_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW).range);
                    break;
                case CROSSBOW_COG_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_COG_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_COG_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_COG_CROSSBOW).range);
                    break;
                case CROSSBOW_PRIDE_OF_THE_PIGLINS:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS).range);
                    break;
                case CROSSBOW_HARPOON_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW).range);
                    break;
                case CROSSBOW_NAUTICAL_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW).range);
                    break;
                case CROSSBOW_SHADOW_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW).range);
                    break;
                case CROSSBOW_VEILED_CROSSBOW:
                    weapon =
                            new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW).material),
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW).drawSpeed,
                                    CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW).range);
                    break;
                default:
                    weapon = new McdwCrossbow(ToolMaterials.WOOD,
                            0.0f,
                            0.0f);
            }

            crossbowItems.put(crossbowsID, weapon);
            registerItem(crossbowsID.toString().toLowerCase(), weapon);
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, ID(id), item);
    }

    private static ToolMaterial stringToMaterial(String material) {
        switch (material) {
            case "wood":
                return ToolMaterials.WOOD;
            case "stone":
                return ToolMaterials.STONE;
            case "gold":
                return ToolMaterials.GOLD;
            case "diamond":
                return ToolMaterials.DIAMOND;
            case "netherite":
                return ToolMaterials.NETHERITE;
            default:
                return ToolMaterials.IRON;
        }
    }
}
