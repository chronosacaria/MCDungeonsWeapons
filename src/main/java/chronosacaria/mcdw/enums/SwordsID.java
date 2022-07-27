package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SwordsID implements IMcdwWeaponID, IMeleeWeaponID {
    SWORD_BEESTINGER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),0, -0.9f),
    SWORD_BROADSWORD(McdwNewStatsConfig.materialToString(ToolMaterials.IRON), 4, -3.0f),
    SWORD_BROKEN_SAWBLADE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -2.4f),
    SWORD_CLAYMORE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON), 5, -3.0f),
    SWORD_CORAL_BLADE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -2.4f),
    SWORD_CUTLASS(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -2.7f),
    SWORD_DANCERS_SWORD(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.0f),
    SWORD_DARK_KATANA(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE),2, -1.15f),
    SWORD_DIAMOND_SWORD_VAR(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),3, -2.4f),
    SWORD_FREEZING_FOIL(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),0, -0.9f),
    SWORD_FROST_SLAYER(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND), 5, -3.0f),
    SWORD_GREAT_AXEBLADE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON), 6, -3.0f),
    SWORD_HAWKBRAND(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.0f),
    SWORD_HEARTSTEALER(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND), 4, -3.0f),
    SWORD_IRON_SWORD_VAR(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),3, -2.4f),
    SWORD_KATANA(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.5f),
    SWORD_MASTERS_KATANA(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),1, -1.1f),
    SWORD_MECHANIZED_SAWBLADE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),3, -2.4f),
    SWORD_NAMELESS_BLADE(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),1, -1.7f),
    SWORD_OBSIDIAN_CLAYMORE(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE), 6, -3.0f),
    SWORD_RAPIER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),0, -0.9f),
    SWORD_SINISTER(McdwNewStatsConfig.materialToString(ToolMaterials.IRON),5, -2.0f),
    SWORD_SPONGE_STRIKER(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND),3, -2.4f),
    SWORD_THE_STARLESS_NIGHT(McdwNewStatsConfig.materialToString(ToolMaterials.NETHERITE), 8, -3.0f);

    private final String material;
    private final int damage;
    private final float attackSpeed;

    SwordsID(String material, int damage, float attackSpeed) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
    }

    public static HashMap<SwordsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.swordsEnabled;
    }

    public static EnumMap<SwordsID, McdwSword> getItemsEnum() {
        return ItemsInit.swordItems;
    }

    public static HashMap<SwordsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.swordSpawnRates;
    }

    public static HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.swordStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSword getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IMeleeWeaponID, IMeleeWeaponID.MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.swordStats;
    }

    public IMeleeWeaponID.MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public IMeleeWeaponID.MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.swordStats.get(this);
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

    public McdwSword makeWeapon() {
        McdwSword mcdwSword = new McdwSword(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed);

        getItemsEnum().put(this, mcdwSword);
        return mcdwSword;
    }
}