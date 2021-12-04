package chronosacaria.mcdw.items;

import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.bases.McdwDoubleAxe;
import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.enums.AxesID;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.DoubleAxesID;
import chronosacaria.mcdw.enums.SwordsID;
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

    public static void init() {
        for (SwordsID swordsID : SwordsID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.enableSwords.get(swordsID))
                continue;

            McdwSword mcdwSword;

            switch (swordsID) {
                case SWORD_CLAYMORE:
                    mcdwSword =
                            new McdwSword(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).damage,
                                    CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CLAYMORE).attackSpeed);
                    break;
                case SWORD_BROADSWORD:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROADSWORD).attackSpeed);
                    break;
                case SWORD_FROST_SLAYER:
                    mcdwSword = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FROST_SLAYER).attackSpeed);
                    break;
                case SWORD_HEARTSTEALER:
                    mcdwSword = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HEARTSTEALER).attackSpeed);
                    break;
                case SWORD_GREAT_AXEBLADE:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_GREAT_AXEBLADE).attackSpeed);
                    break;
                case SWORD_RAPIER:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_RAPIER).attackSpeed);
                    break;
                case SWORD_BEESTINGER:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BEESTINGER).attackSpeed);
                    break;
                case SWORD_FREEZING_FOIL:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_FREEZING_FOIL).attackSpeed);
                    break;
                case SWORD_CUTLASS:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CUTLASS).attackSpeed);
                    break;
                case SWORD_NAMELESS_BLADE:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_NAMELESS_BLADE).attackSpeed);
                    break;
                case SWORD_DANCERS_SWORD:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DANCERS_SWORD).attackSpeed);
                    break;
                case SWORD_KATANA:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_KATANA).attackSpeed);
                    break;
                case SWORD_MASTERS_KATANA:
                    mcdwSword = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MASTERS_KATANA).attackSpeed);
                    break;
                case SWORD_DARK_KATANA:
                    mcdwSword = new McdwSword(ToolMaterials.NETHERITE,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DARK_KATANA).attackSpeed);
                    break;
                case SWORD_IRON_SWORD_VAR:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_IRON_SWORD_VAR).attackSpeed);
                    break;
                case SWORD_DIAMOND_SWORD_VAR:
                    mcdwSword = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_DIAMOND_SWORD_VAR).attackSpeed);
                    break;
                case SWORD_HAWKBRAND:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_HAWKBRAND).attackSpeed);
                    break;
                case SWORD_SINISTER:
                    mcdwSword = new McdwSword(ToolMaterials.NETHERITE,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SINISTER).attackSpeed);
                    break;
                case SWORD_BROKEN_SAWBLADE:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_BROKEN_SAWBLADE).attackSpeed);
                    break;
                case SWORD_MECHANIZED_SAWBLADE:
                    mcdwSword = new McdwSword(ToolMaterials.NETHERITE,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_MECHANIZED_SAWBLADE).attackSpeed);
                    break;
                case SWORD_CORAL_BLADE:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_CORAL_BLADE).attackSpeed);
                    break;
                case SWORD_SPONGE_STRIKER:
                    mcdwSword = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_SPONGE_STRIKER).attackSpeed);
                    break;
                case SWORD_OBSIDIAN_CLAYMORE:
                    mcdwSword = new McdwSword(ToolMaterials.DIAMOND,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_OBSIDIAN_CLAYMORE).attackSpeed);
                    break;
                case SWORD_THE_STARLESS_NIGHT:
                    mcdwSword = new McdwSword(ToolMaterials.NETHERITE,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).damage,
                            CONFIG.mcdwNewStatsConfig.swordStats.get(SwordsID.SWORD_THE_STARLESS_NIGHT).attackSpeed);
                    break;
                default:
                    mcdwSword = new McdwSword(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            swordItems.put(swordsID, mcdwSword);
            registerItem(swordsID.toString().toLowerCase(), mcdwSword);
        }
        for (AxesID axesID : AxesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.enableAxes.get(axesID))
                continue;

            McdwAxe mcdwAxe;

            switch (axesID) {
                case AXE:
                    mcdwAxe =
                            new McdwAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).attackSpeed);
                    break;
                case AXE_FIREBRAND:
                    mcdwAxe =
                            new McdwAxe(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).attackSpeed);
                    break;
                case AXE_HIGHLAND:
                    mcdwAxe =
                            new McdwAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).attackSpeed);
                    break;
                case AXE_ANCHOR:
                    mcdwAxe =
                            new McdwAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ANCHOR).attackSpeed);
                    break;
                case AXE_ENCRUSTED_ANCHOR:
                    mcdwAxe =
                            new McdwAxe(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_ENCRUSTED_ANCHOR).attackSpeed);
                    break;
                default:
                    mcdwAxe = new McdwAxe(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            axeItems.put(axesID, mcdwAxe);
            registerItem(axesID.toString().toLowerCase(), mcdwAxe);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.enableDoubleAxes.get(doubleAxesID))
                continue;

            McdwDoubleAxe mcdwDoubleAxe;

            switch (doubleAxesID) {
                case AXE_DOUBLE:
                    mcdwDoubleAxe =
                            new McdwDoubleAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE).attackSpeed);
                    break;
                case AXE_CURSED:
                    mcdwDoubleAxe =
                            new McdwDoubleAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_FIREBRAND).attackSpeed);
                    break;
                case AXE_WHIRLWIND:
                    mcdwDoubleAxe =
                            new McdwDoubleAxe(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).damage,
                                    CONFIG.mcdwNewStatsConfig.axeStats.get(AxesID.AXE_HIGHLAND).attackSpeed);
                    break;
                default:
                    mcdwDoubleAxe = new McdwDoubleAxe(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            doubleAxeItems.put(doubleAxesID, mcdwDoubleAxe);
            registerItem(doubleAxesID.toString().toLowerCase(), mcdwDoubleAxe);
        }
        for (DaggersID daggersID : DaggersID.values()) {
            if (!CONFIG.mcdwEnableItemsConfig.enableDaggers.get(daggersID))
                continue;

            McdwDagger mcdwDagger;

            switch (daggersID) {
                case DAGGER_DAGGER:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_DAGGER).attackSpeed);
                    break;
                case DAGGER_FANGS_OF_FROST:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_FANGS_OF_FROST).attackSpeed);
                    break;
                case DAGGER_MOON:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_MOON).attackSpeed);
                    break;
                case DAGGER_SHEAR_DAGGER:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SHEAR_DAGGER).attackSpeed);
                    break;
                case DAGGER_TEMPEST_KNIFE:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_TEMPEST_KNIFE).attackSpeed);
                    break;
                case DAGGER_RESOLUTE_TEMPEST_KNIFE:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE).attackSpeed);
                    break;
                case DAGGER_CHILL_GALE_KNIFE:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.IRON,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_CHILL_GALE_KNIFE).attackSpeed);
                    break;
                case DAGGER_BACKSTABBER:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_BACKSTABBER).attackSpeed);
                    break;
                case DAGGER_SWIFT_STRIKER:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.NETHERITE,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_SWIFT_STRIKER).attackSpeed);
                    break;
                case DAGGER_VOID_TOUCHED_BLADE:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.DIAMOND,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_VOID_TOUCHED_BLADE).attackSpeed);
                    break;
                case DAGGER_THE_BEGINNING:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.NETHERITE,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_BEGINNING).attackSpeed);
                    break;
                case DAGGER_THE_END:
                    mcdwDagger =
                            new McdwDagger(ToolMaterials.NETHERITE,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).damage,
                                    CONFIG.mcdwNewStatsConfig.daggerStats.get(DaggersID.DAGGER_THE_END).attackSpeed);
                    break;
                default:
                    mcdwDagger = new McdwDagger(ToolMaterials.IRON,
                            0,
                            0.0f);
            }

            daggerItems.put(daggersID, mcdwDagger);
            registerItem(daggersID.toString().toLowerCase(), mcdwDagger);
        }
    }

    protected static void registerItem(String id, Item item) {
        Registry.register(Registry.ITEM, ID(id), item);
    }
}
