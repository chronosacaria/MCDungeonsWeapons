package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum CrossbowsID implements IMcdwWeaponID, IRangedWeaponID {
    CROSSBOW_AUTO_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_AZURE_SEEKER(ToolMaterials.IRON, 28, 8.4f),
    CROSSBOW_BABY_CROSSBOW(ToolMaterials.IRON, 23, 7.2f),
    CROSSBOW_BURST_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_BUTTERFLY_CROSSBOW(ToolMaterials.IRON, 28, 8.9f),
    CROSSBOW_COG_CROSSBOW(ToolMaterials.IRON, 28, 8.4f),
    CROSSBOW_CORRUPTED_CROSSBOW(ToolMaterials.NETHERITE, 22, 14.0f),
    CROSSBOW_DOOM_CROSSBOW(ToolMaterials.NETHERITE, 26, 8.0f),
    CROSSBOW_DUAL_CROSSBOW(ToolMaterials.IRON, 24, 7.0f),
    CROSSBOW_EXPLODING_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_FERAL_SOUL_CROSSBOW(ToolMaterials.IRON, 28, 9.2f),
    CROSSBOW_FIREBOLT_THROWER(ToolMaterials.IRON, 28, 7.9f),
    CROSSBOW_HARPOON_CROSSBOW(ToolMaterials.IRON, 28, 11.0f),
    CROSSBOW_HARP_CROSSBOW(ToolMaterials.IRON, 28, 8.6f),
    CROSSBOW_HEAVY_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_IMPLODING_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_LIGHTNING_HARP_CROSSBOW(ToolMaterials.DIAMOND, 28, 14.2f),
    CROSSBOW_NAUTICAL_CROSSBOW(ToolMaterials.DIAMOND, 24, 14.0f),
    CROSSBOW_PRIDE_OF_THE_PIGLINS(ToolMaterials.NETHERITE, 20, 13.0f),
    CROSSBOW_RAPID_CROSSBOW(ToolMaterials.IRON, 20, 8.2f),
    CROSSBOW_SCATTER_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_SHADOW_CROSSBOW(ToolMaterials.DIAMOND, 25, 12.0f),
    CROSSBOW_SLAYER_CROSSBOW(ToolMaterials.DIAMOND, 26, 8.8f),
    CROSSBOW_SOUL_CROSSBOW(ToolMaterials.IRON, 28, 8.0f),
    CROSSBOW_SOUL_HUNTER_CROSSBOW(ToolMaterials.DIAMOND, 28, 11.0f),
    CROSSBOW_SPELLBOUND_CROSSBOW(ToolMaterials.IRON, 28, 8.9f),
    CROSSBOW_THE_SLICER(ToolMaterials.IRON, 28, 10.2f),
    CROSSBOW_VEILED_CROSSBOW(ToolMaterials.DIAMOND, 22, 14.5f),
    CROSSBOW_VOIDCALLER_CROSSBOW(ToolMaterials.DIAMOND, 26, 12.5f);

    public final ToolMaterial material;
    public final int drawSpeed;
    public final float range;

    CrossbowsID(ToolMaterial material, int drawSpeed, float range) {
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;
    }

    public static HashMap<CrossbowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.crossbowsEnabled;
    }

    public static EnumMap<CrossbowsID, McdwCrossbow> getItemsEnum() {
        return ItemsInit.crossbowItems;
    }

    public static HashMap<CrossbowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.crossbowSpawnRates;
    }

    public static HashMap<IRangedWeaponID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.crossbowStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwCrossbow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<IRangedWeaponID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.crossbowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.crossbowStats.get(this);
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
    public McdwCrossbow makeWeapon() {
        McdwCrossbow mcdwCrossbow = new McdwCrossbow(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range);

        getItemsEnum().put(this, mcdwCrossbow);
        return mcdwCrossbow;
    }
}