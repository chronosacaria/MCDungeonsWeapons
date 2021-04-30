package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class StunningEnchantment extends Enchantment{

    public StunningEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("stunning")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("stunning"), this);
        }
    }

    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return McdwEnchantsConfig.getValue("stunning");
    }

}
