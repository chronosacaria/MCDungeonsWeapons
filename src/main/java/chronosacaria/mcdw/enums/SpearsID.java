package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.Item;

import java.util.EnumMap;
import java.util.HashMap;

public enum SpearsID implements McdwWeaponID {
    SPEAR_SPEAR,
    SPEAR_WHISPERING_SPEAR,
    SPEAR_FORTUNE;

    public HashMap<SpearsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.spearsEnabled;
    }

    public EnumMap<SpearsID, McdwSpear> getItemsEnum() {
        return ItemsInit.spearItems;
    }

    public HashMap<SpearsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.spearSpawnRates;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public Item getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }
}
