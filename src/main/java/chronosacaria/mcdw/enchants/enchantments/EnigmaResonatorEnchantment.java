package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class EnigmaResonatorEnchantment extends DamageBoostEnchantment {

    public EnigmaResonatorEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (McdwEnchantsConfig.getValue("enigma_resonator")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("enigma_resonator"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return McdwEnchantsConfig.getValue("enable_op_mixing") || !(other instanceof AOEEnchantment || other instanceof DamageBoostEnchantment);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return McdwEnchantsConfig.getValue("enigma_resonator");
    }
}
