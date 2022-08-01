package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSickle;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SicklesID implements IMcdwWeaponID, IMeleeWeaponID {
    SICKLE_LAST_LAUGH_GOLD(ToolMaterials.IRON,2, -1.9f),
    SICKLE_LAST_LAUGH_SILVER(ToolMaterials.IRON,2, -1.9f),
    SICKLE_NIGHTMARES_BITE(ToolMaterials.IRON,3, -1.9f),
    SICKLE_SICKLE(ToolMaterials.IRON,1, -1.9f);

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;

    SicklesID(ToolMaterial material, int damage, float attackSpeed) {
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

    public static HashMap<SicklesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.sickleSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.sickleStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSickle getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.sickleStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.sickleStats.get(this);
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
    public McdwSickle makeWeapon() {
        McdwSickle mcdwSickle = new McdwSickle(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSickle);
        return mcdwSickle;
    }
}