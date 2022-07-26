package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwGlaive;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.configs.stats.MeleeWeaponStats;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum GlaivesID implements IMcdwWeaponID, IMeleeWeaponID {
    SPEAR_CACKLING_BROOM(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.4f),
    SPEAR_GLAIVE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -2.7f),
    SPEAR_GRAVE_BANE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.4f),
    SPEAR_VENOM_GLAIVE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.5f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    GlaivesID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<GlaivesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.glaivesEnabled;
    }

    public static EnumMap<GlaivesID, McdwGlaive> getItemsEnum() {
        return ItemsInit.glaiveItems;
    }

    public static HashMap<GlaivesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.glaiveSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeWeaponStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.glaiveStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwGlaive getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeWeaponStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.glaiveStats;
    }

    public MeleeWeaponStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeWeaponStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.glaiveStats.get(this);
    }

    public int getDamage(){
        return damage;
    }

    public String getMaterial(){
        return material;
    }

    public float getAttackSpeed(){
        return attackSpeed;
    }

    @Override
    public McdwGlaive makeWeapon() {
        McdwGlaive mcdwGlaive = new McdwGlaive(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwGlaive);
        return mcdwGlaive;
    }
}