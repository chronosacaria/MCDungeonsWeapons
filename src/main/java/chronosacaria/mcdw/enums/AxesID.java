package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwAxe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum AxesID implements IMeleeWeaponID {
    AXE_ANCHOR(ToolMaterials.IRON,8, -3.4f, "minecraft:iron_ingot"),
    AXE_AXE(ToolMaterials.IRON,6, -3.1f, "minecraft:iron_ingot"),
    AXE_ENCRUSTED_ANCHOR(ToolMaterials.DIAMOND,8, -3.4f, "minecraft:diamond"),
    AXE_FIREBRAND(ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    AXE_HIGHLAND(ToolMaterials.IRON,4, -2.9f, "minecraft:iron_ingot");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    AxesID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<AxesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.AXES_ENABLED;
    }

    public static EnumMap<AxesID, McdwAxe> getItemsEnum() {
        return ItemsRegistry.AXE_ITEMS;
    }

    public static HashMap<AxesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.AXE_SPAWN_RATES;
    }

    public static HashMap<AxesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.axeStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwAxe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<AxesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.axeStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.axeStats.get(this);
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
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public McdwAxe makeWeapon() {
        McdwAxe mcdwAxe = new McdwAxe(ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwAxe);
        return mcdwAxe;
    }
}
