package chronosacaria.mcdw.enchants.types;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class DamageBoostEnchantment extends Enchantment {
    protected DamageBoostEnchantment(Enchantment.Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots){
        super(rarity, enchantmentTarget, equipmentSlots);
    }
}
