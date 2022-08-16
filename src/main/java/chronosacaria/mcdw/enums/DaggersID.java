package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwDagger;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum DaggersID implements IMcdwWeaponID, IMeleeWeaponID {
    DAGGER_BACKSTABBER(ToolMaterials.DIAMOND,3, -1.2f, "minecraft:diamond"),
    DAGGER_CHILL_GALE_KNIFE(ToolMaterials.DIAMOND,3, -1.3f, "minecraft:diamond"),
    DAGGER_DAGGER(ToolMaterials.IRON,1, -1.3f, "minecraft:iron_ingot"),
    DAGGER_FANGS_OF_FROST(ToolMaterials.IRON,1, -1.0f, "minecraft:iron_ingot"),
    DAGGER_MOON(ToolMaterials.IRON,1, -1.0f, "minecraft:iron_ingot"),
    DAGGER_RESOLUTE_TEMPEST_KNIFE(ToolMaterials.IRON,3, -1.3f, "minecraft:iron_ingot"),
    DAGGER_SHEAR_DAGGER(ToolMaterials.IRON,1, -1.3f, "minecraft:iron_ingot"),
    DAGGER_SWIFT_STRIKER(ToolMaterials.NETHERITE,4, -1.0f, "minecraft:netherite_scrap"),
    DAGGER_TEMPEST_KNIFE(ToolMaterials.IRON,2, -1.3f, "minecraft:iron_ingot"),
    DAGGER_THE_BEGINNING(ToolMaterials.NETHERITE,4, -1.2f, "minecraft:netherite_scrap"),
    DAGGER_THE_END(ToolMaterials.NETHERITE,4, -1.2f, "minecraft:netherite_scrap"),
    DAGGER_VOID_TOUCHED_BLADE(ToolMaterials.DIAMOND,4, -1.2f, "minecraft:diamond");

    private final ToolMaterials material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;


    DaggersID(ToolMaterials material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<DaggersID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.daggersEnabled;
    }

    public static EnumMap<DaggersID, McdwDagger> getItemsEnum() {
        return ItemsInit.daggerItems;
    }

    public static HashMap<DaggersID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.daggerSpawnRates;
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
    public ToolMaterials getMaterial(){
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
    public McdwDagger makeWeapon() {
        McdwDagger mcdwDagger = new McdwDagger(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwDagger);
        return mcdwDagger;
    }
}