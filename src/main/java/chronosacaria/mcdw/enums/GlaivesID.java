package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwGlaive;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum GlaivesID implements IMcdwWeaponID, IMeleeWeaponID {
    SPEAR_CACKLING_BROOM(ToolMaterials.IRON,5, -2.4f),
    SPEAR_GLAIVE(ToolMaterials.IRON,3, -2.7f),
    SPEAR_GRAVE_BANE(ToolMaterials.IRON,5, -2.4f),
    SPEAR_VENOM_GLAIVE(ToolMaterials.IRON,5, -2.5f);

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;

    GlaivesID(ToolMaterial material, int damage, float attackSpeed) {
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

    public static HashMap<GlaivesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.glaiveSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.glaiveStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwGlaive getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.glaiveStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.glaiveStats.get(this);
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
    public McdwGlaive makeWeapon() {
        McdwGlaive mcdwGlaive = new McdwGlaive(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwGlaive);
        return mcdwGlaive;
    }
}