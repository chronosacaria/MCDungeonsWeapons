package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSoulDagger;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SoulDaggersID implements IMcdwWeaponID, IMeleeWeaponID {
    DAGGER_ETERNAL_KNIFE(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),4, -0.9f),
    DAGGER_SOUL_KNIFE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.1f),
    SWORD_TRUTHSEEKER(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),3, -1.5f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    SoulDaggersID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<SoulDaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.soulDaggersEnabled;
    }

    public static EnumMap<SoulDaggersID, McdwSoulDagger> getItemsEnum() {
        return ItemsInit.soulDaggerItems;
    }

    public static HashMap<SoulDaggersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.soulDaggerSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.soulDaggerStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSoulDagger getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.soulDaggerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.soulDaggerStats.get(this);
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
    public McdwSoulDagger makeWeapon() {
        McdwSoulDagger mcdwSoulDagger = new McdwSoulDagger(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSoulDagger);
        return mcdwSoulDagger;
    }
}