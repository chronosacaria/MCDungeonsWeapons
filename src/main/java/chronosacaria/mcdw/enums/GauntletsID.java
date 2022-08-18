package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwGauntlet;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum GauntletsID implements IMcdwWeaponID, IMeleeWeaponID {
    GAUNTLET_GAUNTLET(ToolMaterials.IRON,0, -1.53f, "minecraft:iron_ingot"),
    GAUNTLET_MAULERS(ToolMaterials.DIAMOND,1, -1.53f, "minecraft:diamond"),
    GAUNTLET_SOUL_FISTS(ToolMaterials.NETHERITE,0, -1.53f, "minecraft:netherite_scrap");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    GauntletsID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<GauntletsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.gauntletsEnabled;
    }

    public static EnumMap<GauntletsID, McdwGauntlet> getItemsEnum() {
        return ItemsInit.gauntletItems;
    }

    public static HashMap<GauntletsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.gauntletSpawnRates;
    }

    public static HashMap<GauntletsID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.gauntletStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwGauntlet getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<GauntletsID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.gauntletStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.gauntletStats.get(this);
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
    public McdwGauntlet makeWeapon() {
        McdwGauntlet mcdwGauntlet = new McdwGauntlet(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwGauntlet);
        return mcdwGauntlet;
    }
}