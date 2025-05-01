/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries;

import com.blamejared.clumps.api.events.ClumpsEvents;
import dev.timefall.mcdw.damagesources.OffHandDamageSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;

public class CompatRegistry {
    public static void register() {
        if (FabricLoader.getInstance().isModLoaded("clumps")) {
            ClumpsEvents.VALUE_EVENT.register(event -> {
                int experienceAmount = event.getValue();
                PlayerEntity playerEntity = event.getPlayer();
                boolean isOffHandAttack = playerEntity.getRecentDamageSource() instanceof OffHandDamageSource;

                //if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.SOUL_DEVOURER).mcdw$getIsEnabled())
                //    experienceAmount = EnchantmentEffects.soulDevourerExperience(playerEntity, experienceAmount);

                //if (Mcdw.CONFIG.mcdwEnchantmentsConfig.ENCHANTMENT_CONFIG.get(EnchantmentsID.ANIMA_CONDUIT).mcdw$getIsEnabled())
                //    experienceAmount = EnchantmentEffects.animaConduitExperience(playerEntity, experienceAmount, isOffHandAttack);

                event.setValue(experienceAmount);
                return null;
            });
        }
    }
}
