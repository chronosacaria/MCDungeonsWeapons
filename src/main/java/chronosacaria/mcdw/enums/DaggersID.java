package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.api.interfaces.IInnateEnchantment;
import chronosacaria.mcdw.bases.McdwDagger;
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

public enum DaggersID implements IMeleeWeaponID, IInnateEnchantment {
    DAGGER_BACKSTABBER(ToolMaterials.DIAMOND,1, -1.7f, "minecraft:diamond"),
    DAGGER_CHILL_GALE_KNIFE(ToolMaterials.DIAMOND,2, -2.2f, "minecraft:diamond"),
    DAGGER_DAGGER(ToolMaterials.IRON,1, -1.5f, "minecraft:iron_ingot"),
    DAGGER_FANGS_OF_FROST(ToolMaterials.IRON,1, -1.5f, "minecraft:iron_ingot"),
    DAGGER_MOON(ToolMaterials.IRON,1, -1.5f, "minecraft:iron_ingot"),
    DAGGER_RESOLUTE_TEMPEST_KNIFE(ToolMaterials.IRON,2, -2.2f, "minecraft:iron_ingot"),
    DAGGER_SHEAR_DAGGER(ToolMaterials.IRON,0, -1.5f, "minecraft:iron_ingot"),
    DAGGER_SWIFT_STRIKER(ToolMaterials.NETHERITE,1, -1.7f, "minecraft:netherite_scrap"),
    DAGGER_TEMPEST_KNIFE(ToolMaterials.IRON,2, -2.2f, "minecraft:iron_ingot"),
    DAGGER_THE_BEGINNING(ToolMaterials.NETHERITE,1, -1.8f, "minecraft:netherite_scrap"),
    DAGGER_THE_END(ToolMaterials.NETHERITE,1, -1.8f, "minecraft:netherite_scrap"),
    DAGGER_VOID_TOUCHED_BLADE(ToolMaterials.DIAMOND,1, -1.8f, "minecraft:diamond");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;


    DaggersID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<DaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.DAGGERS_ENABLED;
    }

    public static EnumMap<DaggersID, McdwDagger> getItemsEnum() {
        return ItemsRegistry.DAGGER_ITEMS;
    }

    public static HashMap<DaggersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.DAGGER_SPAWN_RATES;
    }

    public static HashMap<DaggersID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.daggerStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwDagger getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<DaggersID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.daggerStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.daggerStats.get(this);
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
            case DAGGER_BACKSTABBER -> Map.of(EnchantsRegistry.AMBUSH, 1, EnchantsRegistry.BONUS_SHOT, 1);
            case DAGGER_CHILL_GALE_KNIFE, DAGGER_FANGS_OF_FROST -> Map.of(EnchantsRegistry.FREEZING, 1);
            case DAGGER_DAGGER, DAGGER_TEMPEST_KNIFE -> null;
            case DAGGER_MOON -> Map.of(EnchantsRegistry.ENIGMA_RESONATOR, 1);
            case DAGGER_RESOLUTE_TEMPEST_KNIFE -> Map.of(EnchantsRegistry.COMMITTED, 1 , EnchantsRegistry.RUSHDOWN, 1);
            case DAGGER_SHEAR_DAGGER -> Map.of(EnchantsRegistry.SWIRLING, 1);
            case DAGGER_SWIFT_STRIKER -> Map.of(EnchantsRegistry.AMBUSH, 1, EnchantsRegistry.ECHO, 1);
            case DAGGER_THE_BEGINNING -> Map.of(EnchantsRegistry.LEECHING, 1);
            case DAGGER_THE_END, DAGGER_VOID_TOUCHED_BLADE -> Map.of(EnchantsRegistry.VOID_STRIKE, 1);
        };
    }

    @Override
    public @NotNull ItemStack getInnateEnchantedStack(Item item) {
        return item.getDefaultStack();
    }

    @Override
    public McdwDagger makeWeapon() {
        McdwDagger mcdwDagger = new McdwDagger(this, ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwDagger);
        return mcdwDagger;
    }
}
