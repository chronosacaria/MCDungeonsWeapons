package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwGauntlet;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum GauntletsID implements IMcdwWeaponID, IMeleeWeaponID {
    GAUNTLET_GAUNTLET(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),0, -1.4f),
    GAUNTLET_MAULERS(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),0, -1.4f),
    GAUNTLET_SOUL_FISTS(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),0, -1.4f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    GauntletsID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<GauntletsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.gauntletsEnabled;
    }

    public static EnumMap<GauntletsID, McdwGauntlet> getItemsEnum() {
        return ItemsInit.gauntletItems;
    }

    public static HashMap<GauntletsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.gauntletSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.gauntletStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwGauntlet getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.gauntletStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.gauntletStats.get(this);
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
    public McdwGauntlet makeWeapon() {
        McdwGauntlet mcdwGauntlet = new McdwGauntlet(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwGauntlet);
        return mcdwGauntlet;
    }
}