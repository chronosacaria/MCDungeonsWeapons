package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class TempoTheftEnchantment extends Enchantment {
    public TempoTheftEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("tempo_theft")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("tempo_theft"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

}
