/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.bases;

import dev.timefall.mcdw.api.interfaces.IMcdwDrawSpeed;
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.registries.ItemGroupRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class McdwCrossbowItem extends CrossbowItem implements IMcdwDrawSpeed {

    private final ToolMaterial material;
    @SuppressWarnings("CanBeFinal")
    IMcdwWeaponStats.RangedStats itemStats;
    public final int drawSpeed;
    public final int range;

    public McdwCrossbowItem(IMcdwWeaponStats.RangedStats itemStats, ToolMaterial material, Item.Settings settings, int drawSpeed, int range) {
        super(settings);
        this.itemStats = itemStats;
        this.material = material;
        this.drawSpeed = drawSpeed;
        this.range = range;

        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.RANGED).register(entries -> entries.add(this.getDefaultStack()));
    }

    public static float mcdw$getSpeed(ChargedProjectilesComponent stack) {
        return stack.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.2F;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int getEnchantability() {
        return material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return itemStats.repairIngredient.toIngredient().test(ingredient);
    }

    @Override
    public float getDrawSpeed() {
        return this.drawSpeed;
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, tooltipContext, tooltip, type);
        CleanlinessHelper.mcdw$tooltipHelper(stack, tooltip, 19);
    }
}