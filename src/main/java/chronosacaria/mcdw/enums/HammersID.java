package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwHammer;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum HammersID implements IMcdwWeaponID, IMeleeWeaponID {
    HAMMER_BONECLUB(ToolMaterials.IRON,7, -3.2f, "minecraft:bone_block"),
    HAMMER_BONE_CUDGEL(ToolMaterials.NETHERITE,7, -3.2f, "minecraft:netherite_scrap"),
    HAMMER_FLAIL(ToolMaterials.IRON,5, -2.8f, "minecraft:iron_ingot"),
    HAMMER_GRAVITY(ToolMaterials.DIAMOND,6, -3.2f, "minecraft:diamond"),
    HAMMER_GREAT_HAMMER(ToolMaterials.IRON,6, -3.2f, "minecraft:iron_ingot"),
    HAMMER_MACE(ToolMaterials.IRON,5, -2.8f, "minecraft:iron_ingot"),
    HAMMER_STORMLANDER(ToolMaterials.DIAMOND,7, -3.2f, "minecraft:diamond"),
    HAMMER_SUNS_GRACE(ToolMaterials.DIAMOND,4, -2.8f, "minecraft:diamond");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    HammersID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<HammersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.HAMMERS_ENABLED;
    }

    public static EnumMap<HammersID, McdwHammer> getItemsEnum() {
        return ItemsInit.HAMMER_ITEMS;
    }

    public static HashMap<HammersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.HAMMER_SPAWN_RATES;
    }

    public static HashMap<HammersID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.hammerStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwHammer getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<HammersID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.hammerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.hammerStats.get(this);
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
    public McdwHammer makeWeapon() {
        McdwHammer mcdwHammer = new McdwHammer(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwHammer);
        return mcdwHammer;
    }
}