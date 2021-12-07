package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.*;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {

    public EnumMap<SwordsID, Boolean> swordsEnabled = new EnumMap<>(SwordsID.class);
    public EnumMap<AxesID, Boolean> axesEnabled = new EnumMap<>(AxesID.class);
    public EnumMap<DoubleAxesID, Boolean> doubleAxesEnabled = new EnumMap<>(DoubleAxesID.class);
    public EnumMap<DaggersID, Boolean> daggersEnabled = new EnumMap<>(DaggersID.class);
    public EnumMap<SoulDaggersID, Boolean> soulDaggersEnabled = new EnumMap<>(SoulDaggersID.class);
    public EnumMap<HammersID, Boolean> hammersEnabled = new EnumMap<>(HammersID.class);
    public EnumMap<GauntletsID, Boolean> gauntletsEnabled = new EnumMap<>(GauntletsID.class);
    public EnumMap<SicklesID, Boolean> sicklesEnabled = new EnumMap<>(SicklesID.class);
    public EnumMap<ScythesID, Boolean> scythesEnabled = new EnumMap<>(ScythesID.class);
    public EnumMap<PicksID, Boolean> picksEnabled = new EnumMap<>(PicksID.class);
    public EnumMap<GlaivesID, Boolean> glaivesEnabled = new EnumMap<>(GlaivesID.class);
    public EnumMap<SpearsID, Boolean> spearsEnabled = new EnumMap<>(SpearsID.class);
    public EnumMap<StavesID, Boolean> stavesEnabled = new EnumMap<>(StavesID.class);
    public EnumMap<WhipsID, Boolean> whipsEnabled = new EnumMap<>(WhipsID.class);
    public EnumMap<BowsID, Boolean> bowsEnabled = new EnumMap<>(BowsID.class);
    public EnumMap<ShortBowsID, Boolean> shortBowsEnabled = new EnumMap<>(ShortBowsID.class);
    public EnumMap<LongBowsID, Boolean> longBowsEnabled = new EnumMap<>(LongBowsID.class);

    public McdwEnableItemsConfig() {
        for (SwordsID swordsID : SwordsID.values()){
            swordsEnabled.put(swordsID, true);
        }
        for (AxesID axesID : AxesID.values()){
            axesEnabled.put(axesID, true);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()){
            doubleAxesEnabled.put(doubleAxesID, true);
        }
        for (DaggersID daggersID : DaggersID.values()){
            daggersEnabled.put(daggersID, true);
        }
        for (SoulDaggersID soulDaggersID : SoulDaggersID.values()){
            soulDaggersEnabled.put(soulDaggersID, true);
        }
        for (HammersID hammersID : HammersID.values()){
            hammersEnabled.put(hammersID, true);
        }
        for (GauntletsID gauntletsID : GauntletsID.values()){
            gauntletsEnabled.put(gauntletsID, true);
        }
        for (SicklesID sicklesID : SicklesID.values()){
            sicklesEnabled.put(sicklesID, true);
        }
        for (ScythesID scythesID : ScythesID.values()){
            scythesEnabled.put(scythesID, true);
        }
        for (PicksID picksID : PicksID.values()){
            picksEnabled.put(picksID, true);
        }
        for (GlaivesID glaivesID : GlaivesID.values()){
            glaivesEnabled.put(glaivesID, true);
        }
        for (SpearsID spearsID : SpearsID.values()){
            spearsEnabled.put(spearsID, true);
        }
        for (StavesID stavesID : StavesID.values()){
            stavesEnabled.put(stavesID, true);
        }
        for (WhipsID whipsID : WhipsID.values()){
            whipsEnabled.put(whipsID, true);
        }
        for (BowsID bowsID : BowsID.values()){
            bowsEnabled.put(bowsID, true);
        }
        for (ShortBowsID shortBowsID : ShortBowsID.values()){
            shortBowsEnabled.put(shortBowsID, true);
        }
        for (LongBowsID longBowsID : LongBowsID.values()){
            longBowsEnabled.put(longBowsID, true);
        }
    }
}
