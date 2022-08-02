package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwShortBow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ShortBowsID implements IMcdwWeaponID, IRangedWeaponID {
    BOW_LOVE_SPELL_BOW(ToolMaterials.IRON, 9, 8f),
    BOW_MECHANICAL_SHORTBOW(ToolMaterials.IRON, 9, 9f),
    BOW_PURPLE_STORM(ToolMaterials.IRON, 9, 8f),
    BOW_SHORTBOW(ToolMaterials.IRON, 9, 8f);

    public final ToolMaterial material;
    public final int drawSpeed;
    public final float range;

    ShortBowsID(ToolMaterial material, int drawSpeed, float range) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
    }

    public static HashMap<ShortBowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.shortBowsEnabled;
    }

    public static EnumMap<ShortBowsID, McdwShortBow> getItemsEnum() {
        return ItemsInit.shortBowItems;
    }

    public static HashMap<ShortBowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.shortBowSpawnRates;
    }

    public static HashMap<ShortBowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.shortBowStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwShortBow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<ShortBowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.shortBowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.shortBowStats.get(this);
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
    public McdwShortBow makeWeapon() {
        McdwShortBow mcdwShortBow = new McdwShortBow(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range);

        getItemsEnum().put(this, mcdwShortBow);
        return mcdwShortBow;
    }
}