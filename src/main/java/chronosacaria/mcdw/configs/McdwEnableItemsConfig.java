package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.HashMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {

    // TODO Change nomenclature to final convention for hashmaps and subsequent usages
    public final HashMap<SwordsID, Boolean> swordsEnabled = new HashMap<>();
    public final HashMap<AxesID, Boolean> axesEnabled = new HashMap<>();
    public final HashMap<DoubleAxesID, Boolean> doubleAxesEnabled = new HashMap<>();
    public final HashMap<DaggersID, Boolean> daggersEnabled = new HashMap<>();
    public final HashMap<SoulDaggersID, Boolean> soulDaggersEnabled = new HashMap<>();
    public final HashMap<HammersID, Boolean> hammersEnabled = new HashMap<>();
    public final HashMap<GauntletsID, Boolean> gauntletsEnabled = new HashMap<>();
    public final HashMap<SicklesID, Boolean> sicklesEnabled = new HashMap<>();
    public final HashMap<ScythesID, Boolean> scythesEnabled = new HashMap<>();
    public final HashMap<PicksID, Boolean> picksEnabled = new HashMap<>();
    public final HashMap<GlaivesID, Boolean> glaivesEnabled = new HashMap<>();
    public final HashMap<SpearsID, Boolean> spearsEnabled = new HashMap<>();
    public final HashMap<StavesID, Boolean> stavesEnabled = new HashMap<>();
    public final HashMap<WhipsID, Boolean> whipsEnabled = new HashMap<>();
    public final HashMap<BowsID, Boolean> bowsEnabled = new HashMap<>();
    public final HashMap<ShortBowsID, Boolean> shortBowsEnabled = new HashMap<>();
    public final HashMap<LongBowsID, Boolean> longBowsEnabled = new HashMap<>();
    public final HashMap<CrossbowsID, Boolean> crossbowsEnabled = new HashMap<>();
    public final HashMap<ShieldsID, Boolean> shieldsEnabled = new HashMap<>();
    public final HashMap<ItemsID, Boolean> itemsEnabled = new HashMap<>();

    public McdwEnableItemsConfig() {
        for (SwordsID swordsID : SwordsID.values())
            swordsEnabled.put(swordsID, true);

        for (AxesID axesID : AxesID.values())
            axesEnabled.put(axesID, true);

        for (DoubleAxesID doubleAxesID : DoubleAxesID.values())
            doubleAxesEnabled.put(doubleAxesID, true);

        for (DaggersID daggersID : DaggersID.values())
            daggersEnabled.put(daggersID, true);

        for (SoulDaggersID soulDaggersID : SoulDaggersID.values())
            soulDaggersEnabled.put(soulDaggersID, true);

        for (HammersID hammersID : HammersID.values())
            hammersEnabled.put(hammersID, true);

        for (GauntletsID gauntletsID : GauntletsID.values())
            gauntletsEnabled.put(gauntletsID, true);

        for (SicklesID sicklesID : SicklesID.values())
            sicklesEnabled.put(sicklesID, true);

        for (ScythesID scythesID : ScythesID.values())
            scythesEnabled.put(scythesID, true);

        for (PicksID picksID : PicksID.values())
            picksEnabled.put(picksID, true);

        for (GlaivesID glaivesID : GlaivesID.values())
            glaivesEnabled.put(glaivesID, true);

        for (SpearsID spearsID : SpearsID.values())
            spearsEnabled.put(spearsID, true);

        for (StavesID stavesID : StavesID.values())
            stavesEnabled.put(stavesID, true);

        for (WhipsID whipsID : WhipsID.values())
            whipsEnabled.put(whipsID, true);

        for (BowsID bowsID : BowsID.values())
            bowsEnabled.put(bowsID, true);

        for (ShortBowsID shortBowsID : ShortBowsID.values())
            shortBowsEnabled.put(shortBowsID, true);

        for (LongBowsID longBowsID : LongBowsID.values())
            longBowsEnabled.put(longBowsID, true);

        for (CrossbowsID crossbowsID : CrossbowsID.values())
            crossbowsEnabled.put(crossbowsID, true);

        for (ShieldsID shieldsID : ShieldsID.values())
            shieldsEnabled.put(shieldsID, true);

        for (ItemsID itemsID : ItemsID.values())
            itemsEnabled.put(itemsID, true);
    }
}

