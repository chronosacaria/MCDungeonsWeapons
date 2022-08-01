package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum AxesID implements IMcdwWeaponID, IMeleeWeaponID {
    AXE_ANCHOR(ToolMaterials.IRON,6, -3.1f),
    AXE_AXE(ToolMaterials.IRON,6, -3.1f),
    AXE_ENCRUSTED_ANCHOR(ToolMaterials.DIAMOND,5, -3.1f),
    AXE_FIREBRAND(ToolMaterials.DIAMOND,5, -2.9f),
    AXE_HIGHLAND(ToolMaterials.IRON,6, -3.1f);

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;

    AxesID(ToolMaterial material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<AxesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.axesEnabled;
    }

    public static EnumMap<AxesID, McdwAxe> getItemsEnum() {
        return ItemsInit.axeItems;
    }

    public static HashMap<AxesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.axeSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.axeStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwAxe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.axeStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.axeStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial(){
        return material;
    }

    @Override
    public int getDamage(){
        return damage;
    }

    @Override
    public float getAttackSpeed(){
        return attackSpeed;
    }

    @Override
    public McdwAxe makeWeapon() {
        McdwAxe mcdwAxe = new McdwAxe(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwAxe);
        return mcdwAxe;
    }
}