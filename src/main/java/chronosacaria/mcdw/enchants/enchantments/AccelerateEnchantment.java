package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwBow;
import chronosacaria.mcdw.bases.McdwLongBow;
import chronosacaria.mcdw.bases.McdwShortBow;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class AccelerateEnchantment extends RangedEnchantment {
    public AccelerateEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("accelerate"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.enableRandomSelection.get(EnchantmentsID.ACCELERATE);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.ACCELERATE)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.enableVillageTrading.get(EnchantmentsID.ACCELERATE);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof McdwBow || stack.getItem() instanceof McdwShortBow || stack.getItem() instanceof McdwLongBow;
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