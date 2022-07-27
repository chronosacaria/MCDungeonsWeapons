package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SpearsID implements IMcdwWeaponID, IMeleeWeaponID {
    SPEAR_SPEAR(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -2.5f),
    SPEAR_WHISPERING_SPEAR(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.5f),
    SPEAR_FORTUNE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),4, -2.15f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    SpearsID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<SpearsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.spearsEnabled;
    }

    public static EnumMap<SpearsID, McdwSpear> getItemsEnum() {
        return ItemsInit.spearItems;
    }

    public static HashMap<SpearsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.spearSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.spearStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSpear getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.spearStats;
    }

    public IMeleeWeaponID.MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public IMeleeWeaponID.MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.spearStats.get(this);
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
    public McdwSpear makeWeapon() {
        McdwSpear mcdwSpear = new McdwSpear(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSpear);
        return mcdwSpear;
    }
}
