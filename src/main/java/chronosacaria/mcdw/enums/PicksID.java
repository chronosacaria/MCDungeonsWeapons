package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwPick;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum PicksID implements IMcdwWeaponID, IMeleeWeaponID {
    PICK_DIAMOND_PICKAXE_VAR(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),1, -2.8f),
    PICK_HAILING_PINNACLE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),1, -2.8f),
    PICK_HOWLING_PICK(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -2.8f),
    PICK_MOUNTAINEER_PICK(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -2.8f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    PicksID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<PicksID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.picksEnabled;
    }

    public static EnumMap<PicksID, McdwPick> getItemsEnum() {
        return ItemsInit.pickItems;
    }

    public static HashMap<PicksID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.pickSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.pickStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwPick getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.pickStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.pickStats.get(this);
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
    public McdwPick makeWeapon() {
        McdwPick mcdwPick = new McdwPick(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwPick);
        return mcdwPick;
    }
}