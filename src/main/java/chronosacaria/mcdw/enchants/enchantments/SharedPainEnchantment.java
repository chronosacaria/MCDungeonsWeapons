package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SwordsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.registry.Registry;

public class SharedPainEnchantment extends Enchantment {
    public SharedPainEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHARED_PAIN)) {
            Registry.register(Registries.ENCHANTMENT, Mcdw.ID("shared_pain"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 1;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHARED_PAIN)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_RANDOM_SELECTION.get(EnchantmentsID.SHARED_PAIN);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_ENCHANTMENTS.get(EnchantmentsID.SHARED_PAIN)
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENABLE_VILLAGER_TRADING.get(EnchantmentsID.SHARED_PAIN);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(SwordsID.SWORD_THE_STARLESS_NIGHT.getItem());
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof DamageBoostEnchantment);
    }

    @Override
    public int getMinPower(int level) {
        return 0;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level);
    }

}