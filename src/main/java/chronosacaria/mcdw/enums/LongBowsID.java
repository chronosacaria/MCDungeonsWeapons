package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum LongBowsID implements IMcdwWeaponID, IRangedWeaponID {
    BOW_GUARDIAN_BOW(ToolMaterials.DIAMOND, 30, 19f, "minecraft:diamond"),
    BOW_LONGBOW(ToolMaterials.IRON, 25, 17f, "minecraft:planks"),
    BOW_RED_SNAKE(ToolMaterials.DIAMOND, 30, 18f, "minecraft:diamond");

    public final ToolMaterial material;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    LongBowsID(ToolMaterial material, int drawSpeed, float range, String... repairIngredient) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<LongBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.longBowsEnabled;
    }

    public static EnumMap<LongBowsID, McdwLongBow> getItemsEnum() {
        return ItemsInit.longBowItems;
    }

    public static HashMap<LongBowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.longBowSpawnRates;
    }

    public static HashMap<LongBowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.longBowStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwLongBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<LongBowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longBowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longBowStats.get(this);
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
    public McdwLongBow makeWeapon() {
        McdwLongBow mcdwLongBow = new McdwLongBow(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwLongBow);
        return mcdwLongBow;
    }
}