package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwHammer;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum HammersID implements IMcdwWeaponID, IMeleeWeaponID {
    HAMMER_BONECLUB(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -3.1f),
    HAMMER_BONE_CUDGEL(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),5, -3.1f),
    HAMMER_FLAIL(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.9f),
    HAMMER_GRAVITY(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),5, -3.0f),
    HAMMER_GREAT_HAMMER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),4, -3.0f),
    HAMMER_MACE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),4, -2.9f),
    HAMMER_STORMLANDER(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),5, -3.0f),
    HAMMER_SUNS_GRACE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),4, -2.5f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    HammersID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<HammersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.hammersEnabled;
    }

    public static EnumMap<HammersID, McdwHammer> getItemsEnum() {
        return ItemsInit.hammerItems;
    }

    public static HashMap<HammersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.hammerSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.hammerStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwHammer getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.hammerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.hammerStats.get(this);
    }

    @Override
    public String getMaterial(){
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
    public McdwHammer makeWeapon() {
        McdwHammer mcdwHammer = new McdwHammer(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwHammer);
        return mcdwHammer;
    }
}