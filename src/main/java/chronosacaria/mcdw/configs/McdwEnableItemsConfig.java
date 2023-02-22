package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.LinkedHashMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {

    public final LinkedHashMap<SwordsID, Boolean> SWORDS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<AxesID, Boolean> AXES_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<DoubleAxesID, Boolean> DOUBLE_AXES_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<DaggersID, Boolean> DAGGERS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<SoulDaggersID, Boolean> SOUL_DAGGERS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<HammersID, Boolean> HAMMERS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<GauntletsID, Boolean> GAUNTLETS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<SicklesID, Boolean> SICKLES_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<ScythesID, Boolean> SCYTHES_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<PicksID, Boolean> PICKS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<GlaivesID, Boolean> GLAIVES_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<SpearsID, Boolean> SPEARS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<StavesID, Boolean> STAVES_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<WhipsID, Boolean> WHIPS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<BowsID, Boolean> BOWS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<ShortbowsID, Boolean> SHORTBOWS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<LongbowsID, Boolean> LONGBOWS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<CrossbowsID, Boolean> CROSSBOWS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<ShieldsID, Boolean> SHIELDS_ENABLED = new LinkedHashMap<>();
    public final LinkedHashMap<ItemsID, Boolean> ITEMS_ENABLED = new LinkedHashMap<>();

    public McdwEnableItemsConfig() {
        for (SwordsID swordsID : SwordsID.values())
            SWORDS_ENABLED.put(swordsID, true);

        for (AxesID axesID : AxesID.values())
            AXES_ENABLED.put(axesID, true);

        for (DoubleAxesID doubleAxesID : DoubleAxesID.values())
            DOUBLE_AXES_ENABLED.put(doubleAxesID, true);

        for (DaggersID daggersID : DaggersID.values())
            DAGGERS_ENABLED.put(daggersID, true);

        for (SoulDaggersID soulDaggersID : SoulDaggersID.values())
            SOUL_DAGGERS_ENABLED.put(soulDaggersID, true);

        for (HammersID hammersID : HammersID.values())
            HAMMERS_ENABLED.put(hammersID, true);

        for (GauntletsID gauntletsID : GauntletsID.values())
            GAUNTLETS_ENABLED.put(gauntletsID, true);

        for (SicklesID sicklesID : SicklesID.values())
            SICKLES_ENABLED.put(sicklesID, true);

        for (ScythesID scythesID : ScythesID.values())
            SCYTHES_ENABLED.put(scythesID, true);

        for (PicksID picksID : PicksID.values())
            PICKS_ENABLED.put(picksID, true);

        for (GlaivesID glaivesID : GlaivesID.values())
            GLAIVES_ENABLED.put(glaivesID, true);

        for (SpearsID spearsID : SpearsID.values())
            SPEARS_ENABLED.put(spearsID, true);

        for (StavesID stavesID : StavesID.values())
            STAVES_ENABLED.put(stavesID, true);

        for (WhipsID whipsID : WhipsID.values())
            WHIPS_ENABLED.put(whipsID, true);

        for (BowsID bowsID : BowsID.values())
            BOWS_ENABLED.put(bowsID, true);

        for (ShortbowsID shortBowsID : ShortbowsID.values())
            SHORTBOWS_ENABLED.put(shortBowsID, true);

        for (LongbowsID longBowsID : LongbowsID.values())
            LONGBOWS_ENABLED.put(longBowsID, true);

        for (CrossbowsID crossbowsID : CrossbowsID.values())
            CROSSBOWS_ENABLED.put(crossbowsID, true);

        for (ShieldsID shieldsID : ShieldsID.values())
            SHIELDS_ENABLED.put(shieldsID, true);

        for (ItemsID itemsID : ItemsID.values())
            ITEMS_ENABLED.put(itemsID, true);
    }
}
