package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwStaff;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum StavesID implements IMcdwWeaponID, IMeleeWeaponID {
    STAFF_BATTLESTAFF(ToolMaterials.WOOD,2, -0.1f),
    STAFF_BATTLESTAFF_OF_TERROR(ToolMaterials.IRON,2, -0.1f),
    STAFF_GROWING_STAFF(ToolMaterials.IRON,3, -0.1f);

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;

    StavesID(ToolMaterial material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<StavesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.stavesEnabled;
    }

    public static EnumMap<StavesID, McdwStaff> getItemsEnum() {
        return ItemsInit.staffItems;
    }

    public static HashMap<StavesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.staffSpawnRates;
    }

    public static HashMap<StavesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.staffStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwStaff getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<StavesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.staffStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.staffStats.get(this);
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
    public McdwStaff makeWeapon() {
        McdwStaff mcdwStaff = new McdwStaff(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwStaff);
        return mcdwStaff;
    }
}