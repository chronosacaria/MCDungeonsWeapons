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
import net.minecraft.util.registry.Registry;

public class BonusShotEnchantment extends RangedEnchantment {
    public BonusShotEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("bonus_shot"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof MultishotEnchantment);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT);
    }

    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.BONUS_SHOT);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem || stack.getItem() instanceof BowItem;
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