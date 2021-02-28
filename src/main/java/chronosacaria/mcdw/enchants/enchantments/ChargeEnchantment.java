package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class ChargeEnchantment extends Enchantment {

    public ChargeEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("charge")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("charge"), this);
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

}
