/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchantment.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GoalUtils {

    @Nullable
    public static LivingEntity getOwner(AbstractHorseEntity horseBaseEntity){
        try{
            UUID ownerUniqueId = horseBaseEntity.getOwnerUuid();
            return ownerUniqueId == null ? null : horseBaseEntity.getWorld().getPlayerByUuid(ownerUniqueId);
        }catch (IllegalArgumentException var2) {
            return null;
        }
    }
}