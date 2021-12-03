package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.SwordsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

import java.util.EnumMap;

@Config(name = "mcdw_stats_config")
public class McdwNewStatsConfig implements ConfigData {

    public EnumMap<SwordsID, MeleeWeaponStats> meleeWeaponStats = new EnumMap<>(SwordsID.class);

    // convenience methods:
    protected MeleeWeaponStats swordStats(int damage, float attackSpeed, ToolMaterial toolMaterial, SwordsID swordsID){
        return meleeWeaponStats.get(swordsID).meleeWeaponStats(damage, attackSpeed, toolMaterial);
    }

    public McdwNewStatsConfig() {
        for (SwordsID swordsID : SwordsID.values()) {
            meleeWeaponStats.put(swordsID, new MeleeWeaponStats());
        }

        // Sword Configs
        swordStats(5, -3.0f, ToolMaterials.IRON, SwordsID.SWORD_CLAYMORE);

    }


}
