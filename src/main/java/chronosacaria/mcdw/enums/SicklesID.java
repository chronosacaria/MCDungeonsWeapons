package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSickle;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SicklesID implements IMcdwWeaponID, IMeleeWeaponID {
    SICKLE_LAST_LAUGH_GOLD(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),2, -1.9f),
    SICKLE_LAST_LAUGH_SILVER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),2, -1.9f),
    SICKLE_NIGHTMARES_BITE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -1.9f),
    SICKLE_SICKLE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.9f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    SicklesID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<SicklesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.sicklesEnabled;
    }

    public static EnumMap<SicklesID, McdwSickle> getItemsEnum() {
        return ItemsInit.sickleItems;
    }

    public static HashMap<SicklesID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.sickleSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.sickleStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSickle getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.sickleStats;
    }

    public IMeleeWeaponID.MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public IMeleeWeaponID.MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.sickleStats.get(this);
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
    public McdwSickle makeWeapon() {
        McdwSickle mcdwSickle = new McdwSickle(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSickle);
        return mcdwSickle;
    }
}