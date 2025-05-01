/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.function.Consumer;

@Mixin(Enchantment.class)
public interface EnchantmentAccessor {
    @Invoker
    static <T> void callApplyEffects(List<EnchantmentEffectEntry<T>> conditions, LootContext lootContext, Consumer<T> effectConsumer) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    static LootContext callCreateHitBlockLootContext(ServerWorld world, int level, Entity entity, Vec3d pos, BlockState state) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    static LootContext callCreateEnchantedEntityLootContext(ServerWorld world, int level, Entity entity, Vec3d pos) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    static LootContext callCreateEnchantedLocationLootContext(ServerWorld world, int level, Entity entity, boolean enchantmentActive) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    static LootContext callCreateEnchantedItemLootContext(ServerWorld world, int level, ItemStack stack) {
        throw new UnsupportedOperationException();
    }

    @Accessor
    ComponentMap getEffects();
}