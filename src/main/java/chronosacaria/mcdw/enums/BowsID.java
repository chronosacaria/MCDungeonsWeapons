package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum BowsID implements IMcdwWeaponID, IRangedWeaponID {
    BOW_ANCIENT_BOW(ToolMaterials.NETHERITE,14, 18f, "minecraft:netherite_scrap"),
    BOW_BONEBOW(ToolMaterials.STONE,16, 12f, "minecraft:bone"),
    BOW_BUBBLE_BOW(ToolMaterials.IRON,15, 12f, "minecraft:iron_ingot"),
    BOW_BUBBLE_BURSTER(ToolMaterials.DIAMOND,15, 13f, "minecraft:diamond"),
    BOW_BURST_GALE_BOW(ToolMaterials.DIAMOND,12, 16f, "minecraft:diamond"),
    BOW_CALL_OF_THE_VOID(ToolMaterials.NETHERITE,15, 16f, "minecraft:netherite_scrap"),
    BOW_ECHO_OF_THE_VALLEY(ToolMaterials.DIAMOND,11, 16f, "minecraft:diamond"),
    BOW_ELITE_POWER_BOW(ToolMaterials.IRON,20, 15f, "minecraft:iron_ingot"),
    BOW_GREEN_MENACE(ToolMaterials.DIAMOND,17, 13f, "minecraft:diamond"),
    BOW_HAUNTED_BOW(ToolMaterials.NETHERITE,18, 16f, "minecraft:netherite_scrap"),
    BOW_HUNTERS_PROMISE(ToolMaterials.IRON,15, 10f, "minecraft:iron_ingot"),
    BOW_HUNTING_BOW(ToolMaterials.IRON,16, 11f, "minecraft:iron_ingot"),
    BOW_LOST_SOULS(ToolMaterials.NETHERITE,12, 17f, "minecraft:netherite_scrap"),
    BOW_MASTERS_BOW(ToolMaterials.IRON,17, 10f, "minecraft:iron_ingot"),
    BOW_NOCTURNAL_BOW(ToolMaterials.DIAMOND,17, 14f, "minecraft:diamond"),
    BOW_PHANTOM_BOW(ToolMaterials.DIAMOND,20, 14f, "minecraft:diamond"),
    BOW_PINK_SCOUNDREL(ToolMaterials.DIAMOND,17, 13f, "minecraft:diamond"),
    BOW_POWER_BOW(ToolMaterials.IRON,20, 14f, "minecraft:iron_ingot"),
    BOW_SABREWING(ToolMaterials.DIAMOND,10, 13f, "minecraft:diamond"),
    BOW_SHIVERING_BOW(ToolMaterials.DIAMOND,14, 10f, "minecraft:diamond"),
    BOW_SNOW_BOW(ToolMaterials.IRON,16, 11f, "minecraft:iron_ingot"),
    BOW_SOUL_BOW(ToolMaterials.IRON,14, 10f, "minecraft:iron_ingot"),
    BOW_TRICKBOW(ToolMaterials.DIAMOND,12, 10f, "minecraft:diamond"),
    BOW_TWIN_BOW(ToolMaterials.DIAMOND,12, 10f, "minecraft:diamond"),
    BOW_TWISTING_VINE_BOW(ToolMaterials.IRON,15, 10f, "minecraft:iron_ingot"),
    BOW_VOID_BOW(ToolMaterials.DIAMOND,15, 15f, "minecraft:diamond"),
    BOW_WEB_BOW(ToolMaterials.DIAMOND,15, 12f, "minecraft:diamond"),
    BOW_WEEPING_VINE_BOW(ToolMaterials.IRON,15, 10f, "minecraft:iron_ingot"),
    BOW_WIND_BOW(ToolMaterials.DIAMOND,11, 15f, "minecraft:diamond"),
    BOW_WINTERS_TOUCH(ToolMaterials.DIAMOND,15, 13f, "minecraft:diamond");

    public final ToolMaterial material;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    BowsID(ToolMaterial material, int drawSpeed, float range, String... repairIngredient) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<BowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.BOWS_ENABLED;
    }

    public static EnumMap<BowsID, McdwBow> getItemsEnum() {
        return ItemsInit.bowItems;
    }

    public static HashMap<BowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.BOW_SPAWN_RATES;
    }

    public static HashMap<BowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.bowStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<BowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.bowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.bowStats.get(this);
    }

    @Override
    public ToolMaterial getMaterial() {
        return material;
    }

    @Override
    public int getDrawSpeed() {
        return drawSpeed;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public String[] getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public McdwBow makeWeapon() {
        McdwBow mcdwBow = new McdwBow(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwBow);
        return mcdwBow;
    }
}