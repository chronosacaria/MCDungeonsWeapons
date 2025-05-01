/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.registries.items.McdwLongbowItemRegistry;
import dev.timefall.mcdw.registries.items.McdwShieldItemRegistry;
import dev.timefall.mcdw.registries.items.McdwSwordItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
    public static final RegistryKey<ItemGroup> MELEE = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("weapons/melee"));
    public static final RegistryKey<ItemGroup> RANGED = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("weapons/ranged"));
    public static final RegistryKey<ItemGroup> SHIELDS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("weapons/shields"));
    public static final RegistryKey<ItemGroup> ENCHANTMENTS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcdw.ID("enchantments"));

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, MELEE, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.weapons/melee"))
                .icon(() -> {
                    if(McdwSwordItemRegistry.SWORD_HEARTSTEALER != null) {
                        return new ItemStack(McdwSwordItemRegistry.SWORD_HEARTSTEALER);
                    }
                    return new ItemStack(Items.IRON_SWORD);
                })
                .build());
        Registry.register(Registries.ITEM_GROUP, RANGED, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.weapons/ranged"))
                .icon(() -> {
                    if(McdwLongbowItemRegistry.LONGBOW_LONGBOW != null) {
                        return new ItemStack(McdwLongbowItemRegistry.LONGBOW_LONGBOW);
                    }
                    return new ItemStack(Items.BOW);
                })
                .build());
        Registry.register(Registries.ITEM_GROUP, SHIELDS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.weapons/shields"))
                .icon(() -> {
                    if (McdwShieldItemRegistry.SHIELD_ROYAL_GUARD_SHIELD != null) {
                        return new ItemStack(McdwShieldItemRegistry.SHIELD_ROYAL_GUARD_SHIELD);
                    }
                    return new ItemStack(Items.SHIELD);
                })
                .build());
        Registry.register(Registries.ITEM_GROUP, ENCHANTMENTS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcdw.enchantments"))
                .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
                .build());
    }
}
