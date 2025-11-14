/*
 * Timefall Development License 1.2
 * Copyright (c) 2025. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.api.util.move_to_mcdx;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.Optional;

public class EnchantHelper {
    public static int mcdx$getEnchantmentLevel(RegistryKey<Enchantment> enchantment, LivingEntity enchantedEntity, boolean isOffHandStack) {
        if (FabricLoader.getInstance().isModLoaded("bettercombat")) {
            // Better Combat can figure out if the hit was done by offhand
            return EnchantHelper.mcdx$getLevel(enchantment, enchantedEntity);
        } else {
            // We know if the hit was done by offhand
            return EnchantHelper.mcdx$getLevel(enchantment, enchantedEntity.getEntityWorld(), isOffHandStack ? enchantedEntity.getOffHandStack() : enchantedEntity.getMainHandStack());
        }
    }

    public static int mcdx$getLevel(RegistryKey<Enchantment> registryKey, LivingEntity livingEntity) {
        Optional<? extends RegistryEntry<Enchantment>> entry = livingEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(registryKey);
        return entry.map((e) -> EnchantmentHelper.getEquipmentLevel(e, livingEntity)).orElse(0);
    }

    public static int mcdx$getLevel(RegistryKey<Enchantment> registryKey, World world, ItemStack stack) {
        if (stack == null) return 0;
        ItemEnchantmentsComponent itemEnchantmentsComponent = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        Optional<? extends RegistryEntry<Enchantment>> entry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(registryKey);

        return entry.map(itemEnchantmentsComponent::getLevel).orElse(0);
    }
}
