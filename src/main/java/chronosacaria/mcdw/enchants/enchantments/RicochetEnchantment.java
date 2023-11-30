package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.types.RangedEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RicochetEnchantment extends RangedEnchantment {
    public RicochetEnchantment(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getIsEnabled()) {
            Registry.register(Registries.ENCHANTMENT, Mcdw.ID("ricochet"), this);
            ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.ENCHANTMENTS).register(entries -> {
                // For loop creates first 3 levels of enchanted books
                for (int i = 1; i <= getMaxLevel(); i++)
                    entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(this, i)));
            });
        }
    }

    @Override
    public int getMaxLevel(){
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getMaxLevel();
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getIsEnabled()
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getIsAvailableForRandomSelection();
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getIsEnabled()
                && Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.RICOCHET).mcdw$getIsAvailableForEnchantedBookOffer();
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem || stack.getItem() instanceof BowItem;
    }

}