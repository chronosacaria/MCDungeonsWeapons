package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.projectile_damage.api.IProjectileWeapon;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum BowsID implements IRangedWeaponID {
    BOW_ANCIENT_BOW(       ToolMaterials.NETHERITE, 7, 14, 18f, "minecraft:netherite_scrap"),
    BOW_BONEBOW(           ToolMaterials.STONE,     5, 16, 12f, "minecraft:bone"),
    BOW_BUBBLE_BOW(        ToolMaterials.IRON,      5, 15, 12f, "minecraft:iron_ingot"),
    BOW_BUBBLE_BURSTER(    ToolMaterials.DIAMOND,   5, 15, 13f, "minecraft:diamond"),
    BOW_BURST_GALE_BOW(    ToolMaterials.DIAMOND,   6, 12, 16f, "minecraft:diamond"),
    BOW_CALL_OF_THE_VOID(  ToolMaterials.NETHERITE, 6, 15, 16f, "minecraft:netherite_scrap"),
    BOW_ECHO_OF_THE_VALLEY(ToolMaterials.DIAMOND,   6, 11, 16f, "minecraft:diamond"),
    BOW_ELITE_POWER_BOW(   ToolMaterials.IRON,      6, 20, 15f, "minecraft:iron_ingot"),
    BOW_GREEN_MENACE(      ToolMaterials.DIAMOND,   5, 17, 13f, "minecraft:diamond"),
    BOW_HAUNTED_BOW(       ToolMaterials.NETHERITE, 6, 18, 16f, "minecraft:netherite_scrap"),
    BOW_HUNTERS_PROMISE(   ToolMaterials.IRON,      6, 15, 16f, "minecraft:iron_ingot"),
    BOW_HUNTING_BOW(       ToolMaterials.IRON,      6, 16, 15f, "minecraft:iron_ingot"),
    BOW_LOST_SOULS(        ToolMaterials.NETHERITE, 6, 12, 17f, "minecraft:netherite_scrap"),
    BOW_MASTERS_BOW(       ToolMaterials.IRON,      6, 17, 16f, "minecraft:iron_ingot"),
    BOW_NOCTURNAL_BOW(     ToolMaterials.DIAMOND,   6, 17, 14f, "minecraft:diamond"),
    BOW_PHANTOM_BOW(       ToolMaterials.DIAMOND,   6, 20, 14f, "minecraft:diamond"),
    BOW_PINK_SCOUNDREL(    ToolMaterials.DIAMOND,   5, 17, 13f, "minecraft:diamond"),
    BOW_POWER_BOW(         ToolMaterials.IRON,      6, 20, 14f, "minecraft:iron_ingot"),
    BOW_SABREWING(         ToolMaterials.DIAMOND,   5, 10, 13f, "minecraft:diamond"),
    BOW_SHIVERING_BOW(     ToolMaterials.DIAMOND,   6, 14, 15f, "minecraft:diamond"),
    BOW_SNOW_BOW(          ToolMaterials.IRON,      5, 16, 13f, "minecraft:iron_ingot"),
    BOW_SOUL_BOW(          ToolMaterials.IRON,      6, 14, 15f, "minecraft:iron_ingot"),
    BOW_TRICKBOW(          ToolMaterials.DIAMOND,   5, 12, 12f, "minecraft:diamond"),
    BOW_TWIN_BOW(          ToolMaterials.DIAMOND,   5, 12, 12f, "minecraft:diamond"),
    BOW_TWISTING_VINE_BOW( ToolMaterials.IRON,      5, 15, 13f, "minecraft:iron_ingot"),
    BOW_VOID_BOW(          ToolMaterials.DIAMOND,   6, 15, 16f, "minecraft:diamond"),
    BOW_WEB_BOW(           ToolMaterials.DIAMOND,   5, 15, 12f, "minecraft:diamond"),
    BOW_WEEPING_VINE_BOW(  ToolMaterials.IRON,      5, 15, 13f, "minecraft:iron_ingot"),
    BOW_WIND_BOW(          ToolMaterials.DIAMOND,   6, 11, 15f, "minecraft:diamond"),
    BOW_WINTERS_TOUCH(     ToolMaterials.DIAMOND,   6, 15, 14f, "minecraft:diamond");

    public final ToolMaterial material;
    public final double projectileDamage;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    BowsID(ToolMaterial material, double projectileDamage, int drawSpeed, float range, String... repairIngredient) {
        this.material = material;
        if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
            this.projectileDamage = projectileDamage;
        } else {
            this.projectileDamage = 0;
        }
        this.drawSpeed = drawSpeed;
        this.range = range;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<BowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.BOWS_ENABLED;
    }

    public static EnumMap<BowsID, McdwBow> getItemsEnum() {
        return ItemsRegistry.BOW_ITEMS;
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
    public double getProjectileDamage() {
        if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
            return projectileDamage;
        } else {
            return 0;
        }
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

    @SuppressWarnings("ConstantConditions")
    @Override
    public McdwBow makeWeapon() {
        McdwBow mcdwBow = new McdwBow(ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);

        if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
            ((IProjectileWeapon) mcdwBow).setProjectileDamage(this.getWeaponItemStats().projectileDamage);
            ((IProjectileWeapon) mcdwBow).setCustomLaunchVelocity((this.getWeaponItemStats().range / 15.0f) * 3.0);
        }

        getItemsEnum().put(this, mcdwBow);
        return mcdwBow;
    }
}