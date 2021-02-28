package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class IllagersBaneEnchantment extends Enchantment {
    public IllagersBaneEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("illagers_bane")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("illagers_bane"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group){
        if (group == EntityGroup.ILLAGER){
            return 1 + (0.2f * level);
        } else {
            return 0f;
        }
    }
}
