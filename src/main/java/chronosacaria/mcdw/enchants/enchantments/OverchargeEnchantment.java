package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.bases.McdwShortBow;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.enchantment.PunchEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class OverchargeEnchantment extends RangedEnchantment {
    public OverchargeEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("overcharge"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof PunchEnchantment || other instanceof PowerEnchantment || other instanceof AccelerateEnchantment);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.enableRandomSelection.get(EnchantmentsID.OVERCHARGE);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.OVERCHARGE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.enableVillageTrading.get(EnchantmentsID.OVERCHARGE);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof McdwBow || stack.getItem() instanceof McdwLongBow || stack.getItem() instanceof McdwShortBow;
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
