package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwLongbow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum LongbowsID implements IMcdwWeaponID, IRangedWeaponID {
    BOW_GUARDIAN_BOW(ToolMaterials.DIAMOND, 30, 19f, "minecraft:diamond"),
    BOW_LONGBOW(ToolMaterials.IRON, 25, 17f, "minecraft:planks"),
    BOW_RED_SNAKE(ToolMaterials.DIAMOND, 30, 18f, "minecraft:diamond");

    public final ToolMaterial material;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    LongbowsID(ToolMaterial material, int drawSpeed, float range, String... repairIngredient) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<LongbowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.LONGBOWS_ENABLED;
    }

    public static EnumMap<LongbowsID, McdwLongbow> getItemsEnum() {
        return ItemsRegistry.LONGBOW_ITEMS;
    }

    public static HashMap<LongbowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.LONGBOW_SPAWN_RATES;
    }

    public static HashMap<LongbowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.longbowStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwLongbow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<LongbowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longbowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longbowStats.get(this);
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
    public McdwLongbow makeWeapon() {
        McdwLongbow mcdwLongbow = new McdwLongbow(ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwLongbow);
        return mcdwLongbow;
    }
}