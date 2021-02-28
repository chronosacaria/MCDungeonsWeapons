package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class AccelerateEnchantment extends RangedEnchantment {
    public AccelerateEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("accelerate")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("accelerate"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

}