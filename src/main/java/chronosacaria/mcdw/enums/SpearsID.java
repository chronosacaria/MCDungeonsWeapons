package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwSpear;
import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import chronosacaria.mcdw.registries.ItemsRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;
import java.util.HashMap;

import static chronosacaria.mcdw.Mcdw.CONFIG;

public enum SpearsID implements IMeleeWeaponID {
    SPEAR_SPEAR(ToolMaterials.IRON,4, -2.5f, "minecraft:iron_ingot"),
    SPEAR_WHISPERING_SPEAR(ToolMaterials.IRON,5, -2.5f, "minecraft:iron_ingot"),
    SPEAR_FORTUNE(ToolMaterials.IRON,5, -2.5f, "minecraft:iron_ingot");

    private final ToolMaterial material;
    private final int damage;
    private final float attackSpeed;
    private final String[] repairIngredient;

    SpearsID(ToolMaterial material, int damage, float attackSpeed, String... repairIngredient) {
        this.material = material;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.repairIngredient = repairIngredient;
    }

    public static HashMap<SpearsID, Boolean> getEnabledItems(){
        return Mcdw.CONFIG.mcdwEnableItemsConfig.SPEARS_ENABLED;
    }

    public static EnumMap<SpearsID, McdwSpear> getItemsEnum() {
        return ItemsRegistry.SPEAR_ITEMS;
    }

    public static HashMap<SpearsID, Integer> getSpawnRates() {
        return Mcdw.CONFIG.mcdwNewlootConfig.SPEAR_SPAWN_RATES;
    }

    public static HashMap<SpearsID, MeleeStats> getWeaponStats() {
        return CONFIG.mcdwNewStatsConfig.spearStats;
    }

    @Override
    public Boolean isEnabled(){
        return getEnabledItems().get(this);
    }

    @Override
    public McdwSpear getItem() {
        return getItemsEnum().get(this);
    }

    @Override
    public Integer getItemSpawnRate() {
        return getSpawnRates().get(this);
    }

    @Override
    public HashMap<SpearsID, MeleeStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.spearStats;
    }

    @Override
    public MeleeStats getWeaponItemStats() {
        return getWeaponStats().get(this);
    }

    @Override
    public MeleeStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig) {
        return mcdwNewStatsConfig.spearStats.get(this);
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
    public McdwSpear makeWeapon() {
        McdwSpear mcdwSpear = new McdwSpear(ItemsRegistry.stringToMaterial(this.getWeaponItemStats().material),
                this.getWeaponItemStats().damage, this.getWeaponItemStats().attackSpeed, this.getWeaponItemStats().repairIngredient);

        getItemsEnum().put(this, mcdwSpear);
        return mcdwSpear;
    }

    //@Override
    //public Map<Enchantment, Integer> getInnateEnchantments() {
    //    return switch (this) {
    //        case SPEAR_SPEAR -> null;
    //        case SPEAR_WHISPERING_SPEAR -> Map.of(EnchantsRegistry.ECHO, 1);
    //        case SPEAR_FORTUNE -> Map.of(Enchantments.LOOTING, 1);
    //    };
    //}
}
