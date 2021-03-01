package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class RushdownEnchantment extends Enchantment {
    public RushdownEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("rushdown")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("rushdown"), this);
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

}
