package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcdw_loot_config")
public class McdwNewLootConfig implements ConfigData {
    public EnumMap<SettingsID, Boolean> enableLootTableSettings = new EnumMap<>(SettingsID.class);
    public EnumMap<AxesID, Float> axeSpawnRates = new EnumMap<>(AxesID.class);
    public EnumMap<CrossbowsID, Float> crossbowSpawnRates = new EnumMap<>(CrossbowsID.class);
    public EnumMap<DaggersID, Float> daggerSpawnRates = new EnumMap<>(DaggersID.class);
    public EnumMap<DoubleAxesID, Float> doubleAxeSpawnRates = new EnumMap<>(DoubleAxesID.class);
    public EnumMap<GauntletsID, Float> gauntletSpawnRates = new EnumMap<>(GauntletsID.class);
    public EnumMap<GlaivesID, Float> glaiveSpawnRates = new EnumMap<>(GlaivesID.class);
    public EnumMap<HammersID, Float> hammerSpawnRates = new EnumMap<>(HammersID.class);
    public EnumMap<PicksID, Float> pickSpawnRates = new EnumMap<>(PicksID.class);
    public EnumMap<ScythesID, Float> scytheSpawnRates = new EnumMap<>(ScythesID.class);
    public EnumMap<SicklesID, Float> sickleSpawnRates = new EnumMap<>(SicklesID.class);
    public EnumMap<SoulDaggersID, Float> soulDaggerSpawnRates = new EnumMap<>(SoulDaggersID.class);
    public EnumMap<SpearsID, Float> spearSpawnRates = new EnumMap<>(SpearsID.class);
    public EnumMap<StavesID, Float> staffSpawnRates = new EnumMap<>(StavesID.class);
    public EnumMap<SwordsID, Float> swordSpawnRates = new EnumMap<>(SwordsID.class);
    public EnumMap<WhipsID, Float> whipSpawnRates = new EnumMap<>(WhipsID.class);

    public EnumMap<BowsID, Float> bowSpawnRates = new EnumMap<>(BowsID.class);
    public EnumMap<LongBowsID, Float> longBowSpawnRates = new EnumMap<>(LongBowsID.class);
    public EnumMap<ShortBowsID, Float> shortBowSpawnRates = new EnumMap<>(ShortBowsID.class);

    public EnumMap<ShieldsID, Float> shieldSpawnRates = new EnumMap<>(ShieldsID.class);

    public McdwNewLootConfig(){
        for (SettingsID settingsEnum : SettingsID.values()) {
            enableLootTableSettings.put(SettingsID.ENABLE_BASIC_WEAPONS_IN_LOOTTABLES, true);
            enableLootTableSettings.put(SettingsID.ENABLE_ALL_WEAPONS_IN_LOOTTABLES, false);
        }

        for (AxesID axesID : AxesID.values()) {
            axeSpawnRates.put(AxesID.AXE, 0.1f);
            axeSpawnRates.put(AxesID.AXE_FIREBRAND, 0.05f);
            axeSpawnRates.put(AxesID.AXE_HIGHLAND, 0.05f);
            axeSpawnRates.put(AxesID.AXE_ANCHOR, 0.1f);
            axeSpawnRates.put(AxesID.AXE_ENCRUSTED_ANCHOR, 0.05f);
        }
        for (DaggersID daggersID : DaggersID.values()) {
            daggerSpawnRates.put(DaggersID.DAGGER_DAGGER, 0.1f);
            daggerSpawnRates.put(DaggersID.DAGGER_FANGS_OF_FROST, 0.05f);
            daggerSpawnRates.put(DaggersID.DAGGER_MOON, 0.05f);
            daggerSpawnRates.put(DaggersID.DAGGER_SHEAR_DAGGER, 0.1f);
            daggerSpawnRates.put(DaggersID.DAGGER_TEMPEST_KNIFE, 0.1f);
            daggerSpawnRates.put(DaggersID.DAGGER_RESOLUTE_TEMPEST_KNIFE, 0.05f);
            daggerSpawnRates.put(DaggersID.DAGGER_CHILL_GALE_KNIFE, 0.05f);
            daggerSpawnRates.put(DaggersID.DAGGER_BACKSTABBER, 0.1f);
            daggerSpawnRates.put(DaggersID.DAGGER_SWIFT_STRIKER, 0.05f);
            daggerSpawnRates.put(DaggersID.DAGGER_VOID_TOUCHED_BLADE, 0.1f);
            daggerSpawnRates.put(DaggersID.DAGGER_THE_BEGINNING, 0.05f);
            daggerSpawnRates.put(DaggersID.DAGGER_THE_END, 0.05f);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()) {
            doubleAxeSpawnRates.put(DoubleAxesID.AXE_DOUBLE, 0.1f);
            doubleAxeSpawnRates.put(DoubleAxesID.AXE_CURSED, 0.05f);
            doubleAxeSpawnRates.put(DoubleAxesID.AXE_WHIRLWIND, 0.05f);
        }
        for (GauntletsID gauntletsID : GauntletsID.values()) {
            gauntletSpawnRates.put(GauntletsID.GAUNTLET_GAUNTLET, 0.1f);
            gauntletSpawnRates.put(GauntletsID.GAUNTLET_MAULERS, 0.05f);
            gauntletSpawnRates.put(GauntletsID.GAUNTLET_SOUL_FISTS, 0.05f);
        }
        for (GlaivesID glaivesID : GlaivesID.values()) {
            glaiveSpawnRates.put(GlaivesID.SPEAR_GLAIVE, 0.1f);
            glaiveSpawnRates.put(GlaivesID.SPEAR_GRAVE_BANE, 0.05f);
            glaiveSpawnRates.put(GlaivesID.SPEAR_VENOM_GLAIVE, 0.05f);
            //glaiveSpawnRates.put(GlaivesID.SPEAR_CACKLING_BROOM, 0.05f);
        }
    }

}
