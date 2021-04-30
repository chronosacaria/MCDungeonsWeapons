package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.configs.McdwEnchantsConfig;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class SoulSiphonEnchantment extends AOEEnchantment {

    public SoulSiphonEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (McdwEnchantsConfig.getValue("soul_siphon")) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("soul_siphon"), this);
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return McdwEnchantsConfig.getValue("extra_xp_healing") || !(other instanceof AnimaConduitEnchantment);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return McdwEnchantsConfig.getValue("soul_siphon");
    }
    
}
