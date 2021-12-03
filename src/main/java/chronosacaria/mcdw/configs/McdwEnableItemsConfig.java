package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.SwordsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {

    public EnumMap<SwordsID, Boolean> enableSwords = new EnumMap<>(SwordsID.class);

    public McdwEnableItemsConfig() {
        for (SwordsID swordsID : SwordsID.values()){
            enableSwords.put(swordsID, true);
        }
    }
}
