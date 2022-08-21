package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSword;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.items.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SwordsID implements IMcdwWeaponID, IMeleeWeaponID {
    SWORD_BEESTINGER(ToolMaterials.IRON, 0, -1.1f, "minecraft:iron_ingot"),
    SWORD_BROADSWORD(ToolMaterials.IRON, 5, -3.0f, "minecraft:iron_ingot"),
    SWORD_BROKEN_SAWBLADE(ToolMaterials.IRON,3, -2.4f, "minecraft:iron_ingot"),
    SWORD_CLAYMORE(ToolMaterials.IRON, 7, -3.2f, "minecraft:iron_ingot"),
    SWORD_CORAL_BLADE(ToolMaterials.IRON,3, -2.4f, "minecraft:iron_ingot"),
    SWORD_CUTLASS(ToolMaterials.IRON,2, -2.3f, "minecraft:iron_ingot"),
    SWORD_DANCERS_SWORD(ToolMaterials.IRON,3, -2.0f, "minecraft:iron_ingot"),
    SWORD_DARK_KATANA(ToolMaterials.NETHERITE,4, -2.9f, "minecraft:netherite_scrap"),
    SWORD_DIAMOND_SWORD_VAR(ToolMaterials.DIAMOND,3, -2.4f, "minecraft:diamond"),
    SWORD_FREEZING_FOIL(ToolMaterials.IRON,1, -1.1f, "minecraft:iron_ingot"),
    SWORD_FROST_SLAYER(ToolMaterials.DIAMOND, 6, -3.2f, "minecraft:diamond"),
    SWORD_GREAT_AXEBLADE(ToolMaterials.IRON, 7, -3.2f, "minecraft:iron_ingot"),
    SWORD_HAWKBRAND(ToolMaterials.IRON,6, -2.9f, "minecraft:iron_ingot"),
    SWORD_HEARTSTEALER(ToolMaterials.DIAMOND, 6, -3.2f, "minecraft:diamond"),
    SWORD_IRON_SWORD_VAR(ToolMaterials.IRON,3, -2.4f, "minecraft:iron_ingot"),
    SWORD_KATANA(ToolMaterials.IRON,4, -2.9f, "minecraft:iron_ingot"),
    SWORD_MASTERS_KATANA(ToolMaterials.DIAMOND,4, -2.9f, "minecraft:diamond"),
    SWORD_MECHANIZED_SAWBLADE(ToolMaterials.DIAMOND,3, -2.4f, "minecraft:blaze_rod"),
    SWORD_NAMELESS_BLADE(ToolMaterials.IRON,4, -2.3f, "minecraft:iron_ingot"),
    SWORD_OBSIDIAN_CLAYMORE(ToolMaterials.NETHERITE, 6, -3.3f, "minecraft:netherite_scrap"),
    SWORD_RAPIER(ToolMaterials.IRON,0, -1.14f, "minecraft:iron_ingot"),
    SWORD_SINISTER(ToolMaterials.IRON,6, -2.9f, "minecraft:iron_ingot"),
    SWORD_SPONGE_STRIKER(ToolMaterials.DIAMOND,3, -2.4f, "minecraft:diamond"),
    //TODO Change damage back to 6 upon implementation of SharedPainEnchantment
    SWORD_THE_STARLESS_NIGHT(ToolMaterials.NETHERITE, 7, -3.3f, "minecraft:netherite_scrap");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    SwordsID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<SwordsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.SWORDS_ENABLED;
    }

    public static EnumMap<SwordsID, McdwSword> getItemsEnum() {
        return ItemsInit.SWORD_ITEMS;
    }

    public static HashMap<SwordsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SWORD_SPAWN_RATES;
    }

    public static HashMap<SwordsID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.swordStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSword getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<SwordsID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.swordStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.swordStats.get(this);
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
    public McdwSword makeWeapon() {
        McdwSword mcdwSword = new McdwSword(ItemsInit.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwSword);
        return mcdwSword;
    }
}
