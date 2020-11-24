package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

//TODO Actually get it to work...
public class Exploding extends Enchantment {

    public Exploding (Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "exploding"),this);
    }

    public int getMaxLevel() {
        return 3;
    }


    
}
