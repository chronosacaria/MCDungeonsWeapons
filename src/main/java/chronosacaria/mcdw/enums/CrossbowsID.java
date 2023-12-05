package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.bases.McdwCrossbow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.projectile_damage.api.IProjectileWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum CrossbowsID implements IRangedWeaponID, IInnateEnchantment {
    CROSSBOW_AUTO_CROSSBOW(          ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_AZURE_SEEKER(           ToolMaterials.IRON,      10, 28, 8.4f,  "minecraft:iron_ingot"),
    CROSSBOW_BABY_CROSSBOW(          ToolMaterials.IRON,      8,  23, 7.2f,  "minecraft:iron_ingot"),
    CROSSBOW_BURST_CROSSBOW(         ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_BUTTERFLY_CROSSBOW(     ToolMaterials.IRON,      10, 28, 8.9f,  "minecraft:iron_ingot"),
    CROSSBOW_COG_CROSSBOW(           ToolMaterials.IRON,      10, 28, 8.4f,  "minecraft:iron_ingot"),
    CROSSBOW_CORRUPTED_CROSSBOW(     ToolMaterials.NETHERITE, 16, 22, 14.0f, "minecraft:netherite_scrap"),
    CROSSBOW_DOOM_CROSSBOW(          ToolMaterials.NETHERITE, 9,  26, 8.0f,  "minecraft:netherite_scrap"),
    CROSSBOW_DUAL_CROSSBOW(          ToolMaterials.IRON,      8,  24, 7.0f,  "minecraft:iron_ingot"),
    CROSSBOW_EXPLODING_CROSSBOW(     ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_FERAL_SOUL_CROSSBOW(    ToolMaterials.IRON,      10, 28, 9.2f,  "minecraft:iron_ingot"),
    CROSSBOW_FIREBOLT_THROWER(       ToolMaterials.IRON,      9,  28, 7.9f,  "minecraft:iron_ingot"),
    CROSSBOW_HARPOON_CROSSBOW(       ToolMaterials.IRON,      12, 28, 11.0f, "minecraft:iron_ingot"),
    CROSSBOW_HARP_CROSSBOW(          ToolMaterials.IRON,      10, 28, 8.6f,  "minecraft:iron_ingot"),
    CROSSBOW_HEAVY_CROSSBOW(         ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_IMPLODING_CROSSBOW(     ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_LIGHTNING_HARP_CROSSBOW(ToolMaterials.DIAMOND,   16, 28, 14.2f, "minecraft:diamond"),
    CROSSBOW_NAUTICAL_CROSSBOW(      ToolMaterials.DIAMOND,   16, 24, 14.0f, "minecraft:diamond"),
    CROSSBOW_PRIDE_OF_THE_PIGLINS(   ToolMaterials.NETHERITE, 15, 20, 13.0f, "minecraft:netherite_scrap"),
    CROSSBOW_RAPID_CROSSBOW(         ToolMaterials.IRON,      9,  20, 8.2f,  "minecraft:iron_ingot"),
    CROSSBOW_SCATTER_CROSSBOW(       ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_SHADOW_CROSSBOW(        ToolMaterials.DIAMOND,   14, 25, 12.0f, "minecraft:diamond"),
    CROSSBOW_SLAYER_CROSSBOW(        ToolMaterials.DIAMOND,   10, 26, 8.8f,  "minecraft:diamond"),
    CROSSBOW_SOUL_CROSSBOW(          ToolMaterials.IRON,      9,  28, 8.0f,  "minecraft:iron_ingot"),
    CROSSBOW_SOUL_HUNTER_CROSSBOW(   ToolMaterials.DIAMOND,   12, 28, 11.0f, "minecraft:diamond"),
    CROSSBOW_SPELLBOUND_CROSSBOW(    ToolMaterials.IRON,      10, 28, 8.9f,  "minecraft:iron_ingot"),
    CROSSBOW_THE_SLICER(             ToolMaterials.IRON,      12, 28, 10.2f, "minecraft:iron_ingot"),
    CROSSBOW_VEILED_CROSSBOW(        ToolMaterials.DIAMOND,   16, 22, 14.5f, "minecraft:diamond"),
    CROSSBOW_VOIDCALLER_CROSSBOW(    ToolMaterials.DIAMOND,   14, 26, 12.5f, "minecraft:diamond");

    public final ToolMaterial material;
    public final double projectileDamage;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    CrossbowsID(ToolMaterial material, double projectileDamage, int drawSpeed, float range, String... repairIngredient) {
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

    public static HashMap<CrossbowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.CROSSBOWS_ENABLED;
    }

    @SuppressWarnings("SameReturnValue")
    public static EnumMap<CrossbowsID, McdwCrossbow> getItemsEnum() {
        return ItemsRegistry.CROSSBOW_ITEMS;
    }

    public static HashMap<CrossbowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.CROSSBOW_SPAWN_RATES;
    }

    public static HashMap<CrossbowsID, RangedStats> getWeaponStats() {
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
    public HashMap<CrossbowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
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

    @Override
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case CROSSBOW_AUTO_CROSSBOW -> Map.of(EnchantsRegistry.ACCELERATE, 1);
            case CROSSBOW_AZURE_SEEKER, CROSSBOW_BURST_CROSSBOW, CROSSBOW_COG_CROSSBOW, CROSSBOW_DUAL_CROSSBOW, CROSSBOW_RAPID_CROSSBOW, CROSSBOW_SPELLBOUND_CROSSBOW -> null;
            case CROSSBOW_BABY_CROSSBOW -> Map.of(EnchantsRegistry.GROWING, 1);
            case CROSSBOW_BUTTERFLY_CROSSBOW -> Map.of(EnchantsRegistry.BONUS_SHOT, 1);
            case CROSSBOW_CORRUPTED_CROSSBOW -> Map.of(EnchantsRegistry.DYNAMO, 1);
            case CROSSBOW_DOOM_CROSSBOW -> Map.of(Enchantments.PUNCH, 1, Enchantments.POWER, 1);
            case CROSSBOW_HARP_CROSSBOW -> Map.of(Enchantments.MULTISHOT, 2);
            case CROSSBOW_HEAVY_CROSSBOW, CROSSBOW_HARPOON_CROSSBOW -> Map.of(Enchantments.PUNCH, 1);
            case CROSSBOW_EXPLODING_CROSSBOW -> Map.of(EnchantsRegistry.FUSE_SHOT, 1);
            case CROSSBOW_FERAL_SOUL_CROSSBOW, CROSSBOW_SOUL_CROSSBOW, CROSSBOW_SOUL_HUNTER_CROSSBOW -> Map.of(EnchantsRegistry.ENIGMA_RESONATOR, 1);
            case CROSSBOW_FIREBOLT_THROWER -> Map.of(EnchantsRegistry.FUSE_SHOT, 1, EnchantsRegistry.CHAIN_REACTION, 1);
            case CROSSBOW_SCATTER_CROSSBOW -> Map.of(Enchantments.MULTISHOT, 1);
            case CROSSBOW_IMPLODING_CROSSBOW -> Map.of(EnchantsRegistry.FUSE_SHOT, 1, EnchantsRegistry.GRAVITY, 1);
            case CROSSBOW_VOIDCALLER_CROSSBOW -> Map.of(EnchantsRegistry.GRAVITY, 1);
            case CROSSBOW_LIGHTNING_HARP_CROSSBOW -> Map.of(Enchantments.MULTISHOT, 1, EnchantsRegistry.RICOCHET, 1, EnchantsRegistry.THUNDERING, 1);
            case CROSSBOW_NAUTICAL_CROSSBOW -> Map.of(Enchantments.PIERCING, 1, Enchantments.PUNCH, 1);
            case CROSSBOW_PRIDE_OF_THE_PIGLINS, CROSSBOW_THE_SLICER -> Map.of(Enchantments.PIERCING, 1);
            case CROSSBOW_SHADOW_CROSSBOW -> Map.of(EnchantsRegistry.SHADOW_SHOT, 1);
            case CROSSBOW_SLAYER_CROSSBOW -> Map.of(Enchantments.POWER, 1, EnchantsRegistry.RICOCHET, 1);
            case CROSSBOW_VEILED_CROSSBOW -> Map.of(EnchantsRegistry.SHADOW_SHOT, 1, EnchantsRegistry.SHADOW_BARB, 1);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public McdwCrossbow makeWeapon() {
        McdwCrossbow mcdwCrossbow = new McdwCrossbow(this, ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);
        if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
            ((IProjectileWeapon) mcdwCrossbow).setProjectileDamage(this.getWeaponItemStats().projectileDamage);
            ((IProjectileWeapon) mcdwCrossbow).setCustomLaunchVelocity((this.getWeaponItemStats().range / 8.0f) * 3.15);
        }
        getItemsEnum().put(this, mcdwCrossbow);
        return mcdwCrossbow;
    }
}