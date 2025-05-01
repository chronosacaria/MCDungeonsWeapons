/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.data;

import dev.timefall.mcdw.enchantment.EnchantmentIds;
import dev.timefall.mcdw.registries.tag.McdwEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class McdwEnchantmentTagGenerator extends FabricTagProvider.EnchantmentTagProvider {
    public McdwEnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        // ENCHANTMENT TAGS

        getOrCreateTagBuilder(McdwEnchantmentTags.AOE_EXCLUSIVE)
                .setReplace(false)
                .addOptional(EnchantmentIds.ECHO);

        getOrCreateTagBuilder(McdwEnchantmentTags.AOE_AND_DAMAGE_EXCLUSIVE)
                .setReplace(false)
                .addTag(McdwEnchantmentTags.AOE_EXCLUSIVE)
                .addTag(McdwEnchantmentTags.DAMAGE_EXCLUSIVE);

        getOrCreateTagBuilder(McdwEnchantmentTags.DAMAGE_EXCLUSIVE)
                .setReplace(false)
                .addOptional(EnchantmentIds.DYNAMO)
                .addOptional(EnchantmentIds.PAIN_CYCLE);

        getOrCreateTagBuilder(McdwEnchantmentTags.EXPERIENCE_EXCLUSIVE)
                .setReplace(false)
                .addOptional(EnchantmentIds.ANIMA_CONDUIT)
                .addOptional(EnchantmentIds.SOUL_DEVOURER)
                .addOptional(EnchantmentIds.SOUL_SIPHON);

        getOrCreateTagBuilder(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                .setReplace(false)
                .addOptional(EnchantmentIds.ANIMA_CONDUIT)
                .addOptional(EnchantmentIds.LEECHING)
                .addOptional(EnchantmentIds.RADIANCE);

    }

}