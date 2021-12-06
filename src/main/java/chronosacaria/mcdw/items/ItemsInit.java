package chronosacaria.mcdw.items;

import chronosacaria.mcdw.bases.*;
import chronosacaria.mcdw.enums.*;
import net.minecraft.item.Item;
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

    public static void init() {
        for (SwordsID swordsID : SwordsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.swordsEnabled.get(swordsID))
                continue;

            McdwSword weapon;

            switch (swordsID) {
                case SWORD_CLAYMORE:
                    weapon =
                            new McdwSword(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).damage,
                                    CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).attackSpeed);
                    break;
                case SWORD_BROADSWORD:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).attackSpeed);
                    break;
                case SWORD_FROST_SLAYER:
                    weapon = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).attackSpeed);
                    break;
                case SWORD_HEARTSTEALER:
                    weapon = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).attackSpeed);
                    break;
                case SWORD_GREAT_AXEBLADE:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).attackSpeed);
                    break;
                case SWORD_RAPIER:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).attackSpeed);
                    break;
                case SWORD_BEESTINGER:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).attackSpeed);
                    break;
                case SWORD_FREEZING_FOIL:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).attackSpeed);
                    break;
                case SWORD_CUTLASS:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).attackSpeed);
                    break;
                case SWORD_NAMELESS_BLADE:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).attackSpeed);
                    break;
                case SWORD_DANCERS_SWORD:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).attackSpeed);
                    break;
                case SWORD_KATANA:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).attackSpeed);
                    break;
                case SWORD_MASTERS_KATANA:
                    weapon = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).attackSpeed);
                    break;
                case SWORD_DARK_KATANA:
                    weapon = new McdwSword(ToolMaterials.NETHERITE,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).attackSpeed);
                    break;
                case SWORD_IRON_SWORD_VAR:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).attackSpeed);
                    break;
                case SWORD_DIAMOND_SWORD_VAR:
                    weapon = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).attackSpeed);
                    break;
                case SWORD_HAWKBRAND:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).attackSpeed);
                    break;
                case SWORD_SINISTER:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).attackSpeed);
                    break;
                case SWORD_BROKEN_SAWBLADE:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).attackSpeed);
                    break;
                case SWORD_MECHANIZED_SAWBLADE:
                    weapon = new McdwSword(ToolMaterials.NETHERITE,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).attackSpeed);
                    break;
                case SWORD_CORAL_BLADE:
                    weapon = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).attackSpeed);
                    break;
                case SWORD_SPONGE_STRIKER:
                    weapon = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).attackSpeed);
                    break;
                case SWORD_OBSIDIAN_CLAYMORE:
                    weapon = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).attackSpeed);
                    break;
                case SWORD_THE_STARLESS_NIGHT:
                    weapon = new McdwSword(ToolMaterials.NETHERITE,
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
                            new McdwAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).attackSpeed);
                    break;
                case AXE_FIREBRAND:
                    weapon =
                            new McdwAxe(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).attackSpeed);
                    break;
                case AXE_HIGHLAND:
                    weapon =
                            new McdwAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).attackSpeed);
                    break;
                case AXE_ANCHOR:
                    weapon =
                            new McdwAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).attackSpeed);
                    break;
                case AXE_ENCRUSTED_ANCHOR:
                    weapon =
                            new McdwAxe(ToolMaterials.DIAMOND,
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
                            new McdwDoubleAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).attackSpeed);
                    break;
                case AXE_CURSED:
                    weapon =
                            new McdwDoubleAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).attackSpeed);
                    break;
                case AXE_WHIRLWIND:
                    weapon =
                            new McdwDoubleAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).attackSpeed);
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
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).attackSpeed);
                    break;
                case DAGGER_FANGS_OF_FROST:
                    weapon =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).attackSpeed);
                    break;
                case DAGGER_MOON:
                    weapon =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).attackSpeed);
                    break;
                case DAGGER_SHEAR_DAGGER:
                    weapon =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).attackSpeed);
                    break;
                case DAGGER_TEMPEST_KNIFE:
                    weapon =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).attackSpeed);
                    break;
                case DAGGER_RESOLUTE_TEMPEST_KNIFE:
                    weapon =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).attackSpeed);
                    break;
                case DAGGER_CHILL_GALE_KNIFE:
                    weapon =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).attackSpeed);
                    break;
                case DAGGER_BACKSTABBER:
                    weapon =
                            new McdwDagger(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).attackSpeed);
                    break;
                case DAGGER_SWIFT_STRIKER:
                    weapon =
                            new McdwDagger(ToolMaterials.NETHERITE,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).attackSpeed);
                    break;
                case DAGGER_VOID_TOUCHED_BLADE:
                    weapon =
                            new McdwDagger(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).attackSpeed);
                    break;
                case DAGGER_THE_BEGINNING:
                    weapon =
                            new McdwDagger(ToolMaterials.NETHERITE,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).attackSpeed);
                    break;
                case DAGGER_THE_END:
                    weapon =
                            new McdwDagger(ToolMaterials.NETHERITE,
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
                            new McdwSoulDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_SOUL_KNIFE).attackSpeed);
                    break;
                case DAGGER_ETERNAL_KNIFE:
                    weapon =
                            new McdwSoulDagger(ToolMaterials.NETHERITE,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.soulDaggerStats.get(SoulDaggersID.DAGGER_ETERNAL_KNIFE).attackSpeed);
                    break;
                case SWORD_TRUTHSEEKER:
                    weapon =
                            new McdwSoulDagger(ToolMaterials.NETHERITE,
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
                            new McdwHammer(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GREAT).attackSpeed);
                    break;
                case HAMMER_STORMLANDER:
                    weapon =
                            new McdwHammer(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_STORMLANDER).attackSpeed);
                    break;
                case HAMMER_GRAVITY:
                    weapon =
                            new McdwHammer(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_GRAVITY).attackSpeed);
                    break;
                case HAMMER_MACE:
                    weapon =
                            new McdwHammer(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_MACE).attackSpeed);
                    break;
                case HAMMER_FLAIL:
                    weapon =
                            new McdwHammer(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_FLAIL).attackSpeed);
                    break;
                case HAMMER_SUNS_GRACE:
                    weapon =
                            new McdwHammer(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_SUNS_GRACE).attackSpeed);
                    break;
                case HAMMER_BONECLUB:
                    weapon =
                            new McdwHammer(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).damage,
                                    CONFIG.mcdwNewStatsConfig.hammerStats.get(HammersID.HAMMER_BONECLUB).attackSpeed);
                    break;
                case HAMMER_BONE_CUDGEL:
                    weapon =
                            new McdwHammer(ToolMaterials.DIAMOND,
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
                            new McdwGauntlet(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).damage,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_GAUNTLET).attackSpeed);
                    break;
                case GAUNTLET_MAULERS:
                    weapon =
                            new McdwGauntlet(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).damage,
                                    CONFIG.mcdwNewStatsConfig.gauntletStats.get(GauntletsID.GAUNTLET_MAULERS).attackSpeed);
                    break;
                case GAUNTLET_SOUL_FISTS:
                    weapon =
                            new McdwGauntlet(ToolMaterials.NETHERITE,
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
                            new McdwSickle(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_SICKLE).attackSpeed);
                    break;
                case SICKLE_NIGHTMARES_BITE:
                    weapon =
                            new McdwSickle(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_NIGHTMARES_BITE).attackSpeed);
                    break;
                case SICKLE_LAST_LAUGH_GOLD:
                    weapon =
                            new McdwSickle(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).damage,
                                    CONFIG.mcdwNewStatsConfig.sickleStats.get(SicklesID.SICKLE_LAST_LAUGH_GOLD).attackSpeed);
                    break;
                case SICKLE_LAST_LAUGH_SILVER:
                    weapon =
                            new McdwSickle(ToolMaterials.IRON,
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
                            new McdwScythe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_JAILORS_SCYTHE).attackSpeed);
                    break;
                case SICKLE_SOUL_SCYTHE:
                    weapon =
                            new McdwScythe(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_SOUL_SCYTHE).attackSpeed);
                    break;
                case SICKLE_FROST_SCYTHE:
                    weapon =
                            new McdwScythe(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).damage,
                                    CONFIG.mcdwNewStatsConfig.scytheStats.get(ScythesID.SICKLE_FROST_SCYTHE).attackSpeed);
                    break;
                case SICKLE_SKULL_SCYTHE:
                    weapon =
                            new McdwScythe(ToolMaterials.DIAMOND,
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
                            new McdwPick(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_DIAMOND_PICKAXE_VAR).attackSpeed);
                    break;
                case PICK_MOUNTAINEER_PICK:
                    weapon =
                            new McdwPick(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_MOUNTAINEER_PICK).attackSpeed);
                    break;
                case PICK_HOWLING_PICK:
                    weapon =
                            new McdwPick(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).damage,
                                    CONFIG.mcdwNewStatsConfig.pickStats.get(PicksID.PICK_HOWLING_PICK).attackSpeed);
                    break;
                case PICK_HAILING_PINNACLE:
                    weapon =
                            new McdwPick(ToolMaterials.DIAMOND,
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
                            new McdwGlaive(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GLAIVE).attackSpeed);
                    break;
                case SPEAR_GRAVE_BANE:
                    weapon =
                            new McdwGlaive(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_GRAVE_BANE).attackSpeed);
                    break;
                case SPEAR_VENOM_GLAIVE:
                    weapon =
                            new McdwGlaive(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).damage,
                                    CONFIG.mcdwNewStatsConfig.glaiveStats.get(GlaivesID.SPEAR_VENOM_GLAIVE).attackSpeed);
                    break;
                case SPEAR_CACKLING_BROOM:
                    weapon =
                            new McdwGlaive(ToolMaterials.IRON,
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
                            new McdwSpear(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).damage,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_SPEAR).attackSpeed);
                    break;
                case SPEAR_WHISPERING_SPEAR:
                    weapon =
                            new McdwSpear(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).damage,
                                    CONFIG.mcdwNewStatsConfig.spearStats.get(SpearsID.SPEAR_WHISPERING_SPEAR).attackSpeed);
                    break;
                case SPEAR_FORTUNE:
                    weapon =
                            new McdwSpear(ToolMaterials.IRON,
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
                            new McdwStaff(ToolMaterials.WOOD,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).damage,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_BATTLESTAFF).attackSpeed);
                    break;
                case STAFF_GROWING_STAFF:
                    weapon =
                            new McdwStaff(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).damage,
                                    CONFIG.mcdwNewStatsConfig.staffStats.get(StavesID.STAFF_GROWING_STAFF).attackSpeed);
                    break;
                case STAFF_BATTLESTAFF_OF_TERROR:
                    weapon =
                            new McdwStaff(ToolMaterials.IRON,
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
                            new McdwWhip(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).damage,
                                    CONFIG.mcdwNewStatsConfig.whipStats.get(WhipsID.WHIP_WHIP).attackSpeed);
                    break;
                case WHIP_VINE_WHIP:
                    weapon =
                            new McdwWhip(ToolMaterials.IRON,
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
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, ID(id), item);
    }
}
