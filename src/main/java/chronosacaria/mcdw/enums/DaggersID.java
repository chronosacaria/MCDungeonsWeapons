package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum DaggersID implements IMcdwWeaponID, IMeleeWeaponID {
    DAGGER_BACKSTABBER(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),3, -1.2f),
    DAGGER_CHILL_GALE_KNIFE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),3, -1.3f),
    DAGGER_DAGGER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.3f),
    DAGGER_FANGS_OF_FROST(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.0f),
    DAGGER_MOON(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.0f),
    DAGGER_RESOLUTE_TEMPEST_KNIFE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -1.3f),
    DAGGER_SHEAR_DAGGER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.3f),
    DAGGER_SWIFT_STRIKER(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),4, -1.0f),
    DAGGER_TEMPEST_KNIFE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),2, -1.3f),
    DAGGER_THE_BEGINNING(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),4, -1.2f),
    DAGGER_THE_END(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),4, -1.2f),
    DAGGER_VOID_TOUCHED_BLADE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),4, -1.2f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    DaggersID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<DaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.daggersEnabled;
    }

    public static EnumMap<DaggersID, McdwDagger> getItemsEnum() {
        return ItemsInit.daggerItems;
    }

    public static HashMap<DaggersID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.daggerSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.daggerStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwDagger getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.daggerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.daggerStats.get(this);
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
    public McdwDagger makeWeapon() {
        McdwDagger mcdwDagger = new McdwDagger(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwDagger);
        return mcdwDagger;
    }
}