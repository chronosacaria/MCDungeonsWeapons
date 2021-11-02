package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class CobwebShotEnchantment extends Enchantment {

    public CobwebShotEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("cobweb_shot")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("cobweb_shot"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return McdwEnchantsConfig.getValue("enable_op_mixing") || !(other instanceof AOEEnchantment);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return McdwEnchantsConfig.getValue("cobweb_shot");
    }
}

