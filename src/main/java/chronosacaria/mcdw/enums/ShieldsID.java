package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwShield;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ShieldsID implements IMcdwWeaponID, IShieldID {
    SHIELD_ROYAL_GUARD(ToolMaterials.DIAMOND),
    SHIELD_VANGUARD(ToolMaterials.DIAMOND);

    private final ToolMaterial material;

    ShieldsID(ToolMaterial material) {
        this.material = material;
    }

    public static HashMap<ShieldsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.shieldsEnabled;
    }

    public static EnumMap<ShieldsID, McdwShield> getItemsEnum() {
        return ItemsInit.shieldItems;
    }

    public static HashMap<ShieldsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.shieldSpawnRates;
    }

    public static HashMap<IShieldID, ShieldStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.shieldStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwShield getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IShieldID, ShieldStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.shieldStats;
    }

    public ShieldStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public ShieldStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return getWeaponStats(mcdwNewStatsConfig).get(this);
    }

    @Override
    public ToolMaterial getMaterial() {
        return material;
    }

    @Override
    public McdwShield makeWeapon() {
        McdwShield mcdwShield = new McdwShield(ItemsInit.stringToMaterial(this.getWeaponItemStats().material));

        getItemsEnum().put(this, mcdwShield);
        return mcdwShield;
    }
}
