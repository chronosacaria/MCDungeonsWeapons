package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AccelerateEnchantment extends RangedEnchantment {
    public AccelerateEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ACCELERATE)) {
            Registry.register(Registries.ENCHANTMENT, Mcdw.ID("accelerate"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ACCELERATE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_RANDOM_SELECTION.get(EnchantmentsID.ACCELERATE);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.ACCELERATE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_VILLAGER_TRADING.get(EnchantmentsID.ACCELERATE);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof BowItem || stack.getItem() instanceof CrossbowItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof MultishotEnchantment || other instanceof OverchargeEnchantment);
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