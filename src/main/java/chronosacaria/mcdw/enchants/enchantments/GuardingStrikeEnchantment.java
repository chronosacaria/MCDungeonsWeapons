package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class GuardingStrikeEnchantment extends Enchantment {

    public GuardingStrikeEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GUARDING_STRIKE)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("guarding_strike"), this);
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GUARDING_STRIKE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.enableRandomSelection.get(EnchantmentsID.GUARDING_STRIKE);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GUARDING_STRIKE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.enableVillageTrading.get(EnchantmentsID.GUARDING_STRIKE);
    }

    @Override
    public int getMinPower(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }
}
