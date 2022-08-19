package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwPick;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum PicksID implements IMcdwWeaponID, IMeleeWeaponID {
    PICK_DIAMOND_PICKAXE_VAR(ToolMaterials.DIAMOND,1, -2.8f, "minecraft:diamond"),
    PICK_HAILING_PINNACLE(ToolMaterials.DIAMOND,1, -2.8f, "minecraft:diamond"),
    PICK_HOWLING_PICK(ToolMaterials.IRON,1, -2.8f, "minecraft:iron_ingot"),
    PICK_MOUNTAINEER_PICK(ToolMaterials.IRON,1, -2.8f, "minecraft:iron_ingot");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    PicksID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<PicksID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.PICKS_ENABLED;
    }

    public static EnumMap<PicksID, McdwPick> getItemsEnum() {
        return ItemsInit.pickItems;
    }

    public static HashMap<PicksID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.PICK_SPAWN_RATES;
    }

    public static HashMap<PicksID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.pickStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwPick getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<PicksID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.pickStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.pickStats.get(this);
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
    public McdwPick makeWeapon() {
        McdwPick mcdwPick = new McdwPick(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwPick);
        return mcdwPick;
    }
}