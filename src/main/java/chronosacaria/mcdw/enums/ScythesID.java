package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.bases.McdwScythe;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.EnchantsRegistry;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum ScythesID implements IMeleeWeaponID, IInnateEnchantment {
    SICKLE_FROST_SCYTHE(ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    SICKLE_JAILORS_SCYTHE(ToolMaterials.IRON,4, -2.9f, "minecraft:iron_ingot"),
    SICKLE_SKULL_SCYTHE(ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    SICKLE_SOUL_SCYTHE(ToolMaterials.DIAMOND,3, -2.9f, "minecraft:diamond");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    ScythesID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<ScythesID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.SCYTHES_ENABLED;
    }

    public static EnumMap<ScythesID, McdwScythe> getItemsEnum() {
        return ItemsRegistry.SCYTHE_ITEMS;
    }

    public static HashMap<ScythesID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SCYTHE_SPAWN_RATES;
    }

    public static HashMap<ScythesID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.scytheStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwScythe getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<ScythesID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.scytheStats.get(this);
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
    public Map<Enchantment, Integer> getInnateEnchantments() {
        return switch (this) {
            case SICKLE_FROST_SCYTHE, SICKLE_SKULL_SCYTHE -> Map.of(EnchantsRegistry.FREEZING, 1);
            case SICKLE_JAILORS_SCYTHE -> Map.of(EnchantsRegistry.CHAINS, 1);
            case SICKLE_SOUL_SCYTHE -> Map.of(EnchantsRegistry.SOUL_DEVOURER, 1);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwScythe makeWeapon() {
        McdwScythe mcdwScythe = new McdwScythe(this, ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwScythe);
        return mcdwScythe;
    }
}
