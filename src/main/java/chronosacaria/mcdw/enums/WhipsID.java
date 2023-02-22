package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwWhip;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum WhipsID implements IMcdwWeaponID, IMeleeWeaponID {
    WHIP_VINE_WHIP(ToolMaterials.IRON, 5, -3.1f, "minecraft:vine"),
    WHIP_WHIP(ToolMaterials.IRON, 3, -3.1f, "minecraft:string");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;
    WhipsID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<WhipsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.WHIPS_ENABLED;
    }

    public static EnumMap<WhipsID, McdwWhip> getItemsEnum() {
        return ItemsRegistry.WHIP_ITEMS;
    }

    public static HashMap<WhipsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.WHIP_SPAWN_RATES;
    }

    public static HashMap<WhipsID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.whipStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwWhip getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<WhipsID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.whipStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.whipStats.get(this);
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
    public McdwWhip makeWeapon() {
        McdwWhip mcdwWhip = new McdwWhip(ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwWhip);
        return mcdwWhip;
    }
}
