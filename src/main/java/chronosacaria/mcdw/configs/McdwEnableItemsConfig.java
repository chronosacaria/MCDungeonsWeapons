package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.AxesID;
import chronosacaria.mcdw.enums.DaggersID;
import chronosacaria.mcdw.enums.DoubleAxesID;
import chronosacaria.mcdw.enums.SwordsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {

    public EnumMap<SwordsID, Boolean> enableSwords = new EnumMap<>(SwordsID.class);
    public EnumMap<AxesID, Boolean> enableAxes = new EnumMap<>(AxesID.class);
    public EnumMap<DoubleAxesID, Boolean> enableDoubleAxes = new EnumMap<>(DoubleAxesID.class);
    public EnumMap<DaggersID, Boolean> enableDaggers = new EnumMap<>(DaggersID.class);

    public McdwEnableItemsConfig() {
        for (SwordsID swordsID : SwordsID.values()){
            enableSwords.put(swordsID, true);
        }
        for (AxesID axesID : AxesID.values()){
            enableAxes.put(axesID, true);
        }
        for (DoubleAxesID doubleAxesID : DoubleAxesID.values()){
            enableDoubleAxes.put(doubleAxesID, true);
        }
        for (DaggersID daggersID : DaggersID.values()){
            enableDaggers.put(daggersID, true);
        }
    }
}
