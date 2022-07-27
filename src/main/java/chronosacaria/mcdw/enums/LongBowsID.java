package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum LongBowsID implements IMcdwWeaponID, IRangedWeaponID {
    BOW_GUARDIAN_BOW(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND), 30, 19f),
    BOW_LONGBOW(McdwNewStatsConfig.materialToString(ToolMaterials.IRON), 25, 17f),
    BOW_RED_SNAKE(McdwNewStatsConfig.materialToString(ToolMaterials.DIAMOND), 30, 18f);

    public final String material;
    public final int drawSpeed;
    public final float range;

    LongBowsID(String material, int drawSpeed, float range) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
    }

    public static HashMap<LongBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.longBowsEnabled;
    }

    public static EnumMap<LongBowsID, McdwLongBow> getItemsEnum() {
        return ItemsInit.longBowItems;
    }

    public static HashMap<LongBowsID, Float> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.longBowSpawnRates;
    }

    public static HashMap<IRangedWeaponID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.longBowStats;
    }

    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwLongBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Float getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IRangedWeaponID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longBowStats;
    }

    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.longBowStats.get(this);
    }

    @Override
    public String getMaterial() {
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
    public McdwLongBow makeWeapon() {
        McdwLongBow mcdwLongBow = new McdwLongBow(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range);

        getItemsEnum().put(this, mcdwLongBow);
        return mcdwLongBow;
    }
}