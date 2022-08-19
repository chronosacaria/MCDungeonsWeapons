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
    STAFF_BATTLESTAFF(ToolMaterials.WOOD,2, -2.6f, "minecraft:planks"),
    STAFF_BATTLESTAFF_OF_TERROR(ToolMaterials.IRON,5, -2.6f, "minecraft:iron_ingot"),
    STAFF_GROWING_STAFF(ToolMaterials.IRON,5, -2.6f, "minecraft:iron_ingot");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    StavesID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<StavesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.STAVES_ENABLED;
    }

    public static EnumMap<StavesID, McdwStaff> getItemsEnum() {
        return ItemsInit.staffItems;
    }

    public static HashMap<StavesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.STAFF_SPAWN_RATES;
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
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public McdwStaff makeWeapon() {
        McdwStaff mcdwStaff = new McdwStaff(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwStaff);
        return mcdwStaff;
    }
}