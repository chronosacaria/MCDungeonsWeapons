package chronosacaria.mcdw.items;

import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enums.*;
import net.minecraft.item.*;
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
    public static final EnumMap<ShieldsID, McdwShield> shieldItems = new EnumMap<>(ShieldsID.class);
    public static final EnumMap<ItemsID, BeeStingerItem> mcdwItems = new EnumMap<>(ItemsID.class);

    public static void init() {
        for (SwordsID swordsID : SwordsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(swordsID))
                continue;

            McdwSword weapon = switch (swordsID) {
                case SWORD_CLAYMORE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).attackSpeed);
                case SWORD_BROADSWORD -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).attackSpeed);
                case SWORD_FROST_SLAYER -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).attackSpeed);
                case SWORD_HEARTSTEALER -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).attackSpeed);
                case SWORD_GREAT_AXEBLADE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).attackSpeed);
                case SWORD_RAPIER -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).attackSpeed);
                case SWORD_BEESTINGER -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).attackSpeed);
                case SWORD_FREEZING_FOIL -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).attackSpeed);
                case SWORD_CUTLASS -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).attackSpeed);
                case SWORD_NAMELESS_BLADE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).attackSpeed);
                case SWORD_DANCERS_SWORD -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).attackSpeed);
                case SWORD_KATANA -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).attackSpeed);
                case SWORD_MASTERS_KATANA -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).attackSpeed);
                case SWORD_DARK_KATANA -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).attackSpeed);
                case SWORD_IRON_SWORD_VAR -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).attackSpeed);
                case SWORD_DIAMOND_SWORD_VAR -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).attackSpeed);
                case SWORD_HAWKBRAND -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).attackSpeed);
                case SWORD_SINISTER -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).attackSpeed);
                case SWORD_BROKEN_SAWBLADE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).attackSpeed);
                case SWORD_MECHANIZED_SAWBLADE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).attackSpeed);
                case SWORD_CORAL_BLADE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).attackSpeed);
                case SWORD_SPONGE_STRIKER -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).attackSpeed);
                case SWORD_OBSIDIAN_CLAYMORE -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).attackSpeed);
                case SWORD_THE_STARLESS_NIGHT -> new McdwSword(stringToMaterial(CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).material),
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).damage,
                        CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwSword(ToolMaterials.IRON, 0, 0.0f);
            };

            swordItems.put(swordsID, weapon);
            registerItem(swordsID.toString().toLowerCase(), weapon);
        }
        for (AxesID axesID : AxesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.axesEnabled.get(axesID))
                continue;

            McdwAxe weapon = switch (axesID) {
                case AXE -> new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).material),
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).damage,
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).attackSpeed);
                case AXE_FIREBRAND -> new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).material),
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).damage,
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).attackSpeed);
                case AXE_HIGHLAND -> new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).material),
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).damage,
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).attackSpeed);
                case AXE_ANCHOR -> new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).material),
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).damage,
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).attackSpeed);
                case AXE_ENCRUSTED_ANCHOR -> new McdwAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).material),
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).damage,
                        CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwAxe(ToolMaterials.IRON, 0, 0.0f);
            };

            axeItems.put(axesID, weapon);
            registerItem(axesID.toString().toLowerCase(), weapon);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.doubleAxesEnabled.get(doubleAxesID))
                continue;

            McdwDoubleAxe weapon = switch (doubleAxesID) {
                case AXE_DOUBLE -> new McdwDoubleAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_DOUBLE).material),
                        CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_DOUBLE).damage,
                        CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_DOUBLE).attackSpeed);
                case AXE_CURSED -> new McdwDoubleAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_CURSED).material),
                        CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_CURSED).damage,
                        CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_CURSED).attackSpeed);
                case AXE_WHIRLWIND -> new McdwDoubleAxe(stringToMaterial(CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_WHIRLWIND).material),
                        CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_WHIRLWIND).damage,
                        CONFIG.mcdwNewStatsConfig.doubleAxeStats.get(DoubleAxesID.AXE_WHIRLWIND).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwDoubleAxe(ToolMaterials.IRON,
                        0,
                        0.0f);
            };

            doubleAxeItems.put(doubleAxesID, weapon);
            registerItem(doubleAxesID.toString().toLowerCase(), weapon);
        }
        for (DaggersID daggersID : DaggersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.daggersEnabled.get(daggersID))
                continue;

            McdwDagger weapon = switch (daggersID) {
                case DAGGER_DAGGER -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).attackSpeed);
                case DAGGER_FANGS_OF_FROST -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).attackSpeed);
                case DAGGER_MOON -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).attackSpeed);
                case DAGGER_SHEAR_DAGGER -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).attackSpeed);
                case DAGGER_TEMPEST_KNIFE -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).attackSpeed);
                case DAGGER_RESOLUTE_TEMPEST_KNIFE -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).attackSpeed);
                case DAGGER_CHILL_GALE_KNIFE -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).attackSpeed);
                case DAGGER_BACKSTABBER -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).attackSpeed);
                case DAGGER_SWIFT_STRIKER -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).attackSpeed);
                case DAGGER_VOID_TOUCHED_BLADE -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).attackSpeed);
                case DAGGER_THE_BEGINNING -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).attackSpeed);
                case DAGGER_THE_END -> new McdwDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).material),
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).damage,
                        CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwDagger(ToolMaterials.IRON, 0, 0.0f);
            };

            daggerItems.put(daggersID, weapon);
            registerItem(daggersID.toString().toLowerCase(), weapon);
        }
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled.get(soulDaggersID))
                continue;

            McdwSoulDagger weapon = switch (soulDaggersID) {
                case DAGGER_SOUL_KNIFE -> new McdwSoulDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).material),
                        CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).damage,
                        CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).attackSpeed);
                case DAGGER_ETERNAL_KNIFE -> new McdwSoulDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).material),
                        CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).damage,
                        CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).attackSpeed);
                case SWORD_TRUTHSEEKER -> new McdwSoulDagger(stringToMaterial(CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.SWORD_TRUTHSEEKER).material),
                        CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.SWORD_TRUTHSEEKER).damage,
                        CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.SWORD_TRUTHSEEKER).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwSoulDagger(ToolMaterials.IRON, 0, 0.0f);
            };

            soulDaggerItems.put(soulDaggersID, weapon);
            registerItem(soulDaggersID.toString().toLowerCase(), weapon);
        }
        for (HammersID hammersID : HammersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.hammersEnabled.get(hammersID))
                continue;

            McdwHammer weapon = switch (hammersID) {
                case HAMMER_GREAT -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).attackSpeed);
                case HAMMER_STORMLANDER -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).attackSpeed);
                case HAMMER_GRAVITY -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).attackSpeed);
                case HAMMER_MACE -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).attackSpeed);
                case HAMMER_FLAIL -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).attackSpeed);
                case HAMMER_SUNS_GRACE -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).attackSpeed);
                case HAMMER_BONECLUB -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).attackSpeed);
                case HAMMER_BONE_CUDGEL -> new McdwHammer(stringToMaterial(CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONE_CUDGEL).material),
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONE_CUDGEL).damage,
                        CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONE_CUDGEL).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwHammer(ToolMaterials.IRON, 0, 0.0f);
            };

            hammerItems.put(hammersID, weapon);
            registerItem(hammersID.toString().toLowerCase(), weapon);
        }
        for (GauntletsID gauntletsID : GauntletsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.gauntletsEnabled.get(gauntletsID))
                continue;

            McdwGauntlet weapon = switch (gauntletsID) {
                case GAUNTLET_GAUNTLET -> new McdwGauntlet(stringToMaterial(CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).material),
                        CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).damage,
                        CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).attackSpeed);
                case GAUNTLET_MAULERS -> new McdwGauntlet(stringToMaterial(CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).material),
                        CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).damage,
                        CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).attackSpeed);
                case GAUNTLET_SOUL_FISTS -> new McdwGauntlet(stringToMaterial(CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_SOUL_FISTS).material),
                        CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_SOUL_FISTS).damage,
                        CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_SOUL_FISTS).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwGauntlet(ToolMaterials.IRON, 0, 0.0f);
            };

            gauntletItems.put(gauntletsID, weapon);
            registerItem(gauntletsID.toString().toLowerCase(), weapon);
        }
        for (SicklesID sicklesID : SicklesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.sicklesEnabled.get(sicklesID))
                continue;

            McdwSickle weapon = switch (sicklesID) {
                case SICKLE_SICKLE -> new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).material),
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).damage,
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).attackSpeed);
                case SICKLE_NIGHTMARES_BITE -> new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).material),
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).damage,
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).attackSpeed);
                case SICKLE_LAST_LAUGH_GOLD -> new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).material),
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).damage,
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).attackSpeed);
                case SICKLE_LAST_LAUGH_SILVER -> new McdwSickle(stringToMaterial(CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_SILVER).material),
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_SILVER).damage,
                        CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_SILVER).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwSickle(ToolMaterials.IRON, 0, 0.0f);
            };

            sickleItems.put(sicklesID, weapon);
            registerItem(sicklesID.toString().toLowerCase(), weapon);
        }
        for (ScythesID scythesID : ScythesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.scythesEnabled.get(scythesID))
                continue;

            McdwScythe weapon = switch (scythesID) {
                case SICKLE_JAILORS_SCYTHE -> new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).material),
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).damage,
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).attackSpeed);
                case SICKLE_SOUL_SCYTHE -> new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).material),
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).damage,
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).attackSpeed);
                case SICKLE_FROST_SCYTHE -> new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).material),
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).damage,
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).attackSpeed);
                case SICKLE_SKULL_SCYTHE -> new McdwScythe(stringToMaterial(CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SKULL_SCYTHE).material),
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SKULL_SCYTHE).damage,
                        CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SKULL_SCYTHE).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwScythe(ToolMaterials.IRON, 0, 0.0f);
            };

            scytheItems.put(scythesID, weapon);
            registerItem(scythesID.toString().toLowerCase(), weapon);
        }
        for (PicksID picksID : PicksID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.picksEnabled.get(picksID))
                continue;

            McdwPick weapon = switch (picksID) {
                case PICK_DIAMOND_PICKAXE_VAR -> new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).material),
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).damage,
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).attackSpeed);
                case PICK_MOUNTAINEER_PICK -> new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).material),
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).damage,
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).attackSpeed);
                case PICK_HOWLING_PICK -> new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).material),
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).damage,
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).attackSpeed);
                case PICK_HAILING_PINNACLE -> new McdwPick(stringToMaterial(CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HAILING_PINNACLE).material),
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HAILING_PINNACLE).damage,
                        CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HAILING_PINNACLE).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwPick(ToolMaterials.IRON, 0, 0.0f);
            };

            pickItems.put(picksID, weapon);
            registerItem(picksID.toString().toLowerCase(), weapon);
        }
        for (GlaivesID glaivesID : GlaivesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.glaivesEnabled.get(glaivesID))
                continue;

            McdwGlaive weapon = switch (glaivesID) {
                case SPEAR_GLAIVE -> new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).material),
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).damage,
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).attackSpeed);
                case SPEAR_GRAVE_BANE -> new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).material),
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).damage,
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).attackSpeed);
                case SPEAR_VENOM_GLAIVE -> new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).material),
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).damage,
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).attackSpeed);
                case SPEAR_CACKLING_BROOM -> new McdwGlaive(stringToMaterial(CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_CACKLING_BROOM).material),
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_CACKLING_BROOM).damage,
                        CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_CACKLING_BROOM).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwGlaive(ToolMaterials.IRON, 0, 0.0f);
            };

            glaiveItems.put(glaivesID, weapon);
            registerItem(glaivesID.toString().toLowerCase(), weapon);
        }
        for (SpearsID spearsID : SpearsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.spearsEnabled.get(spearsID))
                continue;

            McdwSpear weapon = switch (spearsID) {
                case SPEAR_SPEAR -> new McdwSpear(stringToMaterial(CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).material),
                        CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).damage,
                        CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).attackSpeed);
                case SPEAR_WHISPERING_SPEAR -> new McdwSpear(stringToMaterial(CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).material),
                        CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).damage,
                        CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).attackSpeed);
                case SPEAR_FORTUNE -> new McdwSpear(stringToMaterial(CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_FORTUNE).material),
                        CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_FORTUNE).damage,
                        CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_FORTUNE).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwSpear(ToolMaterials.IRON, 0, 0.0f);
            };

            spearItems.put(spearsID, weapon);
            registerItem(spearsID.toString().toLowerCase(), weapon);
        }
        for (StavesID stavesID : StavesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.stavesEnabled.get(stavesID))
                continue;

            McdwStaff weapon = switch (stavesID) {
                case STAFF_BATTLESTAFF -> new McdwStaff(stringToMaterial(CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).material),
                        CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).damage,
                        CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).attackSpeed);
                case STAFF_GROWING_STAFF -> new McdwStaff(stringToMaterial(CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).material),
                        CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).damage,
                        CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).attackSpeed);
                case STAFF_BATTLESTAFF_OF_TERROR -> new McdwStaff(stringToMaterial(CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR).material),
                        CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR).damage,
                        CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF_OF_TERROR).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwStaff(ToolMaterials.IRON, 0, 0.0f);
            };

            staffItems.put(stavesID, weapon);
            registerItem(stavesID.toString().toLowerCase(), weapon);
        }
        for (WhipsID whipsID : WhipsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.whipsEnabled.get(whipsID))
                continue;

            McdwWhip weapon = switch (whipsID) {
                case WHIP_WHIP -> new McdwWhip(stringToMaterial(CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).material),
                        CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).damage,
                        CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).attackSpeed);
                case WHIP_VINE_WHIP -> new McdwWhip(stringToMaterial(CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_VINE_WHIP).material),
                        CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_VINE_WHIP).damage,
                        CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_VINE_WHIP).attackSpeed);
                //noinspection UnnecessaryDefault
                default -> new McdwWhip(ToolMaterials.IRON, 0, 0.0f);
            };

            whipItems.put(whipsID, weapon);
            registerItem(whipsID.toString().toLowerCase(), weapon);
        }
        for (BowsID bowsID : BowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.bowsEnabled.get(bowsID))
                continue;

            McdwBow weapon = switch (bowsID) {
                case BOW_ANCIENT_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ANCIENT_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ANCIENT_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ANCIENT_BOW).range);
                case BOW_BONEBOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BONEBOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BONEBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BONEBOW).range);
                case BOW_LOST_SOULS -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_LOST_SOULS).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_LOST_SOULS).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_LOST_SOULS).range);
                case BOW_ELITE_POWER_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ELITE_POWER_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ELITE_POWER_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ELITE_POWER_BOW).range);
                case BOW_HAUNTED_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HAUNTED_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HAUNTED_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HAUNTED_BOW).range);
                case BOW_HUNTERS_PROMISE -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTERS_PROMISE).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTERS_PROMISE).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTERS_PROMISE).range);
                case BOW_HUNTING_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTING_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTING_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_HUNTING_BOW).range);
                case BOW_MASTERS_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_MASTERS_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_MASTERS_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_MASTERS_BOW).range);
                case BOW_NOCTURNAL_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_NOCTURNAL_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_NOCTURNAL_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_NOCTURNAL_BOW).range);
                case BOW_POWER_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_POWER_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_POWER_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_POWER_BOW).range);
                case BOW_SABREWING -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SABREWING).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SABREWING).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SABREWING).range);
                case BOW_SNOW_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SNOW_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SNOW_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SNOW_BOW).range);
                case BOW_SOUL_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SOUL_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SOUL_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SOUL_BOW).range);
                case BOW_GREEN_MENACE -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_GREEN_MENACE).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_GREEN_MENACE).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_GREEN_MENACE).range);
                case BOW_PINK_SCOUNDREL -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PINK_SCOUNDREL).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PINK_SCOUNDREL).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PINK_SCOUNDREL).range);
                case BOW_TRICKBOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TRICKBOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TRICKBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TRICKBOW).range);
                case BOW_TWIN_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWIN_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWIN_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWIN_BOW).range);
                case BOW_WINTERS_TOUCH -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WINTERS_TOUCH).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WINTERS_TOUCH).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WINTERS_TOUCH).range);
                case BOW_SHIVERING_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SHIVERING_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SHIVERING_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_SHIVERING_BOW).range);
                case BOW_WIND_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WIND_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WIND_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WIND_BOW).range);
                case BOW_ECHO_OF_THE_VALLEY -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ECHO_OF_THE_VALLEY).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ECHO_OF_THE_VALLEY).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_ECHO_OF_THE_VALLEY).range);
                case BOW_BURST_GALE_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BURST_GALE_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BURST_GALE_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BURST_GALE_BOW).range);
                case BOW_TWISTING_VINE_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWISTING_VINE_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWISTING_VINE_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_TWISTING_VINE_BOW).range);
                case BOW_WEEPING_VINE_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEEPING_VINE_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEEPING_VINE_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEEPING_VINE_BOW).range);
                case BOW_BUBBLE_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BOW).range);
                case BOW_BUBBLE_BURSTER -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BURSTER).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BURSTER).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_BUBBLE_BURSTER).range);
                case BOW_VOID_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_VOID_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_VOID_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_VOID_BOW).range);
                case BOW_CALL_OF_THE_VOID -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_CALL_OF_THE_VOID).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_CALL_OF_THE_VOID).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_CALL_OF_THE_VOID).range);
                case BOW_PHANTOM_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PHANTOM_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PHANTOM_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_PHANTOM_BOW).range);
                case BOW_WEB_BOW -> new McdwBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEB_BOW).material),
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEB_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.bowStats.get(BowsID.BOW_WEB_BOW).range);
                //noinspection UnnecessaryDefault
                default -> new McdwBow(ToolMaterials.IRON, 0.0f, 0.0f);
            };

            bowItems.put(bowsID, weapon);
            registerItem(bowsID.toString().toLowerCase(), weapon);
        }
        for (ShortBowsID shortBowsID : ShortBowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.shortBowsEnabled.get(shortBowsID))
                continue;

            McdwShortBow weapon = switch (shortBowsID) {
                case BOW_SHORTBOW -> new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_SHORTBOW).material),
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_SHORTBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_SHORTBOW).range);
                case BOW_LOVE_SPELL_BOW -> new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_LOVE_SPELL_BOW).material),
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_LOVE_SPELL_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_LOVE_SPELL_BOW).range);
                case BOW_MECHANICAL_SHORTBOW -> new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW).material),
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_MECHANICAL_SHORTBOW).range);
                case BOW_PURPLE_STORM -> new McdwShortBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_PURPLE_STORM).material),
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_PURPLE_STORM).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.shortBowStats.get(ShortBowsID.BOW_PURPLE_STORM).range);
                //noinspection UnnecessaryDefault
                default -> new McdwShortBow(ToolMaterials.WOOD, 0.0f, 0.0f);
            };

            shortBowItems.put(shortBowsID, weapon);
            registerItem(shortBowsID.toString().toLowerCase(), weapon);
        }
        for (LongBowsID longBowsID : LongBowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.longBowsEnabled.get(longBowsID))
                continue;

            McdwLongBow weapon = switch (longBowsID) {
                case BOW_LONGBOW -> new McdwLongBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_LONGBOW).material),
                        CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_LONGBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_LONGBOW).range);
                case BOW_GUARDIAN_BOW -> new McdwLongBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_GUARDIAN_BOW).material),
                        CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_GUARDIAN_BOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_GUARDIAN_BOW).range);
                case BOW_RED_SNAKE -> new McdwLongBow(stringToMaterial(CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_RED_SNAKE).material),
                        CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_RED_SNAKE).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.longBowStats.get(LongBowsID.BOW_RED_SNAKE).range);
                //noinspection UnnecessaryDefault
                default -> new McdwLongBow(ToolMaterials.WOOD, 0.0f, 0.0f);
            };

            longBowItems.put(longBowsID, weapon);
            registerItem(longBowsID.toString().toLowerCase(), weapon);
        }
        for (CrossbowsID crossbowsID : CrossbowsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.crossbowsEnabled.get(crossbowsID))
                continue;

            McdwCrossbow weapon = switch (crossbowsID) {
                case CROSSBOW_THE_SLICER -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_THE_SLICER).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_THE_SLICER).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).range);
                case CROSSBOW_AZURE_SEEKER -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AZURE_SEEKER).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AZURE_SEEKER).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AZURE_SEEKER).range);
                case CROSSBOW_EXPLODING_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_EXPLODING_CROSSBOW).range);
                case CROSSBOW_IMPLODING_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_IMPLODING_CROSSBOW).range);
                case CROSSBOW_FIREBOLT_THROWER -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FIREBOLT_THROWER).range);
                case CROSSBOW_HEAVY_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HEAVY_CROSSBOW).range);
                case CROSSBOW_DOOM_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DOOM_CROSSBOW).range);
                case CROSSBOW_SLAYER_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SLAYER_CROSSBOW).range);
                case CROSSBOW_RAPID_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_RAPID_CROSSBOW).range);
                case CROSSBOW_BUTTERFLY_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BUTTERFLY_CROSSBOW).range);
                case CROSSBOW_AUTO_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_AUTO_CROSSBOW).range);
                case CROSSBOW_SCATTER_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SCATTER_CROSSBOW).range);
                case CROSSBOW_HARP_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARP_CROSSBOW).range);
                case CROSSBOW_LIGHTNING_HARP_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_LIGHTNING_HARP_CROSSBOW).range);
                case CROSSBOW_SOUL_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_CROSSBOW).range);
                case CROSSBOW_FERAL_SOUL_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_FERAL_SOUL_CROSSBOW).range);
                case CROSSBOW_VOIDCALLER_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VOIDCALLER_CROSSBOW).range);
                case CROSSBOW_DUAL_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_DUAL_CROSSBOW).range);
                case CROSSBOW_SPELLBOUND_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SPELLBOUND_CROSSBOW).range);
                case CROSSBOW_BABY_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BABY_CROSSBOW).range);
                case CROSSBOW_BURST_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_BURST_CROSSBOW).range);
                case CROSSBOW_SOUL_HUNTER_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SOUL_HUNTER_CROSSBOW).range);
                case CROSSBOW_CORRUPTED_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_CORRUPTED_CROSSBOW).range);
                case CROSSBOW_COG_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_COG_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_COG_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_COG_CROSSBOW).range);
                case CROSSBOW_PRIDE_OF_THE_PIGLINS -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_PRIDE_OF_THE_PIGLINS).range);
                case CROSSBOW_HARPOON_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_HARPOON_CROSSBOW).range);
                case CROSSBOW_NAUTICAL_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_NAUTICAL_CROSSBOW).range);
                case CROSSBOW_SHADOW_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_SHADOW_CROSSBOW).range);
                case CROSSBOW_VEILED_CROSSBOW -> new McdwCrossbow(stringToMaterial(CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW).material),
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW).drawSpeed,
                        CONFIG.mcdwNewStatsConfig.crossbowStats.get(CrossbowsID.CROSSBOW_VEILED_CROSSBOW).range);
                //noinspection UnnecessaryDefault
                default -> new McdwCrossbow(ToolMaterials.WOOD, 0.0f, 0.0f);
            };

            crossbowItems.put(crossbowsID, weapon);
            registerItem(crossbowsID.toString().toLowerCase(), weapon);
        }
        for (ShieldsID shieldsID : ShieldsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.shieldsEnabled.get(shieldsID))
                continue;

            McdwShield shield = switch (shieldsID) {
                case SHIELD_ROYAL_GUARD -> new McdwShield(stringToMaterial(CONFIG.mcdwNewStatsConfig.shieldStats.get(ShieldsID.SHIELD_ROYAL_GUARD).material));
                case SHIELD_VANGUARD -> new McdwShield(stringToMaterial(CONFIG.mcdwNewStatsConfig.shieldStats.get(ShieldsID.SHIELD_VANGUARD).material));
                //noinspection UnnecessaryDefault
                default -> new McdwShield(ToolMaterials.WOOD);
            };

            shieldItems.put(shieldsID, shield);
            registerItem(shieldsID.toString().toLowerCase(), shield);
        }
        for (ItemsID itemsID : ItemsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.itemsEnabled.get(itemsID))
                continue;

            var beeStingerItem = new BeeStingerItem(new Item.Settings().group(ItemGroup.MISC).maxCount(64));

            if (itemsID == ItemsID.ITEM_BEE_STINGER)
                beeStingerItem.asItem();
            else
                new Item(new Item.Settings());

            mcdwItems.put(itemsID, beeStingerItem);
            registerItem(itemsID.toString().toLowerCase(), beeStingerItem);
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
