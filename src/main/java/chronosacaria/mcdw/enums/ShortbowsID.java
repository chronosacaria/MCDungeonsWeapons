package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwShortbow;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.projectile_damage.api.IProjectileWeapon;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ShortbowsID implements IRangedWeaponID {
    BOW_LOVE_SPELL_BOW(     ToolMaterials.IRON, 3, 9, 8f, "minecraft:iron_ingot"),
    BOW_MECHANICAL_SHORTBOW(ToolMaterials.IRON, 4, 9, 9f, "minecraft:iron_ingot"),
    BOW_PURPLE_STORM(       ToolMaterials.IRON, 3, 9, 8f, "minecraft:iron_ingot"),
    BOW_SHORTBOW(           ToolMaterials.IRON, 3, 9, 8f, "minecraft:planks");

    public final ToolMaterial material;
    public final double projectileDamage;
    public final int drawSpeed;
    public final float range;
    private final String[] repairIngredient;

    ShortbowsID(ToolMaterial material, double projectileDamage, int drawSpeed, float range, String... repairIngredient) {
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

    public static HashMap<ShortbowsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.SHORTBOWS_ENABLED;
    }

    public static EnumMap<ShortbowsID, McdwShortbow> getItemsEnum() {
        return ItemsRegistry.SHORTBOW_ITEMS;
    }

    public static HashMap<ShortbowsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SHORTBOW_SPAWN_RATES;
    }

    public static HashMap<ShortbowsID, RangedStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.shortbowStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwShortbow getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<ShortbowsID, RangedStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.shortbowStats;
    }

    @Override
    public RangedStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public RangedStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.shortbowStats.get(this);
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

    //@Override
    //public Map<Enchantment, Integer> getInnateEnchantments() {
    //    return switch (this) {
    //        case BOW_LOVE_SPELL_BOW -> Map.of(EnchantsRegistry.RADIANCE, 1);
    //        case BOW_MECHANICAL_SHORTBOW -> Map.of(EnchantsRegistry.ACCELERATE, 1);
    //        case BOW_PURPLE_STORM,BOW_SHORTBOW -> null;
    //    };
    //}
    //
    //@Override
    //public @NotNull ItemStack getInnateEnchantedStack(Item item) {
    //    return item.getDefaultStack();
    //}

    @SuppressWarnings("ConstantConditions")
    @Override
    public McdwShortbow makeWeapon() {
        McdwShortbow mcdwShortbow = new McdwShortbow(ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().drawSpeed, this.getWeaponItemStats().range, this.getWeaponItemStats().repairIngredient);
        if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
            ((IProjectileWeapon) mcdwShortbow).setProjectileDamage(this.getWeaponItemStats().projectileDamage);
            ((IProjectileWeapon) mcdwShortbow).setCustomLaunchVelocity((this.getWeaponItemStats().range / 15.0f) * 3.0);
        }
        getItemsEnum().put(this, mcdwShortbow);
        return mcdwShortbow;
    }
}