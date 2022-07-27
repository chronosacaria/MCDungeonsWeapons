package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwScythe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ScythesID implements IMcdwWeaponID, IMeleeWeaponID {
    SICKLE_FROST_SCYTHE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),4, -2.5f),
    SICKLE_JAILORS_SCYTHE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),4, -2.25f),
    SICKLE_SKULL_SCYTHE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),4, -2.5f),
    SICKLE_SOUL_SCYTHE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),3, -2.25f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    ScythesID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<ScythesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.scythesEnabled;
    }

    public static EnumMap<ScythesID, McdwScythe> getItemsEnum() {
        return ItemsInit.scytheItems;
    }

    public static HashMap<ScythesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.scytheSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.scytheStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwScythe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats;
    }

    public IMeleeWeaponID.MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public IMeleeWeaponID.MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats.get(this);
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
    public McdwScythe makeWeapon() {
        McdwScythe mcdwScythe = new McdwScythe(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwScythe);
        return mcdwScythe;
    }
}