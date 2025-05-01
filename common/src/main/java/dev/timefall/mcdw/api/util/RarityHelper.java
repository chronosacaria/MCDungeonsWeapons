/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.util;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class RarityHelper {
    public static Rarity fromToolMaterial(ToolMaterial material){
        return
                material == ToolMaterials.NETHERITE ? Rarity.EPIC :
                material == ToolMaterials.DIAMOND ? Rarity.RARE :
                material == ToolMaterials.GOLD ? Rarity.UNCOMMON :
                material == ToolMaterials.IRON ? Rarity.UNCOMMON : Rarity.COMMON;
    }
}
